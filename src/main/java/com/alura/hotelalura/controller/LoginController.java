package com.alura.hotelalura.controller;

import com.alura.hotelalura.auth.JWTGenerate;
import com.alura.hotelalura.model.Login;
import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.repository.dto.ConseguirUsuario;
import com.alura.hotelalura.repository.dto.RegistrarUsuario;
import com.alura.hotelalura.repository.dto.ResponseToken;
import com.alura.hotelalura.repository.persistence.ClienteRepository;
import com.alura.hotelalura.repository.persistence.EmpleadoRepository;
import com.alura.hotelalura.repository.persistence.LoginRepository;
import com.alura.hotelalura.service.ClienteService;
import com.alura.hotelalura.service.EmpleadoService;
import com.alura.hotelalura.service.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.Injector;
import io.javalin.Javalin;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;


public class LoginController
{
    private Javalin javalin;
    private Injector injector;
    private LoginRepository loginService;
    private ClienteRepository clienteService;
    private EmpleadoRepository empleadoService;
    private ObjectMapper mapper;

    public LoginController(Javalin javalin, Injector injector)
    {
        this.javalin = javalin;
        this.injector = injector;
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.loginService = injector.getInstance(LoginService.class);
        this.clienteService = injector.getInstance(ClienteService.class);
        this.empleadoService = injector.getInstance(EmpleadoService.class);
        cargarRutas();

    }

    private void cargarRutas()
    {
        javalin.post("/login/user",this::loguearse);
        javalin.post("/registrar/cliente",this::agregarCliente);
        javalin.post("/registrar/empleado",this::agreagarEmpleado);
    }

    private void loguearse(Context context) throws JsonProcessingException {

            ConseguirUsuario usuario = context.bodyAsClass(ConseguirUsuario.class);
            Optional<Usuario> myUsuario = loginService.ingresoSistema(usuario);
            myUsuario.ifPresent( user -> {
                try {
                    context.json(mapper.writeValueAsString(new ResponseToken(JWTGenerate.createTokenCliente(user.getDni()), "Bearer")));
                } catch (JsonProcessingException e) {
                    throw new BadRequestResponse("Uusario o contraseña incorrecta");
                }
            });


    }

    private void agregarCliente(Context context) throws JsonProcessingException {

        RegistrarUsuario registrarUsuario = context.bodyAsClass(RegistrarUsuario.class);
        Usuario usuario = registrarUsuario.usuario();

        validarDatos(registrarUsuario,usuario);

        Byte result = clienteService.guardarUsuarioLogin(usuario,new Login(registrarUsuario.username().trim(),
                                                                           registrarUsuario.password().trim(),
                                                                           usuario));
        if (result>=1)
           {context.status(201);
            context.json(mapper.writeValueAsString(clienteService.buscar(usuario.getDni().trim())));}
        else
           {throw new BadRequestResponse("Hay errores en la informacion");}



    }

    private void agreagarEmpleado(Context context) throws JsonProcessingException {
        RegistrarUsuario registrarUsuario = context.bodyAsClass(RegistrarUsuario.class);
        Usuario usuario = registrarUsuario.usuario();
        validarDatos(registrarUsuario,usuario);

        Byte result = empleadoService.guardarUsuarioLogin(usuario,new Login(registrarUsuario.username().trim(),
                                                                            registrarUsuario.password().trim(),
                                                                            usuario));
        if(result>=1)
          {context.status(201);
           context.json(mapper.writeValueAsString(empleadoService.buscar(usuario.getDni())));}
        else
          {throw new BadRequestResponse("Hay errores en la informacion");}

    }


    private void validarDatos(RegistrarUsuario registrarUsuario, Usuario usuario)
    {
        int edadActual = Period.between(usuario.getFechaNacimiento(), LocalDate.now()).getYears();
        if (edadActual<=17){throw new BadRequestResponse("Eres menor de edad");}

        if (usuario.getDni().trim().isEmpty() || usuario.getApellido().trim().isEmpty() ||
                usuario.getNombre().trim().isEmpty() || usuario.getTelefono().trim().isEmpty() ||
                registrarUsuario.username().isEmpty() || registrarUsuario.password().isEmpty()
        ) {throw new BadRequestResponse("Hay Campos vacios");}
    }


}
