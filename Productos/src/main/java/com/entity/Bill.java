package com.entity;

import java.util.Date;

public class Bill {
	
	protected int id;
	
	protected int supermercado;
	
	protected String nombreSupermercado;
	
	protected float total;
	
	protected String fecha;

	public Bill() {
		
	}
	
	public Bill(int supermercado, String fecha) {
		this.supermercado = supermercado;
		this.fecha = fecha;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSupermercado() {
		return supermercado;
	}

	public void setSupermercado(int supermercado) {
		this.supermercado = supermercado;
	}

	public String getNombreSupermercado() {
		return nombreSupermercado;
	}

	public void setNombreSupermercado(String nombreSupermercado) {
		this.nombreSupermercado = nombreSupermercado;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
	
	public String toString() {
		
		return supermercado + ": " + fecha;
	}

}
