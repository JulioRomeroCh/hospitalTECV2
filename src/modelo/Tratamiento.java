package modelo;

//Imports fundamentales
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Clase Tratamiento: Clase encargada de gestionar y crear los objetos de tipo tratamiento a nivel de Java y a nivel de base de datos.
 * 
 * @author José Ignacio Blanco
 * @author Kevin Rojas Salazar
 * @author Julio Romero Chacón
 */
public class Tratamiento {
 
  //Atributos de la clase
  private String nombreTratamiento;
  private String tipo;
  private int dosis;
  
  /**
  * Método Tratamiento: corresponde al constructor vacío de la clase.
  */
  public Tratamiento(){
  }
  
  /**
   * Método Tratamiento: constructor que llama a los métodos set de los atributos y les asigna el valor de cada parámetro.
   * 
   * @param pNombreTratamiento: string que representa el nombre del tratamiento.
   * @param pTipo: string que representa el tipo de tratamiento.
   * @param pDosis: int que representa la cantidad de dosis del tratamiento.
   */
  public Tratamiento(String pNombreTratamiento, String pTipo, int pDosis){
    setNombreTratamiento(pNombreTratamiento);
    setTipo(pTipo);
    setDosis(pDosis);
  }

  //Métodos accesores
  public String getNombreTratamiento() {
    return nombreTratamiento;
  }

  public void setNombreTratamiento(String pNombreTratamiento) {
    this.nombreTratamiento = pNombreTratamiento;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String pTipo) {
    this.tipo = pTipo;
  }

  public int getDosis() {
    return dosis;
  }

  public void setDosis(int pDosis) {
      this.dosis = pDosis;
  }
  
  /**
  * Método toString: llama a los métodos get de los atributos para colocarlos en una misma cadena de caracteres.
  * 
  * @return mensaje: String que posee los valores de cada atributo.
  */
  public String toString(){
    String mensaje="";
    mensaje="Nombre: "+getNombreTratamiento()+"\n";
    mensaje+="Tipo: "+getTipo()+"\n";
    mensaje+="Dosis: "+getDosis()+"\n";
    return mensaje;
  }
    
  /**
   * Método insertarTratamiento: método que inserta un tratamiento a la base de datos.
   * 
   * @param pNombreTratamiento: string que representa el nombre del tratamiento.
   * @param pTipo: string que representa el tipo de tratamiento.
   * @param pDosis: int que representa la cantidad de dosis del tratamiento.
   * @return salida: Boolean que representa el estado de la consulta.
   */
  public boolean insertarTratamiento(String pNombreTratamiento, String pTipo, int pDosis){
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercion;
    try{
      insercion = conectar.prepareStatement("INSERT INTO tratamiento VALUES (?,?,?)");
      insercion.setString(1, pNombreTratamiento);
      insercion.setString(2, pTipo);
      insercion.setInt(3, pDosis);
      insercion.execute();
    }
    catch(SQLException errorBaseDatos){
      JOptionPane.showMessageDialog(null, "Favor verifique los datos");
    }
    catch(Exception error){
      salida = false;     
      JOptionPane.showMessageDialog(null, "Favor verifique los datos");
    }
    return salida;
  } 
}
