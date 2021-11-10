package modelo;

/**
 *
 * @author KevRj
 */
public class Usuario {
    
  private String nombre;
  private String contraseña;
  
  public Usuario(){
      
  }
  
  public Usuario(String pNombre, String pContraseña){
    setNombre(pNombre);
    setContraseña(pContraseña);
  }

  //Métodos accesores
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String pNombre) {
    this.nombre = pNombre;
  }

  public String getContraseña() {
    return contraseña;
  }

  public void setContraseña(String pContraseña) {
    this.contraseña = pContraseña;
  }
  
  
    
}
