package com.happyfest.dao;

import com.happyfest.model.Brinquedo;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class BrinquedoDAO extends DAO {
    public void save(Brinquedo brinquedo) throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(brinquedo);
            transaction.commit();
        } catch (PersistenceException pe) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaDacException("Erro ao tentar salvar o brinquedo.", pe);
        } finally {
            em.close();
        }
    }

    public Brinquedo update(Brinquedo brinquedo) throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Brinquedo resultado;
        try {
            resultado = em.merge(brinquedo);
            transaction.commit();
        } catch (PersistenceException pe) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaDacException("Erro ao tentar atualizar o brinquedo.", pe);
        } finally {
            em.close();
        }
        return resultado;
    }

    public void delete(Brinquedo brinquedo) throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            brinquedo = em.find(Brinquedo.class, brinquedo.getId());
            em.remove(brinquedo);
            transaction.commit();
        } catch (PersistenceException pe) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaDacException("Erro ao tentar remover o brinquedo.", pe);
        } finally {
            em.close();
        }
    }

    public Brinquedo getByID(Long id) throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        try {
            return em.find(Brinquedo.class, id);
        } catch (PersistenceException pe) {
            throw new PersistenciaDacException("Erro ao tentar recuperar o brinquedo pelo ID.", pe);
        } finally {
            em.close();
        }
    }

    public List<Brinquedo> getAll() throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Brinquedo> query = em.createQuery("SELECT b FROM Brinquedo b", Brinquedo.class);
            return query.getResultList();
        } catch (PersistenceException pe) {
            throw new PersistenciaDacException("Erro ao tentar recuperar todos os brinquedos.", pe);
        } finally {
            em.close();
        }
    }
}
