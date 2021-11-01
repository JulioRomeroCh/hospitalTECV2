
package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Monitoreo {

private Date fechaAplicacion;

private Cita citaMedica;
private Bitacora registro;
    
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
    
    public Cita getCitaMedica() {
        return citaMedica;
    }
    
    public Bitacora getRegistro() {
        return registro;
    }

    /**
     * @param pFechaAplicacion the fechaAplicacion to set
     */
    public void setFechaAplicacion(Date pFechaAplicacion) {
        this.fechaAplicacion = pFechaAplicacion;
    }
    
    public void setCita(Cita pCita){
      citaMedica = pCita;
    }
    
    public void setRegistro(Bitacora pRegistro){
      registro = pRegistro;
    }
    
    public String toString(){
        String mensaje="";
        mensaje="Fecha: "+getFechaAplicacion()+"\n";
        mensaje+="Cita: "+getCitaMedica().toString()+"\n";
        mensaje+="Bitácora: "+getRegistro().toString()+"\n";
        return mensaje;
    }

}
