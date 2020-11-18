package Vista;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import Modelo.Vuelo;

public class Vista {

	public int menuAccesoDatos() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Elija una opcion de las siguientes que se muestran pon pantalla:");
		System.out.println("1) Base de datos");
		System.out.println("2) Fichero");
		System.out.println("3) Hibernate");
		System.out.println("4) MongoDB");
		int opcionEscoger = sc.nextInt();
		return opcionEscoger;
	}

	public int menuEditarDatos() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Elija una opcion de las siguientes que se muestran pon pantalla:");
		System.out.println("1) Ver datos");
		System.out.println("2) Editar");
		System.out.println("3) Añadir");
		System.out.println("4) Borra");
		System.out.println("5) Buscar");
		System.out.println("6) Transferir");
		int opcionEscoger = sc.nextInt();
		return opcionEscoger;
	}

	public void mostrarDatos(HashMap<Integer, Vuelo> miVuelito) {

		for (Entry<Integer, Vuelo> entry : miVuelito.entrySet()) {
			Integer key = entry.getKey();
			Vuelo value = entry.getValue();
			System.out.println(entry.getValue().toString());

		}
	}

	public int pedirId(HashMap<Integer, Vuelo> misVuelos) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Elija una opcion de las siguientes que se muestran pon pantalla:");
		for (Entry<Integer, Vuelo> entry : misVuelos.entrySet()) {
			Integer key = entry.getKey();
			Vuelo value = entry.getValue();
			System.out.println(entry.getValue().getId() + " - " + entry.getValue().toString());

		}
		int opcionEscoger = sc.nextInt();
		return opcionEscoger;

	}

	public int menuOpcionesVuelo(Vuelo vuelo) {

		Scanner sc = new Scanner(System.in);
		System.out.println("¿Qué quieres cambiar?");
		for (int i = 0; i < vuelo.getTitulo().length; i++) {
			System.out.print("[ " + i + " - " + vuelo.getTitulo()[i] + " ]");

		}

		int opcionEscoger = sc.nextInt();
		return opcionEscoger;

	}

	public String cambiarValor() {
		Scanner sc = new Scanner(System.in);
		System.out.println("¿Por qué lo quieres cambiar?");
		String nuevoValor = sc.nextLine();
		return nuevoValor;

	}

	public String nuevoValor(String nombre) {
		Scanner sc = new Scanner(System.in);
		System.out.println("AÑADE: " + nombre);
		String nuevoValor = sc.nextLine();
		return nuevoValor;

	}

	public void verBusqueda(Vuelo miVuelo) {
		System.out.println(miVuelo);

	}
}