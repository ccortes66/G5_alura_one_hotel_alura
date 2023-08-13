package com.alura.hotelalura.repository.persistence;

import java.util.List;

public interface UnitarioRepository <T>
{
    List<T> listar();
    void guardar(T objeto);
    void eliminar(T objeto);
    T buscar(String valor);
}
