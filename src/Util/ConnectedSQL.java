package Util;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

import Modelo.Vuelo;

public class ConnectedSQL implements AccesoDatos {

	private String db = "ADAT_VUELOS";
	private String login = "root";
	private String pwd = "";
	private String url = "jdbc:mysql://localhost/" + db
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	private Connection miConexion;

	public void CreateConnection() throws SQLException {
		Connection conexion = DriverManager.getConnection(url, login, pwd);
		miConexion = conexion;

	}

	@Override
	public HashMap<Integer, Vuelo> mostrar() throws SQLException {

		HashMap<Integer, Vuelo> misVuelos = new HashMap<Integer, Vuelo>();

		CreateConnection();

		try {

			Statement s = miConexion.createStatement();
			ResultSet rs = s.executeQuery("SELECT * from Vuelos");
			while (rs.next()) {

				Vuelo miVuelo = new Vuelo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5), rs.getString(6), rs.getInt(7), rs.getInt(8));

				misVuelos.put(rs.getInt(1), miVuelo);

			}
			miConexion.close();

			return misVuelos;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return misVuelos;

	}

	@Override
	public void insertar(Vuelo vuelo) throws SQLException {

		CreateConnection();

		try {
			PreparedStatement ps = miConexion.prepareStatement(
					"INSERT INTO `Vuelos`(`CODVUELO`, `ORIGEN`, `DESTINO`, `FECHA`, `HORA`, `PLAZATOTALES`, `PLAZASDISPONIBLES`) VALUES (?, ?, ?, ?, ?, ?, ?)");

			ps.setString(1, vuelo.getCodigoVuelo());
			ps.setString(2, vuelo.getOrigen());
			ps.setString(3, vuelo.getDestino());
			ps.setDate(4, vuelo.getFecha());
			ps.setString(5, vuelo.getHora());
			ps.setInt(6, vuelo.getPlazasTotales());
			ps.setInt(7, vuelo.getPlazasDisponibles());
			ps.executeUpdate();

			System.out.println("INSERTADO CORRECTAMENTE");
		} catch (SQLException e) {

			System.out.println("INSERCCIÓN FALLIDA");

			e.printStackTrace();
		}
	}

	@Override
	public void modificar(Vuelo vuelo) throws SQLException {

		CreateConnection();

		try {

			PreparedStatement ps = miConexion.prepareStatement(
					"UPDATE `Vuelos` SET `CODVUELO`= ?,`ORIGEN`= ?,`DESTINO`= ?,`FECHA`= ?,`HORA`= ?,`PLAZATOTALES`= ?,`PLAZASDISPONIBLES`= ? WHERE ID = ? ");

			ps.setString(1, vuelo.getCodigoVuelo());
			ps.setString(2, vuelo.getOrigen());
			ps.setString(3, vuelo.getDestino());
			ps.setDate(4, vuelo.getFecha());
			ps.setString(5, vuelo.getHora());
			ps.setInt(6, vuelo.getPlazasTotales());
			ps.setInt(7, vuelo.getPlazasDisponibles());
			ps.setInt(8, vuelo.getId());
			ps.executeUpdate();

			System.out.println("ACTUALIZACIÓN COMPLETADA");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void borrar(int id) throws SQLException {

		CreateConnection();

		try {
			PreparedStatement ps = miConexion.prepareStatement("DELETE FROM `Vuelos` WHERE ID = ? ");

			ps.setInt(1, id);
			ps.executeUpdate();

			System.out.println("BORRADO CORRECTAMENTE");
		} catch (SQLException e) {

			System.out.println("BORRADO FALLIDO");

			e.printStackTrace();
		}
	}

	@Override
	public Vuelo buscar(int id) throws SQLException {

		CreateConnection();

		try {

			PreparedStatement ps = miConexion.prepareStatement("SELECT * FROM `Vuelos` WHERE ID = ? ");

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			Vuelo miVuelo = null;

			while (rs.next()) {

				miVuelo = new Vuelo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8));
			}

			miConexion.close();

			return miVuelo;

		} catch (SQLException e) {

			System.out.println("BUSCADO FALLIDO");

			e.printStackTrace();
		}
		return null;
	}

}
