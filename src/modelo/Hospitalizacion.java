package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Hospitalizacion {
   
  private int identificadorHospitalizacion;
  private Date fechaInicio;
  private Date fechaFin;
  
  private Seguimiento seguimiento;
  
  private Doctor doctorEncargado;
  
  private Lista<Diagnostico> diagnosticos;
  
  public Hospitalizacion(){
  }
  
  public Hospitalizacion(int pIdentificadorHospitalizacion, Date pFechaInicio, Date pFechaFin){
    setIdentificadorHospitalizacion(pIdentificadorHospitalizacion);
    setFechaInicio(pFechaInicio);
    setFechaFin(pFechaFin);
  }

    /**
     * @return the identificadorHospitalizacion
     */
    public int getIdentificadorHospitalizacion() {
        return identificadorHospitalizacion;
    }

    /**
     * @param pIdentificadorHospitalizacion the identificadorHospitalizacion to set
     */
    public void setIdentificadorHospitalizacion(int pIdentificadorHospitalizacion) {
        this.identificadorHospitalizacion = pIdentificadorHospitalizacion;
    }

    /**
     * @return the fechaInicio
     */
    public String getFechaInicio() {
        SimpleDateFormat formatoFecha = SimpleDateFormat("yyy/mm/dd");
        return formatoFecha.format(this.fechaInicio);
    }

    /**
     * @param pFechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date pFechaInicio) {
        this.fechaInicio = pFechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public String getFechaFin() {
      SimpleDateFormat formatoFecha = SimpleDateFormat("yyy/mm/dd");
      return formatoFecha.format(this.fechaFin);
    }

    /**
     * @param pFechaFin the fechaFin to set
     */
    public void setFechaFin(Date pFechaFin) {
        this.fechaFin = pFechaFin;
    }

    
    public void setSeguimiento(int pIdentificadorSeguimiento, Lista<Date> pFechasSeguimiento,
        Lista<String> observaciones){
    
      Seguimiento nuevoSeguimiento = new Seguimiento(pIdentificadorSeguimiento);
      nuevoSeguimiento.reemplazarListaFechas(pFechasSeguimiento);
      nuevoSeguimiento.reemplazarListaObservaciones(observaciones);
      this.seguimiento = nuevoSeguimiento;
    }
    
    public void setDoctorEncargado(Doctor pDoctorEncargado){
      doctorEncargado = pDoctorEncargado;
    }
    
    public void añadirDiagnosticos(Diagnostico pDiagnostico){
      diagnosticos.add(pDiagnostico);
    }
    
    
    
    
    
    
    
    
    
    private SimpleDateFormat SimpleDateFormat(String yyymmdd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
