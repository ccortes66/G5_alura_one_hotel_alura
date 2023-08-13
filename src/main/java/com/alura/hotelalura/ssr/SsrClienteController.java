package com.alura.hotelalura.ssr;

import com.alura.hotelalura.repository.dto.ReservaInfo;
import com.alura.hotelalura.repository.persistence.ReservaRepository;
import com.alura.hotelalura.service.ReservaService;
import com.alura.hotelalura.ssr.error.ErrorResponse;
import com.google.inject.Injector;
import io.javalin.Javalin;

import java.util.Collections;
import java.util.List;

public class SsrClienteController
{
    private final Javalin javalin;
    private final Injector injector;
    private ErrorResponse response;
    private final ReservaRepository reservaService;

    public SsrClienteController(Javalin javalin, Injector injector)
    {
        this.javalin = javalin;
        this.injector = injector;
        this.reservaService = injector.getInstance(ReservaService.class);
        cargarEvento();
    }

    private void cargarEvento()
    {
        javalin.before("/listar/reservaciones",new CookieController.MiddewareCookie());
        javalin.get("/listar/reservaciones",context -> {
            List<ReservaInfo> infoList = reservaService.listarReservaPorCliente(CookieController.getDinUsusario());
            context.render("index.jte", Collections.singletonMap("infoList",infoList));
        });


    }
}
