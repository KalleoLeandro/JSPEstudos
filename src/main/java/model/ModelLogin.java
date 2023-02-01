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
	
	private String login;
	private String senha;
	
	public ModelLogin() {
		
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
}
