package modelo;

//Imports fundamentales
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
* Declaración de la clase Cita: Almacenará toda la información respectiva a una cita
* @author José Ignacio Blanco
* @author Julio Romero
* @author Kevin Rojas
*/
public class Cita {
    
  //Atributos de la clase
  private int identificadorCita;
  private Date fecha;
  private Date hora;
  private String area;
  private String observacion;
  private Bitacora bitacora;
  private EstadoCita estado;
  
  private Funcionario encargadoCita;
  
  private Lista<Diagnostico> diagnosticos;
  
  private Paciente paciente;
  
  
  /**
  *Método Cita: Método constructor que inicializa todos los atributos por defecto
  */
  public Cita(){
  }
  
  /**
  *Método Cita: Constructor sobrecargado, inicializa todos los atributos en un valor específico
  *@param pIdentificadorCita: Identificador de la cita
  *@param pFecha: Fecha de la cita
  *@param pHora: Hora de la cita
  *@param pArea: Especialidad de la cita
  *@param pObservacion: Observación en especial sobre la cita
  *@param pEstado: Algún estado de la cita de los disponibles
  */
  public Cita(int pIdentificadorCita, Date pFecha, Date pHora, String pArea, String pObservacion, EstadoCita pEstado){
    diagnosticos = new Lista<Diagnostico>();
    setIdentificadorCita(pIdentificadorCita);
    setFecha(pFecha);
    setHora(pHora);
    setArea(pArea);
    setObservacion(pObservacion);
  }

  //Métodos accesores
  public int getIdentificadorCita() {
    return identificadorCita;
  }

  public void setIdentificadorCita(int pIdentificadorCita) {
    this.identificadorCita = pIdentificadorCita;
  }

  public String getFecha() {
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/mm/dd");
    return formatoFecha.format(this.fecha);
  }
  
  public void setFecha(Date pFecha) {
    this.fecha = pFecha;
  }

  public String getHora() {
    SimpleDateFormat formatoHoras = new SimpleDateFormat("HH:mm:ss");
    return formatoHoras.format(this.hora);
  }

  public void setHora(Date pHora) {
    this.hora = pHora;
  }
    
  public Lista<Diagnostico> getDiagnosticos(){
    return diagnosticos;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String pArea) {
    this.area = pArea;
  }

  public String getObservacion() {
    return observacion;
  }

  public void setObservacion(String pObservacion) {
    this.observacion = pObservacion;
  }

  public Bitacora getBitacora() {
    return bitacora;
  }

  public void setBitacora(Bitacora pBitacora) {
    this.bitacora = pBitacora;
  }

  public void añadirDiagnostico(String pNombreDiagnostico, NivelDiagnostico pNivel){
    Diagnostico nuevoDiagnostico = new Diagnostico(pNombreDiagnostico, pNivel);
    diagnosticos.add(nuevoDiagnostico);
  }
    
  public void setEncargadoCita(Funcionario pFuncionario){
    encargadoCita = pFuncionario;
  }
    
  public Funcionario getEncargadoCita(){
     return encargadoCita;
  }
    
  Lista<String> observaciones = new Lista<String>();
  public void crearObservacionesDiagnostico(String pElemento){
    observaciones.add(pElemento);
  }
    
  public void registrarDiagnostico(String pNombre){
    for (int indice = 0; indice != diagnosticos.getSize(); indice++){
      if(pNombre.equals(diagnosticos.get(indice).getNombreDiagnostico())){
        diagnosticos.get(indice).reemplazarListaObservaciones(observaciones);
        break;
      }
    }
  }
    
  public void asociarDiagnostico (Diagnostico pDiagnostico){
    diagnosticos.add(pDiagnostico);
  }
    
  public void registrarTratamientos(String pNombreDiagnostico, Tratamiento pTratamiento){     
    for (int indice = 0; indice != diagnosticos.getSize(); indice++){
      if (diagnosticos.get(indice).getNombreDiagnostico().equals(pNombreDiagnostico)){
        diagnosticos.get(indice).añadirTratamiento(pTratamiento);
      }
    }
  }
    
 /**
  * Método toString: llama a los métodos get de los atributos para colocarlos en una misma cadena de caracteres.
  * 
  * @return mensaje: String que posee los valores de cada atributo.
  */
  @Override
    public String toString(){
       String mensaje="";
       mensaje="Identificador: "+getIdentificadorCita()+"\n";
       mensaje+="Fecha: "+getFecha()+"\n";
       mensaje+="Hora: "+getHora()+"\n";
       mensaje+="Área: "+getArea()+"\n";
       mensaje+="Observación: "+getObservacion()+"\n";
       
       for (int indice=0;indice!=diagnosticos.getSize();indice++){
           mensaje+=diagnosticos.get(indice).getNombreDiagnostico()+"\n";
       }
       return mensaje;
    }
    
  public EstadoCita getEstado() {
    return estado;
  }

  public void setEstado(EstadoCita pEstado) {
    this.estado = pEstado;
  }
    
  public void setPaciente(Paciente pPaciente){
    this.paciente = pPaciente;
  }
    
