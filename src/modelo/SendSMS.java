package modelo;

import java.net.*;
import java.util.Base64;
import java.io.*;
public class SendSMS {
    
  public void enviarMensaje(String username, String password, String to, boolean pEstadoCita) throws Exception {
    try{
      String message;
      if (pEstadoCita == true){
        message = "Su cita ha sido registrada";
      }
      else{
        message = "Su cita ha sido cancelada";
      }
      // This URL is used for sending messages
      String myURI = "https://api.bulksms.com/v1/messages";
      // change these values to match your own account
      String myUsername = ""+username+"";
      String myPassword = ""+password+"";
      // the details of the message we want to send
      String myData = "{to: \""+to+"\", encoding: \"UNICODE\", body: \""+message+"\"}";
      //SendSMS mensaje = new SendSMS();
      //mensaje.enviarMensaje("hospitaltecjjk", "BulkSMSHospitalTEC*", "+50685184388", "Hola Kevin!");
      //mensaje.enviarMensaje("", "", "+50685184388", "Hola Kevin!");
    
      // build the request based on the supplied settings
      URL url = new URL(myURI);
      HttpURLConnection request = (HttpURLConnection) url.openConnection();
      request.setDoOutput(true);
      // supply the credentials
      String authStr = myUsername + ":" + myPassword;
      String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
      request.setRequestProperty("Authorization", "Basic " + authEncoded);
      // we want to use HTTP POST
      request.setRequestMethod("POST");
      request.setRequestProperty( "Content-Type", "application/json");
      // write the data to the request
      OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
      out.write(myData);
      out.close();
      // try ... catch to handle errors nicely
      try {
        // make the call to the API
        InputStream response = request.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(response));
        String replyText;
        while ((replyText = in.readLine()) != null) {
          //System.out.println(replyText);
        }
        in.close();
      } 
      catch (IOException ex) {
        //System.out.println("An error occurred:" + ex.getMessage());
        BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
        // print the detail that comes with the error
        String replyText;
        while ((replyText = in.readLine()) != null) {
          //System.out.println(replyText);
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