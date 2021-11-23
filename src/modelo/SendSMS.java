package modelo;

//Imports fundamentales
import java.net.*;
import java.util.Base64;
import java.io.*;

/**
 * Clase SendSMS: Clase encargada de enviar un mensaje con la información del estado de la cita al número de teléfono indicado.
 * 
 * @author José Ignacio Blanco
 * @author Kevin Rojas Salazar
 * @author Julio Romero Chacón
 */
public class SendSMS {
    
  /**
   * Método enviarMensaje: Método que envía un mensaje de texto al paciente con la información del estado de la cita,
   *     para lo cual se realiza la autenticación por medio de credenciales y se adjunta un mensaje con la información
   * 
   * @param pUsuario: dato de tipo string que corresponde al usuario del servicio de mensajes BulkSMS
   * @param pContraseña: dato de tipo string que corresponde a la contraseña del servicio de mensajes BulkSMS
   * @param pReceptor: dato de tipo string que corresponde al número del teléfono al cual será enviada la información.
   * @param pEstadoCita: dato de tipo boolean que corresponde al estado de la cita.
   * @throws Exception: excepción que se lanza cuando hay un problema de autenticación.
   */  
  public void enviarMensaje(String pUsuario, String pContraseña, String pReceptor, boolean pEstadoCita) throws Exception {
    try{
      String message;
      if (pEstadoCita == true){
        message = "Su cita ha sido registrada";
      }
      else{
        message = "Su cita ha sido cancelada";
      }
      String myURI = "https://api.bulksms.com/v1/messages";
      String myUsername = ""+pUsuario+"";
      String myPassword = ""+pContraseña+"";
      //Contenido del mensaje
      String myData = "{to: \""+pReceptor+"\", encoding: \"UNICODE\", body: \""+message+"\"}";
      URL url = new URL(myURI);
      HttpURLConnection request = (HttpURLConnection) url.openConnection();
      request.setDoOutput(true);
      String authStr = myUsername + ":" + myPassword;
      String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
      request.setRequestProperty("Authorization", "Basic " + authEncoded);
      request.setRequestMethod("POST");
      request.setRequestProperty( "Content-Type", "application/json");
      OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
      out.write(myData);
      out.close();
      try {
        InputStream response = request.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(response));
        String replyText;
        while ((replyText = in.readLine()) != null) {
        
        }
        in.close();
      } 
      catch (IOException ex) {
        BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
        String replyText;
        while ((replyText = in.readLine()) != null) {
        }
        in.close();
      }
      request.disconnect();  
    }
    catch (Exception error) {
      System.out.println("ERROR:" + error);
    }
  }
}