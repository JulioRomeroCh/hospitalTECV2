package modelo;

/**
 *
 * @author KevRj
 */
public class Sala {
    
  private String identificador;
  private String ubicacion;
  private int capacidad;
  
  public Sala(){
      
  }
  
  public Sala(String pIdentificador, String pUbicacion, int pCapacidad){
    setIdentificador(pIdentificador);
    setUbicacion(pUbicacion);
    setCapacidad(pCapacidad);
  }

  public String getIdentificador() {
    return identificador;
  }

  public void setIdentificador(String pIdentificador) {
    this.identificador = pIdentificador;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(String pUbicacion) {
    this.ubicacion = pUbicacion;
  }

  public int getCapacidad() {
    return capacidad;
  }

  public void setCapacidad(int pCapacidad) {
    this.capacidad = pCapacidad;
  }
  
  
    
}
