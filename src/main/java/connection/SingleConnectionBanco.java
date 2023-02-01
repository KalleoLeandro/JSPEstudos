/**
 * 
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe para armazenar os métodos de conexão com o banco de dados
 * @author Kalleo Leandro dos Santos Leal
 * @version 1.0
 * since 01/02/2023
 */


public class SingleConnectionBanco {

	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String password = "root";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		conectar();
	}
	
	public SingleConnectionBanco () {
		conectar();
	}
	
	private static void conectar() {
	
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver");//Carrega o driver de conexão do banco
				connection = DriverManager.getConnection(banco,user,password);
				connection.setAutoCommit(false);
				System.out.println("Conexão efetuada com sucesso");
			}
		}catch (Exception e) {
			e.printStackTrace();/*Mostrar qualquer erro no momento de conectar*/
		}
	}
	
}
