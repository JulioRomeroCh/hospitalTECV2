package modelo;

import java.util.Date;
public class Bitacora {
  
  private int identificador;
  private Lista<Date> fechasModificacion;
  private Lista<Date> horasModificacion;
  
  public Bitacora(){
  }
  
  public Bitacora(int pIdentificador){
      
    fechasModificacion = new Lista<Date>();
    horasModificacion = new Lista<Date>();
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
      horasModificacion.add(new Date());
    }
    
  @Override
    public String toString(){
        String mensaje="";
        mensaje="Indentificador: "+getIdentificador()+"\n";
        for (int indice=0; indice!=fechasModificacion.getSize();indice++){
          mensaje+=fechasModificacion.get(indice).getDay()+"/"+fechasModificacion.get(indice).getMonth()+"/"+fechasModificacion.get(indice).getYear()
          +"\n";   
        }
        for (int indice=0; indice!=horasModificacion.getSize();indice++){
          mensaje+=horasModificacion.get(indice).getHours()+":"+horasModificacion.get(indice).getMinutes()+"\n";
        }
        return mensaje;
    }
}
  
  

