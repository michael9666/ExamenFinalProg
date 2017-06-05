package Models;

public class Coche {

	// ATRIBUTOS
	private String _matricula;
	private int _horaLlegada;
	private int _minutosLlegada;
	private float _precio;

	// CONSTRUCOTORES

	public Coche(String matricula, int horaLlegada, int minutosLlegada, float precio) {
		this._matricula = matricula;
		this._horaLlegada = horaLlegada;
		this._minutosLlegada = minutosLlegada;
		this._precio = precio;
	}

	// PROPIEDADES

	public String get_matricula() {
		return _matricula;
	}

	public int get_horaLlegada() {
		return _horaLlegada;
	}

	public int get_minutosLlegada() {
		return _minutosLlegada;
	}

	public float get_precio() {
		return _precio;
	}

	public void set_precio(float Precio) {
		this._precio = Precio;
	}
	
	

}
