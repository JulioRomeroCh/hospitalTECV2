
package modelo;


public class CentroAtencion {
   
  private int codigo;
  private String nombre;
  private String lugar;
  private int capacidad;
  private String tipo;
  private static int numeroCentros = 0;
  
  public CentroAtencion(){
  }
  
  public CentroAtencion(String pNombre, String pLugar, int pCapacidad, String pTipo){
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
}
