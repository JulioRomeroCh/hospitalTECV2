
package modelo;

import java.util.Date;


public class CentroAtencion {
   
  private int codigo;
  private String nombre;
  private String lugar;
  private int capacidad;
  private String tipo;
  private static int numeroCentros = 0;
  
  private Lista<Funcionario> empleados;
  
  private Lista<Paciente> pacientes;
  
  public CentroAtencion(){
  }
  
  public CentroAtencion(String pNombre, String pLugar, int pCapacidad, String pTipo){
    empleados = new Lista<Funcionario>();
    pacientes = new Lista<Paciente>();
    numeroCentros++;
    setCodigo();
    setNombre(pNombre);
    setLugar(pLugar);
    setCapacidad(pCapacidad);
    setTipo(pTipo);
   
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
     * @param pTipo the tipo to set
     */
    public void setTipo(String pTipo) {
        this.tipo = pTipo;
    }
    
    public void añadirDoctor (String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
      Date pFechaIngreso, String pArea, int pCodigoDoctor, Lista<String> pEspecialidades){
        
      Doctor nuevoDoctor = new Doctor(pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, 
          pContraseña, pIdentificadorFuncionario, pTipo, pFechaIngreso, pArea, pCodigoDoctor);
      
      nuevoDoctor.reemplazarListaEspecialidades(pEspecialidades);
      
      empleados.add(nuevoDoctor);
    }
    
    public void añadirSecretario(String pCedula, String pNombre, String pApellido1, String pApellido2,
        String pRol, String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario,
        TipoFuncionario pTipo, Date pFechaIngreso, String pArea, int pCodigoSecretario){
    
      Secretario nuevoSecretario = new Secretario(pCedula, pNombre, pApellido1, pApellido2, pRol,
          pNombreUsuario, pContraseña, pIdentificadorFuncionario, pTipo, pFechaIngreso, pArea,
          pCodigoSecretario);
      
      empleados.add(nuevoSecretario);
    }
    
    
    public void añadirEnfermero(String pCedula, String pNombre, String pApellido1, String pApellido2,
        String pRol, String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario,
        TipoFuncionario pTipo, Date pFechaIngreso, String pArea, int pCodigoEnfermero,
        boolean pIndicadorPersonasACargo, boolean pIndicadorExperienciaCapacitaciones){
    
      Enfermero nuevoEnfermero = new Enfermero(pCedula, pNombre, pApellido1, pApellido2, pRol,
          pNombreUsuario, pContraseña, pIdentificadorFuncionario, pTipo, pFechaIngreso, pArea,
          pCodigoEnfermero);
      
      nuevoEnfermero.setIndicadorPersonasACargo(pIndicadorPersonasACargo);
      nuevoEnfermero.setIndicadorExperienciaCapacitaciones(pIndicadorExperienciaCapacitaciones);
      
      empleados.add(nuevoEnfermero);
    }
    
    public void añadirPacientes(String pCedula, String pNombre, String pApellido1, String pApellido2,
        String pRol, String pNombreUsuario, String pContraseña, int pIdentificadorPaciente, 
        String pNacionalidad, String pResidencia, Date pFechaNacimiento, TipoSangre pTipoSangre,
        Lista<String> pTelefonos){
    
     Paciente nuevoPaciente = new Paciente(pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario,
         pContraseña, pIdentificadorPaciente, pNacionalidad, pResidencia, pFechaNacimiento, pTipoSangre);
     
     nuevoPaciente.reemplazarListaTelefonos(pTelefonos);
     
     pacientes.add(nuevoPaciente);
    }
    
    public String toString(){
        String mensaje="";
        mensaje="Código"+getCodigo()+"\n";
        mensaje+="Nombre: "+getNombre()+"\n";
        mensaje+="Lugar: "+getLugar()+"\n";
        mensaje+="Capacidad: "+getCapacidad()+"\n";
        mensaje+="Tipo: "+getTipo()+"\n";
        for (int indice=0;indice!=empleados.getSize();indice++){
            mensaje+=empleados.get(indice).toString()+"\n";
        }
        
        for (int indice=0;indice!=pacientes.getSize();indice++){
            mensaje+=pacientes.get(indice).toString()+"\n";
        }
        return mensaje;
    }
    
}
