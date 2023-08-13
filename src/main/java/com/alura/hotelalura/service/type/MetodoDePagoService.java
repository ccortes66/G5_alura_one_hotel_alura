package com.alura.hotelalura.service.type;

import com.alura.hotelalura.model.type.MetodoPago;
import com.alura.hotelalura.repository.persistence.MetodoDePagoRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MetodoDePagoService implements MetodoDePagoRepository
{
    final EntityManager entityManager;
    private String jpql;

    @Inject
    public MetodoDePagoService(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<MetodoPago> listar()
    {
        TypedQuery<MetodoPago> query = entityManager.createQuery("SELECT MP FROM MetodoPago MP",MetodoPago.class);
        return query.getResultList();
    }

    @Override
    public void guardar(MetodoPago objeto)
    {
        entityManager.getTransaction().begin();
        try {entityManager.persist(objeto);
            entityManager.getTransaction().commit();}
        catch (Exception ex)
        {entityManager.getTransaction().rollback();}

    }

    @Override
    public void eliminar(MetodoPago objeto)
    {
        entityManager.getTransaction().begin();
        try {entityManager.remove(objeto);
             entityManager.getTransaction().commit();}
        catch (Exception ex)
        {entityManager.getTransaction().rollback();}
    }

    @Override
    public MetodoPago buscar(String valor)
    {
        jpql = "SELECT MP FROM MetodoPago MP "+
                "WHERE MP.nombre = :valor";
        TypedQuery<MetodoPago> query = entityManager.createQuery(jpql, MetodoPago.class);
        query.setParameter("valor",valor.trim());
        return query.getSingleResult();
    }


}
