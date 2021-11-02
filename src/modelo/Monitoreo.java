
package modelo;

import java.text.SimpleDateFormat;

public class Monitoreo {

private EstadoCita estadoActual;

private Lista<EstadoCita> estadosCita;

private Cita citaMedica;
private Bitacora registro;

public Usuario usuario;
    
public Monitoreo(){
}

public Monitoreo(EstadoCita pEstado){
    
  estadosCita = new Lista<EstadoCita>();
  estadosCita.add(EstadoCita.REGISTRADA);
 
  setEstado(EstadoCita.REGISTRADA);
}

    /**
     * @return the fechaAplicacion
     */
    public EstadoCita getEstado() {
       return estadoActual;
    }
    
    public Cita getCitaMedica() {
        return citaMedica;
    }
    
    public Bitacora getRegistro() {
        return registro;
    }

    /**
     * @param pEstado the fechaAplicacion to set
     */
    public void setEstado(EstadoCita pEstado) {
        estadoActual = pEstado;
    }
    
    public void setCita(Cita pCita){
      citaMedica = pCita;
    }
    
    public void setRegistro(Bitacora pRegistro){
      registro = pRegistro;
    }
    
    public void setUsuario (Usuario pUsuario){
      usuario = pUsuario;
    }
    
    public Usuario getUsuario(){
      return usuario;
    }
    
    
    public void cambiarEstadoCita(boolean pFueAtendido){
      if (usuario instanceof Funcionario && pFueAtendido == false){
        estadoActual = EstadoCita.CANCELADA_POR_CENTRO_MEDICO;
      }
      
      else if (usuario instanceof Paciente && pFueAtendido == false){
        estadoActual = EstadoCita.CANCELADA_POR_PACIENTE;
      }
      
      else if (usuario instanceof Funcionario && pFueAtendido == true){
        estadoActual = EstadoCita.REALIZADA;
      }
      
      else if (estadoActual.equals(EstadoCita.CANCELADA_POR_CENTRO_MEDICO)){
        estadoActual = EstadoCita.ASIGNADA;
      }
      
      estadosCita.add(estadoActual);
      registro.registrarNuevaFecha();
    }
    
    
@Override
    public String toString(){
        String mensaje="";
        mensaje="Estado: "+getEstado()+"\n";
        mensaje+="Cita: "+getCitaMedica().toString()+"\n";
        mensaje+="Bitácora: "+getRegistro().toString()+"\n";
        return mensaje;
    }

}
