package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

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

@MultipartConfig
@WebServlet(urlPatterns = {"/ServletUsuarioController"})
public class ServletUsuarioController extends ServletGenericUtil {
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
				List<ModelLogin> listaRetorno = daoUsuarioRepository.consultaUsuariosLista();
				request.setAttribute("listaLogins", listaRetorno);
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
				List<ModelLogin> listaRetorno = daoUsuarioRepository.consultaUsuariosLista();
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPaginas());
				request.setAttribute("listaLogins", listaRetorno);
				RequestDispatcher redicionar =  request.getRequestDispatcher("principal/usuario.jsp");				
				request.setAttribute("modelLogin", modelLogin);
				request.setAttribute("msg", "Usuário em edição");
				redicionar.forward(request, response);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUsers")) {
				List<ModelLogin> listaRetorno = daoUsuarioRepository.consultaUsuariosListaPaginada(0);				
				RequestDispatcher redicionar = request.getRequestDispatcher("principal/usuario.jsp");
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPaginas());
				request.setAttribute("listaLogins", listaRetorno);
				request.setAttribute("NOCHOOSE", "perfil");
				redicionar.forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")) {
				String idUser = request.getParameter("id");
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioPorId(idUser);
				if(modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
					response.setHeader("Content-Disposition", "attachment;filename=arquivo." + modelLogin.getExtensaoFotoUser());
					response.getOutputStream().write(new Base64().decodeBase64(modelLogin.getFotoUser().split("\\,")[1]));
				}
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {
				 Integer offset = Integer.parseInt(request.getParameter("pagina"));
				 
				 List<ModelLogin> listaRetorno = daoUsuarioRepository.consultaUsuariosListaPaginada(offset);
				 
				 request.setAttribute("listaLogins", listaRetorno);
			     request.setAttribute("totalPagina", daoUsuarioRepository.totalPaginas());
				 request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redicionar =  request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redicionar.forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setCharacterEncoding("UTF-8");
		try {		
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");		
			String senha = request.getParameter("senha");
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");
			String msg = "";
			String cep = request.getParameter("cep");;
			String logradouro = request.getParameter("logradouro");;
			String numero = request.getParameter("numero");;
			String bairro = request.getParameter("bairro");;
			String localidade = request.getParameter("localidade");;
			String uf = request.getParameter("uf");;
			
			
			
			ModelLogin modelLogin = new ModelLogin(id != null && !id.isEmpty() ? Long.parseLong(id) : null,nome,email,login,senha, perfil,sexo);
			modelLogin.setCep(cep);
			modelLogin.setLogradouro(logradouro);
			modelLogin.setNumero(numero);
			modelLogin.setBairro(bairro);
			modelLogin.setLocalidade(localidade);
			modelLogin.setUf(uf);
			if (ServletFileUpload.isMultipartContent(request)) {
				Part part = request.getPart("selecionarImagem");	
				InputStream in = part.getInputStream();
				byte[] foto = IOUtils.toByteArray(in);
				in.close();				
				String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64," +  new Base64().encodeBase64String(foto);					
				modelLogin.setFotoUser(imagemBase64);
				modelLogin.setExtensaoFotoUser(part.getContentType().split("\\/")[1]);				
			}
			
			if(daoUsuarioRepository.validarLogin(modelLogin) && modelLogin.getId() == null) {
				msg = "Já existe usuário com o mesmo login, informe outro login";
			} else {				
				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin, super.getUserLogado(request));
				if(modelLogin.isNovo()) {
					msg = "Gravado com sucesso!";	
				} else {					
					msg = "Atualizado com sucesso!";
				}
			}
			List<ModelLogin> listaRetorno = daoUsuarioRepository.consultaUsuariosLista();
			request.setAttribute("totalPagina", daoUsuarioRepository.totalPaginas());
			request.setAttribute("listaLogins", listaRetorno);
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
