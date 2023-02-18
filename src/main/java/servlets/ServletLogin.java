package servlets;

import java.io.IOException;

import dao.DAOLoginRepository;
import dao.DAOUsuarioRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ModelLogin;

/**
 * Classe Servlet para Login
 * @author Kalleo Leandro dos Santos Leal
 * @version 1.0
 * @since 31/01/2023
 * 
 * **/

@WebServlet(urlPatterns = {"/principal/ServletLogin" , "/ServletLogin"}) /*Mapeamento de URL que vem da tela*/
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

    public ServletLogin() {               
    }

    /*Recebe os parâmetros em parâmetros da requisição*/

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate();
			RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
			redirecionar.forward(request, response);
		} else {
			doPost(request,response);
		}
		
		
	}
	
	/*Recebe os parâmetros do corpo da requisição*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		try {
			if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
				
				ModelLogin modelLogin = new ModelLogin(login,senha);
				if(daoLoginRepository.validarAutenticacao(modelLogin)) {
					modelLogin = daoUsuarioRepository.consultaUsuario(modelLogin.getLogin());
					request.getSession().setAttribute("usuario", modelLogin.getLogin().toUpperCase());
					request.getSession().setAttribute("isAdmin", modelLogin.isAdmin());
					request.getSession().setAttribute("imagemUser", modelLogin.getFotoUser());
					
					if(url == null || url.equals("null")) {
						url = "principal/principal.jsp";
					}
					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);
				} else {
					
					RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
					request.setAttribute("msg", "Login e/ou senha inválido(s)");
					redirecionar.forward(request, response);
				}
			} else {
					
					RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
					request.setAttribute("msg", "Informe o login e senha");
					redirecionar.forward(request, response);
					}			
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}		
	}
}
