/**
 * 
 */
package model;

import java.io.Serializable;
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
	
	
	
	
	public ModelLogin() {
		
	}


	public ModelLogin(Long id,String nome, String email, String login, String senha, String perfil, String sexo) {		
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
		this.sexo = sexo;
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
