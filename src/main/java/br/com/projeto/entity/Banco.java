package br.com.projeto.entity;

import javax.xml.bind.annotation.XmlRootElement;

public enum Banco {
	CAIXA("Caixa"),
	HSBC("HSBC"),
	ITAU("Itaú"),
	SANTANDER("Santander");

	Banco(String banco){
		this.banco = banco;
	}

	private String banco;

	public String getBanco() {
		return banco;
	}
}
