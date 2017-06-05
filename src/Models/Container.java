package Models;

import java.util.ArrayList;

public class Container {

		static MiLista<Coche> coches = new MiLista<Coche>(
				(Coche c1, Coche c2) -> c1.get_horaLlegada() - c2.get_horaLlegada());

		public static ArrayList<Coche> getListaCoches() {
			return coches;
		}
}
