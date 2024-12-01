package com.happyfest.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_Brinquedo")
public class Brinquedo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private double precoDiaria;

    @OneToMany(mappedBy = "brinquedo", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    // Construtor padrão
    public Brinquedo() {}

    // Construtor completo
    public Brinquedo(String nome, String descricao, double precoDiaria) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoDiaria = precoDiaria;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
