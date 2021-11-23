package modelo;

//Imports fundamentales
import java.sql.*;

/**
 * Clase Conexión: Conecta Java con el SGBD Mysql, a través de un controlador.
 * 
 * @author José Ignacio Blanco
 * @author Kevin Rojas Salazar
 * @author Julio Romero Chacón
 */
public class Conexion {
  
  Connection conectar = null;
  /**
  * Método conectar: A través del conector de Mysql se conecta a la base de datos con Java usando las credenciales correspondientes.
  * @return conectar: Objeto de tipo Connection, representa la conexion con la base de datos.
  */
  public Connection conectar(){      
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitaltec","root","");
    }
    catch(ClassNotFoundException | SQLException error){
        
    }
    return conectar;   
  }
}
