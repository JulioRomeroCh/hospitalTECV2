package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
*Declaración de la clase paciente, la cual amancenará los datos de un paciente, clase especializada
*/
public class Paciente extends Usuario{
  
  //Atributos de la clase
  private int identificadorPaciente;
  private String nacionalidad;
  private String residencia;
  private Date fechaNacimiento;
  private TipoSangre tipoSangre;
  private Lista<String> telefonos;
  private Lista<Vacuna> listaVacunas;
  private Lista<Cita> citas;
  
  /**
  *Método Paciente: Método constructor que inicializa todos los atributos por defecto
  */
  public Paciente(){
  }
  
  /**
  *Método Paciente: Constructor sobrecargado, inicializa todos los atributos en un valor específico
  *@param pCedula: Número de cédula del usuario
  *@param pNombre: Nombre del usuario
  *@param pApellido1: Primer apellido del usuario
  *@param pApellido2: Segundo apellido del usuario
  *@param pRol: Rol del usuario
  *@param pNombreUsuario: Nombre de usuario de la persona
  *@param pContraseña: Contraseña del usuario
  *@param pIdentificadorPaciente: Identificador del paciente
  *@param pNacionalidad: Nacionalidad del paciente
  *@param pResidencia: Lugar de residencia del paciente
  *@param pFechaNacimiento: Fecha de nacimiento del paciente
  *@param pTipoSangre Tipo de sangre del paciente
  */
  public Paciente(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorPaciente, String pNacionalidad,
      String pResidencia, Date pFechaNacimiento, TipoSangre pTipoSangre){
   
    super (pCedula, pNombre, pApellido1, pApellido2, pRol,pNombreUsuario, pContraseña);
    
    telefonos = new Lista<String>();
    citas = new Lista<Cita>();
    listaVacunas = new Lista<Vacuna>();
    setIdentificadorPaciente(pIdentificadorPaciente);
    setNacionalidad(pNacionalidad);
    setResidencia(pResidencia);
    setFechaNacimiento(pFechaNacimiento);
    setTipoSangre(pTipoSangre);
    
  }

  //Métodos accesores
  public int getIdentificadorPaciente() {
    return identificadorPaciente;
  }

  public void setIdentificadorPaciente(int PidentificadorPaciente) {
    this.identificadorPaciente = PidentificadorPaciente;
  }

  public String getNacionalidad() {
    return nacionalidad;
  }

  public void setNacionalidad(String pNacionalidad) {
    this.nacionalidad = pNacionalidad;
  }

  public String getResidencia() {
    return residencia;
  }

  public void setResidencia(String pResidencia) {
    this.residencia = pResidencia;
  }

  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(Date pFechaNacimiento) {
    this.fechaNacimiento = pFechaNacimiento;
  }

  public TipoSangre getTipoSangre() {
    return tipoSangre;
  }

  public void setTipoSangre(TipoSangre pTipoSangre) {
    this.tipoSangre = pTipoSangre;
  }

  public void setTelefono(String pTelefono) {
    telefonos.add(pTelefono);
  }
    
  public void reemplazarListaTelefonos(Lista<String> pTelefonos){
    telefonos = pTelefonos;
  }
    
  public void añadirVacuna(Vacuna pVacuna){
    listaVacunas.add(pVacuna);
  }
    
  public void añadirCita(Cita pCita){
    citas.add(pCita);
  }

  
  /**
  * Método toString: llama a los métodos get de los atributos para colocarlos en una misma cadena de caracteres.
  * 
  * @return mensaje: String que posee los valores de cada atributo.
  */
  @Override
  public String toString(){
    String mensaje="";
    mensaje="Identificador: "+getIdentificadorPaciente()+"\n";
    mensaje+=super.toString()+"\n";
    mensaje+=super.toString()+"\n";
    mensaje+="Nacionalidad: "+getNacionalidad()+"\n";
    mensaje+="Residencia: "+getResidencia()+"\n";
    mensaje+="Fecha Nacimiento: "+getFechaNacimiento().getDay()+"/"+getFechaNacimiento().getMonth()+"/"+getFechaNacimiento().getYear()+"\n";
    mensaje+="Tipo Sangre: "+getTipoSangre()+"\n";
    mensaje+="Teléfonos: ";
    for (int indice=0; indice!=telefonos.getSize();indice++){
      mensaje+=telefonos.get(indice)+"\n";
    }
    return mensaje;
  }
    
  /**
  *Método cancelarCita: El paciente cancela una cita
  *@param pIdentificadorCita: Identificador de la cita
  */
  @Override
  public void cancelarCita(int pIdentificadorCita){
    for(int indice = 0; indice != citas.getSize(); indice++){
      if(citas.get(indice).getIdentificadorCita() == pIdentificadorCita){
        EstadoCita estado = citas.get(indice).cambiarEstadoCita(this, false);
        citas.get(indice).modificarEstadoCita(pIdentificadorCita, estado);
        citas.get(indice).getBitacora().añadirCambioEstadoCita();
        citas.get(indice).getBitacora().insertarBitacoraCitaEstado(pIdentificadorCita, estado);
        citas.get(indice).getBitacora().insertarBitacoraFechayHora(pIdentificadorCita);
        break;
      }    
    }       
  }
   
