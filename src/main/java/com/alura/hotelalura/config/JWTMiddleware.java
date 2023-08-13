package com.alura.hotelalura.config;

import com.alura.hotelalura.auth.JWTGenerate;
import com.alura.hotelalura.repository.persistence.ClienteRepository;
import com.alura.hotelalura.repository.persistence.EmpleadoRepository;
import com.alura.hotelalura.service.ClienteService;
import com.alura.hotelalura.service.EmpleadoService;
import com.google.inject.Injector;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import org.jetbrains.annotations.NotNull;

public final class JWTMiddleware
{

    private static Injector myinjector;

    private JWTMiddleware() {}

    public static class JWTMiddlewareCliente implements Handler
    {
        private final ClienteRepository repository;

        public JWTMiddlewareCliente(Injector injector){
            myinjector = injector;
            repository = myinjector.getInstance(ClienteService.class);
        }

        @Override
        public void handle(@NotNull Context context) throws Exception
        {
            String token = context.header("Authorization");

            if(token == null || !token.startsWith("Bearer "))
            {throw new UnauthorizedResponse("token invalido");}

            token = token.substring(7);

            if(!JWTGenerate.validateToken(token))
            {throw new UnauthorizedResponse("Accesso no autorizado");}

            try { repository.buscar(JWTGenerate.getMyCliente()); }
            catch (Exception exception) {throw new UnauthorizedResponse("No eres Cliente");}
        }
    }


    public static class JWTMiddlewareEmpleado implements Handler
    {
        private final EmpleadoRepository repository;
        public JWTMiddlewareEmpleado(Injector injector)
        {
            myinjector = injector;
            repository = injector.getInstance(EmpleadoService.class);
        }

        @Override
        public void handle(@NotNull Context context) throws Exception
        {
            String token = context.header("Authorization");

            if(token == null || !token.startsWith("Bearer "))
            {throw new UnauthorizedResponse("token invalido");}

            token = token.substring(7);

            if(!JWTGenerate.validateToken(token))
            {throw new UnauthorizedResponse("Accesso no autorizado");}

            try { repository.buscar(JWTGenerate.getMyCliente()); }
            catch (Exception exception) {throw new UnauthorizedResponse("No eres Empleado");}
        }
    }



}
