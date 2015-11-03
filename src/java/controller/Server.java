/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.PrintWriter;
import java.util.Scanner;
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
    
    private Juego game = new Juego();
    private String pregunta;
    private int respuesta = -1;
    private boolean isLeafNode = false;
    private boolean lastQuestionDisplayed = false;
    
    /*
        1.- El juego inicia y el servidor manda la pregunta actual.
        2.- El cliente recibe la pregunta.
        3.- El usuario responde la pregunta y el cliente captura y manda la respuesta al servidor.
        4.- El servidor recibe la respuesta y se la pasa al juego.
        5.- Rinse and repeat.
    
    */

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
    
    private JSONObject generarRespuesta(String resp)
    {
        JSONObject obj = new JSONObject();
        
        if(resp.equalsIgnoreCase("yes"))               
            {
                if(!lastQuestionDisplayed)       
                {
                    this.respuesta = Juego.YES_LEFT_SIDE;
                }
                else
                {
                    obj.put("pregunta", "¡Sí! ¡Gané!");
                    return obj;
                }
            }
            else if(resp.equalsIgnoreCase("no"))  
            {
                if(!lastQuestionDisplayed)
                {
                    this.respuesta = Juego.NO_RIGHT_SIDE;
                }
                else
                {
                    obj.put("aprender", "¡Me ganaste! \n ¿Quién es?");
                    return obj; 
                }
            }
            else if(resp.equalsIgnoreCase("reiniciar"))     reiniciarJuego();
            
            //Actualizar juego
            updateGame();
          
            
            
            if(!isLeafNode)          obj.put("pregunta", pregunta);
            else                  
            {
                obj.put("respuesta", pregunta);
                lastQuestionDisplayed = true;
            }
            
            return obj;
    }
    
    
     @SuppressWarnings("unchecked")
     public void doPost(HttpServletRequest request, HttpServletResponse response){
        
        response.setContentType("text/html");

        try{
            String resp = request.getParameter("respuesta");
            
            JSONObject obj = generarRespuesta(resp);
            
            /*
            if(request.getParameter("nombrePersonaje") != null)
            {
                String nombrePersonaje = request.getParameter("nombrePersonaje");
                String preguntaPersonaje = request.getParameter("preguntaPersonaje");
                
                game.addLeafNode(nombrePersonaje, preguntaPersonaje, (Juego.LeafNode) game.current, game.penultimo, respuesta);
                
            }
            else
            {
                obj = generarRespuesta(resp);
            }
            //if(resp == null)    resp = "no";*/
            
            
            
           /*
            if(resp.equalsIgnoreCase("yes"))               
            {
                if(!lastQuestionDisplayed)       
                {
                    this.respuesta = Juego.YES_LEFT_SIDE;
                }
                else
                {
                    //return el json con el "¡Sí! ¡Gané!
                }
            }
            else if(resp.equalsIgnoreCase("no"))  
            {
                if(!lastQuestionDisplayed)
                {
                    this.respuesta = Juego.NO_RIGHT_SIDE;
                }
                else
                {
                    //return el json con el "¡Me ganaste! ¿Quién es?" Escribe el nombre y una pregunta asociada
                }
            }
            else if(resp.equalsIgnoreCase("reiniciar"))     reiniciarJuego();
            
            //Actualizar juego
            updateGame();
            
            JSONObject obj = new JSONObject();
            
            
            if(!isLeafNode)          obj.put("pregunta", pregunta);
            else                  
            {
                obj.put("respuesta", pregunta);
                lastQuestionDisplayed = true;
            }
            */
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
