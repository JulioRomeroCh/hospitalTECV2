package modelo;

//Imports fundamentales
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
*Declaración de la clase Seguimiento: Describe el seguimeinto que se le da a un paciente en una
*    hospitalización
*/
public class Seguimiento {
   
  //Atributos de la clase
  private int identificadorSeguimiento;
  private Date fechaSeguimiento;
  private String observacion;
  private Funcionario responsable;
  private Tratamiento tratamiento;
 
  /**
  *Método Seguimiento: Método constructor que inicializa todos los atributos por defecto
  */
  public Seguimiento(){
  }
  
  
  /**
  *Método Seguimiento: Constructor sobrecargado, inicializa todos los atributos en un valor específico
  *@param pIdentificador: Identificador del seguimiento
  *@param pFecha: Fecha del seguimiento
  *@param pObservación: Observación del seguimiento
  */
  public Seguimiento(int pIdentificador, Date pFecha, String pObservacion){  
    setIdentificadorSeguimiento(pIdentificador);
    setFecha(pFecha);
    setObservacion(pObservacion);
  }

  //Métodos accesores
  public int getIdentificadorSeguimiento() {
    return identificadorSeguimiento;
  }

  public void setIdentificadorSeguimiento(int pIdentificadorSeguimiento) {
    this.identificadorSeguimiento = pIdentificadorSeguimiento;
  }
    
  public void setFecha(Date pFecha){
    this.fechaSeguimiento = pFecha;
  }
    
  public String getFechaFin(){
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
    String fechaString = formato.format(fechaSeguimiento);
    return fechaString;
  }
 
  public void setObservacion(String pObservacion){
    observacion = pObservacion;
  }
    
  public String getObservacion (){
    return this.observacion;
  }
     
  public void setEncargado(Funcionario pEncargado){
    responsable = pEncargado;
  }
 
  public Funcionario getEncargado(){
    return responsable;
  }
   
  public Tratamiento getTratamiento(){
    return tratamiento;
  }
    
  public void setTratamiento(Tratamiento pTratamiento){
    tratamiento = pTratamiento;
  }
  
  /**
  * Método toString: llama a los métodos get de los atributos para colocarlos en una misma cadena de caracteres.
  * 
  * @return mensaje: String que posee los valores de cada atributo.
  */
  public String toString(){
    String mensaje="";
    mensaje="Identificador: "+getIdentificadorSeguimiento()+"\n";
    mensaje+="Encargado: "+getEncargado().toString()+"\n";
    mensaje+="Tratamiento: "+getTratamiento().toString()+"\n";
    return mensaje;
  }
    
  /**
  *Método insertarSeguimiento: Agrega un seguimiento a la base de datos
  *@param pIdentificador: Identificador del seguimiento
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarSeguimiento(int pIdentificador){
     boolean salida = true;
     Conexion nuevaConexion = new Conexion();
     Connection conectar = nuevaConexion.conectar();
     PreparedStatement insercion;
     try{
       insercion = conectar.prepareStatement("INSERT INTO seguimiento VALUES (?)");
       insercion.setInt(1, pIdentificador);
       insercion.execute();
     }
     catch(Exception error){
       salida = false;        
     }
     return salida;
  }
    
  /**
  *Método insertarSeguimientoFecha: Inserta la fecha de seguimeinto
  *@param pFechaSeguimiento: Fecha del seguimiento
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarSeguimientoFecha(Date pFechaSeguimiento){
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionFecha;

    try{
      insercionFecha = conectar.prepareStatement("INSERT INTO seguimiento_fecha VALUES (?,?)");
      insercionFecha.setInt(1, identificadorSeguimiento);
      SimpleDateFormat formatoFecha = new SimpleDateFormat("yyy-MM-dd");
      String fechaString = formatoFecha.format(pFechaSeguimiento);
      Date fechaFormatoSQL = formatoFecha.parse(fechaString);
      java.sql.Date fechaSQL = new java.sql.Date(fechaFormatoSQL.getTime());
          
      insercionFecha.setDate(2, fechaSQL);
      insercionFecha.execute();
    }
    catch(Exception error){
      salida = false;        
    }
    return salida;
  }
        
  /**
  *Método insertarSeguimientoObservacion: Asocia una observación al seguimiento
  *@param pObservacion: Observación por asociar
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarSeguimientoObservacion(String pObservacion){
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionObservacion;
    try{
      insercionObservacion = conectar.prepareStatement("INSERT INTO seguimiento_observacion VALUES (?,?)");
      insercionObservacion.setInt(1, identificadorSeguimiento);
      insercionObservacion.setString(2, pObservacion);
      insercionObservacion.execute();         
    }
    catch(Exception error){
      salida = false;        
    }
    return salida;
  }
      
  /**
  *Método insertarSeguimientoTratamiento: Asocia un tratamiento a un seguimiento
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarSeguimientoTratamiento(){
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionTratamiento;
    try{
      insercionTratamiento = conectar.prepareStatement("INSERT INTO seguimiento_requiere_tratamiento VALUES (?,?)");
      insercionTratamiento.setString(1, tratamiento.getNombreTratamiento());
      insercionTratamiento.setInt(2, identificadorSeguimiento);
      insercionTratamiento.execute();
    }
    catch(Exception error){
      salida = false;        
    }
    return salida;
  }
            
  /**
  *Método insertarFuncionarioSeguimiento: Asocia un funcionario a un seguimiento
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarFuncionarioSeguimiento(){
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionFuncionario;
    try{
      insercionFuncionario = conectar.prepareStatement("INSERT INTO funcionario_realiza_seguimiento VALUES (?,?)");
      insercionFuncionario.setInt(1, identificadorSeguimiento);
      insercionFuncionario.setInt(2, responsable.getIdentificadorFuncionario());
      insercionFuncionario.execute();
    }
    catch(Exception error){
      salida = false;        
    }
    return salida;
  }
}
