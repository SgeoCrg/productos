package com.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Product;
import com.entity.Supermarket;

public class Utils {
	
	public static String pasarAFloat(String numero) {
		return numero.replace(',', '.');
	}
	
	public static List<Product> soloUltimaVersion(List<Product> list) {
		
		int size = list.size();
				for(int i = 0; i < size; i++) {
			for(int j = i + 1; j < size; j++) {
				String a = list.get(i).getNombre().toLowerCase();
				String b = list.get(j).getNombre().toLowerCase();
				if(list.get(i).getNombre().toLowerCase().equals(list.get(j).getNombre().toLowerCase()) &&
						list.get(i).getVersion() < list.get(j).getVersion()) {
					list.remove(i);
					size = list.size();
					if (i > 0) i--;
					else i = 0;
					j = size;
				}
			}
		}
		return list;
	}
	
	/*public static void productoAEditar() {
		System.out.println("holi");
	}*/
	
	public static int comprobarVersion(Product p, List<Product> list) {
		
		int version = 1;
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getNombre().toLowerCase().equals(p.getNombre().toLowerCase()) &&
					list.get(i).getUnidades() == p.getUnidades() &&
					list.get(i).getSupermercado() == p.getSupermercado() &&
					list.get(i).getPrecio() == p.getPrecio()) {
				return 0;
			} else if(list.get(i).getNombre().toLowerCase().equals(p.getNombre().toLowerCase()) &&
					list.get(i).getUnidades() == p.getUnidades() &&
					list.get(i).getSupermercado() == p.getSupermercado() &&
					list.get(i).getPrecio() != p.getPrecio()){
				version = list.get(i).getVersion() + 1;
			}
		}
		
		return version;
	}
	
	public static int comprobarVersion(Supermarket s, List<Supermarket> list) {
		int version = 1;
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getNombre().toLowerCase().equals(s.getNombre().toLowerCase()) &&
					list.get(i).getDireccion().toLowerCase().equals(s.getDireccion().toLowerCase())) {
				return 0;
			}
		}
		
		return version;
		
	}

}
