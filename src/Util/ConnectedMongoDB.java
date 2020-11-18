package Util;

import java.sql.Date;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import Auxiliares.Auxiliares;
import Modelo.Vuelo;

public class ConnectedMongoDB implements AccesoDatos {

	MongoClient mongo;
	MongoDatabase db;
	MongoCollection collectionVuelos;

	public ConnectedMongoDB() {

		System.out.println("Conexion MongoDB");
		mongo = new MongoClient("localhost", 27017);
		db = mongo.getDatabase("adat_vuelos");
		collectionVuelos = db.getCollection("vuelos");
	}

	public HashMap<Integer, Vuelo> mostrar() throws SQLException {
		HashMap<Integer, Vuelo> misVuelos = new HashMap<Integer, Vuelo>();
		System.out.println("Listar los registros de la tabla: ");
		FindIterable fi = collectionVuelos.find();
		MongoCursor cur = fi.cursor();

		while (cur.hasNext()) {
			Document doc = (Document) cur.next();
			int id = Auxiliares.castearInt(doc, "id");
			String codigoVuelo = doc.getString("codigo");
			String origen = doc.getString("origen");
			String destino = (doc.getString("destino"));
			Date fecha = Auxiliares.parseFecha(doc.getString("fecha"));
			String hora = doc.getString("hora");
			int plazasTotales = Auxiliares.castearInt(doc, "plazas_totales");
			int plazasDisponibles = Auxiliares.castearInt(doc, "plazas_disponibles");

			Vuelo miVuelo = new Vuelo(id, codigoVuelo, origen, destino, fecha, hora, plazasTotales, plazasDisponibles);
			misVuelos.put(miVuelo.getId(), miVuelo);

		}

		return misVuelos;
	}

	public void insertar(Vuelo vuelo) throws SQLException {
		MongoDatabase db = mongo.getDatabase("adat_vuelos");
		MongoCollection collectionVuelos = db.getCollection("vuelos");

		Document doc = new Document("id", vuelo.getId()).append("codigo", vuelo.getCodigoVuelo())
				.append("origen", vuelo.getOrigen()).append("destino", vuelo.getDestino())
				.append("fecha", Auxiliares.parseFechaStr(vuelo.getFecha())).append("hora", vuelo.getHora())
				.append("plazas_totales", vuelo.getPlazasTotales())
				.append("plazas_disponibles", vuelo.getPlazasDisponibles());

		collectionVuelos.insertOne(doc);
		System.out.println("Insertado correctamente");

	}

	public void modificar(Vuelo vuelo) throws SQLException {

		MongoDatabase db = mongo.getDatabase("adat_vuelos");
		MongoCollection collectionVuelos = db.getCollection("vuelos");

		Vuelo originalVuelo = buscar(vuelo.getId());

		Document queryCambiar = new Document("id", originalVuelo.getId())
				.append("codigo", originalVuelo.getCodigoVuelo()).append("origen", originalVuelo.getOrigen())
				.append("destino", originalVuelo.getDestino())
				.append("fecha", Auxiliares.parseFechaStr(originalVuelo.getFecha()))
				.append("hora", originalVuelo.getHora()).append("plazas_totales", originalVuelo.getPlazasTotales())
				.append("plazas_disponibles", originalVuelo.getPlazasDisponibles());

		Document modificado = new Document("id", vuelo.getId()).append("codigo", vuelo.getCodigoVuelo())
				.append("origen", vuelo.getOrigen()).append("destino", vuelo.getDestino())
				.append("fecha", Auxiliares.parseFechaStr(vuelo.getFecha())).append("hora", vuelo.getHora())
				.append("plazas_totales", vuelo.getPlazasTotales())
				.append("plazas_disponibles", vuelo.getPlazasDisponibles());

		Document auxSet = new Document("$set", modificado);

		collectionVuelos.updateOne(queryCambiar, auxSet);

		System.out.println("Modificado correctamente");

	}

	public void borrar(int id) throws SQLException {
		MongoDatabase db = mongo.getDatabase("adat_vuelos");
		MongoCollection collectionVuelos = db.getCollection("vuelos");

		Vuelo originalVuelo = buscar(id);

		Document queryBorrar = new Document("id", originalVuelo.getId())
				.append("codigo", originalVuelo.getCodigoVuelo()).append("origen", originalVuelo.getOrigen())
				.append("destino", originalVuelo.getDestino())
				.append("fecha", Auxiliares.parseFechaStr(originalVuelo.getFecha()))
				.append("hora", originalVuelo.getHora()).append("plazas_totales", originalVuelo.getPlazasTotales())
				.append("plazas_disponibles", originalVuelo.getPlazasDisponibles());

		collectionVuelos.deleteOne(queryBorrar);
		System.out.println("Borrado correctamente");

	}

	public Vuelo buscar(int id) throws SQLException {
		FindIterable fi = collectionVuelos.find();
		MongoCursor cur = fi.cursor();

		while (cur.hasNext()) {
			Document doc = (Document) cur.next();

			if (Auxiliares.castearInt(doc, "id") == id) {
				String codigoVuelo = doc.getString("codigo");
				String origen = doc.getString("origen");
				String destino = (doc.getString("destino"));
				Date fecha = Auxiliares.parseFecha(doc.getString("fecha"));
				String hora = doc.getString("hora");
				int plazasTotales = Auxiliares.castearInt(doc, "plazas_totales");
				int plazasDisponibles = Auxiliares.castearInt(doc, "plazas_disponibles");
				return new Vuelo(id, codigoVuelo, origen, destino, fecha, hora, plazasTotales, plazasDisponibles);

			}

		}

		return null;
	}

}
