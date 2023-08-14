package com.alura.hotelalura.service;

import com.alura.hotelalura.model.Cliente;
import com.alura.hotelalura.model.Reserva;
import com.alura.hotelalura.repository.dto.ReservaInfo;
import com.alura.hotelalura.repository.dto.ReservaInfoEmpleados;
import com.alura.hotelalura.repository.dto.ReservaInfoSsr;
import com.alura.hotelalura.repository.persistence.ReservaRepository;
import com.google.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ReservaService implements ReservaRepository
{
    private final EntityManager entityManager;
    private String jpql;

    @Inject
    public ReservaService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Reserva> listar(Integer skip, Integer limit)
    {
        return entityManager.createQuery("SELECT RS FROM Reserva RS ",Reserva.class).getResultList();
    }

    @Override
    public Reserva buscar(String valor)
    {
        jpql = "SELECT RS FROM Reserva RS " +
                "WHERE RS.metodoPago IS NOT NULL AND RS.reserva = :valor";

        return entityManager.createQuery(jpql,Reserva.class)
                                                    .setParameter("valor",valor.trim())
                                                    .getSingleResult();
    }

    @Override
    public Byte eliminar(String codigo)
    {
        entityManager.getTransaction().begin();
        try
        {
            entityManager.remove(this.buscar(codigo));
            entityManager.flush();
            entityManager.getTransaction().commit();
            entityManager.clear();
            return 1;
        }catch (Exception ex)
        {  entityManager.getTransaction().rollback();
         return 0;
        }
    }


    @Override
    public void generar(Reserva reserva)
    {
        entityManager.getTransaction().begin();
        try
          {
              entityManager.merge(reserva);
              entityManager.flush();
              entityManager.getTransaction().commit();
              entityManager.clear();
          }
        catch (Exception ex)
          {entityManager.getTransaction().rollback();}
    }

    @Override
    public ReservaInfo buscar(String dni, String reserva)
    {
        jpql = "SELECT NEW com.alura.hotelalura.repository.dto.ReservaInfo(RS.reserva,RS.checkIn,RS.checkOut,RS.valorReserva,RS.habitacion.tipo.nombre,RS.habitacion.numero) "+
                "FROM Reserva RS " +
                "WHERE RS.cliente.usuario.dni = :dni "+
                "AND RS.reserva = :reserva ";

        TypedQuery<ReservaInfo> query = entityManager.createQuery(jpql,ReservaInfo.class);
        query.setParameter("dni",dni);
        query.setParameter("reserva",reserva.trim());
        return query.getSingleResult();
    }

    @Override
    public ReservaInfoSsr buscarResultado(String dni)
    {
        jpql = "SELECT NEW com.alura.hotelalura.repository.dto.ReservaInfoSsr(RS.reserva,RS.valorReserva,RS.habitacion.numero) "+
                "FROM Reserva RS " +
                "WHERE RS.cliente.usuario.dni = :dni "+
                "ORDER BY RS.id DESC";
        TypedQuery<ReservaInfoSsr> query = entityManager.createQuery(jpql, ReservaInfoSsr.class);
        query.setParameter("dni",dni.trim());
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    @Override
    public List<ReservaInfoEmpleados> listaReservaActual(Integer skip, Integer limit)
    {
        jpql = "SELECT NEW com.alura.hotelalura.repository.dto.ReservaInfoEmpleados("+
                "RS.reserva,RS.checkIn,RS.checkOut,RS.habitacion.tipo.nombre,RS.habitacion.numero,RS.metodoPago.nombre," +
                "RS.habitacion.reservado) "+
                "FROM Reserva RS ";

        TypedQuery<ReservaInfoEmpleados> query = entityManager.createQuery(jpql,ReservaInfoEmpleados.class);
        query.setFirstResult(skip);
        query.setMaxResults(limit);
        return query.getResultList();
    }


    @Override
    public List<ReservaInfo> listarReservaPorCliente(String dni) {
        jpql = "SELECT NEW com.alura.hotelalura.repository.dto.ReservaInfo(RS.reserva,RS.checkIn,RS.checkOut,RS.valorReserva,RS.habitacion.tipo.nombre,RS.habitacion.numero) "+
                "FROM Reserva RS " +
                "WHERE RS.cliente.usuario.dni = :dni ";
        TypedQuery<ReservaInfo> query = entityManager.createQuery(jpql, ReservaInfo.class);
        query.setParameter("dni",dni.trim());
        return query.getResultList();
    }





}
