package com.alura.hotelalura.repository.persistence;

import com.alura.hotelalura.model.Cliente;
import com.alura.hotelalura.model.Login;
import com.alura.hotelalura.model.Usuario;


public interface ClienteRepository extends CompuestoRepository<Cliente,String>
{

    Cliente agregarVip(Cliente cliente, Byte numero);
    Byte guardarUsuarioLogin(Cliente cliente, Login login);
    Byte modificar(Usuario usuario, String dni);

}
