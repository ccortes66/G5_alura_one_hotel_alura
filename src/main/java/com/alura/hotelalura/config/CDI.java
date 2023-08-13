package com.alura.hotelalura.config;

import com.alura.hotelalura.controller.ClienteController;
import com.alura.hotelalura.repository.persistence.*;
import com.alura.hotelalura.service.*;
import com.alura.hotelalura.service.type.EmpleadoTipoService;
import com.alura.hotelalura.service.type.HabitacionTipoService;
import com.alura.hotelalura.service.type.MetodoDePagoService;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import io.javalin.Javalin;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CDI extends AbstractModule
{
    @Override
    protected void configure() {
        bind(EntityManager.class).toProvider(EntityManagerProvider.class);

        bind(ClienteRepository.class).to(ClienteService.class);
        bind(LoginRepository.class).to(LoginService.class);
        bind(HabitacionTipoRepository.class).to(HabitacionTipoService.class);
        bind(HabitacionRepository.class).to(HabitacionService.class);
        bind(ReservaRepository.class).to(ReservaService.class);
        bind(EmpleadoRepository.class).to(EmpleadoService.class);
        bind(EmpleadoTipoRepository.class).to(EmpleadoTipoService.class);
        bind(MetodoDePagoRepository.class).to(MetodoDePagoService.class);

    }
}



