package com.alura.hotelalura.repository.dto;

import com.alura.hotelalura.model.Cliente;

import java.util.List;

public record ListarDataCliente(List<Cliente> clientes,
                                Integer skip,
                                Integer limit) { }
