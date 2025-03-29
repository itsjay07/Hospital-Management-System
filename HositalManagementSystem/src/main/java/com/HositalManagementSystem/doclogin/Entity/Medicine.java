package com.HositalManagementSystem.doclogin.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="medicines")
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="drug_name")
	private String drugName;
	
	@Column(name="stock")
	private String stock;

	public Medicine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Medicine(long id, String drugName, String stock) {
		super();
		this.id = id;
		this.drugName = drugName;
		this.stock = stock;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}
	
	
}
