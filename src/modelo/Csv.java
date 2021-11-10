/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author jului
 */
public class Csv {
        public static void exportarCSV(ArrayList<Usuario> pUsuarios){
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
            
            //escribe el nombre de las columnas.
            salidaCSV.write("Nombre");
            salidaCSV.write("Telefono");
            salidaCSV.write("Correo");
             
            salidaCSV.endRecord();// termina la escritura.
            
            //se recorre la lista y lo escribe en el archivo csv.
            for (Usuario user: pUsuarios){
              salidaCSV.write(user.getNombre());
              salidaCSV.write(user.getApellido1());
              salidaCSV.write(user.getApellido2());
              
               salidaCSV.endRecord();// termina la escritura.
            }
            
            salidaCSV.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
