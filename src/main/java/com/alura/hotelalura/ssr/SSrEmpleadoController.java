package com.alura.hotelalura.ssr;

import com.alura.hotelalura.model.Cliente;
import com.alura.hotelalura.model.Empleado;
import com.alura.hotelalura.model.Habitacion;
import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.model.type.HabitacionTipo;
import com.alura.hotelalura.model.type.MetodoPago;
import com.alura.hotelalura.repository.dto.RegistrarReserva;
import com.alura.hotelalura.repository.dto.ReservaInfoEmpleados;
import com.alura.hotelalura.repository.persistence.*;
import com.alura.hotelalura.service.EmpleadoService;
import com.alura.hotelalura.service.HabitacionService;
import com.alura.hotelalura.service.type.HabitacionTipoService;
import com.alura.hotelalura.service.type.MetodoDePagoService;
import com.alura.hotelalura.ssr.dto.*;
import com.alura.hotelalura.ssr.error.ErrorResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Injector;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

public class SSrEmpleadoController
{
    private final Javalin javalin;
    private final Injector injector;
    private ReservaRepository reservaService;
    private HabitacionTipoRepository habitacionTipoService;
    private HabitacionRepository habitacionService;
    private MetodoDePagoRepository metodoDePagoService;
    private EmpleadoRepository empleadoService;
    private ErrorResponse myResponse = null;
    private ConsultaInfoReservacion reservacion = null;


    public SSrEmpleadoController(Javalin javalin, Injector injector)
    {
        this.javalin = javalin;
        this.injector = injector;
        this.reservaService = injector.getInstance(ReservaRepository.class);
        this.habitacionTipoService = injector.getInstance(HabitacionTipoService.class);
        this.habitacionService = injector.getInstance(HabitacionService.class);
        this.metodoDePagoService = injector.getInstance(MetodoDePagoService.class);
        this.empleadoService = injector.getInstance(EmpleadoService.class);
        cargarEvento();
        cargarHtml();
    }

