package com.alura.hotelalura;

import com.alura.hotelalura.config.CDI;
import com.alura.hotelalura.ssr.CoockieControllerEmpleado;
import com.alura.hotelalura.ssr.CookieController;
import com.alura.hotelalura.ssr.SSrEmpleadoController;
import com.alura.hotelalura.ssr.SsrClienteController;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import java.io.IOException;


public class Main
{
    public static void main(String[] args) throws IOException {
        JavalinJte.init();
        Injector injector = Guice.createInjector(new CDI());

        //csr
        /*
        Javalin restApi = Javalin.create().start(8080);
        new ClienteController(restApi,injector);
        new LoginController(restApi,injector);
        new EmpleadoController(restApi,injector);
        */

        //ssr cliente
        Javalin ssr = Javalin.create().start(8080);
        new CookieController(ssr,injector);
        new SsrClienteController(ssr,injector);

        //ssr empleado
        Javalin ssrEmpleados = Javalin.create().start(8083);
        new CoockieControllerEmpleado(ssrEmpleados,injector);
        new SSrEmpleadoController(ssrEmpleados,injector);


    }

}

