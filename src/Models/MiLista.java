package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@SuppressWarnings({ "serial", "unused" })
public class MiLista<T> extends ArrayList<T> {
	// ATRIBUTOS
	private Comparator<T> comp;

	// CONSTRUCTORES
	public MiLista(Comparator<T> comp) {
		super();
		this.comp = comp;
	}

	// Sobreescribimos los metodos necesarios para nuestro comportamiento
	/**
	 * Añadimos un elemento y ordenamos despues.
	 */
	@Override
	public boolean add(T element) {
		if (element != null) {
			boolean estado = (super.add(element));
			super.sort(this.comp);
			return estado;
		} else {
			System.out.println("ERROR: Es un null.");
		}
		return false;
	}

	/**
	 * Eliminamos un elemento y después lo ordenamos
	 */
	public boolean remove(Object o) {
		if (this.contains(o)) {
			boolean eliminado = (super.remove(o));
			super.sort(this.comp);
			return eliminado;
		} else {
			System.out.println("El elemento no existe en la lista.");
		}
		return false;
	}

}
