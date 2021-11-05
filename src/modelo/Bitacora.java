package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
public class Bitacora {
  
  private int identificador;
  private Lista<Date> fechasYHorasModificacion;
  private Lista<EstadoCita> estadosCita;
    
  private Cita cita;
  

  
  public Bitacora(){
  }
  
  public Bitacora(int pIdentificador){
      
    fechasYHorasModificacion = new Lista<Date>();
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
      fechasYHorasModificacion.add(new Date());
    }
    
  @Override
    public String toString(){
        String mensaje="";
        mensaje="Indentificador: "+getIdentificador()+"\n";
        for (int indice=0; indice!=fechasYHorasModificacion.getSize();indice++){
          mensaje+=fechasYHorasModificacion.get(indice).getDay()+"/"+fechasYHorasModificacion.get(indice).getMonth()+"/"+fechasYHorasModificacion.get(indice).getYear()
          +"\n";   
        }
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
      PreparedStatement insercionCita;
      PreparedStatement insercionCitaEstado;
      try{
          insercion = conectar.prepareStatement("INSERT INTO bitacora VALUES (?)");
          insercion.setInt(1, pIdentificador);
          

          insercionHora = conectar.prepareStatement("INSERT INTO bitacora_horamodificacion VALUES (?,?)");
          for (int indice = 0; indice != fechasYHorasModificacion.getSize(); indice++){
            insercionHora.setInt(1, pIdentificador);
            insercionHora.setTime(2, (java.sql.Time) fechasYHorasModificacion.get(indice));
          }
          
          insercionFecha = conectar.prepareStatement("INSERT INTO bitacora_fechamodificacion VALUES (?,?)");
          for (int indice = 0; indice != fechasYHorasModificacion.getSize(); indice++){
            insercionFecha.setInt(1, pIdentificador);
            insercionFecha.setDate(2, (java.sql.Date) fechasYHorasModificacion.get(indice));
          }

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
          insercionCita.setInt(1, cita.getIdentificadorCita());
          insercionCita.setInt(2, pIdentificador);

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
      }
      catch(Exception error){
        salida = false;        
      }
      return salida;
    }
}
  
  

