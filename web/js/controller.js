/*$(document).ready(function(){

	$("#borrego2").hide();
	
	$("#botonYes").click(function(){
		console.log("boton click");
		$("#borrego1-1").hide();
		$("#borrego2").show();
		$("#pregunta").text("¿Es estudiante?");
	});
	$("#botonNo").click(function(){
		console.log("boton click");
		$("#borrego1").hide();
		$("#borrego2").show();
		$("#pregunta").text("¿Es profesor?");
	});
});*/

$(document).ready(function(){
	
	$("#formaNuevoPersonaje").hide();
        $("#borrego2, #borrego3, #borrego4, #borrego5, #borrego6").hide();
        $("#pregunta").text("¿Es hombre?");
	
	$("#botonYes").click(function(){
		console.log("boton click NO");
		$("#borrego1").hide();
		$("#borrego3").hide();
		$("#borrego2").show();
		//$("#pregunta").text("¿Es estudiante?");
	});
	$("#botonNo").click(function(){
		console.log("boton click YES");
		$("#borrego1").hide();
		$("#borrego2").hide();
		$("#borrego3").show();
		//$("#pregunta").text("¿Es profesor?");
	});
	
	$("#botonAgregar").click(function(){
		console.log("boton click Agregar");
                $("#pregunta").text("¿Es hombre?");
		$("#imgFondo, #imgDialogo, #pregunta, #borrego1, #botonYes, #botonNo, #formaNuevoPersonaje").show();
		$("#formaNuevoPersonaje").hide();
	});
	
	//Cuando adivino
	/*$("#borrego1").hide();
	$("#borrego2").hide();
	$("#borrego3").hide();
	$("#borrego4").show();*/
	
        /*var s = $("#pregunta");
        console.log(s);
	if(s == "¡Me ganaste! \n ¿Quién es?"){
	$("#imgFondo, #imgDialogo, #pregunta, #borrego1, #borrego2, #borrego3, #borrego4, #botonYes, #botonNo").hide();
	$("#formaNuevoPersonaje").show();
        }*/
});