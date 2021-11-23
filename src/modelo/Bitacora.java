package modelo;

//Imports fundamentales
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Clase Bitacora: Clase encargada de gestionar y crear los objetos de tipo bitacora a nivel de Java y a nivel de base de datos.
 * 
 * @author José Ignacio Blanco
 * @author Kevin Rojas Salazar
 * @author Julio Romero Chacón
 */
public class Bitacora {
  
  //Atributos de la clase
  private int identificador;
  private Lista<Date> fechasModificacion;
  private Lista<Date>horasModificacion;
  private Lista<EstadoCita> estadosCita;    
  private Cita cita;
  
  /**
  * Método Bitacora: corresponde al constructor vacío de la clase.
  */
  public Bitacora(){
  }

  /**
   * Método Bitacora: constructor que llama a los métodos set de los atributos y les asigna el valor de cada parámetro.
   *     Además, inicializa las Listas.
   * 
   * @param pIdentificador: int que representa el identificador de la bitácora.
   */
  public Bitacora(int pIdentificador){    
    fechasModificacion = new Lista<Date>();
    horasModificacion = new Lista<Date>();
    estadosCita = new Lista<EstadoCita>();
    setIdentificador(pIdentificador);
  }
  
  //Métodos accesores
  public int getIdentificador() {
    return identificador;
  }

  public void setIdentificador(int pIdentificador) {
    this.identificador = pIdentificador;
  }
    
  public void registrarNuevaFecha(){
    fechasModificacion.add(new Date());
  }
    
  public void registrarNuevaHora(){
    horasModificacion.add(new Date());
  }

  public void setCita(Cita pCita){
    this.cita = pCita;
  }
    
  public Cita getCita(){
    return this.cita;
  }
    
  public void añadirCambioEstadoCita(){
    estadosCita.add(getCita().getEstado());
  }
    
  /**
  * Método toString: llama a los métodos get de los atributos para colocarlos en una misma cadena de caracteres.
  * 
  * @return mensaje: String que posee los valores de cada atributo.
  */      
  @Override
  public String toString(){
    String mensaje="";
    mensaje="Indentificador: "+getIdentificador()+"\n";
    return mensaje;
  }
    
  /**
   * Método insertarBitacora: método que inserta una bitácora a la base de datos.
   * 
   * @param pIdentificador: int que representa el identificador de la bitácora.
   * @return salida: Boolean que representa el estado de la consulta.
   */
  public boolean insertarBitacora(int pIdentificador){
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercion;
    PreparedStatement insercionHora;
    PreparedStatement insercionFecha;
    try{
        insercion = conectar.prepareStatement("INSERT INTO bitacora VALUES (?)");
        insercion.setInt(1, pIdentificador);
        insercion.execute();
          
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");
        Date hora = new Date();
        String horaFormato = formatoHora.format(hora);
        Date horaParaSQL = formatoHora.parse(horaFormato);
        java.sql.Time horaSQL = new java.sql.Time(horaParaSQL.getTime());
          
        Date fecha = new Date();
        String fechaFormato = formatoFecha.format(fecha);
        Date fechaParaSQL = formatoFecha.parse(fechaFormato);
        java.sql.Date fechaSQL = new java.sql.Date(fechaParaSQL.getTime());
                  
        insercionHora = conectar.prepareStatement("INSERT INTO bitacora_horamodificacion VALUES (?,?)");
        insercionHora.setInt(1, pIdentificador);
        insercionHora.setTime(2, horaSQL);
        insercionHora.execute();
          
          
        insercionFecha = conectar.prepareStatement("INSERT INTO bitacora_fechamodificacion VALUES (?,?)");
        insercionFecha.setInt(1, pIdentificador);
        insercionFecha.setString(2, fechaFormato);
        insercionFecha.execute();

        fechasModificacion.add(fecha);
        horasModificacion.add(hora);
      }
      catch(SQLException errorBaseDatos){
        JOptionPane.showMessageDialog(null, "Favor verifique los datos");
      }
      catch(Exception error){
        salida = false;  
        JOptionPane.showMessageDialog(null, "Favor verifique los datos");
        //System.out.println("ERROR: " + error);
        //error.printStackTrace();    
      }
      return salida;
    }
  
  /**
  * Método insertarBitacoraFechayHora: método que inserta una fecha y hora relacionada a una bitácora a la base de datos.
  * 
  * @param pIdentificador: int que representa el identificador de la bitácora.
  * @return salida: Boolean que representa el estado de la consulta.
  */
  public boolean insertarBitacoraFechayHora(int pIdentificador){
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();

    PreparedStatement insercionHora;
    PreparedStatement insercionFecha;
    try{
      SimpleDateFormat formatoFecha = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
      SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");
      Date hora = new Date();
      String horaFormato = formatoHora.format(hora);
      Date horaParaSQL = formatoHora.parse(horaFormato);
      java.sql.Time horaSQL = new java.sql.Time(horaParaSQL.getTime());
          
      Date fecha = new Date();
      String fechaFormato = formatoFecha.format(fecha);
      Date fechaParaSQL = formatoFecha.parse(fechaFormato);
      java.sql.Date fechaSQL = new java.sql.Date(fechaParaSQL.getTime());
                   
      insercionHora = conectar.prepareStatement("INSERT INTO bitacora_horamodificacion VALUES (?,?)");
      insercionHora.setInt(1, pIdentificador);
      insercionHora.setTime(2, horaSQL);
      insercionHora.execute();
                  
      insercionFecha = conectar.prepareStatement("INSERT INTO bitacora_fechamodificacion VALUES (?,?)");
      insercionFecha.setInt(1, pIdentificador);
      insercionFecha.setString(2, fechaFormato);
      insercionFecha.execute();

      fechasModificacion.add(fecha);
      horasModificacion.add(hora);
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
  * Método insertarBitacoraCita: método que inserta una bitácora relacionada a una cita a la base de datos.
  * 
  * @param pIdentificador: int que representa el identificador de la bitácora.
  * @return salida: Boolean que representa el estado de la consulta.
  */
  public boolean insertarBitacoraCita(int pIdentificador){
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();

    PreparedStatement insercionCita;
    try{          
      insercionCita = conectar.prepareStatement("INSERT INTO bitacora_cita VALUES (?,?)");
      insercionCita.setInt(1, pIdentificador);
      insercionCita.setInt(2, pIdentificador);
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
  * Método insertarBitacoraCitaEstado: método que inserta a una bitácora el estado de la cita a la base de datos.
  * 
  * @param pIdentificador: int que representa el identificador de la bitácora.
  * @param pEstado: enum EstadoCita que representa el estado de la cita.
  * @return salida: Boolean que representa el estado de la consulta.
  */  
  public boolean insertarBitacoraCitaEstado(int pIdentificador, EstadoCita pEstado){
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();

    PreparedStatement insercionCitaEstado;
    try{
      insercionCitaEstado = conectar.prepareStatement("INSERT INTO bitacora_cita_estado VALUES (?,?)");
      insercionCitaEstado.setInt(1, pIdentificador);
      insercionCitaEstado.setString(2, pEstado.name());
      insercionCitaEstado.execute();
    }
    catch(SQLException errorBaseDatos){
      JOptionPane.showMessageDialog(null, "Favor verifique los datos");
    }
    catch(Exception error){ 
      salida = false; 
      JOptionPane.showMessageDialog(null, "Favor verifique los datos");
      //error.printStackTrace();
    }
    return salida;
  }
}
  
  

