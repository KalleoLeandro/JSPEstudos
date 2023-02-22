/**
 * Scripts JS
 * @author Kalleo Leandro dos Santos Leal
 * @version 1.0
 * @since 11/02/2023
 */

$("#numero").keypress(function(event){
	return /\d/.test(String.fromCharCode(event.keyCode));
});

$("#cep").keypress(function(event){
	return /\d/.test(String.fromCharCode(event.keyCode));
});


$(function (){
	$("#rendaMensal").maskMoney({prefix:'R$ ', allowNegative: true, thousands:'.', decimal:',', affixesStay: false});
})

const formatter = new Intl.NumberFormat('pt-BR', {
    currency : 'BRL',
    minimumFractionDigits : 2
});

$("#rendamensal").val(formatter.format($("#rendamensal").val()));

$("#rendamensal").focus();


$( function() {	  
	  $("#dataNascimento #dataInicial #dataFinal").datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'Próximo',
		    prevText: 'Anterior'
		});
} );

function limparForm() {
	var elements = document.getElementById('form-user').elements;
	for (p = 0; p < elements.length; p++) {
		elements[p].value = "";
	}
	if (!document.getElementById('radioMasculino').checked) {
		document.getElementById('radioMasculino').checked = true;
	}
}

function deletarUsuarioComAjax() {
	if (confirm("Deseja realmente excluir este usuário?")) {
		var urlAction = document.getElementById('form-user').action;
		var idUser = document.getElementById('id').value;
		debugger;
		$.ajax({
			method: "GET",
			url: urlAction,
			data: "id=" + idUser + "&acao=deletar",
			success: function(response) {
				limparForm();
				alert(response);
				document.getElementById('msg').textContent = response;
			}
		}).fail(function(xhr, status, errorThrow) {
			alert("Erro ao deletar o usuário por id" + xhr.responseText);
		});
	}
}

function deletarUsuario() {
	if (confirm("Deseja realmente excluir este usuário?")) {
		document.getElementById('form-user').method = 'get';
		document.getElementsByName('acao')[0].value = 'deletar';
		document.getElementById('form-user').submit();
	}
}

function buscarUsuarios() {
	var elemento = document.getElementById('nomeBusca').value;
	var urlAction = document.getElementById('form-user').action;
	if (elemento != null && elemento.trim() != '') {
		$.ajax({
			method: "GET",
			url: urlAction,
			data: "nomeBusca=" + elemento + "&acao=buscarUser",
			success: function(response) {
				var json = JSON.parse(response);
				debugger;
				$('#tabelaResultados > tbody > tr').remove();
				for (var i = 0; i < json.length; i++) {
					$('#tabelaResultados > tbody').append('<tr>' +
						'<td>' + json[i].id + '</td>' +
						'<td>' + json[i].nome + '</td>' +
						'<td><button type="button" class="btn btn-info" onclick="verEditar(' + json[i].id + ')">Ver</button></td>' +
						'</tr>');
				}
				document.getElementById('resultados').textContent = "Resultados: " + json.length;

			}
		}).fail(function(xhr, status, errorThrow) {
			alert("Erro ao buscar usuários por nome: " + xhr.responseText);
		});
	}
}

function verEditar(id) {
	var urlAction = document.getElementById('form-user').action;
	window.location.href = urlAction + '?acao=buscarUserPorId&id=' + id;
}


function visualizarImg(fotoUser, selecionarImagem) {


	var preview = document.getElementById(fotoUser);
	var fileUser = document.getElementById(selecionarImagem).files[0];
	var reader = new FileReader();

	reader.onloadend = function() {
		preview.src = reader.result;
	};

	if (fileUser) {
		reader.readAsDataURL(fileUser);
	} else {
		preview.src = '';
	}
}

function pesquisaCep() {
	var cep = $("#cep").val().replace(/\D/g, '');

	//Verifica se campo cep possui valor informado.
	if (cep != "") {

		//Expressão regular para validar o CEP.
		var validacep = /^[0-9]{8}$/;

		//Valida o formato do CEP.
		if (validacep.test(cep)) {

			//Consulta o webservice viacep.com.br/
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?", function(dados) {				
				if (!("erro" in dados)) {					
					$("#logradouro").val(dados.logradouro);
					$("#bairro").val(dados.bairro);
					$("#localidade").val(dados.localidade);
					$("#uf").val(dados.uf);
				} //end if.
				else {
					alert("CEP não encontrado.");
				}
			});
		} //end if.
		else {
			alert("Formato de CEP inválido.");
		}
	} //end if.
	else {
	}
}

function imprimirHtml(){
	document.getElementById('acaoRelatorioImprimirTipo').value="imprimirRelatorioUser";
	$('#form-user').submit();
}
	
function imprimirPdf(){
	debugger;
	document.getElementById('acaoRelatorioImprimirTipo').value="imprimirRelatorioPDF";
	$('#form-user').submit();
}