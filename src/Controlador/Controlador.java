package Controlador;

import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import Auxiliares.Auxiliares;
import Modelo.Vuelo;
import Util.AccesoDatos;
import Util.ConnectedFicheros;
import Util.ConnectedHibernate;
import Util.ConnectedMongoDB;
import Util.ConnectedSQL;
import Vista.Vista;

public class Controlador {

	Vista miVista;
	AccesoDatos miAcceso;

	public Controlador(Vista miVista) {
		this.miVista = miVista;

	}

	public void init() {

		// Primer menú elije dónde quieres buscar los datos

		miAcceso = selectorAcceso();

		// Segundo menú dónde se interacciona los datos

		int opcion = miVista.menuEditarDatos();
		switch (opcion) {
		case 1: {
			mostrar();
			break;
		}

		case 2: {
			editar();
			break;
		}

		case 3: {
			incluir();
			break;

		}
		case 4: {
			eliminar();
			break;

		}
		case 5: {
			busqueda();
			break;
		}

		case 6: {
			traspasoInformacion();
			break;
		}

		}

	}

	public void mostrar() {

		try {

			HashMap<Integer, Vuelo> misVuelos = miAcceso.mostrar();

			miVista.mostrarDatos(misVuelos);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void editar() {

		try {

			HashMap<Integer, Vuelo> misVuelos = miAcceso.mostrar();
			int id = miVista.pedirId(misVuelos);
			int atributoCambiando = miVista.menuOpcionesVuelo(misVuelos.get(id));
			String nuevoValor = miVista.cambiarValor();

			switch (atributoCambiando) {
			case 0: {
				misVuelos.get(id).setCodigoVuelo(nuevoValor);
				break;
			}
			case 1: {
				misVuelos.get(id).setOrigen(nuevoValor);
				break;
			}

			case 2: {
				misVuelos.get(id).setDestino(nuevoValor);
				break;
			}
			case 3: {
				Date nuevoDate = Auxiliares.parseFecha(nuevoValor);
				misVuelos.get(id).setFecha(nuevoDate);
				break;
			}
			case 4: {
				misVuelos.get(id).setHora(nuevoValor);
				break;
			}
			case 5: {
				int nuevoNumero = Integer.parseInt(nuevoValor);
				misVuelos.get(id).setPlazasTotales(nuevoNumero);
				break;
			}
			case 6: {
				int nuevoNumero = Integer.parseInt(nuevoValor);
				misVuelos.get(id).setPlazasDisponibles(nuevoNumero);
				break;

			}

			}

			miAcceso.modificar(misVuelos.get(id));
		} catch (SQLException e) {

			System.out.println("ERROR EN CONTROLADOR");
			e.printStackTrace();
		}

	}

	public void incluir() {

		Vuelo miVuelo = new Vuelo();
		miVuelo.setCodigoVuelo(miVista.nuevoValor("Código Vuelo"));
		miVuelo.setOrigen(miVista.nuevoValor("Origen"));
		miVuelo.setDestino(miVista.nuevoValor("Destino"));
		miVuelo.setFecha(Auxiliares.parseFecha(miVista.nuevoValor("Fecha")));
		miVuelo.setHora(miVista.nuevoValor("Hora"));
		miVuelo.setPlazasTotales(Integer.parseInt(miVista.nuevoValor("Plazas Totales")));
		miVuelo.setPlazasDisponibles(Integer.parseInt(miVista.nuevoValor("Plazas Disponibles")));

		try {
			miAcceso.insertar(miVuelo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void eliminar() {

		try {

			HashMap<Integer, Vuelo> misVuelos = miAcceso.mostrar();
			int id = miVista.pedirId(misVuelos);
			miAcceso.borrar(id);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void busqueda() {

		try {

			HashMap<Integer, Vuelo> misVuelos = miAcceso.mostrar();
			int id = miVista.pedirId(misVuelos);
			miAcceso.buscar(id);
			Vuelo miVuelo = miAcceso.buscar(id);
			miVista.verBusqueda(miVuelo);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void traspasoInformacion() {

		AccesoDatos[] miAccesoInsertar = selectorRestoAccesos();
		try {
			HashMap<Integer, Vuelo> misVuelos = miAcceso.mostrar();

			for (Entry<Integer, Vuelo> entry : misVuelos.entrySet()) {

				for (int i = 0; i < miAccesoInsertar.length; i++) {

					miAccesoInsertar[i].insertar(entry.getValue());

				}

			}

			System.out.println("TRASPASO COMPLETADO");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public AccesoDatos[] selectorRestoAccesos() {

		if (miAcceso instanceof ConnectedSQL) {

			AccesoDatos ficheros = new ConnectedFicheros();
			AccesoDatos hibernate = new ConnectedHibernate();
			AccesoDatos mongoDB = new ConnectedMongoDB();
			return new AccesoDatos[] { ficheros, hibernate, mongoDB };

		} else if (miAcceso instanceof ConnectedFicheros) {

			AccesoDatos SQL = new ConnectedSQL();
			AccesoDatos hibernate = new ConnectedHibernate();
			AccesoDatos mongoDB = new ConnectedMongoDB();
			return new AccesoDatos[] { SQL, hibernate, mongoDB };

		} else if (miAcceso instanceof ConnectedHibernate) {

			AccesoDatos SQL = new ConnectedSQL();
			AccesoDatos ficheros = new ConnectedFicheros();
			AccesoDatos mongoDB = new ConnectedMongoDB();
			return new AccesoDatos[] { SQL, ficheros, mongoDB };

		} else if (miAcceso instanceof ConnectedMongoDB) {

			AccesoDatos SQL = new ConnectedSQL();
			AccesoDatos ficheros = new ConnectedFicheros();
			AccesoDatos hibernate = new ConnectedHibernate();
			return new AccesoDatos[] { SQL, ficheros, hibernate };
		} else {

			return null;

		}

	}

	public AccesoDatos selectorAcceso() {

		int opcion = miVista.menuAccesoDatos();
		switch (opcion) {
		case 1:
			return new ConnectedSQL();

		case 2:
			return new ConnectedFicheros();

		case 3:
			return new ConnectedHibernate();

		case 4:
			return new ConnectedMongoDB();
		default:
			return null;
		}

	}
}
