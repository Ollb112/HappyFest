package com.happyfest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.happyfest.model.Pagamento;

public class PagamentoDAO extends DAO {
	public void save(Pagamento pagamento) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			em.persist(pagamento);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar salvar o Pedido.", pe);
		} finally {
			em.close();
		}
	}

	public Pagamento update(Pagamento pagamento) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Pagamento resultado = pagamento;
		try {
			resultado = em.merge(pagamento);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar atualizar o pedido.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}

	public void delete(Pagamento pagamento) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		try {
			pagamento = em.find(Pagamento.class, pagamento.getId());
			em.remove(pagamento);
			transaction.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar remover o pedido.", pe);
		} finally {
			em.close();
		}
	}

	public Pagamento getByID(String pagamentoID) throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		Pagamento resultado = null;
		try {
			resultado = em.find(Pagamento.class, pagamentoID);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar o pedido com base no ID.", pe);
		} finally {
			em.close();
		}

		return resultado;
	}

	public List<Pagamento> getAll() throws PersistenciaDacException {
		EntityManager em = getEntityManager();
		List<Pagamento> resultado = null;
		try {
			TypedQuery<Pagamento> query = em.createQuery("SELECT p FROM Pagamento p", Pagamento.class);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw new PersistenciaDacException("Ocorreu algum erro ao tentar recuperar todos os pedidos.", pe);
		} finally {
			em.close();
		}
		return resultado;
	}
}
