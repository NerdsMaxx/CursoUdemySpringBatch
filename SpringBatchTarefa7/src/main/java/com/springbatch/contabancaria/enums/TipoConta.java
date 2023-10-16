package com.springbatch.contabancaria.enums;

import java.util.Arrays;
import java.util.Optional;

public enum TipoConta {
	
	PRATA("PRATA", 500.00, 3000.00),
	OURO("OURO", 1000.00, 5000.00),
	PLATINA("PLATINA", 2500.00, 10_000.00),
	DIAMANTE("DIAMANTE", 5000.00, null);
	
	private String nomeTipo;
	private double limiteSalarial;
	private Optional<Double> limiteSalarioAceito;
	
	private TipoConta(String nomeTipo, double limiteSalarial, Double limiteSalarioAceito) {
		this.nomeTipo = nomeTipo;
		this.limiteSalarial = limiteSalarial;
		this.limiteSalarioAceito = Optional.ofNullable(limiteSalarioAceito);
	}
	
	public String getNomeTipo() {
		return nomeTipo;
	}

	public double getLimiteSalarial() {
		return limiteSalarial;
	}
	
	private boolean eAceito(double salario) {
		if(! limiteSalarioAceito.isPresent()) {
			return salario > PLATINA.limiteSalarioAceito.get();
		}
		
		return salario <= limiteSalarioAceito.get();
	}

	public static TipoConta getTipoConta(double salario) {
		if(salario < 0.00) {
			throw new IllegalArgumentException("Salário não pode ser menor R$ 0,00!");
		}
		
		return Arrays.asList(values())
				.stream()
				.filter(tipoConta -> tipoConta.eAceito(salario))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("Deveria ter tipoConta presente!"));
	}
}
