jQuery(function($) {
    
    var id_sesion = Math.floor(Math.random() * 100000000);

//Botón de reinicio
$(document).on("click", "#botonReiniciar", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
               
               $('#botonYes').prop('disabled', false);
               $('#botonNo').prop('disabled', false);
               
               var data = {"respuesta" : "reiniciar", "id_sesion" : id_sesion};
               $.post("servidor", data, function(responseJson) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                    $.each(responseJson, function(index, item) { // Iterate over the JSON array.
                         if(index == "pregunta"){
                             $("#pregunta").text(item);
                             $("#borrego6").hide();
                             $("#borrego1").show();
                         }           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                    });
                    
                });
            });

$(document).on("click", "#botonAgregar", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
               
               $('#botonYes').prop('disabled', true);
               $('#botonNo').prop('disabled', true);
               
               var personaje = $("#nombrePersonaje").val();
               var pregunta = $("#preguntaPersonaje").val();
               
               var data = {"respuesta" : "aprender", "nombrePersonaje" : personaje, "preguntaPersonaje" : pregunta, "id_sesion" : id_sesion};
               $.post("servidor", data, function(responseJson) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                    $.each(responseJson, function(index, item) { // Iterate over the JSON array.
                         if(index == "agradecer")            $("#pregunta").text(item);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                    });
                    
                });
            });
            

$(document).on("click", "#botonYes", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
               var data = {"respuesta" : "yes", "id_sesion" : id_sesion};
               $.post("servidor", data, function(responseJson) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                    $.each(responseJson, function(index, item) { // Iterate over the JSON array.
                        if(index == "pregunta"){
                            $("#pregunta").text(item);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                            
                            if($("#pregunta").text() == "¡Sí! ¡Gané!"){
                                $("#borrego6").show();
                                $("#borrego1, #borrego2, #borrego3, #borrego4, #borrego5").hide();                                
                            }
                        }  
                         else{
                             $("#pregunta").text("¿Es " + item + "?");
                            $("#borrego5").show();
                            $("#borrego1, #borrego2, #borrego3, #borrego4, #borrego6").hide();                             
                         }
                    });
                    
                });
            });

     $(document).on("click", "#botonNo", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
                var data = {"respuesta" : "no", "id_sesion" : id_sesion};
                $.post("servidor", data, function(responseJson) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                    $.each(responseJson, function(index, item) { // Iterate over the JSON array.
                         if(index == "pregunta")            $("#pregunta").text(item);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                         else if(index == "respuesta"){
                             $("#pregunta").text("¿Es " + item + "?");
                             $("#borrego5").show();
                             $("#borrego1, #borrego2, #borrego3, #borrego4, #borrego6").hide();
                         } 
                         else if(index == "aprender")       
                         {
                             $("#pregunta").text("¡Me ganaste! ¿Quién es?");
                             $("#borrego3").show();
                             $("#borrego1, #borrego2, #borrego4, #borrego5, #borrego6").hide();
                             setTimeout(
                                function(){
                                    $("#imgFondo, #imgDialogo, #pregunta, #borrego1, #borrego2, #borrego3, #borrego4, #borrego5, #borrego6, #botonYes, #botonNo").hide();
                                    $("#formaNuevoPersonaje").show();
                                    $("#textoFormaNuevoPersonaje").text(item);
                             }, 1500);
                             $("#nombrePersonaje, #preguntaPersonaje").val("");
                        }
                    });
                });
            });

	//#main-slider
	$(function(){
		$('#main-slider.carousel').carousel({
			interval: 8000
		});
	});

	$( '.centered' ).each(function( e ) {
		$(this).css('margin-top',  ($('#main-slider').height() - $(this).height())/2);
	});

	$(window).resize(function(){
		$( '.centered' ).each(function( e ) {
			$(this).css('margin-top',  ($('#main-slider').height() - $(this).height())/2);
		});
	});
});