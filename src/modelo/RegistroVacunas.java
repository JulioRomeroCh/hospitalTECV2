package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;


public class RegistroVacunas {
 
  private Date fechaAplicacion;
  
  private Paciente paciente;
  
  private Lista<Vacuna> listaVacunas;
  
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
    
    
    public void setPaciente(Paciente pPaciente){
      paciente = pPaciente;
    }
    
    public void añadirVacuna(Vacuna pVacuna){
      listaVacunas.add(pVacuna);
    }
    
    
    private SimpleDateFormat SimpleDateFormat(String yyymmdd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //revisar el array
    public String toString(){
        String mensaje="";
        return mensaje;
    }
}
