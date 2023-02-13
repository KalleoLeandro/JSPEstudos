package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import model.ModelLogin;

/**
 * Classe Servlet para Usuario
 * @author Kalleo Leandro dos Santos Leal
 * @version 1.0
 * @since 07/02/2023
 * 
 * **/

@WebServlet(urlPatterns = {"/ServletUsuarioController"})
public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
    
    public ServletUsuarioController() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";
		response.setCharacterEncoding("UTF-8");
		try {
			String acao = request.getParameter("acao");
			if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				String userId = request.getParameter("id");
				daoUsuarioRepository.deletarUsuario(userId);
				msg = "Deletado com sucesso!";								
				
				response.getWriter().write("Excluído com successo!");
				
				//Redirecionamento com Servlet, não serve para ajax
				/*
				request.setAttribute("msg", msg);
				redicionar.forward(request, response);
				*/
				
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUser")) {
				String nomeBusca = request.getParameter("nomeBusca");
				List<ModelLogin> listaRetorno = daoUsuarioRepository.consultaUsuarios(nomeBusca);				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(listaRetorno);
				response.getWriter().write(json);
			} else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserPorId")) {
				String id = request.getParameter("id");
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioPorId(id);
				RequestDispatcher redicionar =  request.getRequestDispatcher("principal/usuario.jsp");
				request.setAttribute("modelLogin", modelLogin);
				request.setAttribute("msg", "Usuário em edição");
				redicionar.forward(request, response);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUsers")) {
				List<ModelLogin> listaRetorno = daoUsuarioRepository.consultaUsuariosLista();				
				RequestDispatcher redicionar = request.getRequestDispatcher("principal/usuario.jsp");
				request.setAttribute("listaLogins", listaRetorno);				
				redicionar.forward(request, response);

			}
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redicionar =  request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redicionar.forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try {		
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");		
			String senha = request.getParameter("senha");
			String msg = "";
			
			ModelLogin modelLogin = new ModelLogin(id != null && !id.isEmpty() ? Long.parseLong(id) : null,nome,email,login,senha);
			
			if(daoUsuarioRepository.validarLogin(modelLogin) && modelLogin.getId() == null) {
				msg = "Já existe usuário com o mesmo login, informe outro login";
			} else {				
				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin);
				if(modelLogin.isNovo()) {
					msg = "Gravado com sucesso!";	
				} else {					
					msg = "Atualizado com sucesso!";
				}
			}
			RequestDispatcher redicionar =  request.getRequestDispatcher("principal/usuario.jsp");
			request.setAttribute("modelLogin", modelLogin);
			request.setAttribute("msg", msg);
			redicionar.forward(request, response);						
			
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redicionar =  request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redicionar.forward(request, response);
		}
	}

}
