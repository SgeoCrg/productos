package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.entity.Bill;
import com.entity.User;

public class BillDAO {

	private Connection conn;
	
	public BillDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public boolean addBill(Bill b, int idUsuario) {
		boolean f = false;
		
		try {
			String sql = "INSERT INTO facturas(supermercado, fecha, total, id_usuario) VALUES(?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, b.getSupermercado());
			ps.setString(2, b.getFecha());
			ps.setFloat(3, b.getTotal());
			ps.setInt(4, idUsuario);
			
			int i = ps.executeUpdate();
			
			if(i==1) {
				f = true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public int getSiguienteId() {
		try {
			String sql = "SELECT MAX(id) AS id FROM facturas";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next() ) {
				return rs.getInt(1) + 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<Bill> getAllBills(int userId) {
		
		List<Bill> bills = new ArrayList<Bill>();
		Bill newBill = null;
		
		try {
			String sql = "SELECT s.nombre, f.fecha, f.total, f.id"
					+ " FROM facturas f, supermercados s"
					+ " WHERE f.supermercado = s.id";
			if(userId != 1) {//0
				sql += " and f.id_usuario=" + userId;
			}
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				newBill = new Bill();
				newBill.setNombreSupermercado(rs.getString(1));
				newBill.setFecha(rs.getString(2));
				newBill.setTotal(rs.getFloat(3));
				newBill.setId(rs.getInt(4));
				bills.add(newBill);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return bills;
	}
	
	public List<Bill> getBillsBySupermarket(int id, int userId) {
		List<Bill> bills = new ArrayList<Bill>();
		Bill newBill = null;
		
		try {
			String sql =  "SELECT s.nombre, f.fecha, f.total, f.id"
					+ " FROM facturas f, supermercados s"
					+ " WHERE f.supermercado = s.id"
					+ " AND f.supermercado = " + id;
			if(userId != 0) {
				sql += " and f.id_usuario=" + userId;
			}
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				newBill = new Bill();
				newBill.setNombreSupermercado(rs.getString(1));
				newBill.setFecha(rs.getString(2));
				newBill.setTotal(rs.getFloat(3));
				newBill.setId(rs.getInt(4));
				bills.add(newBill);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return bills;
	}

	public boolean updateBill(int facturaId, float total) {
		boolean f = false;
		try {
			String sql = "UPDATE facturas set total=? where id=?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, total);
			ps.setInt(2, facturaId);
			
			int i = ps.executeUpdate();
			
			if(i==1) {
				f = true;
			}	
	} catch(Exception e) {
		e.printStackTrace();
	}
		return f;
	
	}
}
