package com.alura.hotelalura.ssr;

import com.alura.hotelalura.model.Habitacion;
import com.alura.hotelalura.repository.dto.ReservaInfoEmpleados;
import com.alura.hotelalura.repository.persistence.HabitacionRepository;
import com.alura.hotelalura.repository.persistence.HabitacionTipoRepository;
import com.alura.hotelalura.repository.persistence.ReservaRepository;
import com.alura.hotelalura.service.HabitacionService;
import com.alura.hotelalura.service.type.HabitacionTipoService;
import com.alura.hotelalura.ssr.dto.ResultadoLista;
import com.alura.hotelalura.ssr.dto.ResultadoListaEmpleado;
import com.alura.hotelalura.ssr.dto.RtsReservacionEmpresa;
import com.google.inject.Injector;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SSrEmpleadoController
{
    private final Javalin javalin;
    private final Injector injector;
    private ReservaRepository reservaService;
    private HabitacionTipoRepository habitacionTipoService;
    private HabitacionRepository habitacionService;


    public SSrEmpleadoController(Javalin javalin, Injector injector)
    {
        this.javalin = javalin;
        this.injector = injector;
        this.reservaService = injector.getInstance(ReservaRepository.class);
        this.habitacionTipoService = injector.getInstance(HabitacionTipoService.class);
        this.habitacionService = injector.getInstance(HabitacionService.class);
        cargarEvento();
        cargarHtml();
    }

    private void cargarEvento()
    {
        javalin.after("/listar/reservaciones",new CoockieControllerEmpleado.MiddleareEmpleado());
        javalin.after("/busqueda",new CoockieControllerEmpleado.MiddleareEmpleado());
        javalin.after("/administar/reservacion",new CoockieControllerEmpleado.MiddleareEmpleado());
    }

    private void cargarHtml()
    {
       javalin.get("/listar/reservaciones",context -> {

           context.render("employer/index.jte", Collections.singletonMap("resultadoListaEmpleado",
                                                                                new ResultadoListaEmpleado(reservaService.listaReservaActual(0,10)
                                                                                                           ,context.sessionAttribute("empleado"))));
       });

       javalin.get("/busqueda",context -> {

           int pagina = Integer.parseInt(Objects.requireNonNull(context.queryParam("buscar")));
           int skip, limit;
           if(pagina<=0)
            {pagina = 1;}
            skip = (pagina-1) * 10;
            limit = skip + 10;


           context.render("employer/index.jte",Collections.singletonMap("resultadoListaEmpleado",new ResultadoListaEmpleado(new ArrayList<>(reservaService.listaReservaActual(skip,limit)),
                                                                                                                                   context.sessionAttribute("empleado"))));
       });

       javalin.get("/administar/reservacion",context -> {
           context.render("employer/reservacion.jte",Collections.singletonMap("respuesta",new RtsReservacionEmpresa(habitacionTipoService.listar(),
                                                                                                                           context.sessionAttribute("empleado"))));
       });
    }


}
