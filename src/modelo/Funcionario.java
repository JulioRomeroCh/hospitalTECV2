
package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JOptionPane;
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
    citasAgendadas = new Lista<Cita>();
    
    setIdentificadorFuncionario(pIdentificadorFuncionario);
    setTipo(pTipo);
    setFechaIngreso(pFechaIngreso);
    setArea(pIndice);
  }
  
   public Funcionario (String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
      Date pFechaIngreso, String pArea){
    
    super (pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña);
    areaTrabajo.clear();
    areaTrabajo.add("Administrativa");
    areaTrabajo.add("Emergencias");
    areaTrabajo.add("Ginecología");
    areaTrabajo.add("Oncología");
    areaTrabajo.add("Dermatología");
    areaTrabajo.add("Ortopedia");
    citasAgendadas = new Lista<Cita>();
    
    setIdentificadorFuncionario(pIdentificadorFuncionario);
    setTipo(pTipo);
    setFechaIngreso(pFechaIngreso);
    area=pArea;
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
    
    public void asignarCita(Cita pCita){
      gestionarCita(pCita);
      //System.out.println("SIZE: " + citasAgendadas.getSize());
      for(int indice = 0; indice != citasAgendadas.getSize(); indice++){  
        //System.out.println("IDCITA: " + citasAgendadas.get(indice).getIdentificadorCita());  
        if(citasAgendadas.get(indice).getIdentificadorCita() == pCita.getIdentificadorCita()){
          //System.err.println("ENTRÉ A ASIGNARCITA");
          citasAgendadas.get(indice).setEstado(EstadoCita.ASIGNADA);
          citasAgendadas.get(indice).modificarEstadoCita(pCita.getIdentificadorCita(), EstadoCita.ASIGNADA);
          citasAgendadas.get(indice).getBitacora().añadirCambioEstadoCita();
          citasAgendadas.get(indice).getBitacora().insertarBitacoraCitaEstado(pCita.getIdentificadorCita(), EstadoCita.ASIGNADA);
          citasAgendadas.get(indice).getBitacora().insertarBitacoraFechayHora(pCita.getIdentificadorCita());
          break;
        } 
      }
    }
    
    public void gestionarCita(Cita pCita){
        añadirCita(pCita);
        insertarFuncionarioGestionaCita(pCita.getIdentificadorCita(), getIdentificadorFuncionario());

    }
    
  @Override
    public void cancelarCita(int pIdentificadorCita){
      for(int indice = 0; indice != citasAgendadas.getSize(); indice++){
        if(citasAgendadas.get(indice).getIdentificadorCita() == pIdentificadorCita){
          //EstadoCita estado = citasAgendadas.get(indice).cambiarEstadoCita(this, false);
          citasAgendadas.get(indice).modificarEstadoCita(pIdentificadorCita, EstadoCita.CANCELADA_POR_CENTRO_MEDICO);
          citasAgendadas.get(indice).getBitacora().añadirCambioEstadoCita();
          citasAgendadas.get(indice).getBitacora().insertarBitacoraCitaEstado(pIdentificadorCita, EstadoCita.CANCELADA_POR_CENTRO_MEDICO);
          break;
        }    
      }          
    }
    
    public boolean insertarFuncionario(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, String pNombreUsuario, String pContraseña,
        int pIdentificadorFuncionario, TipoFuncionario pTipo, Date pFechaIngreso, String pArea){
        
      boolean salida = true;
      //setArea(pIndice);
      //String area = getArea();
      String tipo = pTipo.name();
      Conexion nuevaConexion = new Conexion();
      Connection conectar = nuevaConexion.conectar();
      PreparedStatement insercion;
      //PreparedStatement insercionCita;
      try{
          
          SimpleDateFormat formato= new SimpleDateFormat("yyy-mm-dd");
          String fechaString = formato.format(pFechaIngreso);
          Date fechaDate = formato.parse(fechaString);
          java.sql.Date fechaSQL = new java.sql.Date(fechaDate.getTime());
          super.insertarUsuario(pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña);
          CallableStatement insertar = conectar.prepareCall("{CALL insertarFuncionario(?,?,?,?)}");
          insertar.setInt(1, pIdentificadorFuncionario);
          insertar.setString(2, tipo);
          insertar.setDate(3, fechaSQL);
          insertar.setString(4, pArea);
          insertar.execute();
          
          insercion = conectar.prepareStatement("INSERT INTO funcionario_usuario VALUES (?,?)");
          insercion.setInt(1, pIdentificadorFuncionario);
          insercion.setString(2, pCedula);
          insercion.execute();
          
          /*insercionCita = conectar.prepareStatement("INSERT INTO funcionario_gestiona_cita VALUES (?,?)");
          for (int indice = 0; indice != citasAgendadas.getSize(); indice++){
            insercionCita.setInt(1, citasAgendadas.get(indice).getIdentificadorCita());
            insercionCita.setInt(2, pIdentificadorFuncionario);
          }*/
      }
          catch(SQLException errorBaseDatos){
      JOptionPane.showMessageDialog(null, "Favor verifique los datos");
    }
      catch(Exception error){
          //System.out.println("CATCH INSERTAR FUNCIONARIO");  
          //error.printStackTrace();
        salida = false;   
        JOptionPane.showMessageDialog(null, "Favor verifique los datos");
      }
      return salida;
    }
    
     public boolean insertarFuncionarioGestionaCita(int pIdentificadorCita, int pIdentificadorFuncionario){    
      boolean salida = true;
      Conexion nuevaConexion = new Conexion();
      Connection conectar = nuevaConexion.conectar();
      PreparedStatement insertar;
      //PreparedStatement insercionCita;
      try{
          insertar = conectar.prepareStatement("INSERT INTO funcionario_gestiona_cita VALUES (?,?)");
          insertar.setInt(1, pIdentificadorCita);
          insertar.setInt(2, pIdentificadorFuncionario);
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
      
}
