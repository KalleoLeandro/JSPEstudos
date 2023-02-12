<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Tela de erros</title>
</head>
<body>
	<h1>Mensagem de erro, entre em contato com a equipe de suporte do sistema</h1>
	<span style="color:red;"><%
		out.print(request.getAttribute("msg")); 
	%></span>
</body>
</html>