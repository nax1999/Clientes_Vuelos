package Controlador;

import Vista.Vista;

public class Main {
	public static void main(String[] args) {
		Vista miVista = new Vista();
		Controlador miControlador = new Controlador(miVista);

		miControlador.init();

	}

}
