package com.alura.hotelalura.service;



import com.alura.hotelalura.model.*;
import com.alura.hotelalura.repository.persistence.ClienteRepository;
import com.google.inject.Inject;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClienteService implements ClienteRepository
{

    private final EntityManager entityManager;
    private Argon2 argon2 = Argon2Factory.create();
    private String jpql;

    @Inject
    public ClienteService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Cliente> listar(Integer skip, Integer limit) {

        TypedQuery<Cliente> query = entityManager.createQuery("SELECT C FROM Cliente AS C", Cliente.class);
        query.setFirstResult(skip);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public Cliente buscar(String valor) {
        jpql = "SELECT cliente FROM Cliente cliente " +
                "JOIN FETCH cliente.usuario usuario  " +
                "WHERE usuario.dni = :valor";

           return entityManager.createQuery(jpql,Cliente.class)
                                            .setParameter("valor",valor)
                                            .getSingleResult();



    }


    @Override
    public Byte guardarUsuarioLogin(Cliente cliente, Login login)
    {
        entityManager.getTransaction().begin();
        try
        {
            entityManager.persist(cliente.getUsuario());
            entityManager.persist(cliente);
            login.setPassword(argon2.hash(2,65536,2,login.getPassword().toCharArray()));
            entityManager.persist(login);
            entityManager.flush();
            entityManager.getTransaction().commit();
            entityManager.clear();
            return 1;

        }catch (Exception ex)
        {entityManager.getTransaction().rollback();
         return 0;}

    }

    @Override
    public Byte modificar(Usuario usuario,String dni)
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

    @Override
    public Byte eliminar(String codigo)
    {
        entityManager.getTransaction().begin();
        try
        {
            Cliente cliente = this.buscar(codigo);

            Query query = entityManager.createQuery("DELETE FROM Reserva RS WHERE RS.cliente = :cl");
            query.setParameter("cl",cliente);
            query.executeUpdate();

            query = entityManager.createQuery("DELETE FROM Login l WHERE l.usuario = :cl");
            query.setParameter("cl",cliente.getUsuario());
            query.executeUpdate();

            entityManager.remove(cliente);;

            entityManager.flush();
            entityManager.getTransaction().commit();
            entityManager.clear();
            return 1;

        }catch (Exception ex)
        {entityManager.getTransaction().rollback();
            ex.printStackTrace();
            return 0;}
    }


    @Override
    public Cliente agregarVip(Cliente cliente, Byte numero) {
        cliente.setVip(numero);
        Cliente clienteActual = entityManager.merge(cliente);
        entityManager.getTransaction().commit();
        return clienteActual;
    }
}
