package com.alura.hotelalura.ssr;

import com.alura.hotelalura.model.Empleado;
import com.alura.hotelalura.model.Login;
import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.repository.dto.ConseguirUsuario;
import com.alura.hotelalura.repository.persistence.EmpleadoRepository;
import com.alura.hotelalura.repository.persistence.LoginRepository;
import com.alura.hotelalura.service.EmpleadoService;
import com.alura.hotelalura.service.LoginService;
import com.alura.hotelalura.ssr.dto.ResultadoLista;
import com.alura.hotelalura.ssr.dto.ResultadoListaEmpleado;
import com.alura.hotelalura.ssr.error.ErrorResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Injector;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import net.bytebuddy.utility.visitor.ContextClassVisitor;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class CoockieControllerEmpleado
{
    private final Javalin javalin;
    private final Injector injector;
    private final LoginRepository loginService;
    private final EmpleadoRepository empleadoService;
    private ObjectMapper mapper;
    private ErrorResponse response = null;



    public CoockieControllerEmpleado(Javalin javalin,Injector injector)
    {
        this.javalin = javalin;
        this.injector = injector;
        this.loginService = injector.getInstance(LoginService.class);
        this.empleadoService = injector.getInstance(EmpleadoService.class);
        this.mapper = new ObjectMapper();
        cargarHtml();
    }

    private void cargarHtml()
    {
        javalin.before(context -> {
            if(!validEmpleado(context) && !context.path().equals("/empleado/registrar"))
               {context.render("employer/login.jte");}
        });

        javalin.get("/",context -> context.render("employer/login.jte",Collections.singletonMap("response",null)));
        javalin.get("/cerrar",this::closedSession);
        javalin.get("/empleado/registrar",context -> context.render("employer/formulario.jte"));

        javalin.post("/empleado/autenticar",this::ingresoAlSystema);
        javalin.post("/empleado/registrar",this::registarUsuario);
    }

    private void closedSession(Context context)
    {
        context.sessionAttribute("empleado",null);
        context.redirect("/");
    }

    private boolean validEmpleado(Context context)
    {
        Usuario user = context.sessionAttribute("empleado");
        return  user != null;
    }

    private boolean verificarCaptcha(Context context) throws IOException {
        Map<String,String> params = new HashMap<>();
        params.put("response",context.formParam("h-captcha-response"));
        params.put("secret",System.getenv("secret"));

        URL url = new URL("https://hcaptcha.com/siteverify");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        String postData = params.entrySet().stream()
                .map(param-> param.getKey() + "="+ param.getValue())
                .collect(Collectors.joining("&"));

        connection.getOutputStream().write(postData.getBytes());
        JsonNode result = this.mapper.readTree(connection.getInputStream());


        return result.get("success").asBoolean();
    }

    private void ingresoAlSystema(Context context) throws IOException {

        String username = context.formParam("username");
        String password = context.formParam("password");
        this.response = new ErrorResponse(400,"Usuario o contraseña errónea");

        try
          {

              if(verificarCaptcha(context))
              {
                       Optional<Usuario> usuario = loginService.ingresoSistema(new ConseguirUsuario(username,password));
                       usuario.ifPresentOrElse(
                               (user) -> {
                                   Optional<Empleado> empleado = Optional.ofNullable(empleadoService.buscar(user.getDni()));
                                   empleado.ifPresent((emp)->{
                                       context.sessionAttribute("empleado", user);
                                       context.render("employer/index.jte", Collections.singletonMap("resultadoListaEmpleado", new ResultadoListaEmpleado(null, user)));
                                   });

                               },
                               () -> context.render("employer/login.jte",Collections.singletonMap("response",response))
                       );
              }

            }catch (Exception ex) {context.render("employer/login.jte",Collections.singletonMap("response",new ErrorResponse(500,ex.getMessage())));}

    }
    private void registarUsuario(Context context) throws IOException {
        this.response = null;

        if (verificarCaptcha(context))
        {
            try {
                String dni = context.formParam("dni");
                String nombre = context.formParam("nombre");
                String apellido = context.formParam("apellido");
                LocalDate fechaNacimiento = LocalDate.parse(Objects.requireNonNull(context.formParam("fechaNacimiento")));
                String username = context.formParam("username");
                String password = context.formParam("password");
                String password2 = context.formParam("password2");
                String cargo = context.formParam("cargo");
                int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();

                if (!dni.matches("^[0-9A-Za-z]+$") || !nombre.matches("^[0-9A-Za-z ]+$") ||
                        !apellido.matches("^[0-9A-Za-z ]+$") || !username.matches("^[0-9A-Za-z]+$"))
                {this.response = new ErrorResponse(400, "Campos llevan caracteres especiales");}

                else if (edad <= 17)
                {this.response = new ErrorResponse(400, "Eres menor de edad");}

                else if (!password.equals(password2))
                {this.response = new ErrorResponse(400, "Contraseña no coinciden");}
                else
                {
                    Usuario usuario = new Usuario(dni,nombre,apellido,fechaNacimiento," ");
                    empleadoService.guardarUsuarioLogin(new Empleado(usuario,cargo),new Login(username,password,usuario));
                    context.redirect("/");
                }
                context.render("formulario.jte",Collections.singletonMap("response",response));

            }catch (Exception ex)
            { this.response = new ErrorResponse(400,ex.getMessage());
                context.render("formulario.jte",Collections.singletonMap("response",response));
            }

        }else {context.render("formulario.jte",Collections.singletonMap("response",new ErrorResponse(400,"Captcha vacio")));};


    }



    public static class MiddleareEmpleado implements Handler
    {

        @Override
        public void handle(@NotNull Context context) throws Exception
        {
             Optional<Usuario> usuario = Optional.ofNullable(context.sessionAttribute("empleado"));
            if(usuario.isEmpty() && !context.path().equals("/empleado/registrar"))
               {context.render("employer/login.jte");}
        }
    }





}
