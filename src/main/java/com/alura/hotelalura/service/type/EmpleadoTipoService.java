package com.alura.hotelalura.service.type;

import com.alura.hotelalura.model.Login;
import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.model.type.EmpleadoTipo;
import com.alura.hotelalura.model.type.HabitacionTipo;
import com.alura.hotelalura.repository.persistence.EmpleadoTipoRepository;
import com.google.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

public class EmpleadoTipoService implements EmpleadoTipoRepository
{
    final EntityManager entityManager;
    @Inject
    public EmpleadoTipoService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void guardar(EmpleadoTipo objeto)
    {
        entityManager.getTransaction().begin();
        try {entityManager.persist(objeto);
             entityManager.getTransaction().commit();}
        catch (Exception ex)
             {entityManager.getTransaction().rollback();}

    }

    @Override
    public void eliminar(EmpleadoTipo objeto)
    {
       entityManager.getTransaction().begin();
       try {entityManager.remove(objeto);
            entityManager.getTransaction().commit();}
        catch (Exception ex)
        {entityManager.getTransaction().rollback();}
    }

    @Override
    public List<EmpleadoTipo> listar()
    {
        TypedQuery<EmpleadoTipo> query = entityManager.createQuery("SELECT ET FROM EmpleadoTipo ET",EmpleadoTipo.class);
        return query.getResultList();
    }

    @Override
    public EmpleadoTipo buscar(String nombre)
    {
        String jpql = "SELECT ET FROM EmpleadoTipo ET " +
                      "WHERE ET.nombre = :nombre";
        return entityManager.createQuery(jpql,EmpleadoTipo.class)
                                                       .setParameter("nombre",nombre.trim())
                                                       .getSingleResult();
    }



}
