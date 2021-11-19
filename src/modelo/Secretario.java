
package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;


public class Secretario extends Funcionario {
  private int codigoSecretario;
  
  public Secretario(){
  }
  
  public Secretario(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
      Date pFechaIngreso, int pIndice, int pCodigoSecretario){
    
    super (pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña,
           pIdentificadorFuncionario, pTipo, pFechaIngreso, pIndice);
    
    setCodigoSecretario(pCodigoSecretario);
  }
  
  public Secretario(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
      Date pFechaIngreso, String pArea, int pCodigoSecretario){
    
    super (pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña,
           pIdentificadorFuncionario, pTipo, pFechaIngreso, pArea);
    
    setCodigoSecretario(pCodigoSecretario);
  }

    /**
     * @return the codigoSecretario
     */
    public int getCodigoSecretario() {
        return codigoSecretario;
    }

    /**
     * @param pCodigoSecretario the codigoSecretario to set
     */
    public void setCodigoSecretario(int pCodigoSecretario){
      this.codigoSecretario = pCodigoSecretario;
    }
    
    public String toString(){
        String mensaje="";
        mensaje="Código: "+getCodigoSecretario()+"\n";
        mensaje+=super.toString()+"\n";
        return mensaje;
    }
    
    public boolean insertarSecretario(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
        String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
        Date pFechaIngreso, String pArea, int pCodigoSecretario){
      boolean salida = true;
      Conexion nuevaConexion = new Conexion();
      Connection conectar = nuevaConexion.conectar();
      PreparedStatement insercionSecretario;
      try{
          super.insertarFuncionario(pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña, pIdentificadorFuncionario, pTipo, pFechaIngreso, pArea);
          
          insercionSecretario = conectar.prepareStatement("INSERT INTO secretario VALUES (?,?)");
          insercionSecretario.setInt(1, pCodigoSecretario);
          insercionSecretario.setInt(2, pIdentificadorFuncionario);
          insercionSecretario.execute();
      }
      catch(Exception error){
                    System.out.println("CATCH INSERTAR SECRETARIO");  
          error.printStackTrace();
        salida = false;        
      }
      return salida;
    }
}
