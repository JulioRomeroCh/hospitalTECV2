/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import static j2html.TagCreator.style;
import static j2html.TagCreator.table;
import static j2html.TagCreator.td;
import static j2html.TagCreator.th;
import static j2html.TagCreator.tr;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author jului
 */
public class Html {
   public static void exportarHTML(ArrayList<Usuario> pUsuarios) throws IOException{

    String html2= style("table, th, td { border: 1px solid black; }" +
     "\ntbody tr:nth-child(odd) { background-color: #06FCD3 }" +
        "\nth, td { padding: 10px }").render();
    for (int indice=0;indice!=pUsuarios.size();indice++){
     html2 +=   
      table().with(   
        tr().with(
            th().withText("Persona")
            ),
            tr().with(
                td().withText("Nombre: "+pUsuarios.get(indice).getNombre())
            ),
        
            tr().with(
                td().withText("Telefono: "+pUsuarios.get(indice).getApellido1())
            ),
            tr().with(
                td().withText("Correo: "+pUsuarios.get(indice).getApellido2())
            )
            ).render();
    }
      File archivo= new File("C:\\Users\\jului\\Desktop\\APIS PP2 POO\\ejemplo.html");
      System.out.println(html2);
       try{
           BufferedWriter bw=new BufferedWriter(new FileWriter(archivo));
           bw.write(html2);
           bw.close();
       } catch(IOException e){
           System.out.println(e);
       }
}
}