  /**
  *Método solicitarCita: El cliente solicita una cita
  *@param pIdentificadorCita: Identificador de la cita
  *@param pFecha: Fecha de la cita
  *@param pHora: Hora de la cita
  *@param pArea: Especialidad de la cita
  *@param pObservacion: Observación en especial sobre la cita
  *@param pEstado: Algún estado de la cita de los disponibles
  */ 
  public void solicitarCita(int pIdentificadorCita, Date pFecha, Date pHora, String pArea, String pObservacion, EstadoCita pEstado){
    Cita nuevaCita = new Cita(pIdentificadorCita, pFecha, pHora, pArea, pObservacion, pEstado);
    citas.add(nuevaCita);
    nuevaCita.insertarCita(pIdentificadorCita, pFecha, pHora, pArea, pObservacion, pEstado);
    this.insertarCita(nuevaCita);
    Bitacora nuevaBitacora = new Bitacora(pIdentificadorCita);
    nuevaCita.setBitacora(nuevaBitacora);
    nuevaCita.setEstado(EstadoCita.REGISTRADA);
    nuevaCita.getBitacora().insertarBitacora(pIdentificadorCita);
    nuevaCita.getBitacora().insertarBitacoraCita(pIdentificadorCita);
    nuevaCita.getBitacora().insertarBitacoraCitaEstado(pIdentificadorCita, EstadoCita.REGISTRADA);
  }

  /**
  *Método insertarPaciente: Inserta un paciente en la base de datos
  *@param pCedula: Número de cédula del usuario
  *@param pNombre: Nombre del usuario
  *@param pApellido1: Primer apellido del usuario
  *@param pApellido2: Segundo apellido del usuario
  *@param pRol: Rol del usuario
  *@param pNombreUsuario: Nombre de usuario de la persona
  *@param pContraseña: Contraseña del usuario
  *@param pIdentificadorPaciente: Identificador del paciente
  *@param pNacionalidad: Nacionalidad del paciente
  *@param pResidencia: Lugar de residencia del paciente
  *@param: pFechaNacimiento: Fecha de nacimiento del paciente
  *@pTipoSangre: Tipo de sangre del paciente
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarPaciente(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorPaciente, String pNacionalidad,
      String pResidencia, Date pFechaNacimiento, TipoSangre pTipoSangre){
        
    boolean salida = true;
    String tipoSangre = pTipoSangre.name();
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercion;

    try{
      SimpleDateFormat formato= new SimpleDateFormat("yyy-mm-dd");
      String fechaString = formato.format(pFechaNacimiento);
      Date fechaDate = formato.parse(fechaString);
      java.sql.Date fechaSQL = new java.sql.Date(fechaDate.getTime());
      super.insertarUsuario(pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña);
      CallableStatement insertar = conectar.prepareCall("{CALL insertarPaciente(?,?,?,?,?)}");
      insertar.setInt(1, pIdentificadorPaciente);
      insertar.setString(2, pNacionalidad);
      insertar.setString(3, pResidencia);
      insertar.setDate(4, fechaSQL);
      insertar.setString(5, tipoSangre);
      insertar.execute();
          
      insercion = conectar.prepareStatement("INSERT INTO paciente_usuario VALUES (?,?)");
      insercion.setInt(1, pIdentificadorPaciente);
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
  *Método insertarTelefono: Inserta un teléfono de un paciente a la base de datos
  *@param pTelefono: Número de teléfono del paciente
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarTelefono(String pTelefono){
    boolean salida = true;
    setTelefono(pTelefono);
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionTelefonos;
        
    try{
      insercionTelefonos = conectar.prepareStatement("INSERT INTO paciente_telefono VALUES (?,?)"); 
      insercionTelefonos.setInt(1, identificadorPaciente);
      insercionTelefonos.setString(2, pTelefono); 
      insercionTelefonos.execute();
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
  *Método insertarVacuna: Asocia un paciente a una vacuna a la base de datos
  *@param pVacuna: Vacuna por aplicar
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarVacuna(Vacuna pVacuna){
    boolean salida = true;
    añadirVacuna(pVacuna);
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionVacunas;
        
    try{
      insercionVacunas = conectar.prepareStatement("INSERT INTO paciente_tiene_vacuna VALUES (?,?)"); 
      insercionVacunas.setInt(1, pVacuna.getNumeroLote());
      insercionVacunas.setInt(2, identificadorPaciente); 
      insercionVacunas.execute();
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
  *Método insertarCita: Asocia una cita a un paciente
  *@param pCita: Cita que se le asociará al paciente
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarCita(Cita pCita){
    boolean salida = true;
    añadirCita(pCita);
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionCitaPaciente;
        
    try{
      insercionCitaPaciente = conectar.prepareStatement("INSERT INTO citas_paciente VALUES (?,?)"); 
      insercionCitaPaciente.setInt(1, pCita.getIdentificadorCita());
      insercionCitaPaciente.setInt(2, identificadorPaciente); 
      insercionCitaPaciente.execute();
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