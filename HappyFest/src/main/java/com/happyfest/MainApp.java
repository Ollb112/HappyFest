package com.happyfest;

import com.happyfest.dao.ClienteDAO;
import com.happyfest.dao.BrinquedoDAO;
import com.happyfest.dao.ReservaDAO;
import com.happyfest.dao.PagamentoDAO;
import com.happyfest.dao.PersistenciaDacException;
import com.happyfest.model.Cliente;
import com.happyfest.model.Brinquedo;
import com.happyfest.model.Reserva;
import com.happyfest.model.Pagamento;

import java.util.Date;

public class MainApp {

	public static void main(String[] args) throws PersistenciaDacException {

		// DAOs
		ClienteDAO clienteDAO = new ClienteDAO();
		BrinquedoDAO brinquedoDAO = new BrinquedoDAO();
		ReservaDAO reservaDAO = new ReservaDAO();
		PagamentoDAO pagamentoDAO = new PagamentoDAO();

		// Criar e salvar um Cliente
		Cliente cliente = new Cliente("João Silva", "123.456.789-00", "(83) 99999-9999");
		clienteDAO.save(cliente);

		// Criar e salvar um Brinquedo
		Brinquedo brinquedo = new Brinquedo("Tobogã Inflável", "Um tobogã divertido para crianças.", 150.0);
		brinquedoDAO.save(brinquedo);

		// Criar e salvar uma Reserva
		Reserva reserva = new Reserva();
		reserva.setCliente(cliente);
		reserva.setBrinquedo(brinquedo);
		reserva.setDataReserva(new Date());
		reserva.setDataEntrega(new Date(System.currentTimeMillis() + 86400000L)); // Entrega para o dia seguinte
		reserva.setValorTotal(150.0);
		reservaDAO.save(reserva);

		// Criar e salvar um Pagamento
		Pagamento pagamento = new Pagamento();
		pagamento.setReserva(reserva);
		pagamento.setMetodoPagamento("Cartão de Crédito");
		pagamento.setValor(150.0);
		pagamentoDAO.save(pagamento);

		// Associar o pagamento à reserva e atualizar a reserva
		reserva.setPagamento(pagamento);
		reservaDAO.update(reserva);

		// Imprimir todos os dados salvos para verificação
		System.out.println("Clientes:");
		clienteDAO.getAll().forEach(System.out::println);

		System.out.println("\nBrinquedos:");
		brinquedoDAO.getAll().forEach(System.out::println);

		System.out.println("\nReservas:");
		reservaDAO.getAll().forEach(System.out::println);

		System.out.println("\nPagamentos:");
		pagamentoDAO.getAll().forEach(System.out::println);


	}
}
