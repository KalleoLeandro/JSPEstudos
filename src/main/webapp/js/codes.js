/**
 * Scripts JS
 * @author Kalleo Leandro dos Santos Leal
 * @version 1.0
 * @since 11/02/2023
 */

function limparForm(){
	var elements = document.getElementById('form-user').elements;
	for(p = 0;p < elements.length;p++){
		elements[p].value = "";
	}	 
}

function deletarUsuarioComAjax(){
	if(confirm("Deseja realmente excluir este usuário?")){
		var urlAction = document.getElementById('form-user').action;
		var idUser = document.getElementById('id').value;
		debugger;
		$.ajax({
			method: "GET",
			url: urlAction,
			data: "id=" + idUser + "&acao=deletar",
			success: function(response){
				limparForm();
				alert(response);
				document.getElementById('msg').textContent = response;
			}		
		}).fail(function(xhr, status, errorThrow){
			alert("Erro ao deletar o usuário por id" + xhr.responseText);
		});	
	}
}

function deletarUsuario(){	
	if(confirm("Deseja realmente excluir este usuário?")){
		document.getElementById('form-user').method = 'get';
		document.getElementsByName('acao')[0].value = 'deletar';
		document.getElementById('form-user').submit();	
	}	
}

function buscarUsuarios(){
	var elemento = document.getElementById('nomeBusca').value;
	var urlAction = document.getElementById('form-user').action;
	if(elemento != null && elemento.trim() != ''){
		$.ajax({
			method: "GET",
			url: urlAction,
			data: "nomeBusca=" + elemento + "&acao=buscarUser",
			success: function(response){
				var json = JSON.parse(response);
				debugger;
				$('#tabelaResultados > tbody > tr').remove();
				for(var i = 0; i < json.length; i++ ){
					$('#tabelaResultados > tbody').append('<tr>' + 
					'<td>'+ json[i].id + '</td>' + 
					'<td>' + json[i].nome + '</td>' + 
					'<td><button type="button" class="btn btn-info" onclick="verEditar(' + json[i].id + ')">Ver</button></td>' + 
					'</tr>');
				}	
				document.getElementById('resultados').textContent = "Resultados: " + json.length;		
											
			}		
		}).fail(function(xhr, status, errorThrow){
			alert("Erro ao buscar usuários por nome: " + xhr.responseText);
		});	
	}	
}

function verEditar(id){
		var urlAction = document.getElementById('form-user').action;
		window.location.href = urlAction + '?acao=buscarUserPorId&id='+id;		
}