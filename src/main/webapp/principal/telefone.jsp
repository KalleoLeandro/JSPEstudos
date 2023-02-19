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
														<h5>Cadastro de Telefones</h5>
													</div>
													<div class="card-block">
														<form id="form-fone" class="form-material"
															action="<%=request.getContextPath()%>/ServletTelefoneController"
															method="post">
															<div
																class="form-group form-default form-static-label form-font-color">
																<h5 class="card-title">Dados de Telefone</h5>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="idUser" id="idUser"
																	value="${usuario.id}" class="form-control"
																	readonly="readonly"> <span class="form-bar"></span>
																<label class="float-label text-dark">ID User: </label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	value="${usuario.nome}" class="form-control"
																	readonly="readonly"> <span class="form-bar"></span>
																<label class="float-label text-dark">Nome User:
																</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="numero" id="numero"
																	class="form-control"> <span class="form-bar"></span>
																<label class="float-label text-dark">Número </label>
															</div>
															<div class="form-group form-default form-static-label">
																<button
																	class="btn btn-success  waves-effect waves-light">Salvar</button>
															</div>
														</form>

													</div>
												</div>
											</div>
										</div>
										<span style="color: red;" id="msg">${msg}</span>
										<table class="table" id="telefonesCadastrados">
											<thead>
												<tr>
													<th scope="col">ID</th>
													<th scope="col">Número</th>													
													<th scope="col">Ação</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${listaTelefones}" var="tf">
													<tr>
														<td><c:out value="${tf.id}"></c:out></td>														
														<td><c:out value="${tf.numero}"></c:out></td>
														<td><a class="btn btn-danger"
															href="<%=request.getContextPath()%>/ServletTelefoneController?acao=excluir&id=${tf.id}&user=${usuario.id}">Excluir</a></td>
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

	<jsp:include page="javascript-file.jsp"></jsp:include>
</body>
</html>
