package Util;

import java.sql.SQLException;
import java.util.HashMap;

import Modelo.Vuelo;

public interface AccesoDatos {

	public HashMap<Integer, Vuelo> mostrar() throws SQLException;

	public void insertar(Vuelo vuelo) throws SQLException;

	public void modificar(Vuelo vuelo) throws SQLException;

	public void borrar(int id) throws SQLException;

	public Vuelo buscar(int id) throws SQLException;

}
