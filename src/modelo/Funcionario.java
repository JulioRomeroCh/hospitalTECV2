
package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
public class Funcionario extends Usuario {
 
  protected static Lista<String> areaTrabajo = new Lista<String>();
  protected int identificadorFuncionario;
  protected TipoFuncionario tipo;
  protected Date fechaIngreso;
  protected String area;
  protected Lista<Cita> citasAgendadas;
  
  
  public Funcionario(){
  }
  
  public Funcionario (String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
      Date pFechaIngreso, int pIndice){
    
    super (pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña);
    areaTrabajo.clear();
    areaTrabajo.add("Administrativa");
    areaTrabajo.add("Emergencias");
    areaTrabajo.add("Ginecología");
    areaTrabajo.add("Oncología");
    areaTrabajo.add("Dermatología");
    areaTrabajo.add("Ortopedia");
    
    setIdentificadorFuncionario(pIdentificadorFuncionario);
    setTipo(pTipo);
    setFechaIngreso(pFechaIngreso);
    setArea(pIndice);
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
     * @param pIndice the area to set
     */
    public void setArea(int pIndice) {
      for (int indice = 0; indice != areaTrabajo.getSize(); indice++){
        if (indice == pIndice){
          this.area = areaTrabajo.get(pIndice);
          break;
        }
      } 
    }
    
    public void setArea (String pArea){
      areaTrabajo.add(pArea);
    }
    
    public void añadirCita(Cita pCita){
      citasAgendadas.add(pCita);
    }
    
    public void añadirAreaTrabajo(String pArea){
      areaTrabajo.add(pArea);
    }
    
    public void modificarAreaTrabajo (int pIndice, String pNuevaArea){
      areaTrabajo.remove(pIndice);
      setArea(pNuevaArea);
    }
    
    public void eliminarAreaTrabajo (int pIndice){
      areaTrabajo.remove(pIndice);
    }
    
    
  @Override
    public String toString(){
      String mensaje="";
      mensaje="Identificador: "+getIdentificadorFuncionario()+"\n";
      mensaje+="Tipo: "+getTipo()+"\n";
      mensaje+="Fecha de ingreso: "+getFechaIngreso()+"\n";
      mensaje+=super.toString()+"\n";
      mensaje+="Area"+getArea()+"\n";
      for (int indice=0;indice!=citasAgendadas.getSize();indice++){
          mensaje+=citasAgendadas.get(indice).toString()+"\n";
      }
      return mensaje;
    }  
}
