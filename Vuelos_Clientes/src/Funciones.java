
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Funciones {
	Scanner sc = new Scanner(System.in);
	String randomL;
	Vuelos vuelo = new Vuelos();

	public MongoClient crearConexion() {
		MongoClient mongo = null;
		try {
			mongo = new MongoClient("localhost", 27017);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mongo;
	}

	public MongoCollection<?> Conexion() {
		MongoClient mongo = crearConexion();
		MongoDatabase db = mongo.getDatabase("adat_vuelos_ampliada");
		MongoCollection<?> colleccionVuelos = db.getCollection("clientes");
		return colleccionVuelos;
	}

	public void obtenerVuelos() {
		MongoCollection<?> colleccionVuelos = Conexion();
		HashMap<String, Vuelos> obtenerDepositos = new HashMap<String, Vuelos>();
		try {
			FindIterable<?> fi = colleccionVuelos.find();
			MongoCursor<?> cur = fi.cursor();
			while (cur.hasNext()) {
				Document rs = (Document) cur.next();
				vuelo.setCodigoVuelo(rs.getString("codigo"));
				vuelo.setOrigen(rs.getString("origen"));
				vuelo.setDestino(rs.getString("destino"));
				vuelo.setFecha(rs.getString("fecha"));
				vuelo.setHora(rs.getString("hora"));
				vuelo.setPlazasTotales(rs.getInteger("plazas_totales"));
				vuelo.setPlazasDisponibles(rs.getInteger("plazas_disponibles"));
				vuelo = new Vuelos(vuelo.getCodigoVuelo(), vuelo.getOrigen(), vuelo.getDestino(), vuelo.getFecha(),
						vuelo.getHora(), vuelo.getPlazasTotales(), vuelo.getPlazasDisponibles());
				obtenerDepositos.put(vuelo.getCodigoVuelo(), vuelo);
				System.out.println(vuelo);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void comprarBilletes(String codigoVuelo, String dni, String nombre, String apellido, String dniPagador,
			String tarjeta) {
		int decrementarPlazas = -1;
		String codV = numAleatorios();
		MongoCollection<?> colleccionVuelos = Conexion();
		int plazasDisponibles;
        FindIterable<?> fi = colleccionVuelos.find();
        MongoCursor<?> cur = fi.cursor();
            Document rs = (Document) cur.next();
            plazasDisponibles = rs.getInteger("plazas_disponibles");    
            int asientos=(int) (Math.random()*plazasDisponibles);
		Document quienCambio = new Document("codigo", codigoVuelo);
		Document doc = new Document();
		doc.append("asiento", asientos);
		doc.append("dni", dni);
		doc.append("apellido", nombre);
		doc.append("nombre", apellido);
		doc.append("dniPagador", dniPagador);
		doc.append("tarjeta", tarjeta);
		doc.append("codigoVenta", codV);
		Document auxSet1 = new Document("vendidos", doc);
		Document auxSet3 = new Document("plazas_disponibles", decrementarPlazas);
		Document auxSet2 = new Document("$push", auxSet1);
		Document auxSet4 = new Document("$inc", auxSet3);
		if(plazasDisponibles==0 ) {
			System.out.println("NO QUEDAN PLAZAS DISPONIBLES, INTENTELO EN OTRO MOMENTO");
		}else{			
			colleccionVuelos.updateOne(quienCambio, auxSet2);
			colleccionVuelos.updateOne(quienCambio, auxSet4);
			System.out.println("Su codigo de venta es: " + codV);
			System.out.println("Su asiento es: " + asientos);
						
		}

	}
	
	public String numAleatorios() {

		char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
		StringBuilder sb = new StringBuilder(9);
		Random random = new Random();
		for (int i = 0; i < 9; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		randomL = sb.toString();
		return randomL;

	}

	public void borrarBilletes(String codigoVuelo, String dni, String codigoVenta) {

		MongoCollection<?> colleccionVuelos = Conexion();
		int plazasDisponibles = 1;
		Document quienCambio = new Document("codigo", codigoVuelo);
		Document cambios = new Document();
		cambios.append("dni", dni);
		cambios.append("codigoVenta", codigoVenta);
		Document auxSet1 = new Document("vendidos", cambios);
		Document auxSet2 = new Document("plazas_disponibles", plazasDisponibles);
		Document auxSet3 = new Document("$pull", auxSet1);
		Document auxSet4 = new Document("$inc", auxSet2);
		colleccionVuelos.updateOne(quienCambio, auxSet3);
		colleccionVuelos.updateOne(quienCambio, auxSet4);
	}

	public void modificarBilletes(String codigoVuelo, String dni, String codigoVenta, String dniN, String apellidoN,
			String nombreN, String dniPagadorN, String tarjetaN) {
		MongoCollection<?> colleccionVuelos = Conexion();
		String codV = numAleatorios();
		int plazasDisponibles;
		Document quienCambio = new Document("codigo", codigoVuelo);
		Document cambios = new Document();
		cambios.append("dni", dni);
		cambios.append("codigoVenta", codigoVenta);
		Document auxSet1 = new Document("vendidos", cambios);
		Document auxSet2 = new Document("$pull", auxSet1);
		colleccionVuelos.updateOne(quienCambio, auxSet2);
		FindIterable<?> fi = colleccionVuelos.find();
        MongoCursor<?> cur = fi.cursor();
        Document rs = (Document) cur.next();
		plazasDisponibles = rs.getInteger("plazas_disponibles");    
        int asientos=(int) (Math.random()*plazasDisponibles);
		Document cambios2 = new Document();
		cambios2.append("asiento", asientos);
		cambios2.append("dni", dniN);
		cambios2.append("apellido", apellidoN);
		cambios2.append("nombre", nombreN);
		cambios2.append("dniPagador", dniPagadorN);
		cambios2.append("tarjeta", tarjetaN);
		cambios2.append("codigoVenta", codV);
		System.out.println("Su codigo de venta es: " + codV);
		Document quienCambio2 = new Document("codigo", codigoVuelo);
		Document auxSet3 = new Document("vendidos", cambios2);
		Document auxSet4 = new Document("$push", auxSet3);
		colleccionVuelos.updateOne(quienCambio2, auxSet4);

	}
	
	public void obtenerMisVuelos(String dni) {
		MongoCollection<?> colleccionVuelos = Conexion();
		HashMap<String, Vuelos> obtenerDepositos = new HashMap<String, Vuelos>();
		
		try {
			Document quienCambio2 = new Document("vendidos.dni",dni);
			FindIterable<?> fi = colleccionVuelos.find(quienCambio2);
			MongoCursor<?> cur = fi.cursor();
			while (cur.hasNext()) {
				Document rs = (Document) cur.next();
				vuelo.setCodigoVuelo(rs.getString("codigo"));
				vuelo.setOrigen(rs.getString("origen"));
				vuelo.setDestino(rs.getString("destino"));
				vuelo.setFecha(rs.getString("fecha"));
				vuelo.setHora(rs.getString("hora"));
				vuelo.setPlazasTotales(rs.getInteger("plazas_totales"));
				vuelo.setPlazasDisponibles(rs.getInteger("plazas_disponibles"));
				vuelo = new Vuelos(vuelo.getCodigoVuelo(), vuelo.getOrigen(), vuelo.getDestino(), vuelo.getFecha(),
						vuelo.getHora(), vuelo.getPlazasTotales(), vuelo.getPlazasDisponibles());
				obtenerDepositos.put(vuelo.getCodigoVuelo(), vuelo);
				System.out.println(vuelo);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}
	

}
