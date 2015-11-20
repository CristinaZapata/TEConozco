/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author A01335563
 */

/*
Juego multisala solamente va a tener un nodo root, ya que éste no va a llevar
cuenta de la posición en la que se encuentre. De esta manera, el árbol solamente
va a estar cargado en memoria y los clientes mismos serán los que lleven el registro
de dónde se encuentran en el árbol, mediante apuntadores.

*/

public class JuegoMultisala {
    //Ivars
    private BranchNode root;
    
    public static final int YES_LEFT_SIDE = 0;
    public static final int NO_RIGHT_SIDE = 1;
    
    //Constructor
    public JuegoMultisala()
    {
        root = new BranchNode();
        root.pregunta = "¿Es hombre?";
        root.derecha = new LeafNode("Fernanda Montiel"); //Sí
        root.izquierda = new LeafNode("Rafael Lozano"); //No
    }
    
    public void addLeafNode(String animal, String newQuestion, LeafNode currentLeaf, BranchNode branch)
    {
        int side = -1;
        
        if(branch.izquierda instanceof LeafNode)
        {
            if(currentLeaf.animal.equals(((JuegoMultisala.LeafNode) branch.izquierda).animal))           side = JuegoMultisala.YES_LEFT_SIDE;
        }
        else if(branch.derecha instanceof LeafNode)
        { 
            if(currentLeaf.animal.equals(((JuegoMultisala.LeafNode) branch.derecha).animal))             side = JuegoMultisala.NO_RIGHT_SIDE; 
        }
        BranchNode newBranch = new BranchNode();
        newBranch.pregunta = newQuestion;
        
        
        LeafNode newLeaf = new LeafNode(animal);
        
        assert side == YES_LEFT_SIDE || side == NO_RIGHT_SIDE;
        
        if(side == YES_LEFT_SIDE)             branch.izquierda = newBranch;
        else if(side == NO_RIGHT_SIDE)       branch.derecha = newBranch;

        newBranch.izquierda = newLeaf;
        newBranch.derecha = currentLeaf;
        
    }
    
    
    public String getCurrentQuestion(Node current)
    {
        if(current instanceof BranchNode)       
        {
            return ((BranchNode) current).pregunta;
        }
        else if(current instanceof LeafNode)  
        {
            return ((LeafNode) current).animal;
        }
        else
        {
            return null;
        }
    }
    
    public boolean isLastQuestion(Node current)
    {
        if(current.izquierda == null || current.derecha == null)  
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    public void descendTree(Node current, int side)
    {
        if(current.derecha != null && current.izquierda != null)
        {
            if(side == YES_LEFT_SIDE)
            {
                if(current.izquierda instanceof BranchNode)
                {
                    current = (BranchNode) current.izquierda;
                    //penultimo = (BranchNode) current;
                }
                else //branch.izquierda is instance of LeafNode
                {
                    current = (LeafNode) current.izquierda;
                }
            }
            else if(side == NO_RIGHT_SIDE)
            {
                if(current.derecha instanceof BranchNode)
                {
                    current = (BranchNode) current.derecha;
                    //penultimo = (BranchNode) current;
                }
                else
                {
                    current = (LeafNode) current.derecha;
                }
            }
        }
    }
    
    
    public abstract class Node
    {
        protected Node derecha;
        protected Node izquierda;
        
    }
    
    public class BranchNode extends Node
    {
        String pregunta;
        
        private BranchNode() {}
        
        public BranchNode(Node derecha, Node izquierda, String pregunta)
        {
            this.derecha = derecha;
            this.izquierda = izquierda;
            this.pregunta = pregunta;
        }
    }
    
    public class LeafNode extends Node
    {
        String animal;
        
        public LeafNode(String animal)
        {
            this.animal = animal;
            this.derecha = null;
            this.izquierda = null;
        }
    }
    
}
