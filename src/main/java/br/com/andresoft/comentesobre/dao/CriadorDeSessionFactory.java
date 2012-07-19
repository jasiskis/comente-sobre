package br.com.andresoft.comentesobre.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
public class CriadorDeSessionFactory implements
		ComponentFactory<SessionFactory> {

	private SessionFactory factory;

	public void abre() {
		Configuration cfg = new Configuration();
		cfg.configure();
		this.factory = cfg.buildSessionFactory();
	}

	public SessionFactory getInstance() {
		return this.factory;
	}

	public void fecha() {
		this.factory.close();
	}

}