/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

/**
 * Classe para armazenar os métodos de transação com o banco do objeto login
 * @author Kalleo Leandro dos Santos Leal
 * @version 1.0
 * @since 01/02/2023
 */


public class DAOLoginRepository {
	
	private Connection connection;
	
	public DAOLoginRepository() {
		
		connection = SingleConnectionBanco.getConnection();
		
	}
	
	public boolean validarAutenticacao(ModelLogin modelLogin) throws SQLException {
		
		String sql = "select * from model_login where upper(login) = upper(?) and upper(senha) = upper(?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, modelLogin.getLogin());
		stmt.setString(2, modelLogin.getSenha());
		ResultSet resultSet = stmt.executeQuery();
		if(resultSet.next()) {
			return true;
		}		
		return false;
		
	}

}
