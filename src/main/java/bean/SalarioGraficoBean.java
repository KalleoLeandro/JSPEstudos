/**
 * 
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe para armazenar o DTO SalarioGraficoBean
 * @author Kalleo Leandro dos Santos Leal
 * @version 1.0
 * @since 22/02/2023
 */

public class SalarioGraficoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	List<String> cargos = new ArrayList<String>();
	List<Double> salarios = new ArrayList<Double>();
	
	public List<String> getCargos() {
		return cargos;
	}
	public void setCargos(List<String> cargos) {
		this.cargos = cargos;
	}
	public List<Double> getSalarios() {
		return salarios;
	}
	public void setSalarios(List<Double> salarios) {
		this.salarios = salarios;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cargos, salarios);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalarioGraficoBean other = (SalarioGraficoBean) obj;
		return Objects.equals(cargos, other.cargos) && Objects.equals(salarios, other.salarios);
	}
}
