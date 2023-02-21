/**
 * 
 */
package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * Classe contendo atributos e métodos do objeto Login
 * @author Kalleo Leandro dos Santos Leal
 * @version 1.0
 * @since 31/01/2023
 */


public class ModelLogin implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private String login;
	private String senha;	
	private boolean isAdmin;
	private String perfil;	
	private String sexo;
	
	private String fotoUser;
	private String extensaoFotoUser;
	
	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
	private String numero;
	
	private Date dataNascimento;
	
	private Double rendaMensal;
	
	
	public ModelLogin() {
		
	}


	public ModelLogin(Long id,String nome, String email, String login, String senha, String perfil, String sexo, Double rendaMensal) {		
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
		this.sexo = sexo;
		this.rendaMensal = rendaMensal;
	}
	
	public ModelLogin(Long id,String nome, String email, String login, String perfil, String sexo) {		
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.login = login;		
		this.perfil = perfil;
		this.sexo = sexo;
	}
	
	public ModelLogin(String login, String senha) {		
		this.login = login;
		this.senha = senha;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}


	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public String getPerfil() {
		return perfil;
	}


	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public String getFotoUser() {
		return fotoUser;
	}


	public void setFotoUser(String fotoUser) {
		this.fotoUser = fotoUser;
	}


	public String getExtensaoFotoUser() {
		return extensaoFotoUser;
	}


	public void setExtensaoFotoUser(String extensaoFotoUser) {
		this.extensaoFotoUser = extensaoFotoUser;
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public String getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getLocalidade() {
		return localidade;
	}


	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}


	public String getUf() {
		return uf;
	}


	public void setUf(String uf) {
		this.uf = uf;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public Double getRendaMensal() {
		return rendaMensal;
	}


	public void setRendaMensal(Double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}


	@Override
	public String toString() {
		return "Login: " + this.login + "\n"
			+  "Senha: " + this.senha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(login, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelLogin other = (ModelLogin) obj;
		return Objects.equals(login, other.login) && Objects.equals(senha, other.senha);
	}
	
	public boolean isNovo() {
		if(this.id == null) {
			return true;
		} else if(this.id != null && this.id > 0) {
			return false;
		}
		return id == null;
	}
}
