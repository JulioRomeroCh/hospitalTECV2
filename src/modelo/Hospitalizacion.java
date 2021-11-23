package modelo;

//Imports fundamentales
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
*Declaración de la clase Hospitalización: Almacenará los datos de un internamiento
*/

public class Hospitalizacion {
   
  //Atributos de la clase  
  private int identificadorHospitalizacion;
  private Date fechaInicio;
  private Date fechaFin; 
  private Seguimiento seguimiento;
  private Cita motivo;
  private CentroAtencion centroHospitalizacion;
  
  
  /**
  *Método Hospitalizacion: Método constructor que inicializa todos los atributos por defecto
  */
  public Hospitalizacion(){
  }
  
  
  /**
  *Método Hospitalizacion: Constructor sobrecargado, inicializa todos los atributos en un valor específico
  *@param pIdentificadorHospitalizacion: Identificador de la hospitalización
  *@param pFechaFin: Fecha de salida del paciente
  *@param: pCita: Cita en la cual se hospitalizó al paciente
  *@param pCentro: Centro de atención, donde se hospitalizará el paciente
  *@param pObservacion: Observación asociada a la hospitalización
  *@param pTratamiento: Tratamiento asociado a la hospitalización
  */
  public Hospitalizacion(int pIdentificadorHospitalizacion, Date pFechaFin, Cita pCita, CentroAtencion pCentro, String pObservacion, Tratamiento pTratamiento){
    setIdentificadorHospitalizacion(pIdentificadorHospitalizacion);
    setFechaInicio(new Date());
    setFechaFin(pFechaFin);
    motivo=pCita;
    setCentroHospitalizacion(pCentro);
    setSeguimiento(pIdentificadorHospitalizacion, pFechaFin, pObservacion, pTratamiento);   
  }

  /**
  *Método insertarHospitalizacionBD: Método que llama a todos los demás métodos correspondientes a base de datos
  *    para realizar las respectivas inserciones.
  */
  public void insertarHospitalizacionBD(){
    insertarHospitalizacion(getIdentificadorHospitalizacion(), fechaInicio, fechaFin);
    getSeguimiento().insertarSeguimiento(identificadorHospitalizacion);
    getSeguimiento().insertarSeguimientoFecha(fechaFin);
    getSeguimiento().insertarSeguimientoObservacion(getSeguimiento().getObservacion());
    getSeguimiento().insertarSeguimientoTratamiento();
    insertarHospitalizacionSeguimiento(getSeguimiento().getIdentificadorSeguimiento());
    insertarCitaHospitalizacion(getMotivo().getIdentificadorCita());
    insertarCentroHospitalizacion();
  }
  
  //Métodos accesores
  public int getIdentificadorHospitalizacion() {
    return identificadorHospitalizacion;
  }

  public void setIdentificadorHospitalizacion(int pIdentificadorHospitalizacion) {
    this.identificadorHospitalizacion = pIdentificadorHospitalizacion;
  }

  public String getFechaInicio() {
    SimpleDateFormat formatoFecha = SimpleDateFormat("yyy/mm/dd");
    return formatoFecha.format(this.fechaInicio);
  }

  public void setFechaInicio(Date pFechaInicio) {
    this.fechaInicio = pFechaInicio;
  }

  public String getFechaFin() {
    SimpleDateFormat formatoFecha = SimpleDateFormat("yyy/mm/dd");
    return formatoFecha.format(this.fechaFin);
  }
    
  public Seguimiento getSeguimiento() {
    return seguimiento;
  }

  public void setFechaFin(Date pFechaFin) {
    this.fechaFin = pFechaFin;
  }
  
  public void setSeguimiento(int pIdentificadorSeguimiento, Date pFechaSeguimiento, String pObservacion, Tratamiento pTratamiento){
    
    Seguimiento nuevoSeguimiento = new Seguimiento(pIdentificadorSeguimiento, pFechaSeguimiento, pObservacion);
    nuevoSeguimiento.setTratamiento(pTratamiento);
    nuevoSeguimiento.setObservacion(pObservacion);
    this.seguimiento = nuevoSeguimiento;
  }
    
