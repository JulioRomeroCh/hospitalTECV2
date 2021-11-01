package modelo;

import java.util.Date;

public class Doctor extends Funcionario {
  
  private int codigoDoctor;
  private Lista<String> especialidades;
  
  public Doctor(){
  }
  
  public Doctor(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
      Date pFechaIngreso, String pArea, int pCodigoDoctor){
      
       super (pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña,
           pIdentificadorFuncionario, pTipo, pFechaIngreso, pArea);
       
       especialidades = new Lista<String>();
       setCodigoDoctor(pCodigoDoctor);
  }

    /**
     * @return the codigoDoctor
     */
    public int getCodigoDoctor() {
        return codigoDoctor;
    }

    /**
     * @param pCodigoDoctor the codigoDoctor to set
     */
    public void setCodigoDoctor(int pCodigoDoctor) {
        this.codigoDoctor = pCodigoDoctor;
    }
    
    public void añadirEspecialidad(String pEspecialidad){
      especialidades.add(pEspecialidad);
    }
    
    public void reemplazarListaEspecialidades(Lista<String> pEspecialidades){
        especialidades = pEspecialidades;
    }
    
    public String toString (){
       String mensaje=""; 
       mensaje="Código: "+getCodigoDoctor()+"\n";
       mensaje+=super.toString()+"\n";
       for (int indice=0;indice!=especialidades.getSize();indice++){
           mensaje+=especialidades.get(indice)+"\n";
       }
       return mensaje;
    }
}
