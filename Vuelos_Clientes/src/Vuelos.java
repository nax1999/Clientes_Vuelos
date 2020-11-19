

import java.sql.Date;

public class Vuelos {

	
	private String codigoVuelo;
	private String origen;
	private String destino;
	private String fecha;
	private String hora;
	private int plazasTotales;
	private int plazasDisponibles;

	public Vuelos( String codigoVuelo, String origen, String destino, String fecha, String hora, int plazasTotales,
			int plazasisponibles) {
		super();
		
		this.codigoVuelo = codigoVuelo;
		this.origen = origen;
		this.destino = destino;
		this.fecha = fecha;
		this.hora = hora;
		this.plazasTotales = plazasTotales;
		this.plazasDisponibles = plazasisponibles;

	}

	public Vuelos() {

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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
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



	
	@Override
	public String toString() {
		return "Vuelo [CODIGOVUELO = " + codigoVuelo + ",ORIGEN = " + origen
				+ ",DESTINO = " + destino + ",FECHA = " + fecha + ",HORA = " + hora
				+ ",PLAZAS TOTALES = " + plazasTotales + ",PLAZAS DISPONIBLES = " + plazasDisponibles + "]";
	}

	public String[] getTitulo() {
		String[] array = { "CODIGOVUELO", "ORIGEN", "DESTINO", "FECHA", "HORA", "PLAZAS TOTALES",
				"PLAZAS DISPONIBLES" };
		return array;

	}
}