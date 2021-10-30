
package controlador;

import modelo.Conexion;


public class HospitalTEC {
   
    
  public static void main (String[] args){
  
    Conexion nuevaConexion = new Conexion();
    
    nuevaConexion.conectar();
  }
}
