package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.User;

public class UserDAO {

	private Connection conn;

	public UserDAO(Connection conn) {
		super();
		this.conn = conn;

	}

	public User getUserByEmail(String email) {
		try {
			String sql = "SELECT * FROM users WHERE email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			User u = null;

			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				u = new User();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setRole(rs.getString(5));
				return u;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean register(User newUser) {
		try {
			System.out.println(newUser.getEmail());
			String sql = "INSERT INTO users(userName, password, email) VALUES (?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, newUser.getName());
			ps.setString(2, newUser.getPassword());
			ps.setString(3, newUser.getEmail());
			
			int i = ps.executeUpdate();
			
			if(i==1)
				return true;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;

	}

}
