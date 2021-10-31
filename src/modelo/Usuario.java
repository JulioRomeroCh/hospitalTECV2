package modelo;

public class Usuario {

  protected String cedula;
  protected String nombre;
  protected String apellido1;
  protected String apellido2;
  protected String rol;
  protected String nombreUsuario;
  protected String contraseña;

  
  public Usuario(){
  }
  
  public Usuario(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña){
   
    setCedula(pCedula);
    setNombre(pNombre);
    setApellido1(pApellido1);
    setApellido2(pApellido2);
    setRol(pRol);
    setNombreUsuario(pNombreUsuario);
    setContraseña(pContraseña);
  }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param pCedula the cedula to set
     */
    public void setCedula(String pCedula) {
        this.cedula = pCedula;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param pNombre the nombre to set
     */
    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

    /**
     * @return the apellido1
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * @param pApellido1 the apellido1 to set
     */
    public void setApellido1(String pApellido1) {
        this.apellido1 = pApellido1;
    }

    /**
     * @return the apellido2
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * @param apellido2 the apellido2 to set
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * @param pRol the rol to set
     */
    public void setRol(String pRol) {
        this.rol = pRol;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param pNombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String pNombreUsuario) {
        this.nombreUsuario = pNombreUsuario;
    }

    /**
     * @return the contraseña
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * @param pContraseña the contraseña to set
     */
    public void setContraseña(String pContraseña) {
        this.contraseña = pContraseña;
    }
  
  
}