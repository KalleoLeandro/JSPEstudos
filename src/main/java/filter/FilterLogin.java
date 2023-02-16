package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * Classe de filtro do Login
 * @author Kalleo Leandro dos Santos Leal
 * @version 1.0
 * @since 31/01/2023
 **/


@WebFilter(urlPatterns = {"/principal/*"})
public class FilterLogin extends HttpFilter implements Filter {
     
	private static final long serialVersionUID = 1L;
	
	private static Connection connection;

	public FilterLogin() {
    }
	
	/*Encerra os processos quando o servidor é parado*/
	//Ex.: Mataria os processos de conexão com o banco
	public void destroy() {	
		try {			
			connection.close();						
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}

	/*Intercepta as requisições e dá as respostas no sistema*/
	/*Tudo que fizermos no sistema, dentro do range das urlPatterns, passa por ele*/
	//Ex.: Validação de autenticação, Dar commit e rollback de transações no banco, validar e fazer redirecioamentos específicos
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {			
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			String usuarioLogado = (String) session.getAttribute("usuario");
			
			String urlParaAutenticar = req.getServletPath(); //Url a ser acessada
			
			/*Validar se está logado, senão redireciona para a tela de login*/	
			
			if(usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor realize o login");
				redirecionar.forward(request, response);
				return;/*Caso não esteja logado irá redirecionar para a tela de login e deixará em cache a tela a ser redirecionada*/
			} else {
				chain.doFilter(request, response);
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();	
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			try {				
				connection.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
		}
	}
	
	/*Inicia os processos ou recursos quando o servidor sobe o projeto*/
	//Ex.: Iniciar a conexão com o banco
	public void init(FilterConfig fConfig) throws ServletException {	
		connection = SingleConnectionBanco.getConnection();
	}

}
