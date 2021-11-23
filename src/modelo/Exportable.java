package modelo;

//Imports fundamentales
import java.sql.ResultSet;

/*
*Declaración de la interface Exportable: Determina los métodos que serán necesarios para realizar reportes
*/
public interface Exportable {
  /**
  * Método que crea la estructura del documento pdf, coloca los resultados de las consultas en forma de filas y se añade formato, 
  *     formando una tabla. Seguidamente guarda el documento en una dirección local. Método abstracto
  * @param resultado: ResultSet que representa la información de una consultada dada.
  */
  public abstract void exportarPDF(ResultSet resultado);
  
  /**
  * Método que crea la estructura del documento csv, coloca los resultados de las consultas en forma de filas y se añade formato, 
  *     formando una tabla. Seguidamente guarda el documento en una dirección local. Método abstracto.
  * @param resultado: ResultSet que representa la información de una consultada dada.
  */
  public abstract void exportarCSV(ResultSet resultado);
  
  /**
  * Método que crea la estructura del documento html, coloca los resultados de las consultas en forma de filas y se añade formato. 
  *     Seguidamente guarda el documento en una dirección local. Método abstracto
  * @param resultado: ResultSet que representa la información de una consultada dada.
  */
  public abstract void exportarHTML(ResultSet resultado);
}
