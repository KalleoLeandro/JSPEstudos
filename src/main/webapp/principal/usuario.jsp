<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
                                                        <form id="form-user" class="form-material" action="<%=request.getContextPath()%>/ServletUsuarioController" method="post">
                                                        	<input type="hidden" name="acao" value="">
                                                        	<div class="form-group form-default form-static-label">
                                                                <input type="text" name="id" id="id" value="${modelLogin.id}" class="form-control" readonly="readonly">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label text-dark">ID:</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label form-font-color">
                                                                <input type="text" name="nome" id="nome" value="${modelLogin.nome}" class="form-control" required="required" autocomplete="off">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label text-dark">Nome</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label form-font-color">
                                                                <input type="text" name="email" id="email" value="${modelLogin.email}" class="form-control" required="required" autocomplete="off">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label text-dark">Email</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label form-font-color">
                                                                <input type="text" name="login" id="login" value="${modelLogin.login}" class="form-control" required="required" autocomplete="off">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label text-dark">Login</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label form-font-color">
                                                                <input type="password" name="senha" id="senha" value="${modelLogin.senha}" class="form-control" required="required" autocomplete="off">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label text-dark">Senha</label>
                                                            </div>     
															<button type="button" class="btn btn-primary  waves-effect waves-light" onclick="limparForm();">Novo</button>
                                                            <button class="btn btn-success  waves-effect waves-light">Salvar</button>                                                       
                                                            <button type="button" class="btn btn-info  waves-effect waves-light" onclick="deletarUsuarioComAjax();">Excluir</button>
                                                            <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#modalExemplo">Pesquisar</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
										</div>
										<span style="color:red;" id="msg">${msg}</span>
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
	<div class="modal fade" id="modalExemplo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de Usuários</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        ...
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
	        <button type="button" class="btn btn-primary"></button>
	      </div>
	    </div>
	  </div>
	</div>
	<jsp:include page="javascript-file.jsp"></jsp:include>
</body>
</html>
