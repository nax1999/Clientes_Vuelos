package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import Auxiliares.Auxiliares;
import Modelo.Vuelo;

public class ConnectedFicheros implements AccesoDatos {

	String archivoVuelos = "FicheroVuelos.txt";

	@Override
	public HashMap<Integer, Vuelo> mostrar() throws SQLException {

		HashMap<Integer, Vuelo> auxiliar = new HashMap<Integer, Vuelo>();

		Vuelo miVuelo = new Vuelo();

		try {

			FileReader fr = new FileReader(archivoVuelos);
			BufferedReader b = new BufferedReader(fr);

			String cadena = b.readLine();
			while (cadena != null) {

				miVuelo = new Vuelo();

				miVuelo.setId(Integer.parseInt(cadena));
				miVuelo.setCodigoVuelo(b.readLine());
				miVuelo.setOrigen(b.readLine());
				miVuelo.setDestino(b.readLine());
				miVuelo.setFecha(Auxiliares.parseFecha(b.readLine()));
				miVuelo.setHora(b.readLine());
				miVuelo.setPlazasTotales(Integer.parseInt(b.readLine()));
				miVuelo.setPlazasDisponibles(Integer.parseInt(b.readLine()));

				auxiliar.put(miVuelo.getId(), miVuelo);
				cadena = b.readLine();

			}

			b.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return auxiliar;
	}

	@Override
	public void insertar(Vuelo vuelo) throws SQLException {

		try {

			File fich = new File(archivoVuelos);
			FileWriter fichero = new FileWriter(archivoVuelos, true);
			BufferedWriter out = new BufferedWriter(fichero);

			out.write(vuelo.getId() + "\n");
			out.write(vuelo.getCodigoVuelo() + "\n");
			out.write(vuelo.getOrigen() + "\n");
			out.write(vuelo.getDestino() + "\n");
			out.write(vuelo.getFecha() + "\n");
			out.write(vuelo.getHora() + "\n");
			out.write(vuelo.getPlazasTotales() + "\n");
			out.write(vuelo.getPlazasDisponibles() + "\n");

			out.close();

			System.out.println("SE HA INSERTADO CORRECTAMENTE");

		} catch (IOException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void modificar(Vuelo vuelo) throws SQLException {

		HashMap<Integer, Vuelo> auxiliar = new HashMap<Integer, Vuelo>();

		Vuelo miVuelo = new Vuelo();

		try {

			FileReader f = new FileReader(archivoVuelos);
			BufferedReader b = new BufferedReader(f);
			String cadena = b.readLine();

			while (cadena != null) {
				miVuelo = new Vuelo();
				miVuelo.setId(Integer.parseInt(cadena));
				miVuelo.setCodigoVuelo(b.readLine());
				miVuelo.setOrigen(b.readLine());
				miVuelo.setDestino(b.readLine());
				miVuelo.setFecha(Auxiliares.parseFecha(b.readLine()));
				miVuelo.setHora(b.readLine());
				miVuelo.setPlazasTotales(Integer.parseInt(b.readLine()));
				miVuelo.setPlazasDisponibles(Integer.parseInt(b.readLine()));

				if (miVuelo.getId() != vuelo.getId()) {
					System.out.println("true");
					System.out.println(miVuelo);

					auxiliar.put(miVuelo.getId(), miVuelo);

				} else {
					System.out.println("false");
					System.out.println(vuelo);

					auxiliar.put(vuelo.getId(), vuelo);

				}

				cadena = b.readLine();

			}

			borrarTodo();

			for (Entry<Integer, Vuelo> entry : auxiliar.entrySet()) {

				insertar(entry.getValue());

			}

			System.out.println("ARCHIVO ACTUALIZADO");
			b.close();

		} catch (

		IOException e) {

			e.printStackTrace();

		}
	}

	@Override
	public void borrar(int id) throws SQLException {
		HashMap<Integer, Vuelo> auxiliar = new HashMap<Integer, Vuelo>();

		Vuelo miVuelo = new Vuelo();

		try {

			FileReader f = new FileReader(archivoVuelos);
			BufferedReader b = new BufferedReader(f);
			String cadena = b.readLine();

			while (cadena != null) {
				miVuelo = new Vuelo();
				miVuelo.setId(Integer.parseInt(cadena));
				miVuelo.setCodigoVuelo(b.readLine());
				miVuelo.setOrigen(b.readLine());
				miVuelo.setDestino(b.readLine());
				miVuelo.setFecha(Auxiliares.parseFecha(b.readLine()));
				miVuelo.setHora(b.readLine());
				miVuelo.setPlazasTotales(Integer.parseInt(b.readLine()));
				miVuelo.setPlazasDisponibles(Integer.parseInt(b.readLine()));

				cadena = b.readLine();

				if (miVuelo.getId() != id) {

					auxiliar.put(miVuelo.getId(), miVuelo);

				}

			}

			borrarTodo();

			for (Entry<Integer, Vuelo> entry : auxiliar.entrySet()) {

				System.out.println(entry.getValue().toString());
				insertar(entry.getValue());

			}

			System.out.println("ARCHIVO ELIMINADO");
			b.close();

		} catch (

		IOException e) {

			e.printStackTrace();

		}
	}

	@Override
	public Vuelo buscar(int id) {

		Vuelo vueloBuscado = null;

		Vuelo miVuelo = new Vuelo();

		try {

			FileReader f = new FileReader(archivoVuelos);
			BufferedReader b = new BufferedReader(f);
			String cadena = b.readLine();

			while (cadena != null) {
				miVuelo = new Vuelo();
				miVuelo.setId(Integer.parseInt(cadena));
				miVuelo.setCodigoVuelo(b.readLine());
				miVuelo.setOrigen(b.readLine());
				miVuelo.setDestino(b.readLine());
				miVuelo.setFecha(Auxiliares.parseFecha(b.readLine()));
				miVuelo.setHora(b.readLine());
				miVuelo.setPlazasTotales(Integer.parseInt(b.readLine()));
				miVuelo.setPlazasDisponibles(Integer.parseInt(b.readLine()));

				cadena = b.readLine();

				if (miVuelo.getId() == id) {

					vueloBuscado = miVuelo;

					break;

				}

			}

			b.close();

		} catch (

		IOException e) {

			e.printStackTrace();

		}

		return vueloBuscado;

	}

	public void borrarTodo() {

		BufferedWriter bw;

		try {

			bw = new BufferedWriter(new FileWriter(archivoVuelos));
			bw.write("");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
