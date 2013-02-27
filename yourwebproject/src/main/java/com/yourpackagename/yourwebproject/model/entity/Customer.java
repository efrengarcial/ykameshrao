package com.yourpackagename.yourwebproject.model.entity;

import java.io.Serializable;

import com.yourpackagename.framework.data.JpaEntity;

public class Customer extends JpaEntity<Long> implements Serializable {
	
	public Customer(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	private static final long serialVersionUID = 4265236234678452294L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
