jQuery(function($) {

//Botón de reinicio
$(document).on("click", "#botonReiniciar", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
               var data = {"respuesta" : "reiniciar"};
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

/*$(document).on("click", "#botonAgregar", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
               var personaje = $("#nombrePersonaje").text();
               var pregunta = $("#preguntaPersonaje").text();
                var data = {"respuesta" : "aprender", "nombrePersonaje" : personaje, "preguntaPersonaje" : pregunta};
               $.post("servidor", data, function(responseJson) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                    $.each(responseJson, function(index, item) { // Iterate over the JSON array.
                         if(index == "agradecer")            $("#pregunta").text(item);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                    });
                    
                });
            });*/
            

$(document).on("click", "#botonYes", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
               var data = {"respuesta" : "yes"};
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
                var data = {"respuesta" : "no"};
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
                             $("#pregunta").text(item);
                             $("#borrego3").show();
                             $("#borrego1, #borrego2, #borrego4, #borrego5, #borrego6").hide();
                             setTimeout(
                                function(){
                                    $("#imgFondo, #imgDialogo, #pregunta, #borrego1, #borrego2, #borrego3, #borrego4, #borrego5, #borrego6, #botonYes, #botonNo").hide();
                                    $("#formaNuevoPersonaje").show();
                             }, 2000);
                             
        
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

	//portfolio
	/*$(window).load(function(){
		$portfolio_selectors = $('.portfolio-filter >li>a');
		if($portfolio_selectors!='undefined'){
			$portfolio = $('.portfolio-items');
			$portfolio.isotope({
				itemSelector : 'li',
				layoutMode : 'fitRows'
			});
			$portfolio_selectors.on('click', function(){
				$portfolio_selectors.removeClass('active');
				$(this).addClass('active');
				var selector = $(this).attr('data-filter');
				$portfolio.isotope({ filter: selector });
				return false;
			});
		}
	});

	//contact form
	var form = $('.contact-form');
	form.submit(function () {
		$this = $(this);
		$.post($(this).attr('action'), function(data) {
			$this.prev().text(data.message).fadeIn().delay(3000).fadeOut();
		},'json');
		return false;
	});

	//goto top
	$('.gototop').click(function(event) {
		event.preventDefault();
		$('html, body').animate({
			scrollTop: $("body").offset().top
		}, 500);
	});	

	//Pretty Photo
	$("a[rel^='prettyPhoto']").prettyPhoto({
		social_tools: false
	});*/	
});