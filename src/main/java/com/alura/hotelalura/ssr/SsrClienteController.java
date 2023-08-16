package com.alura.hotelalura.ssr;

import com.alura.hotelalura.model.Cliente;
import com.alura.hotelalura.model.Habitacion;
import com.alura.hotelalura.model.Reserva;
import com.alura.hotelalura.model.Usuario;
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
import com.alura.hotelalura.ssr.dto.ConsultaInfoReservacion;
import com.alura.hotelalura.ssr.dto.ListarBusqueda;
import com.alura.hotelalura.ssr.dto.ListarCatrgoriaMetodo;
import com.alura.hotelalura.ssr.error.ErrorResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Injector;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import io.javalin.Javalin;
import io.javalin.http.Context;
import lombok.Getter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

public class SsrClienteController
{
    private final Javalin javalin;
    private final Injector injector;
    private ErrorResponse response = null;
    private final ReservaRepository reservaService;
    private final MetodoDePagoRepository metodoDePagoService;
    private final HabitacionTipoRepository habitacionTipoService;
    private ListarCatrgoriaMetodo metodo;
    private ReservaInfoSsr infoSsr = null;
    private ReservaInfo reservaInfo = null;
    private final ClienteRepository clienteService;
    private final HabitacionRepository habitacionService;
    private List<ReservaInfo> infoList;

    @Getter
    private static TreeSet<String> mysPaises = new TreeSet<>();
    private ConsultaInfoReservacion reservacion = null;
    private ListarBusqueda busqueda;

    private ObjectMapper mapper;



    public SsrClienteController(Javalin javalin, Injector injector)
    {
        this.javalin = javalin;
        this.injector = injector;
        this.reservaService = injector.getInstance(ReservaService.class);
        this.metodoDePagoService = injector.getInstance(MetodoDePagoRepository.class);
        this.habitacionTipoService = injector.getInstance(HabitacionTipoService.class);
        this.clienteService = injector.getInstance(ClienteRepository.class);
        this.habitacionService = injector.getInstance(HabitacionService.class);
        this.mapper = new ObjectMapper();

        cargarMiddleware();
        cargarEvento();
    }

    private void cargarMiddleware()
    {
        javalin.after("/listar/reservaciones",new CookieController.MiddewareCookie());
        javalin.after("/generar/reservacion",new CookieController.MiddewareCookie());
        javalin.after("/buscar/reservacion",new CookieController.MiddewareCookie());
        javalin.after("/consultar/reservacion",new CookieController.MiddewareCookie());
        javalin.after("/busqueda",new CookieController.MiddewareCookie());
        javalin.after("/perfil",new CookieController.MiddewareCookie());
        javalin.after("/eliminar",new CookieController.MiddewareCookie());
    }

