package com.alura.hotelalura.service;

import com.alura.hotelalura.model.Login;
import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.repository.dto.ConseguirUsuario;
import com.alura.hotelalura.repository.persistence.LoginRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;


public class LoginService implements LoginRepository
{
    private final EntityManager entityManager;
    private Argon2 argon2 = Argon2Factory.create();
    private String jpql;

    @Inject
    public LoginService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Usuario> ingresoSistema(ConseguirUsuario usuario)
    {
        jpql = "SELECT login FROM Login login " +
                "JOIN FETCH login.usuario usr "+
                "WHERE login.username = :username ";
        Login resultLogin = entityManager.createQuery(jpql,Login.class)
                                                            .setParameter("username",usuario.username().trim())
                                                            .getSingleResult();
        if(!argon2.verify(resultLogin.getPassword(),usuario.password().trim().toCharArray()))
        {
            return Optional.empty();
        }

        return Optional.of(resultLogin.getUsuario());

    }






}
