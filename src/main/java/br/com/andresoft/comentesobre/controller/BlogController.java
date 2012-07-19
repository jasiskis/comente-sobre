package br.com.andresoft.comentesobre.controller;

import java.util.List;

import br.com.andresoft.comentesobre.dao.ComentariosDao;
import br.com.andresoft.comentesobre.model.Comentario;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class BlogController {

	private ComentariosDao comentariosDao;
	private Result result;

	public BlogController(ComentariosDao cDAO, Result result) {
		this.comentariosDao = cDAO;
		this.result = result;
	}

	public List<Comentario> busca(String assunto) {
		return comentariosDao.buscaPorAssunto(assunto);

	}

	public void comentario(Comentario comentario) {
		comentariosDao.adiciona(comentario);
	}
}
