package com.springbatch.contabancaria.dominio;

import java.util.Objects;

import com.springbatch.contabancaria.enums.TipoConta;

public class ContaBancaria {
	
	private Integer id;
	private String tipo;
	private Double limite;
	private String clienteId;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = Objects.requireNonNull(id);
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipoConta(TipoConta tipoConta) {
		this.tipo = tipoConta.getNomeTipo();
		this.limite = tipoConta.getLimiteSalarial();
	}
	
	public Double getLimite() {
		return limite;
	}
	
	public String getClienteId() {
		return clienteId;
	}
	
	public void setClienteId(String cliente_id) {
		this.clienteId = cliente_id;
	}


	@Override
	public String toString() {
		return "Conta [id=" + id + 
				", tipo=" + tipo + 
				", limite=" + limite + 
				", clienteId=" + clienteId + 
				"]";
	}
	
	
}
