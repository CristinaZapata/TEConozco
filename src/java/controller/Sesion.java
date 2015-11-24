/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Juego;

/**
 *
 * @author administrador
 */
public class Sesion {
    //Ivars
    private Juego game;
    private String pregunta;
    private int respuesta;
    private boolean isLeafNode;
    private boolean lastQuestionDisplayed;
    
    public Sesion()
    {
        this.game = new Juego();
        this.respuesta = -1;
        this.isLeafNode = false;
        this.lastQuestionDisplayed = false;
    }
    
    public void reiniciarJuego()
    {
        game.reset();
        respuesta = -1;
        isLeafNode = false;
        lastQuestionDisplayed = false;
    }
    
    public void updateGame()
    {
        game.descendTree(respuesta);  
        pregunta = game.getCurrentQuestion();
        isLeafNode = game.isLastQuestion();
    }

    public Juego getGame() {
        return game;
    }

    public void setGame(Juego game) {
        this.game = game;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isLeafNode() {
        return isLeafNode;
    }

    public void setIsLeafNode(boolean isLeafNode) {
        this.isLeafNode = isLeafNode;
    }

    public boolean isLastQuestionDisplayed() {
        return lastQuestionDisplayed;
    }

    public void setLastQuestionDisplayed(boolean lastQuestionDisplayed) {
        this.lastQuestionDisplayed = lastQuestionDisplayed;
    }
    
    
    
}
