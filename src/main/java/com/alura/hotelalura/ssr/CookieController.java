package com.alura.hotelalura.ssr;

import com.alura.hotelalura.model.Login;
import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.repository.dto.ConseguirUsuario;
import com.alura.hotelalura.repository.persistence.ClienteRepository;
import com.alura.hotelalura.repository.persistence.LoginRepository;
import com.alura.hotelalura.service.ClienteService;
import com.alura.hotelalura.service.LoginService;
import com.alura.hotelalura.ssr.error.ErrorResponse;
import com.google.inject.Injector;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

public class CookieController
{

    private final Javalin javalin;
    private final Injector injector;
    private ErrorResponse response;
    private final LoginRepository loginService;
    private final ClienteRepository clienteService;

    public CookieController(Javalin javalin, Injector injector)
    {
        this.javalin = javalin;
        this.injector = injector;
        this.loginService = injector.getInstance(LoginService.class);
        this.clienteService = injector.getInstance(ClienteService.class);
        cargarHtml();
        eventosHtml();
    }

    private void cargarHtml()
    {
        //validador de coockies
        javalin.before(context -> {
            if(!isValidCookie(context) && !context.path().equals("/registrar"))
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
            Optional<Usuario> usuario = loginService.ingresoSistema(new ConseguirUsuario(username,password));
            usuario.ifPresentOrElse(
                    (user) -> {
                        context.render("index.jte", Collections.singletonMap("user",user));
                        setSessionCookie(context,user.getDni());
                     },
                    () -> { context.render("login.jte",Collections.singletonMap("response",response));}
                    );
        } catch (Exception ex)
           {context.render("login.jte",Collections.singletonMap("response",response));}


    }

    private boolean isValidCookie(Context context)
    {
        String coockie = getSessionCookie(context);
        return coockie != null && !coockie.isEmpty();
    }

    private void setSessionCookie(Context context,String dni)
    {
       context.cookie("dni",dni,3600);
    }

    public String getSessionCookie(Context context)
    {
        return context.cookie("dni");
    }

    private void closedSession(Context context)
    {
        context.removeCookie("dni");
        context.redirect("/");
    }

    private void registarUsuario(Context context)
    {
        String dni = context.formParam("dni");
        String nombre = context.formParam("nombre");
        String apellido = context.formParam("apellido");
        LocalDate fechaNacimiento = LocalDate.parse(Objects.requireNonNull(context.formParam("fechaNacimiento")));
        String username = context.formParam("username");
        String password = context.formParam("password");
        String password2 = context.formParam("password2");


        try {
            if (!dni.matches("^[0-9A-Za-z]+$") || !nombre.matches("^[0-9A-Za-z ]+$") ||
                    !apellido.matches("^[0-9A-Za-z ]+$") || !username.matches("^[0-9A-Za-z]+$")) {
                this.response = new ErrorResponse(400, "Campos llevan caracteres especiales");
                context.render("formulario.jte", Collections.singletonMap("response", response));
            }
            int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
            if (edad <= 17) {
                this.response = new ErrorResponse(400, "Eres menor de edad");
                context.render("formulario.jte", Collections.singletonMap("response", response));
            }

            if (!password.equals(password2)) {
                this.response = new ErrorResponse(400, "Contraseña no coinciden");
                context.render("formulario.jte", Collections.singletonMap("response", response));
            }

            Usuario usuario = new Usuario(dni,nombre,apellido,fechaNacimiento," ");
            clienteService.guardarUsuarioLogin(usuario,new Login(username,password,usuario));


        }catch (Exception ex) { this.response = new ErrorResponse(400,ex.getMessage());
                               context.render("formulario.jte", Collections.singletonMap("response", response));}



    }
}