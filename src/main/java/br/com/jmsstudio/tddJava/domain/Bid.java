package br.com.jmsstudio.tddJava.domain;

public class Bid {

	private User user;
	private double valor;
	
	public Bid(User user, double valor) {
		this.user = user;
		this.valor = valor;
	}

	public User getUser() {
		return user;
	}

	public double getValor() {
		return valor;
	}
	
	
	
}
