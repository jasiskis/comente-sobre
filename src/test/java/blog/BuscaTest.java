package blog;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import br.com.andresoft.comentesobre.controller.BlogController;
import br.com.andresoft.comentesobre.dao.ComentariosDao;
import br.com.andresoft.comentesobre.dao.CriadorDeSession;
import br.com.andresoft.comentesobre.dao.CriadorDeSessionFactory;
import br.com.andresoft.comentesobre.model.Comentario;
import br.com.caelum.vraptor.util.test.MockResult;

public class BuscaTest {
	private Session session;

	@Test
	public void DeveRetornarBuscaPorPostsVazia() {
		final String TEMA_PARA_BUSCA = "tdc2012";

		List<Comentario> comentarios = fazBusca(TEMA_PARA_BUSCA);
		int quantidadeDePosts = verificaQuantidadeDePostsRetornados(comentarios);

		assertThat(quantidadeDePosts, is(0));
	}

	@Test
	public void DeveRetornar1ComentarioParaABusca() {
		final String TEMA_PARA_BUSCA = "Agile2012";

		Comentario comentario = new Comentario();
		comentario.setAssunto(TEMA_PARA_BUSCA).setData(Calendar.getInstance())
				.setEmail("teste@teste.com")
				.setTexto("Comentario Maroto sobre a Agile brasil 2012");

		fazerComentario(comentario);

		List<Comentario> comentarios = fazBusca(TEMA_PARA_BUSCA);
		int quantidadeDePosts = verificaQuantidadeDePostsRetornados(comentarios);

		assertThat(quantidadeDePosts, is(1));
	}

	private void fazerComentario(Comentario comentario) {
		// TODO Auto-generated method stub
		MockResult result = new MockResult();

		BlogController controller = new BlogController(new ComentariosDao(
				this.session), result);

		controller.comentario(comentario);
	}

	private int verificaQuantidadeDePostsRetornados(List<Comentario> comentarios) {
		// TODO Auto-generated method stub
		return comentarios.size();
	}

	private List<Comentario> fazBusca(String tema) {
		MockResult result = new MockResult();

		BlogController controller = new BlogController(new ComentariosDao(
				this.session), result);
		return controller.busca(tema);

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
