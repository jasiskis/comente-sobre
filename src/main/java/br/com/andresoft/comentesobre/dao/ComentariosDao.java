package br.com.andresoft.comentesobre.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.andresoft.comentesobre.model.Comentario;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class ComentariosDao extends DAOGenerico<Comentario> {

	public ComentariosDao(Session session) {
		super(Comentario.class, session);
	}

	public List<Comentario> buscaPorAssunto(String assunto) {
		@SuppressWarnings("unchecked")
		List<Comentario> comentarios = session.createCriteria(Comentario.class)
				.add(Restrictions.eq("assunto", assunto)).list();

		return comentarios;

	}
}
