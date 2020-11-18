package Util;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import javax.persistence.TypedQuery;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Modelo.Vuelo;

public class ConnectedHibernate implements AccesoDatos {

	Session session;

	public ConnectedHibernate() {
		ConnectionHibernate util = new ConnectionHibernate();
		session = util.getSessionFactory().openSession();
	}

	@Override
	public HashMap<Integer, Vuelo> mostrar() throws SQLException {

		HashMap<Integer, Vuelo> misVuelos = new HashMap<Integer, Vuelo>();
		// TypedQuery<Vuelo> q = session.createQuery("select e from Vuelo e");
		// List<Vuelo> results = q.getResultList();

		Query q = session.createQuery("select e from Vuelo e");
		List results = q.list();

		Iterator<Vuelo> equiposIterator = results.iterator();
		while (equiposIterator.hasNext()) {
			Vuelo miVuelo = equiposIterator.next();
			misVuelos.put(miVuelo.getId(), miVuelo);
		}

		return misVuelos;
	}

	@Override
	public void insertar(Vuelo vuelo) throws SQLException {
		session.beginTransaction();
		session.save(vuelo);
		session.getTransaction().commit();
	}

	@Override
	public void modificar(Vuelo vuelo) throws SQLException {
		session.beginTransaction();
		session.update(vuelo);
		session.getTransaction().commit();

	}

	@Override
	public void borrar(int id) throws SQLException {
		System.out.println("Inicio Borrado");
		session.beginTransaction();
		Vuelo mivuelo;
		mivuelo = session.load(Vuelo.class, id);
		session.delete(mivuelo);
		session.getTransaction().commit();

	}

	@Override
	public Vuelo buscar(int id) throws SQLException {

		Query q = session.createQuery("from Vuelo v where v.id = :id");
		q.setParameter("id", id);

		return (Vuelo) q.uniqueResult();

	}

}

class ConnectionHibernate {

	private SessionFactory sessionFactory;

	public ConnectionHibernate() {

		try {
			// Create the SessionFactory from standard (hibernate.cfg.xml) config file.
			sessionFactory = new Configuration().configure().buildSessionFactory();

		} catch (Throwable ex) {
			// Log the exception.
			System.err.println("Initial SessionFactory creation failed");
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
