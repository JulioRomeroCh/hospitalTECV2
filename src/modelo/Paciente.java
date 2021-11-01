
package modelo;
import java.util.Date;

public class Paciente extends Usuario{
  
  private int identificadorPaciente;
  private String nacionalidad;
  private String residencia;
  private Date fechaNacimiento;
  private TipoSangre tipoSangre;
  private Lista<String> telefonos;
  
  private Lista<Funcionario> encargados;
  
  private Lista<Hospitalizacion> hospitalizaciones;
  
  private Cita cita;
  
  public Paciente(){
  }
  
  public Paciente(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorPaciente, String pNacionalidad,
      String pResidencia, Date pFechaNacimiento, TipoSangre pTipoSangre){
   
    super (pCedula, pNombre, pApellido1, pApellido2, pRol,pNombreUsuario, pContraseña);
    
    telefonos = new Lista<String>();
    setIdentificadorPaciente(pIdentificadorPaciente);
    setNacionalidad(pNacionalidad);
    setResidencia(pResidencia);
    setFechaNacimiento(pFechaNacimiento);
    setTipoSangre(pTipoSangre);
    
  }

    /**
     * @return the identificadorPaciente
     */
    public int getIdentificadorPaciente() {
        return identificadorPaciente;
    }

    /**
     * @param PidentificadorPaciente the identificadorPaciente to set
     */
    public void setIdentificadorPaciente(int PidentificadorPaciente) {
        this.identificadorPaciente = PidentificadorPaciente;
    }

    /**
     * @return the nacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @param pNacionalidad the nacionalidad to set
     */
    public void setNacionalidad(String pNacionalidad) {
        this.nacionalidad = pNacionalidad;
    }

    /**
     * @return the residencia
     */
    public String getResidencia() {
        return residencia;
    }

    /**
     * @param pResidencia the residencia to set
     */
    public void setResidencia(String pResidencia) {
        this.residencia = pResidencia;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param pFechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date pFechaNacimiento) {
        this.fechaNacimiento = pFechaNacimiento;
    }

    /**
     * @return the tipoSangre
     */
    public TipoSangre getTipoSangre() {
        return tipoSangre;
    }

    public Cita getCita() {
        return cita;
    }

    /**
     * @param tipoSangre the tipoSangre to set
     */
    public void setTipoSangre(TipoSangre pTipoSangre) {
        this.tipoSangre = pTipoSangre;
    }

    /**
     * @param pTelefono the telefonos to set
     */
    public void setTelefono(String pTelefono) {
        telefonos.add(pTelefono);
    }
    
    public void reemplazarListaTelefonos(Lista<String> pTelefonos){
      telefonos = pTelefonos;
    }
    
    public void añadirEncargados(Funcionario pEncargado){
      encargados.add(pEncargado);
    }
    
    public void añadirHospitalizacion (int pIdentificadorHospitalizacion, Date pFechaInicio,
        Date pFechaFin){
    
      Hospitalizacion nuevaHospitalizacion = new Hospitalizacion(pIdentificadorHospitalizacion, 
          pFechaInicio, pFechaFin);
      
      hospitalizaciones.add(nuevaHospitalizacion);
    }
    
    public void setCita(int pIdentificadorCita, Date pFecha, Date pHora, String pArea,
        String pObservacion){
        
      Cita nuevaCita = new Cita(pIdentificadorCita, pFecha, pHora, pObservacion);
      
      cita = nuevaCita;
    }
  
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
        mensaje+="Hospitalizaciones: "+"\n";
        for (int indice=0; indice!=hospitalizaciones.getSize();indice++){
            mensaje+=hospitalizaciones.get(indice).toString()+"\n";
        }
        mensaje+="Encargados: ";
        for (int indice=0; indice!=encargados.getSize();indice++){
            mensaje+=encargados.get(indice).toString()+"\n";
        }
        mensaje+="Cita asociada: ";
        mensaje +="Cita: "+getCita().toString()+"\n";
        return mensaje;
    }
  
}