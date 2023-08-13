package com.alura.hotelalura.controller;

import com.alura.hotelalura.auth.JWTGenerate;
import com.alura.hotelalura.config.JWTMiddleware;
import com.alura.hotelalura.model.Empleado;
import com.alura.hotelalura.model.Habitacion;
import com.alura.hotelalura.model.type.EmpleadoTipo;
import com.alura.hotelalura.model.type.HabitacionTipo;
import com.alura.hotelalura.model.type.MetodoPago;
import com.alura.hotelalura.repository.dto.RegistrarHabitacion;
import com.alura.hotelalura.repository.persistence.*;
import com.alura.hotelalura.service.EmpleadoService;
import com.alura.hotelalura.service.HabitacionService;
import com.alura.hotelalura.service.ReservaService;
import com.alura.hotelalura.service.type.EmpleadoTipoService;
import com.alura.hotelalura.service.type.HabitacionTipoService;
import com.alura.hotelalura.service.type.MetodoDePagoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.Injector;
import io.javalin.Javalin;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.InternalServerErrorResponse;
import io.javalin.http.NotFoundResponse;

public class EmpleadoController
{
    private Javalin javalin;
    private final EmpleadoRepository empleadoService;
    private final EmpleadoTipoRepository empleadoTipoService;
    private final MetodoDePagoRepository metodoDePagoService;
    private final HabitacionTipoRepository habitacionTipoService;
    private final HabitacionRepository habitacionService;
    private final  ReservaRepository reservaService;
    private ObjectMapper mapper;
    private Injector injector;
    private Integer skip, limit;

    public EmpleadoController(Javalin javalin, Injector injector)
    {
        this.javalin = javalin;
        this.injector = injector;
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.injector = injector;
        this.empleadoService = injector.getInstance(EmpleadoService.class);
        this.empleadoTipoService = injector.getInstance(EmpleadoTipoService.class);
        this.metodoDePagoService = injector.getInstance(MetodoDePagoService.class);
        this.habitacionTipoService = injector.getInstance(HabitacionTipoService.class);
        this.habitacionService = injector.getInstance(HabitacionService.class);
        this.reservaService = injector.getInstance(ReservaService.class);

        cargarMiddleware();
        cargarRutas();
    }

    private void cargarMiddleware()
    {
        javalin.before("/empleado/tipo/listar",new JWTMiddleware.JWTMiddlewareEmpleado(injector));
        javalin.before("/empleado/tipo/generar",new JWTMiddleware.JWTMiddlewareEmpleado(injector));
        javalin.before("/empleado/tipo/eliminar",new JWTMiddleware.JWTMiddlewareEmpleado(injector));

        javalin.before("/empleado/pago/listar",new JWTMiddleware.JWTMiddlewareEmpleado(injector));
        javalin.before("/empleado/pago/generar",new JWTMiddleware.JWTMiddlewareEmpleado(injector));
        javalin.before("/empleado/pago/eliminar",new JWTMiddleware.JWTMiddlewareEmpleado(injector));

        javalin.before("/empleado/habitaciontipo/listar/",new  JWTMiddleware.JWTMiddlewareEmpleado(injector));
        javalin.before("/empleado/habitaciontipo/generar/",new JWTMiddleware.JWTMiddlewareEmpleado(injector));
        javalin.before("/empleado/habitaciontipo/eliminar/",new JWTMiddleware.JWTMiddlewareEmpleado(injector));

        javalin.before("/empleado/habitacion/listar/{pagina}",new JWTMiddleware.JWTMiddlewareEmpleado(injector));
        javalin.before("/empleado/habitacion/buscar/{numero}",new JWTMiddleware.JWTMiddlewareEmpleado(injector));
        javalin.before("/empleado/habitacion/generar",new JWTMiddleware.JWTMiddlewareEmpleado(injector));
        javalin.before("/empleado/habitacion/eliminar/{numero}",new JWTMiddleware.JWTMiddlewareEmpleado(injector));

        javalin.before("/empleado/reserva/listar/{pagina}",new JWTMiddleware.JWTMiddlewareEmpleado(injector));
        javalin.before("/empleado/reserva/eliminar/{reserva}",new JWTMiddleware.JWTMiddlewareEmpleado(injector));
    }
    private void cargarRutas()
    {
        javalin.get("/empleado/tipo/listar",this::listarTipo);
        javalin.post("/empleado/tipo/generar",this::generarTipo);
        javalin.delete("/empleado/tipo/eliminar",this::eliminarTipo);

        javalin.get("/empleado/pago/listar",this::listarMetodoPago);
        javalin.post("/empleado/pago/generar",this::generarMetodoPago);
        javalin.delete("/empleado/pago/eliminar",this::eliminarMetodoPago);

        javalin.get("/empleado/habitaciontipo/listar",this::listarHabitacionTipo);
        javalin.post("/empleado/habitaciontipo/generar",this::generarHabitacionTipo);
        javalin.delete("/empleado/habitaciontipo/eliminar",this::eliminarHabitacionTipo);

        javalin.get("/empleado/habitacion/listar/{pagina}",this::listarHabitacion);
        javalin.get("/empleado/habitacion/buscar/{numero}",this::buscarHabitacion);
        javalin.post("/empleado/habitacion/generar",this::agregarHabitacion);
        javalin.delete("/empleado/habitacion/eliminar/{numero}",this::eliminarHabitacion);

        javalin.get("/empleado/reserva/listar/{pagina}",this::listarReservaciones);
        javalin.delete("/empleado/reserva/eliminar/{reserva}",this::eliminarReservacion);
    }

