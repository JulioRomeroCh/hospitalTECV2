package modelo;

//Imports fundamentales
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
*Declaración de la clase usuario: Almacenará todos los datos de los usuarios registrados en el sistema
*    Clase abstracta.
*/
public abstract class Usuario {
    
  //Atributos de la clase
  protected String cedula;
  protected String nombre;
  protected String apellido1;
  protected String apellido2;
  protected String rol;
  protected String nombreUsuario;
  protected String contraseña;

  /**
  *Método Usuario: Método constructor que inicializa todos los atributos por defecto
  */
  public Usuario(){
  }
  
  /**
  *Método Usuario: Constructor sobrecargado, inicializa todos los atributos en un valor específico
  *@param pCedula: Número de cédula del usuario
  *@param pNombre: Nombre del usuario
  *@param pApellido1: Primer apellido del usuario
  *@param pApellido2: Segundo apellido del usuario
  *@param pRol: Rol del usuario
  *@param pNombreUsuario: Nombre de usuario de la persona
  *@param pContraseña: Contraseña del usuario
  */
  public Usuario(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, String pNombreUsuario, String pContraseña){
    setCedula(pCedula);
    setNombre(pNombre);
    setApellido1(pApellido1);
    setApellido2(pApellido2);
    setRol(pRol);
    setNombreUsuario(pNombreUsuario);
    setContraseña(pContraseña);
  }

  //Métodos accesores
  public String getCedula() {
    return cedula;
  }

  public void setCedula(String pCedula) {
    this.cedula = pCedula;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String pNombre) {
    this.nombre = pNombre;
  }

  public String getApellido1() {
    return apellido1;
  }
  
  public void setApellido1(String pApellido1) {
    this.apellido1 = pApellido1;
  }

  public String getApellido2() {
    return apellido2;
  }

  public void setApellido2(String apellido2) {
    this.apellido2 = apellido2;
  }

  public String getRol() {
    return rol;
  }

  public void setRol(String pRol) {
    this.rol = pRol;
  }

  public String getNombreUsuario() {
    return nombreUsuario;
  }

  public void setNombreUsuario(String pNombreUsuario) {
    this.nombreUsuario = pNombreUsuario;
  }

  public String getContraseña() {
    return contraseña;
  }

  public void setContraseña(String pContraseña) {
    this.contraseña = pContraseña;
  }
    
  /**
  *Método insertarUsuario: Inserta un usuario a la base de datos
  *@param pCedula: Número de cédula del usuario
  *@param pNombre: Nombre del usuario
  *@param pApellido1: Primer apellido del usuario
  *@param pApellido2: Segundo apellido del usuario
  *@param pRol: Rol del usuario
  *@param pNombreUsuario: Nombre de usuario de la persona
  *@param pContraseña: Contraseña del usuario
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
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
      insertar.execute();
    }
    catch(SQLException errorBaseDatos){
      JOptionPane.showMessageDialog(null, "Favor verifique los datos");
    }
    catch(Exception error){
      salida = false;  
      JOptionPane.showMessageDialog(null, "Favor verifique los datos");
    }
    return salida;
  }
    
  /**
  *Método cancelarCitas: Cancela citas de un paciente. Método abstracto
  */
  public abstract void cancelarCita(int pIdentificadorCita);

  /**
  * Método toString: llama a los métodos get de los atributos para colocarlos en una misma cadena de caracteres.
  * 
  * @return mensaje: String que posee los valores de cada atributo.
  */
  public String toString(){
    String mensaje="Cédula: "+getCedula()+"\n";
    mensaje+="Nombre: "+getNombre()+" "+getApellido1()+" "+getApellido2()+"\n";
    mensaje+="Rol: "+getRol()+"\n";
    mensaje+="Usuario: "+getNombreUsuario()+"\n";
    mensaje+="Contraseña: **********"+"\n";
    return mensaje;
  }
  
}
