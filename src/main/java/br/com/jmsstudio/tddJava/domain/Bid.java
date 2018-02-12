package br.com.jmsstudio.tddJava.domain;

public class Bid {

	private User user;
	private double value;
	
	public Bid(User user, double value) {
		this.user = user;
		this.value = value;
	}

	public User getUser() {
		return user;
	}

	public double getValue() {
		return value;
	}
	
	public Bid doubleValue() {
		return new Bid(this.user, this.value * 2);
	}
	
}
