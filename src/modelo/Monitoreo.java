
package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Monitoreo {

private Date fechaAplicacion;
    
public Monitoreo(){
}

public Monitoreo(Date pFechaAplicacion){
 
  setFechaAplicacion(pFechaAplicacion);
}

    /**
     * @return the fechaAplicacion
     */
    public String getFechaAplicacion() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyy/mm/dd");
        return formatoFecha.format(this.fechaAplicacion);
    }

    /**
     * @param pFechaAplicacion the fechaAplicacion to set
     */
    public void setFechaAplicacion(Date pFechaAplicacion) {
        this.fechaAplicacion = pFechaAplicacion;
    }


}
