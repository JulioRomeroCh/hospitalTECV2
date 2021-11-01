package modelo;


public class AreaEspecialidad {

  private String area;
  
  private CentroAtencion centroMedico;
  private Lista<Hospitalizacion> hospitalizaciones;
  
  public AreaEspecialidad(){
    hospitalizaciones = new Lista<Hospitalizacion>();
  }
  
  public AreaEspecialidad(String pArea){
    setArea(pArea);
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
    
    public void setCentroMedico(CentroAtencion pCentroMedico){
      centroMedico = pCentroMedico;
    }
    
    public void añadirHospitalizaciones (Hospitalizacion pHospitalizacion){
      hospitalizaciones.add(pHospitalizacion);
    }
    
    public String toString(){
        String mensaje="";
        mensaje="Área: "+getArea()+"\n";
        mensaje+="Centro Médico: " +centroMedico.getNombre()+"\n";
        for (int indice=0;indice!=hospitalizaciones.getSize();indice++){
          mensaje+=hospitalizaciones.get(indice).toString()+"\n";
        }
     
        return mensaje;
    }
}
