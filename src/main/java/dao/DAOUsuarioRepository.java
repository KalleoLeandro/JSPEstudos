/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bean.SalarioGraficoBean;
import connection.SingleConnectionBanco;
import model.ModelLogin;

/**
 * Classe para armazenar os métodos de transação com o banco do objeto usuario
 * 
 * @author Kalleo Leandro dos Santos Leal
 * @version 1.0
 * @since 07/02/2023
 */

public class DAOUsuarioRepository {

	private Connection connection;
	
	private DAOTelefoneRepository daoTelefoneRepository = new DAOTelefoneRepository();

	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}

	public ModelLogin gravarUsuario(ModelLogin modelLogin, Long userLogado) throws SQLException {
		if (modelLogin.isNovo()) {
			String sql = "insert into model_login(nome,email,login,senha, usuario_id, perfil, useradmin, sexo,data_nascimento, renda_mensal) values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, modelLogin.getNome());
			stmt.setString(2, modelLogin.getEmail());
			stmt.setString(3, modelLogin.getLogin());
			stmt.setString(4, modelLogin.getSenha());
			stmt.setLong(5, userLogado);
			stmt.setString(6, modelLogin.getPerfil());
			if (modelLogin.getPerfil().equalsIgnoreCase("ADMINISTRADOR")) {
				stmt.setBoolean(7, true);
			} else {
				stmt.setBoolean(7, false);
			}
			stmt.setString(8, modelLogin.getSexo());
			stmt.setDate(9, modelLogin.getDataNascimento());
			stmt.setDouble(10, modelLogin.getRendaMensal());
			stmt.execute();
			connection.commit();

			if (modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
				sql = "update model_login set fotouser = ?, extensaofotouser = ? where login = ?";
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, modelLogin.getFotoUser());
				stmt.setString(2, modelLogin.getExtensaoFotoUser());
				stmt.setString(3, modelLogin.getLogin());
				stmt.executeUpdate();
				connection.commit();
			}
			
			sql = "update model_login set cep = ?, logradouro = ?, numero = ?, bairro = ?, localidade = ?, uf = ? where login = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, modelLogin.getCep());
			stmt.setString(2, modelLogin.getLogradouro());
			stmt.setString(3, modelLogin.getNumero());
			stmt.setString(4, modelLogin.getBairro());
			stmt.setString(5, modelLogin.getLocalidade());
			stmt.setString(6, modelLogin.getUf());
			stmt.executeUpdate();
			connection.commit();
			

		} else {
			String sql = "update model_login set nome = ?, email = ?, login = ?, senha = ?, perfil = ?, sexo = ?, data_nascimento = ?, renda_mensal = ? where id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, modelLogin.getNome());
			stmt.setString(2, modelLogin.getEmail());
			stmt.setString(3, modelLogin.getLogin());
			stmt.setString(4, modelLogin.getSenha());
			stmt.setString(5, modelLogin.getPerfil());
			stmt.setString(6, modelLogin.getSexo());
			stmt.setDate(7, modelLogin.getDataNascimento());
			stmt.setDouble(8, modelLogin.getRendaMensal());
			stmt.setLong(9, modelLogin.getId());
			stmt.executeUpdate();
			connection.commit();

			if (modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
				sql = "update model_login set fotouser = ?, extensaofotouser = ? where id = ?";
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, modelLogin.getFotoUser());
				stmt.setString(2, modelLogin.getExtensaoFotoUser());
				stmt.setLong(3, modelLogin.getId());
				stmt.executeUpdate();
				connection.commit();
			}
			
			sql = "update model_login set cep = ?, logradouro = ?, numero = ?, bairro = ?, localidade = ?, uf = ? where login = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, modelLogin.getCep());
			stmt.setString(2, modelLogin.getLogradouro());
			stmt.setString(3, modelLogin.getNumero());
			stmt.setString(4, modelLogin.getBairro());
			stmt.setString(5, modelLogin.getLocalidade());
			stmt.setString(6, modelLogin.getUf());
			stmt.setString(7, modelLogin.getLogin());
			stmt.executeUpdate();
			connection.commit();
		}
		return this.consultaUsuario(modelLogin.getLogin());
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
				modelLogin.setPerfil(resultado.getString("perfil"));
				modelLogin.setSexo(resultado.getString("sexo"));
				modelLogin.setAdmin(resultado.getBoolean("useradmin"));
				modelLogin.setFotoUser(resultado.getString("fotouser"));
				
			}		
			return modelLogin;		
	}

	public ModelLogin consultaUsuarioPorId(String id) throws SQLException {
		ModelLogin modelLogin = new ModelLogin();
		String sql = "select * from model_login where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, Long.parseLong(id));
		ResultSet resultado = stmt.executeQuery();
		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));			
			modelLogin.setDataNascimento(resultado.getDate("data_nascimento"));
			modelLogin.setFotoUser(resultado.getString("fotouser"));
			modelLogin.setExtensaoFotoUser(resultado.getString("extensaofotouser"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setNumero(resultado.getString("numero"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setRendaMensal(resultado.getDouble("renda_mensal"));
		}
		return modelLogin;
	}

	public List<ModelLogin> consultaUsuariosListaPaginada(Integer pagina) throws SQLException {
		List<ModelLogin> lista = new ArrayList<ModelLogin>();
		String sql = "select * from model_login where useradmin is false order by nome offset ? limit 2";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, pagina);
		ResultSet resultado = stmt.executeQuery();
		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));

			lista.add(modelLogin);
		}
		return lista;
	}
	
	public List<ModelLogin> consultaUsuariosLista() throws SQLException {
		List<ModelLogin> lista = new ArrayList<ModelLogin>();
		String sql = "select * from model_login where useradmin is false";
		PreparedStatement stmt = connection.prepareStatement(sql);		
		ResultSet resultado = stmt.executeQuery();
		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setDataNascimento(resultado.getDate("data_nascimento"));
			modelLogin.setRendaMensal(resultado.getDouble("renda_mensal"));
			lista.add(modelLogin);
		}
		return lista;
	}
	
	public List<ModelLogin> consultaUsuariosLista(Long userLogado) throws SQLException {
		List<ModelLogin> lista = new ArrayList<ModelLogin>();
		String sql = "select * from model_login where useradmin is false and usuario_id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);		
		stmt.setLong(1, userLogado);
		ResultSet resultado = stmt.executeQuery();
		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setDataNascimento(resultado.getDate("data_nascimento"));
			modelLogin.setRendaMensal(resultado.getDouble("renda_mensal"));
			modelLogin.setListaTelefones(daoTelefoneRepository.listaFones(modelLogin.getId()));

			lista.add(modelLogin);
		}
		return lista;
	}
	
	public List<ModelLogin> consultaUsuariosLista(Long userLogado, Date dataInicial, Date dataFinal) throws SQLException {
		List<ModelLogin> lista = new ArrayList<ModelLogin>();
		String sql = "select * from model_login where useradmin is false and usuario_id = ? and data_nascimento >= ? and data_nascimento <= ?";
		PreparedStatement stmt = connection.prepareStatement(sql);		
		stmt.setLong(1, userLogado);		
		stmt.setDate(2, dataInicial);
		stmt.setDate(3, dataFinal);
		
		ResultSet resultado = stmt.executeQuery();
		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setDataNascimento(resultado.getDate("data_nascimento"));
			modelLogin.setRendaMensal(resultado.getDouble("renda_mensal"));
			modelLogin.setListaTelefones(daoTelefoneRepository.listaFones(modelLogin.getId()));

			lista.add(modelLogin);
		}
		return lista;
	}

	public boolean validarLogin(ModelLogin modelLogin) throws Exception {
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper(?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, modelLogin.getLogin());
		ResultSet resultado = stmt.executeQuery();

		if (resultado.next()) {
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

	public List<ModelLogin> consultaUsuarios(String nome) throws SQLException {
		List<ModelLogin> lista = new ArrayList<ModelLogin>();
		String sql = "select * from model_login where upper(login) like upper(?) and useradmin is false";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, "%" + nome + "%");
		ResultSet resultado = stmt.executeQuery();
		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin(resultado.getLong("id"), resultado.getString("nome"),
					resultado.getString("email"), resultado.getString("login"), resultado.getString("perfil"),
					resultado.getString("sexo"));
			lista.add(modelLogin);
		}
		return lista;
	}
	
	public int totalPaginas() throws SQLException {
		String sql = "select count(1) as total from model_login";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet resultado = stmt.executeQuery();
		resultado.next();
		Double total = resultado.getDouble("total");
		Double porPagina = 2.0;
		Double pagina = total / porPagina;
		Double resto = pagina % 2;
		
		if (resto > 0) {
			pagina ++;
		}
		return pagina.intValue();
	}
	
	public SalarioGraficoBean montarGraficoMediaSalarial(Long userLogado) throws SQLException {
		List<String> cargos = new ArrayList<String>();
		List<Double> salarios = new ArrayList<Double>();
		String sql = "select avg(renda_mensal) as media_salarial, perfil from model_login where usuario_id = ? group by perfil";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, userLogado);
		ResultSet resultado = stmt.executeQuery();
		SalarioGraficoBean salarioGraficoBean = new SalarioGraficoBean();
		while (resultado.next()) {
			Double  medial_salarial = resultado.getDouble("media_salarial");
			String perfil = resultado.getString("perfil");
			cargos.add(perfil);
			salarios.add(medial_salarial);
		}
		salarioGraficoBean.setCargos(cargos);
		salarioGraficoBean.setSalarios(salarios);
		return salarioGraficoBean;
	}
	
	public SalarioGraficoBean montarGraficoMediaSalarial(Long userLogado, Date dataInicial, Date dataFinal) throws SQLException {
		List<String> cargos = new ArrayList<String>();
		List<Double> salarios = new ArrayList<Double>();
		String sql = "select avg(renda_mensal) as media_salarial, perfil from model_login where usuario_id = ? and data_nascimento >= ? and data_nascimento <= ? group by perfil";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, userLogado);
		stmt.setDate(2, dataInicial);
		stmt.setDate(3, dataFinal);		
		ResultSet resultado = stmt.executeQuery();
		SalarioGraficoBean salarioGraficoBean = new SalarioGraficoBean();
		while (resultado.next()) {
			Double  medial_salarial = resultado.getDouble("media_salarial");
			String perfil = resultado.getString("perfil");
			cargos.add(perfil);
			salarios.add(medial_salarial);
		}
		salarioGraficoBean.setCargos(cargos);
		salarioGraficoBean.setSalarios(salarios);
		return salarioGraficoBean;
	}
}
