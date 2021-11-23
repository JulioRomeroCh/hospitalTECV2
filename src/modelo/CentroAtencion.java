package modelo;

//Imports fundamentales
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Clase CentroAtencion: Clase encargada de gestionar y crear los objetos de tipo centroAtención
 *     a nivel de Java y a nivel de base de datos.
 * 
 * @author José Ignacio Blanco
 * @author Kevin Rojas Salazar
 * @author Julio Romero Chacón
 */
public class CentroAtencion {
  
  //Atributos de la clase
  private static Lista<String> tiposCentro = new Lista<String>();
  private int codigo;
  private String nombre;
  private String lugar;
  private int capacidad;
  private String tipo;
  private static int numeroCentros = 0;
  private Lista<Usuario> usuarios;
  
  /**
  * Método CentroAtencion: corresponde al constructor vacío de la clase.
  */
  public CentroAtencion(){
  }
  
  /**
   * Método CentroAtencion: constructor que llama a los métodos set de los atributos y les asigna el valor de cada parámetro
   * 
   * @param pNombre: string que representa el nombre del centro de atención.
   * @param pLugar: string que representa el lugar del centro de atención.
   * @param pCapacidad: int que representa la capacidad del centro de atención.
   * @param pIndice: int que representa el tipo de centro.
   */
  public CentroAtencion(String pNombre, String pLugar, int pCapacidad, int pIndice){

    tiposCentro.clear();
    tiposCentro.add("Hospital");
    tiposCentro.add("Clínica");
    tiposCentro.add("EBAIS");
    numeroCentros++;
    
    setCodigo();
    setNombre(pNombre);
    setLugar(pLugar);
    setCapacidad(pCapacidad);
    setTipo(pIndice);
   
  }
  
   /**
   * Método CentroAtencion: constructor que llama a los métodos set de los atributos y les asigna el valor de cada parámetro
   * 
   * @param pNombre: string que representa el nombre del centro de atención.
   * @param pLugar: string que representa el lugar del centro de atención.
   * @param pCapacidad: int que representa la capacidad del centro de atención.
   */
  public CentroAtencion(String pNombre, String pLugar, int pCapacidad){

    tiposCentro.clear();
    tiposCentro.add("Hospital");
    tiposCentro.add("Clínica");
    tiposCentro.add("EBAIS");

    numeroCentros++;
    setCodigo();
    setNombre(pNombre);
    setLugar(pLugar);
    setCapacidad(pCapacidad);
   
  }
  
  //Métodos accesores
  public int getCodigo() {
    return codigo;
  }
  
  public void setCodigo() {
    this.codigo = numeroCentros;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String pNombre) {
    this.nombre = pNombre;
  }

  public String getLugar() {
    return lugar;
  }
  
  public void setLugar(String pLugar) {
    this.lugar = pLugar;
  }

  public int getCapacidad() {
    return capacidad;
  }

  public void setCapacidad(int pCapacidad) {
    this.capacidad = pCapacidad;
  }

  public String getTipo() {
      return tipo;
  }

  public void setTipo(int pIndice) {
    for (int indice = 0; indice != tiposCentro.getSize(); indice++){
      if (indice == pIndice){
        this.tipo = tiposCentro.get(pIndice);
        break;
      }
    }
  }
    
  public void setTipo(String pTipo){
    this.tipo = pTipo;
  }
    
  /**
   * Método añadirTipoCentro: método que añade un tipo de centro a una lista.
   * 
   * @param pTipo: String que representa el tipo de cento
   */
  public void añadirTipoCentro(String pTipo){
    tiposCentro.add(pTipo);
  }
  
  /**
  * Método toString: llama a los métodos get de los atributos para colocarlos en una misma cadena de caracteres.
  * 
  * @return mensaje: String que posee los valores de cada atributo.
  */  
  @Override
  public String toString(){
    String mensaje="";
    mensaje="Código"+getCodigo()+"\n";
    mensaje+="Nombre: "+getNombre()+"\n";
    mensaje+="Lugar: "+getLugar()+"\n";
    mensaje+="Capacidad: "+getCapacidad()+"\n";
    mensaje+="Tipo: "+getTipo()+"\n";
    for (int indice=0;indice!=usuarios.getSize();indice++){
      mensaje+=usuarios.get(indice).toString()+"\n";
    }
    return mensaje;
  }
   
  /**
   * Método insertarCentro: método que inserta un centro a la base de datos.
   * 
   * @param pNombre: string que representa el nombre del centro de atención.
   * @param pLugar: string que representa el lugar del centro de atención.
   * @param pCapacidad: int que representa la capacidad del centro de atención.
   * @param pIndice: int que representa el tipo de centro.
   * @return salida: Boolean que representa el estado de la consulta.
   */ 
  public boolean insertarCentro(String pNombre, String pLugar, int pCapacidad, int pIndice){
    boolean salida = true;
    setTipo(pIndice);
    String tipoCentro = getTipo();
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionCentro;
    PreparedStatement insercionCentroTipo;

    try{
      insercionCentro = conectar.prepareStatement("INSERT INTO centroatencion VALUES (?, ?,?,?)");
      insercionCentro.setInt(1, getCodigo());
      insercionCentro.setString(2, pNombre);
      insercionCentro.setString(3,  pLugar);
      insercionCentro.setInt(4, pCapacidad);
      insercionCentro.execute();

      insercionCentroTipo = conectar.prepareStatement("INSERT INTO centro_tipo VALUES (?,?)");
      insercionCentroTipo.setInt(1, getCodigo());
      insercionCentroTipo.setInt(2, pIndice);
      insercionCentroTipo.execute();

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
   * Método insertarTipoCentro: método que inserta un tipo de centro a la base de datos.
   * 
   * @param pIdentificador: int que representa el identificador del centro
   * @param pTipo: string que representa el tipo de centro
   * @return salida: Boolean que representa el estado de la consulta.
   */
  public boolean insertarTipoCentro(int pIdentificador, String pTipo){
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionTipoCentro;

    try{
      insercionTipoCentro = conectar.prepareStatement("INSERT INTO tipocentro VALUES (?,?)");
      insercionTipoCentro.setInt(1, pIdentificador);
      insercionTipoCentro.setString(2, pTipo);
      insercionTipoCentro.execute();
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
   * Método insertarUsuarioCentro: método que inserta un usuario de un centro a la base de datos.
   * 
   * @param pCedula: string que representa la cedula del usuarios
   * @return salida: Boolean que representa el estado de la consulta.
   */
  public boolean insertarUsuarioCentro(String pCedula){
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionUsuario;

    try{
      insercionUsuario = conectar.prepareStatement("INSERT INTO centroatencion_tiene_usuario VALUES (?,?)");
      insercionUsuario.setString(1, pCedula);
      insercionUsuario.setInt(2, getCodigo());
      insercionUsuario.execute();
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
}
