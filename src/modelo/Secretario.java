package modelo;

//Imports fundamentales
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Declaración de la clase "Secretario": Especialización de la clase Funcionario, almacenará los datos de un Secretario.
 * 
 * @author José Ignacio Blanco
 * @author Kevin Rojas Salazar
 * @author Julio Romero Chacón
 */
public class Secretario extends Funcionario {
 
  //Atributos de la clase  
  private int codigoSecretario;
  
     
  /**
  * Método Secretario: corresponde al constructor vacío de la clase.
  */
  public Secretario(){
  }
  
   /**
   * Método Secretario: constructor que llama a los métodos set de los atributos y les asigna el valor de cada parámetro
   * 
   * @param pCedula: string que representa la cédula del secretario.
   * @param pNombre: string que representa el nombredel secretario.
   * @param pApellido1: string que representa primer apellido del secretario.
   * @param pApellido2: string que representa el segundo apellido del secretario.
   * @param pRol: string que representa el rol del secretario.
   * @param pNombreUsuario: string que representa el nombre de usuario del secretario.
   * @param pContraseña: string que representa la contraseña del secretario.
   * @param pIdentificadorFuncionario: int que representa el identificador del funcionario.
   * @param pTipo: TipoFuncionario que representa el tipo de funcionario.
   * @param pFechaIngreso: Date que representa la fecha de ingreso del secretario.
   * @param pIndice: int que representa el área del secretario.
   * @param pCodigoSecretario: int que representa el identificador del secretario.
   */
  public Secretario(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
      Date pFechaIngreso, int pIndice, int pCodigoSecretario){
    
    super (pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña, 
        pIdentificadorFuncionario, pTipo, pFechaIngreso, pIndice);
    
    setCodigoSecretario(pCodigoSecretario);
  }
  
   /**
   * Método Secretario: constructor que llama a los métodos set de los atributos y les asigna el valor de cada parámetro
   * 
   * @param pCedula: string que representa la cédula del secretario.
   * @param pNombre: string que representa el nombredel secretario.
   * @param pApellido1: string que representa primer apellido del secretario.
   * @param pApellido2: string que representa el segundo apellido del secretario.
   * @param pRol: string que representa el rol del secretario.
   * @param pNombreUsuario: string que representa el nombre de usuario del secretario.
   * @param pContraseña: string que representa la contraseña del secretario.
   * @param pIdentificadorFuncionario: int que representa el identificador del funcionario.
   * @param pTipo: TipoFuncionario que representa el tipo de funcionario.
   * @param pFechaIngreso: Date que representa la fecha de ingreso del secretario.
   * @param pArea: string que representa el área del secretario.
   * @param pCodigoSecretario: int que representa el identificador del secretario.
   */
  public Secretario(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
      Date pFechaIngreso, String pArea, int pCodigoSecretario){
    
    super (pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña,
           pIdentificadorFuncionario, pTipo, pFechaIngreso, pArea);
    
    setCodigoSecretario(pCodigoSecretario);
  }

  //Métodos accesores
  public int getCodigoSecretario() {
    return codigoSecretario;
  }
  
  public void setCodigoSecretario(int pCodigoSecretario){
    this.codigoSecretario = pCodigoSecretario;
  }
   
  /**
  * Método toString: llama a los métodos get de los atributos para colocarlos en una misma cadena de caracteres.
  * 
  * @return mensaje: String que posee los valores de cada atributo.
  */
  public String toString(){
    String mensaje="";
    mensaje="Código: "+getCodigoSecretario()+"\n";
    mensaje+=super.toString()+"\n";
    return mensaje;
  }

  /**
   * Método insertarSecretario: método que inserta un secretario a la base de datos.
   * 
   * @param pCedula: string que representa la cédula del secretario.
   * @param pNombre: string que representa el nombredel secretario.
   * @param pApellido1: string que representa primer apellido del secretario.
   * @param pApellido2: string que representa el segundo apellido del secretario.
   * @param pRol: string que representa el rol del secretario.
   * @param pNombreUsuario: string que representa el nombre de usuario del secretario.
   * @param pContraseña: string que representa la contraseña del secretario.
   * @param pIdentificadorFuncionario: int que representa el identificador del funcionario.
   * @param pTipo: TipoFuncionario que representa el tipo de funcionario.
   * @param pFechaIngreso: Date que representa la fecha de ingreso del secretario.
   * @param pArea: string que representa el área del secretario.
   * @param pCodigoSecretario: int que representa el identificador del secretario.
   * @return salida: Boolean que representa el estado de la consulta.
   */ 
  public boolean insertarSecretario(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
      Date pFechaIngreso, String pArea, int pCodigoSecretario){
      
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionSecretario;
    try{
      super.insertarFuncionario(pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña, pIdentificadorFuncionario, 
          pTipo, pFechaIngreso, pArea);          
      insercionSecretario = conectar.prepareStatement("INSERT INTO secretario VALUES (?,?)");
      insercionSecretario.setInt(1, pCodigoSecretario);
      insercionSecretario.setInt(2, pIdentificadorFuncionario);
      insercionSecretario.execute();
    }
    catch(SQLException errorBaseDatos){
      JOptionPane.showMessageDialog(null, "Favor verifique los datos");
    }
    catch(Exception error){
      //error.printStackTrace();
      salida = false;
      JOptionPane.showMessageDialog(null, "Favor verifique los datos");
    }
    return salida;
  }
}
