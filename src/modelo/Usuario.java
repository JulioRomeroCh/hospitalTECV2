package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;

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
    
    public boolean insertarUsuario(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, String pNombreUsuario, String pContraseña){
      boolean salida = true;
      Conexion nuevaConexion = new Conexion();
      Connection conectar = nuevaConexion.conectar();
      try{
          CallableStatement insertar = conectar.prepareCall("{CALL insertarUsuario(?,?,?,?,?,?,?)}");
          insertar.setString(1, pCedula);
          insertar.setString(2, pNombre);
          insertar.setString(3, pApellido1);
          insertar.setString(4, pApellido2);
          insertar.setString(5, pRol);
          insertar.setString(6, pNombreUsuario);
          insertar.setString(7, pContraseña);
      }
      catch(Exception error){
        salida = false;        
      }
      return salida;
    }

    public String toString(){
        String mensaje="Cédula: "+getCedula()+"\n";
        mensaje+="Nombre: "+getNombre()+" "+getApellido1()+" "+getApellido2()+"\n";
        mensaje+="Rol: "+getRol()+"\n";
        mensaje+="Usuario: "+getNombreUsuario()+"\n";
        mensaje+="Contraseña: **********"+"\n";
        return mensaje;
    }
  
}
