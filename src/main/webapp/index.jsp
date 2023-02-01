<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Primeira Página</title>
</head>
<body>
	<h1>Bem vindo ao curso JSP</h1>
	<form action="ServletLogin" method="post">
		<input type="hidden" value="<%=request.getParameter("url")%>" name="url">
		<table>
			<tr>
				<td><input name="login" type="text"></td>
			</tr>
			<tr>
				<td><input name="senha" type="password"></td>
			</tr>
			<tr>
				<td><button type="submit">Enviar</button></td>
			</tr>
		</table>		
	</form>
	<h4 style="color:red;">${msg}</h4>
</body>
</html>