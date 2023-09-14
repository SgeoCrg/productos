package com.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	
	private static Connection conexion;
	
	public static Connection getConnection() {
		
		try {
			if(conexion == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/productos","root","Ark674!10");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexion;
	}
}
