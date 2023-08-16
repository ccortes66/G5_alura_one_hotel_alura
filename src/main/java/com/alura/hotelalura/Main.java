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
import io.javalin.rendering.template.JavalinJte;


public class Main
{
    public static void main(String[] args)
    {
        JavalinJte.init();

        Injector injector = Guice.createInjector(new CDI());


        //csr
        Javalin restApi = Javalin.create().start(8080);
        new ClienteController(restApi,injector);
        new LoginController(restApi,injector);
        new EmpleadoController(restApi,injector);


        //ssr
        Javalin ssr = Javalin.create().start(8083);
        new CookieController(ssr,injector);
        new SsrClienteController(ssr,injector);


    }

}

