package com.alura.hotelalura;

import com.alura.hotelalura.config.CDI;
import com.alura.hotelalura.controller.ClienteController;
import com.alura.hotelalura.controller.EmpleadoController;
import com.alura.hotelalura.controller.LoginController;
import com.alura.hotelalura.ssr.CookieController;
import com.alura.hotelalura.ssr.SsrClienteController;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.rendering.template.JavalinJte;

import java.util.Collections;
import java.util.Objects;


public class Main
{
    public static void main(String[] args)
    {
        JavalinJte.init();

        Injector injector = Guice.createInjector(new CDI());

       //rest api
        Javalin restApi = Javalin.create().start(8080);
        new ClienteController(restApi,injector);
        new LoginController(restApi,injector);
        new EmpleadoController(restApi,injector);



        //ssr
        Javalin ssr = Javalin.create().start(8083);
        new CookieController(ssr,injector);
        new SsrClienteController(ssr,injector);




    }

    static void RenderHelloWord(Context context)
    {
       PasingClass pasingClass = new PasingClass("Criss",1234);
       context.render("login.jte", Collections.singletonMap("pasingClass",pasingClass));
    }

    static void RenderHelloWordDos(Context context)
    {
        PasingClass pasingClass = new PasingClass("Criss",1234);
        context.render("formulario.jte", Collections.singletonMap("pasingClass",pasingClass));
    }

    static void ResultSet(Context context)
    {
        String nombre = context.formParam("nombre");
        String fecha = context.formParam("fecha");
        int habitacion = Integer.parseInt(Objects.requireNonNull(context.formParam("habitacion")));
        ResulsetColl coll = new ResulsetColl(nombre,fecha,habitacion);
        context.render("index.jte", Collections.singletonMap("coll",coll));
    }

    public record PasingClass(String username,
                              int userKarma)
    {}

    public record ResulsetColl(String nombre,
                               String fecha,
                               int habitacion){}

}

