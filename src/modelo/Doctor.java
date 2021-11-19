package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

public class Doctor extends Funcionario {
  
  private int codigoDoctor;
  private Lista<String> especialidades;
  
  private Cita citaPorAtender;
  
  public Doctor(){
  }
  
  public Doctor(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
      Date pFechaIngreso, int pIndice, int pCodigoDoctor){
      
       super (pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña,
           pIdentificadorFuncionario, pTipo, pFechaIngreso, pIndice);
       
       especialidades = new Lista<String>();
       setCodigoDoctor(pCodigoDoctor);
  }
  
  
 public Doctor(String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
      Date pFechaIngreso, String pArea, int pCodigoDoctor){
      
       super (pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña,
           pIdentificadorFuncionario, pTipo, pFechaIngreso, pArea);
       
       especialidades = new Lista<String>();
       setCodigoDoctor(pCodigoDoctor);
  }

    /**
     * @return the codigoDoctor
     */
    public int getCodigoDoctor() {
        return codigoDoctor;
    }

    /**
     * @param pCodigoDoctor the codigoDoctor to set
     */
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
   
    public String toString (){
       String mensaje=""; 
       mensaje="Código: "+getCodigoDoctor()+"\n";
       mensaje+=super.toString()+"\n";
       for (int indice=0;indice!=especialidades.getSize();indice++){
           mensaje+=especialidades.get(indice)+"\n";
       }
       return mensaje;
    }

    //REVISAR ESTE MÉTODO
    public void atenderCita(Cita pCitaPorAtender){
     
      setCita(pCitaPorAtender);
      getCita().cambiarEstadoCita(this, true);
      getCita().modificarEstadoCita(getCita().getIdentificadorCita(), EstadoCita.REALIZADA);
      System.out.println("BITACORA: " + getCita().getBitacora().toString());
      getCita().getBitacora().añadirCambioEstadoCita();
      getCita().getBitacora().insertarBitacoraCitaEstado(getCita().getIdentificadorCita(), EstadoCita.REALIZADA);

    }
    
    public void añadirObservacionCita(Cita pCitaPorAtender, Diagnostico pDiagnostico,String pObservacion, Tratamiento pTratamiento){
                pDiagnostico.añadirObservacion(pObservacion);
                pDiagnostico.añadirTratamiento(pTratamiento);
                pCitaPorAtender.asociarDiagnostico(pDiagnostico);       
                pDiagnostico.insertarDiagnosticoObservaciones(pObservacion);
                pCitaPorAtender.insertarCitaDiagnostico(pDiagnostico.getNombreDiagnostico());
    }
    
    public void hospitalizar (int pIdetificadorHospitalizacion, Date pFechaFin, Cita pCitaPorAtender, CentroAtencion pCentro, String pObservacion, Tratamiento pTratamiento){
      Hospitalizacion nuevaHospitalizacion = new Hospitalizacion(pIdetificadorHospitalizacion, pFechaFin, pCitaPorAtender, pCentro, pObservacion, pTratamiento);
      nuevaHospitalizacion.insertarHospitalizacionBD();
    }
    
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
      catch(Exception error){
        salida = false;        
      }
      return salida;
    }
     
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
      catch(Exception error){
        salida = false;        
      }
      return salida;
    }
}
