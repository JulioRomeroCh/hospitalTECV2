package dao;

//Imports fundametales
import com.csvreader.CsvWriter;
import static j2html.TagCreator.style;
import static j2html.TagCreator.table;
import static j2html.TagCreator.td;
import static j2html.TagCreator.tr;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Exportable;
import modelo.Paciente;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


/**
 * Clase ReportesDAO: Clase encargada de crear los documentos CSV, PDF y HTMK con la información de un una consulta en particular.
 *     Dicha consulta se transforma y se añade a un archivo local.
 * 
 * @author José Ignacio Blanco
 * @author Kevin Rojas Salazar
 * @author Julio Romero Chacón
 */

public class ReportesDAO implements Exportable{
  /**
  * Método que crea la estructura del documento csv, coloca los resultados de las consultas en forma de filas y se añade formato, 
  *     formando una tabla. Seguidamente guarda el documento en una dirección local.
  * @param resultado: ResultSet que representa la información de una consultada dada.
  */ 
  public void exportarCSV(ResultSet resultado) {
    String salidaArchivo="Consultar.csv";
    boolean existe= new File(salidaArchivo).exists();
    //si existe el archivo entonces lo borra.
    if (existe){
      File archivoConsulta=new File(salidaArchivo);
      archivoConsulta.delete();
    }
    try{
      //crea el archivo.
      CsvWriter salidaCSV= new CsvWriter(new FileWriter(salidaArchivo,true), ',');
            
      //se recorre la lista y lo escribe en el archivo csv.
      while(resultado.next()){  
        for(int indice = 1; indice!= resultado.getMetaData().getColumnCount()+1; indice++){
          salidaCSV.write(resultado.getObject(indice).toString());            
        } 
        salidaCSV.endRecord();
      }
      salidaCSV.close();
    }       
    catch(IOException e){
      e.printStackTrace();
    } 
    catch (SQLException ex) {
      Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  /**
  * Método que crea la estructura del documento pdf, coloca los resultados de las consultas en forma de filas y se añade formato, 
  *     formando una tabla. Seguidamente guarda el documento en una dirección local.
  * @param resultado: ResultSet que representa la información de una consultada dada.
  */ 
  public void exportarPDF(ResultSet resultado) {           
    try (PDDocument documento = new PDDocument ()) {
    PDPage pagina = new PDPage(PDRectangle.A0);
    documento.addPage(pagina);  
    //Texto
    PDPageContentStream contenido = new PDPageContentStream (documento, pagina);          
    //Ciclo for para imprimir los datos en PDF
    // ResultSet datosTotalesCursosYCreditos = selecionarCursosYCreditosTotalesPlanEstudios(numeroPlan);
    int pixelesX = 80;
    int pixelesY = 700;
    while(resultado.next()){ 
      pixelesX = 80;
      for(int indice = 1; indice!=resultado.getMetaData().getColumnCount()+1; indice++){  
        contenido.beginText();
        contenido.setFont(PDType1Font.TIMES_ROMAN, 11);
        contenido.moveTextPositionByAmount(pixelesX, pixelesY);
        contenido.showText(" " + resultado.getObject(indice));
        contenido.endText();
        pixelesX += 120;
      }     
      pixelesY -= 50;
    }
    contenido.close();
    documento.save("ConsultaPDF.pdf");
    } 
    catch (IOException ex) {        
      Logger.getLogger(ReportesDAO.class.getName()).log(Level.SEVERE, null, ex);
    } 
    catch (SQLException ex) {        
      Logger.getLogger(ReportesDAO.class.getName()).log(Level.SEVERE, null, ex);
    }        
  }
  /**
  * Método que crea la estructura del documento html, coloca los resultados de las consultas en forma de filas y se añade formato. 
  *     Seguidamente guarda el documento en una dirección local.
  * @param resultado: ResultSet que representa la información de una consultada dada.
  */       
  public void exportarHTML(ResultSet resultado) {
    String html2= style("table, th, td { border: 1px solid black; }" + "\ntbody tr:nth-child(odd) { background-color: #06FCD3 }" + "\nth, td { padding: 10px }").render();
    try {
      while (resultado.next()){
        for (int indice=1;indice!=resultado.getMetaData().getColumnCount()+1;indice++){
          html2 +=   
          table().with(   
          tr().with(
          td().withText(resultado.getObject(indice).toString())
          )
          ).render();
          }
        File archivo= new File("C:\\Users\\KevRj\\OneDrive\\Documents\\NetBeansProjects\\HospitalTEC\\ConsultaHTML.html");
        try{
          BufferedWriter bw=new BufferedWriter(new FileWriter(archivo));
          bw.write(html2);
          bw.close();
        } 
        catch(IOException e){
          System.out.println(e);
        }      
      }          
    } 
    catch (SQLException ex) {
      Logger.getLogger(ReportesDAO.class.getName()).log(Level.SEVERE, null, ex);
    }   
  }    
}
