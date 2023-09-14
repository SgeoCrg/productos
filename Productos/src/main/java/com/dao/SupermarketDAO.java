package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Supermarket;

public class SupermarketDAO {
	
	private Connection conn;

	public SupermarketDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public boolean addSuperMarket(Supermarket s) {
		boolean f = false;
		
		if(s.getVersion() == 0) {
			return f;
		}
		
		try {
			String sql = "INSERT INTO supermercados(nombre, direccion, version) VALUES(?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s.getNombre());
			ps.setString(2, s.getDireccion());
			ps.setInt(3, s.getVersion());
			
			int i = ps.executeUpdate();
			
			if(i==1) {
				f = true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public Supermarket getSuperMarketById(int id) {
		
		Supermarket s = null;
		
		try {
			String sql = "SELECT * FROM supermercados WHERE id = " + id;
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				s= new Supermarket();
				s.setId(rs.getInt(1));
				s.setNombre(rs.getString(2));
				s.setDireccion(rs.getString(3));
				s.setVersion(rs.getInt(4));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return s;
		
	}
	
	public List<Supermarket> getSupermarkets() {
		
		List<Supermarket> list = new ArrayList<Supermarket>();
		Supermarket s = null;
		
		
		try {
			String sql = "SELECT * FROM supermercados ";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				s = new Supermarket();
				s.setId(rs.getInt(1));
				s.setNombre(rs.getString(2));
				s.setDireccion(rs.getString(3));
				s.setVersion(rs.getInt(4));
				list.add(s);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public boolean editSupermarket(Supermarket s) {
		boolean f = false;
		
		try {
			String sql = "UPDATE supermercados SET nombre=?, direccion=? WHERE id= ? ";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s.getNombre());
			ps.setString(2, s.getDireccion());
			ps.setInt(3, s.getId());
			
			int i = ps.executeUpdate();
			
			if(i == 1) {
				f = true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
		
	}

	public boolean deleteSupermarket(int id) {
		boolean f = false;

		try {
			String sql = "DELETE FROM supermercados WHERE id = " + id;
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, id);
			
			
			int i =ps.executeUpdate();
			
			if(i== 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
}
