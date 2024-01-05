package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.entity.Bill;
import com.entity.Line;

public class LineDAO {
	
	private Connection conn;
	
	public LineDAO(Connection conn) {
		this.conn = conn;
	}
	
	public boolean addLine(Line l) {
		boolean f = false;
		
		try {
			String sql = "INSERT INTO detalles(id_factura, producto, cantidad, total, fecha) VALUES(?,?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, l.getIdfactura());
			ps.setInt(2, l.getProducto());
			ps.setInt(3, l.getCantidad());
			ps.setFloat(4, l.getTotal());
			ps.setString(5, l.getFecha());
			
			int i = ps.executeUpdate();
			
			if(i==1) {
				f = true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public List<Line> getLinesForBillId(int id) {
		
		List<Line> lista = new ArrayList<>();
		Line l = null;
		
		try {
			String sql = "SELECT " 
					+ "detalles.id, detalles.producto, detalles.cantidad, detalles.total, productos.nombre, detalles.fecha "
					+ "FROM detalles "
					+ "INNER JOIN productos " 
					+ "WHERE detalles.id_factura=" + id
					+ " AND detalles.producto = productos.id";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				l = new Line();
				l.setIdfactura(rs.getInt(1));
				l.setProducto(rs.getInt(2));
				l.setCantidad(rs.getInt(3));
				l.setTotal(rs.getFloat(4));
				l.setNombreProducto(rs.getString(5));
				l.setFecha(rs.getString(6));
				lista.add(l);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
