package modelo;


public class Diagnostico {
 
  private String nombreDiagnostico;
  private NivelDiagnostico nivel;
  private Lista<String> observaciones;
  
  private Lista<Tratamiento> tratamientos;
  
  public Diagnostico(){
  }
  
  public Diagnostico(String pNombreDiagnostico, NivelDiagnostico pNivel){
    observaciones = new Lista<String>();
    setNombreDiagnostico(pNombreDiagnostico);
    setNivel(pNivel);
  }

    /**
     * @return the nombreDiagnostico
     */
    public String getNombreDiagnostico() {
        return nombreDiagnostico;
    }

    /**
     * @param pNombreDiagnostico the nombreDiagnostico to set
     */
    public void setNombreDiagnostico(String pNombreDiagnostico) {
        this.nombreDiagnostico = pNombreDiagnostico;
    }

    /**
     * @return the nivel
     */
    public NivelDiagnostico getNivel() {
        return nivel;
    }

    /**
     * @param pNivel the nivel to set
     */
    public void setNivel(NivelDiagnostico pNivel) {
        this.nivel = pNivel;
    }
    
    public void añadirObservacion (String pObservacion){
      observaciones.add(pObservacion);
    }
    
    public void reemplazarListaObservaciones(Lista<String> pObservaciones){
      observaciones = pObservaciones;
    }
    
    public void añadirTratamiento (Tratamiento pTratamiento){
      tratamientos.add(pTratamiento);
    }
    
    public String toString(){
        String mensaje="";
        mensaje="Nombre"+getNombreDiagnostico()+"\n";
        mensaje+="Nivel: "+getNivel()+"\n";
        for (int indice=0;indice!=observaciones.getSize();indice++){
            mensaje+=observaciones.get(indice).toString()+"\n";
        }
        return mensaje;
    }
}
