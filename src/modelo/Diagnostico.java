package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;


public class Diagnostico {
 
  private String nombreDiagnostico;
  private NivelDiagnostico nivel;
  private Lista<String> observaciones;
  
  private Lista<Tratamiento> tratamientos;
  
  public Diagnostico(){
  }
  
  public Diagnostico(String pNombreDiagnostico, NivelDiagnostico pNivel){
    observaciones = new Lista<String>();
    tratamientos = new Lista<Tratamiento>();
    setNombreDiagnostico(pNombreDiagnostico);
    setNivel(pNivel);
  }

    /**
     * @return the nombreDiagnostico
     */
    public String getNombreDiagnostico() {
        return nombreDiagnostico;
    }

    /**
     * @param pNombreDiagnostico the nombreDiagnostico to set
     */
    public void setNombreDiagnostico(String pNombreDiagnostico) {
        this.nombreDiagnostico = pNombreDiagnostico;
    }

    /**
     * @return the nivel
     */
    public NivelDiagnostico getNivel() {
        return nivel;
    }

    /**
     * @param pNivel the nivel to set
     */
    public void setNivel(NivelDiagnostico pNivel) {
        this.nivel = pNivel;
    }
    
    public void añadirObservacion (String pObservacion){
      observaciones.add(pObservacion);
    }
    
    public void reemplazarListaObservaciones(Lista<String> pObservaciones){
      observaciones = pObservaciones;
    }
    
    public void añadirTratamiento (Tratamiento pTratamiento){
      tratamientos.add(pTratamiento);
    }
    
    
    public String toString(){
        String mensaje="";
        mensaje="Nombre"+getNombreDiagnostico()+"\n";
        mensaje+="Nivel: "+getNivel()+"\n";
        for (int indice=0;indice!=observaciones.getSize();indice++){
            mensaje+=observaciones.get(indice).toString()+"\n";
        }
        return mensaje;
    }
    
    public boolean insertarDiagnostico(String pNombreDiagnostico, NivelDiagnostico pNivel){
      boolean salida = true;
      String nivel = pNivel.name();
      Conexion nuevaConexion = new Conexion();
      Connection conectar = nuevaConexion.conectar();
      PreparedStatement insercionDiagnostico;
      try{
          insercionDiagnostico = conectar.prepareStatement("INSERT INTO diagnostico VALUES (?,?)");
          insercionDiagnostico.setString(1, pNombreDiagnostico);
          insercionDiagnostico.setString(2, nivel);
          insercionDiagnostico.execute();

      }
          catch(SQLException errorBaseDatos){
      JOptionPane.showMessageDialog(null, "Favor verifique los datos");
    }
      catch(Exception error){
        salida = false; 
        JOptionPane.showMessageDialog(null, "Favor verifique los datos");
      }
      return salida;
    }
    
    public boolean insertarDiagnosticoObservaciones(String pObservacion){
      boolean salida = true;
      añadirObservacion(pObservacion);
      Conexion nuevaConexion = new Conexion();
      Connection conectar = nuevaConexion.conectar();
      PreparedStatement insercionObservaciones;
      try{
          insercionObservaciones = conectar.prepareStatement("INSERT INTO diagnostico_observaciones VALUES (?,?)");
          insercionObservaciones.setString(1, nombreDiagnostico);
          System.err.println("B: " + nombreDiagnostico);
          insercionObservaciones.setString(2, pObservacion);   
          insercionObservaciones.execute();
         
      }
          catch(SQLException errorBaseDatos){
      JOptionPane.showMessageDialog(null, "Favor verifique los datos");
    }
      catch(Exception error){
        salida = false;      
        //System.out.println("ERROR: " + error);
        //error.printStackTrace();
        JOptionPane.showMessageDialog(null, "Favor verifique los datos");
      }
      return salida;
    }
        
    public boolean insertarDiagnosticoTratamiento(Tratamiento pTratamiento){
      boolean salida = true;
      añadirTratamiento(pTratamiento);
      Conexion nuevaConexion = new Conexion();
      Connection conectar = nuevaConexion.conectar();
      PreparedStatement insercionTratamiento;
      try{
          insercionTratamiento = conectar.prepareStatement("INSERT INTO diagnostico_contiene_tratamiento VALUES (?,?)");
          insercionTratamiento.setString(1, pTratamiento.getNombreTratamiento());
          insercionTratamiento.setString(2, nombreDiagnostico);   
          insercionTratamiento.execute();
      }
          catch(SQLException errorBaseDatos){
      JOptionPane.showMessageDialog(null, "Favor verifique los datos");
    }
      catch(Exception error){
        salida = false;    
        JOptionPane.showMessageDialog(null, "Favor verifique los datos");
      }
      return salida;
    }
}
