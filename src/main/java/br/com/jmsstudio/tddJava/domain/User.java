package br.com.jmsstudio.tddJava.domain;

public class User {

	private int id;
	private String name;
	
	public User(String name) {
		this(0, name);
	}

	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	
	
}
