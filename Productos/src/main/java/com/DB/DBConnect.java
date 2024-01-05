package com.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	
	private static Connection conexion;
	
	public static Connection getConnection() {
		
		try {
			if(conexion == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String unicode="useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8";
				conexion=DriverManager.getConnection("jdbc:mysql://uxhqrjmnsyx3iv8i:9Y3O2sf6eJ29acba7UW7@bgishobzhdikukact3r3-mysql.services.clever-cloud.com:3306/bgishobzhdikukact3r3?"+unicode,
						"uxhqrjmnsyx3iv8i",
						"9Y3O2sf6eJ29acba7UW7");
				//conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/productos","root","Ark674!10");//jdbc:mysql://localhost:3306/productos
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexion;
	}
}
