package com.etc.domain;

/**
 * Gate information
 * 
 * @author kaushik
 */
public class Gate {
	
	private int id;
	private String name;

	public Gate(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
