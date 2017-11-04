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

	public AutoKey getKey() {
		return new AutoKey(this.getMarca(), this.getModelo(), this.getAño());
	}
}
