package com.alura.hotelalura.repository.persistence;

import com.alura.hotelalura.model.Cliente;
import com.alura.hotelalura.model.Empleado;
import com.alura.hotelalura.model.Login;
import com.alura.hotelalura.model.Usuario;

public interface EmpleadoRepository extends CompuestoRepository<Empleado,String>
{
    Byte guardarUsuarioLogin(Usuario usuario, Login login);
    Byte modificar(Usuario usuario, String dni);

}
