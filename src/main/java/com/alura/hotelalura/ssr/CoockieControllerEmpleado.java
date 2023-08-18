package com.alura.hotelalura.ssr;

import com.alura.hotelalura.repository.persistence.EmpleadoRepository;
import com.alura.hotelalura.repository.persistence.LoginRepository;
import com.alura.hotelalura.service.EmpleadoService;
import com.alura.hotelalura.service.LoginService;
import com.google.inject.Injector;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class CoockieControllerEmpleado
{
    private final Javalin javalin;
    private final Injector injector;
    private final LoginRepository loginService;
    private final EmpleadoRepository empleadoService;


    public CoockieControllerEmpleado(Javalin javalin,Injector injector)
    {
        this.javalin = javalin;
        this.injector = injector;
        this.loginService = injector.getInstance(LoginService.class);
        this.empleadoService = injector.getInstance(EmpleadoService.class);
        cargarHtml();
    }

    private void cargarHtml()
    {
        javalin.before(context -> {
            if(context.sessionAttribute("employer") == null && !context.path().equals("/registrar"))
               {context.render("employer/login.jte");}
        });

        javalin.get("/",context -> context.render("employer/login.jte"));
    }

    private boolean verificarCaptcha(Context context)
    {
        Map<String,String> params = new HashMap<>();
        params.put("response",context.formParam("h-captcha-response"));
        params.put("secret",System.getenv("secret"));

        return false;
    }




}
