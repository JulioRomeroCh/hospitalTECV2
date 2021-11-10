package controlador;

import dao.UsuarioDAO;
import dao.UsuarioDAOXML;
import javax.swing.*;
import java.awt.event.*;
import modelo.*;
import vista.*;


/**
 *
 * @author KevRj
 */
public class ControladorUsuario {
    
 public LoginForm vista; 
 public UsuarioDAO daoUsuario;
 public Usuario usuario; 

 public ControladorUsuario(LoginForm pVista, Usuario pUsuario){
   vista = pVista;
   usuario = pUsuario;
   UsuarioDAO daoUsuario= new UsuarioDAOXML();

   this.vista.btIniciarLogin.addActionListener((ActionListener) this);
   this.vista.btCancelarLogin.addActionListener((ActionListener) this);
 }
 
 public void actionPerformed(ActionEvent e) {
   switch(e.getActionCommand()) {
   case "Iniciar logIn":
   logIn();
   break;
   case "Cancelar logIn":
   cerrarVentanaLogin();
   break;
   default:
   break;
   }
 }
 
 public void logIn() {
   if (vista.logInDatosCorrectos() == true) {
     String nombreUsuario = vista.txtNombreUsuario.getText();
     String contraseña = vista.txtContraseña.getText();

     usuario = new Usuario(nombreUsuario, contraseña);
     Usuario usuarioActual = daoUsuario.iniciarSesion(usuario);
   
        if (usuarioActual != null) {
          vista.setVisible(false);
          // Dependiendo del tipo de usuario se puede abrir una
          // vista diferente
          JOptionPane.showMessageDialog(vista, "Bienvenido: " +
          usuario.getNombre());
          vista.setVisible(true);
        }
        else {
          JOptionPane.showMessageDialog(vista, "El usuario indicado no existe");
        }
   }       
   else {
     JOptionPane.showMessageDialog(vista, "Todos lo datos sonrequeridos");
   }
  }
    
  public void cerrarVentanaLogin() {
    vista.cancelarInicioSesion();
  }

}
