/**
 * 
 */
package servlets;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnectionBanco;
import dao.DAOUsuarioRepository;

/**
 * @author Kalleo
 *
 */
public class ServletGenericUtil extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Connection connection;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();;
	
	public ServletGenericUtil() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public Long getUserLogado(HttpServletRequest request) throws SQLException {
		HttpSession session = request.getSession();
		String usuarioLogado = (String) session.getAttribute("usuario");		
		return daoUsuarioRepository.consultaUsuario(usuarioLogado).getId();
	}
	

}
