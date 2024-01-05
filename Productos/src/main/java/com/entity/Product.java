package com.entity;

public class Product {
	
	protected int id;
	protected String nombre;
	protected float precio;
	protected int unidades;
	protected int supermercado;
	protected int version;
	
	protected int numeroUsado;
	
	public Product() {}
	
	public Product(String nombre, float precio, int unidades, int supermercado) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.unidades = unidades;
		this.supermercado = supermercado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public int getSupermercado() {
		return supermercado;
	}

	public void setSupermercado(int supermercado) {
		this.supermercado = supermercado;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public int getNumeroUsado() {
		return numeroUsado;
	}
	
	public void setNumeroUsado(int numeroUsado) {
		this.numeroUsado = numeroUsado;
	}

	public String toString() {
		return nombre + ", " + precio + "€";
	}

}
