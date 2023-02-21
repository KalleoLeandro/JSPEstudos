package servlets;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;
import model.ModelLogin;
import model.ModelTelefones;

/**
 * Classe Servlet para Telefone
 * 
 * @author Kalleo Leandro dos Santos Leal
 * @version 1.0
 * @since 18/02/2023
 * 
 **/

@WebServlet("/ServletTelefoneController")
public class ServletTelefoneController extends ServletGenericUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	private DAOTelefoneRepository daoTelefoneRepository = new DAOTelefoneRepository();

	public ServletTelefoneController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("excluir")) {
				String idFone = request.getParameter("id");
				String idUser = request.getParameter("user");
				daoTelefoneRepository.deletaTelefone(Long.parseLong(idFone));
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioPorId(idUser);
				request.setAttribute("usuario", modelLogin);
				List<ModelTelefones> listaTelefones = daoTelefoneRepository.listaFones(Long.parseLong(idUser));
				request.setAttribute("usuario", modelLogin);
				request.setAttribute("listaTelefones", listaTelefones);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
				return;
			}

			String idUser = request.getParameter("idUser");

			if (idUser != null && !idUser.isEmpty()) {

				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioPorId(idUser);
				request.setAttribute("usuario", modelLogin);
				List<ModelTelefones> listaTelefones = daoTelefoneRepository.listaFones(Long.parseLong(idUser));
				request.setAttribute("usuario", modelLogin);
				request.setAttribute("listaTelefones", listaTelefones);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

			} else {
				List<ModelLogin> listaRetorno = daoUsuarioRepository.consultaUsuariosLista();
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPaginas());
				request.setAttribute("listaLogins", listaRetorno);
				RequestDispatcher redicionar = request.getRequestDispatcher("principal/usuario.jsp");
				redicionar.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String msg = "";
		try {
			String idUser = request.getParameter("idUser");
			String numero = request.getParameter("numero");
			if (!daoTelefoneRepository.existeTelefone(numero, Long.parseLong(idUser))) {
				ModelTelefones modelTelefones = new ModelTelefones(numero, Long.parseLong(idUser));
				modelTelefones.setUsuario_cadastro_id(super.getUserLogado(request));

				daoTelefoneRepository.gravaTelefone(modelTelefones);
				msg = "Telefone gravado com sucesso!";
			} else {
				msg = "Este telefone já está cadastrado!";
			}

			request.setAttribute("msg", msg);
			List<ModelTelefones> listaTelefones = daoTelefoneRepository.listaFones(Long.parseLong(idUser));
			ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioPorId(idUser);
			request.setAttribute("usuario", modelLogin);
			request.setAttribute("listaTelefones", listaTelefones);
			request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
