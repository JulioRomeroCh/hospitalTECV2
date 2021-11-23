package modelo;

//Imports fundamentales
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
*Declaración de la clase funcionario: Clase especializada de usuario, la cual contendrá a los trabajadores de
*    un centro médico en específico.
*/
public class Funcionario extends Usuario {
 
  //Atributos de la clase
  protected static Lista<String> areaTrabajo = new Lista<String>();
  protected int identificadorFuncionario;
  protected TipoFuncionario tipo;
  protected Date fechaIngreso;
  protected String area;
  protected Lista<Cita> citasAgendadas;
  
  /**
  *Método Funcionario: Método constructor que inicializa todos los atributos por defecto
  */
  public Funcionario(){
  }
  
  /*
  *Método Funcionario: Constructor sobrecargado, inicializa todos los atributos en un valor específico
  *@param pCedula: Número de cédula del usuario
  *@param pNombre: Nombre del usuario
  *@param pApellido1: Primer apellido del usuario
  *@param pApellido1: Segundo apellido del usuario
  *@param pRol: Rol del usuario
  *@param pNombreUsuario: Nombre de usuario de la persona
  *@param pContraseña: Contraseña del usuario
  *@param pIdentificadorFuncionario: Identificador del funcionario
  *@param pTipo: Tipo del funcionario
  *@param pFechaIngreso: Fecha de ingreso al trabajo
  *@param pIndice: Índice que busca el área de especialidad
  */
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
  
  /*
  *Método Funcionario: Constructor sobrecargado, inicializa todos los atributos en un valor específico
  *@param pCedula: Número de cédula del usuario
  *@param pNombre: Nombre del usuario
  *@param pApellido1: Primer apellido del usuario
  *@param pApellido1: Segundo apellido del usuario
  *@param pRol: Rol del usuario
  *@param pNombreUsuario: Nombre de usuario de la persona
  *@param pContraseña: Contraseña del usuario
  *@param pIdentificadorFuncionario: Identificador del funcionario
  *@param pTipo: Tipo del funcionario
  *@param pFechaIngreso: Fecha de ingreso al trabajo
  *@param pArea: Área de especialidad
  */
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

  //Métodos accesores
  public int getIdentificadorFuncionario() {
    return identificadorFuncionario;
  }

  public void setIdentificadorFuncionario(int pIdentificadorFuncionario) {
    this.identificadorFuncionario = pIdentificadorFuncionario;
  }

  public TipoFuncionario getTipo() {
    return tipo;
  }

  public void setTipo(TipoFuncionario pTipo) {
    this.tipo = pTipo;
  }

  public String getFechaIngreso() {
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
    return formatoFecha.format(this.fechaIngreso);
  }

  public void setFechaIngreso(Date pFechaIngreso) {
    this.fechaIngreso = pFechaIngreso;
  }

  public String getArea() {
    return area;
  }

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
    
  /**
  * Método toString: llama a los métodos get de los atributos para colocarlos en una misma cadena de caracteres.
  * 
  * @return mensaje: String que posee los valores de cada atributo.
  */ 
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
  
  /**
  *Método asignarCita: Asigna una cita cancelada por el centro médico
  *@param pCita: Cita por asignar
  */ 
  public void asignarCita(Cita pCita){
    gestionarCita(pCita);
    for(int indice = 0; indice != citasAgendadas.getSize(); indice++){  
      if(citasAgendadas.get(indice).getIdentificadorCita() == pCita.getIdentificadorCita()){
        citasAgendadas.get(indice).setEstado(EstadoCita.ASIGNADA);
        citasAgendadas.get(indice).modificarEstadoCita(pCita.getIdentificadorCita(), EstadoCita.ASIGNADA);
        citasAgendadas.get(indice).getBitacora().añadirCambioEstadoCita();
        citasAgendadas.get(indice).getBitacora().insertarBitacoraCitaEstado(pCita.getIdentificadorCita(), EstadoCita.ASIGNADA);
        citasAgendadas.get(indice).getBitacora().insertarBitacoraFechayHora(pCita.getIdentificadorCita());
        break;
      } 
    }
  }
  /**
  *Método gestionarCita: Gestiona la cita de un paciente
  *@param pCita: Cita por gestionar
  */  
  public void gestionarCita(Cita pCita){
    añadirCita(pCita);
    insertarFuncionarioGestionaCita(pCita.getIdentificadorCita(), getIdentificadorFuncionario());
  }
  
  /**
  *Método cancelarCita: Cancela una cita solicitada por un paciente
  *@param pIdentificadorCita: Identificador de la cita por cancelar
  */  
  @Override
  public void cancelarCita(int pIdentificadorCita){
    for(int indice = 0; indice != citasAgendadas.getSize(); indice++){
      if(citasAgendadas.get(indice).getIdentificadorCita() == pIdentificadorCita){
        citasAgendadas.get(indice).modificarEstadoCita(pIdentificadorCita, EstadoCita.CANCELADA_POR_CENTRO_MEDICO);
        citasAgendadas.get(indice).getBitacora().añadirCambioEstadoCita();
        citasAgendadas.get(indice).getBitacora().insertarBitacoraCitaEstado(pIdentificadorCita, EstadoCita.CANCELADA_POR_CENTRO_MEDICO);
        break;
      }    
    }          
  }
    
  /**
  *Método insertarFuncionario: Inserta un funcionario en la base de datos
  *@param pCedula: Número de cédula del usuario
  *@param pNombre: Nombre del usuario
  *@param pApellido1: Primer apellido del usuario
  *@param pApellido2: Segundo apellido del usuario
  *@param pRol: Rol del usuario
  *@param pNombreUsuario: Nombre de usuario de la persona
  *@param pContraseña: Contraseña del usuario
  *@param pIdentificadorFuncionario: Identificador del funcionario
  *@param pTipo: Tipo del funcionario
  *@param pFechaIngreso: Fecha de ingreso al trabajo
  *@param pArea: Área de especialidad
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarFuncionario(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, String pNombreUsuario, String pContraseña,
      int pIdentificadorFuncionario, TipoFuncionario pTipo, Date pFechaIngreso, String pArea){
        
    boolean salida = true;
    String tipo = pTipo.name();
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercion;
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
    
    
  /**
  *Método insertarFuncionarioGestionCita: Asocia una cita con un funcionario
  *@param pIdentificadorCita: Identificador de la cita
  *@param pIdentificadorFuncionario: Idnetificador del funcionario
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarFuncionarioGestionaCita(int pIdentificadorCita, int pIdentificadorFuncionario){    
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insertar;
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
