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
    public Byte guardarUsuarioLogin(Usuario usuario, Login login)
    {
        entityManager.getTransaction().begin();
        try
        {
            entityManager.persist(usuario);
            entityManager.persist(new Cliente(usuario));
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
        { jpql = "SELECT L FROM Login L "+
                 "JOIN FETCH L.usuario usr "+
                 "WHERE  usr.dni = :codigo";

            Login login = entityManager.createQuery(jpql,Login.class)
                                                        .setParameter("codigo",codigo.trim())
                                                        .getSingleResult();
            entityManager.remove(login);

            jpql = "SELECT c FROM Cliente c "+
                    "JOIN FETCH c.usuario usr "+
                    "WHERE usr.dni = :codigo";

            Cliente cliente = entityManager.createQuery(jpql,Cliente.class)
                                                                .setParameter("codigo",codigo.trim())
                                                                .getSingleResult();
            entityManager.remove(cliente);

            jpql = "SELECT u FROM Usuario u "+
                    "WHERE u.dni = :codigo";

            Usuario usuario = entityManager.createQuery(jpql,Usuario.class)
                                                                    .setParameter("codigo",codigo.trim())
                                                                    .getSingleResult();
            entityManager.remove(usuario);

            entityManager.flush();
            entityManager.getTransaction().commit();
            entityManager.clear();
            return 1;

        }catch (Exception ex)
        {entityManager.getTransaction().rollback();
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
