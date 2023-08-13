package com.alura.hotelalura.repository.persistence;

import java.util.List;

public interface CompuestoRepository <T,E>
{
    List<T> listar(Integer skip, Integer limit);
    T buscar(E valor);
    Byte eliminar(E codigo);

}
