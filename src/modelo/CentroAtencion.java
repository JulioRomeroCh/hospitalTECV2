
package modelo;

import java.util.Date;


public class CentroAtencion {
  
  private static Lista<String> tiposCentro = new Lista<String>();
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
  
  public CentroAtencion(String pNombre, String pLugar, int pCapacidad, int pIndice){
    empleados = new Lista<Funcionario>();
    pacientes = new Lista<Paciente>();
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
    
    public void añadirDoctor (String pCedula, String pNombre, String pApellido1, String pApellido2, String pRol, 
      String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario, TipoFuncionario pTipo,
      Date pFechaIngreso, int pIndice, int pCodigoDoctor, Lista<String> pEspecialidades){
        
      Doctor nuevoDoctor = new Doctor(pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, 
          pContraseña, pIdentificadorFuncionario, pTipo, pFechaIngreso, pIndice, pCodigoDoctor);
      
      nuevoDoctor.reemplazarListaEspecialidades(pEspecialidades);
      
      empleados.add(nuevoDoctor);
    }
    
    public void añadirSecretario(String pCedula, String pNombre, String pApellido1, String pApellido2,
        String pRol, String pNombreUsuario, String pContraseña, int pIdentificadorFuncionario,
        TipoFuncionario pTipo, Date pFechaIngreso, int pIndice, int pCodigoSecretario){
    
      Secretario nuevoSecretario = new Secretario(pCedula, pNombre, pApellido1, pApellido2, pRol,
          pNombreUsuario, pContraseña, pIdentificadorFuncionario, pTipo, pFechaIngreso, pIndice,
          pCodigoSecretario);
      
      empleados.add(nuevoSecretario);
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
        for (int indice=0;indice!=empleados.getSize();indice++){
            mensaje+=empleados.get(indice).toString()+"\n";
        }
        
        for (int indice=0;indice!=pacientes.getSize();indice++){
            mensaje+=pacientes.get(indice).toString()+"\n";
        }
        return mensaje;
    }
    
}
