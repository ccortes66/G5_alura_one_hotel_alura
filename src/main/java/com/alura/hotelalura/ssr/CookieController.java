package com.alura.hotelalura.ssr;

import com.alura.hotelalura.model.Cliente;
import com.alura.hotelalura.model.Login;
import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.repository.dto.ConseguirUsuario;
import com.alura.hotelalura.repository.persistence.ClienteRepository;
import com.alura.hotelalura.repository.persistence.LoginRepository;
import com.alura.hotelalura.service.ClienteService;
import com.alura.hotelalura.service.LoginService;
import com.alura.hotelalura.ssr.dto.ResultadoLista;
import com.alura.hotelalura.ssr.error.ErrorResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Injector;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class CookieController
{

    private final Javalin javalin;
    private final Injector injector;
    private ErrorResponse response;
    private final LoginRepository loginService;
    private final ClienteRepository clienteService;
    private static ObjectMapper mapper = new ObjectMapper();
    @Getter
    private final static TreeSet<String> mysPaises = new TreeSet<>();



    public CookieController(Javalin javalin, Injector injector) throws IOException {
        this.javalin = javalin;
        this.injector = injector;
        this.loginService = injector.getInstance(LoginService.class);
        this.clienteService = injector.getInstance(ClienteService.class);
        cargarHtml();
        eventosHtml();
        generarPaises();
    }

    private void cargarHtml()
    {
        //validador de sessiones
        javalin.before(context -> {
            if(!isValidSession(context) && !context.path().equals("/registrar"))
              {context.render("login.jte");}

        });

        javalin.get("/", context -> context.render("login.jte"));
        javalin.get("/registrar",context -> context.render("formulario.jte"));


    }

    private void eventosHtml()
    {
        javalin.post("/authetication",this::ingresoSistemas);
        javalin.post("/registrar",this::registarUsuario);
        javalin.get("/cerrar",this::closedSession);

    }

    private void ingresoSistemas(Context context)
    {
        String username = context.formParam("username");
        String password = context.formParam("password");

        this.response = new ErrorResponse(400,"Usuario o contraseña errónea");

        try
        {
            if(verifyCapcha(context))
            {
                Optional<Usuario> usuario = loginService.ingresoSistema(new ConseguirUsuario(username,password));
                usuario.ifPresentOrElse(
                        (user) -> {
                            context.sessionAttribute("user",user);
                            ResultadoLista resultadoLista = new ResultadoLista(null,user);
                            context.render("index.jte", Collections.singletonMap("resultadoLista",resultadoLista));
                        },
                        () ->  context.render("login.jte",Collections.singletonMap("response",response))
                );
            }

        } catch (Exception ex)
           {context.render("login.jte",Collections.singletonMap("response",response));}


    }

    private boolean isValidSession(Context context)
    {
        Usuario session = context.sessionAttribute("user");
        return session != null;
    }

    private void closedSession(Context context)
    {
        context.sessionAttribute("user",null);
        context.removeCookie("user");
        context.redirect("/");
    }

    private static boolean verifyCapcha(Context context) throws IOException
    {

        Map<String,String> params = new HashMap<>();
        params.put("response",context.formParam("h-captcha-response"));
        params.put("secret",System.getenv("secret"));



        URL url = new URL("https://hcaptcha.com/siteverify");
        HttpURLConnection connection =  (HttpURLConnection)  url.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        String posData = params.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        connection.getOutputStream().write(posData.getBytes());
        JsonNode responseJson = mapper.readTree(connection.getInputStream());

        return responseJson.get("success").asBoolean();
    };

    private void registarUsuario(Context context) throws IOException {
        this.response = null;

        if (verifyCapcha(context))
        {
            try {
                String dni = context.formParam("dni");
                String nombre = context.formParam("nombre");
                String apellido = context.formParam("apellido");
                LocalDate fechaNacimiento = LocalDate.parse(Objects.requireNonNull(context.formParam("fechaNacimiento")));
                String username = context.formParam("username");
                String password = context.formParam("password");
                String password2 = context.formParam("password2");
                String nacionalidad = context.formParam("nacionalidad");

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
                    clienteService.guardarUsuarioLogin(new Cliente(usuario,nacionalidad),new Login(username,password,usuario));
                    context.redirect("/");
                }
                context.render("formulario.jte",Collections.singletonMap("response",response));

            }catch (Exception ex)
            { this.response = new ErrorResponse(400,ex.getMessage());
                context.render("formulario.jte",Collections.singletonMap("response",response));
            }

        }else {context.render("formulario.jte",Collections.singletonMap("response",new ErrorResponse(400,"Captcha vacio")));};


    }

    private void generarPaises() throws IOException {

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
    }


    public static class MiddewareCookie implements Handler
    {

        @Override
        public void handle(@NotNull Context context) throws Exception
        {

            if(!isValidSession(context) && !context.path().equals("/registrar"))
              {context.render("login.jte");}

        }

        private boolean isValidSession(Context context)
        {
            Usuario user = context.sessionAttribute("user");
            return user != null;
        }

    }
}
