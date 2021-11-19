package modelo;

import java.sql.ResultSet;

public interface Exportable {
 
  public abstract void exportarPDF(ResultSet resultado);
  public abstract void exportarCSV(ResultSet resultado);
  public abstract void exportarHTML(ResultSet resultado);
}
