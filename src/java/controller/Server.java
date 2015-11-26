/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Juego;
import org.json.simple.JSONObject;

/**
 * 
 * @author A01335563
 */
@WebServlet("/servidor/*")
public class Server extends HttpServlet{
    
    private TreeMap<Integer, Sesion> sesiones = new TreeMap<Integer, Sesion>();
    
    /*
        1.- El juego inicia y el servidor manda la pregunta actual.
        2.- El cliente recibe la pregunta.
        3.- El usuario responde la pregunta y el cliente captura y manda la respuesta al servidor.
        4.- El servidor recibe la respuesta y se la pasa al juego.
        5.- Rinse and repeat.
    
    */
/*
    private void reiniciarJuego()
    {
        game.reset();
        respuesta = -1;
        isLeafNode = false;
        lastQuestionDisplayed = false;
    }
    
    private void updateGame()
    {
        game.descendTree(respuesta);  
        pregunta = game.getCurrentQuestion();
        isLeafNode = game.isLastQuestion();
    }
    */
    
    private JSONObject generarRespuesta(HttpServletRequest request)
    {
        JSONObject obj = new JSONObject();
        String resp = request.getParameter("respuesta");
        String id = request.getParameter("id_sesion");
        
        System.out.println("ID_SESION: " + id);
        
        int id_sesion = Integer.parseInt(id);
        
        Sesion sesion = sesiones.get(id_sesion);
        
        System.out.println("ID_SESION: " + id);
        
        if(sesion == null)      
        {
            sesion = new Sesion();
            System.out.println("Se ha creado una nueva sesión con id: " + id_sesion);
            
            sesiones.put(id_sesion, sesion);
        }
        
        if(resp.equalsIgnoreCase("yes"))               
            {
                if(!sesion.isLastQuestionDisplayed())       
                {
                    sesion.setRespuesta(Juego.YES_LEFT_SIDE);
                }
                
                else
                {
                    obj.put("pregunta", "¡Sí! ¡Gané!");
                    return obj;
                }
            }
            else if(resp.equalsIgnoreCase("no"))  
            {
                if(!sesion.isLastQuestionDisplayed())
                {
                    sesion.setRespuesta(Juego.NO_RIGHT_SIDE);
                }
                else
                {
                    //obj.put("aprender", "¡Me ganaste! \n ¿Quién es?");
                    obj.put("aprender", "Escribe una pregunta cuya respuesta sea 'sí' para tu personaje y 'no' para " + sesion.getPregunta());
                    return obj; 
                }
            }
            else if(resp.equalsIgnoreCase("reiniciar"))   
            {
                sesion.reiniciarJuego();
            }
            else if(resp.equalsIgnoreCase("aprender"))
            {
                //TODO - Validar en vista que ambos parámetros NO estén vacíos
                String pregunta = request.getParameter("preguntaPersonaje");
                String personaje = request.getParameter("nombrePersonaje");
                
                System.out.println("LA PREGUNTA ES: " + pregunta);
                System.out.println("EL PERSONAJE ES: " + personaje);
                
                Juego.LeafNode  leaf = (Juego.LeafNode) sesion.getGame().current;
                Juego.BranchNode branch = (Juego.BranchNode) sesion.getGame().penultimo;
                
                String penultimaPregunta = branch.pregunta;
                                                       
                sesion.getGame().addLeafNode(personaje, pregunta, leaf, branch);       
                
                obj.put("agradecer", "¡Muchas gracias! ¡Ahora soy más sabio!");
            }
            
        
            //Actualizar juego
            sesion.updateGame();
          
            
            
            if(!sesion.isLeafNode())          obj.put("pregunta", sesion.getPregunta());
            else                  
            {
                obj.put("respuesta", sesion.getPregunta());
                sesion.setLastQuestionDisplayed(true);
            }
            
            return obj;
    }
    
    
     @SuppressWarnings("unchecked")
     public void doPost(HttpServletRequest request, HttpServletResponse response){
        
        response.setContentType("text/html");

        try{
            
            JSONObject obj = generarRespuesta(request);
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            //PrintWriter out = response.getWriter();
            
            //out.print(obj);
            if(obj != null) response.getWriter().write(obj.toJSONString());
            //out.flush();
            //throw new IndexOutOfBoundsException();
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }
     
     @SuppressWarnings("unchecked")
     public void doGet(HttpServletRequest request, HttpServletResponse response){
        
         doPost(request, response);

    }

}
