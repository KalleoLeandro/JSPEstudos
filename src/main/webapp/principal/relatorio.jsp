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
														<h5>Relatórios</h5>
														<div class="card-block">
															<form id="form-user" class="form-material"
																enctype="multipart/form-data"
																action="<%=request.getContextPath()%>/ServletUsuarioController"
																method="get">
																<input type="hidden" name="acao" id="acaoRelatorioImprimirTipo"
																	value="imprimirRelatorioUser">
																<div class="form-row align-items-center">
																	<div class="col-md-4">
																		<label for="dataInicial">Data Inicial</label> <input
																			value="${dataInicial}" type="date"
																			class="form-control" id="dataInicial"
																			name="dataInicial">
																	</div>
																	<div class="col-md-4">
																		<label for="dataInicial">Data Final</label> <input
																			value="${dataFinal}" type="date" class="form-control"
																			id="dataFinal" name="dataFinal">
																	</div>
																	<div class="col-md-4">
																		<button type="button" onclick="imprimirHtml();" class="btn btn-primary">Detalhar Dados</button>
																		<button type="button" onclick="imprimirPdf();" class="btn btn-info">Imprimir PDF</button>
																		<button type="button" onclick="imprimirExcel();" class="btn btn-success">Imprimir Excel</button>
																	</div>
																</div>

															</form>
															<table class="table" id="usuariosCadastrados">
																<thead>
																	<tr>
																		<th scope="col">ID</th>
																		<th scope="col">Nome</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${listaUser}" var="ml">
																		<tr>
																			<td><c:out value="${ml.id}"></c:out></td>
																			<td><c:out value="${ml.nome}"></c:out></td>



																			<c:forEach items="${ml.listaTelefones}" var="fone">
																				<td><c:out
																						value="${fone.numero}"></c:out></td>

																			</c:forEach>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														</div>
													</div>
												</div>
											</div>
										</div>
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
