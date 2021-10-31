
package modelo;

import java.util.Date;


public class Secretario extends Funcionario {
  private int codigoSecretario;
  
  public Secretario(){
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
}