    private void cargarEvento()
    {


        javalin.get("/listar/reservaciones",context -> {
            this.infoList = reservaService.listarReservaPorCliente(CookieController.getDinUsusario());
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

        javalin.get("/buscar/reservacion",context -> {
            this.infoList = reservaService.listarReservaPorCliente(CookieController.getDinUsusario());
            ListarBusqueda busqueda = new ListarBusqueda(this.infoList,null);
            context.render("buscar.jte",Collections.singletonMap("busqueda",busqueda));
        });

        javalin.get("/busqueda",context ->
        {
            this.busqueda = null;
            this.infoList = reservaService.listarReservaPorCliente(CookieController.getDinUsusario());
            Optional<ReservaInfo> myReservainfo = this.infoList.stream()
                                         .filter(data -> data.reserva().equals(context.queryParam("busqueda")))
                                         .findFirst();
            myReservainfo.ifPresentOrElse(
                    info -> this.busqueda = new ListarBusqueda(this.infoList,info),
                    () -> this.busqueda =  new ListarBusqueda(null,null)
            );

            context.render("buscar.jte",Collections.singletonMap("busqueda",busqueda));
        });

        javalin.get("/consultar/reservacion",context -> {
            ConsultaInfoReservacion reservacion = new ConsultaInfoReservacion(
                                                                    habitacionTipoService.listar(),
                                                                     null,
                                                                    null);
            context.render("consultar.jte",Collections.singletonMap("reservacion",reservacion));

        });

        javalin.get("/perfil",context -> {

              OkHttpClient client = new OkHttpClient();
              Request request = new Request.Builder()
                                               .url("https://restcountries.com/v3.1/all")
                                               .build();

              Response responses = client.newCall(request).execute();
              String responseBody = responses.body().string();
              JsonNode paises = mapper.readTree(responseBody);

              if(mysPaises.isEmpty())
              {
                  for (JsonNode pais: paises)
                  {mysPaises.add(pais.get("name").get("common").asText());}

              }
              context.render("perfil.jte",Collections.singletonMap("mysPaises",mysPaises));

        });

        javalin.get("/eliminar",context -> {
            Byte result = clienteService.eliminar(CookieController.getDinUsusario());
            if(result>=1)
            {context.render("login.jte");}
            else
             {context.render("perfil.jte");}

        });


        javalin.post("/generar/reservacion",this::generarReservacion);
        javalin.post("/consultar/reservacion",this::consultarPrecio);
        javalin.post("/perfil",this::editarCuenta);



    }

    private void generarReservacion(Context context)
    {

        try
        {
            LocalDate checkIn = LocalDate.parse(context.formParam("checkIn"));
            LocalDate checkOut = LocalDate.parse(context.formParam("checkOut"));
            String categoria = context.formParam("categoria");
            String metodoPago = context.formParam("metodoPago");


            if(checkIn.isEqual(checkOut))
              { this.response =  new ErrorResponse(400,"Las Fechas son iguales");}
            else if(checkIn.isBefore(LocalDate.now()) || checkOut.isBefore(LocalDate.now()) || checkIn.isAfter(checkOut))
              { this.response =  new  ErrorResponse(400,"Las fechas pertenecen al día o días anteriores"); }
            else
              {
                Cliente cliente = clienteService.buscar(CookieController.getDinUsusario());
                Reserva reserva = new Reserva(cliente,checkIn,checkOut);
                reserva.setMetodoPago(metodoDePagoService.buscar(metodoPago));
                reserva.setValorReserva(habitacionTipoService.consultarPrecioHabitacion(checkIn,checkOut,categoria));

                Habitacion habitacion = habitacionService.asignarHabitacion(categoria);
                habitacionService.guardarHabitacionPorReseva(habitacion);

                reserva.setHabitacion(habitacion);
                reservaService.generar(reserva);

                this.infoSsr  = reservaService.buscarResultado(CookieController.getDinUsusario());
                this.response = new  ErrorResponse(200,"ok");
            }
        }catch (Exception ex)
                {this.response = new ErrorResponse(500,ex.getMessage());}

         metodo = new ListarCatrgoriaMetodo(metodoDePagoService.listar(),
                                            habitacionTipoService.listar(),
                                            this.response,
                                            this.infoSsr
                                             );

         context.render("reservacion.jte",Collections.singletonMap("metodo",metodo));

    }

    private void consultarPrecio(Context context)
    {
        BigDecimal resultadoPrecio = null;
        this.reservacion = null;
        this.response = null;
        RegistrarReserva registrarReserva = null;

        try
        {

            LocalDate checkIn = LocalDate.parse(context.formParam("checkIn"));
            LocalDate checkOut = LocalDate.parse(context.formParam("checkOut"));
            String categoria = context.formParam("categoria");


            if(checkIn.isEqual(checkOut))
            { this.response =  new ErrorResponse(400,"Las Fechas son iguales");}
            else if(checkIn.isBefore(LocalDate.now()) || checkOut.isBefore(LocalDate.now()) || checkIn.isAfter(checkOut))
            { this.response =  new  ErrorResponse(400,"Las fechas pertenecen al día o días anteriores"); }
            else
            { resultadoPrecio = habitacionTipoService.consultarPrecioHabitacion(checkIn,checkOut,categoria); }
              registrarReserva = new RegistrarReserva(checkIn,
                                                      checkOut,
                                                      categoria,
                                                      new DecimalFormat("#,###.00").format( (resultadoPrecio == null) ? 0 : resultadoPrecio ));
        } catch (Exception ex)
                 {this.response =  new  ErrorResponse(500,ex.getMessage());}

         this.reservacion = new ConsultaInfoReservacion(habitacionTipoService.listar(),
                                                        this.response,
                                                        registrarReserva);
         context.render("consultar.jte",Collections.singletonMap("reservacion",reservacion));

    }

    private void editarCuenta(Context context)
    {
        String telefono = context.formParam("telefono");

        try
        {
              Optional <Cliente> cliente = Optional.ofNullable(clienteService.buscar(CookieController.getDinUsusario()));
              cliente.ifPresent((client) -> {
                  Usuario usuario = client.getUsuario();
                  usuario.setTelefono(telefono);
                  clienteService.modificar(usuario,client.getUsuario().getDni());
                  CookieController.setIsCliente(client);
              });

        }catch (Exception ex)
                {ex.printStackTrace();}


        context.render("perfil.jte",Collections.singletonMap("mysPaises",mysPaises));
    }
}
