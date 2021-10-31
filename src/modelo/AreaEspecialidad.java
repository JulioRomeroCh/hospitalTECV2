package modelo;


public class AreaEspecialidad {

  private String area;
  
  public AreaEspecialidad(){
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
}
