package autoPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Auto implements Comparable<Auto>{
	
	private String marca;
	private String modelo;
	private int precio;
	private int año;
	private int mes;
	
	public Auto(String marca, String modelo, int precio, int año, int mes) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
		this.año = año;
		this.mes = mes;
	}

	public Auto() {
		// TODO Auto-generated constructor stub
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public int getPrecio() {
		return precio;
	}

	public int getAño() {
		return año;
	}

	public int getMes() {
		return mes;
	}

	@Override
	public int compareTo(Auto auto) {
		List<String> lista = new ArrayList<String>();
		if (auto.getMarca().equals(this.getMarca())) {
			if (auto.getModelo().equals(this.getModelo())) {
				return auto.getAño() - this.getAño();
			}
			lista.add(this.getModelo());
			lista.add(auto.getModelo());
			Collections.sort(lista);
			if (lista.get(0).equals(auto.getModelo()))
				return 1;
			return -1;
		}
		lista.add(this.getMarca());
		lista.add(auto.getMarca());
		Collections.sort(lista);
		if (lista.get(0).equals(auto.getMarca()))
			return 1;
		return -1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + año;
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Auto other = (Auto) obj;
		if (this.getModelo().equals(other.getModelo()) && this.getMarca().equals(other.getMarca()) && this.getAño() == other.getAño()) {
			return true;
		}
		return false;
	}
}
