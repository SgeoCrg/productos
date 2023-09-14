package com.dao;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Product;

public class ProductDAO {
	
	private Connection conn;
	
	public ProductDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public boolean addProduct(Product p) {
		boolean f = false;
		
		if(p.getVersion() == 0) {
			return f;
		}
		
		try {
			String sql = "INSERT INTO productos(nombre, precio, unidades, supermercado, version) VALUES(?,?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, p.getNombre());
			ps.setFloat(2, (float)p.getPrecio());
			ps.setInt(3, p.getUnidades());
			ps.setInt(4, p.getSupermercado());
			ps.setInt(5, p.getVersion());
			
			int i = ps.executeUpdate();
			
			if(i==1) {
				f = true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	public List<Product> getProducts() {
		
		List<Product> list = new ArrayList<Product>();
		Product p = null;
		try {
			
			String sql = "SELECT * FROM productos ";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				p = new Product();
				p.setId(Integer.parseInt(rs.getString(1)));
				p.setNombre(rs.getString(2));
				p.setPrecio(Float.parseFloat(rs.getString(3)));
				p.setUnidades(Integer.parseInt(rs.getString(4)));
				p.setSupermercado(Integer.parseInt(rs.getString(5)));
				p.setVersion(Integer.parseInt(rs.getString(6)));
				list.add(p);
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public List<Product> getProductsBySupermarket(int id) {
		List<Product> list = new ArrayList<Product>();
		Product p = null;
		
		try {
			
			String sql = "SELECT * FROM productos WHERE supermercado = " + id;
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				p = new Product();
				p.setId(Integer.parseInt(rs.getString(1)));
				p.setNombre(rs.getString(2));
				p.setPrecio(Float.parseFloat(rs.getString(3)));
				p.setUnidades(Integer.parseInt(rs.getString(4)));
				p.setSupermercado(Integer.parseInt(rs.getString(5)));
				p.setVersion(Integer.parseInt(rs.getString(6)));
				list.add(p);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public Product getProductById(int id) {
		Product p = null;
		
		try {
			
			String sql = "SELECT * FROM productos WHERE id = " + id;
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				p = new Product();
				p.setId(Integer.parseInt(rs.getString(1)));
				p.setNombre(rs.getString(2));
				p.setPrecio(Float.parseFloat(rs.getString(3)));
				p.setUnidades(Integer.parseInt(rs.getString(4)));
				p.setSupermercado(Integer.parseInt(rs.getString(5)));
				p.setVersion(Integer.parseInt(rs.getString(6)));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public boolean editProduct(Product p) {
		boolean f = false;
		
		try {
			String sql = "UPDATE productos SET nombre =?, precio =?, unidades =? WHERE id =? ";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getNombre());
			ps.setFloat(2, p.getPrecio());
			ps.setInt(3, p.getUnidades());
			ps.setInt(4, p.getId());
			
			int i = ps.executeUpdate();
			
			if(i==1) {
				f = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	public boolean deleteProduct(int id) {
		boolean f = false;
		
		try {
			String sql = "DELETE FROM productos WHERE id=? ";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			int i = ps.executeUpdate();
			
			if(i == 1) {
				f = true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}

	public int getIdByName(String nombre, float precio, int supermercado) {
		try {
			String sql = "SELECT id FROM productos WHERE nombre=? and precio=? and supermercado =?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,nombre);
			ps.setFloat(2, precio);
			ps.setInt(3, supermercado);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getUltimaId() {
		try {
			String sql = "SELECT MAX(id) AS id FROM productos";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next() ) {
				return rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
