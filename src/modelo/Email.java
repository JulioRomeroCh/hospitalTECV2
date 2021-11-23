package modelo;

//Imports Fundamentales
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Clase Email: Clase encargada de enviar un correo con la información del estado de la cita 
 *     a la dirección de correo electrónico indicada.
 * 
 * @author José Ignacio Blanco
 * @author Kevin Rojas Salazar
 * @author Julio Romero Chacón
 */
public class Email {   
   
  //Atributos de la clase  
  private final String usuarioAdmi = "hospitaltecjjk@gmail.com";
  private final String contraseña = "hospitaltec123";
   
  /**
  * Método enviarCorreo: Método que envía un correo al usuario con información del estado de la cita, 
  *     para lo cual se realiza la autenticación por medio de credenciales y
  *     se adjunta un mensaje con la información.
  * 
  *@throws AddressException: Excepción en caso de que se presente una falla con la dirección de email.
  *@throws MessagingException: Excepción que se lanza cuando el método de conexión falla debido a un error de autenticación
  */
  public void enviarCorreo (boolean pEstadoCita) throws AddressException, MessagingException {
        
    // Instanciar un objeto de tipo properties (propiedades del correo)
    Properties props = new  Properties();
    //Setea al host al TTLS y a un puerto
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.setProperty("mail.smtp.port", "587");
    props.setProperty("mail.smtp.auth", "true");
    props.setProperty("mail.smtp.starttls.enable", "true");
    //Autenticación
    props.setProperty("mail.smtp.user", usuarioAdmi);
    props.setProperty("mail.smtp.clave", contraseña);  
    //Se crea una sesion, vincula con gmail
    Session sesion = Session.getDefaultInstance(props); 
    //Redacción del mensaje
    MimeMessage mensaje = new MimeMessage(sesion);  
    //Envía el correo al destinatario
    mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress ("KevRjs172@gmail.com"));       
    //Escribe el asunto
    mensaje.setSubject("Información sobre la cita médica");       
    if (pEstadoCita == true){
      mensaje.setText("Su cita ha sido registrada");
    }
    else{
      mensaje.setText("Su cita ha sido cancelada");
    }       
    //Servidor de salida del correo
    Transport transport = sesion.getTransport("smtp");       
    transport.connect(usuarioAdmi, contraseña);
    transport.sendMessage(mensaje, mensaje.getRecipients(Message.RecipientType.TO));
    transport.close();
    System.out.println("Correo enviado");
  }  
}
