package com.entity;

public class Supermarket {
	
	private int id;
	
	private String nombre;
	
	private String direccion;
	
	private int version;
	
	public Supermarket() { }

	public Supermarket(int id, String name) {
		super();
		this.nombre = name;
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

	public void setNombre(String name) {
		this.nombre = name;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String toString() {
		return id + ":" + nombre;
	}

}
