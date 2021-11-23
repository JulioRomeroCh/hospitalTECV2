package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

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
  
  public Enfermero(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
      Date pFechaIngreso, String pArea, int pCodigoEnfermero){
    
   super (pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña,
           pIdentificadorFuncionario, pTipo, pFechaIngreso, pArea);
   
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
    
    public boolean insertarEnfermero(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
        String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
        Date pFechaIngreso, String pArea, int pCodigoEnfermero, String pIndicadorUno, String pIndicadorDos){
      boolean salida = true;
      //boolean indicadorPersonas = getIndicadorPersonasACargo();
      //boolean indicadorExperiencia = getIndicadorExperienciaCapacitaciones();
      int indicador1 = 0;
      int indicador2 = 0;
      if(pIndicadorUno.equals("Sí")){
        indicador1 = 1;    
      }
      if(pIndicadorDos.equals("Sí")){
        indicador2 = 1;    
      }
      Conexion nuevaConexion = new Conexion();
      Connection conectar = nuevaConexion.conectar();
      PreparedStatement insercionEnfermero;
      PreparedStatement insercion;
      try{
          super.insertarFuncionario(pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña, pIdentificadorFuncionario, pTipo, pFechaIngreso, pArea);
          
          insercionEnfermero = conectar.prepareStatement("INSERT INTO enfermero VALUES (?,?,?)");
          insercionEnfermero.setInt(1, pCodigoEnfermero);
          insercionEnfermero.setInt(2, indicador1);
          insercionEnfermero.setInt(3, indicador2);
          insercionEnfermero.execute();
          
          insercion = conectar.prepareStatement("INSERT INTO enfermero_funcionario VALUES (?,?)");
          insercion.setInt(1, pCodigoEnfermero);
          insercion.setInt(2, pIdentificadorFuncionario);
          insercion.execute();
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
    
    public void vacunar(Paciente pPaciente, Vacuna pVacuna) throws ParseException{
      pPaciente.añadirVacuna(pVacuna);
      String fecha = pVacuna.getFechaAplicacion();
      Date fechaAplicacion = new SimpleDateFormat("yyy/mm/dd").parse(fecha);
      pVacuna.insertarVacuna(pVacuna.getNumeroLote(), pVacuna.getNombre(), pVacuna.getFarmaceutica(), fechaAplicacion);
      pPaciente.insertarVacuna(pVacuna);

    }
}
