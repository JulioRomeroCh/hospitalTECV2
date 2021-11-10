
package controlador;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import modelo.Conexion;
import modelo.Email;
import modelo.SendSMS;
import vista.Formulario;

public class HospitalTEC {
   
    
  public static void main (String[] args) throws Exception{
  
    //Conexion nuevaConexion = new Conexion();
    
    //nuevaConexion.conectar();
    
    //SendSMS mensaje = new SendSMS();
    //mensaje.enviarMensaje("hospitaltecjjk", "BulkSMSHospitalTEC*", "+50685184388", false);

    
    
    vista.Formulario nuevaVista = new vista.Formulario();
    nuevaVista.setVisible(true);
    

  }
}
