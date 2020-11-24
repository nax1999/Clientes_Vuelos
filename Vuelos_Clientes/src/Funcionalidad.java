import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Funcionalidad {
	Scanner sc = new Scanner(System.in);
	String randomL;

	private static MongoClient crearConexion() {
		MongoClient mongo = null;
		try {
			mongo = new MongoClient("localhost", 27017);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mongo;
	}

	private MongoCollection<?> Conexion() {
		MongoClient mongo = crearConexion();
		MongoDatabase db = mongo.getDatabase("adat_vuelos_ampliada");
		MongoCollection<?> colleccionVuelos = db.getCollection("clientes");
		return colleccionVuelos;
	}

	public void obtenerVuelos() {
		MongoCollection<?> colleccionVuelos = Conexion();
		HashMap<String, Vuelos> obtenerDepositos = new HashMap<String, Vuelos>();
		Vuelos vuelos;
		String codigoVuelo;
		String origen;
		String destino;
		String fecha;
		String hora;
		int plazasTotales;
		int plazasDisponibles;
		try {
			System.out.println("Listar los registros de la tabla: ");
			FindIterable<?> fi = colleccionVuelos.find();
			MongoCursor<?> cur = fi.cursor();
			while (cur.hasNext()) {
				Document rs = (Document) cur.next();
				codigoVuelo = rs.getString("codigo");
				origen = rs.getString("origen");
				destino = rs.getString("destino");
				fecha = rs.getString("fecha");
				hora = rs.getString("hora");
				plazasTotales = rs.getInteger("plazas_totales");
				plazasDisponibles = rs.getInteger("plazas_disponibles");
				vuelos = new Vuelos(codigoVuelo, origen, destino, fecha, hora, plazasTotales, plazasDisponibles);
				obtenerDepositos.put(codigoVuelo, vuelos);
				System.out.println(vuelos);

			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public void comprarBilletes() {
		int plazasDisponibles = -1;
		String codV = numAleatorios();
		MongoCollection<?> colleccionVuelos = Conexion();
		obtenerVuelos();
		System.out.println("Introduzca el CodigoVuelo a comprar");
		Document quienCambio = new Document("codigo", sc.next());
		Document doc = new Document();
		System.out.println("INTRODUZCA EL ASIENTO");
		doc.append("asiento", sc.next());
		System.out.println("INTRODUZCA SU DNI");
		doc.append("dni", sc.next());
		System.out.println("INTRODUZCA SU APELLIDO");
		doc.append("apellido", sc.next());
		System.out.println("INTRODUZCA SU NOMBRE");
		doc.append("nombre", sc.next());
		System.out.println("INTRODUZCA EL DNI DEL PAGADOR");
		doc.append("dniPagador", sc.next());
		System.out.println("INTRODUZCA EL Nº DE TARJETA");
		doc.append("tarjeta", sc.next());
		System.out.println("SE HA GENERADO EL CODIGO VENTA");
		doc.append("codigoVenta", codV);
		System.out.println("SU CODIGO DE VENTA ES: " + codV);
		Document auxSet1 = new Document("vendidos", doc);
		Document auxSet3 = new Document("plazas_disponibles", plazasDisponibles);
		Document auxSet2 = new Document("$push", auxSet1);
		Document auxSet4 = new Document("$inc", auxSet3);
		colleccionVuelos.updateOne(quienCambio, auxSet2);
		colleccionVuelos.updateOne(quienCambio, auxSet4);

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

	public void restarNumPlazas() {
		MongoCollection<?> colleccionVuelos = Conexion();
		System.out.println("¿Que codigo quieres modificar?");
		Document cambiar = new Document("CodigoVuelo", sc.next());
		Document doc = new Document();
		System.out.println("Inserte plazas disponibles");
		doc.append("PlazasDisponibles", sc.nextInt());
		Document auxSet = new Document("$set", doc);
		colleccionVuelos.updateOne(cambiar, auxSet);

	}

	public void borrarBilletes() {
		MongoCollection colleccionVuelos = Conexion();
		HashMap<String, Vuelos> obtenerDepositos = new HashMap<String, Vuelos>();
		int plazasDisponibles = 1;
		obtenerVuelos();
		System.out.println("¿Que CODIGO VUELO quieres seleccionar?");
		String codigo = sc.next();
		Document quienCambio = new Document("codigo", codigo);
		Document cambios = new Document();
		System.out.println("INTRODUZCA EL DNI A BORRAR");
		cambios.append("dni", sc.next());
		System.out.println("INTRODUZCA SU CODIGO VENTA A BORRAR");
		cambios.append("codigoVenta", sc.next());
		Document auxSet1 = new Document("vendidos", cambios);
		Document auxSet2 = new Document("plazas_disponibles", plazasDisponibles);
		Document auxSet3 = new Document("$pull", auxSet1);
		Document auxSet4 = new Document("$inc", auxSet2);
		colleccionVuelos.updateOne(quienCambio, auxSet3);
		colleccionVuelos.updateOne(quienCambio, auxSet4);
		System.out.println("BILLETE BORRADO CORRECTAMENTE.");

	}

	public void modificarBilletes() {
		MongoCollection colleccionVuelos = Conexion();
		String codV = numAleatorios();
		System.out.println("Que codigo vuelo quieres seleccionar");
		String codigo = sc.next();
		Document quienCambio = new Document("codigo", codigo);
		Document cambios = new Document();
		System.out.println("INTRODUZCA EL DNI A BORRAR");
		cambios.append("dni", sc.next());
		System.out.println("INTRODUZCA SU CODIGO VENTA A BORRAR");
		cambios.append("codigoVenta", sc.next());
		Document auxSet1 = new Document("vendidos", cambios);
		Document auxSet2 = new Document("$pull", auxSet1);
		colleccionVuelos.updateOne(quienCambio, auxSet2);
		Document cambios2 = new Document();
		System.out.println("INTRODUZCA SU ASIENTO");
		cambios2.append("asiento", sc.next());
		System.out.println("INTRODUZCA SU DNI");
		cambios2.append("dni", sc.next());
		System.out.println("INTRODUZCA SU APELLIDO");
		cambios2.append("apellido", sc.next());
		System.out.println("INTRODUZCA SU NOMBRE");
		cambios2.append("nombre", sc.next());
		System.out.println("INTRODUZCA EL DNI DEL PAGADOR");
		cambios2.append("dniPagador", sc.next());
		System.out.println("INTRODUZCA EL Nº DE TARJETA ");
		cambios2.append("tarjeta", sc.next());
		System.out.println("SE HA GENERADO UN NUEVO CODIGO DE VENTA PARA MAYOR SEGURIDAD");
		cambios2.append("codigoVenta", codV);
		System.out.println("SU NUEVO CODIGO DE VENTA ES:" + codV);
		Document quienCambio2 = new Document("codigo", codigo);
		Document auxSet3 = new Document("vendidos", cambios2);
		Document auxSet4 = new Document("$push", auxSet3);
		colleccionVuelos.updateOne(quienCambio2, auxSet4);
		System.out.println("BILLETE MODIFICADO CORRECTAMENTE.");

	}

}