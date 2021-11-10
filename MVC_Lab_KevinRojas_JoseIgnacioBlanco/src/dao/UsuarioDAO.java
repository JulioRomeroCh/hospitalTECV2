package dao;

import java.util.ArrayList;
import modelo.Usuario;

/**
 *
 * @author KevRj
 */
public abstract interface UsuarioDAO {
    
  public abstract Usuario iniciarSesion(Usuario pUsuario);
  public abstract ArrayList<Usuario> cargarListaUsuarios();
  public abstract boolean cambiarContraseña(String pNombreUsuario, String pContraseña,
      String pContraseñaNueva);
  public abstract boolean registrarUsuario(Usuario pUsuario);
    
}
