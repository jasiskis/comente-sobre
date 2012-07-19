package hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {
	Connection connection;
	Statement statement;

	@Test
	public void deveInserirRegistroNoBanco() throws SQLException {
		String registroASerInserido = "Teste Maroto =)! "
				+ System.currentTimeMillis();
		statement.execute("insert into teste values ('" + registroASerInserido
				+ "')");
		ResultSet resultset = statement
				.executeQuery("select top 1 * from teste desc;");

		String registroInserido = "";
		while (resultset.next()) {
			registroInserido = resultset.getString(1);
		}

		Assert.assertEquals(registroInserido, registroASerInserido);
	}

	@Before
	public void setUp() throws SQLException {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			String url = "jdbc:hsqldb:mem:src/main/resources/db/comentesobre";
			String username = "sa";
			String password = "";

			connection = DriverManager.getConnection(url, username, password);

			statement = connection.createStatement();
		} catch (Exception e) {
			System.err
					.println("ERRO: falha ao carregar o driver JDBC do HSQLDB!");
			e.printStackTrace();
		}

		try {
			statement.executeQuery("select * from sec_edgar_filer;");
		} catch (SQLException ex) { // if no table then create one.
			statement
					.execute("CREATE TABLE teste ( teste VARCHAR(100) NOT NULL);");
		}
	}

	@After
	public void tearDown() {
		try {
			statement.execute("SHUTDOWN;");
		} catch (Exception ex) {
		}
		try {
			connection.close();
		} catch (Exception ex) {
		}
	}
}
