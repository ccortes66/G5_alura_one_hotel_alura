package com.alura.hotelalura.controller;

import com.alura.hotelalura.auth.JWTGenerate;
import com.alura.hotelalura.config.JWTMiddleware;
import com.alura.hotelalura.model.Cliente;
import com.alura.hotelalura.model.Habitacion;
import com.alura.hotelalura.model.Reserva;
import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.repository.dto.RegistrarReserva;
import com.alura.hotelalura.repository.persistence.*;
import com.alura.hotelalura.service.ClienteService;
import com.alura.hotelalura.service.HabitacionService;
import com.alura.hotelalura.service.ReservaService;
import com.alura.hotelalura.service.type.HabitacionTipoService;
import com.alura.hotelalura.service.type.MetodoDePagoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.Injector;
import io.javalin.Javalin;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.UUID;


public class ClienteController
{
    private final Javalin myApp;
    private Injector injector;
    private ClienteRepository clienteService;
    private ReservaRepository reservaService;
    private HabitacionTipoRepository habitacionTipoService;
    private HabitacionRepository habitacionService;
    private MetodoDePagoRepository metodoDePagoService;
    private ObjectMapper mapper;
    public ClienteController(Javalin app, Injector injector)
    {
        //inicia servidor javalin
        this.myApp = app;
        //iniciar inyeccion de dependencia
        this.injector = injector;
        this.clienteService = injector.getInstance(ClienteService.class);
        this.reservaService = injector.getInstance(ReservaService.class);
        this.habitacionTipoService = injector.getInstance(HabitacionTipoService.class);
        this.habitacionService = injector.getInstance(HabitacionService.class);
        this.metodoDePagoService = injector.getInstance(MetodoDePagoService.class);
        //serealizar a json
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        cargarMiddleware();
        cargarRutas();
    }

    private void cargarMiddleware()
    {

        myApp.before("/cliente/whoami", new JWTMiddleware.JWTMiddlewareCliente(injector));
        myApp.before("/cliente/reserva/listar",new JWTMiddleware.JWTMiddlewareCliente(injector));
        myApp.before("/cliente/reserva/generar",new JWTMiddleware.JWTMiddlewareCliente(injector));
        myApp.before("/cliente/actualizar",new JWTMiddleware.JWTMiddlewareCliente(injector));
        myApp.before("/cliente/eliminar/",new JWTMiddleware.JWTMiddlewareCliente(injector));
        myApp.before("/cliente/habitacion/precio",new JWTMiddleware.JWTMiddlewareCliente(injector));
        myApp.before("/cliente/reserva/buscar/{reserva}",new JWTMiddleware.JWTMiddlewareCliente(injector));

    }

    private void cargarRutas()
    {

        myApp.get("/cliente/whoami",this::buscarClientePorDin);
        myApp.get("/cliente/reserva/listar",this::listarReservasPorCliente);
        myApp.get("/cliente/reserva/buscar/{reserva}",this::buscarReserva);
        myApp.get("/cliente/habitacion/precio",this::calculoDeHabitacion);
        myApp.post("/cliente/reserva/generar",this::generarReservacion);
        myApp.put("/cliente/actualizar",this::actualizarCliente);
        myApp.delete("/cliente/eliminar",this::eliminarCliente);


    }



    private void buscarClientePorDin(Context context) {
        try {

            Cliente cliente = clienteService.buscar(JWTGenerate.getMyCliente());
            context.json(mapper.writeValueAsString(cliente));}
        catch (Exception ex){  throw new NotFoundResponse("No existe Cliente");}

    }



    private void actualizarCliente(Context context) throws JsonProcessingException {
        Usuario usuario = context.bodyAsClass(Usuario.class);
        if (usuario.getApellido().trim().isEmpty() ||
            usuario.getNombre().trim().isEmpty() || usuario.getTelefono().trim().isEmpty()
        ) {throw new BadRequestResponse("Hay Campos vacios");}

         Byte result = clienteService.modificar(usuario,JWTGenerate.getMyCliente());
         if(result>=1)
           { context.json(mapper.writeValueAsString(clienteService.buscar(JWTGenerate.getMyCliente())));}
         else
           { throw new NotFoundResponse("No existe el Cliente");}

    }

    private void eliminarCliente(Context context)
    {
        Byte resultado = clienteService.eliminar(JWTGenerate.getMyCliente());
        if(resultado>=1)
          { context.result("Cliente eliminado");}
        else
          { throw new NotFoundResponse("No existe Cliente");};

    }


    private void listarReservasPorCliente(Context context) throws JsonProcessingException
    {
        context.json(mapper.writeValueAsString(reservaService.listarReservaPorCliente(JWTGenerate.getMyCliente())));
    }

    private void buscarReserva(Context context) throws JsonProcessingException
    {
        String reserva = context.pathParam("reserva");
        context.json(mapper.writeValueAsString(reservaService.buscar(JWTGenerate.getMyCliente(),reserva)));
    }

    private void generarReservacion(Context context)
    {
        RegistrarReserva reserva = context.bodyAsClass(RegistrarReserva.class);
        if( reserva.checkIn().isEqual(reserva.checkOut()))
          { throw new BadRequestResponse("Es la misma fecha"); }

        if( reserva.checkIn().isBefore(LocalDate.now()) || reserva.checkOut().isBefore(LocalDate.now()) ||
                reserva.checkIn().isAfter(reserva.checkOut()))
          { throw new BadRequestResponse("Las fechas pertenecen al día o días anteriores"); }

        if(reserva.metodoPago().trim().isEmpty())
          {throw new BadRequestResponse("Campo est vacio");}

        try
        {

            Cliente cliente = clienteService.buscar(JWTGenerate.getMyCliente());
            Reserva myReseva = new Reserva(cliente,reserva.checkIn(),reserva.checkOut());
            myReseva.setMetodoPago(metodoDePagoService.buscar(reserva.metodoPago().trim()));
            myReseva.setValorReserva(habitacionTipoService.consultarPrecioHabitacion(reserva));
            Habitacion habitacion = habitacionService.asignarHabitacion(reserva.tipo().trim());
            habitacionService.guardarHabitacionPorReseva(habitacion);
            myReseva.setHabitacion(habitacion);
            reservaService.generar(myReseva);

            context.status(201);
            context.result("Reserva Creada");


        } catch (Exception ex){throw new NotFoundResponse(ex.getMessage());}
    }

    private void calculoDeHabitacion(Context context)
    {
        RegistrarReserva reserva = context.bodyAsClass(RegistrarReserva.class);
        if( reserva.checkIn().isEqual(reserva.checkOut()))
        { throw new BadRequestResponse("Es la misma fecha"); }

        if( reserva.checkIn().isBefore(LocalDate.now()) || reserva.checkOut().isBefore(LocalDate.now()))
        { throw new BadRequestResponse("Las fechas pertenecen al dia o dias anteriores"); }

        context.result( new DecimalFormat("#,###.00")
                                                  .format(habitacionTipoService.consultarPrecioHabitacion(reserva)));
    }





}
