package modelo;

import java.util.Date;

public class Enfermero extends Funcionario {
   
  private int codigoEnfermero;
  private boolean indicadorPersonasACargo = false;
  private boolean indicadorExperienciaCapacitaciones = false;
  
  public Enfermero(){
  }
  
  public Enfermero(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
      Date pFechaIngreso, int pIndice, int pCodigoEnfermero){
    
   super (pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña,
           pIdentificadorFuncionario, pTipo, pFechaIngreso, pIndice);
   
   setCodigoEnfermero(pCodigoEnfermero);
  }

    /**
     * @return the codigoEnfermero
     */
    public int getCodigoEnfermero() {
        return codigoEnfermero;
    }

    /**
     * @param pCodigoEnfermero the codigoEnfermero to set
     */
    public void setCodigoEnfermero(int pCodigoEnfermero) {
        this.codigoEnfermero = pCodigoEnfermero;
    }

    /**
     * @return the indicadorPersonasACargo
     */
    public boolean getIndicadorPersonasACargo() {
        return indicadorPersonasACargo;
    }

    /**
     * @param pIndicadorPersonasACargo the indicadorPersonasACargo to set
     */
    public void setIndicadorPersonasACargo(boolean pIndicadorPersonasACargo) {
        this.indicadorPersonasACargo = pIndicadorPersonasACargo;
    }

    /**
     * @return the indicadorExperienciaCapacitaciones
     */
    public boolean getIndicadorExperienciaCapacitaciones() {
        return indicadorExperienciaCapacitaciones;
    }

    /**
     * @param pIndicadorExperienciaCapacitaciones the indicadorExperienciaCapacitaciones to set
     */
    public void setIndicadorExperienciaCapacitaciones(boolean pIndicadorExperienciaCapacitaciones) {
        this.indicadorExperienciaCapacitaciones = pIndicadorExperienciaCapacitaciones;
    }
    
    public String toString (){
       String mensaje=""; 
       mensaje="Código: "+getCodigoEnfermero()+"\n";
       mensaje+="Personas a cargo: "+getIndicadorPersonasACargo()+"\n";
       mensaje+="Experiencia en capacitaciones"+getIndicadorExperienciaCapacitaciones()+"\n";
       mensaje+=super.toString()+"\n";
       return mensaje;
    }
}
