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
  
  
}
