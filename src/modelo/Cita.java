
package modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cita {
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
  
  public Cita(){
  }
  
  public Cita(int pIdentificadorCita, Date pFecha, Date pHora, String pArea, String pObservacion, EstadoCita pEstado){
    diagnosticos = new Lista<Diagnostico>();
    setIdentificadorCita(pIdentificadorCita);
    setFecha(pFecha);
    setHora(pHora);
    setArea(pArea);
    setObservacion(pObservacion);
  }

    /**
     * @return the identificadorCita
     */
    public int getIdentificadorCita() {
        return identificadorCita;
    }

    /**
     * @param pIdentificadorCita the identificadorCita to set
     */
    public void setIdentificadorCita(int pIdentificadorCita) {
        this.identificadorCita = pIdentificadorCita;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/mm/dd");
        return formatoFecha.format(this.fecha);
    }

    /**
     * @param pFecha the fecha to set
     */
    public void setFecha(Date pFecha) {
        this.fecha = pFecha;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        SimpleDateFormat formatoHoras = new SimpleDateFormat("HH:mm:ss");
        return formatoHoras.format(this.hora);
    }

    /**
     * @param pHora the hora to set
     */
    public void setHora(Date pHora) {
        this.hora = pHora;
    }
    
    public Lista<Diagnostico> getDiagnosticos(){
        return diagnosticos;
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

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param pObservacion the observacion to set
     */
    public void setObservacion(String pObservacion) {
        this.observacion = pObservacion;
    }
    
        /**
     * @return the bitacora
     */
    public Bitacora getBitacora() {
        return bitacora;
    }

    /**
     * @param bitacora the bitacora to set
     */
    public void setBitacora(Bitacora pBitacora) {
        this.bitacora = pBitacora;
    }
    
    //Admin
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
    
    //Llamar en el botón de Módulo Doctor, el que está a la par de Observaciones
    Lista<String> observaciones = new Lista<String>();
    public void crearObservacionesDiagnostico(String pElemento){
      observaciones.add(pElemento);
    }
    
    //Doctor
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

    /**
     * @return the estado
     */
    public EstadoCita getEstado() {
        return estado;
    }

    /**
     * @param pEstado the estado to set
     */
    public void setEstado(EstadoCita pEstado) {
        this.estado = pEstado;
    }
    
    public void setPaciente(Paciente pPaciente){
      this.paciente = pPaciente;
    }
    
    public Paciente getPaciente(){
      return this.paciente;
    }
  
    public EstadoCita cambiarEstadoCita(Usuario usuario, boolean pFueAtendido){
      //boolean bandera = true;
      if (usuario instanceof Funcionario && pFueAtendido == false){
        estado = EstadoCita.CANCELADA_POR_CENTRO_MEDICO;
        //bandera = false;
      }
      
      else if (usuario instanceof Paciente && pFueAtendido == false){
        estado = EstadoCita.CANCELADA_POR_PACIENTE;
        //bandera = false;
      }
      
      else if (usuario instanceof Funcionario && pFueAtendido == true){
        estado = EstadoCita.REALIZADA;
      }
      
      else if (estado.equals(EstadoCita.CANCELADA_POR_CENTRO_MEDICO)){
        estado = EstadoCita.ASIGNADA;
      }
      return estado;
    }

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
      catch(Exception error){
          System.err.println("Error: " + error);
          error.printStackTrace();
        salida = false;        
      }
      return salida;
    }
    
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
        
      catch(Exception error){
        error.printStackTrace();
        salida = false;        
      }
      System.out.println("BANDERA: " + salida);
      return salida;
    }
    
    public boolean insertarCitaDiagnostico(String pNombreDiagnostico){
      boolean salida = true;
      Conexion nuevaConexion = new Conexion();
      Connection conectar = nuevaConexion.conectar();
      PreparedStatement insercionDiagnostico;
      try{
          insercionDiagnostico = conectar.prepareStatement("INSERT INTO cita_registra_diagnostico VALUES (?,?)");
          insercionDiagnostico.setString(1, pNombreDiagnostico);
          insercionDiagnostico.setInt(2, identificadorCita);
          System.err.println("A: " + identificadorCita);
          insercionDiagnostico.execute();
      }
      catch(Exception error){
        salida = false;      
          System.out.println("ERROR: " + error);
          error.printStackTrace();
      }
      return salida;
    }


}
