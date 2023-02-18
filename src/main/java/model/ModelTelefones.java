/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe contendo atributos e métodos do objeto Telefones
 * @author Kalleo Leandro dos Santos Leal
 * @version 1.0
 * @since 18/02/2023
 */


public class ModelTelefones implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String numero;
	private Long usuario_id;
	private Long usuario_cadastro_id;
	
	public ModelTelefones() {
		
	}

	public ModelTelefones(Long id, String numero, Long usuario_id, Long usuario_cadastro_id) {		
		this.id = id;
		this.numero = numero;
		this.usuario_id = usuario_id;
		this.usuario_cadastro_id = usuario_cadastro_id;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Long getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
	}

	public Long getUsuario_cadastro_id() {
		return usuario_cadastro_id;
	}

	public void setUsuario_cadastro_id(Long usuario_cadastro_id) {
		this.usuario_cadastro_id = usuario_cadastro_id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelTelefones other = (ModelTelefones) obj;
		return Objects.equals(id, other.id);
	}
}
