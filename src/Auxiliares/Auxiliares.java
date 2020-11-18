package Auxiliares;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.bson.Document;

import java.sql.Date;

public class Auxiliares {

	public static Date parseFecha(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date fechaUtil = null;
		java.sql.Date fechaSql = null;
		try {
			fechaUtil = formato.parse(fecha);
			fechaSql = new java.sql.Date(fechaUtil.getTime());
		} catch (ParseException ex) {
			System.out.println(ex);
		}
		return fechaSql;
	}

	public static String parseFechaStr(Date fecha) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(fecha);

	}

	public static int castearInt(Document doc, String nombre) {
		int valor;
		try {
			valor = doc.getInteger(nombre);
		} catch (Exception e) {
			valor = (int) Math.round(doc.getDouble(nombre));
		}

		return valor;
	}

}
