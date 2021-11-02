
package modelo;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cita {
  private int identificadorCita;
  private Date fecha;
  private Date hora;
  private String area;
  private String observacion;
  
  private Funcionario encargadoCita;
  
  private Lista<Diagnostico> diagnosticos;
  
  public Cita(){
  }
  
  public Cita(int pIdentificadorCita, Date pFecha, Date pHora, String pObservacion){
    setIdentificadorCita(pIdentificadorCita);
    setFecha(pFecha);
    setHora(pHora);
    setObservacion(pObservacion);
  }

    /**
     * @return the identificadorCita
     */
    public int getIdentificadorCita() {
        return identificadorCita;
    }

    /**
     * @param pIdentificadorCita the identificadorCita to set
     */
    public void setIdentificadorCita(int pIdentificadorCita) {
        this.identificadorCita = pIdentificadorCita;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/mm/dd");
        return formatoFecha.format(this.fecha);
    }

    /**
     * @param pFecha the fecha to set
     */
    public void setFecha(Date pFecha) {
        this.fecha = pFecha;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        SimpleDateFormat formatoHoras = new SimpleDateFormat("HH:mm:ss");
        return formatoHoras.format(this.hora);
    }

    /**
     * @param pHora the hora to set
     */
    public void setHora(Date pHora) {
        this.hora = pHora;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param pArea the area to set
     */
    public void setArea(String pArea) {
        this.area = pArea;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param pObservacion the observacion to set
     */
    public void setObservacion(String pObservacion) {
        this.observacion = pObservacion;
    }
    
    public void añadirDiagnostico(String pNombreDiagnostico, NivelDiagnostico pNivel, 
        Lista<String> pObservaciones){
    
     Diagnostico nuevoDiagnostico = new Diagnostico(pNombreDiagnostico, pNivel);
     nuevoDiagnostico.reemplazarListaObservaciones(pObservaciones);
     diagnosticos.add(nuevoDiagnostico);
    }
    
    public void setEncargadoCita(Funcionario pFuncionario){
      encargadoCita = pFuncionario;
    }
    
    public Funcionario getEncargadoCita(){
      return encargadoCita;
    }
    
    public void registrarDiagnostico(String pNombre, NivelDiagnostico pNivel, Lista<String> pObservaciones){
      Diagnostico nuevoDiagnostico = new Diagnostico(pNombre, pNivel);
      nuevoDiagnostico.reemplazarListaObservaciones(pObservaciones);
      diagnosticos.add(nuevoDiagnostico);
    }
    
    
    public void registrarTratamientos(String pNombreDiagnostico, String pNombreTratamiento, String pTipo,
            int pDosis){
      
        for (int indice = 0; indice != diagnosticos.getSize(); indice++){
          if (diagnosticos.get(indice).getNombreDiagnostico().equals(pNombreDiagnostico)){
              diagnosticos.get(indice).añadirTratamiento(pNombreTratamiento, pTipo, pDosis);
          }
        }
    }
    
    
    
    
  @Override
    public String toString(){
       String mensaje="";
       mensaje="Identificador: "+getIdentificadorCita()+"\n";
       mensaje+="Fecha: "+getFecha()+"\n";
       mensaje+="Hora: "+getHora()+"\n";
       mensaje+="Área: "+getArea()+"\n";
       mensaje+="Observación: "+getObservacion()+"\n";
       
       for (int indice=0;indice!=diagnosticos.getSize();indice++){
           mensaje+=diagnosticos.get(indice).getNombreDiagnostico()+"\n";
       }
       return mensaje;
    }
  
  
}
