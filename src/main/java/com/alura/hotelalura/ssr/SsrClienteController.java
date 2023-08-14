package com.alura.hotelalura.ssr;

import com.alura.hotelalura.model.Cliente;
import com.alura.hotelalura.model.Habitacion;
import com.alura.hotelalura.model.Reserva;
import com.alura.hotelalura.model.type.HabitacionTipo;
import com.alura.hotelalura.model.type.MetodoPago;
import com.alura.hotelalura.repository.dto.RegistrarReserva;
import com.alura.hotelalura.repository.dto.ReservaInfo;
import com.alura.hotelalura.repository.dto.ReservaInfoSsr;
import com.alura.hotelalura.repository.persistence.*;
import com.alura.hotelalura.service.ClienteService;
import com.alura.hotelalura.service.HabitacionService;
import com.alura.hotelalura.service.ReservaService;
import com.alura.hotelalura.service.type.HabitacionTipoService;
import com.alura.hotelalura.ssr.dto.ListarCatrgoriaMetodo;
import com.alura.hotelalura.ssr.error.ErrorResponse;
import com.google.inject.Injector;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SsrClienteController
{
    private final Javalin javalin;
    private final Injector injector;
    private ErrorResponse response;
    private final ReservaRepository reservaService;
    private final MetodoDePagoRepository metodoDePagoService;
    private final HabitacionTipoRepository habitacionTipoService;
    private ListarCatrgoriaMetodo metodo;
    private ReservaInfoSsr infoSsr = null;
    private final ClienteRepository clienteService;
    private final HabitacionRepository habitacionService;


    public SsrClienteController(Javalin javalin, Injector injector)
    {
        this.javalin = javalin;
        this.injector = injector;
        this.reservaService = injector.getInstance(ReservaService.class);
        this.metodoDePagoService = injector.getInstance(MetodoDePagoRepository.class);
        this.habitacionTipoService = injector.getInstance(HabitacionTipoService.class);
        this.clienteService = injector.getInstance(ClienteRepository.class);
        this.habitacionService = injector.getInstance(HabitacionService.class);

        cargarMiddleware();
        cargarEvento();
    }

    private void cargarMiddleware()
    {
        javalin.after("/listar/reservaciones",new CookieController.MiddewareCookie());
        javalin.after("/generar/reservacion",new CookieController.MiddewareCookie());
    }

    private void cargarEvento()
    {


        javalin.get("/listar/reservaciones",context -> {
            List<ReservaInfo> infoList = reservaService.listarReservaPorCliente(CookieController.getDinUsusario());
            context.render("index.jte", Collections.singletonMap("infoList",infoList));
        });

        javalin.get("/generar/reservacion",context -> {
             this.response = null;
             this.infoSsr = null;
             metodo = new ListarCatrgoriaMetodo(metodoDePagoService.listar(),
                                                habitacionTipoService.listar(),
                                                                     response,
                                                                      infoSsr);

            context.render("reservacion.jte",Collections.singletonMap("metodo",metodo));
        });


        javalin.post("/generar/reservacion",this::generarReservacion);


    }

    private void generarReservacion(Context context)
    {
        this.response = null;
        this.infoSsr = null;

        LocalDate checkIn = LocalDate.parse(context.formParam("checkIn"));
        LocalDate checkOut = LocalDate.parse(context.formParam("checkOut"));
        String categoria = context.formParam("categoria");
        String metodoPago = context.formParam("metodoPago");

        LocalDate inicio = checkIn.atStartOfDay().toLocalDate();
        LocalDate fin = checkOut.atStartOfDay().toLocalDate();

        if(inicio.isEqual(fin))
        {
            metodo = new ListarCatrgoriaMetodo(metodoDePagoService.listar(),
                                               habitacionTipoService.listar(),
                                               new ErrorResponse(400,"Las Fechas son iguales"),
                                               infoSsr);

            context.render("reservacion.jte",Collections.singletonMap("metodo",metodo));
        }

        else if(checkIn.isBefore(LocalDate.now()) || checkOut.isBefore(LocalDate.now()) ||
            checkIn.isAfter(checkOut))
        {
             metodo = new ListarCatrgoriaMetodo(metodoDePagoService.listar(),
                                                habitacionTipoService.listar(),
                                                new  ErrorResponse(400,"Las fechas pertenecen al día o días anteriores"),
                                                infoSsr);

            context.render("reservacion.jte",Collections.singletonMap("metodo",metodo));
        }else
        {
            try
            {

                Cliente cliente = clienteService.buscar(CookieController.getDinUsusario());
                Reserva reserva = new Reserva(cliente,checkIn,checkOut);
                reserva.setMetodoPago(metodoDePagoService.buscar(metodoPago));
                reserva.setValorReserva(habitacionTipoService.consultarPrecioHabitacion(new RegistrarReserva(checkIn,checkOut,categoria,metodoPago)));

                Habitacion habitacion = habitacionService.asignarHabitacion(categoria);
                habitacionService.guardarHabitacionPorReseva(habitacion);

                reserva.setHabitacion(habitacion);
                reservaService.generar(reserva);

                this.infoSsr  = reservaService.buscarResultado(CookieController.getDinUsusario());
                System.out.println(infoSsr);
                metodo = new ListarCatrgoriaMetodo(metodoDePagoService.listar(),
                                                   habitacionTipoService.listar(),
                                                   new ErrorResponse(200,"0k"),
                                                   this.infoSsr
                                                  );

                context.render("reservacion.jte",Collections.singletonMap("metodo",metodo));



            }catch (Exception ex){
                metodo = new ListarCatrgoriaMetodo(metodoDePagoService.listar(),
                                                   habitacionTipoService.listar(),
                                                   new ErrorResponse(500,ex.getMessage()),
                                                    infoSsr);
                context.render("reservacion.jte",Collections.singletonMap("metodo",metodo));
            }






        }


    }
}
