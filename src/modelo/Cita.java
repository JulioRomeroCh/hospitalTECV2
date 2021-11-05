
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
  private EstadoCita estado;
  
  private Funcionario encargadoCita;
  
  private Lista<Diagnostico> diagnosticos;
  
  private Paciente paciente;
  
  public Cita(){
  }
  
  public Cita(int pIdentificadorCita, Date pFecha, Date pHora, String pObservacion, EstadoCita pEstado){
    setIdentificadorCita(pIdentificadorCita);
    setFecha(pFecha);
    setHora(pHora);
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
    
    public void añadirDiagnostico(String pNombreDiagnostico, NivelDiagnostico pNivel, 
        Lista<String> pObservaciones){
    
     Diagnostico nuevoDiagnostico = new Diagnostico(pNombreDiagnostico, pNivel);
     nuevoDiagnostico.reemplazarListaObservaciones(pObservaciones);
     diagnosticos.add(nuevoDiagnostico);
    }
    
    public void setEncargadoCita(Funcionario pFuncionario){
      encargadoCita = pFuncionario;
    }
    
    public Funcionario getEncargadoCita(){
      return encargadoCita;
    }
    
    public void registrarDiagnostico(String pNombre, NivelDiagnostico pNivel, Lista<String> pObservaciones){
      Diagnostico nuevoDiagnostico = new Diagnostico(pNombre, pNivel);
      nuevoDiagnostico.reemplazarListaObservaciones(pObservaciones);
      diagnosticos.add(nuevoDiagnostico);
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

    public boolean insertarCita(int pIdentificadorCita, Date pFecha, Date pHora, String pObservacion, EstadoCita pEstado){
      boolean salida = true;
      String estado = pEstado.name();
      Conexion nuevaConexion = new Conexion();
      Connection conectar = nuevaConexion.conectar();
      PreparedStatement insercionCita;
      try{
          insercionCita = conectar.prepareStatement("INSERT INTO cita VALUES (?,?,?,?,?)");
          insercionCita.setInt(1, pIdentificadorCita);
          insercionCita.setDate(2, (java.sql.Date) pFecha);
          insercionCita.setTime(3, (java.sql.Time) pHora);
          insercionCita.setString(4, pObservacion);
          insercionCita.setString(5, estado);
      }
      catch(Exception error){
        salida = false;        
      }
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
      }
      catch(Exception error){
        salida = false;        
      }
      return salida;
    }
}
