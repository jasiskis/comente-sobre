package br.com.andresoft.comentesobre.controller;

import java.util.Calendar;
import java.util.List;

import br.com.andresoft.comentesobre.dao.ComentariosDao;
import br.com.andresoft.comentesobre.model.Comentario;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class BlogController {

	private ComentariosDao comentariosDao;
	private Result result;
	private Validator validator;

	public BlogController(ComentariosDao cDAO, Result result, Validator validator) {
		this.comentariosDao = cDAO;
		this.result = result;
		this.validator = validator;
	}

	@Get @Path("/{assunto}")
	public void busca(String assunto) {
		List<Comentario> buscaPorAssunto = comentariosDao.buscaPorAssunto(assunto);
		
		result.include("posts", buscaPorAssunto);
		result.include("assunto", assunto);

	}
	
	@Get @Path("/{assunto}/comentario")
	public void comentario(final Comentario comentario, String assunto) {
		validator.validate(comentario);
		validator.onErrorForwardTo(this).busca(assunto);
		
		comentario.setData(Calendar.getInstance());
				
		comentariosDao.adiciona(comentario);

		result.include("posts", comentariosDao.buscaPorAssunto(assunto));
		result.include("assunto", assunto);
		
		result.redirectTo(BlogController.class).busca(assunto);
	}
}
