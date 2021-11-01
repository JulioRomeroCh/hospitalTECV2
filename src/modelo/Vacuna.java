
package modelo;

public class Vacuna {
 
   private int numeroLote;
   private String nombre;
   private String farmaceutica;
   
   public Vacuna(){
   }
   
   public Vacuna(int pNumeroLote, String pNombre, String pFarmaceutica){
     
     setNumeroLote(pNumeroLote);
     setNombre(pNombre);
     setFarmaceutica(pFarmaceutica);
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
}
  