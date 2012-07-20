package blog;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import br.com.andresoft.comentesobre.controller.BlogController;
import br.com.andresoft.comentesobre.dao.ComentariosDao;
import br.com.andresoft.comentesobre.dao.CriadorDeSession;
import br.com.andresoft.comentesobre.dao.CriadorDeSessionFactory;
import br.com.andresoft.comentesobre.model.Comentario;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.ValidationException;

public class BuscaTest {
	private Session session;
	final String TEMA_PARA_BUSCA = "Agile2012";
	
	@Test
	public void DeveRetornarBuscaPorPostsVazia() {
		final String TEMA_PARA_BUSCA = "tdc2012";

		List<Comentario> comentarios = fazBusca(TEMA_PARA_BUSCA);
		int quantidadeDePosts = verificaQuantidadeDePostsRetornados(comentarios);

		assertThat(quantidadeDePosts, is(0));
	}

	@Test
	public void DeveRetornar1ComentarioParaABusca() {
		
		Comentario comentario = new Comentario();
		comentario.setAssunto(TEMA_PARA_BUSCA).setData(Calendar.getInstance())
				.setEmail("teste@teste.com")
				.setTexto("Comentario Maroto sobre a Agile brasil 2012");

		fazerComentario(comentario);

		List<Comentario> comentarios = fazBusca(TEMA_PARA_BUSCA);
		int quantidadeDePosts = verificaQuantidadeDePostsRetornados(comentarios);

		assertThat(quantidadeDePosts, is(1));
	}
	
	@Test(expected=ConstraintViolationException.class)
	public void TentaAdicionarComentarioSemTextoERetornaError() {
		
		Comentario comentario = new Comentario();
		comentario.setAssunto(TEMA_PARA_BUSCA)
				.setData(Calendar.getInstance())
				.setEmail("teste@teste.com")
				.setTexto("");

		fazerComentario(comentario);
	}
	
	@Test(expected=ConstraintViolationException.class)
	public void TentaAdicionarComentarioSemEmailERetornaError() {
		
		Comentario comentario = new Comentario();
		comentario.setAssunto(TEMA_PARA_BUSCA)
				.setData(Calendar.getInstance())
				.setEmail("")
				.setTexto("asdfasdfasdf");

		fazerComentario(comentario);
	}
	
	@Test(expected=ConstraintViolationException.class)
	public void TentaAdicionarEmailInvalidoERetornaError() {
		
		Comentario comentario = new Comentario();
		comentario.setAssunto(TEMA_PARA_BUSCA)
				.setData(Calendar.getInstance())
				.setEmail("test@om")
				.setTexto("aa");

		fazerComentario(comentario);
	}
	
	@Test(expected=ConstraintViolationException.class)
	public void TentaAdicionarComentarioComMenosDe3CaracERetornaError() {
		

		Comentario comentario = new Comentario();
		comentario.setAssunto(TEMA_PARA_BUSCA)
				.setData(Calendar.getInstance())
				.setEmail("ss")
				.setTexto("asdfasdfasdf");

		fazerComentario(comentario);
	}
	
	private void fazerComentario(Comentario comentario) {
		MockResult result = new MockResult();

		BlogController controller = new BlogController(new ComentariosDao(
				this.session), result, new MockValidator());

		controller.comentario(comentario, comentario.getAssunto());
	}

	private int verificaQuantidadeDePostsRetornados(List<Comentario> comentarios) {
		return comentarios.size();
	}

	private List<Comentario> fazBusca(String assunto) {
		MockResult result = new MockResult();

		BlogController controller = new BlogController(new ComentariosDao(
				this.session), result, new MockValidator());
		controller.busca(assunto);
		return result.included("posts");

	}

	@Before
	public void setUp() {
		CriadorDeSessionFactory factory = new CriadorDeSessionFactory();

		factory.abre();

		CriadorDeSession criadorDeSession = new CriadorDeSession(
				factory.getInstance());
		criadorDeSession.abre();

		this.session = criadorDeSession.getInstance();

	}

}
