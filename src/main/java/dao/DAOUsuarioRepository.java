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
 * Classe para armazenar os métodos de transação com o banco do objeto usuario
 * @author Kalleo Leandro dos Santos Leal
 * @version 1.0
 * @since 07/02/2023
 */

public class DAOUsuarioRepository {

	private Connection connection;
	
	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();		
	}
	
	public ModelLogin gravarUsuario(ModelLogin modelLogin) throws SQLException{
		if(modelLogin.isNovo()) {
			String sql = "insert into model_login(nome,email,login,senha) values(?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, modelLogin.getNome());
			stmt.setString(2, modelLogin.getEmail());
			stmt.setString(3, modelLogin.getLogin());
			stmt.setString(4, modelLogin.getSenha());
			stmt.execute();
			connection.commit();		
			return this.consultaUsuario(modelLogin.getLogin());
		} else {
			String sql = "update model_login set nome = ?, email = ?, login = ?, senha = ? where id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, modelLogin.getNome());
			stmt.setString(2, modelLogin.getEmail());
			stmt.setString(3, modelLogin.getLogin());
			stmt.setString(4, modelLogin.getSenha());
			stmt.setLong(5, modelLogin.getId());			
			stmt.executeUpdate();
			connection.commit();
			return this.consultaUsuario(modelLogin.getLogin());
		}
		
	}
	
	public ModelLogin consultaUsuario(String login) throws SQLException {
		ModelLogin modelLogin = new ModelLogin();
		String sql = "select * from model_login where upper(login) = upper(?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, login);
		ResultSet resultado = stmt.executeQuery();
		while(resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			
		}		
		return modelLogin;
	}
	
	public boolean validarLogin(ModelLogin modelLogin) throws Exception {
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper(?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, modelLogin.getLogin());
		ResultSet resultado = stmt.executeQuery();
		
		if(resultado.next()) {
			return resultado.getBoolean("existe");
		}
		return false;		
	}
	
	public void deletarUsuario(String id) throws SQLException {
		String sql = "delete from model_login where id = ?";		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, Long.parseLong(id));
		stmt.executeUpdate();
		connection.commit();		
	}
	
	
}
