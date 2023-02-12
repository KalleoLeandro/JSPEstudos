/**
 * 
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

