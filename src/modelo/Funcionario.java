
package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
public class Funcionario extends Usuario {
 
  protected int identificadorFuncionario;
  protected TipoFuncionario tipo;
  protected Date fechaIngreso;
  protected String area;
  
  public Funcionario(){
  }
  
  public Funcionario (String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
      Date pFechaIngreso, String pArea){
    
    super (pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña);
    
    setIdentificadorFuncionario(pIdentificadorFuncionario);
    setTipo(pTipo);
    setFechaIngreso(pFechaIngreso);
    setArea(pArea);
  }

    /**
     * @return the identificadorFuncionario
     */
    public int getIdentificadorFuncionario() {
        return identificadorFuncionario;
    }

    /**
     * @param pIdentificadorFuncionario the identificadorFuncionario to set
     */
    public void setIdentificadorFuncionario(int pIdentificadorFuncionario) {
        this.identificadorFuncionario = pIdentificadorFuncionario;
    }

    /**
     * @return the tipo
     */
    public TipoFuncionario getTipo() {
        return tipo;
    }

    /**
     * @param pTipo the tipo to set
     */
    public void setTipo(TipoFuncionario pTipo) {
        this.tipo = pTipo;
    }

    /**
     * @return the fechaIngreso
     */
    public String getFechaIngreso() {
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
    return formatoFecha.format(this.fechaIngreso);
    }

    /**
     * @param pDia
     * @param pMes
     * @param pAño
     */
    public void setFechaIngreso(Date pFechaIngreso) {
        this.fechaIngreso = pFechaIngreso;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param pArea the area to set
     */
    public void setArea(String pArea) {
        this.area = pArea;
    }
  
  
}
