package controlador;

public class HospitalTEC {
  
  /**
   * Declaración del método principal
   * 
   * @param String[] args, es un parámetro obligatiorio para la ejecución del método main.
   * @throws Exception, captura las excepciones relacionadas a la base de datos.
   */  
  public static void main (String[] args) throws Exception{
    modelo.Bitacora modeloUno = new modelo.Bitacora();
    modelo.CentroAtencion modeloDos = new modelo.CentroAtencion();
    modelo.Cita modeloTres = new modelo.Cita();
    modelo.Diagnostico modeloCuatro = new modelo.Diagnostico();
    modelo.Doctor modeloCinco = new modelo.Doctor();
    modelo.Enfermero modeloSeis = new modelo.Enfermero();
    modelo.Funcionario modeloSiete = new modelo.Funcionario();
    modelo.Hospitalizacion modeloOcho = new modelo.Hospitalizacion();
    modelo.Paciente modeloNueve = new modelo.Paciente();
    modelo.Secretario modeloDiez = new modelo.Secretario();
    modelo.Seguimiento modeloOnce = new modelo.Seguimiento();
    modelo.Tratamiento modeloDoce = new modelo.Tratamiento();
    dao.ReportesDAO daoUno = new dao.ReportesDAO();
    modelo.Vacuna modeloTrece = new modelo.Vacuna();    
    vista.Formulario nuevaVista = new vista.Formulario();
    controlador controladores = new controlador(modeloUno, modeloDos, modeloTres, modeloCuatro, modeloCinco, modeloSeis, 
        modeloSiete, modeloOcho, modeloNueve, modeloDiez, modeloOnce, modeloDoce, modeloTrece, daoUno, nuevaVista);
    controladores.CargarBaseDatos();
    controladores.iniciar();     
    nuevaVista.setVisible(true);
  } 
}
