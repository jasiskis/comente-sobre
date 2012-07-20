package br.com.andresoft.comentesobre.controller;

import java.util.Calendar;
import java.util.List;

import br.com.andresoft.comentesobre.dao.ComentariosDao;
import br.com.andresoft.comentesobre.model.Comentario;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
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

	@Get @Path("/{assunto}")
	public void busca(String assunto) {
		List<Comentario> buscaPorAssunto = comentariosDao.buscaPorAssunto(assunto);
		
		result.include("posts", buscaPorAssunto);
		result.include("assunto", assunto);

	}
	
	@Get @Path("/{assunto}/comentario")
	public void comentario(Comentario comentario, String assunto) {
		
		comentario.setData(Calendar.getInstance());
				
		System.out.println(comentario.getTexto()+""+comentario.getEmail());
		comentariosDao.adiciona(comentario);

		result.include("posts", comentariosDao.buscaPorAssunto(assunto));
		result.include("assunto", assunto);
		
		result.redirectTo(BlogController.class).busca(assunto);
	}
}
