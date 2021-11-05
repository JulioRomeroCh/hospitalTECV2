
package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vacuna {
 
   private int numeroLote;
   private String nombre;
   private String farmaceutica;
   private Date fechaAplicacion;
   
   public Vacuna(){
   }
   
   public Vacuna(int pNumeroLote, String pNombre, String pFarmaceutica, Date pFechaAplicacion){
     
     setNumeroLote(pNumeroLote);
     setNombre(pNombre);
     setFarmaceutica(pFarmaceutica);
     setFechaAplicacion(pFechaAplicacion);
   }

    /**
     * @return the numeroLote
     */
    public int getNumeroLote() {
        return numeroLote;
    }

    /**
     * @param numeroLote the numeroLote to set
     */
    public void setNumeroLote(int pNumeroLote) {
        this.numeroLote = pNumeroLote;
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
     * @return the farmaceutica
     */
    public String getFarmaceutica() {
        return farmaceutica;
    }

    /**
     * @param pFarmaceutica the farmaceutica to set
     */
    public void setFarmaceutica(String pFarmaceutica) {
        this.farmaceutica = pFarmaceutica;
    }

    public String toString(){
        String mensaje="";
        mensaje+="Número de lote: "+getNumeroLote()+"\n";
        mensaje+="Nombre: "+getNombre()+"\n";
        mensaje+="Farmaceutica: "+getFarmaceutica()+"\n";
        return mensaje;
    }

    /**
     * @return the fechaAplicacion
     */
    public String getFechaAplicacion() {
      SimpleDateFormat formatoFecha = new SimpleDateFormat("yyy/mm/dd");
      return formatoFecha.format(fechaAplicacion);
    }

    /**
     * @param pFechaAplicacion the fechaAplicacion to set
     */
    public void setFechaAplicacion(Date pFechaAplicacion) {
        this.fechaAplicacion = pFechaAplicacion;
    }
    
    public boolean insertarVacuna(int pNumeroLote, String pNombre, String pFarmaceutica, Date pFechaAplicacion){
      boolean salida = true;
      Conexion nuevaConexion = new Conexion();
      Connection conectar = nuevaConexion.conectar();
      PreparedStatement insercionVacuna;
      try{
          insercionVacuna = conectar.prepareStatement("INSERT INTO vacuna VALUES (?,?,?,?)");
          insercionVacuna.setInt(1, pNumeroLote);
          insercionVacuna.setString(2, pNombre);
          insercionVacuna.setString(3, pFarmaceutica);
          insercionVacuna.setDate(4, (java.sql.Date) pFechaAplicacion);
      }
      catch(Exception error){
        salida = false;        
      }
      return salida;
    }
}
  