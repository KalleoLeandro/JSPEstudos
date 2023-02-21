/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelTelefones;

/**
 * Classe para armazenar os métodos de transação com o banco do objeto telefone
 * @author Kalleo Leandro dos Santos Leal
 * @version 1.0
 * @since 18/02/2023
 */

public class DAOTelefoneRepository {
	
	private Connection connection;
	
	public DAOTelefoneRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public List<ModelTelefones> listaFones(Long idUsuario) throws SQLException{
		List<ModelTelefones> listaRetorno = new ArrayList<ModelTelefones>();
		String sql = "select * from telefones where usuario_id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, idUsuario);
		ResultSet retornoBd = stmt.executeQuery();
		while(retornoBd.next()) {
			ModelTelefones modelTelefones = new ModelTelefones();
			modelTelefones.setId(retornoBd.getLong("id"));
			modelTelefones.setNumero(retornoBd.getString("numero"));
			modelTelefones.setUsuario_id(retornoBd.getLong("usuario_id"));
			modelTelefones.setUsuario_cadastro_id(retornoBd.getLong("usuario_cadastro_id"));
			listaRetorno.add(modelTelefones);
		}
		return listaRetorno;		
	}
	
	public void gravaTelefone(ModelTelefones modelTelefones) throws SQLException {
		String sql = "insert into telefones(numero,usuario_id, usuario_cadastro_id) values(?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, modelTelefones.getNumero());
		stmt.setLong(2, modelTelefones.getUsuario_id());
		stmt.setLong(3, modelTelefones.getUsuario_cadastro_id());
		stmt.execute();
		connection.commit();		
	}
	
	public void deletaTelefone(Long id) throws SQLException {
		String sql = "delete from telefones where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, id);		
		stmt.executeUpdate();
		connection.commit();
	} 

	public boolean existeTelefone(String numero, Long idUser) throws SQLException {
		String sql = "select count(1) > 0 as existe from telefones where usuario_id = ? and numero = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, idUser);
		stmt.setString(2, numero);		
		ResultSet resultado = stmt.executeQuery();
		resultado.next();
		
		return	resultado.getBoolean("existe");
		
		
	}
}
