package com.alura.hotelalura.service;

import com.alura.hotelalura.model.Cliente;
import com.alura.hotelalura.model.Empleado;
import com.alura.hotelalura.model.Login;
import com.alura.hotelalura.model.Usuario;
import com.alura.hotelalura.repository.persistence.EmpleadoRepository;
import com.google.inject.Inject;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmpleadoService implements EmpleadoRepository
{
    private final EntityManager entityManager;
    private final Argon2 argon2 = Argon2Factory.create();
    private String jpql;

    @Inject
    public EmpleadoService(EntityManager entityManager)
    {
        this.entityManager = entityManager;

    }

    @Override
    public List<Empleado> listar(Integer skip, Integer limit)
    {
        TypedQuery<Empleado> query = entityManager.createQuery("SELECT E FROM Empleado E",Empleado.class);
        query.setFirstResult(skip);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public Empleado buscar(String valor) {
        jpql = "SELECT E FROM Empleado E "+
                "JOIN FETCH E.usuario usuario "+
                "WHERE usuario.dni =:valor";
        TypedQuery<Empleado> query = entityManager.createQuery(jpql,Empleado.class);
        query.setParameter("valor",valor.trim());
        Empleado empleado = query.getSingleResult();
        entityManager.refresh(empleado);
        return empleado;
    }

    @Override
    public Byte eliminar(String codigo)
    {
        jpql = "SELECT L FROM Login L "+
                "JOIN FETCH L.usuario usuario "+
                "WHERE usuario.dni = :codigo";
        entityManager.getTransaction().begin();
        try
        {Login login = entityManager.createQuery(jpql,Login.class)
                                                    .setParameter("codigo",codigo.trim())
                                                    .getSingleResult();
        entityManager.remove(login);

        jpql = "SELECT e FROM Empleado e "+
                "JOIN FETCH e.usuario usuario "+
                "WHERE usuario.dni = :codigo";

        Empleado empleado = entityManager.createQuery(jpql,Empleado.class)
                                                        .setParameter("codigo",codigo.trim())
                                                        .getSingleResult();
        entityManager.remove(empleado);

        jpql = "SELECT u FROM Usuario u "+
                "WHERE u.dni = :codigo";

        Usuario usuario = entityManager.createQuery(jpql,Usuario.class)
                                                        .setParameter("codigo",codigo.trim())
                                                        .getSingleResult();
        entityManager.remove(usuario);

        entityManager.getTransaction().commit();
        return 1;

        }catch (Exception ex)
        {entityManager.getTransaction().rollback();
         return 0;}
    }


    @Override
    public Byte guardarUsuarioLogin(Usuario usuario, Login login)
    {
        entityManager.getTransaction().begin();
        try
        {
            entityManager.persist(usuario);
            entityManager.persist(new Empleado(usuario));
            login.setPassword(argon2.hash(2,65536,2,login.getPassword().toCharArray()));
            entityManager.persist(login);
            entityManager.getTransaction().commit();
            return 1;

        }catch (Exception ex)
        {entityManager.getTransaction().rollback();
         return 0;}

    }

    @Override
    public Byte modificar(Usuario usuario, String dni)
    {
        entityManager.getTransaction().begin();

        jpql = "UPDATE Usuario u SET " +
                "u.nombre= :nombre, u.apellido= :apellido, " +
                "u.fechaNacimiento= :fechaNacimiento, u.telefono= :telefono " +
                "WHERE dni= :dni" ;
        try {
            Query query = entityManager.createQuery(jpql);
            query.setParameter("nombre", usuario.getNombre());
            query.setParameter("apellido", usuario.getApellido());
            query.setParameter("fechaNacimiento", usuario.getFechaNacimiento());
            query.setParameter("telefono", usuario.getTelefono());
            query.setParameter("dni", dni);
            query.executeUpdate();

            entityManager.flush();
            entityManager.getTransaction().commit();
            entityManager.clear();
            return 1;
        } catch (Exception e)
        { entityManager.getTransaction().rollback();
            return 0; }
    }

}
