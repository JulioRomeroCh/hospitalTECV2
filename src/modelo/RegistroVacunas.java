package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;


public class RegistroVacunas {
 
  private Date fechaAplicacion;
  
  public RegistroVacunas(){
  }
  
  public RegistroVacunas(Date pFechaAplicacion){
    setFechaAplicacion(pFechaAplicacion);
  }

    /**
     * @return the fechaAplicacion
     */
    public String getFechaAplicacion() {
        SimpleDateFormat formatoFecha = SimpleDateFormat("yyy/mm/dd");
        return formatoFecha.format(this.fechaAplicacion);
    }

    /**
     * @param pFechaAplicacion the fechaAplicacion to set
     */
    public void setFechaAplicacion(Date pFechaAplicacion) {
        this.fechaAplicacion = pFechaAplicacion;
    }

    
    
    private SimpleDateFormat SimpleDateFormat(String yyymmdd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
