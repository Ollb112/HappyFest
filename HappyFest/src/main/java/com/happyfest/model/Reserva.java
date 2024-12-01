package com.happyfest.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TB_Reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fk_brinquedo_id", nullable = false)
    private Brinquedo brinquedo;

    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL)
    private Pagamento pagamento;

    private Date dataReserva;
    private Date dataEntrega;
    private double valorTotal;

    // Construtor padrão
    public Reserva() {}

    // Construtor completo
    public Reserva(Cliente cliente, Brinquedo brinquedo, Date dataReserva, Date dataEntrega, double valorTotal) {
        this.cliente = cliente;
        this.brinquedo = brinquedo;
        this.dataReserva = dataReserva;
        this.dataEntrega = dataEntrega;
        this.valorTotal = valorTotal;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Brinquedo getBrinquedo() {
        return brinquedo;
    }

    public void setBrinquedo(Brinquedo brinquedo) {
        this.brinquedo = brinquedo;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
