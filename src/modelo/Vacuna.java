package modelo;

//Imports fundamentales
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Clase Vacuna: Clase encargada de gestionar y crear los objetos de tipo vacuna a nivel de Java y a nivel de base de datos.
 * 
 * @author José Ignacio Blanco
 * @author Kevin Rojas Salazar
 * @author Julio Romero Chacón
 */
public class Vacuna {
 
  //Atributos de la clase  
  private int numeroLote;
  private String nombre;
  private String farmaceutica;
  private Date fechaAplicacion;
   
  /**
   * Método Vacuna: corresponde al constructor vacío de la clase.
   */
  public Vacuna(){
       
  }

  /**
   * Método Vacuna: constructor que llama a los métodos set de los atributos y les asigna el valor de cada parámetro
   * 
   * @param pNumeroLote: String que representa el número de lote de la vacuna.
   * @param pNombre: String que representa el nombre de la vacuna.
   * @param pFarmaceutica; String que representa la farmaceutica de la vacuna.
   * @param pFechaAplicacion: Date que representa la fecha de aplicación de la vacuna
   */
  public Vacuna(int pNumeroLote, String pNombre, String pFarmaceutica, Date pFechaAplicacion){    
    setNumeroLote(pNumeroLote);
    setNombre(pNombre);
    setFarmaceutica(pFarmaceutica);
    setFechaAplicacion(pFechaAplicacion);
  }
  
  //Métodos accesores
  public int getNumeroLote() {
    return numeroLote;
  }

  public void setNumeroLote(int pNumeroLote) {
    this.numeroLote = pNumeroLote;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String pNombre) {
    this.nombre = pNombre;
  }
  
  public String getFarmaceutica() {
    return farmaceutica;
  }

  public void setFarmaceutica(String pFarmaceutica) {
    this.farmaceutica = pFarmaceutica;
  }

  public String getFechaAplicacion() {
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyy/mm/dd");
    return formatoFecha.format(fechaAplicacion);
  }

  public void setFechaAplicacion(Date pFechaAplicacion) {
    this.fechaAplicacion = pFechaAplicacion;
  }
    
  /**
  * Método toString: llama a los métodos get de los atributos para colocarlos en una misma cadena de caracteres.
  * 
  * @return mensaje: String que posee los valores de cada atributo.
  */
  public String toString(){
    String mensaje="";
    mensaje+="Número de lote: "+getNumeroLote()+"\n";
    mensaje+="Nombre: "+getNombre()+"\n";
    mensaje+="Farmaceutica: "+getFarmaceutica()+"\n";
    return mensaje;
  }

  /**
   * Método insertarVacuna: método que inserta una vacuna a la base de datos.
   * 
   * @param pNumeroLote: String que representa el número de lote de la vacuna.
   * @param pNombre: String que representa el nombre de la vacuna.
   * @param pFarmaceutica; String que representa la farmaceutica de la vacuna.
   * @param pFechaAplicacion: Date que representa la fecha de aplicación de la vacuna
   * @return salida: Boolean que representa el estado de la consulta.
   */
  public boolean insertarVacuna(int pNumeroLote, String pNombre, String pFarmaceutica, Date pFechaAplicacion){
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionVacuna;
    try{
      insercionVacuna = conectar.prepareStatement("INSERT INTO vacuna VALUES (?,?,?,?)");
      insercionVacuna.setInt(1, pNumeroLote);
      insercionVacuna.setString(2, pNombre);
      insercionVacuna.setString(3, pFarmaceutica);
      insercionVacuna.setDate(4, (java.sql.Date) pFechaAplicacion);
      insercionVacuna.execute();
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
  