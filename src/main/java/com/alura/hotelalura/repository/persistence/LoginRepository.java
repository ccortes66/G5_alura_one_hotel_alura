package com.alura.hotelalura.repository.persistence;

import com.alura.hotelalura.model.Login;
import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.repository.dto.ConseguirUsuario;

import java.util.Optional;


public interface LoginRepository
{
    Optional<Usuario> ingresoSistema(ConseguirUsuario usuario);

}
