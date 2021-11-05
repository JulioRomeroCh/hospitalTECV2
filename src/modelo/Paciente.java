
package modelo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
      public boolean insertarPaciente(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
          String pNombreUsuario, String pContraseña, int pIdentificadorPaciente, String pNacionalidad,
          String pResidencia, Date pFechaNacimiento, TipoSangre pTipoSangre){
        
      boolean salida = true;

      String tipoSangre = pTipoSangre.name();
      Conexion nuevaConexion = new Conexion();
      Connection conectar = nuevaConexion.conectar();
      PreparedStatement insercion;

      try{
          super.insertarUsuario(pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña);
          CallableStatement insertar = conectar.prepareCall("{CALL insertarPaciente(?,?,?,?,?)}");
          insertar.setInt(1, pIdentificadorPaciente);
          insertar.setString(2, pNacionalidad);
          insertar.setString(3, pResidencia);
          insertar.setDate(4, (java.sql.Date) pFechaNacimiento);
          insertar.setString(5, tipoSangre);
          
          insercion = conectar.prepareStatement("INSERT INTO paciente_usuario VALUES (?,?)");
          insercion.setInt(1, pIdentificadorPaciente);
          insercion.setString(2, pCedula);
          
      }
      catch(Exception error){
        salida = false;        
      }
      return salida;
    }
      
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
      }
      catch(Exception error){
        salida = false;        
      }
      return salida;
    }
    
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
      }
      catch(Exception error){
        salida = false;        
      }
      return salida;
    }
    
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
      }
      catch(Exception error){
        salida = false;        
      }
      return salida;
    }
  
}