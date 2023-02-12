<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link href="css/style.css" rel="stylesheet">
<title>Primeira Página</title>
</head>
<body>	
	<div id="form-login">
		<h1>Sistema JSP</h1>
		<form action="<%=request.getContextPath()%>/ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
			<input type="hidden" value="<%=request.getParameter("url")%>"
				name="url">
			<div class="com-md-6">
				<label class="form-label" for="login">Login</label> 
				<input class="form-control form-input" name="login" type="text" placeholder="Digite o login" required>
				<div class="invalid-feedback">Informe o login</div>
				<div class="valid-feedback">Informe o login</div>
			</div>
			<div class="com-md-6">
				<label class="form-label" for="senha">Senha</label> 
				<input class="form-control form-input" name="senha" type="password" placeholder="Digite a senha" required>
				<div class="invalid-feedback">Informe a senha</div>
				<div class="valid-feedback">Informe a senha</div>
			</div>
			<button id="logar" type="submit" value="Logar" class="btn btn-primary">Logar</button>
		</form>
		<h4 style="color: red;">${msg}</h4>
	</div>	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<script src="js/script.js" type="text/javascript"></script>	
</body>
</html>