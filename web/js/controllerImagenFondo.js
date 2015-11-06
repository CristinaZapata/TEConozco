$(document).ready(function(){

	var imgRandom = {
        imgs: [
          "images/fondo/aulas2.png",
          "images/fondo/aulas3.jpg",
          "images/fondo/aulas3Noche.jpg",
		  "images/fondo/biblio.jpg",
		  "images/fondo/cedetec.jpg",
		  "images/fondo/cenote.jpg",
		  "images/fondo/dae.jpg",
		  "images/fondo/dae2.jpg",
		  "images/fondo/dentroAulas3.jpg",
		  "images/fondo/dentroCedetec.jpg",
		  "images/fondo/fueraAulas3.jpg",
		  "images/fondo/haciaCedetec.jpg",
        ],
        generate: function(){
          var rutaImagen = imgRandom.imgs[Math.floor(Math.random()*imgRandom.imgs.length)];
          var img = new Image();
          img.src = rutaImagen;
          $("img.imgFondo").html(img);
          $("img.imgFondo").attr("src", rutaImagen);
        }
      }
      imgRandom.generate();
});