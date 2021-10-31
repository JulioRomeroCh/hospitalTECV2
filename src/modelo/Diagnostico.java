package modelo;


public class Diagnostico {
 
  private String nombreDiagnostico;
  private NivelDiagnostico nivel;
  private Lista<String> observaciones;
  
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
}
