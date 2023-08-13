package com.alura.hotelalura.service;

import com.alura.hotelalura.model.Habitacion;
import com.alura.hotelalura.model.Reserva;
import com.alura.hotelalura.repository.dto.MostarHabitacionReserva;
import com.alura.hotelalura.repository.persistence.HabitacionRepository;
import com.google.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class HabitacionService implements HabitacionRepository
{
    private final EntityManager entityManager;
    private String jpql;

    @Inject
    public HabitacionService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Habitacion> listar(Integer skip, Integer limit)
    {
        jpql = "SELECT H FROM Habitacion H "+
                "JOIN FETCH H.tipo T "+
                "WHERE H.reservado = false "+
                "ORDER BY H.id DESC ";

        TypedQuery<Habitacion> query = entityManager.createQuery(jpql,Habitacion.class);
        query.setFirstResult(skip);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public Habitacion buscar(Integer valor)
    {
        jpql = "SELECT HB FROM Habitacion HB "+
                "JOIN FETCH HB.tipo T "+
                "WHERE HB.numero = :valor";

        Habitacion habitacion = entityManager.createQuery(jpql,Habitacion.class)
                                                                .setParameter("valor",valor)
                                                                .getSingleResult();
        entityManager.refresh(habitacion);
        return habitacion;
    }

    @Override
    public Byte guardar(Habitacion habitacion)
    {
        entityManager.getTransaction().begin();
        try
        {
            entityManager.persist(habitacion);
            entityManager.flush();
            entityManager.getTransaction().commit();
            entityManager.clear();
            return 1;
        }catch (Exception ex)
        {entityManager.getTransaction().rollback();
         return 0;}
    }



    @Override
    public Byte eliminar(Integer codigo)
    {
        entityManager.getTransaction().begin();
        try
        {   entityManager.remove(this.buscar(codigo));
            entityManager.flush();
            entityManager.getTransaction().commit();
            entityManager.clear();
            return 1;
        }catch (Exception e)
        {entityManager.getTransaction().rollback();
         return 0;}
    }


    @Override
    public List<Habitacion> listarHabitacionDisponible(String tipo)
    {
        jpql = "SELECT HB FROM Habitacion HB " +
                "JOIN FETCH HB.tipo tp "+
                "WHERE HB.reservado = false AND "+
                "tp.nombre = :tipo";

        TypedQuery<Habitacion> query = entityManager.createQuery(jpql, Habitacion.class);
        query.setParameter("tipo",tipo);
        return query.getResultList();
    }

    @Override
    public Habitacion asignarHabitacion(String tipo)
    {
        jpql = "SELECT HB FROM Habitacion HB " +
                "JOIN FETCH HB.tipo tp "+
                "WHERE HB.reservado = false AND "+
                "tp.nombre = :tipo ORDER BY HB.numero ASC";

        return  entityManager.createQuery(jpql, Habitacion.class)
                                                       .setParameter("tipo",tipo)
                                                       .setMaxResults(1)
                                                       .getSingleResult();
    }

    @Override
    public void guardarHabitacionPorReseva(Habitacion habitacion)
    {
        entityManager.getTransaction().begin();
        try
        {
            habitacion.setReservado(true);
            entityManager.merge(habitacion);
            entityManager.flush();
            entityManager.getTransaction().commit();
            entityManager.clear();
        }catch (Exception ex)
         {entityManager.getTransaction().rollback();}
    }


}