    private void cargarEvento()
    {
        javalin.after("/listar/reservaciones",new CoockieControllerEmpleado.MiddleareEmpleado());
        javalin.after("/busqueda",new CoockieControllerEmpleado.MiddleareEmpleado());
        javalin.after("/administar/reservacion",new CoockieControllerEmpleado.MiddleareEmpleado());
        javalin.after("/buscar/reservacion",new CoockieControllerEmpleado.MiddleareEmpleado());
        javalin.after("/busqueda/reservacion",new CoockieControllerEmpleado.MiddleareEmpleado());
        javalin.after("/eliminar/reservacion",new CoockieControllerEmpleado.MiddleareEmpleado());
        javalin.after("/consultar/reservacion/empleado",new CoockieControllerEmpleado.MiddleareEmpleado());
        javalin.after("/administar/pagos",new CoockieControllerEmpleado.MiddleareEmpleado());
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
                                                                                                                              context.sessionAttribute("empleado"),
                                                                                                                               new Boolean[]{false,false})));
       });

        javalin.get("/buscar/reservacion",context ->
            context.render("employer/buscar.jte",Collections.singletonMap("listarBusquedaEmpleado",new ListarBusquedaEmpleado(null,context.sessionAttribute("empleado"),true)))

        );

        javalin.get("/busqueda/reservacion", context ->{
            String busqueda = Optional.ofNullable(context.queryParam("busqueda")).orElse(" ");
            ListarBusquedaEmpleado listarBusquedaEmpleado = null;
            try
            { listarBusquedaEmpleado = new ListarBusquedaEmpleado(reservaService.busquedaEmpleado(busqueda),context.sessionAttribute("empleado"),true); }
            catch (Exception ex) { listarBusquedaEmpleado = new ListarBusquedaEmpleado(null,context.sessionAttribute("empleado"),false);}
            context.render("employer/buscar.jte",Collections.singletonMap("listarBusquedaEmpleado",listarBusquedaEmpleado));
        });

        javalin.get("/eliminar/reservacion", context ->{
            String busqueda = Optional.ofNullable(context.queryParam("busqueda")).orElse(" ");
            reservaService.eliminar(busqueda);
            context.render("employer/buscar.jte",Collections.singletonMap("listarBusquedaEmpleado", new ListarBusquedaEmpleado(null,context.sessionAttribute("empleado"),true)));
        });

        javalin.get("/consultar/reservacion/empleado",context -> {
            this.reservacion = new ConsultaInfoReservacion(
                                                        habitacionTipoService.listar(),
                                                        null,
                                                        null,
                                                        context.sessionAttribute("empleado"));
            context.render("employer/consultar.jte",Collections.singletonMap("reservacion",reservacion));

        });

        javalin.get("/administar/pagos",context -> {
            context.render("employer/pagos.jte",Collections.singletonMap("respuesta",new RtsReservacionEmpresa(habitacionTipoService.listar(),
                                                                                                                            context.sessionAttribute("empleado"),
                                                                                                                            new Boolean[]{false,false})));
        });

        javalin.get("/perfil",context -> {

            Usuario usuario = context.sessionAttribute("empleado");
            Empleado listarPerfil = empleadoService.buscar(usuario.getDni());
            context.render("employer/perfil.jte",Collections.singletonMap("listarPerfil",listarPerfil));

        });



        javalin.post("/consultar/reservacion/empleado",this::consultarPrecio);
        javalin.post("/generar/habitacion/tipo",this::generarHabitacionTipo);
        javalin.post("/generar/habitacion",this::generarHabitacion);
        javalin.post("/administar/pagos",this::generarMetodoDePago);

        javalin.post("/perfil",context -> {
            String telefono = context.formParam("telefono");
            Usuario usuario = context.sessionAttribute("empleado");
            usuario.setTelefono(telefono);
            empleadoService.modificar(usuario, usuario.getDni());
            context.render("employer/perfil.jte",Collections.singletonMap("listarPerfil",empleadoService.buscar(usuario.getDni())));
        });



    }

    private void consultarPrecio(Context context)
    {
        BigDecimal resultadoPrecio = null;
        this.reservacion = null;
        this.myResponse = null;
        RegistrarReserva registrarReserva = null;

        try
        {

            LocalDate checkIn = LocalDate.parse(context.formParam("checkIn"));
            LocalDate checkOut = LocalDate.parse(context.formParam("checkOut"));
            String categoria = context.formParam("categoria");


            if(checkIn.isEqual(checkOut))
            { this.myResponse =  new ErrorResponse(400,"Las Fechas son iguales");}
            else if(checkIn.isBefore(LocalDate.now()) || checkOut.isBefore(LocalDate.now()) || checkIn.isAfter(checkOut))
            { this.myResponse =  new  ErrorResponse(400,"Las fechas pertenecen al día o días anteriores"); }
            else
            { resultadoPrecio = habitacionTipoService.consultarPrecioHabitacion(checkIn,checkOut,categoria); }
              registrarReserva = new RegistrarReserva(checkIn,
                    checkOut,
                    categoria,
                    new DecimalFormat("#,###.00").format( (resultadoPrecio == null) ? 0 : resultadoPrecio ));
        } catch (Exception ex)
        {this.myResponse =  new  ErrorResponse(500,ex.getMessage());}

        this.reservacion = new ConsultaInfoReservacion(habitacionTipoService.listar(),
                this.myResponse,
                registrarReserva,
                context.sessionAttribute("empleado"));
        context.render("employer/consultar.jte",Collections.singletonMap("reservacion",reservacion));

    }

    private void generarHabitacionTipo(Context context)
    {
        boolean respuesta;
        try
        {
            String nombre = context.formParam("nombre");
            BigDecimal precioUnitario = new BigDecimal(Optional.ofNullable(context.formParam("precioUnitario")).orElse("0"));
            Integer puntoUnitario = Integer.valueOf(Objects.requireNonNull(context.formParam("puntoUnitario")));
            habitacionTipoService.guardar(new HabitacionTipo(nombre,precioUnitario,puntoUnitario));
            respuesta = true;
        }catch (Exception ex) {respuesta = false;}
        context.render("employer/reservacion.jte",Collections.singletonMap("respuesta",new RtsReservacionEmpresa(habitacionTipoService.listar(),
                                                                                                                        context.sessionAttribute("empleado"),
                                                                                                                        new Boolean[] {respuesta,false})));
    }

    private void generarHabitacion(Context context)
    {
        boolean respuesta;

        try
        {
            String nombre = context.formParam("categoria");
            Integer numero = Integer.valueOf(Objects.requireNonNull(context.formParam("numero")));
            HabitacionTipo categoria = habitacionTipoService.buscar(nombre);
            respuesta = habitacionService.guardar(new Habitacion(categoria, numero)) != 0;
        }catch (Exception exception) {respuesta = false;}
          context.render("employer/reservacion.jte",Collections.singletonMap("respuesta",new RtsReservacionEmpresa(habitacionTipoService.listar(),
                                                                                                                          context.sessionAttribute("empleado"),
                                                                                                                          new Boolean[]{false,respuesta})));
    }

    private void generarMetodoDePago(Context context)
    {
        boolean respuesta;

        try
        {
            String nombre = context.formParam("nombre");
            metodoDePagoService.guardar(new MetodoPago(nombre));
            respuesta = true;
        }catch (Exception exception) {respuesta = false;}
           context.render("employer/pagos.jte",Collections.singletonMap("respuesta",new RtsReservacionEmpresa(habitacionTipoService.listar(),
                                                                                                                           context.sessionAttribute("empleado"),
                                                                                                                           new Boolean[]{respuesta,false})));
    }


}
