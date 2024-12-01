package com.happyfest.dao;

import com.happyfest.model.Reserva;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class ReservaDAO extends DAO {
    public void save(Reserva reserva) throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(reserva);
            transaction.commit();
        } catch (PersistenceException pe) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaDacException("Erro ao tentar salvar a reserva.", pe);
        } finally {
            em.close();
        }
    }

    public Reserva update(Reserva reserva) throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Reserva resultado;
        try {
            resultado = em.merge(reserva);
            transaction.commit();
        } catch (PersistenceException pe) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaDacException("Erro ao tentar atualizar a reserva.", pe);
        } finally {
            em.close();
        }
        return resultado;
    }

    public void delete(Reserva reserva) throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            reserva = em.find(Reserva.class, reserva.getId());
            em.remove(reserva);
            transaction.commit();
        } catch (PersistenceException pe) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaDacException("Erro ao tentar remover a reserva.", pe);
        } finally {
            em.close();
        }
    }

    public Reserva getByID(Long id) throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reserva.class, id);
        } catch (PersistenceException pe) {
            throw new PersistenciaDacException("Erro ao tentar recuperar a reserva pelo ID.", pe);
        } finally {
            em.close();
        }
    }

    public List<Reserva> getAll() throws PersistenciaDacException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Reserva> query = em.createQuery("SELECT r FROM Reserva r", Reserva.class);
            return query.getResultList();
        } catch (PersistenceException pe) {
            throw new PersistenciaDacException("Erro ao tentar recuperar todas as reservas.", pe);
        } finally {
            em.close();
        }
    }
}
