package com.happyfest.dao;

import com.happyfest.model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClienteDAO extends DAO {
    public void save(Cliente cliente) throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(cliente);
            transaction.commit();
        } catch (PersistenceException pe) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaDacException("Erro ao tentar salvar o cliente.", pe);
        } finally {
            em.close();
        }
    }

    public Cliente update(Cliente cliente) throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Cliente resultado;
        try {
            resultado = em.merge(cliente);
            transaction.commit();
        } catch (PersistenceException pe) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaDacException("Erro ao tentar atualizar o cliente.", pe);
        } finally {
            em.close();
        }
        return resultado;
    }

    public void delete(Cliente cliente) throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            cliente = em.find(Cliente.class, cliente.getId());
            em.remove(cliente);
            transaction.commit();
        } catch (PersistenceException pe) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaDacException("Erro ao tentar remover o cliente.", pe);
        } finally {
            em.close();
        }
    }

    public Cliente getByID(Long id) throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } catch (PersistenceException pe) {
            throw new PersistenciaDacException("Erro ao tentar recuperar o cliente pelo ID.", pe);
        } finally {
            em.close();
        }
    }

    public List<Cliente> getAll() throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
            return query.getResultList();
        } catch (PersistenceException pe) {
            throw new PersistenciaDacException("Erro ao tentar recuperar todos os clientes.", pe);
        } finally {
            em.close();
        }
    }
}
