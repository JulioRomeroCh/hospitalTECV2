
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;


public class CentroAtencion {
  
  private static Lista<String> tiposCentro = new Lista<String>();
  private int codigo;
  private String nombre;
  private String lugar;
  private int capacidad;
  private String tipo;
  private static int numeroCentros = 0;
  
 private Lista<Usuario> usuarios;
  
  public CentroAtencion(){
  }
  
  public CentroAtencion(String pNombre, String pLugar, int pCapacidad, int pIndice){

    tiposCentro.clear();
    tiposCentro.add("Hospital");
    tiposCentro.add("Clínica");
    tiposCentro.add("EBAIS");

    numeroCentros++;
    setCodigo();
    setNombre(pNombre);
    setLugar(pLugar);
    setCapacidad(pCapacidad);
    setTipo(pIndice);
   
}
  
  public CentroAtencion(String pNombre, String pLugar, int pCapacidad){

    tiposCentro.clear();
    tiposCentro.add("Hospital");
    tiposCentro.add("Clínica");
    tiposCentro.add("EBAIS");

    numeroCentros++;
    setCodigo();
    setNombre(pNombre);
    setLugar(pLugar);
    setCapacidad(pCapacidad);
   
}

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo() {
        this.codigo = numeroCentros;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param pNombre the nombre to set
     */
    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

    /**
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * @param pLugar the lugar to set
     */
    public void setLugar(String pLugar) {
        this.lugar = pLugar;
    }

    /**
     * @return the capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * @param pCapacidad the capacidad to set
     */
    public void setCapacidad(int pCapacidad) {
        this.capacidad = pCapacidad;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param pIndice the tipo to set
     */
    public void setTipo(int pIndice) {
        for (int indice = 0; indice != tiposCentro.getSize(); indice++){
          if (indice == pIndice){
            this.tipo = tiposCentro.get(pIndice);
            break;
          }
        }
    }
    
    public void setTipo(String pTipo){
      this.tipo = pTipo;
    }
    
    public void añadirDoctor (String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
      Date pFechaIngreso, int pIndice, int pCodigoDoctor, Lista<String> pEspecialidades){
        
      Doctor nuevoDoctor = new Doctor(pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, 
          pContraseña, pIdentificadorFuncionario, pTipo, pFechaIngreso, pIndice, pCodigoDoctor);
      
      nuevoDoctor.reemplazarListaEspecialidades(pEspecialidades);
      
      usuarios.add(nuevoDoctor);
    }
    
    public void añadirSecretario(String pCedula, String pNombre, String pApellido1, String pApellido2,
        String pRol, String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario,
        TipoFuncionario pTipo, Date pFechaIngreso, int pIndice, int pCodigoSecretario){
    
      Secretario nuevoSecretario = new Secretario(pCedula, pNombre, pApellido1, pApellido2, pRol,
          pNombreUsuario, pContraseña, pIdentificadorFuncionario, pTipo, pFechaIngreso, pIndice,
          pCodigoSecretario);
      
      usuarios.add(nuevoSecretario);
    }
    
    
    public void añadirEnfermero(String pCedula, String pNombre, String pApellido1, String pApellido2,
        String pRol, String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario,
        TipoFuncionario pTipo, Date pFechaIngreso, int pIndice, int pCodigoEnfermero,
        boolean pIndicadorPersonasACargo, boolean pIndicadorExperienciaCapacitaciones){
    
      Enfermero nuevoEnfermero = new Enfermero(pCedula, pNombre, pApellido1, pApellido2, pRol,
          pNombreUsuario, pContraseña, pIdentificadorFuncionario, pTipo, pFechaIngreso, pIndice,
          pCodigoEnfermero);
      
      nuevoEnfermero.setIndicadorPersonasACargo(pIndicadorPersonasACargo);
      nuevoEnfermero.setIndicadorExperienciaCapacitaciones(pIndicadorExperienciaCapacitaciones);
      
      usuarios.add(nuevoEnfermero);
    }
    
    public void añadirPacientes(String pCedula, String pNombre, String pApellido1, String pApellido2,
        String pRol, String pNombreUsuario, String pContraseña, int pIdentificadorPaciente, 
        String pNacionalidad, String pResidencia, Date pFechaNacimiento, TipoSangre pTipoSangre,
        Lista<String> pTelefonos){
    
     Paciente nuevoPaciente = new Paciente(pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario,
         pContraseña, pIdentificadorPaciente, pNacionalidad, pResidencia, pFechaNacimiento, pTipoSangre);
     
     nuevoPaciente.reemplazarListaTelefonos(pTelefonos);
     
     usuarios.add(nuevoPaciente);
    }
    
    public void añadirTipoCentro(String pTipo){
      tiposCentro.add(pTipo);
    }
    
  @Override
    public String toString(){
        String mensaje="";
        mensaje="Código"+getCodigo()+"\n";
        mensaje+="Nombre: "+getNombre()+"\n";
        mensaje+="Lugar: "+getLugar()+"\n";
        mensaje+="Capacidad: "+getCapacidad()+"\n";
        mensaje+="Tipo: "+getTipo()+"\n";
        for (int indice=0;indice!=usuarios.getSize();indice++){
            mensaje+=usuarios.get(indice).toString()+"\n";
        }
        return mensaje;
    }
   
    public boolean insertarCentro(String pNombre, String pLugar, int pCapacidad, int pIndice){
      boolean salida = true;
      setTipo(pIndice);
      String tipoCentro = getTipo();
      Conexion nuevaConexion = new Conexion();
      Connection conectar = nuevaConexion.conectar();
      PreparedStatement insercionCentro;
      PreparedStatement insercionCentroTipo;

      try{
          insercionCentro = conectar.prepareStatement("INSERT INTO centroatencion VALUES (?, ?,?,?)");
          insercionCentro.setInt(1, getCodigo());
          insercionCentro.setString(2, pNombre);
          insercionCentro.setString(3,  pLugar);
          insercionCentro.setInt(4, pCapacidad);
          insercionCentro.execute();

          insercionCentroTipo = conectar.prepareStatement("INSERT INTO centro_tipo VALUES (?,?)");
          insercionCentroTipo.setInt(1, getCodigo());
          insercionCentroTipo.setInt(2, pIndice);
          insercionCentroTipo.execute();

      }
      catch(Exception error){
        salida = false;        
      }
      return salida;
    }
    
    public boolean insertarTipoCentro(int pIdentificador, String pTipo){
      boolean salida = true;
      Conexion nuevaConexion = new Conexion();
      Connection conectar = nuevaConexion.conectar();
      PreparedStatement insercionTipoCentro;

      try{
          insercionTipoCentro = conectar.prepareStatement("INSERT INTO tipocentro VALUES (?,?)");
          insercionTipoCentro.setInt(1, pIdentificador);
          insercionTipoCentro.setString(2, pTipo);
          insercionTipoCentro.execute();
      }
      catch(Exception error){
        salida = false;        
      }
      return salida;
    }
    
        
    public boolean insertarUsuarioCentro(String pCedula){
      boolean salida = true;
      Conexion nuevaConexion = new Conexion();
      Connection conectar = nuevaConexion.conectar();
      PreparedStatement insercionUsuario;

      try{
          insercionUsuario = conectar.prepareStatement("INSERT INTO centroatencion_tiene_usuario VALUES (?,?)");
          insercionUsuario.setString(1, pCedula);
          insercionUsuario.setInt(2, getCodigo());
          insercionUsuario.execute();
      }
      catch(Exception error){
        salida = false;        
      }
      return salida;
    }
    
}
