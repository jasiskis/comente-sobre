package br.com.andresoft.comentesobre.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

abstract class DAOGenerico<T> {

	private Class<T> classe;
	protected Session session;

	public DAOGenerico(Class<T> persistentClass, Session session) {
		this.classe = persistentClass;
		this.session = session;
	}

	public void adiciona(T obj) {
		Transaction tx = session.beginTransaction();
		session.save(obj);
		tx.commit();
	}

	public void atualiza(T obj) {
		Transaction tx = session.beginTransaction();
		session.update(obj);
		tx.commit();
	}

	public void remove(T obj) {
		Transaction tx = session.beginTransaction();
		session.delete(obj);
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	public List<T> listarTodos() {
		return this.session.createCriteria(classe).list();
	}

	@SuppressWarnings("unchecked")
	public T buscaPorId(int id) {
		Transaction tx = session.beginTransaction();
		T obj = (T) session.load(classe, id);
		tx.commit();
		return obj;
	}

	public void removeTodos() {
		session.beginTransaction();
		Query query = session.createQuery("delete from " + classe.getName());
		query.executeUpdate();
		session.getTransaction().commit();
	}

}
