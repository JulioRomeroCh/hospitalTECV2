package controlador;

import vista.*;
import controlador.*;
import modelo.*;

/**
 *
 * @author KevRj
 */
public class GestorSalasMVC {
    
  public static void main(String[] args) {

    LoginForm vista = new LoginForm();
    Usuario usuario = new Usuario();

    ControladorUsuario controladorUsuario = new
    ControladorUsuario(vista, usuario);

    controladorUsuario.vista.setVisible(true);
    controladorUsuario.vista.setLocationRelativeTo(null);
    
    
 }
    
}
