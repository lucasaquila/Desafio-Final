package br.com.projeto.entity;

public class Transferencia {
	
	private Lancamento saida;
	private Lancamento entrada;
	
	public Lancamento getSaida() {
		return saida;
	}
	public void setSaida(Lancamento saida) {
		this.saida = saida;
	}
	public Lancamento getEntrada() {
		return entrada;
	}
	public void setEntrada(Lancamento entrada) {
		this.entrada = entrada;
	}
	
}
