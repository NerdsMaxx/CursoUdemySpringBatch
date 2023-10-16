package com.springbatch.contabancaria.dominio;

import java.util.Objects;

public class Cliente {
	
	private String nome;
	private Integer idade;
	private String email;
	private Double faixaSalarial;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getIdade() {
		return idade;
	}
	
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = Objects.requireNonNull(email);
	}
	
	public Double getFaixaSalarial() {
		return faixaSalarial;
	}
	
	public void setFaixaSalarial(Double faixaSalarial) {
		this.faixaSalarial = faixaSalarial;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + 
				", idade=" + idade + 
				", email=" + email + 
				", faixaSalarial=" + faixaSalarial
				+ "]";
	}
	
	
}
