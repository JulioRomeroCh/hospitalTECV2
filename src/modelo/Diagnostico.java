package modelo;

//Imports fundamentales
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
*Declaración de la clase diagnóstico: Es una parte de la clase cita, aquí se guardará toda la información
*    sobre un diagnóstico
*/
public class Diagnostico {
 
  //Atributos de la clase
  private String nombreDiagnostico;
  private NivelDiagnostico nivel;
  private Lista<String> observaciones;
  private Lista<Tratamiento> tratamientos;
  
  /**
  *Método Diagnostico: Método constructor que inicializa todos los atributos por defecto
  */
  public Diagnostico(){
  }
  
  /**
  *Método Diagnóstico: Constructor sobrecargado, inicializa todos los atributos en un valor específico
  *@param pNombreDiagnostico: Nombre del diagnóstico
  *@param pNivel: Nivel de gravedad del diagnóstico
  */
  public Diagnostico(String pNombreDiagnostico, NivelDiagnostico pNivel){
    observaciones = new Lista<String>();
    tratamientos = new Lista<Tratamiento>();
    setNombreDiagnostico(pNombreDiagnostico);
    setNivel(pNivel);
  }

  //Métodos accesores
  public String getNombreDiagnostico() {
    return nombreDiagnostico;
  }

  public void setNombreDiagnostico(String pNombreDiagnostico) {
    this.nombreDiagnostico = pNombreDiagnostico;
  }

  public NivelDiagnostico getNivel() {
    return nivel;
  }
  
  public void setNivel(NivelDiagnostico pNivel) {
    this.nivel = pNivel;
  }
    
  public void añadirObservacion (String pObservacion){
    observaciones.add(pObservacion);
  }
    
  public void reemplazarListaObservaciones(Lista<String> pObservaciones){
    observaciones = pObservaciones;
  }
    
  public void añadirTratamiento (Tratamiento pTratamiento){
    tratamientos.add(pTratamiento);
  }
    
  /**
  * Método toString: llama a los métodos get de los atributos para colocarlos en una misma cadena de caracteres.
  * 
  * @return mensaje: String que posee los valores de cada atributo.
  */
  public String toString(){
    String mensaje="";
    mensaje="Nombre"+getNombreDiagnostico()+"\n";
    mensaje+="Nivel: "+getNivel()+"\n";
    for (int indice=0;indice!=observaciones.getSize();indice++){
      mensaje+=observaciones.get(indice).toString()+"\n";
    }
    return mensaje;
  }
   
  /**
  *Método insertarDiagnostico: Inserta un diagnóstico en la base de datos
  *@param pNombreDiagnostico: Nombre del diagnóstico
  *@param pNivel: Nivel de gravedad del diagnóstico
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarDiagnostico(String pNombreDiagnostico, NivelDiagnostico pNivel){
    boolean salida = true;
    String nivel = pNivel.name();
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionDiagnostico;
    try{
      insercionDiagnostico = conectar.prepareStatement("INSERT INTO diagnostico VALUES (?,?)");
      insercionDiagnostico.setString(1, pNombreDiagnostico);
      insercionDiagnostico.setString(2, nivel);
      insercionDiagnostico.execute();
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
  /**
  *Método insertarDiagnosticoObservaciones: Asocia una observación a un diagnóstico en la base de datos
  *@param pObservacion: Observación del diagnóstico
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */  
  public boolean insertarDiagnosticoObservaciones(String pObservacion){
    boolean salida = true;
    añadirObservacion(pObservacion);
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionObservaciones;
    try{
      insercionObservaciones = conectar.prepareStatement("INSERT INTO diagnostico_observaciones VALUES (?,?)");
      insercionObservaciones.setString(1, nombreDiagnostico);
      insercionObservaciones.setString(2, pObservacion);   
      insercionObservaciones.execute();        
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
       
  /**
  *Método insertarDiagnosticoTratamiento: Asocia un tratamiento a un diagnóstico en la base de datos
  *@param pTratamiento: Tratamiento por asociar
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarDiagnosticoTratamiento(Tratamiento pTratamiento){
    boolean salida = true;
    añadirTratamiento(pTratamiento);
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionTratamiento;
    try{
      insercionTratamiento = conectar.prepareStatement("INSERT INTO diagnostico_contiene_tratamiento VALUES (?,?)");
      insercionTratamiento.setString(1, pTratamiento.getNombreTratamiento());
      insercionTratamiento.setString(2, nombreDiagnostico);   
      insercionTratamiento.execute();
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
