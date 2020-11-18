package Modelo;

import java.sql.Date;

public class Vuelo {

	private int id;
	private String codigoVuelo;
	private String origen;
	private String destino;
	private Date fecha;
	private String hora;
	private int plazasTotales;
	private int plazasDisponibles;

	public Vuelo(int id, String codigoVuelo, String origen, String destino, Date fecha, String hora, int plazasTotales,
			int plazasisponibles) {
		super();
		this.id = id;
		this.codigoVuelo = codigoVuelo;
		this.origen = origen;
		this.destino = destino;
		this.fecha = fecha;
		this.hora = hora;
		this.plazasTotales = plazasTotales;
		this.plazasDisponibles = plazasisponibles;

	}

	public Vuelo() {

	}

	public String getCodigoVuelo() {
		return codigoVuelo;
	}

	public void setCodigoVuelo(String codigoVuelo) {
		this.codigoVuelo = codigoVuelo;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getPlazasTotales() {
		return plazasTotales;
	}

	public void setPlazasTotales(int plazasTotales) {
		this.plazasTotales = plazasTotales;
	}

	public int getPlazasDisponibles() {
		return plazasDisponibles;
	}

	public void setPlazasDisponibles(int plazasDisponibles) {
		this.plazasDisponibles = plazasDisponibles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Vuelo [\n ID = " + id + ", \n CODIGOVUELO = " + codigoVuelo + ", \n ORIGEN = " + origen
				+ ", \n DESTINO = " + destino + ", \n FECHA = " + fecha + ", \n HORA = " + hora
				+ ", \n PLAZAS TOTALES = " + plazasTotales + ", \n PLAZAS DISPONIBLES = " + plazasDisponibles + "]";
	}

	public String[] getTitulo() {
		String[] array = { "CODIGOVUELO", "ORIGEN", "DESTINO", "FECHA", "HORA", "PLAZAS TOTALES",
				"PLAZAS DISPONIBLES" };
		return array;

	}
}