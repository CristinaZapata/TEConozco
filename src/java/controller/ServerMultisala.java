/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Juego;
import model.JuegoMultisala;
import org.json.simple.JSONObject;

/**
 *
 * @author A01335563
 */
public class ServerMultisala extends HttpServlet {

    
    private JuegoMultisala game = new JuegoMultisala();
    private JuegoMultisala.Node current;
    private String pregunta;
    private int respuesta = -1;
    private boolean isLeafNode = false;
    private boolean lastQuestionDisplayed = false;
    
    
    
    private void updateGame()
    {
        game.descendTree(current, respuesta);  
        pregunta = game.getCurrentQuestion(current);
        isLeafNode = game.isLastQuestion(current);
    }
    
     private JSONObject generarRespuesta(HttpServletRequest request)
    {
        JSONObject obj = new JSONObject();
        String resp = request.getParameter("respuesta");
        
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
                    //obj.put("aprender", "¡Me ganaste! \n ¿Quién es?");
                    obj.put("aprender", "Escribe una pregunta cuya respuesta sea 'sí' para tu personaje y 'no' para " + pregunta);
                    return obj; 
                }
            }
            else if(resp.equalsIgnoreCase("reiniciar"))   
            {
                //reiniciarJuego();
            }
            else if(resp.equalsIgnoreCase("aprender"))
            {
                //TODO - Validar en vista que ambos parámetros NO estén vacíos
                String pregunta = request.getParameter("preguntaPersonaje");
                String personaje = request.getParameter("nombrePersonaje");
                
                System.out.println("LA PREGUNTA ES: " + pregunta);
                System.out.println("EL PERSONAJE ES: " + personaje);
                
                JuegoMultisala.LeafNode  leaf = (JuegoMultisala.LeafNode) current;
                //Juego.BranchNode branch = (JuegoMultisala.BranchNode) game.penultimo;
                                                       
                //game.addLeafNode(personaje, pregunta, leaf, game.penultimo);       
                
                obj.put("agradecer", "¡Muchas gracias! ¡Ahora soy más sabio!");
            }
            
        
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
    
    
    
    /**
     * 
     *
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServerMultisala</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServerMultisala at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
