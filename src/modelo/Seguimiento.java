package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

public class Seguimiento {
  
  private int identificadorSeguimiento;
  private Lista<Date> fechasSeguimiento;
  private Lista<String> observaciones;
  
  private Funcionario responsable;
  private Tratamiento tratamiento;
  
  public Seguimiento(){
  }
  
  public Seguimiento(int pIdentificador){
    
    fechasSeguimiento = new Lista<Date>();
    observaciones = new Lista<String>();
    setIdentificadorSeguimiento(pIdentificador);
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
    
    public void añadirFechas(Date pFecha){
      fechasSeguimiento.add(pFecha);
    }
    public void reemplazarListaFechas(Lista<Date> pFechas){
      fechasSeguimiento = pFechas;
    }
    
    public void añadirObservaciones(String pObservacion){
      observaciones.add(pObservacion);
    }
    
    public void reemplazarListaObservaciones(Lista<String> pObservaciones){
       
      observaciones = pObservaciones;    
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
        for (int indice=0;indice!=fechasSeguimiento.getSize();indice++){
            mensaje+=fechasSeguimiento.get(indice).getDay()+"/"+fechasSeguimiento.get(indice).getMonth()+"/"+
                    fechasSeguimiento.get(indice).getYear()+"\n";
        }
        for (int indice=0;indice!=observaciones.getSize();indice++){
            mensaje+=observaciones.get(indice)+"\n";
        }
       
        return mensaje;
    }
    
    public boolean insertarSeguimiento(int pIdentificador){
      boolean salida = true;
      Conexion nuevaConexion = new Conexion();
      Connection conectar = nuevaConexion.conectar();
      PreparedStatement insercion;
      PreparedStatement insercionFecha;
      PreparedStatement insercionObservacion;
      PreparedStatement insercionTratamiento;
      PreparedStatement insercionFuncionario;
      try{
          insercion = conectar.prepareStatement("INSERT INTO seguimiento VALUES (?)");
          insercion.setInt(1, pIdentificador);
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
          insercionFecha.setDate(2, (java.sql.Date) pFechaSeguimiento);

      }
      catch(Exception error){
        salida = false;        
      }
      return salida;
    }
        
    public boolean insertarSeguimientoObservacion(String pObservacion){
      boolean salida = true;
        añadirObservaciones(pObservacion);
      Conexion nuevaConexion = new Conexion();
      Connection conectar = nuevaConexion.conectar();
      PreparedStatement insercionObservacion;
      try{
          insercionObservacion = conectar.prepareStatement("INSERT INTO seguimiento_observacion VALUES (?,?)");
          insercionObservacion.setInt(1, identificadorSeguimiento);
          insercionObservacion.setString(2, pObservacion);
          
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
      }
      catch(Exception error){
        salida = false;        
      }
      return salida;
    }
}
