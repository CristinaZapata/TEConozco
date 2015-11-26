/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;

/**
 *
 * @author cristinazapata
 */
public class DAOPreguntas {
    
    private Connection conexion;
    private static final String DB = "TEConozco";
    private static final String USER = "root";
    private static final String PASSWORD = "espacio";
    private static final String SERVER = "jdbc:mysql://localhost:3306/" + DB;

    /** Creates a new instance of BasedeDatos */
    public DAOPreguntas(){
        
    }

    public void establecerConexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection(SERVER, USER, PASSWORD);
        }
        catch(Exception e){
            System.out.println("Error en el driver");
            e.printStackTrace();
        }
    }

    public Connection getConexion(){
         return conexion;
    }

    public void cerrar(ResultSet rs){
        if(rs !=  null){
            try{
                rs.close();
            }
            catch(Exception e){
                System.out.print("No es posible cerrar la conexion");
            }
        }
    }

    public void cerrar(Statement statement){
        if(statement != null){
            try{
                statement.close();
            }
            catch(Exception e){}
         }
    }

    public void destruir(){
        if(conexion !=null){
            try{
            conexion.close();
            }
            catch(Exception e){}
        }
    }
    
}