    private void listarTipo(Context context) throws JsonProcessingException
    {
        context.json(mapper.writeValueAsString(empleadoTipoService.listar()));
    }

    private void generarTipo(Context context)
    {
        EmpleadoTipo tipo = context.bodyAsClass(EmpleadoTipo.class);
        if(tipo.getNombre().trim().isEmpty())
        {throw new BadRequestResponse("Campo vacio");}

        try
        {
            empleadoTipoService.guardar(new EmpleadoTipo(tipo.getNombre().trim()));
            context.status(201);
            context.json(mapper.writeValueAsString(empleadoTipoService.buscar(tipo.getNombre())));

        }catch (Exception ex){throw new BadRequestResponse("Ya esta registrado");}

    }

    private void eliminarTipo(Context context)
    {
        EmpleadoTipo tipo = context.bodyAsClass(EmpleadoTipo.class);
        if(tipo.getNombre().trim().isEmpty())
        {throw new BadRequestResponse("Campo vacio");}

        try
        {
            EmpleadoTipo empleadoTipo = empleadoTipoService.buscar(tipo.getNombre().trim());
            empleadoTipoService.eliminar(empleadoTipo);
            context.json(empleadoTipo);

        } catch (Exception exception) { throw new NotFoundResponse("No existe registro");}

    }


    private void listarMetodoPago(Context context) throws JsonProcessingException
    {
        context.json(mapper.writeValueAsString(metodoDePagoService.listar()));
    }

    private void generarMetodoPago(Context context)
    {
        MetodoPago metodoPago = context.bodyAsClass(MetodoPago.class);
        if(metodoPago.getNombre().trim().isEmpty())
        {throw new BadRequestResponse("Campo vacio");}
        try
        {
            metodoDePagoService.guardar(new MetodoPago(metodoPago.getNombre().trim()));
            context.status(201);
            context.json(mapper.writeValueAsString(metodoDePagoService.buscar(metodoPago.getNombre().trim())));

        }catch (Exception ex){throw new BadRequestResponse("Ya esta registrado");}
    }

    private void eliminarMetodoPago(Context context)
    {
        MetodoPago metodoPago = context.bodyAsClass(MetodoPago.class);
        if(metodoPago.getNombre().trim().isEmpty())
        {throw new BadRequestResponse("Campo vacio");}

        try
        {
          MetodoPago myPago = metodoDePagoService.buscar(metodoPago.getNombre().trim());
          metodoDePagoService.eliminar(myPago);
          context.json(mapper.writeValueAsString(myPago));
        }catch (Exception ex){throw new NotFoundResponse("No existe registro");}
    }


    private void listarHabitacionTipo(Context context) throws JsonProcessingException
    {
        context.json(mapper.writeValueAsString(habitacionTipoService.listar()));
    }

