package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.entity.Bill;

public class BillDAO {

	private Connection conn;
	
	public BillDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public boolean addBill(Bill b) {
		boolean f = false;
		
		try {
			String sql = "INSERT INTO facturas(supermercado, fecha, total) VALUES(?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, b.getSupermercado());
			ps.setString(2, b.getFecha());
			ps.setFloat(3, b.getTotal());
			
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
	
	public List<Bill> getAllBills() {
		
		List<Bill> bills = new ArrayList<Bill>();
		Bill newBill = null;
		
		try {
			String sql = "SELECT s.nombre, f.fecha, f.total, f.id"
					+ " FROM facturas f, supermercados s"
					+ " WHERE f.supermercado = s.id";
			
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
	
	public List<Bill> getBillsBySupermarket(int id) {
		List<Bill> bills = new ArrayList<Bill>();
		Bill newBill = null;
		
		try {
			String sql =  "SELECT s.nombre, f.fecha, f.total, f.id"
					+ " FROM facturas f, supermercados s"
					+ " WHERE f.supermercado = s.id"
					+ " AND f.supermercado = " + id;
			
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
}
