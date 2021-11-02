
package modelo;

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
}
