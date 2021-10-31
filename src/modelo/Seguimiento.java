package modelo;

import java.util.Date;

public class Seguimiento {
  
  private int identificadorSeguimiento;
  private Lista<Date> fechasSeguimiento;
  private Lista<String> observaciones;
  
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
  
  
}
