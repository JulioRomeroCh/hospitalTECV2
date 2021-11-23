package modelo;

//Imports fundamentales
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
*Declaración de la clase Doctor: Guardará la información de un doctor
*/
public class Doctor extends Funcionario {
  
  //Atributos de la clase
  private int codigoDoctor;
  private Lista<String> especialidades; 
  private Cita citaPorAtender;
  
  /**
  *Método Doctor: Método constructor que inicializa todos los atributos por defecto
  */
  public Doctor(){
  }
  
  /**
  *Método Doctor: Constructor sobrecargado, inicializa todos los atributos en un valor específico
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
  *@param pIndice: Índice que busca el área de especialidad
  *@param pCodigoDoctor: Código del doctor
  */
  public Doctor(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
      Date pFechaIngreso, int pIndice, int pCodigoDoctor){
      
    super (pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña, pIdentificadorFuncionario, pTipo, pFechaIngreso, pIndice);     
    especialidades = new Lista<String>();
    setCodigoDoctor(pCodigoDoctor);
  }
  
  /**
  * Método Doctor: Constructor sobrecargado, inicializa todos los atributos en un valor específico
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
  *@param pCodigoDoctor: Código del doctor
  */ 
  public Doctor(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
      Date pFechaIngreso, String pArea, int pCodigoDoctor){
      
    super (pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña, pIdentificadorFuncionario, pTipo, pFechaIngreso, pArea);     
    especialidades = new Lista<String>();
    setCodigoDoctor(pCodigoDoctor);
  }

  //Métodos accesores
  public int getCodigoDoctor() {
    return codigoDoctor;
  }

  public void setCodigoDoctor(int pCodigoDoctor) {
    this.codigoDoctor = pCodigoDoctor;
  }
    
  public void añadirEspecialidad(String pEspecialidad){
    especialidades.add(pEspecialidad);
  }
    
  public void reemplazarListaEspecialidades(Lista<String> pEspecialidades){
    especialidades = pEspecialidades;
  }
    
  public void setCita(Cita pCita){
    this.citaPorAtender = pCita;
  }
    
  public Cita getCita(){
    return this.citaPorAtender;
  }
   
  /**
  * Método toString: llama a los métodos get de los atributos para colocarlos en una misma cadena de caracteres.
  * 
  * @return mensaje: String que posee los valores de cada atributo.
  */
  public String toString (){
    String mensaje=""; 
    mensaje="Código: "+getCodigoDoctor()+"\n";
    mensaje+=super.toString()+"\n";
    for (int indice=0;indice!=especialidades.getSize();indice++){
      mensaje+=especialidades.get(indice)+"\n";
    }
    return mensaje;
  }

  /**
  *Método atenderCita: Atiende una cita de un paciente
  *@param pCitaPorAtender: Cita socilitada
  */
  public void atenderCita(Cita pCitaPorAtender){   
    setCita(pCitaPorAtender);
    getCita().cambiarEstadoCita(this, true);
    getCita().modificarEstadoCita(getCita().getIdentificadorCita(), EstadoCita.REALIZADA);
    getCita().getBitacora().añadirCambioEstadoCita();
    getCita().getBitacora().insertarBitacoraCitaEstado(getCita().getIdentificadorCita(), EstadoCita.REALIZADA);
  }
  
  /**
  *Método añadirObservacionCita: Añade una observación a un diagnóstico registrado en una cita
  *@param pCitaPorAtender: Cita socilitada
  *@param pDiagnostico: Diagnóstico registrado
  *@param pObservacion: Observación sobre el diagnóstico
  *@param pTratamiento: Tratamiento asociado al diagnóstico
  */  
  public void añadirObservacionCita(Cita pCitaPorAtender, Diagnostico pDiagnostico,String pObservacion, Tratamiento pTratamiento){
    pDiagnostico.añadirObservacion(pObservacion);
    pDiagnostico.añadirTratamiento(pTratamiento);
    pCitaPorAtender.asociarDiagnostico(pDiagnostico);       
    pDiagnostico.insertarDiagnosticoObservaciones(pObservacion);
    pCitaPorAtender.insertarCitaDiagnostico(pDiagnostico.getNombreDiagnostico());
  }
    
  /**
  *Método hospitalizar: Hospitaliza a un paciente en una cita
  *@param pIdetificadorHospitalizacion: Identifiacdor de la hospitalización
  *@param pFechaFin: Fecha de salida del paciente
  *@param pCitaPorAtender: Cita en la cual se hospitalizó al paciente
  *@param pCentro: Centro de atención, donde se hospitalizará el paciente
  *@param pObservacion: Observación asociada a la hospitalización
  *@param pTratamiento: Tratamiento asociado a la hospitalización
  */
  public void hospitalizar (int pIdetificadorHospitalizacion, Date pFechaFin, Cita pCitaPorAtender, CentroAtencion pCentro, String pObservacion, Tratamiento pTratamiento){
    Hospitalizacion nuevaHospitalizacion = new Hospitalizacion(pIdetificadorHospitalizacion, pFechaFin, pCitaPorAtender, pCentro, pObservacion, pTratamiento);
    nuevaHospitalizacion.insertarHospitalizacionBD();
  }
    
  /**
  *Método insertarDoctor: Inserta un doctor en la base de datos
  *@param pCedula: Número de cédula del usuario
  *@param pNombre: Nombre del usuario
  *@param pApellido1: Primer apellido del usuario
  *@param pApellido: Segundo apellido del usuario
  *@param pRol: Rol del usuario
  *@param pNombreUsuario: Nombre de usuario de la persona
  *@param pContraseña: Contraseña del usuario
  *@param pIdentificadorFuncionario: Identificador del funcionario
  *@param pTipo: Tipo del funcionario
  *@param pFechaIngreso: Fecha de ingreso al trabajo
  *@param pArea: Área de especialidad
  *@param pCodigoDoctor: Código del doctor
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarDoctor(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
        String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
        Date pFechaIngreso, String pArea, int pCodigoDoctor){
    boolean salida = true;

    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionDoctor;
    PreparedStatement insercionEspecialidades;
    try{
      super.insertarFuncionario(pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña, pIdentificadorFuncionario, pTipo, pFechaIngreso, pArea);
          
      insercionDoctor = conectar.prepareStatement("INSERT INTO doctor VALUES (?,?)");
      insercionDoctor.setInt(1, pCodigoDoctor);
      insercionDoctor.setInt(2, pIdentificadorFuncionario);
      insercionDoctor.execute();
          
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
  *Método insertarEspecialidad: Inserta una especialidad del doctor en la base de datos
  *@param pCodigoDoctor: Código del doctor
  *@param pEspecialidad: Especialidad del médico
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarEspecialidad(int pCodigoDoctor, String pEspecialidad){
    boolean salida = true;
    añadirEspecialidad(pEspecialidad);
      
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionEspecialidad;
        
    try{
      insercionEspecialidad = conectar.prepareStatement("INSERT INTO doctor_especialidades VALUES (?,?)");  
      insercionEspecialidad.setInt(1, pCodigoDoctor);
      insercionEspecialidad.setString(2, pEspecialidad);
      insercionEspecialidad.execute();
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
