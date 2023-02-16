<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">

<jsp:include page="head.jsp"></jsp:include>

<body>
	<jsp:include page="theme-loader.jsp"></jsp:include>
	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">
			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">
					<jsp:include page="navbar-main-menu.jsp"></jsp:include>
					<div class="pcoded-content">
						<!-- Page-header start -->
						<jsp:include page="page-header.jsp"></jsp:include>
						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">
										<div class="row">
											<!--Conteúdo da página usuário-->
											<div class="col-md-6">
												<div class="card">
													<div class="card-header">
														<h5>Cadastro de Usuários</h5>
														<!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
													</div>
													<div class="card-block">
														<form id="form-user" class="form-material" enctype="multipart/form-data"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="post">
															<input type="hidden" name="acao" value="">
															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	value="${modelLogin.id}" class="form-control"
																	readonly="readonly"> <span class="form-bar"></span>
																<label class="float-label text-dark">ID:</label>
															</div>
															<div
																class="form-group form-default input-group mb-4">
																<div class="input-group-prepend">
																	<img alt="Imagem User" id="fotoUser" src="" width="70px;">
																</div>																
																<input type="file" accept="image/*" onchange="visualizarImagem('fotoUser', 'selecionarImagem');" class="form-control-file" name="selecionarImagem" id="selecionarImagem">
															</div>															
															<div
																class="form-group form-default form-static-label form-font-color">
																<input type="text" name="nome" id="nome"
																	value="${modelLogin.nome}" class="form-control"
																	required="required" autocomplete="off"> <span
																	class="form-bar"></span> <label
																	class="float-label text-dark">Nome</label>
															</div>
															<div
																class="form-group form-default form-static-label form-font-color">
																<input type="text" name="email" id="email"
																	value="${modelLogin.email}" class="form-control"
																	required="required" autocomplete="off"> <span
																	class="form-bar"></span> <label
																	class="float-label text-dark">Email</label>
															</div>
															<div
																class="form-group form-default form-static-label form-font-color">
																<select class="form-control" aria-label="Perfil"
																	name="perfil" id="perfil">
																	<option value="NOCHOOSE" disabled="disabled"
																		selected="selected">[Selecione o Perfil]</option>
																	<option value="ADMINISTRADOR"
																		<%try {
	ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");
	if (modelLogin != null && modelLogin.getPerfil().equals("ADMINISTRADOR")) {
		out.print(" ");
		out.print("selected=\"selected\"");
		out.print(" ");
	}
} catch (Exception e) {
	System.out.print("Usuário não selecionado ainda!");
}%>>Administrador</option>
																	<option value="USUARIO"
																		<%try {
	ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");
	modelLogin = (ModelLogin) request.getAttribute("modelLogin");
	if (modelLogin != null && modelLogin.getPerfil().equals("USUARIO")) {
		out.print(" ");
		out.print("selected=\"selected\"");
		out.print(" ");
	}
} catch (Exception e) {
	System.out.print("Usuário não selecionado ainda!");
}%>>Usuário</option>
																</select> <span class="form-bar"></span> <label
																	class="float-label text-dark">Perfil</label>
															</div>
															<div
																class="form-group form-default form-static-label form-font-color">
																<input type="text" name="login" id="login"
																	value="${modelLogin.login}" class="form-control"
																	required="required" autocomplete="off"> <span
																	class="form-bar"></span> <label
																	class="float-label text-dark">Login</label>
															</div>
															<div
																class="form-group form-default form-static-label form-font-color">
																<input type="password" name="senha" id="senha"
																	value="${modelLogin.senha}" class="form-control"
																	required="required" autocomplete="off"> <span
																	class="form-bar"></span> <label
																	class="float-label text-dark">Senha</label>
															</div>
															<div class="form-group" id="selecaoSexo">
																<div class="form-check">
																	<input class="form-check-input" type="radio"
																		name="sexo" id="radioMasculino" checked="checked" value="Masculino"
																		<% try{
																			ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");
																			if(modelLogin!= null && modelLogin.getSexo().equals("Masculino")){
																				out.print(" ");
																				out.print("checked=\"checked\"");
																				out.print(" ");
																			}
																			}catch(Exception e){
																				e.printStackTrace();
																			}																	
																			
																		%>>										 
																		<label class="form-check-label"
																		for="radioMasculino"> Masculino </label>
																</div>
																<div class="form-check">
																	<input class="form-check-input" type="radio"
																		name="sexo" id="radioFeminino" value="Feminino"
																		<% try{
																				ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");
																				if(modelLogin!= null && modelLogin.getSexo().equals("Feminino")){
																					out.print(" ");
																					out.print("checked=\"checked\"");
																					out.print(" ");
																				}
																			}catch(Exception e){
																				e.printStackTrace();
																			}																			
																		%>>
																	<label class="form-check-label" for="radioFeminino">
																		Feminino </label>
																</div>
															</div>
															<button type="button"
																class="btn btn-primary  waves-effect waves-light"
																onclick="limparForm();">Novo</button>
															<button class="btn btn-success  waves-effect waves-light">Salvar</button>
															<button type="button" id="excluir"
																class="btn btn-info  waves-effect waves-light"
																onclick="deletarUsuarioComAjax();">Excluir</button>
															<button type="button" class="btn btn-warning"
																data-toggle="modal" data-target="#modalExemplo">Pesquisar</button>
														</form>
													</div>
												</div>
											</div>
										</div>
										<span style="color: red;" id="msg">${msg}</span>
										<table class="table" id="usuariosCadastrados">
											<thead>
												<tr>
													<th scope="col">ID</th>
													<th scope="col">Nome</th>
													<th scope="col">Login</th>
													<th scope="col">Selecionar</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${listaLogins}" var="ml">
													<tr>
														<td><c:out value="${ml.id}"></c:out></td>
														<td><c:out value="${ml.nome}"></c:out></td>
														<td><c:out value="${ml.login}"></c:out></td>
														<td><a class="btn btn-success"
															href="<%=request.getContextPath()%>/ServletUsuarioController?acao=buscarUserPorId&id=${ml.id}">Ver</a></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="modalExemplo" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Pesquisa de
						Usuários</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!--Busca-->
					<div class="input-group mb-3">
						<input type="text" id="nomeBusca" class="form-control"
							placeholder="Nome" aria-label="Nome"
							aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn btn-success" type="button"
								onclick="buscarUsuarios();">Buscar</button>
						</div>
					</div>
				</div>
				<!--Tabela de resultados -->
				<div style="height: 300px; overflow-y: scroll;">
					<table class="table" id="tabelaResultados">
						<thead>
							<tr>
								<th scope="col">ID</th>
								<th scope="col">Nome</th>
								<th scope="col">Selecionar</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				<span id="resultados"></span>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="javascript-file.jsp"></jsp:include>
</body>
</html>
