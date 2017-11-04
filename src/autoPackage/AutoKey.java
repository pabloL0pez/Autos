package autoPackage;

public class AutoKey {
	
	private String marca;
	private String modelo;
	private int año;
	
	public AutoKey(String marca, String modelo, int año) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.año = año;
	}

	public AutoKey() {
		
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public int getAño() {
		return año;
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
		AutoKey other = (AutoKey) obj;
		if (this.getModelo().equals(other.getModelo()) && this.getMarca().equals(other.getMarca()) && this.getAño() == other.getAño()) {
			return true;
		}
		return false;
	}
}
