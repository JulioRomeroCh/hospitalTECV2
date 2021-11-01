package modelo;

import java.util.Date;

public class Seguimiento {
  
  private int identificadorSeguimiento;
  private Lista<Date> fechasSeguimiento;
  private Lista<String> observaciones;
  
  private Funcionario encargado;
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
      encargado = pEncargado;
    }
    
    public void setTratamiento(Tratamiento pTratamiento){
      tratamiento = pTratamiento;
    }
  
}
