package modelo;

import com.sun.javafx.print.PrintHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Bitacora {
  
  private int identificador;
  private Lista<Date> fechasModificacion;
  private Lista<Date>horasModificacion;
  private Lista<EstadoCita> estadosCita;
    
  private Cita cita;
  

  
  public Bitacora(){
  }
  
  public Bitacora(int pIdentificador){
      
    fechasModificacion = new Lista<Date>();
    horasModificacion = new Lista<Date>();
    estadosCita = new Lista<EstadoCita>();
    setIdentificador(pIdentificador);
  }

    /**
     * @return the identificador
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * @param pIdentificador the identificador to set
     */
    public void setIdentificador(int pIdentificador) {
        this.identificador = pIdentificador;
    }
    
    public void registrarNuevaFecha(){
      fechasModificacion.add(new Date());
    }
    
    public void registrarNuevaHora(){
     horasModificacion.add(new Date());
    }
    
  @Override
    public String toString(){
        String mensaje="";
        mensaje="Indentificador: "+getIdentificador()+"\n";
        return mensaje;
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
      catch(Exception error){
        salida = false;  
        System.out.println("ERROR: " + error);
        error.printStackTrace();
      }
      return salida;
    }
    
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
      catch(Exception error){
        salida = false;        
      }
      return salida;
    }
    
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
      catch(Exception error){
        salida = false;        
      }
      return salida;
    }
    
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
      catch(Exception error){ 
        salida = false; 
        error.printStackTrace();
      }
      return salida;
    }
}
  
  

