package modelo;

public class Tratamiento {
 
  private String nombreTratamiento;
  private String tipo;
  private int dosis;
  
  public Tratamiento(){
  }
  
  public Tratamiento(String pNombreTratamiento, String pTipo, int pDosis){
    setNombreTratamiento(pNombreTratamiento);
    setTipo(pTipo);
    setDosis(pDosis);
  }

    /**
     * @return the nombreTratamiento
     */
    public String getNombreTratamiento() {
        return nombreTratamiento;
    }

    /**
     * @param pNombreTratamiento the nombreTratamiento to set
     */
    public void setNombreTratamiento(String pNombreTratamiento) {
        this.nombreTratamiento = pNombreTratamiento;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param pTipo the tipo to set
     */
    public void setTipo(String pTipo) {
        this.tipo = pTipo;
    }

    /**
     * @return the dosis
     */
    public int getDosis() {
        return dosis;
    }

    /**
     * @param pDosis the dosis to set
     */
    public void setDosis(int pDosis) {
        this.dosis = pDosis;
    }
  
  
}