  public Paciente getPaciente(){
    return this.paciente;
  }
 
  /**
  *Método cambiarEstadoCita: Cambia el estado de la cita según el rol y la situación
  *@param usuario: Usuario encargado de cancelar o solicitar cita
  *@param pFueAtendido: Valor booleano que indica si la cita fue atendida.
  *@return estado: Estado de la cita
  */  
  public EstadoCita cambiarEstadoCita(Usuario usuario, boolean pFueAtendido){
    if (usuario instanceof Funcionario && pFueAtendido == false){
      estado = EstadoCita.CANCELADA_POR_CENTRO_MEDICO;
    }
      
    else if (usuario instanceof Paciente && pFueAtendido == false){
      estado = EstadoCita.CANCELADA_POR_PACIENTE;
    }
      
    else if (usuario instanceof Funcionario && pFueAtendido == true){
      estado = EstadoCita.REALIZADA;
    }
      
    else if (estado.equals(EstadoCita.CANCELADA_POR_CENTRO_MEDICO)){
      estado = EstadoCita.ASIGNADA;
    }
    return estado;
  }

  /**
  *Método insertarCita: Inserta una cita en la base de datos
  *@param pIdentificadorCita: Identificador de la cita
  *@param pFecha: Fecha de la cita
  *@param pHora: Hora de la cita
  *@param pArea: Especialidad de la cita
  *@param pObservacion: Observación en especial sobre la cita
  *@param pEstado: Algún estado de la cita de los disponibles
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarCita(int pIdentificadorCita, Date pFecha, Date pHora, String pArea, String pObservacion, EstadoCita pEstado){
    boolean salida = true;
    String estado = pEstado.name();
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionCita;
    try{
      SimpleDateFormat formato= new SimpleDateFormat("yyy-mm-dd");
      String fechaString = formato.format(pFecha);
      Date fechaDate = formato.parse(fechaString);
      java.sql.Date fechaSQL = new java.sql.Date(fechaDate.getTime());
          
      SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");
      String horaString = formatoHora.format(pHora);
      Date horaDate = formatoHora.parse(horaString);
      java.sql.Time horaSQL = new java.sql.Time(horaDate.getTime());
      insercionCita = conectar.prepareStatement("INSERT INTO cita VALUES (?,?,?,?,?,?)");
      insercionCita.setInt(1, pIdentificadorCita);
      insercionCita.setDate(2, fechaSQL);
      insercionCita.setTime(3, horaSQL);
      insercionCita.setString(4, pArea);
      insercionCita.setString(5, pObservacion);
      insercionCita.setString(6, estado);
      insercionCita.execute();
    }
    catch(SQLException errorBaseDatos){
      JOptionPane.showMessageDialog(null, "Favor verifique los datos");
    } 
    catch(Exception error){
      //System.err.println("Error: " + error);
      //error.printStackTrace();
      salida = false; 
      JOptionPane.showMessageDialog(null, "Favor verifique los datos");
    }
    return salida;
  }

  /**
  *modificarEstadoCita: Modifica el estado de la cita a nivel de base de datos
  *@param pIdentificadorCita: Identificador de la cita
  *@pEstado: Estado de la cita
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean modificarEstadoCita(int pIdentificadorCita, EstadoCita pEstado){
    boolean salida = true;  
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement actualizarEstado;
    try{
      actualizarEstado = conectar.prepareStatement("UPDATE cita SET estado = ? WHERE identificadorCita = ?");
      actualizarEstado.setString(1, pEstado.name());
      actualizarEstado.setInt(2, pIdentificadorCita);
      actualizarEstado.executeUpdate();
    }
    catch(SQLException errorBaseDatos){
      JOptionPane.showMessageDialog(null, "Favor verifique los datos");
    }
    catch(Exception error){
      //error.printStackTrace();
      salida = false;  
      JOptionPane.showMessageDialog(null, "Favor verifique los datos");
    }
    //System.out.println("BANDERA: " + salida);
    return salida;
  }
    
  /**
  *método insertarCitaDiagnostico: Asocia un diagnóstico a la cita a nivel de base de datos
  *@param pNombreDiagnostico: Nombre del diagnóstico
  *@return salida: Valor booleano que indica si se realizó o no la inserción
  */
  public boolean insertarCitaDiagnostico(String pNombreDiagnostico){
    boolean salida = true;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    PreparedStatement insercionDiagnostico;
    try{
      insercionDiagnostico = conectar.prepareStatement("INSERT INTO cita_registra_diagnostico VALUES (?,?)");
      insercionDiagnostico.setString(1, pNombreDiagnostico);
      insercionDiagnostico.setInt(2, identificadorCita);
      //System.err.println("A: " + identificadorCita);
      insercionDiagnostico.execute();
      }
    catch(SQLException errorBaseDatos){
      JOptionPane.showMessageDialog(null, "Favor verifique los datos");
    }
    catch(Exception error){
      salida = false;      
      JOptionPane.showMessageDialog(null, "Favor verifique los datos");
      //System.out.println("ERROR: " + error);
      //error.printStackTrace();
    }
    return salida;
  }


}