    private void generarHabitacionTipo(Context context)
    {
        HabitacionTipo tipo = context.bodyAsClass(HabitacionTipo.class);
        if(tipo.getNombre().trim().isEmpty() || tipo.getPrecioUnitario().intValue() <=0 ||
                tipo.getPuntoUnitario() <=0 )
          {throw new BadRequestResponse("Campo vacio o ingreso valores invalidos");}
        try
        {
            habitacionTipoService.guardar(new HabitacionTipo(tipo.getNombre().trim(),
                                                             tipo.getPrecioUnitario(),
                                                             tipo.getPuntoUnitario()));
            context.status(201);
            context.json(mapper.writeValueAsString(habitacionTipoService.buscar(tipo.getNombre().trim())));
        }catch (Exception e) {throw new BadRequestResponse("Hay errores en la informacion");}
    }

    private void eliminarHabitacionTipo(Context context)
    {
        HabitacionTipo tipo = context.bodyAsClass(HabitacionTipo.class);
        if(tipo.getNombre().trim().isEmpty())
          {throw new BadRequestResponse("Campo vacio");}
        try
        {
            HabitacionTipo habitacionTipo = habitacionTipoService.buscar(tipo.getNombre().trim());
            habitacionTipoService.eliminar(habitacionTipo);
            context.json(mapper.writeValueAsString(habitacionTipo));

        }catch (Exception e) {throw new NotFoundResponse("No hay registro"); }
    }

    private void listarHabitacion(Context context) throws JsonProcessingException {
        int pagina = Integer.parseInt(context.pathParam("pagina"));
        if(pagina<=0)
        {throw new BadRequestResponse("Valor invalido");}
        skip = (pagina - 1)*10;
        limit = skip + 10;
        context.json(mapper.writeValueAsString(habitacionService.listar(skip,limit)));
    }

    private void buscarHabitacion(Context context)
    {
        int numero = Integer.parseInt(context.pathParam("numero"));
        if(numero<=0)
        {throw new BadRequestResponse("Numero invalido");}
        try
          {context.json(mapper.writeValueAsString(habitacionService.buscar(numero)));}
        catch (Exception exception)
          {throw new NotFoundResponse(exception.getMessage());}

    }

    private void agregarHabitacion(Context context)
    {
        RegistrarHabitacion registrarHabitacion = context.bodyAsClass(RegistrarHabitacion.class);
        if(registrarHabitacion.habitacionTipo().trim().isEmpty() || registrarHabitacion.numero() <=0)
           {throw new BadRequestResponse("Valor invalido"); }
        try
        {
            HabitacionTipo habitacionTipo = habitacionTipoService.buscar(registrarHabitacion.habitacionTipo().trim());
            Byte result = habitacionService.guardar(new Habitacion(habitacionTipo,registrarHabitacion.numero()));
            if(result>=1)
               {context.status(201);
                context.result("Habitacion creada");}
            else {throw new BadRequestResponse("La habitacion ya esta creada");}

        }catch (Exception ex){throw new BadRequestResponse(ex.getMessage()); }
    }

    private void eliminarHabitacion(Context context)
    {
        int numero = Integer.parseInt(context.pathParam("numero"));
        if(numero<=0)
           {throw new BadRequestResponse("Numero invalido");}
       try
         {
             Habitacion habitacion = habitacionService.buscar(numero);
             habitacionService.eliminar(numero);
             context.json(mapper.writeValueAsString(habitacion));
         }
       catch (Exception e)
              {throw  new NotFoundResponse("No hay registros");}
    }

    private void listarReservaciones(Context context) throws JsonProcessingException
    {
        int pagina = Integer.parseInt(context.pathParam("pagina"));
        if(pagina<=0)
        {throw new BadRequestResponse("Valor invalido");}
        skip = (pagina - 1)*10;
        limit = skip + 10;
        context.json( mapper.writeValueAsString(reservaService.listaReservaActual(skip,limit)));

    }

    private void eliminarReservacion(Context context)
    {
        String reserva = context.pathParam("reserva");
        Byte result = reservaService.eliminar(reserva);
        if(result>=1)
          {context.result("Reserva Eliminada");}
        else
          {throw new InternalServerErrorResponse("error interno");}

    }





}