  public void setMotivo (Cita pCita){
    this.motivo = pCita;
  }
    
  public Cita getMotivo(){
    return this.motivo;
  }
    
  public void setCentroHospitalizacion(CentroAtencion pCentro){
    this.centroHospitalizacion = pCentro;
  }
    
  public CentroAtencion getCentroHospitalizacion(){
    return this.centroHospitalizacion;
  }

    
  private SimpleDateFormat SimpleDateFormat(String yyymmdd) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
    
  /**
  * Método toString: llama a los métodos get de los atributos para colocarlos en una misma cadena de caracteres.
  * 
  * @return mensaje: String que posee los valores de cada atributo.
  */
  public String toString(){
    String mensaje="";
    mensaje="Identificador: "+getIdentificadorHospitalizacion()+"\n";
    mensaje+="Fecha Inicio: "+getFechaInicio()+"\n";
    mensaje+="Fecha Fin: "+getFechaFin()+"\n";
    mensaje+="Seguimiento: "+getSeguimiento().toString()+"\n";
    return mensaje;
  }    

  /**
  *Método insertarHospitalizacion: Inserta una hospitalizacion en la base de datos
  *@param pIdentificadorHospitalizacion: Identificador de la hospitalización
  *@param pFechaInicio: Fecha inicial de la hospitalización 
  *@param pFechaFin: Fecha de finalización de la hospitalización
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarHospitalizacion(int pIdentificadorHospitalizacion, Date pFechaInicio, Date pFechaFin){
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercion;

    try{
      SimpleDateFormat formato= new SimpleDateFormat("yyy-mm-dd");
      String fechaString1 = formato.format(pFechaInicio);
      Date fechaDate1 = formato.parse(fechaString1);
      java.sql.Date fechaSQL1 = new java.sql.Date(fechaDate1.getTime());
          
      String fechaString2 = formato.format(pFechaFin);
      Date fechaDate2 = formato.parse(fechaString2);
      java.sql.Date fechaSQL2 = new java.sql.Date(fechaDate2.getTime());
         
      insercion = conectar.prepareStatement("INSERT INTO hospitalizacion VALUES (?,?,?)");
      insercion.setInt(1, pIdentificadorHospitalizacion);
      insercion.setDate(2, fechaSQL1);
      insercion.setDate(3, fechaSQL2);
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
    
  /**
  *Método insertarHospitalizacionSeguimiento: Asocia un seguimiento a una hospitalización en la base de datos
  *@param pIdentificadorSeguimiento: Identificador del seguimiento
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarHospitalizacionSeguimiento(int pIdentificadorSeguimiento){
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionSeguimiento;

    try{
      insercionSeguimiento = conectar.prepareStatement("INSERT INTO hospitalizacion_necesita_seguimiento VALUES (?,?)");
      insercionSeguimiento.setInt(1, pIdentificadorSeguimiento);
      insercionSeguimiento.setInt(2, identificadorHospitalizacion);
       insercionSeguimiento.execute();
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
  *Método insertarCitaHospitalizacion: Asocia una cita a la hospitalización
  *@param pIdentificadorCita: Identificador de la cita
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarCitaHospitalizacion(int pIdentificadorCita){
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionCita;

    try{
      insercionCita = conectar.prepareStatement("INSERT INTO cita_hospitalizacion VALUES (?,?)");
      insercionCita.setInt(1, pIdentificadorCita);
      insercionCita.setInt(2, identificadorHospitalizacion);
      insercionCita.execute();
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
  *Método insertarCentroHospitalizacion: Asocia un centro a una hospitalización
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarCentroHospitalizacion(){
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionHospitalización;

    try{
      insercionHospitalización = conectar.prepareStatement("INSERT INTO centroatencion_recibe_hospitalizacion VALUES (?,?)");
      insercionHospitalización.setInt(1, identificadorHospitalizacion);
      insercionHospitalización.setInt(2, centroHospitalizacion.getCodigo());
       insercionHospitalización.execute();
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
