package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Seguimiento {
  
  private int identificadorSeguimiento;
  private Date fechaSeguimiento;
  private String observacion;
  
  private Funcionario responsable;
  private Tratamiento tratamiento;
  
  public Seguimiento(){
  }
  
  public Seguimiento(int pIdentificador, Date pFecha, String pObservacion){
    
    setIdentificadorSeguimiento(pIdentificador);
    setFecha(pFecha);
    setObservacion(pObservacion);
  }

    /**
     * @return the identificadorSeguimiento
     */
    public int getIdentificadorSeguimiento() {
        return identificadorSeguimiento;
    }

    /**
     * @param pIdentificadorSeguimiento the identificadorSeguimiento to set
     */
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
    public String toString(){
        String mensaje="";
        mensaje="Identificador: "+getIdentificadorSeguimiento()+"\n";
        mensaje+="Encargado: "+getEncargado().toString()+"\n";
        mensaje+="Tratamiento: "+getTratamiento().toString()+"\n";
        return mensaje;
    }
    
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
