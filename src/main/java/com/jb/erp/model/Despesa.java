package com.jb.erp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jb.erp.repository.TipoPagamentos;

@Entity
@Table(name = "despesas")
public class Despesa implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private Double valor;

	@Column(name = "data_gasto")
	private Date dataCusto;
	
	private int quantidade;
	
	@Column(name = "forma_pagamento")
	@Enumerated(EnumType.STRING)
	private TipoPagamentos tipoPagamentos;
	
	private String descricao;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "mes_gasto")
	private String mesGasto;
	
	private boolean deletado;

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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDataCusto() {
		return dataCusto;
	}

	public void setDataCusto(Date data) {
		this.dataCusto = data;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isDeletado() {
		return deletado;
	}

	public void setDeletado(boolean deletado) {
		this.deletado = deletado;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public TipoPagamentos getTipoPagamentos() {
		return tipoPagamentos;
	}

	public void setTipoPagamentos(TipoPagamentos tipoPagamentos) {
		this.tipoPagamentos = tipoPagamentos;
	}

	public String getMesGasto() {
		return mesGasto;
	}

	public void setMesGasto(String mesGasto) {
		this.mesGasto = mesGasto;
	}
	
}
