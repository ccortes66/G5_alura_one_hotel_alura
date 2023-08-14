package com.alura.hotelalura.service.type;

import com.alura.hotelalura.model.type.HabitacionTipo;
import com.alura.hotelalura.repository.dto.RegistrarReserva;
import com.alura.hotelalura.repository.persistence.HabitacionTipoRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class HabitacionTipoService implements HabitacionTipoRepository
{
    final EntityManager entityManager;

    @Inject
    public HabitacionTipoService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<HabitacionTipo> listar() {
        TypedQuery<HabitacionTipo> query = entityManager.createQuery("SELECT HT FROM HabitacionTipo HT",HabitacionTipo.class);
        return  query.getResultList();
    }

    @Override
    public void guardar(HabitacionTipo objeto)
    {
        entityManager.getTransaction().begin();
        try {entityManager.persist(objeto);
             entityManager.getTransaction().commit();}
        catch (Exception ex)
        {entityManager.getTransaction().rollback();}
    }

    @Override
    public void eliminar(HabitacionTipo objeto)
    {
        entityManager.getTransaction().begin();
        try {entityManager.remove(objeto);
            entityManager.getTransaction().commit();}
        catch (Exception ex)
        {entityManager.getTransaction().rollback();}
    }

    @Override
    public HabitacionTipo buscar(String valor)
    {
        String jpql = "SELECT HT FROM HabitacionTipo HT "+
                      "WHERE HT.nombre = :valor  ";
        TypedQuery<HabitacionTipo> query = entityManager.createQuery(jpql, HabitacionTipo.class);
        query.setParameter("valor",valor.trim());
        return query.getSingleResult();
    }

    @Override
    public BigDecimal consultarPrecioHabitacion(RegistrarReserva habitacion)
    {
        HabitacionTipo habitacionTipo = this.buscar(habitacion.tipo().trim());
        long duracion = ChronoUnit.DAYS.between(habitacion.checkIn(),habitacion.checkOut());
        return habitacionTipo.getPrecioUnitario().multiply(new BigDecimal(duracion));
    }

    @Override
    public BigDecimal consultarPrecioHabitacion(LocalDate checkInt,LocalDate checkOut,String tipoHabitacion)
    {
        long duracion = ChronoUnit.DAYS.between(checkInt,checkOut);
        return this.buscar(tipoHabitacion.trim()).getPrecioUnitario().multiply(new BigDecimal(duracion));
    }
}
