package com.entity;

public class Line {
	
	protected int idfactura;
	protected int producto;
	protected String nombreProducto;
	protected int cantidad;
	protected float total;
	protected String fecha;
	
	public Line() {}
	
	public Line(int idfactura, int producto, int cantidad, float total) {
		this.idfactura = idfactura;
		this.producto = producto;
		this.cantidad = cantidad;
		this.total = total;
	}

	public int getIdfactura() {
		return idfactura;
	}

	public void setIdfactura(int idfactura) {
		this.idfactura = idfactura;
	}

	public int getProducto() {
		return producto;
	}

	public void setProducto(int producto) {
		this.producto = producto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String formatearFecha() {
		String fecha = this.getFecha();
		String[] fechaT = fecha.split("/");
		return fechaT[2] + "-" + fechaT[1] + "-" + fechaT[0];
	}

	@Override
	public String toString() {
		return cantidad + " " + nombreProducto + " ";
	}
}
