$(document).ready(function() {
	$("#buscar").click(function(){
		var assunto = $("#assunto").val();
		if(assunto == ""){
			$("#msg").html("");
			$("#msg").append('<div class="alert alert-error span4 fade in">'+
				  '<button class="close" data-dismiss="alert">x</button>'+
				  'Por Favor, Preencha o campo de Assunto.'+
				  '</div>');
			
		}else{
			window.location="/comente-sobre/"+assunto;
		}				 
	});
});