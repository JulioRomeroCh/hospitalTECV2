
package modelo;
import java.util.Date;

public class Paciente extends Usuario{
  
  private int identificadorPaciente;
  private String nacionalidad;
  private String residencia;
  private Date fechaNacimiento;
  private TipoSangre tipoSangre;
  private Lista<String> telefonos;
  
  
  private Lista<Vacuna> listaVacunas;
  private Lista<Cita> citas;
  
  
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
    
    public void añadirVacuna(Vacuna pVacuna){
      listaVacunas.add(pVacuna);
    }
    
    public void añadirCita(Cita pCita){
      citas.add(pCita);
    }

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
  
}