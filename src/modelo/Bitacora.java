package modelo;

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
}
  
  

