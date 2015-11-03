/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Scanner;

/**
 *
 * @author A01335563
 */

public class Juego {
    //Ivars
    private BranchNode root;
    public Node current;
    public BranchNode penultimo;
    
    public static final int YES_LEFT_SIDE = 0;
    public static final int NO_RIGHT_SIDE = 1;
    
    //Constructor
    public Juego()
    {
        root = new BranchNode();
        root.pregunta = "¿Es hombre?";
        root.derecha = new LeafNode("Fernanda Montiel"); //Sí
        root.izquierda = new LeafNode("Rafael Lozano"); //No
        current = root;
    }
    
    //Adds a leaf node at the current position
    //TODO - change side to enum type, instead of String
    public void addLeafNode(String animal, String newQuestion, LeafNode currentLeaf, BranchNode branch, int side)
    {
        BranchNode newBranch = new BranchNode();
        newBranch.pregunta = newQuestion;
        
        
        LeafNode newLeaf = new LeafNode(animal);
        
        assert side == YES_LEFT_SIDE || side == NO_RIGHT_SIDE;
        
        if(side == YES_LEFT_SIDE)             branch.izquierda = newBranch;
        else if(side == NO_RIGHT_SIDE)       branch.derecha = newBranch;

        newBranch.izquierda = newLeaf;
        newBranch.derecha = currentLeaf;
        
    }
    
    private abstract class Node
    {
        protected Node derecha;
        protected Node izquierda;
        
    }
    
    private class BranchNode extends Node
    {
        private String pregunta;
        
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
        private String animal;
        
        public LeafNode(String animal)
        {
            this.animal = animal;
            this.derecha = null;
            this.izquierda = null;
        }
    }
    
    public String getCurrentQuestion()
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
    
    public boolean isLastQuestion()
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
    
    public void reset()
    {
        current = (BranchNode) root;
    }
    
    public void descendTree(int side)
    {
        if(current.derecha != null && current.izquierda != null)
        {
            if(side == YES_LEFT_SIDE)
            {
                if(current.izquierda instanceof BranchNode)
                {
                    current = (BranchNode) current.izquierda;
                    penultimo = (BranchNode) current;
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
                    penultimo = (BranchNode) current;
                }
                else
                {
                    current = (LeafNode) current.derecha;
                }
            }
        }
    }
    /*
    public static void main(String[] args)
    {
        Juego ak = new Juego();
        Scanner scanner = new Scanner(System.in);
        String playAgain = "";
        
        do
        {
            Node current = ak.root;
            
            //Sentinel node that acts as a helper node for the addition of a new node
            BranchNode sentinel = (BranchNode) current;

            while(current instanceof BranchNode)
            {
                System.out.println(((BranchNode)current).pregunta);

                sentinel = (BranchNode) current;

                //Receive input
                String input = StdIn.readString();

                if(input.equalsIgnoreCase("yes"))    
                {
                    if(current.izquierda instanceof BranchNode)
                    {
                        current = (BranchNode) current.izquierda;
                    }
                    else //branch.izquierda is instance of LeafNode
                    {
                        current = (LeafNode) current.izquierda;
                    }

                }
                else if(input.equalsIgnoreCase("no"))
                {
                    if(current.derecha instanceof BranchNode)
                    {
                        current = (BranchNode) current.derecha;
                    }
                    else
                    {
                        current = (LeafNode) current.derecha;
                    }

                }
            }

            if(current instanceof LeafNode) //Double check
            {
                 LeafNode curr = (LeafNode) current;

                //current is a leaf node
                System.out.println("Is it a " + curr.animal + "?");

                //Receive input
                String input = StdIn.readString();

                if(input.equalsIgnoreCase("yes"))
                {
                    System.out.println("Yeah! I won!");
                }
                else
                {
                    System.out.println("What is it?");
                    
                    //Receive input
                    input = StdIn.readString();

                    String name = input;
                    int side = 1000;
                    
                    if(curr.animal.equals(((LeafNode) sentinel.izquierda).animal))           side = Akinator.YES_LEFT_SIDE;
                    else if(curr.animal.equals(((LeafNode) sentinel.derecha).animal))        side = Akinator.NO_RIGHT_SIDE;                                        
                        
                    System.out.println("Tell me a question that is yes for a " + input + " and no for a " + curr.animal);
                    input = scanner.nextLine();
                    ak.addLeafNode(name, input, curr, sentinel, side);

                    System.out.println("Thanks, now I know better! Next time, I'm gonna win!");
                }

                System.out.println("Wanna play again?");
                
                playAgain = scanner.next();
            }
            
        }while(playAgain.equalsIgnoreCase("yes"));
        
    }
    */
}
