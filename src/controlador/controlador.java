package controlador;

//Imports fundamentales
import dao.ReportesDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.Formulario;


public class controlador implements ActionListener{

  //Atributos de la clase
  private Bitacora bitacora;
  private CentroAtencion centroAtencion;
  private Cita cita;
  private Diagnostico diagnostico; 
  private Doctor doctor; 
  private Enfermero enfermero; 
  private Funcionario funcionario;
  private Hospitalizacion hospitalizacion;
  private Paciente paciente;
  private Secretario secretario;
  private Seguimiento seguimiento;
  private Tratamiento tratamiento;
  private Vacuna vacuna;
  private Email email;
  private SendSMS mensaje;
  private Formulario vista;
  private ReportesDAO reportes;
  private Lista<Cita> citas;
  private Lista<CentroAtencion> centrosAtencion;
  private Lista<Hospitalizacion> hospitalizaciones;
  private Lista<Doctor> doctores;
  private Lista<Bitacora> bitacoras;
  private Lista<Diagnostico> diagnosticos;
  private Lista<Enfermero> enfermeros;
  private Lista<Funcionario> funcionarios;
  private Lista<Paciente> pacientes;
  private Lista<Secretario> secretarios;
  private Lista<Seguimiento> seguimientos;
  private Lista<Tratamiento> tratamientos;
  private Lista<Usuario> usuarios;
  private Lista<Vacuna> vacunas;
  
  /**
  * Método controlador: constructor que inicializa los atributos del controlador. 
  *  @param pBitacora: objeto de tipo Bitacora.
  *  @param pCentroAtencion: objeto de tipo CentroAtencion.
  *  @param pCita: objeto de tipo Cita.
  *  @param pDiagnostico: objeto de tipo Diagnostico.
  *  @param pDoctor: objeto de tipo Doctor.
  *  @param pEnfermero: objeto de tipo Enfermero.
  *  @param pFuncionario: objeto de tipo Funcionario.
  *  @param pHospitalizacion: objeto de tipo Hospítalizacion.
  *  @param pPaciente: objeto de tipo Paciente.
  *  @param pSecretario: objeto de tipo Secretario.
  *  @param pSeguimiento: objeto de tipo Seguimiento.
  *  @param pTratamiento: objeto de tipo Tratamiento.
  *  @param pVacuna: objeto de tipo Vacuna.
  *  @param pReportes: objeto de tipo ReportesDAO.
  *  @param pVista: objeto de tipo Formulario.
  */
  public controlador(Bitacora pBitacora, CentroAtencion pCentroAtencion, Cita pCita, Diagnostico pDiagnostico, Doctor pDoctor, Enfermero pEnfermero, Funcionario pFuncionario,
      Hospitalizacion pHospitalizacion, Paciente pPaciente, Secretario pSecretario, Seguimiento pSeguimiento, Tratamiento pTratamiento, Vacuna pVacuna, ReportesDAO pReportes, Formulario pVista){
      
    this.bitacora = pBitacora;
    this.centroAtencion = pCentroAtencion;
    this.cita = pCita;
    this.diagnostico = pDiagnostico;
    this.doctor = pDoctor;
    this.enfermero = pEnfermero;
    this.funcionario = pFuncionario;
    this.hospitalizacion = pHospitalizacion;
    this.paciente = pPaciente;
    this.secretario = pSecretario;
    this.seguimiento = pSeguimiento;
    this.tratamiento = pTratamiento;
    this.vacuna = pVacuna;
    this.reportes = pReportes;
    this.vista = pVista;
    //BOTONES
    this.vista.BotonIniciarSesion.addActionListener(this);
    this.vista.BotonReportePaciente2.addActionListener(this);
    this.vista.BotonDoctorCargarCitas.addActionListener(this);
    this.vista.BotonDoctorAsignarCita.addActionListener(this);
    this.vista.BotonPacienteSolicitar.addActionListener(this);
    this.vista.BotonPacienteCancelar.addActionListener(this);
    this.vista.BotonMPCancelarCita.addActionListener(this);
    this.vista.BotonDoctorCancelarCita.addActionListener(this);
    this.vista.BotonDoctorCargarPaciente.addActionListener(this);
    this.vista.BotonMDAtenderCita.addActionListener(this);
    this.vista.BotonDoctorCargarTratamiento.addActionListener(this);
    this.vista.BotonDoctorInsertarDiagnostico.addActionListener(this);
    this.vista.BotonDoctorHospitalizarNegativo.addActionListener(this);
    this.vista.BotonDoctorAtenderCita.addActionListener(this);
    this.vista.BotonDoctorHospitalizar.addActionListener(this);
    this.vista.BotonVacunarEnfermero.addActionListener(this);
    this.vista.BotonVacunar.addActionListener(this);
    this.vista.BotonMDAsignarCita.addActionListener(this);
    this.vista.BotonRegistrarDiagnostico.addActionListener(this);
    this.vista.BotonTratamientoAdmin.addActionListener(this);
    this.vista.BotonAdminRegistrarTratamiento.addActionListener(this);
    this.vista.BotonRegistrarCentro.addActionListener(this);
    this.vista.BotonAdminRegistrarCentro.addActionListener(this);
    this.vista.BotonRegistrarTipo.addActionListener(this);
    this.vista.BotonAdminConsultarBitacora.addActionListener(this);
    this.vista.BotonAdminConsultaBitacora.addActionListener(this);
    this.vista.BotonAdminRegistrarSecretario.addActionListener(this);
    this.vista.BotonRegistrarDoctor.addActionListener(this);
    this.vista.BotonEspecialidadDoctor.addActionListener(this);
    this.vista.BotonRegistrarEnfermero.addActionListener(this);
    this.vista.BotonRegistrarPaciente.addActionListener(this);
    this.vista.BotonTelefonoPaciente.addActionListener(this);
    this.vista.BotonDoctorHospitalizarPositivo.addActionListener(this);
    this.vista.BotonReportePaciente3.addActionListener(this);
    this.vista.BotonReportePaciente4.addActionListener(this);
    this.vista.BotonReportePaciente5.addActionListener(this);
    //Enfermero
    this.vista.BotonEnfermeroCancelarCita.addActionListener(this);
    this.vista.BotonEnfermeroCargarCitasPaciente.addActionListener(this);
    this.vista.BotonEnfermeroCancelarCita2.addActionListener(this);
    this.vista.BotonEnfermeroAsignarCita.addActionListener(this);
    this.vista.BotonEnfermeroAsignarCita2.addActionListener(this);
    //Secretario
    this.vista.BotonSecretarioCancelarCita1.addActionListener(this);
    this.vista.BotonSecretarioCancelarCita2.addActionListener(this);
    this.vista.BotonSecretarioCargarCitas.addActionListener(this);
    this.vista.BotonSecretarioAsignarCita1.addActionListener(this);
    this.vista.BotonSecretarioAsignarCita2.addActionListener(this);
    //REPORTES PACIENTE
    this.vista.BotonMPReportes.addActionListener(this);
    //REPORTES DOCTOR
    this.vista.BotonMDReportes.addActionListener(this);
    this.vista.RDB1.addActionListener(this);
    this.vista.RDB2.addActionListener(this);
    this.vista.RDB3.addActionListener(this);
    this.vista.RDB4.addActionListener(this);
    this.vista.RDB5.addActionListener(this);
    this.vista.RDB6.addActionListener(this);
    this.vista.RDB7.addActionListener(this);
    //REPORTES SECRETARIO
    this.vista.ReportesMS.addActionListener(this);
    this.vista.RSB1.addActionListener(this);
    this.vista.RSB2.addActionListener(this);
    //LISTAS
    this.citas = new Lista<Cita>();
    this.centrosAtencion = new Lista<CentroAtencion>();
    this.hospitalizaciones = new Lista<Hospitalizacion>();
    this.doctores = new Lista<Doctor>();
    this.bitacoras = new Lista<Bitacora>();
    this.diagnosticos = new Lista<Diagnostico>();
    this.enfermeros = new Lista<Enfermero>();
    this.funcionarios = new Lista<Funcionario>();
    this.pacientes = new Lista<Paciente>();
    this.secretarios = new Lista<Secretario>();
    this.seguimientos = new Lista<Seguimiento>();
    this.tratamientos = new Lista<Tratamiento>();
    this.usuarios = new Lista<Usuario>();
    this.vacunas = new Lista<Vacuna>();
  }
  
  /**
  * Método iniciar: Método que inicia la ejecución del programa. 
  */
  public void iniciar(){
      
  }
  
  /**
  * Método usuarioActual: Método que realiza una consulta a la base de datos para obtener la información del usuario que inicia la sesión. 
  *  @param nombreUsuario: String que representa el usuario actual.
  *  @param rol: String que representa el rol del usuario actual.
  */ 
  Paciente pacienteActivo;
  Doctor doctorActivo;
  Enfermero enfermeroActivo;
  Secretario secretarioActivo;
  public void usuarioActual(String nombreUsuario, String rol){
    ResultSet resultado;
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    if(rol == "Paciente"){    
      try{
        consulta = conectar.prepareStatement("SELECT usuario.cedula, nombre, apellido1, apellido2, rol, nombreUsuario, contraseña, paciente.identificadorPaciente, nacionalidad, residencia, fechaNacimiento, tipoSangre FROM usuario JOIN paciente_usuario ON usuario.cedula = paciente_usuario.cedula JOIN paciente ON paciente_usuario.identificadorPaciente = paciente.identificadorPaciente WHERE usuario.nombreUsuario = ?");
        consulta.setString(1, nombreUsuario);
        resultado = consulta.executeQuery();
        while(resultado.next()){  
          String pCedula = resultado.getObject(1).toString();
          String pNombre = resultado.getObject(2).toString();
          String pApellido1 = resultado.getObject(3).toString();
          String pApellido2 = resultado.getObject(4).toString();
          String pRol = resultado.getObject(5).toString();
          String pNombreUsuario = resultado.getObject(6).toString();
          String pContraseña = resultado.getObject(7).toString();
          int pIdentificadorPaciente = Integer.parseInt(resultado.getObject(8).toString());
          String pNacionalidad = resultado.getObject(9).toString();
          String pResidencia = resultado.getObject(10).toString();
          Date pFechaNacimiento = (java.util.Date) resultado.getObject(11);
          String tipoSangre = resultado.getObject(12).toString();
          TipoSangre pTipoSangre = TipoSangre.valueOf(tipoSangre);
          pacienteActivo = new Paciente(pCedula, pNombre, pApellido1,  pApellido2,  pRol, pNombreUsuario,  pContraseña,  pIdentificadorPaciente,  pNacionalidad, pResidencia, pFechaNacimiento, pTipoSangre);
        }      
      }
      catch(SQLException error){ 
        JOptionPane.showMessageDialog(null, "Favor verifique los datos");
      } 
    }    
    else if(rol == "Doctor"){
      try{
        consulta = conectar.prepareStatement("SELECT usuario.cedula, nombre, apellido1, apellido2, rol, nombreUsuario, contraseña, funcionario.identificadorFuncionario, tipo, fechaIngreso, area, codigoDoctor FROM usuario JOIN funcionario_usuario ON usuario.cedula = funcionario_usuario.cedula JOIN funcionario ON funcionario_usuario.identificadorFuncionario = funcionario.identificadorFuncionario JOIN doctor ON funcionario.identificadorFuncionario = doctor.identificadorFuncionario WHERE usuario.nombreUsuario = ?");
        consulta.setString(1, nombreUsuario);
        resultado = consulta.executeQuery();
        while(resultado.next()){ 
          String pCedula = resultado.getObject(1).toString();
          String pNombre = resultado.getObject(2).toString();
          String pApellido1 = resultado.getObject(3).toString();
          String pApellido2 = resultado.getObject(4).toString();
          String pRol = resultado.getObject(5).toString();
          String pNombreUsuario = resultado.getObject(6).toString();
          String pContraseña = resultado.getObject(7).toString();  
          int pIdentificadorFuncionario = Integer.parseInt(resultado.getObject(8).toString());
          String tipo = resultado.getObject(9).toString();
          TipoFuncionario pTipo = TipoFuncionario.valueOf(tipo);
          Date pFechaIngreso = (java.util.Date) resultado.getObject(10);
          String pArea = resultado.getObject(11).toString();
          int pCodigoDoctor = Integer.parseInt(resultado.getObject(12).toString());
          doctorActivo = new Doctor( pCedula,  pNombre,  pApellido1,  pApellido2,  pRol,  pNombreUsuario,  pContraseña,  pIdentificadorFuncionario,  pTipo, pFechaIngreso,  pArea,  pCodigoDoctor);
        }   
      }
      catch(Exception error){ 
        System.out.println(error);
      }     
    }
    else if(rol == "Enfermero"){
      try{
        consulta = conectar.prepareStatement("SELECT usuario.cedula, nombre, apellido1, apellido2, rol, nombreUsuario, contraseña, funcionario.identificadorFuncionario, tipo, fechaIngreso, area, enfermero.codigoEnfermero, indicadorPersonasACargo, indicadorExperienciaCapacitaciones FROM usuario JOIN funcionario_usuario ON usuario.cedula = funcionario_usuario.cedula JOIN funcionario ON funcionario_usuario.identificadorFuncionario = funcionario.identificadorFuncionario JOIN enfermero_funcionario ON funcionario.identificadorFuncionario = enfermero_funcionario.identificadorFuncionario JOIN enfermero ON enfermero_funcionario.codigoEnfermero = enfermero.codigoEnfermero WHERE usuario.nombreUsuario = ?");
        consulta.setString(1, nombreUsuario);
        resultado = consulta.executeQuery();
        while(resultado.next()){ 
          String pCedula = resultado.getObject(1).toString();
          String pNombre = resultado.getObject(2).toString();
          String pApellido1 = resultado.getObject(3).toString();
          String pApellido2 = resultado.getObject(4).toString();
          String pRol = resultado.getObject(5).toString();
          String pNombreUsuario = resultado.getObject(6).toString();
          String pContraseña = resultado.getObject(7).toString();  
          int pIdentificadorFuncionario = Integer.parseInt(resultado.getObject(8).toString());
          String tipo = resultado.getObject(9).toString();
          TipoFuncionario pTipo = TipoFuncionario.valueOf(tipo);
          Date pFechaIngreso = (java.util.Date) resultado.getObject(10);
          String pArea = resultado.getObject(11).toString();
          int pCodigoEnfermero = Integer.parseInt(resultado.getObject(12).toString());  
          int indicadorUno = Integer.parseInt(resultado.getObject(13).toString());
          int indicadorDos = Integer.parseInt(resultado.getObject(14).toString());   
          enfermeroActivo = new Enfermero(pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña, pIdentificadorFuncionario, pTipo, pFechaIngreso, pArea, pCodigoEnfermero);
          if(indicadorUno == 1){
            enfermeroActivo.setIndicadorPersonasACargo(true);    
          }
          else{
            enfermeroActivo.setIndicadorPersonasACargo(false);    
          }
          if(indicadorDos == 1){
            enfermeroActivo.setIndicadorExperienciaCapacitaciones(true);    
          }
          else{
            enfermeroActivo.setIndicadorExperienciaCapacitaciones(false);    
          }
          
        }   
      }
      catch(Exception error){ 
        System.out.println(error);
      }     
    }
    else if(rol == "Secretario"){
      try{
        consulta = conectar.prepareStatement("SELECT usuario.cedula, nombre, apellido1, apellido2, rol, nombreUsuario, contraseña, funcionario.identificadorFuncionario, tipo, fechaIngreso, area, codigoSecretario FROM usuario JOIN funcionario_usuario ON usuario.cedula = funcionario_usuario.cedula JOIN funcionario ON funcionario_usuario.identificadorFuncionario = funcionario.identificadorFuncionario JOIN secretario ON funcionario.identificadorFuncionario = secretario.identificadorFuncionario WHERE usuario.nombreUsuario = ?");
        consulta.setString(1, nombreUsuario);
        resultado = consulta.executeQuery();
        while(resultado.next()){ 
          String pCedula = resultado.getObject(1).toString();
          String pNombre = resultado.getObject(2).toString();
          String pApellido1 = resultado.getObject(3).toString();
          String pApellido2 = resultado.getObject(4).toString();
          String pRol = resultado.getObject(5).toString();
          String pNombreUsuario = resultado.getObject(6).toString();
          String pContraseña = resultado.getObject(7).toString();  
          int pIdentificadorFuncionario = Integer.parseInt(resultado.getObject(8).toString());
          String tipo = resultado.getObject(9).toString();
          TipoFuncionario pTipo = TipoFuncionario.valueOf(tipo);
          Date pFechaIngreso = (java.util.Date) resultado.getObject(10);
          String pArea = resultado.getObject(11).toString();;
          int pCodigoSecretario= Integer.parseInt(resultado.getObject(12).toString());   
          secretarioActivo = new Secretario(pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña, pIdentificadorFuncionario, pTipo, pFechaIngreso, pArea, pCodigoSecretario);
        }   
      }
      catch(Exception error){ 
        System.out.println(error);
      }    
    }
  }
 
  /**
  * Método actionPerformed: Ejecuta los eventos sobre los elementos en la interfaz gráfica. 
  */
  @Override
  public void actionPerformed(ActionEvent evento){
    if(evento.getSource() == vista.BotonReportePaciente2){
      PrimerReportePaciente();
    }  
    else if(evento.getSource() == vista.BotonReportePaciente3){
      SegundoReportePaciente();
    }
    else if(evento.getSource() == vista.BotonReportePaciente4){
      TercerReportePaciente();
    }
    else if(evento.getSource() == vista.BotonReportePaciente5){
      CuartoReportePaciente();
    }
    //REPORTES DOCTOR
    else if(evento.getSource() == vista.BotonMDReportes){
      cargarReporteDoctorComboBox(vista.RDC3, vista.RDC4, vista.RDC7, vista.RDC8, vista.RDC9, vista.RDC10, vista.RDC11);
    }
    else if(evento.getSource() == vista.RDB1){
      PrimerReporteDoctor();
    }
    else if(evento.getSource() == vista.RDB2){
      SegunReporteDoctor();
    }
    else if(evento.getSource() == vista.RDB3){
      TercerReporteDoctor();
    }
    else if(evento.getSource() == vista.RDB4){
      CuartoReporteDoctor();
    }
    else if(evento.getSource() == vista.RDB5){
      QuintoReporteDoctor();
    }
    else if(evento.getSource() == vista.RDB6){
      SextoReporteDoctor();
    }
    else if(evento.getSource() == vista.RDB7){
      SeptimoReporteDoctor();
    }
    //REPORTES SECRETARIO
    else if(evento.getSource() == vista.RSB1){
      PrimerReporteSecretario();
    }
    else if(evento.getSource() == vista.RSB2){
      SegundoReporteSecretario();
    }
    //INICIO
    else if (evento.getSource() == vista.BotonIniciarSesion){
      usuarioActual(vista.TextoUsuario.getText(), vista.ComboRolInicioSesion.getSelectedItem().toString());
      cargarDoctorComboBox(vista.ComboDoctorCitasCanceladasCM, vista.ComboDoctorPacienteCitaAsignada, vista.ComboDoctorCitasRA);
      cargarDoctorCitasRAAtender(vista.ComboDoctorCitasRA);
    }
    else if(evento.getSource() == vista.BotonEnfermeroCancelarCita){
      cargaEnfermeroCancelarComboBox(vista.ComboEnfermeroPacienteCitaAsignada);
    }
    else if(evento.getSource() == vista.BotonSecretarioCancelarCita1){
      cargaSecretarioCancelarComboBox(vista.ComboSecretarioUno);
    }
    else if(evento.getSource() == vista.BotonMPReportes){
      cargarReportePacienteComboBox(vista.ReportesPacienteNombreDiagnostico1, vista.ReportesPacienteNombreTratamiento);
    }
    else if(evento.getSource() == vista.BotonEnfermeroAsignarCita){
      cargarEnfermeroAsignarComboBox(vista.ComboEnfermeroCitasAsignar);
    }
    else if(evento.getSource() == vista.BotonSecretarioAsignarCita1){
      cargarSecretarioAsignarComboBox(vista.ComboSecretarioTres);
    }

    else if(evento.getSource() == vista.BotonAdminRegistrarSecretario){
      if(vista.TextSecretarioNombre.getText().equals("") || vista.TextSecretarioApellido1.getText().equals("") || vista.TextSecretarioApellido2.getText().equals("") ||
          vista.TextSecretarioCedula.getText().equals("") || vista.TextSecretarioUsuario.getText().equals("") || vista.TextSecretarioCodigo.getText().equals("") ||
          vista.TextSecretarioCodigo.getText().equals("") || vista.TextSecretarioContraseña.getText().equals("") || vista.TextSecretarioIDFuncionario.getText().equals("") ||
          vista.ComboSecretarioArea.getText().equals("") || vista.DateSecretarioFechaIngreso.getDate() == null){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        Secretario nuevoSecretario = new Secretario(vista.TextSecretarioCedula.getText(), vista.TextSecretarioNombre.getText(), vista.TextSecretarioApellido1.getText(), vista.TextSecretarioApellido2.getText(), "Secretario", vista.TextSecretarioUsuario.getText(), vista.TextSecretarioContraseña.getText(), Integer.parseInt(vista.TextSecretarioIDFuncionario.getText()), TipoFuncionario.SECRETARIO, vista.DateSecretarioFechaIngreso.getDate(), vista.ComboSecretarioArea.getText(), Integer.parseInt(vista.TextSecretarioCodigo.getText()));
        usuarios.add(nuevoSecretario);
        funcionarios.add(nuevoSecretario);
        secretarios.add(nuevoSecretario);
        nuevoSecretario.insertarSecretario(vista.TextSecretarioCedula.getText(), vista.TextSecretarioNombre.getText(), vista.TextSecretarioApellido1.getText(), vista.TextSecretarioApellido2.getText(), "Secretario", vista.TextSecretarioUsuario.getText(), vista.TextSecretarioContraseña.getText(), Integer.parseInt(vista.TextSecretarioIDFuncionario.getText()), TipoFuncionario.SECRETARIO, vista.DateSecretarioFechaIngreso.getDate(), vista.ComboSecretarioArea.getText(), Integer.parseInt(vista.TextSecretarioCodigo.getText()));
        JOptionPane.showMessageDialog(null, "Secretario registrado con éxito");
      }
    }
    else if(evento.getSource() == vista.BotonAdminConsultaBitacora){
      cargarCitaComboBox(vista.ComboAdminCitaConsultarBitacora);   
    }
    else if(evento.getSource() == vista.BotonDoctorCargarCitas){
      if(vista.ComboDoctorPacienteCitaAsignada.getSelectedItem().toString().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        cargarDoctorCitasRACancelar(vista.ComboDoctorCitas, Integer.parseInt(vista.ComboDoctorPacienteCitaAsignada.getSelectedItem().toString()));   
      }
    }
    else if(evento.getSource() == vista.BotonEnfermeroCargarCitasPaciente){
      if(vista.ComboEnfermeroPacienteCitaAsignada.getSelectedItem().toString().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        cargarEnfermeroCitasRACancelar(vista.ComboEnfermeroCitaCancelar, Integer.parseInt(vista.ComboEnfermeroPacienteCitaAsignada.getSelectedItem().toString()));    
      }     
    }

    else if(evento.getSource() == vista.BotonSecretarioCargarCitas){
      if(vista.ComboSecretarioUno.getSelectedItem().toString().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        cargarSecretarioCitasRACancelar(vista.ComboSecretarioDos, Integer.parseInt(vista.ComboSecretarioUno.getSelectedItem().toString()));   
      }
        
    }
    else if(evento.getSource() == vista.BotonRegistrarCentro){
      cargarTipoCentroComboBox(vista.ComboAdminTipoCentro);   
    }
    else if(evento.getSource() == vista.BotonRegistrarDoctor){
      if (vista.TextNombreDoctor.getText().equals("") || vista.TextApellido1Doctor.getText().equals("") || vista.TextApellido2Doctor.getText().equals("") ||
          vista.TextUsuarioDoctor.getText().equals("") || vista.TextContraeñaDoctor.getText().equals("") || vista.TextIDFuncionarioDoctor.getText().equals("") ||
          vista.TextCedulaDoctor.getText().equals("") || vista.TextAreaDoctor.getText().equals("") || vista.TextCodigoDoctor2.getText().equals("") ||
          vista.CalendarioDoctor.getDate() == null){
        JOptionPane.showMessageDialog(null, "Verifique los datos");   
      }
      else{
        Doctor nuevoDoctor = new Doctor(vista.TextCedulaDoctor.getText(), vista.TextNombreDoctor.getText(), vista.TextApellido1Doctor.getText(), vista.TextApellido2Doctor.getText(), "Doctor", vista.TextUsuarioDoctor.getText(), vista.TextContraeñaDoctor.getText(), Integer.parseInt(vista.TextIDFuncionarioDoctor.getText()), TipoFuncionario.DOCTOR, vista.CalendarioDoctor.getDate(), vista.TextAreaDoctor.getText(), Integer.parseInt(vista.TextCodigoDoctor2.getText()));
        usuarios.add(nuevoDoctor);
        funcionarios.add(nuevoDoctor);
        doctores.add(nuevoDoctor);
        nuevoDoctor.insertarDoctor(vista.TextCedulaDoctor.getText(), vista.TextNombreDoctor.getText(), vista.TextApellido1Doctor.getText(), vista.TextApellido2Doctor.getText(), "Doctor", vista.TextUsuarioDoctor.getText(), vista.TextContraeñaDoctor.getText(), Integer.parseInt(vista.TextIDFuncionarioDoctor.getText()), TipoFuncionario.DOCTOR, vista.CalendarioDoctor.getDate(), vista.TextAreaDoctor.getText(), Integer.parseInt(vista.TextCodigoDoctor2.getText()));
        JOptionPane.showMessageDialog(null, "Doctor registrado con éxito");
      }
    }
    else if(evento.getSource() == vista.BotonEspecialidadDoctor){
      if(vista.TextEspecialidadDoctor.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        buscarDoctor(Integer.parseInt(vista.TextCodigoDoctor2.getText())).insertarEspecialidad(Integer.parseInt(vista.TextCodigoDoctor2.getText()), vista.TextEspecialidadDoctor.getText());
        buscarDoctor(Integer.parseInt(vista.TextCodigoDoctor2.getText())).añadirEspecialidad(vista.TextEspecialidadDoctor.getText());
        JOptionPane.showMessageDialog(null, "Especialidad asociada con éxito");
      }
    }
    else if(evento.getSource() == vista.BotonRegistrarPaciente){
      if(vista.TextNacionalidadPaciente.getText().equals("") || vista.TextContraeñaPaciente.getText().equals("") || vista.TextUsuarioPaciente.getText().equals("") ||
          vista.TextApellido1Paciente.getText().equals("") || vista.TextApellido2Paciente.getText().equals("") || vista.TextNombrePaciente.getText().equals("") ||
          vista.TextCedulaPaciente.getText().equals("") || vista.TextIDPaciente1.getText().equals("") || vista.TextResidenciaPaciente.getText().equals("") ||
          vista.DateNacimientoPaciente.getDate() == null){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        Paciente nuevoPaciente = new Paciente(vista.TextCedulaPaciente.getText(), vista.TextNombrePaciente.getText(), vista.TextApellido1Paciente.getText(), vista.TextApellido2Paciente.getText(), "Paciente", vista.TextUsuarioPaciente.getText(), vista.TextContraeñaPaciente.getText(), Integer.parseInt(vista.TextIDPaciente1.getText()), vista.TextNacionalidadPaciente.getText(), vista.TextResidenciaPaciente.getText(), vista.DateNacimientoPaciente.getDate(), TipoSangre.valueOf(vista.BoxSangrePaciente.getSelectedItem().toString()));
        usuarios.add(nuevoPaciente);
        pacientes.add(nuevoPaciente);
        nuevoPaciente.insertarPaciente(vista.TextCedulaPaciente.getText(), vista.TextNombrePaciente.getText(), vista.TextApellido1Paciente.getText(), vista.TextApellido2Paciente.getText(), "Paciente", vista.TextUsuarioPaciente.getText(), vista.TextContraeñaPaciente.getText(), Integer.parseInt(vista.TextIDPaciente1.getText()), vista.TextNacionalidadPaciente.getText(), vista.TextResidenciaPaciente.getText(), vista.DateNacimientoPaciente.getDate(), TipoSangre.valueOf(vista.BoxSangrePaciente.getSelectedItem().toString()));
        JOptionPane.showMessageDialog(null, "Paciente registrado con éxito");
      }
    }
    else if(evento.getSource() == vista.BotonTelefonoPaciente){
      if(vista.TextTelefonoPaciente.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");   
      }
      else{
        buscarPaciente(Integer.parseInt(vista.TextIDPaciente1.getText())).insertarTelefono(vista.TextTelefonoPaciente.getText());
        buscarPaciente(Integer.parseInt(vista.TextIDPaciente1.getText())).setTelefono(vista.TextTelefonoPaciente.getText());
        JOptionPane.showMessageDialog(null, "Teléfono asociado con éxito");           
      }
    }
    else if(evento.getSource() == vista.BotonRegistrarEnfermero){
      if(vista.TextNombreEnfermero.getText().equals("") || vista.TextApellido1Enfermero.getText().equals("") || vista.TextApellido2Enfermero.getText().equals("") || 
        vista.TextUsuarioEnfermero.getText().equals("") || vista.TextContraeñaEnfermero.getText().equals("") || vista.TextIDFuncionarioEnfermero.getText().equals("") || 
        vista.TextCodigoEnfermero.getText().equals("") || vista.TextCedulaEnfermero.getText().equals("") || vista.TextAreaEnfermero.getText().equals("") || 
        vista.CalendarioEnfermero.getDate() == null){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        Enfermero nuevoEnfermero = new Enfermero(vista.TextCedulaEnfermero.getText(), vista.TextNombreEnfermero.getText(), vista.TextApellido1Enfermero.getText(), vista.TextApellido2Enfermero.getText(), "Enfermero", vista.TextUsuarioEnfermero.getText(), vista.TextContraeñaEnfermero.getText(), Integer.parseInt(vista.TextIDFuncionarioEnfermero.getText()), TipoFuncionario.ENFERMERO, vista.CalendarioEnfermero.getDate(), vista.TextAreaEnfermero.getText(), Integer.parseInt(vista.TextCodigoEnfermero.getText()));
        usuarios.add(nuevoEnfermero);
        funcionarios.add(nuevoEnfermero);
        enfermeros.add(nuevoEnfermero);
        nuevoEnfermero.insertarEnfermero(vista.TextCedulaEnfermero.getText(), vista.TextNombreEnfermero.getText(), vista.TextApellido1Enfermero.getText(), vista.TextApellido2Enfermero.getText(), "Enfermero", vista.TextUsuarioEnfermero.getText(), vista.TextContraeñaEnfermero.getText(), Integer.parseInt(vista.TextIDFuncionarioEnfermero.getText()), TipoFuncionario.ENFERMERO, vista.CalendarioEnfermero.getDate(), vista.TextAreaEnfermero.getText(), Integer.parseInt(vista.TextCodigoEnfermero.getText()), vista.BoxPersonasEnfermero.getSelectedItem().toString(), vista.BoxExperienciaEnfermero.getSelectedItem().toString());
        JOptionPane.showMessageDialog(null, "Enfermero registrado con éxito");
      }
    }
    else if(evento.getSource() == vista.BotonAdminConsultarBitacora){
      consultaBitacora(vista.tablaConsultaBitacora, Integer.parseInt(vista.ComboAdminCitaConsultarBitacora.getSelectedItem().toString()));
    }
    else if(evento.getSource() == vista.BotonDoctorHospitalizarPositivo){
      cargarHospitalizacionComboBox(vista.ComboTratamientoHospitalizar, vista.ComboCentroHospitalizar, vista.ComboDoctorDiagnostico.getSelectedItem().toString());
    }
    else if(evento.getSource() == vista.BotonRegistrarTipo){
      if(vista.TextAdminIDTipo.getText().equals("") && vista.TextAdminTipo.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");   
      }
      else{
        CentroAtencion centroTemporal = new CentroAtencion();
        centroTemporal.insertarTipoCentro(Integer.parseInt(vista.TextAdminIDTipo.getText()), vista.TextAdminTipo.getText());
        JOptionPane.showMessageDialog(null, "Tipo centro registrado con éxito");
      }
    }
    else if(evento.getSource() == vista.BotonAdminRegistrarCentro){
      if(vista.TextAdminNombreCentro.getText().equals("") && vista.TextAdminCapacidadCentro.getText().equals("") && vista.TextAdminLugarCentro.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        CentroAtencion centroTemporal = new CentroAtencion();
        centroTemporal.insertarCentro(vista.TextAdminNombreCentro.getText(), vista.TextAdminLugarCentro.getText(), Integer.parseInt(vista.TextAdminCapacidadCentro.getText()), Integer.parseInt(vista.ComboAdminTipoCentro.getSelectedItem().toString()));
        JOptionPane.showMessageDialog(null, "Centro médico registrado con éxito");
      }
    }
    else if(evento.getSource() == vista.BotonAdminRegistrarTratamiento){
      if(vista.TextAdminNombreTratamiento.getText().equals("") && vista.TextAdminTipoTratamiento.getText().equals("") &&  vista.TextAdminDosisTratamiento.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");   
      }
      else{
        Tratamiento tratamientoTemporal = new Tratamiento(vista.TextAdminNombreTratamiento.getText(), vista.TextAdminTipoTratamiento.getText(), Integer.parseInt(vista.TextAdminDosisTratamiento.getText()));
        tratamientoTemporal.insertarTratamiento(vista.TextAdminNombreTratamiento.getText(), vista.TextAdminTipoTratamiento.getText(), Integer.parseInt(vista.TextAdminDosisTratamiento.getText()));
        Diagnostico diagnosticoTemporal = buscarDiagnostico(vista.ComboAdminDiagnostico.getSelectedItem().toString());
        diagnosticoTemporal.insertarDiagnosticoTratamiento(tratamientoTemporal);
        JOptionPane.showMessageDialog(null, "Tratamiento registrado y asociado con éxito");
      }
    }
    else if(evento.getSource() == vista.BotonTratamientoAdmin){
        cargarNombreDiagnosticosComboBox(vista.ComboAdminDiagnostico);
    }
    else if(evento.getSource() == vista.BotonRegistrarDiagnostico){
      if(vista.TextAdminNombreDiagnostico.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        Diagnostico diagnosticoTemporal = new Diagnostico();
        diagnosticoTemporal.insertarDiagnostico(vista.TextAdminNombreDiagnostico.getText(), NivelDiagnostico.valueOf(vista.ComboAdminNivelDiagnostico.getSelectedItem().toString()));
        JOptionPane.showMessageDialog(null, "Diagnóstico registrado con éxito");
      }
    }
    else if(evento.getSource() == vista.BotonDoctorHospitalizarNegativo){
      JOptionPane.showMessageDialog(null, "El paciente no será hospitalizado");    
    }
    else if(evento.getSource() == vista.BotonMDAtenderCita){
      cargarNombreDiagnostico(vista.ComboDoctorDiagnostico);
    }
    else if(evento.getSource() == vista.BotonVacunarEnfermero){
      if(vista.ComboPacienteVacunar.getSelectedItem().toString().equals("") && vista.ComboNumeroLoteVacunar.getSelectedItem().toString().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        try {
          enfermeroActivo.vacunar(buscarPaciente(Integer.parseInt(vista.ComboPacienteVacunar.getSelectedItem().toString())), buscarVacuna(Integer.parseInt(vista.ComboNumeroLoteVacunar.getSelectedItem().toString())));
        } 
        catch (ParseException ex) {
          Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    else if(evento.getSource() == vista.BotonVacunar){
      cargarPacienteVacunaComboBox(vista.ComboPacienteVacunar);
      cargarNumerLoteComboBox(vista.ComboNumeroLoteVacunar);
    }
    else if(evento.getSource() == vista.BotonDoctorAtenderCita){
      if(vista.ComboDoctorTratamiento.getSelectedItem().toString().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        doctorActivo.atenderCita(buscarCita(Integer.parseInt(vista.ComboDoctorCitasRA.getSelectedItem().toString())));
        JOptionPane.showMessageDialog(null, "Paciente atentido con éxito");
      }
    }
    else if(evento.getSource() == vista.BotonDoctorHospitalizar){
      if(vista.TextObservaciónHospitalizacion.getText().equals("") && vista.TextIDHospitalizacion.getText().equals("") && vista.FechaHospitalizacion.getDate() == null){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        doctorActivo.hospitalizar(Integer.parseInt(vista.TextIDHospitalizacion.getText()), vista.FechaHospitalizacion.getDate(), buscarCita(Integer.parseInt(vista.ComboDoctorCitasRA.getSelectedItem().toString())), buscarCentro(Integer.parseInt(vista.ComboCentroHospitalizar.getSelectedItem().toString())), vista.TextObservaciónHospitalizacion.getText(), buscarTratamiento(vista.ComboTratamientoHospitalizar.getSelectedItem().toString()));
        JOptionPane.showMessageDialog(null, "El paciente será hospitalizado");
      }
    }
    else if(evento.getSource() == vista.BotonDoctorCargarPaciente){
      if(vista.ComboDoctorCitasRA.getSelectedItem().toString().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        cargarIDPaciente(Integer.parseInt(vista.ComboDoctorCitasRA.getSelectedItem().toString()), vista.ComboDoctorIDPaciente);
      }
    }
    else if(evento.getSource() == vista.BotonDoctorCargarTratamiento){
      if(vista.ComboDoctorDiagnostico.getSelectedItem().toString().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        cargarTratamiento(vista.ComboDoctorDiagnostico.getSelectedItem().toString(), vista.ComboDoctorTratamiento);   
      }
    }
    else if(evento.getSource() == vista.BotonDoctorInsertarDiagnostico){
      if(vista.TextDoctorObservacion.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");   
      }
      else{
        doctorActivo.añadirObservacionCita(buscarCita(Integer.parseInt(vista.ComboDoctorCitasRA.getSelectedItem().toString())),buscarDiagnostico(vista.ComboDoctorDiagnostico.getSelectedItem().toString()), vista.TextDoctorObservacion.getText(), buscarTratamiento(vista.ComboDoctorTratamiento.getSelectedItem().toString()));
        JOptionPane.showMessageDialog(null, doctorActivo.getNombre() + ": diagnóstico registrado con éxito"); 
      }
    }
    else if(evento.getSource() == vista.BotonDoctorCancelarCita){
      if(vista.ComboDoctorCitas.getSelectedItem().toString().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");   
      }
      else{
        CancelarCitaDoctor(Integer.parseInt(vista.ComboDoctorCitas.getSelectedItem().toString()));
        try {
          email.enviarCorreo(false);
          mensaje.enviarMensaje("hospitaltecjjk", "BulkSMSHospitalTEC*",  "+50685184388", false);
          JOptionPane.showMessageDialog(null, "Correo y mensaje enviado con éxito");
        } 
        catch (MessagingException ex) {
          Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex) {
          Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    else if(evento.getSource() == vista.BotonEnfermeroCancelarCita2){
      if(vista.ComboEnfermeroCitaCancelar.getSelectedItem().toString().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        CancelarCitaEnfermero(Integer.parseInt(vista.ComboEnfermeroCitaCancelar.getSelectedItem().toString()));
        try {
          email.enviarCorreo(false);
          mensaje.enviarMensaje("hospitaltecjjk", "BulkSMSHospitalTEC*",  "+50685184388", false);
          JOptionPane.showMessageDialog(null, "Correo y mensaje enviado con éxito");
        } 
        catch (MessagingException ex) {
          Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex) {
          Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    else if(evento.getSource() == vista.BotonSecretarioCancelarCita2){
      if(vista.ComboSecretarioDos.getSelectedItem().toString().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        CancelarCitaSecretario(Integer.parseInt(vista.ComboSecretarioDos.getSelectedItem().toString())); 
        try {
          email.enviarCorreo(false);
          mensaje.enviarMensaje("hospitaltecjjk", "BulkSMSHospitalTEC*",  "+50685184388", false);
          JOptionPane.showMessageDialog(null, "Correo y mensaje enviado con éxito");
        } 
        catch (MessagingException ex) {
          Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex) {
          Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    else if(evento.getSource() == vista.BotonDoctorAsignarCita){
      if(vista.ComboDoctorCitasCanceladasCM.getSelectedItem().toString().equals("")){
         JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        doctorActivo.asignarCita(buscarCita(Integer.parseInt(vista.ComboDoctorCitasCanceladasCM.getSelectedItem().toString())));
        JOptionPane.showMessageDialog(null, "Cita asignada con éxito");
      }   
    }
    else if(evento.getSource() == vista.BotonEnfermeroAsignarCita2){
      if(vista.ComboEnfermeroCitasAsignar.getSelectedItem().toString().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        enfermeroActivo.asignarCita(buscarCita(Integer.parseInt(vista.ComboEnfermeroCitasAsignar.getSelectedItem().toString())));
        JOptionPane.showMessageDialog(null, "Cita asignada con éxito");      
      }
    }
    else if(evento.getSource() == vista.BotonSecretarioAsignarCita2){
      if(vista.ComboSecretarioTres.getSelectedItem().toString().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        secretarioActivo.asignarCita(buscarCita(Integer.parseInt(vista.ComboSecretarioTres.getSelectedItem().toString())));
        JOptionPane.showMessageDialog(null, "Cita asignada con éxito"); 
      }
    }

    else if(evento.getSource() == vista.BotonMPCancelarCita){
        cargarCitasPendientes(vista.ComboPacienteCitasPendientes, pacienteActivo.getNombreUsuario());
    }
    else if(evento.getSource() == vista.BotonPacienteCancelar){
      if(vista.ComboPacienteCitasPendientes.getSelectedItem().toString().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        Lista<Date> fechayhora = ObtenerFechayHora(Integer.parseInt(vista.ComboPacienteCitasPendientes.getSelectedItem().toString()));
        Date fechaCita = fechayhora.get(0);
        Date horaCita = fechayhora.get(1);
        if(new Date().before(fechaCita) == false){  
          if(new Date().getHours() <= horaCita.getHours()){
            if(new Date().getMinutes() != horaCita.getMinutes()){
              pacienteActivo.cancelarCita(Integer.parseInt(vista.ComboPacienteCitasPendientes.getSelectedItem().toString()));
              JOptionPane.showMessageDialog(null, "Cita cancelada con éxito");
              try {
                email.enviarCorreo(false);
                mensaje.enviarMensaje("hospitaltecjjk", "BulkSMSHospitalTEC*",  "+50685184388", false);
                JOptionPane.showMessageDialog(null, "Correo y mensaje enviado con éxito");
              } 
              catch (MessagingException ex) {
                Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
              }
              catch (Exception ex) {
                Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
              }
            }
          }
        }
        else{
          JOptionPane.showMessageDialog(null, "Se presentó un error, la cita está programada para dentro de las próximas 24 horas");    
        }
      }
    }
    else if(evento.getSource() == vista.BotonPacienteSolicitar){
      if(vista.TextPacienteEspecialidad.getText().equals("") || vista.DatePacienteFecha.getDate() == null || vista.TextPacienteObservacion.getText().equals("") &&
          vista.TextPacienteIDCitaSolicitar.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Verifique los datos");    
      }
      else{
        SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");      
        try {
          pacienteActivo.solicitarCita(Integer.parseInt(vista.TextPacienteIDCitaSolicitar.getText()), vista.DatePacienteFecha.getDate(), formato.parse(vista.ComboDoctorHora.getSelectedItem().toString()), vista.TextPacienteEspecialidad.getText(),vista.TextPacienteObservacion.getText(), EstadoCita.REGISTRADA);
          Cita nuevaCita = new Cita(Integer.parseInt(vista.TextPacienteIDCitaSolicitar.getText()), vista.DatePacienteFecha.getDate(), formato.parse(vista.ComboDoctorHora.getSelectedItem().toString()), vista.TextPacienteEspecialidad.getText(),vista.TextPacienteObservacion.getText(), EstadoCita.REGISTRADA);
          Bitacora nuevaBitacora = new Bitacora(Integer.parseInt(vista.TextPacienteIDCitaSolicitar.getText()));
          nuevaCita.setBitacora(nuevaBitacora);
          citas.add(nuevaCita);
          JOptionPane.showMessageDialog(null, pacienteActivo.getNombre() + ": Gracias por utilizar nuestros servicios, su cita ha sido registrada con éxito");
          email.enviarCorreo(true);
          mensaje.enviarMensaje("hospitaltecjjk", "BulkSMSHospitalTEC*",  "+50685184388", true);
          JOptionPane.showMessageDialog(null, "Correo y mensaje enviado con éxito");
          vista.TextPacienteIDCitaSolicitar.setText("");
          vista.TextPacienteEspecialidad.setText("");
          vista.TextPacienteObservacion.setText("");
          vista.DatePacienteFecha.setCalendar(null);
        } 
        catch (ParseException ex) {
          Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (MessagingException ex) {
          Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (Exception ex) {
          Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
    }
  }
  
    
   /**
   * ----------------------------------------------------------------MÉTODOS VARIOS------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------MÉTODOS VARIOS------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------MÉTODOS VARIOS------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------MÉTODOS VARIOS------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------MÉTODOS VARIOS------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------MÉTODOS VARIOS------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------MÉTODOS VARIOS------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------MÉTODOS VARIOS------------------------------------------------------------------------------------------
   */
  
  /**
  * Método CancelarCitaDoctor: Método el cual lo ejecuta un doctor que cancela la cita de un paciente, 
  * la pasa a estado "CanceladaPorCentroMedico" y la registra en la bitácora de la cita. 
  * @param pIdentificadorCita: int que representa la cita que se desea cancelar.
  */
  public void CancelarCitaDoctor(int pIdentificadorCita){
    doctorActivo.cancelarCita(pIdentificadorCita);
    Cita nuevaCita = buscarCita(pIdentificadorCita);
    nuevaCita.setEstado(EstadoCita.CANCELADA_POR_CENTRO_MEDICO);
    nuevaCita.modificarEstadoCita(pIdentificadorCita, EstadoCita.CANCELADA_POR_CENTRO_MEDICO);
    nuevaCita.getBitacora().insertarBitacoraCitaEstado(pIdentificadorCita, EstadoCita.CANCELADA_POR_CENTRO_MEDICO);
    nuevaCita.getBitacora().insertarBitacoraFechayHora(pIdentificadorCita);
    JOptionPane.showMessageDialog(null, doctorActivo.getNombre() + ": cita cancelada con éxito");
  }
  
  /**
  * Método CancelarCitaEnfermero: Método el cual lo ejecuta un enfermero que cancela la cita de un paciente, 
  * la pasa a estado "CanceladaPorCentroMedico" y la registra en la bitácora de la cita. 
  * @param pIdentificadorCita: int que representa la cita que se desea cancelar.
  */
  public void CancelarCitaEnfermero(int pIdentificadorCita){
    enfermeroActivo.cancelarCita(pIdentificadorCita);
    Cita nuevaCita = buscarCita(pIdentificadorCita);
    nuevaCita.setEstado(EstadoCita.CANCELADA_POR_CENTRO_MEDICO);
    nuevaCita.modificarEstadoCita(pIdentificadorCita, EstadoCita.CANCELADA_POR_CENTRO_MEDICO);
    nuevaCita.getBitacora().insertarBitacoraCitaEstado(pIdentificadorCita, EstadoCita.CANCELADA_POR_CENTRO_MEDICO);
    nuevaCita.getBitacora().insertarBitacoraFechayHora(pIdentificadorCita);
    JOptionPane.showMessageDialog(null, enfermeroActivo.getNombre() + ": cita cancelada con éxito");
  }
  
  /**
  * Método CancelarCitaSecretario: Método el cual lo ejecuta un secretario que cancela la cita de un paciente, 
  * la pasa a estado "CanceladaPorCentroMedico" y la registra en la bitácora de la cita. 
  * @param pIdentificadorCita: int que representa la cita que se desea cancelar.
  */
  public void CancelarCitaSecretario(int pIdentificadorCita){
    secretarioActivo.cancelarCita(pIdentificadorCita);
    Cita nuevaCita = buscarCita(pIdentificadorCita);
    nuevaCita.setEstado(EstadoCita.CANCELADA_POR_CENTRO_MEDICO);
    nuevaCita.modificarEstadoCita(pIdentificadorCita, EstadoCita.CANCELADA_POR_CENTRO_MEDICO);
    nuevaCita.getBitacora().insertarBitacoraCitaEstado(pIdentificadorCita, EstadoCita.CANCELADA_POR_CENTRO_MEDICO);
    nuevaCita.getBitacora().insertarBitacoraFechayHora(pIdentificadorCita);
    JOptionPane.showMessageDialog(null, secretarioActivo.getNombre() + ": cita cancelada con éxito");
  }
  
   /**
   * ----------------------------------------------------------------CARGA JCOMBOBOX------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA JCOMBOBOX------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA JCOMBOBOX------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA JCOMBOBOX------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA JCOMBOBOX------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA JCOMBOBOX------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA JCOMBOBOX------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA JCOMBOBOX------------------------------------------------------------------------------------------
   */
  
  /**
  * Método cargarPacienteVacunaComboBox: Método que carga la lista de pacientes en un JComboBox.
  * @param ComboPacienteVacunar: JComboBox en el que se cargan los resultados de la consulta.
  */
  public void cargarPacienteVacunaComboBox(JComboBox ComboPacienteVacunar){
    ResultSet resultadoUno;
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboPacienteVacunar.removeAllItems();

    try{
      consultaUno = conectar.prepareStatement("SELECT identificadorPaciente FROM paciente");
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboPacienteVacunar.addItem(resultadoUno.getObject(indice));        
        }   
      }     
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
  }
  
  /**
  * Método cargarHospitalizacionComboBox: Método que carga los datos necesarios para una hospitalización dentro de dos JComboBox.
  * @param ComboTratamientoHospitalizar: JComboBox en el que se cargan los tratamientos asociados al diagnostico.
  * @param ComboCentroHospitalizar: JComboBox en el que se cargan los centros médicos disponibles para atender la hospitalización.
  */
  public void cargarHospitalizacionComboBox(JComboBox ComboTratamientoHospitalizar, JComboBox ComboCentroHospitalizar, String pNombreDiagnostico){
    ResultSet resultadoUno;
    ResultSet resultadoDos;
    PreparedStatement consultaUno;
    PreparedStatement consultaDos;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboTratamientoHospitalizar.removeAllItems();
    ComboCentroHospitalizar.removeAllItems();

    try{
      consultaUno = conectar.prepareStatement("SELECT nombreTratamiento FROM diagnostico_contiene_tratamiento WHERE nombreDiagnostico = ?");
      consultaUno.setString(1, pNombreDiagnostico);
      resultadoUno = consultaUno.executeQuery();
      
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboTratamientoHospitalizar.addItem(resultadoUno.getObject(indice));        
        }   
      }
      
      consultaDos = conectar.prepareStatement("SELECT codigoCentro FROM centroatencion");
      resultadoDos = consultaDos.executeQuery();
      while(resultadoDos.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboCentroHospitalizar.addItem(resultadoDos.getObject(indice));        
        }   
      }
      
      
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
  }
  
  /**
  * Método cargarTipoCentroComboBox: Método que carga los tipos de centro en un JComboBox.
  * @param ComboAdminTipoCentro: JComboBox en el que se cargan los resultados de la consulta.
  */
  public void cargarTipoCentroComboBox(JComboBox ComboAdminTipoCentro){
    ResultSet resultadoUno;
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboAdminTipoCentro.removeAllItems();

    try{
      consultaUno = conectar.prepareStatement("SELECT identificadorTipo FROM tipocentro");
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboAdminTipoCentro.addItem(resultadoUno.getObject(indice));        
        }   
      }     
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
  }
    
  /**
  * Método cargarCitaComboBox: Método que carga las citas existentes en un JComboBox. 
  * @param ComboAdminCitaConsultarBitacora: JComboBox en el que se cargan los resultados de la consulta.
  */
  public void cargarCitaComboBox(JComboBox ComboAdminCitaConsultarBitacora){
    ResultSet resultadoUno;
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboAdminCitaConsultarBitacora.removeAllItems();

    try{
      consultaUno = conectar.prepareStatement("SELECT identificadorCita FROM cita");
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboAdminCitaConsultarBitacora.addItem(resultadoUno.getObject(indice));        
        }   
      }     
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
  }
  
  /**
  * Método cargarReportePacienteComboBox: Método que carga el nombre de los diagnósticos y tramientos dentro de dos JComboBox.
  * @param ReportesPacienteNombreDiagnostico1: JComboBox en el que se cargan el nombre de los diagnósticos registrados.
  * @param ReportesPacienteNombreTratamiento: JComboBox en el que se cargan los nombres de los tratamientos registrados.
  */
  public void cargarReportePacienteComboBox(JComboBox ReportesPacienteNombreDiagnostico1, JComboBox ReportesPacienteNombreTratamiento){
    ResultSet resultadoUno;
    ResultSet resultadoDos;
    PreparedStatement consultaUno;
    PreparedStatement consultaDos;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ReportesPacienteNombreDiagnostico1.removeAllItems();
    ReportesPacienteNombreTratamiento.removeAllItems();
    ReportesPacienteNombreDiagnostico1.addItem("");
    ReportesPacienteNombreTratamiento.addItem("");

    try{
        
      consultaUno = conectar.prepareStatement("SELECT nombreDiagnostico FROM diagnostico");
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ReportesPacienteNombreDiagnostico1.addItem(resultadoUno.getObject(indice));        
        }   
      }  
      
      consultaDos = conectar.prepareStatement("SELECT nombreTratamiento FROM tratamiento");
      resultadoDos = consultaDos.executeQuery();
      while(resultadoDos.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ReportesPacienteNombreTratamiento.addItem(resultadoDos.getObject(indice));        
        }   
      }  
      
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
  }
  
  /**
  * Método cargarReporteDoctorComboBox: Método que carga los JComboBox necesarios para los reportes del Doctor.
  * @param RDC3: JComboBox en el que se cargan el nombre de los diagnósticos registrados.
  * @param RDC4: JComboBox en el que se cargan los nombres de los tratamientos registrados.
  * @param RDC7: JComboBox en el que se cargan los identificadores de los pacientes registrados.
  * @param RDC8: JComboBox en el que se cargan los identificadores de los pacientes registrados.
  * @param RDC9: JComboBox en el que se cargan los identificadores de los pacientes registrados.
  * @param RDC10: JComboBox en el que se cargan los identificadores de los pacientes registrados.
  * @param RDC11: JComboBox en el que se cargan los identificadores de los pacientes registrados.
  */
  public void cargarReporteDoctorComboBox(JComboBox RDC3, JComboBox RDC4, JComboBox RDC7, JComboBox RDC8, JComboBox RDC9, JComboBox RDC10, JComboBox RDC11){
    ResultSet resultadoUno;
    ResultSet resultadoDos;
    ResultSet resultadoTres;
    PreparedStatement consultaUno;
    PreparedStatement consultaDos;
    PreparedStatement consultaTres;
    
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    RDC3.removeAllItems();
    RDC4.removeAllItems();
    RDC7.removeAllItems();
    RDC8.removeAllItems();
    RDC9.removeAllItems();
    RDC10.removeAllItems();
    RDC11.removeAllItems();
    
    RDC3.addItem("");
    RDC4.addItem("");
    RDC7.addItem("");
    RDC8.addItem("");
    RDC9.addItem("");
    RDC10.addItem("");
    RDC11.addItem("");

    try{
        
      consultaUno = conectar.prepareStatement("SELECT nombreDiagnostico FROM diagnostico");
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){   
          RDC3.addItem(resultadoUno.getObject(indice));        
        }   
      }  
      
      consultaDos = conectar.prepareStatement("SELECT nombreTratamiento FROM tratamiento");
      resultadoDos = consultaDos.executeQuery();
      while(resultadoDos.next()){ 
        for(int indice = 1; indice<2; indice++){   
          RDC4.addItem(resultadoDos.getObject(indice));        
        }   
      }  
      
      consultaTres = conectar.prepareStatement("SELECT identificadorPaciente FROM paciente");
      resultadoTres = consultaTres.executeQuery();
      while(resultadoTres.next()){ 
        for(int indice = 1; indice<2; indice++){   
          RDC7.addItem(resultadoTres.getObject(indice));    
          RDC8.addItem(resultadoTres.getObject(indice));
          RDC9.addItem(resultadoTres.getObject(indice));
          RDC10.addItem(resultadoTres.getObject(indice));
          RDC11.addItem(resultadoTres.getObject(indice));
        }   
      }

      
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
  }
  
  /**
  * Método cargarNombreDiagnosticosComboBox: Método que el nombre de los diagósticos registrados en un JComboBox. 
  * @param ComboAdminDiagnostico: JComboBox en el que se cargan los resultados de la consulta.
  */
  public void cargarNombreDiagnosticosComboBox(JComboBox ComboAdminDiagnostico){
    ResultSet resultadoUno;
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboAdminDiagnostico.removeAllItems();

    try{
      consultaUno = conectar.prepareStatement("SELECT nombreDiagnostico FROM diagnostico");
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboAdminDiagnostico.addItem(resultadoUno.getObject(indice));        
        }   
      }     
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
  }

  /**
  * Método cargarNumerLoteComboBox: Método que carga los números de los lotes de las vacunas en un JComboBox. 
  * @param ComboNumeroLoteVacunar: JComboBox en el que se cargan los resultados de la consulta.
  */
  public void cargarNumerLoteComboBox(JComboBox ComboNumeroLoteVacunar){
    ResultSet resultadoUno;
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboNumeroLoteVacunar.removeAllItems();

    try{
      consultaUno = conectar.prepareStatement("SELECT numeroLote FROM vacuna");
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboNumeroLoteVacunar.addItem(resultadoUno.getObject(indice));        
        }   
      }     
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
  }
  
  /**
  * Método cargarDoctorComboBox: Método que carga los las citas canceladas por el centro médico, 
  * los pacientes con cita registrada y las citas con estado registrada o asignada. 
  * @param ComboDoctorCitasCanceladasCM: JComboBox en el que se cargan las citas canceladas por el centro médico.
  * @param ComboDoctorPacienteCitaAsignada: JComboBox en el que se cargan pacientes con citas registradas.
  * @param ComboDoctorCitasRA: JComboBox en el que se cargan las citas asignadas o registradas.
  */
  public void cargarDoctorComboBox(JComboBox ComboDoctorCitasCanceladasCM, JComboBox ComboDoctorPacienteCitaAsignada, JComboBox ComboDoctorCitasRA){
    ResultSet resultadoUno;
    ResultSet resultadoDos;
    ResultSet resultadoTres;
    PreparedStatement consultaUno;
    PreparedStatement consultaDos;
    PreparedStatement consultaTres;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboDoctorCitasCanceladasCM.removeAllItems();
    ComboDoctorPacienteCitaAsignada.removeAllItems();
    ComboDoctorCitasRA.removeAllItems();
    try{
      consultaUno = conectar.prepareStatement("SELECT cita.identificadorCita  FROM cita JOIN citas_paciente ON cita.identificadorCita = citas_paciente.identificadorCita WHERE cita.estado = 'CANCELADA_POR_CENTRO_MEDICO'");
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboDoctorCitasCanceladasCM.addItem(resultadoUno.getObject(indice));        
        }   
      }
      
      consultaDos = conectar.prepareStatement("SELECT DISTINCT paciente.identificadorPaciente FROM paciente JOIN citas_paciente ON paciente.identificadorPaciente = citas_paciente.identificadorPaciente JOIN cita ON citas_paciente.identificadorCita = cita.identificadorCita WHERE cita.estado = 'REGISTRADA'");
      resultadoDos = consultaDos.executeQuery();
      while(resultadoDos.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboDoctorPacienteCitaAsignada.addItem(resultadoDos.getObject(indice));        
        }   
      }
      
      consultaTres = conectar.prepareStatement("SELECT DISTINCT cita.identificadorCita FROM paciente JOIN citas_paciente ON paciente.identificadorPaciente = citas_paciente.identificadorPaciente JOIN cita ON citas_paciente.identificadorCita = cita.identificadorCita WHERE cita.estado = 'REGISTRADA' or cita.estado = 'ASIGNADA'");
      resultadoTres = consultaTres.executeQuery();
      while(resultadoTres.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboDoctorCitasRA.addItem(resultadoTres.getObject(indice));        
        }   
      }
      
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
  }
  
  /**
  * Método cargaSecretarioCancelarComboBox: Método que carga los pacientes con citas registradas o asignadas en un JComboBox. 
  * @param ComboSecretarioUno: JComboBox en el que se cargan pacientes con citas registradas o asignadas.
  */
  public void cargaSecretarioCancelarComboBox(JComboBox ComboSecretarioUno){
    ResultSet resultadoDos;
    PreparedStatement consultaDos;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboSecretarioUno.removeAllItems();
    try{
      consultaDos = conectar.prepareStatement("SELECT DISTINCT paciente.identificadorPaciente FROM paciente JOIN citas_paciente ON paciente.identificadorPaciente = citas_paciente.identificadorPaciente JOIN cita ON citas_paciente.identificadorCita = cita.identificadorCita WHERE cita.estado = 'REGISTRADA' or cita.estado = 'ASIGNADA'");
      resultadoDos = consultaDos.executeQuery();
      while(resultadoDos.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboSecretarioUno.addItem(resultadoDos.getObject(indice));        
        }   
      }     
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
  }
  
  /**
  * Método cargaEnfermeroCancelarComboBox: Método que carga los pacientes con citas registradas o asignadas en un JComboBox. 
  * @param ComboEnfermeroPacienteCitaAsignada: JComboBox en el que se cargan pacientes con citas registradas o asignadas.
  */
  public void cargaEnfermeroCancelarComboBox(JComboBox ComboEnfermeroPacienteCitaAsignada){
    ResultSet resultadoDos;
    PreparedStatement consultaDos;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboEnfermeroPacienteCitaAsignada.removeAllItems();
    try{
      consultaDos = conectar.prepareStatement("SELECT DISTINCT paciente.identificadorPaciente FROM paciente JOIN citas_paciente ON paciente.identificadorPaciente = citas_paciente.identificadorPaciente JOIN cita ON citas_paciente.identificadorCita = cita.identificadorCita WHERE cita.estado = 'REGISTRADA' or cita.estado = 'ASIGNADA'");
      resultadoDos = consultaDos.executeQuery();
      while(resultadoDos.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboEnfermeroPacienteCitaAsignada.addItem(resultadoDos.getObject(indice));        
        }   
      }     
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
  }
   
  /**
  * Método cargarEnfermeroAsignarComboBox: Método que carga las citas canceladas por el centro médico en un JComboBox. 
  * @param ComboEnfermeroCitasAsignar: JComboBox en el que se cargan las citas con estado canceladas por el centro médico.
  */
  public void cargarEnfermeroAsignarComboBox(JComboBox ComboEnfermeroCitasAsignar){
    ResultSet resultadoUno;
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboEnfermeroCitasAsignar.removeAllItems();

    try{
      consultaUno = conectar.prepareStatement("SELECT cita.identificadorCita  FROM cita JOIN citas_paciente ON cita.identificadorCita = citas_paciente.identificadorCita WHERE cita.estado = 'CANCELADA_POR_CENTRO_MEDICO'");
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboEnfermeroCitasAsignar.addItem(resultadoUno.getObject(indice));        
        }   
      }     
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
  }
   
  /**
  * Método cargarSecretarioAsignarComboBox: Método que carga las citas canceladas por el centro médico en un JComboBox. 
  * @param ComboSecretarioTres: JComboBox en el que se cargan las citas con estado canceladas por el centro médico.
  */
  public void cargarSecretarioAsignarComboBox(JComboBox ComboSecretarioTres){
    ResultSet resultadoUno;
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboSecretarioTres.removeAllItems();

    try{
      consultaUno = conectar.prepareStatement("SELECT cita.identificadorCita  FROM cita JOIN citas_paciente ON cita.identificadorCita = citas_paciente.identificadorCita WHERE cita.estado = 'CANCELADA_POR_CENTRO_MEDICO'");
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboSecretarioTres.addItem(resultadoUno.getObject(indice));        
        }   
      }     
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
  }
  
  /**
  * Método cargarDoctorCitasRAAtender: Método que carga los pacientes con citas registradas o asignadas en un JComboBox. 
  * @param ComboDoctorCitasRA: JComboBox en el que se cargan los pacientes con citas registradas o asignadas.
  */
  public void cargarDoctorCitasRAAtender(JComboBox ComboDoctorCitasRA){
    ResultSet resultadoUno;
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboDoctorCitasRA.removeAllItems();
    try{
      consultaUno = conectar.prepareStatement("SELECT citas_paciente.identificadorCita FROM cita JOIN citas_paciente ON cita.identificadorCita = citas_paciente.identificadorCita WHERE cita.estado = 'REGISTRADA' or cita.estado = 'ASIGNADA'");
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){    
          ComboDoctorCitasRA.addItem(resultadoUno.getObject(indice));
        }   
      }   
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
  }
  
  /**
  * Método cargarCitasPendientes: Método que carga las citas pendientes de un paciente específico. 
  * @param ComboPacienteCitasPendientes: JComboBox en el que se cargan las citas registradas de un paciente.
  * @param pUsuario: String que representa el paciente del que se requiere obtener las citas registradas.
  */
  public void cargarCitasPendientes(JComboBox ComboPacienteCitasPendientes, String pUsuario){
    ResultSet resultadoUno;
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboPacienteCitasPendientes.removeAllItems();
    try{
      consultaUno = conectar.prepareStatement("SELECT cita.identificadorCita FROM usuario JOIN paciente_usuario ON usuario.cedula = paciente_usuario.cedula JOIN paciente ON paciente_usuario.identificadorPaciente = paciente.identificadorPaciente JOIN citas_paciente ON citas_paciente.identificadorPaciente = paciente.identificadorPaciente JOIN cita ON cita.identificadorCita = citas_paciente.identificadorCita WHERE cita.estado = 'REGISTRADA' AND nombreUsuario = ?");
      consultaUno.setString(1, pUsuario);
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){    
          ComboPacienteCitasPendientes.addItem(resultadoUno.getObject(indice));
        }   
      }   
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
  }
  
  /**
  * Método cargarDoctorCitasRACancelar: Método que carga las citas asignadas o registradas de un paciente. 
  * @param ComboDoctorCitas: JComboBox en el que se cargan las citas registradas o asignadas de un paciente.
  * @param pIdentificador: String que representa el paciente del que se requiere obtener las citas registradas o asignadas.
  */
  public void cargarDoctorCitasRACancelar(JComboBox ComboDoctorCitas, int pIdentificador){
    ResultSet resultadoUno;
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboDoctorCitas.removeAllItems();
    try{
      consultaUno = conectar.prepareStatement("SELECT citas_paciente.identificadorCita FROM cita JOIN citas_paciente ON cita.identificadorCita = citas_paciente.identificadorCita WHERE identificadorPaciente = ? AND (cita.estado = 'ASIGNADA' OR cita.estado = 'REGISTRADA')");
      consultaUno.setInt(1, pIdentificador);
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboDoctorCitas.addItem(resultadoUno.getObject(indice));   
        }   
      }   
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
  }
  
  /**
  * Método cargarEnfermeroCitasRACancelar: Método que carga las citas asignadas o registradas de un paciente. 
  * @param ComboEnfermeroCitaCancelar: JComboBox en el que se cargan las citas registradas o asignadas de un paciente.
  * @param pIdentificador: String que representa el paciente del que se requiere obtener las citas registradas o asignadas.
  */
  public void cargarEnfermeroCitasRACancelar(JComboBox ComboEnfermeroCitaCancelar, int pIdentificador){
    ResultSet resultadoUno;
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboEnfermeroCitaCancelar.removeAllItems();
    try{
      consultaUno = conectar.prepareStatement("SELECT citas_paciente.identificadorCita FROM cita JOIN citas_paciente ON cita.identificadorCita = citas_paciente.identificadorCita WHERE identificadorPaciente = ? AND (cita.estado = 'ASIGNADA' OR cita.estado = 'REGISTRADA')");
      consultaUno.setInt(1, pIdentificador);
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboEnfermeroCitaCancelar.addItem(resultadoUno.getObject(indice));   
        }   
      }   
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
  }
  
  /**
  * Método cargarSecretarioCitasRACancelar: Método que carga las citas asignadas o registradas de un paciente. 
  * @param ComboSecretarioDos: JComboBox en el que se cargan las citas registradas o asignadas de un paciente.
  * @param pIdentificador: String que representa el paciente del que se requiere obtener las citas registradas o asignadas.
  */
  public void cargarSecretarioCitasRACancelar(JComboBox ComboSecretarioDos, int pIdentificador){
    ResultSet resultadoUno;
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboSecretarioDos.removeAllItems();
    try{
      consultaUno = conectar.prepareStatement("SELECT citas_paciente.identificadorCita FROM cita JOIN citas_paciente ON cita.identificadorCita = citas_paciente.identificadorCita WHERE identificadorPaciente = ? AND (cita.estado = 'ASIGNADA' OR cita.estado = 'REGISTRADA')");
      consultaUno.setInt(1, pIdentificador);
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboSecretarioDos.addItem(resultadoUno.getObject(indice));   
        }   
      }   
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
  }
  
  /**
  * Método cargarNombreDiagnostico: Método que carga los nombres de los diagnósticos registrados. 
  * @param ComboDoctorDiagnostico: JComboBox en el que se cargan los nombres de los diagnósticos registrados.
  */
  public void cargarNombreDiagnostico(JComboBox ComboDoctorDiagnostico){
    ResultSet resultadoUno;
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboDoctorDiagnostico.removeAllItems();
    try{
      consultaUno = conectar.prepareStatement("SELECT nombreDiagnostico FROM diagnostico");
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboDoctorDiagnostico.addItem(resultadoUno.getObject(indice));   
        }   
      }   
    }
    catch(Exception error){ 
      System.out.println(error);
    }      
  }
  
  /**
  * Método cargarTratamiento: Método que carga los tratamientos de un diagnóstico registrado. 
  * @param ComboDoctorTratamiento: JComboBox en el que se cargan los tratamientos de un diagnóstico registrado.
  * @param pDiagnostico: String que representa el nombre del diagnóstico que contiene los tratamientos.
  */
  public void cargarTratamiento(String pDiagnostico, JComboBox ComboDoctorTratamiento){
    ResultSet resultadoUno;
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboDoctorTratamiento.removeAllItems();
    try{
      consultaUno = conectar.prepareStatement("SELECT nombreTratamiento FROM diagnostico_contiene_tratamiento WHERE nombreDiagnostico = ?");
      consultaUno.setString(1, pDiagnostico);
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboDoctorTratamiento.addItem(resultadoUno.getObject(indice));   
        }   
      }   
    }
    catch(Exception error){ 
      System.out.println(error);
    }      
  }
  
  /**
  * Método cargarIDPaciente: Método que carga el Id del paciente asociado a una cita específica. 
  * @param ComboDoctorIDPaciente: JComboBox en el que se carga el paciente que solicitó la cita.
  * @param pIdentificadorCita: int que representa la cita de la cual se desea conocer el paciente.
  */
  public void cargarIDPaciente(int pIdentificadorCita, JComboBox ComboDoctorIDPaciente){
    ResultSet resultadoUno;
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboDoctorIDPaciente.removeAllItems();
    try{
      consultaUno = conectar.prepareStatement("SELECT citas_paciente.identificadorPaciente FROM cita JOIN citas_paciente ON cita.identificadorCita = citas_paciente.identificadorCita WHERE cita.identificadorCita = ?");
      consultaUno.setInt(1, pIdentificadorCita);
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
        for(int indice = 1; indice<2; indice++){   
          ComboDoctorIDPaciente.addItem(resultadoUno.getObject(indice));   
        }   
      }   
    }
    catch(Exception error){ 
      System.out.println(error);
    }  
  }
  
  /**
  * Método ObtenerFechayHora: Método que obtiene la fecha y hora de una cita. 
  * @param pIdentificadorCita: int que representa la cita.
  * @return listaFechaYHora: objeto de tipo  Lista<Date> que contiene la hora y fecha de la cita.
  */
  public Lista<Date> ObtenerFechayHora(int pIdentificadorCita){
    ResultSet resultadoUno;
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyy-mm-dd");
    SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");
    
    String fecha = "";
    String hora = "";
    Lista<Date> listaFechaYHora = new Lista<Date>();
    try{
      consultaUno = conectar.prepareStatement("SELECT fecha, hora FROM cita WHERE identificadorCita = ?");
      consultaUno.setInt(1, pIdentificadorCita);
      resultadoUno = consultaUno.executeQuery();
      while(resultadoUno.next()){ 
          fecha = resultadoUno.getObject(1).toString();
          hora = resultadoUno.getObject(2).toString();
      }
      Date fechaDate = formatoFecha.parse(fecha);
      Date horaDate = formatoHora.parse(hora);
      listaFechaYHora.add(fechaDate);
      listaFechaYHora.add(horaDate);    
    }
    catch(Exception error){ 
      System.out.println(error);
    }     
    return listaFechaYHora;
  }
  
  /**
  * Método consultaBitacora: Método que la bitácora de una cita en una tabla. 
  * @param tablaConsultaBitacora: tabla que carga los datos de la bitácora de  una cita.
  * @param pIdentificadorCita: int que representa la cita.
  */
  public void consultaBitacora(JTable tablaConsultaBitacora, int pIdentificadorCita){
    DefaultTableModel modeloTabla = (DefaultTableModel) tablaConsultaBitacora.getModel();
    modeloTabla.setRowCount(0);
    PreparedStatement consultaInfo;
    ResultSet resultado;
    ResultSetMetaData datosResultado;
    int columnas;

    try{
      Conexion nuevaConexion = new Conexion();
      Connection conectar = nuevaConexion.conectar();
      consultaInfo = conectar.prepareStatement("SELECT bitacora.identificadorBitacora, cita.identificadorCita, bitacora_cita_estado.estado, fechaModificacion, bitacora_horamodificacion.horaModificacion FROM cita JOIN bitacora_cita ON cita.identificadorCita = bitacora_cita.identificadorCita JOIN bitacora_cita_estado ON bitacora_cita.identificadorCita = bitacora_cita_estado.identificadorCita JOIN bitacora ON bitacora_cita.identificadorBitacora = bitacora.identificadorBitacora JOIN bitacora_horamodificacion ON bitacora.identificadorBitacora = bitacora_horamodificacion.identificadorBitacora JOIN bitacora_fechamodificacion ON bitacora.identificadorBitacora = bitacora_fechamodificacion.identificadorBitacora WHERE cita.identificadorCita = ?");
      consultaInfo.setInt(1, pIdentificadorCita);
        
      resultado = consultaInfo.executeQuery();
      datosResultado = resultado.getMetaData();
      columnas = datosResultado.getColumnCount();

      while(resultado.next()){
        Object [] fila = new Object[columnas];
        for(int indice = 0; indice<columnas; indice++){
          fila[indice] = resultado.getObject(indice +1);
        }
        modeloTabla.addRow(fila);
      }
    }
    catch(Exception error){
 
    }    
  }
  
   /**
   * ----------------------------------------------------------------MÉTODOS BUSCAR------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------MÉTODOS BUSCAR------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------MÉTODOS BUSCAR------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------MÉTODOS BUSCAR------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------MÉTODOS BUSCAR------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------MÉTODOS BUSCAR------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------MÉTODOS BUSCAR------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------MÉTODOS BUSCAR------------------------------------------------------------------------------------------
   */
  
  /**
  * Método buscarCita: Método que busca una cita en la lista "citas" y la retorna. 
  * @param pIdentificadorCita: int que representa la cita.
  * @return cita: objeto de tipo Cita que es el resultado de la búsqueda.
  */
  public Cita buscarCita(int pIdentificadorCita){
    Cita cita = new Cita();
    for(int contador = 0; contador != citas.getSize(); contador++){
      if(citas.get(contador).getIdentificadorCita() == pIdentificadorCita){
        cita = citas.get(contador);
        break;
      }    
    }
    return cita;  
  } 
  
  /**
  * Método buscarVacuna: Método que busca una vacuna en la lista "vacunas" y la retorna. 
  * @param pNumeroLote: int que representa el número de lote de una vacuna.
  * @return vacuna: objeto de tipo vacuna que es el resultado de la búsqueda.
  */
  public Vacuna buscarVacuna(int pNumeroLote){
    Vacuna vacuna = new Vacuna();
    for(int contador = 0; contador != vacunas.getSize(); contador++){
      if(vacunas.get(contador).getNumeroLote() == pNumeroLote){
        vacuna = vacunas.get(contador);
        break;
      }    
    }
    return vacuna;  
  } 
  
  /**
  * Método buscarDoctor: Método que busca un doctor en la lista "doctores" y lo retorna. 
  * @param pCodigo: int que representa el código del doctor.
  * @return doctor: objeto de tipo doctor que es el resultado de la búsqueda.
  */
  public Doctor buscarDoctor(int pCodigo){
    Doctor doctor=new Doctor();
    for (int indice=0;indice!=doctores.getSize();indice++){
      if (doctores.get(indice).getCodigoDoctor()==pCodigo){
        doctor=doctores.get(indice);
        break;
      }
    }
    return doctor;
  }
  
  /**
  * Método buscarCentro: Método que busca un centro en la lista "centrosAtencion" y lo retorna. 
  * @param pIdentificador: int que representa el código del centro.
  * @return centro: objeto de tipo CentroAtencion que es el resultado de la búsqueda.
  */
  public CentroAtencion buscarCentro(int pIdentificador){
      CentroAtencion centro= new CentroAtencion();
      for (int indice=0;indice!=centrosAtencion.getSize();indice++){
          if (centrosAtencion.get(indice).getCodigo()==pIdentificador){
              centro=centrosAtencion.get(indice);
              break;
          }
      }
      return centro;
  }
  
  /**
  * Método buscarPaciente: Método que busca un paciente en la lista "pacientes" y lo retorna. 
  * @param pIdentificador: int que representa el ID del paciente.
  * @return paciente: objeto de tipo Paciente que es el resultado de la búsqueda.
  */
  public Paciente buscarPaciente (int pIdentificador){
    Paciente paciente = new Paciente();
    for (int indice = 0; indice != pacientes.getSize(); indice ++){
      if (pIdentificador == pacientes.get(indice).getIdentificadorPaciente()){
        paciente = pacientes.get(indice);
        break;
      }
    }
    return paciente;
  }
  
  /**
  * Método buscarTratamiento: Método que busca un tratamiento en la lista "tratamientos" y lo retorna. 
  * @param pNombre: String que representa el nombre del tratamiento.
  * @return tratamiento: objeto de tipo Tratamiento que es el resultado de la búsqueda.
  */
  public Tratamiento buscarTratamiento (String pNombre){
    Tratamiento tratamiento= new Tratamiento();
    for (int indice = 0; indice != tratamientos.getSize(); indice ++){
      if (pNombre.equals(tratamientos.get(indice).getNombreTratamiento()) ){
        tratamiento=tratamientos.get(indice);
        break;
      }
    }
    return tratamiento;
  }
  
  /**
  * Método buscarDiagnostico: Método que busca un diagnóstico en la lista "diagnosticos" y lo retorna. 
  * @param pNombre: String que representa el nombre del diagnostico.
  * @return diagnostico: objeto de tipo Diagnostico que es el resultado de la búsqueda.
  */
  public Diagnostico buscarDiagnostico (String pNombre){
    Diagnostico diagnostico = new Diagnostico();
    for (int indice = 0; indice != diagnosticos.getSize(); indice ++){
      if (pNombre.equals(diagnosticos.get(indice).getNombreDiagnostico()) ){
        diagnostico=diagnosticos.get(indice);
        break;
      }
    }
    return diagnostico;
  }
   
  /**
  * Método buscarFuncionario: Método que busca un funcionario en la lista "funcionarios" y lo retorna. 
  * @param pIdentificador: int que representa el Id del funcionario.
  * @return funcionario: objeto de tipo Funcionario que es el resultado de la búsqueda.
  */
  public Funcionario buscarFuncionario(int pIdentificador){
    Funcionario funcionario = new Funcionario();
    for (int indice = 0; indice != funcionarios.getSize(); indice++){
      if (pIdentificador == funcionarios.get(indice).getIdentificadorFuncionario()){
       funcionario = funcionarios.get(indice);
       break;
     }
   }
   return funcionario;
   }
  
  /**
  * Método buscarBitacora: Método que busca una bitácora en la lista "bitacoras" y la retorna. 
  * @param pIdentificador: int que representa el Id de la bitácora.
  * @return bitacora: objeto de tipo Bitacora que es el resultado de la búsqueda.
  */
  public Bitacora buscarBitacora (int pIdentificador){
    Bitacora bitacora = new Bitacora();
     for (int indice = 0; indice != bitacoras.getSize(); indice++){         
       if (pIdentificador == bitacoras.get(indice).getIdentificador()){
         bitacora = bitacoras.get(indice);
         break;
       }
     }
     return bitacora;  
  }
   /**
   * ----------------------------------------------------------------CARGA BASE DATOS------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA BASE DATOS------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA BASE DATOS------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA BASE DATOS------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA BASE DATOS------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA BASE DATOS------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA BASE DATOS------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA BASE DATOS------------------------------------------------------------------------------------------
   */
  
  /**
  * Método CargarBaseDatos: Método que carga los elementos de la base de datos y los convierte a objetos de Java. 
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void CargarBaseDatos()throws SQLException, ParseException{
    cargarCitaBaseDatos();
    cargarDoctorBaseDatos();
    cargarSecretarioBaseDatos();
    cargarEnfermeroBaseDatos();
    cargarPacienteBaseDatos();
    cargarCentroAtencionBaseDatos();
    cargarDiagnosticoBaseDatos();
    cargarTratamientoBaseDatos();
    cargarDoctorEspecialidades();
    cargarCentroTipo();
    cargarDiagnosticosCita();
    cargarPacientesCitas();
    cargarTratamientosDiagnostico();
    cargarObservacionesDiagnostico();
    cargarFuncionarioCita();
    cargarBitacora();
    cargarBitacoraCita();
    cargarFuncionariosCitas();
    cargarVacunas();
    cargarVacunasPaciente();
  }
 
  /**
  * Método cargarBitacoraCita: Método que carga las bitácoras y las asocia a las citas correspondientes. 
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarBitacoraCita() throws SQLException, ParseException{
    ResultSet resultado;
    PreparedStatement consultarBitacora;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarBitacora = conectar.prepareStatement("SELECT bitacora.identificadorBitacora, bitacora_cita.identificadorCita FROM bitacora JOIN bitacora_cita ON bitacora.identificadorBitacora = bitacora_cita.identificadorBitacora JOIN cita ON cita.identificadorCita = bitacora_cita.identificadorCita");
    resultado = consultarBitacora.executeQuery();    
    
    while (resultado.next()){
        
      int identificadorBitacora = (Integer.parseInt(String.valueOf(resultado.getObject(1))));
      int identificadorCita = (Integer.parseInt(String.valueOf(resultado.getObject(2))));
      Bitacora bitacora =  buscarBitacora(identificadorBitacora);
      
      Cita cita = buscarCita(identificadorCita);
      
      bitacora.setCita(cita); 
      cita.setBitacora(bitacora);   
    } 
  }
  
  /**
  * Método cargarBitacora: Método que carga las bitácoras y los convierte a objetos de tipo Bitacora.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarBitacora() throws SQLException, ParseException{
    ResultSet resultado;
    PreparedStatement consultarBitacora;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarBitacora = conectar.prepareStatement("SELECT bitacora.identificadorBitacora FROM bitacora"); 
    resultado = consultarBitacora.executeQuery();    
    
    while (resultado.next()){
      int identificadorBitacora = (Integer.parseInt(String.valueOf(resultado.getObject(1))));
      Bitacora nuevaBitacora = new Bitacora(identificadorBitacora);
      bitacoras.add(nuevaBitacora);
    }
  }
  
  /**
  * Método cargarCitaBaseDatos: Método que carga la información de las citas de la base de datos y lo convierte en objetos de tipo Cita.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarCitaBaseDatos() throws SQLException, ParseException{
    ResultSet resultado;
    PreparedStatement consultarCitas;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarCitas = conectar.prepareStatement("SELECT * FROM cita");
    resultado = consultarCitas.executeQuery();    
    
    while (resultado.next()){
      SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss");
      int identificadorCita = (Integer.parseInt(String.valueOf(resultado.getObject(1))));
      Date fecha = (formatoFecha.parse(String.valueOf(resultado.getObject(2))));
      Date hora = (formatoHora.parse(String.valueOf(resultado.getObject(3))));
      String area = String.valueOf(resultado.getObject(4));
      String observacion = String.valueOf(resultado.getObject(5));
      EstadoCita estado = EstadoCita.valueOf(String.valueOf(resultado.getObject(6)));
      Cita nuevaCita = new Cita(identificadorCita, fecha, hora, area, observacion, estado);
      citas.add(nuevaCita);
    }
  }
  
  /**
  * Método cargarDoctorBaseDatos: Método que carga la información de los doctores de la base de datos y lo convierte en objetos de tipo Doctor.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarDoctorBaseDatos() throws SQLException, ParseException{
    ResultSet resultado;
    
    PreparedStatement consultarDoctor;
    
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarDoctor = conectar.prepareStatement("SELECT usuario.cedula, nombre, apellido1, apellido2, rol, nombreUsuario, contraseña, funcionario.identificadorFuncionario, tipo, fechaIngreso, area, codigoDoctor FROM usuario JOIN funcionario_usuario ON usuario.cedula = funcionario_usuario.cedula JOIN funcionario ON funcionario_usuario.identificadorFuncionario = funcionario.identificadorFuncionario JOIN doctor ON funcionario.identificadorFuncionario = doctor.identificadorFuncionario");

    resultado= consultarDoctor.executeQuery();    

    String cedula = "";
    String nombre = "";
    String apellido1 = "";
    String apellido2 = "";
    String rol = "";
    String nombreUsuario = "";
    String contraseña = "";
    
    int identificadorFuncionario = 0;
    TipoFuncionario tipoFuncionario = null;
    Date fechaIngreso = null;
    String area = "";
    
    int codigoDoctor = 0;
      
    while (resultado.next()){
      cedula = String.valueOf(resultado.getObject(1));
      nombre = String.valueOf(resultado.getObject(2));
      apellido1 = String.valueOf(resultado.getObject(3));
      apellido2 = String.valueOf(resultado.getObject(4));
      rol = String.valueOf(resultado.getObject(5));
      nombreUsuario = String.valueOf(resultado.getObject(6));
      contraseña = String.valueOf(resultado.getObject(7));
      
    
      SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
      identificadorFuncionario = Integer.parseInt(String.valueOf(resultado.getObject(8)));
      tipoFuncionario = TipoFuncionario.valueOf(String.valueOf(resultado.getObject(9)));
      fechaIngreso = formatoFecha.parse(String.valueOf(resultado.getObject(10)));
      area  = String.valueOf(resultado.getObject(11));
    

      codigoDoctor = Integer.parseInt(String.valueOf(resultado.getObject(12)));
       
       Doctor nuevoDoctor = new Doctor(cedula, nombre, apellido1, apellido2, rol, nombreUsuario, contraseña, identificadorFuncionario, tipoFuncionario, fechaIngreso, area, codigoDoctor);
       usuarios.add(nuevoDoctor);
       funcionarios.add(nuevoDoctor);
       doctores.add(nuevoDoctor);
  }            
 }
    
  /**
  * Método cargarSecretarioBaseDatos: Método que carga la información de los secretarios de la base de datos y lo convierte en objetos de tipo Secretario.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarSecretarioBaseDatos() throws SQLException, ParseException{
    ResultSet resultado;
    PreparedStatement consultarSecretario;
    
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarSecretario = conectar.prepareStatement("SELECT usuario.cedula, nombre, apellido1, apellido2, rol, nombreUsuario, contraseña, funcionario.identificadorFuncionario, tipo, fechaIngreso, area, codigoSecretario FROM usuario JOIN funcionario_usuario ON usuario.cedula = funcionario_usuario.cedula JOIN funcionario ON funcionario_usuario.identificadorFuncionario = funcionario.identificadorFuncionario JOIN secretario ON funcionario.identificadorFuncionario = secretario.identificadorFuncionario");

    resultado= consultarSecretario.executeQuery();    

    String cedula = "";
    String nombre = "";
    String apellido1 = "";
    String apellido2 = "";
    String rol = "";
    String nombreUsuario = "";
    String contraseña = "";
    
    int identificadorFuncionario = 0;
    TipoFuncionario tipoFuncionario = null;
    Date fechaIngreso = null;
    String area = "";
    
    int codigoSecretario = 0;
      
    while (resultado.next()){
      cedula = String.valueOf(resultado.getObject(1));
      nombre = String.valueOf(resultado.getObject(2));
      apellido1 = String.valueOf(resultado.getObject(3));
      apellido2 = String.valueOf(resultado.getObject(4));
      rol = String.valueOf(resultado.getObject(5));
      nombreUsuario = String.valueOf(resultado.getObject(6));
      contraseña = String.valueOf(resultado.getObject(7));
      
    
      SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
      identificadorFuncionario = Integer.parseInt(String.valueOf(resultado.getObject(8)));
      tipoFuncionario = TipoFuncionario.valueOf(String.valueOf(resultado.getObject(9)));
      fechaIngreso = formatoFecha.parse(String.valueOf(resultado.getObject(10)));
      area  = String.valueOf(resultado.getObject(11));
    

      codigoSecretario = Integer.parseInt(String.valueOf(resultado.getObject(12)));
       
       Secretario nuevoSecretario = new Secretario(cedula, nombre, apellido1, apellido2, rol, nombreUsuario, contraseña, identificadorFuncionario, tipoFuncionario, fechaIngreso, area, codigoSecretario);
       usuarios.add(nuevoSecretario);
       funcionarios.add(nuevoSecretario);
       secretarios.add(nuevoSecretario);
  }            
 }
  
  /**
  * Método cargarEnfermeroBaseDatos: Método que carga la información de los enfermeros de la base de datos y lo convierte en objetos de tipo Enfermero.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarEnfermeroBaseDatos() throws SQLException, ParseException{
    ResultSet resultado;
    PreparedStatement consultarEnfermero;
    
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarEnfermero = conectar.prepareStatement("SELECT usuario.cedula, nombre, apellido1, apellido2, rol, nombreUsuario, contraseña, funcionario.identificadorFuncionario, tipo, fechaIngreso, area, enfermero.codigoEnfermero, indicadorPersonasACargo, indicadorExperienciaCapacitaciones FROM usuario JOIN funcionario_usuario ON usuario.cedula = funcionario_usuario.cedula JOIN funcionario ON funcionario_usuario.identificadorFuncionario = funcionario.identificadorFuncionario JOIN enfermero_funcionario ON funcionario.identificadorFuncionario = enfermero_funcionario.identificadorFuncionario JOIN enfermero ON enfermero_funcionario.codigoEnfermero = enfermero.codigoEnfermero");

    resultado= consultarEnfermero.executeQuery();    

    String cedula = "";
    String nombre = "";
    String apellido1 = "";
    String apellido2 = "";
    String rol = "";
    String nombreUsuario = "";
    String contraseña = "";
    
    int identificadorFuncionario = 0;
    TipoFuncionario tipoFuncionario = null;
    Date fechaIngreso = null;
    String area = "";
    
    int codigoEnfermero= 0;
    boolean indicadorUno = false;
    boolean indicadorDos = false;
      
    while (resultado.next()){
      cedula = String.valueOf(resultado.getObject(1));
      nombre = String.valueOf(resultado.getObject(2));
      apellido1 = String.valueOf(resultado.getObject(3));
      apellido2 = String.valueOf(resultado.getObject(4));
      rol = String.valueOf(resultado.getObject(5));
      nombreUsuario = String.valueOf(resultado.getObject(6));
      contraseña = String.valueOf(resultado.getObject(7));
      
    
      SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
      identificadorFuncionario = Integer.parseInt(String.valueOf(resultado.getObject(8)));
      tipoFuncionario = TipoFuncionario.valueOf(String.valueOf(resultado.getObject(9)));
      fechaIngreso = formatoFecha.parse(String.valueOf(resultado.getObject(10)));
      area  = String.valueOf(resultado.getObject(11));
    

      codigoEnfermero = Integer.parseInt(String.valueOf(resultado.getObject(12)));
      int indicador1 = Integer.parseInt(String.valueOf(resultado.getObject(13)));
      int indicador2 = Integer.parseInt(String.valueOf(resultado.getObject(14)));
      if(indicador1 == 0){
        indicadorUno = false;  
      }
      else{
        indicadorUno = true;      
      }
      if(indicador2 == 0){
        indicadorDos = false;
      }
      else{
        indicadorDos = true;    
      }

       Enfermero nuevoEnfermero = new Enfermero(cedula, nombre, apellido1, apellido2, rol, nombreUsuario, contraseña, identificadorFuncionario, tipoFuncionario, fechaIngreso, area, codigoEnfermero);
       nuevoEnfermero.setIndicadorPersonasACargo(indicadorUno);
       nuevoEnfermero.setIndicadorExperienciaCapacitaciones(indicadorDos);
       usuarios.add(nuevoEnfermero);
       funcionarios.add(nuevoEnfermero);
       enfermeros.add(nuevoEnfermero);
  }            
 }
  
  /**
  * Método cargarPacienteBaseDatos: Método que carga la información de los paciente de la base de datos y lo convierte en objetos de tipo Paciente.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarPacienteBaseDatos() throws SQLException, ParseException{
    ResultSet resultado;
    PreparedStatement consultarPaciente;
    
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarPaciente = conectar.prepareStatement("SELECT usuario.cedula, nombre, apellido1, apellido2, rol, nombreUsuario, contraseña,  paciente.identificadorPaciente, nacionalidad, residencia, fechaNacimiento, tipoSangre FROM usuario JOIN paciente_usuario ON usuario.cedula = paciente_usuario.cedula JOIN paciente ON paciente_usuario.identificadorPaciente = paciente.identificadorPaciente");

    resultado= consultarPaciente.executeQuery();    

    String cedula = "";
    String nombre = "";
    String apellido1 = "";
    String apellido2 = "";
    String rol = "";
    String nombreUsuario = "";
    String contraseña = "";

    int identificadorPaciente = 0;
    String nacionalidad = "";
    String residencia = "";
    Date fechaNacimiento = null;
    TipoSangre tipoSangre = null;
      
    while (resultado.next()){
      cedula = String.valueOf(resultado.getObject(1));
      nombre = String.valueOf(resultado.getObject(2));
      apellido1 = String.valueOf(resultado.getObject(3));
      apellido2 = String.valueOf(resultado.getObject(4));
      rol = String.valueOf(resultado.getObject(5));
      nombreUsuario = String.valueOf(resultado.getObject(6));
      contraseña = String.valueOf(resultado.getObject(7));
      
    
      SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
      identificadorPaciente = Integer.parseInt(String.valueOf(resultado.getObject(8)));
      nacionalidad = String.valueOf(resultado.getObject(9));
      residencia = String.valueOf(resultado.getObject(10));
      fechaNacimiento = formatoFecha.parse(String.valueOf(resultado.getObject(11)));
      tipoSangre = TipoSangre.valueOf(String.valueOf(resultado.getObject(12)));

      Paciente nuevoPaciente = new Paciente(cedula, nombre, apellido1, apellido2, rol, nombreUsuario, contraseña, identificadorPaciente, nacionalidad, residencia, fechaNacimiento, tipoSangre);
      usuarios.add(nuevoPaciente);
      pacientes.add(nuevoPaciente);
  }            
 }
  
  /**
  * Método cargarCentroAtencionBaseDatos: Método que carga la información de los centro de atención de la base de datos y lo convierte en objetos de tipo CentroAtencion.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarCentroAtencionBaseDatos() throws SQLException, ParseException{
    ResultSet resultado;
    PreparedStatement consultarCentros;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarCentros = conectar.prepareStatement("SELECT * FROM centroatencion");
    resultado = consultarCentros.executeQuery();    
    
    while (resultado.next()){
      int codigoCentro = (Integer.parseInt(String.valueOf(resultado.getObject(1))));
      String nombre = String.valueOf(resultado.getObject(2));
      String lugar = String.valueOf(resultado.getObject(3));
      int capacidad = Integer.parseInt(String.valueOf(resultado.getObject(4)));
      
      CentroAtencion nuevoCentro = new CentroAtencion(nombre, lugar, capacidad);
      centrosAtencion.add(nuevoCentro);
    }
  }
   
  /**
  * Método cargarDiagnosticoBaseDatos: Método que carga la información de los diagnosticos de la base de datos y lo convierte en objetos de tipo Diagnostico.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarDiagnosticoBaseDatos() throws SQLException, ParseException{
    ResultSet resultado;
    PreparedStatement consultarDiagnostico;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarDiagnostico = conectar.prepareStatement("SELECT * FROM diagnostico");
    resultado = consultarDiagnostico.executeQuery();    
    
    while (resultado.next()){
      String nombre = String.valueOf(resultado.getObject(1));
      NivelDiagnostico nivel = NivelDiagnostico.valueOf(String.valueOf(resultado.getObject(2)));

      Diagnostico nuevoDiagnostico = new Diagnostico(nombre, nivel);
      diagnosticos.add(nuevoDiagnostico);
    }
  }
   
  /**
  * Método cargarTratamientoBaseDatos: Método que carga la información de los tratamientos de la base de datos y lo convierte en objetos de tipo Tratamiento.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarTratamientoBaseDatos() throws SQLException, ParseException{
    ResultSet resultado;
    PreparedStatement consultarTratamiento;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarTratamiento = conectar.prepareStatement("SELECT * FROM tratamiento");
    resultado = consultarTratamiento.executeQuery();    
    
    while (resultado.next()){
      String nombre = String.valueOf(resultado.getObject(1));
      String tipo = String.valueOf(resultado.getObject(2));
      int dosis = Integer.parseInt(String.valueOf(resultado.getObject(3)));

      Tratamiento nuevoTratamiento = new Tratamiento(nombre, tipo, dosis);
      tratamientos.add(nuevoTratamiento);//
    }
  }
   
  /**
  * Método cargarDoctorEspecialidades: Método que carga la información de las especialidades de la base de datos y se las asigna al doctor correspondiente.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarDoctorEspecialidades() throws SQLException, ParseException{
    ResultSet resultado;
    PreparedStatement consultarDoctorEspecialidad;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarDoctorEspecialidad = conectar.prepareStatement("SELECT * FROM doctor_especialidades");
    resultado = consultarDoctorEspecialidad.executeQuery();   
    
    while (resultado.next()){
        int codigoDoctor=Integer.parseInt(String.valueOf(resultado.getObject(1)));
        String especialidad=String.valueOf(resultado.getObject(2));
        buscarDoctor(codigoDoctor).añadirEspecialidad(especialidad);
    }
  }
  
  /**
  * Método cargarCentroTipo: Método que carga la información de los tipos de centro de la base de datos y se las asigna al centro correspondiente.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarCentroTipo() throws SQLException, ParseException{
    ResultSet resultado;
    PreparedStatement consultarCentroTipo;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarCentroTipo = conectar.prepareStatement("SELECT tipocentro.identificadorTipo, tipocentro.tipoCentro, codigoCentro FROM tipocentro JOIN centro_tipo ON centro_tipo.identificadorTipo = tipocentro.identificadorTipo");
    resultado = consultarCentroTipo.executeQuery();   
    
    while (resultado.next()){
      int codigoCentro=Integer.parseInt(String.valueOf(resultado.getObject(3)));
      String nombreTipo=String.valueOf(resultado.getObject(2));
      buscarCentro(codigoCentro).setTipo(nombreTipo);
    }
  }
    
  /**
  * Método cargarDiagnosticosCita: Método que carga la información de los diagnósticos de la base de datos y se las asigna a la cita correspondiente.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarDiagnosticosCita() throws SQLException, ParseException{
     ResultSet resultado;
    PreparedStatement consultarDiagnosticosCita;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarDiagnosticosCita = conectar.prepareStatement("SELECT diagnostico.nombreDiagnostico, nivel, identificadorCita FROM diagnostico JOIN cita_registra_diagnostico ON cita_registra_diagnostico.nombreDiagnostico = diagnostico.nombreDiagnostico");
    resultado = consultarDiagnosticosCita.executeQuery();   
    
    while (resultado.next()){
      NivelDiagnostico nivel = NivelDiagnostico.valueOf(String.valueOf(resultado.getObject(2)));
      int identificadorCita=Integer.parseInt(String.valueOf(resultado.getObject(3)));
      String nombreDiagnostico=String.valueOf(resultado.getObject(1));
      buscarCita(identificadorCita).añadirDiagnostico(nombreDiagnostico, nivel);
    }
  } 
    
  /**
  * Método cargarPacientesCitas: Método que asigna un paciente a la cita correspondiente.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarPacientesCitas() throws SQLException, ParseException{
    ResultSet resultado;
    PreparedStatement consultarPacientesCitas;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarPacientesCitas = conectar.prepareStatement("SELECT * from citas_paciente");
    resultado = consultarPacientesCitas.executeQuery();   
    
    while (resultado.next()){
      int identificadorCita=Integer.parseInt(String.valueOf(resultado.getObject(1)));
      int identificadorPaciente=Integer.parseInt(String.valueOf(resultado.getObject(2)));
      buscarPaciente(identificadorPaciente).añadirCita(buscarCita(identificadorCita));
      buscarCita(identificadorCita).setPaciente(buscarPaciente(identificadorPaciente));
    }
  } 
  
  /**
  * Método cargarFuncionariosCitas: Método que asigna una cita al encargado de tipo Funcionario.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarFuncionariosCitas() throws SQLException, ParseException{
    ResultSet resultado;
    PreparedStatement consultarFuncionariosCitas;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarFuncionariosCitas = conectar.prepareStatement("SELECT * from funcionario_gestiona_cita");
    resultado = consultarFuncionariosCitas.executeQuery();   
    
    while (resultado.next()){
        int identificadorCita=Integer.parseInt(String.valueOf(resultado.getObject(1)));
        int identificadorFuncionario=Integer.parseInt(String.valueOf(resultado.getObject(2)));
        buscarFuncionario(identificadorFuncionario).añadirCita(buscarCita(identificadorCita));
        buscarCita(identificadorCita).setEncargadoCita(buscarFuncionario(identificadorFuncionario));
    }
  } 
    
  /**
  * Método cargarTratamientosDiagnostico: Método que asigna un tratamiento al diagnostico correspondiente.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarTratamientosDiagnostico() throws SQLException, ParseException{
     ResultSet resultado;
    PreparedStatement consultarTratamientosDiagnostico;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarTratamientosDiagnostico = conectar.prepareStatement("SELECT tratamiento.nombreTratamiento, tipo, dosis, nombreDiagnostico FROM tratamiento JOIN diagnostico_contiene_tratamiento ON tratamiento.nombreTratamiento = diagnostico_contiene_tratamiento.nombreTratamiento");
    resultado = consultarTratamientosDiagnostico.executeQuery();   
    
    while (resultado.next()){
        String nombreTratamiento = String.valueOf(resultado.getObject(1));
        String tipo = String.valueOf(resultado.getObject(2));
        int dosis = Integer.parseInt(String.valueOf(resultado.getObject(3)));
        String nombreDiagnostico = String.valueOf(resultado.getObject(4));
        buscarDiagnostico(nombreDiagnostico).añadirTratamiento(buscarTratamiento(nombreTratamiento));
        
  }
    } 
    
  /**
  * Método cargarObservacionesDiagnostico: Método que carga las observaciones y los asigna al diagnóstico correspondiente.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarObservacionesDiagnostico() throws SQLException, ParseException{
     ResultSet resultado;
    PreparedStatement consultarObservacionesDiagnostico;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarObservacionesDiagnostico = conectar.prepareStatement("SELECT * FROM diagnostico_observaciones");
    resultado = consultarObservacionesDiagnostico.executeQuery();   
    
    while (resultado.next()){
        String nombreDiagnostico = String.valueOf(resultado.getObject(1));
        String observacion = String.valueOf(resultado.getObject(2));
        buscarDiagnostico(nombreDiagnostico).añadirObservacion(observacion);       
    }
  } 
    
  /**
  * Método cargarFuncionarioCitas: Método que asigna las cita al encargado  respectivo de tipo Funcionario.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarFuncionarioCita()  throws SQLException, ParseException{
    ResultSet resultado;
    PreparedStatement consultarFuncionarioCita;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarFuncionarioCita = conectar.prepareStatement("SELECT * FROM funcionario_gestiona_cita");
    resultado = consultarFuncionarioCita.executeQuery();   
    
    while (resultado.next()){
      int identificadorCita = Integer.parseInt(String.valueOf(resultado.getObject(1)));
      int identificadorFuncionario = Integer.parseInt(String.valueOf(resultado.getObject(2)));
      buscarCita(identificadorCita).setEncargadoCita(buscarFuncionario(identificadorFuncionario));
      buscarFuncionario(identificadorFuncionario).añadirCita(buscarCita(identificadorCita));       
    }
  }
    
  /**
  * Método cargarVacunas: Método que carga la información de las vacunas de la base de datos y lo convierte en objetos de tipo Vacuna.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarVacunas() throws SQLException, ParseException{
    ResultSet resultado;
    PreparedStatement consultarVacuna;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarVacuna = conectar.prepareStatement("SELECT * FROM vacuna"); 
    resultado = consultarVacuna.executeQuery();    
    
    while (resultado.next()){
      SimpleDateFormat formatoFecha = new SimpleDateFormat("yyy-MM-dd");
      int numeroLote= (Integer.parseInt(String.valueOf(resultado.getObject(1))));
      String nombre = String.valueOf(resultado.getObject(2));
      String farmaceutica = String.valueOf(resultado.getObject(3));
      Date fechaAplicacion = formatoFecha.parse(String.valueOf(resultado.getObject(4)));
      Vacuna nuevaVacuna = new Vacuna(numeroLote, nombre, farmaceutica, fechaAplicacion);
      vacunas.add(nuevaVacuna);
    }
  }
  
  /**
  * Método cargarVacunasPaciente: Método que asigna las vacuna a cada paciente.
  * @throws SQLException: Se declara una excepción en caso de algún error en SQL
  * @throws ParseException: Se declara una excepción en caso de algún error en la conversión de los tipos de datos.
  */
  public void cargarVacunasPaciente()  throws SQLException, ParseException{
    ResultSet resultado;
    PreparedStatement consultarVacunasPaciente;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar(); 
    consultarVacunasPaciente = conectar.prepareStatement("SELECT * FROM paciente_tiene_vacuna");
    resultado = consultarVacunasPaciente.executeQuery();  
    
    while (resultado.next()){
      int numeroLote = Integer.parseInt(String.valueOf(resultado.getObject(1)));
      int identificadorPaciente = Integer.parseInt(String.valueOf(resultado.getObject(2)));  
      buscarPaciente(identificadorPaciente).añadirVacuna(buscarVacuna(numeroLote));
    }
    
   }
  
  /**
   * --------------------------------------------------------------------REPORTES---------------------------------------------------------------------------------------------
   * --------------------------------------------------------------------REPORTES---------------------------------------------------------------------------------------------
   * --------------------------------------------------------------------REPORTES---------------------------------------------------------------------------------------------
   * --------------------------------------------------------------------REPORTES---------------------------------------------------------------------------------------------
   * --------------------------------------------------------------------REPORTES---------------------------------------------------------------------------------------------
   * --------------------------------------------------------------------REPORTES---------------------------------------------------------------------------------------------
   * --------------------------------------------------------------------REPORTES---------------------------------------------------------------------------------------------
   * --------------------------------------------------------------------REPORTES---------------------------------------------------------------------------------------------
   */

  /**
  * Método primerMetodo: Método que busca las citas asociadas a un paciente por rango de fechas.
  * @return resultadoUno: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet primerMetodo(){
    ResultSet resultadoUno = null;   
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consultaUno = conectar.prepareStatement("SELECT cita.identificadorCita, fecha, hora, area, observacion FROM cita JOIN citas_paciente\n" +
          "ON cita.identificadorCita = citas_paciente.identificadorCita WHERE identificadorPaciente = "+pacienteActivo.getIdentificadorPaciente()+"\n" +
          "AND cita.fecha between ? AND ?");
      Date fecha1 = vista.ReportesPacienteFecha1.getDate();
      Date fecha2 = vista.ReportesPacienteFecha2.getDate();
      SimpleDateFormat formato= new SimpleDateFormat("yyy-mm-dd");
      String fecha1String= formato.format(fecha1);
      String fecha2String= formato.format(fecha2);
      Date fecha11=formato.parse(fecha1String);
      Date fecha22=formato.parse(fecha2String);

      java.sql.Date fecha11SQL = new java.sql.Date(fecha11.getTime());
      java.sql.Date fecha22SQL = new java.sql.Date(fecha22.getTime());

      consultaUno.setDate(1, fecha11SQL);
      consultaUno.setDate(2, fecha22SQL);
      resultadoUno = consultaUno.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultadoUno;
  }
  
  /**
  * Método SegundaMetodo: Método que busca las citas asociadas a un paciente por estado de la cita.
  * @return resultadoDos: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet SegundoMetodo(){
    ResultSet resultadoDos = null;   
    PreparedStatement consultaDos;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consultaDos = conectar.prepareStatement("SELECT cita.identificadorCita, fecha, hora, area, observacion, estado FROM cita JOIN citas_paciente\n" +
          "ON cita.identificadorCita = citas_paciente.identificadorCita WHERE identificadorPaciente = "+pacienteActivo.getIdentificadorPaciente()+"\n" +
          "AND cita.estado = ?");
      String area = vista.ReportesPacienteEstado1.getSelectedItem().toString();
      consultaDos.setString(1, area);
      resultadoDos = consultaDos.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultadoDos;
  }
  
  /**
  * Método TercerMetodo: Método que busca las citas asociadas a un paciente por la especialidad.
  * @return resultadoTres: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet TercerMetodo(){
    ResultSet resultadoTres = null;   
    PreparedStatement consultaTres;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consultaTres = conectar.prepareStatement("SELECT cita.identificadorCita, fecha, hora, area, observacion, estado FROM cita JOIN citas_paciente\n" +
          "ON cita.identificadorCita = citas_paciente.identificadorCita WHERE identificadorPaciente = "+pacienteActivo.getIdentificadorPaciente()+"\n" +
          "AND cita.area = ?");
      consultaTres.setString(1, vista.ReportesPacienteEspecialidad2.getText());
      resultadoTres = consultaTres.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultadoTres;
  }
  
  /**
  * Método PrimerReportePaciente: Método que exporta el reporte de las citas asociadas a un paciente al formato indicado por el usuario.
  */
  public void PrimerReportePaciente(){//Inicio PrimerReportePaciente

    if(vista.ReportesPacienteFormato.getSelectedItem().equals("CSV")){
      //REPORTE PARA FECHAS 
      if(vista.ReportesPacienteFecha1.getDate() != null && vista.ReportesPacienteFecha2.getDate() != null && vista.ReportesPacienteEspecialidad2.getText().equals("") && vista.ReportesPacienteEstado1.getSelectedItem().toString().equals("")){
          reportes.exportarCSV(primerMetodo());
          JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");
      }
      //REPORTE PARA ESPECIALIDAD
      else if(vista.ReportesPacienteFecha1.getDate() == null && vista.ReportesPacienteFecha2.getDate()  == null && vista.ReportesPacienteEspecialidad2.getText().length()!=0 && vista.ReportesPacienteEstado1.getSelectedItem().toString().equals("")){
          reportes.exportarCSV(TercerMetodo());
          JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }  
      //REPORTE PARA ESTADO
      else if(vista.ReportesPacienteFecha1.getDate() == null && vista.ReportesPacienteFecha2.getDate()  == null && vista.ReportesPacienteEspecialidad2.getText().equals("") && vista.ReportesPacienteEstado1.getSelectedItem().toString() != null){
          reportes.exportarCSV(SegundoMetodo());
          JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }      
    }
    
    else if(vista.ReportesPacienteFormato.getSelectedItem().equals("PDF")){
        //REPORTE PARA FECHAS 
      if(vista.ReportesPacienteFecha1.getDate() != null && vista.ReportesPacienteFecha2.getDate() != null && vista.ReportesPacienteEspecialidad2.getText().equals("") && vista.ReportesPacienteEstado1.getSelectedItem().toString().equals("")){
        reportes.exportarPDF(primerMetodo());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");
      }
      //REPORTE PARA ESPECIALIDAD
      else if(vista.ReportesPacienteFecha1.getDate() == null && vista.ReportesPacienteFecha2.getDate()  == null && vista.ReportesPacienteEspecialidad2.getText().length()!=0 && vista.ReportesPacienteEstado1.getSelectedItem().toString().equals("")){
        reportes.exportarPDF(TercerMetodo());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");  
      }  
      //REPORTE PARA ESTADO
      else if(vista.ReportesPacienteFecha1.getDate() == null && vista.ReportesPacienteFecha2.getDate()  == null && vista.ReportesPacienteEspecialidad2.getText().equals("") && vista.ReportesPacienteEstado1.getSelectedItem().toString() != null){
        reportes.exportarPDF(SegundoMetodo());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");   
      }      
    }
    
    else if(vista.ReportesPacienteFormato.getSelectedItem().equals("HTML")){
       //REPORTE PARA FECHAS 
      if(vista.ReportesPacienteFecha1.getDate() != null && vista.ReportesPacienteFecha2.getDate() != null && vista.ReportesPacienteEspecialidad2.getText().equals("") && vista.ReportesPacienteEstado1.getSelectedItem().toString().equals("")){
        reportes.exportarHTML(primerMetodo());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");
      }
      //REPORTE PARA ESPECIALIDAD
      else if(vista.ReportesPacienteFecha1.getDate() == null && vista.ReportesPacienteFecha2.getDate()  == null && vista.ReportesPacienteEspecialidad2.getText().length()!=0 && vista.ReportesPacienteEstado1.getSelectedItem().toString().equals("")){
                reportes.exportarHTML(TercerMetodo());
          JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");  
      }  
      //REPORTE PARA ESTADO
      else if(vista.ReportesPacienteFecha1.getDate() == null && vista.ReportesPacienteFecha2.getDate()  == null && vista.ReportesPacienteEspecialidad2.getText().equals("") && vista.ReportesPacienteEstado1.getSelectedItem().toString() != null){
           reportes.exportarHTML(SegundoMetodo());
          JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");   
      }      
    }
  }//Fin PrimerReportePaciente
  
  /**
  * Método ConsultaCuatro: Método que busca los diagnósticos asociados a un paciente por rango de fechas.
  * @return resultadoCuatro: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ConsultaCuatro(){
    ResultSet resultadoCuatro = null;   
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consultaUno = conectar.prepareStatement("SELECT diagnostico.nombreDiagnostico, nivel, diagnostico_observaciones.observacion FROM diagnostico JOIN\n" +
        "diagnostico_observaciones ON diagnostico.nombreDiagnostico = diagnostico_observaciones.nombreDiagnostico\n" +
        "JOIN cita_registra_diagnostico ON diagnostico.nombreDiagnostico = cita_registra_diagnostico.nombreDiagnostico\n" +
        "JOIN cita ON cita_registra_diagnostico.identificadorCita = cita.identificadorCita JOIN\n" +
        "bitacora_cita ON cita.identificadorCita = bitacora_cita.identificadorCita JOIN\n" +
        "bitacora_fechamodificacion ON bitacora_cita.identificadorBitacora = bitacora_fechamodificacion.identificadorBitacora\n" +
        "JOIN citas_paciente ON citas_paciente.identificadorCita = cita.identificadorCita\n" +
        "WHERE identificadorPaciente =  ‘id_paciente’AND (SELECT fechaModificacion FROM bitacora_fechamodificacion JOIn bitacora_cita ON\n" +
        "bitacora_fechamodificacion.identificadorBitacora = bitacora_cita.identificadorBitacora\n" +
        "JOIN bitacora_cita_estado ON bitacora_cita.identificadorCita = bitacora_cita_estado.identificadorCita\n" +
        "WHERE estado = 'Realizada') between ? AND ?");
      Date fecha1 = vista.ReportesPacienteFecha3.getDate();
      Date fecha2 = vista.ReportesPacienteFecha4.getDate();
      SimpleDateFormat formato= new SimpleDateFormat("yyy-mm-dd");
      String fecha1String= formato.format(fecha1);
      String fecha2String= formato.format(fecha2);
      Date fecha11=formato.parse(fecha1String);
      Date fecha22=formato.parse(fecha2String);

      java.sql.Date fecha11SQL = new java.sql.Date(fecha11.getTime());
      java.sql.Date fecha22SQL = new java.sql.Date(fecha22.getTime());

      consultaUno.setDate(1, fecha11SQL);
      consultaUno.setDate(2, fecha22SQL);
      resultadoCuatro = consultaUno.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultadoCuatro;      
  }
  
  /**
  * Método ConsultaCinco: Método que busca los diagnósticos asociados a un paciente por el nivel del diagnóstico.
  * @return resultadoCinco: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ConsultaCinco(){
    ResultSet resultadoCinco = null;   
    PreparedStatement consultaDos;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consultaDos = conectar.prepareStatement("SELECT DISTINCT diagnostico.nombreDiagnostico, nivel, diagnostico_observaciones.observacion FROM diagnostico JOIN\n" +
        "diagnostico_observaciones ON diagnostico.nombreDiagnostico = diagnostico_observaciones.nombreDiagnostico\n" +
        "JOIN cita_registra_diagnostico ON diagnostico.nombreDiagnostico = cita_registra_diagnostico.nombreDiagnostico\n" +
        "JOIN cita ON cita_registra_diagnostico.identificadorCita = cita.identificadorCita JOIN\n" +
        "citas_paciente ON cita.identificadorCita = citas_paciente.identificadorCita\n" +
        " WHERE citas_paciente.identificadorPaciente = "+pacienteActivo.getIdentificadorPaciente()+" AND nivel = ?");
      String nivel = vista.ReportesPacienteDiagnostico1.getSelectedItem().toString();
      consultaDos.setString(1, nivel);
      resultadoCinco = consultaDos.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultadoCinco;    
  }
  
  /**
  * Método ConsultaSeis: Método que busca los diagnósticos asociados a un paciente por el nombre del diagnóstico.
  * @return resultadoSeis: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ConsultaSeis(){
    ResultSet resultadoSeis = null;   
    PreparedStatement consultaDos;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consultaDos = conectar.prepareStatement("SELECT DISTINCT diagnostico.nombreDiagnostico, nivel, diagnostico_observaciones.observacion FROM diagnostico JOIN\n" +
        "diagnostico_observaciones ON diagnostico.nombreDiagnostico = diagnostico_observaciones.nombreDiagnostico\n" +
        "JOIN cita_registra_diagnostico ON diagnostico.nombreDiagnostico = cita_registra_diagnostico.nombreDiagnostico\n" +
        "JOIN cita ON cita_registra_diagnostico.identificadorCita = cita.identificadorCita JOIN\n" +
        "citas_paciente ON cita.identificadorCita = citas_paciente.identificadorCita\n" +
        " WHERE citas_paciente.identificadorPaciente = "+pacienteActivo.getIdentificadorPaciente()+" AND diagnostico.nombreDiagnostico\n" +
        " = ?");
      String nombreDiagnostico = vista.ReportesPacienteNombreDiagnostico1.getSelectedItem().toString();
      consultaDos.setString(1, nombreDiagnostico);
      resultadoSeis = consultaDos.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultadoSeis;    
  }
  
  /**
  * Método SegundoReportePaciente: Método que exporta el reporte de los diagnósticos asociados a un paciente al formato indicado por el usuario.
  */
  public void SegundoReportePaciente(){
   
    if(vista.ReportesPacienteFormato.getSelectedItem().equals("CSV")){
      //REPORTE PARA FECHAS 
      if(vista.ReportesPacienteFecha3.getDate() != null && vista.ReportesPacienteFecha4.getDate() != null && vista.ReportesPacienteDiagnostico1.getSelectedItem().toString().equals("") && vista.ReportesPacienteNombreDiagnostico1.getSelectedItem().toString().equals("")){
        reportes.exportarCSV(ConsultaCuatro());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");
      }
      //REPORTE PARA NIVEL DIAGNÓSTICO
      else if(vista.ReportesPacienteFecha3.getDate() == null && vista.ReportesPacienteFecha4.getDate()  == null && vista.ReportesPacienteDiagnostico1.getSelectedItem().toString() != null && vista.ReportesPacienteNombreDiagnostico1.getSelectedItem().toString().equals("")){
        reportes.exportarCSV(ConsultaCinco());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }  
      //REPORTE PARA NOMBRE DIAGNÓSTICO
      else if(vista.ReportesPacienteFecha3.getDate() == null && vista.ReportesPacienteFecha4.getDate()  == null && vista.ReportesPacienteDiagnostico1.getSelectedItem().toString().equals("") && vista.ReportesPacienteNombreDiagnostico1.getSelectedItem().toString()!= null){
        reportes.exportarCSV(ConsultaSeis());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }      
    }
    
    else if(vista.ReportesPacienteFormato.getSelectedItem().equals("PDF")){
      //REPORTE PARA FECHAS 
      if(vista.ReportesPacienteFecha3.getDate() != null && vista.ReportesPacienteFecha4.getDate() != null && vista.ReportesPacienteDiagnostico1.getSelectedItem().toString().equals("") && vista.ReportesPacienteNombreDiagnostico1.getSelectedItem().toString().equals("")){
        reportes.exportarPDF(ConsultaCuatro());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");
      }
      //REPORTE PARA NIVEL DIAGNÓSTICO
      else if(vista.ReportesPacienteFecha3.getDate() == null && vista.ReportesPacienteFecha4.getDate()  == null && vista.ReportesPacienteDiagnostico1.getSelectedItem().toString() != null && vista.ReportesPacienteNombreDiagnostico1.getSelectedItem().toString().equals("")){
        reportes.exportarPDF(ConsultaCinco());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }  
      //REPORTE PARA NOMBRE DIAGNÓSTICO
      else if(vista.ReportesPacienteFecha3.getDate() == null && vista.ReportesPacienteFecha4.getDate()  == null && vista.ReportesPacienteDiagnostico1.getSelectedItem().toString().equals("") && vista.ReportesPacienteNombreDiagnostico1.getSelectedItem().toString()!= null){
        reportes.exportarPDF(ConsultaSeis());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }      
    }
        
    else if(vista.ReportesPacienteFormato.getSelectedItem().equals("HTML")){
      //REPORTE PARA FECHAS 
      if(vista.ReportesPacienteFecha3.getDate() != null && vista.ReportesPacienteFecha4.getDate() != null && vista.ReportesPacienteDiagnostico1.getSelectedItem().toString().equals("") && vista.ReportesPacienteNombreDiagnostico1.getSelectedItem().toString().equals("")){
        reportes.exportarHTML(ConsultaCuatro());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");
      }
      //REPORTE PARA NIVEL DIAGNÓSTICO
      else if(vista.ReportesPacienteFecha3.getDate() == null && vista.ReportesPacienteFecha4.getDate()  == null && vista.ReportesPacienteDiagnostico1.getSelectedItem().toString() != null && vista.ReportesPacienteNombreDiagnostico1.getSelectedItem().toString().equals("")){
        reportes.exportarHTML(ConsultaCinco());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }  
      //REPORTE PARA NOMBRE DIAGNÓSTICO
      else if(vista.ReportesPacienteFecha3.getDate() == null && vista.ReportesPacienteFecha4.getDate()  == null && vista.ReportesPacienteDiagnostico1.getSelectedItem().toString().equals("") && vista.ReportesPacienteNombreDiagnostico1.getSelectedItem().toString()!= null){
        reportes.exportarHTML(ConsultaSeis());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }      
    }
  }
  
  /**
  * Método ConsultaSiete: Método que busca los tratamientos asociados a un paciente por el rango de fechas.
  * @return resultadoSiete: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ConsultaSiete(){
    ResultSet resultadoSiete = null;   
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consultaUno = conectar.prepareStatement("SELECT tratamiento.nombreTratamiento, tipo, dosis FROM tratamiento JOIN\n" +
        "diagnostico_contiene_tratamiento ON tratamiento.nombreTratamiento = diagnostico_contiene_tratamiento.nombreTratamiento\n" +
        "JOIN diagnostico ON diagnostico_contiene_tratamiento.nombreDiagnostico = diagnostico.nombreDiagnostico \n" +
        "JOIN cita_registra_diagnostico ON diagnostico.nombreDiagnostico = cita_registra_diagnostico.nombreDiagnostico\n" +
        "JOIN cita ON cita_registra_diagnostico.identificadorCita = cita.identificadorCita JOIN\n" +
        "bitacora_cita ON cita.identificadorCita = bitacora_cita.identificadorCita JOIN\n" +
        "bitacora_fechamodificacion ON bitacora_cita.identificadorBitacora = bitacora_fechamodificacion.identificadorBitacora\n" +
        "JOIN citas_paciente ON citas_paciente.identificadorCita = cita.identificadorCita\n" +
        "WHERE identificadorPaciente = ‘id_paciente’ AND (SELECT fechaModificacion FROM bitacora_fechamodificacion JOIn bitacora_cita ON\n" +
        "bitacora_fechamodificacion.identificadorBitacora = bitacora_cita.identificadorBitacora\n" +
        "JOIN bitacora_cita_estado ON bitacora_cita.identificadorCita = bitacora_cita_estado.identificadorCita\n" +
        "WHERE estado = 'Realizada') between ? AND ?");
      Date fecha1 = vista.ReportesPacienteFecha1.getDate();
      Date fecha2 = vista.ReportesPacienteFecha2.getDate();
      SimpleDateFormat formato= new SimpleDateFormat("yyy-mm-dd");
      String fecha1String= formato.format(fecha1);
      String fecha2String= formato.format(fecha2);
      Date fecha11=formato.parse(fecha1String);
      Date fecha22=formato.parse(fecha2String);

      java.sql.Date fecha11SQL = new java.sql.Date(fecha11.getTime());
      java.sql.Date fecha22SQL = new java.sql.Date(fecha22.getTime());

      consultaUno.setDate(1, fecha11SQL);
      consultaUno.setDate(2, fecha22SQL);
      resultadoSiete = consultaUno.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultadoSiete;      
  }
  
  /**
  * Método ConsultaOcho: Método que busca los tratamientos asociados a un paciente por el tipo del tratamiento.
  * @return resultadoOcho: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ConsultaOcho(){
    ResultSet resultadoOcho = null;   
    PreparedStatement consultaDos;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consultaDos = conectar.prepareStatement("SELECT tratamiento.nombreTratamiento, tipo, dosis FROM tratamiento JOIN\n" +
            "diagnostico_contiene_tratamiento ON tratamiento.nombreTratamiento = diagnostico_contiene_tratamiento.nombreTratamiento\n" +
            "JOIN diagnostico ON diagnostico_contiene_tratamiento.nombreDiagnostico = diagnostico.nombreDiagnostico \n" +
            "JOIN cita_registra_diagnostico ON diagnostico.nombreDiagnostico = cita_registra_diagnostico.nombreDiagnostico\n" +
            "JOIN cita ON cita_registra_diagnostico.identificadorCita = cita.identificadorCita JOIN\n" +
            "citas_paciente ON citas_paciente.identificadorCita = cita.identificadorCita WHERE\n" +
            "identificadorPaciente ="+pacienteActivo.getIdentificadorPaciente()+" AND tipo = ?");
      String tipoTratamiento = vista.ReportesPacienteTipoTratamiento.getText();
      consultaDos.setString(1, tipoTratamiento);
      resultadoOcho = consultaDos.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultadoOcho;       
  }
  
  /**
  * Método ConsultaNueve: Método que busca los tratamientos asociados a un paciente por el nombre del tratamiento.
  * @return resultadoNueve: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ConsultaNueve(){
    ResultSet resultadoNueve = null;   
    PreparedStatement consultaDos;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consultaDos = conectar.prepareStatement("SELECT tratamiento.nombreTratamiento, tipo, dosis FROM tratamiento JOIN\n" +
            "diagnostico_contiene_tratamiento ON tratamiento.nombreTratamiento = diagnostico_contiene_tratamiento.nombreTratamiento\n" +
            "JOIN diagnostico ON diagnostico_contiene_tratamiento.nombreDiagnostico = diagnostico.nombreDiagnostico \n" +
            "JOIN cita_registra_diagnostico ON diagnostico.nombreDiagnostico = cita_registra_diagnostico.nombreDiagnostico\n" +
            "JOIN cita ON cita_registra_diagnostico.identificadorCita = cita.identificadorCita JOIN\n" +
            "citas_paciente ON citas_paciente.identificadorCita = cita.identificadorCita WHERE\n" +
            "identificadorPaciente = "+pacienteActivo.getIdentificadorPaciente()+" AND tratamiento.nombreTratamiento = ?");
      String nombreTratamiento = vista.ReportesPacienteNombreTratamiento.getSelectedItem().toString();
      consultaDos.setString(1, nombreTratamiento);
      resultadoNueve = consultaDos.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultadoNueve;       
  }
  
  /**
  * Método TercerReportePaciente: Método que exporta el reporte de los tratamientos asociados a un paciente al formato indicado por el usuario.
  */
  public void TercerReportePaciente(){
    
    if(vista.ReportesPacienteFormato.getSelectedItem().equals("CSV")){
      //REPORTE PARA FECHAS 
      if(vista.ReportesPacienteFecha5.getDate() != null && vista.ReportesPacienteFecha6.getDate() != null && vista.ReportesPacienteTipoTratamiento.getText().equals("") && vista.ReportesPacienteNombreTratamiento.getSelectedItem().toString().equals("")){
        reportes.exportarCSV(ConsultaSiete());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");
      }
      //REPORTE PARA NIVEL DIAGNÓSTICO
      else if(vista.ReportesPacienteFecha5.getDate() == null && vista.ReportesPacienteFecha6.getDate()  == null && vista.ReportesPacienteTipoTratamiento.getText().length()!=0 && vista.ReportesPacienteNombreTratamiento.getSelectedItem().toString().equals("")){
        reportes.exportarCSV(ConsultaOcho());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }  
      //REPORTE PARA NOMBRE DIAGNÓSTICO
      else if(vista.ReportesPacienteFecha5.getDate() == null && vista.ReportesPacienteFecha6.getDate()  == null && vista.ReportesPacienteTipoTratamiento.getText().equals("") && vista.ReportesPacienteNombreTratamiento.getSelectedItem().toString()!= null){
        reportes.exportarCSV(ConsultaNueve());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }      
    }   
    
    else if(vista.ReportesPacienteFormato.getSelectedItem().equals("PDF")){
      //REPORTE PARA FECHAS 
      if(vista.ReportesPacienteFecha5.getDate() != null && vista.ReportesPacienteFecha6.getDate() != null && vista.ReportesPacienteTipoTratamiento.getText().equals("") && vista.ReportesPacienteNombreTratamiento.getSelectedItem().toString().equals("")){
        reportes.exportarPDF(ConsultaSiete());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");
      }
      //REPORTE PARA NIVEL DIAGNÓSTICO
      else if(vista.ReportesPacienteFecha5.getDate() == null && vista.ReportesPacienteFecha6.getDate()  == null && vista.ReportesPacienteTipoTratamiento.getText().length()!=0 && vista.ReportesPacienteNombreTratamiento.getSelectedItem().toString().equals("")){
        reportes.exportarPDF(ConsultaOcho());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }  
      //REPORTE PARA NOMBRE DIAGNÓSTICO
      else if(vista.ReportesPacienteFecha5.getDate() == null && vista.ReportesPacienteFecha6.getDate()  == null && vista.ReportesPacienteTipoTratamiento.getText().equals("") && vista.ReportesPacienteNombreTratamiento.getSelectedItem().toString()!= null){
        reportes.exportarPDF(ConsultaNueve());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }      
    }   
    
    else if(vista.ReportesPacienteFormato.getSelectedItem().equals("HTML")){
      //REPORTE PARA FECHAS 
      if(vista.ReportesPacienteFecha5.getDate() != null && vista.ReportesPacienteFecha6.getDate() != null && vista.ReportesPacienteTipoTratamiento.getText().equals("") && vista.ReportesPacienteNombreTratamiento.getSelectedItem().toString().equals("")){
        reportes.exportarHTML(ConsultaSiete());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");
      }
      //REPORTE PARA NIVEL DIAGNÓSTICO
      else if(vista.ReportesPacienteFecha5.getDate() == null && vista.ReportesPacienteFecha6.getDate()  == null && vista.ReportesPacienteTipoTratamiento.getText().length()!=0 && vista.ReportesPacienteNombreTratamiento.getSelectedItem().toString().equals("")){
        reportes.exportarHTML(ConsultaOcho());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }  
      //REPORTE PARA NOMBRE DIAGNÓSTICO
      else if(vista.ReportesPacienteFecha5.getDate() == null && vista.ReportesPacienteFecha6.getDate()  == null && vista.ReportesPacienteTipoTratamiento.getText().equals("") && vista.ReportesPacienteNombreTratamiento.getSelectedItem().toString()!= null){
        reportes.exportarHTML(ConsultaNueve());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }      
    }   
    
  }
  
  /**
  * Método ConsultaDiez: Método que busca las hospitalizaciones registradas por un paciente.
  * @return resultadoDiez: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ConsultaDiez(){
    ResultSet resultadoDiez = null;   
    PreparedStatement consultaDos;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consultaDos = conectar.prepareStatement("SELECT hospitalizacion.identificadorHospitalizacion, hospitalizacion.fechaInicio, hospitalizacion.fechaFin,\n" +
            "centroatencion.nombre, diagnostico.nombreDiagnostico, cita.area, \n" +
            "funcionario.identificadorFuncionario, fechaSeguimiento, seguimiento_observacion.observacion, \n" +
            "nombreTratamiento FROM hospitalizacion JOIN centroatencion_recibe_hospitalizacion\n" +
            "ON centroatencion_recibe_hospitalizacion.identificadorHospitalizacion = hospitalizacion.identificadorHospitalizacion\n" +
            "JOIN centroatencion ON centroatencion_recibe_hospitalizacion.codigoCentro = centroatencion.codigoCentro\n" +
            "JOIN cita_hospitalizacion ON hospitalizacion.identificadorHospitalizacion =\n" +
            "cita_hospitalizacion.identificadorHospitalizacion JOIN cita ON cita_hospitalizacion.identificadorCita\n" +
            "= cita.identificadorCita JOIN cita_registra_diagnostico ON cita.identificadorCita =\n" +
            "cita_registra_diagnostico.identificadorCita JOIN diagnostico ON cita_registra_diagnostico.nombreDiagnostico\n" +
            "= diagnostico.nombreDiagnostico JOIN funcionario_gestiona_cita ON cita.identificadorCita =\n" +
            "funcionario_gestiona_cita.identificadorCita JOIN funcionario ON funcionario_gestiona_cita.identificadorFuncionario =\n" +
            "funcionario.identificadorFuncionario JOIN hospitalizacion_necesita_seguimiento ON\n" +
            "hospitalizacion.identificadorHospitalizacion = hospitalizacion_necesita_seguimiento.identificadorHospitalizacion\n" +
            "JOIN seguimiento ON hospitalizacion_necesita_seguimiento.identificadorSeguimiento =\n" +
            "seguimiento.identificadorSeguimiento JOIN seguimiento_fecha ON seguimiento.identificadorSeguimiento =\n" +
            "seguimiento_fecha.identificadorSeguimiento JOIN seguimiento_observacion ON seguimiento.identificadorSeguimiento =\n" +
            "seguimiento_observacion.identificadorSeguimiento JOIN seguimiento_requiere_tratamiento ON\n" +
            "seguimiento.identificadorSeguimiento = seguimiento_requiere_tratamiento.identificadorSeguimiento JOIN\n" +
            "citas_paciente ON cita.identificadorCita = citas_paciente.identificadorCita WHERE\n" +
            "identificadorPaciente = "+pacienteActivo.getIdentificadorPaciente()+"");
      resultadoDiez = consultaDos.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultadoDiez;
  }
  
  /**
  * Método CuartoReportePaciente: Método que exporta el reporte de las hospitalizaciones registradas por un paciente al formato indicado por el usuario.
  */
  public void CuartoReportePaciente(){
    if(vista.ReportesPacienteFormato.getSelectedItem().equals("CSV")){
      reportes.exportarCSV(ConsultaDiez());
      JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");    
    }  
    else if(vista.ReportesPacienteFormato.getSelectedItem().equals("PDF")){
      reportes.exportarPDF(ConsultaDiez());
      JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");    
    }  
    else if(vista.ReportesPacienteFormato.getSelectedItem().equals("HTML")){
      reportes.exportarHTML(ConsultaDiez());
      JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");    
    }  
  }
  
  //REPORTES DOCTOR Y ENFERMERO
  
  /**
  * Método ReporteDoctorUno: Método que busca las citas registradas en el sistema por el rango de fechas.
  * @return resultadoUno: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorUno(){
    ResultSet resultadoUno = null;   
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
        
      Date fecha1 = vista.RDD1.getDate();
      Date fecha2 = vista.RDD2.getDate();
      SimpleDateFormat formato= new SimpleDateFormat("yyy-mm-dd");
      String fecha1String= formato.format(fecha1);
      String fecha2String= formato.format(fecha2);
      Date fecha11=formato.parse(fecha1String);
      Date fecha22=formato.parse(fecha2String);

      java.sql.Date fecha11SQL = new java.sql.Date(fecha11.getTime());
      java.sql.Date fecha22SQL = new java.sql.Date(fecha22.getTime());
      
      consultaUno = conectar.prepareStatement("SELECT cita.identificadorCita, fecha, hora, area, observacion, estado, identificadorPaciente FROM cita JOIN citas_paciente\n" +
        "ON cita.identificadorCita = citas_paciente.identificadorCita WHERE\n" +
        "cita.fecha between ? AND ?");
      consultaUno.setDate(1, fecha11SQL);
      consultaUno.setDate(2, fecha22SQL);
      resultadoUno = consultaUno.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultadoUno;
  }
 
  /**
  * Método ReporteDoctorDos: Método que busca las citas registradas en el sistema por el estado de la cita.
  * @return resultadoDos: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorDos(){
    ResultSet resultadoDos = null;   
    PreparedStatement consultaDos;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consultaDos = conectar.prepareStatement("SELECT cita.identificadorCita, fecha, hora, area, observacion, estado, identificadorPaciente FROM cita JOIN citas_paciente\n" +
        "ON cita.identificadorCita = citas_paciente.identificadorCita WHERE\n" +
        "cita.estado = ?");
      String estadoCita = vista.RDC1.getSelectedItem().toString();
      consultaDos.setString(1, estadoCita);
      resultadoDos = consultaDos.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultadoDos;       
  }
  
  /**
  * Método ReporteDoctorTres: Método que busca las citas registradas en el sistema por la especialidad.
  * @return resultadoTres: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorTres(){
    ResultSet resultadoTres = null;   
    PreparedStatement consultaTres;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consultaTres = conectar.prepareStatement("SELECT cita.identificadorCita, fecha, hora, area, observacion, estado, identificadorPaciente FROM cita JOIN citas_paciente\n" +
        "ON cita.identificadorCita = citas_paciente.identificadorCita WHERE\n" +
        "cita.area = ?");
      String especialidad = vista.RDT1.getText();
      consultaTres.setString(1, especialidad);
      resultadoTres = consultaTres.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultadoTres;       
  }
 
  /**
  * Método ReporteDoctorCuatro: Método que busca las citas registradas en el sistema por el nombre del paciente.
  * @return resultadoCuatro: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorCuatro(){
    ResultSet resultadoCuatro = null;   
    PreparedStatement consultaCuatro;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consultaCuatro = conectar.prepareStatement("SELECT cita.identificadorCita, fecha, hora, area, observacion, estado, nombre, apellido1, apellido2 FROM cita JOIN citas_paciente\n" +
        "ON cita.identificadorCita = citas_paciente.identificadorCita JOIN paciente ON\n" +
        "citas_paciente.identificadorPaciente = paciente.identificadorPaciente JOIN paciente_usuario\n" +
        "ON paciente.identificadorPaciente = paciente_usuario.identificadorPaciente\n" +
        "JOIN usuario ON paciente_usuario.cedula = usuario.cedula WHERE nombre = ? AND\n" +
        "apellido1 = ? AND apellido2 = ?");
      String nombre = vista.RDT2.getText();
      String apellidoUno = vista.RDT3.getText();
      String apellidoDos = vista.RDT4.getText();
      consultaCuatro.setString(1, nombre);
      consultaCuatro.setString(2, apellidoUno);
      consultaCuatro.setString(3, apellidoDos);
      resultadoCuatro = consultaCuatro.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultadoCuatro;       
  }
  
  /**
  * Método PrimerReporteDoctor: Método que exporta el reporte de las citas registradas en el sistema al formato indicado por el usuario.
  */
  public void PrimerReporteDoctor(){
    
    if(vista.ReportesPacienteFormato1.getSelectedItem().equals("CSV")){
      //REPORTE PARA FECHAS 
      if(vista.RDD1.getDate() != null && vista.RDD2.getDate() != null &&
        vista.RDC1.getSelectedItem().toString().equals("") &&
        vista.RDT1.getText().equals("") && vista.RDT2.getText().equals("") && vista.RDT3.getText().equals("") &&vista.RDT4.getText().equals("")){         
        reportes.exportarCSV(ReporteDoctorUno());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");
      }
      //REPORTE PARA ESTADO
      else if(vista.RDD1.getDate() == null && vista.RDD2.getDate() == null &&
        vista.RDC1.getSelectedItem().toString() != null &&
        vista.RDT1.getText().equals("") && vista.RDT2.getText().equals("") && vista.RDT3.getText().equals("") &&vista.RDT4.getText().equals("")){
        reportes.exportarCSV(ReporteDoctorDos());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }  
      //REPORTE PARA ESPECIALIDAD
      else if(vista.RDD1.getDate() == null && vista.RDD2.getDate() == null &&
        vista.RDC1.getSelectedItem().toString().equals("") &&
        vista.RDT1.getText().length()!=0 && vista.RDT2.getText().equals("") && vista.RDT3.getText().equals("") &&vista.RDT4.getText().equals("")){
        reportes.exportarCSV(ReporteDoctorTres());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }    
      //REPORTE POR NOMBRE PACIENTE
      else if(vista.RDD1.getDate() == null && vista.RDD2.getDate() == null &&
        vista.RDC1.getSelectedItem().toString().equals("") &&
        vista.RDT1.getText().equals("") && vista.RDT2.getText().length()!=0 && vista.RDT3.getText().length()!=0 &&vista.RDT4.getText().length()!=0){
        reportes.exportarCSV(ReporteDoctorCuatro());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }  
    }
    else if(vista.ReportesPacienteFormato1.getSelectedItem().equals("PDF")){
           //REPORTE PARA FECHAS 
      if(vista.RDD1.getDate() != null && vista.RDD2.getDate() != null &&
        vista.RDC1.getSelectedItem().toString().equals("") &&
        vista.RDT1.getText().equals("") && vista.RDT2.getText().equals("") && vista.RDT3.getText().equals("") &&vista.RDT4.getText().equals("")){         
        reportes.exportarPDF(ReporteDoctorUno());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");
      }
      //REPORTE PARA ESTADO
      else if(vista.RDD1.getDate() == null && vista.RDD2.getDate() == null &&
        vista.RDC1.getSelectedItem().toString() != null &&
        vista.RDT1.getText().equals("") && vista.RDT2.getText().equals("") && vista.RDT3.getText().equals("") &&vista.RDT4.getText().equals("")){
        reportes.exportarPDF(ReporteDoctorDos());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }  
      //REPORTE PARA ESPECIALIDAD
      else if(vista.RDD1.getDate() == null && vista.RDD2.getDate() == null &&
        vista.RDC1.getSelectedItem().toString().equals("") &&
        vista.RDT1.getText().length()!=0 && vista.RDT2.getText().equals("") && vista.RDT3.getText().equals("") &&vista.RDT4.getText().equals("")){
        reportes.exportarPDF(ReporteDoctorTres());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }    
      //REPORTE POR NOMBRE PACIENTE
      else if(vista.RDD1.getDate() == null && vista.RDD2.getDate() == null &&
        vista.RDC1.getSelectedItem().toString().equals("") &&
        vista.RDT1.getText().equals("") && vista.RDT2.getText().length()!=0 && vista.RDT3.getText().length()!=0 &&vista.RDT4.getText().length()!=0){
        reportes.exportarPDF(ReporteDoctorCuatro());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }      
    }
    
    else if(vista.ReportesPacienteFormato1.getSelectedItem().equals("HTML")){
           //REPORTE PARA FECHAS 
      if(vista.RDD1.getDate() != null && vista.RDD2.getDate() != null &&
        vista.RDC1.getSelectedItem().toString().equals("") &&
        vista.RDT1.getText().equals("") && vista.RDT2.getText().equals("") && vista.RDT3.getText().equals("") &&vista.RDT4.getText().equals("")){          
        reportes.exportarHTML(ReporteDoctorUno());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");
      }
      //REPORTE PARA ESTADO
      else if(vista.RDD1.getDate() == null && vista.RDD2.getDate() == null &&
        vista.RDC1.getSelectedItem().toString() != null &&
        vista.RDT1.getText().equals("") && vista.RDT2.getText().equals("") && vista.RDT3.getText().equals("") &&vista.RDT4.getText().equals("")){
        reportes.exportarHTML(ReporteDoctorDos());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }  
      //REPORTE PARA ESPECIALIDAD
      else if(vista.RDD1.getDate() == null && vista.RDD2.getDate() == null &&
        vista.RDC1.getSelectedItem().toString().equals("") &&
        vista.RDT1.getText().length()!=0 && vista.RDT2.getText().equals("") && vista.RDT3.getText().equals("") &&vista.RDT4.getText().equals("")){
        reportes.exportarHTML(ReporteDoctorTres());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }    
      //REPORTE POR NOMBRE PACIENTE
      else if(vista.RDD1.getDate() == null && vista.RDD2.getDate() == null &&
        vista.RDC1.getSelectedItem().toString().equals("") &&
        vista.RDT1.getText().equals("") && vista.RDT2.getText().length()!=0 && vista.RDT3.getText().length()!=0 &&vista.RDT4.getText().length()!=0){
        reportes.exportarHTML(ReporteDoctorCuatro());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }      
    }
  }
    
  /**
  * Método ReporteDoctorCinco: Método que busca los diagnósticos asociados a un paciente por el rango de fechas.
  * @return resultadoUno: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorCinco(){
    ResultSet resultadoUno = null;   
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
        
      Date fecha1 = vista.RDD3.getDate();
      Date fecha2 = vista.RDD4.getDate();
      SimpleDateFormat formato= new SimpleDateFormat("yyy-mm-dd");
      String fecha1String= formato.format(fecha1);
      String fecha2String= formato.format(fecha2);
      Date fecha11=formato.parse(fecha1String);
      Date fecha22=formato.parse(fecha2String);

      java.sql.Date fecha11SQL = new java.sql.Date(fecha11.getTime());
      java.sql.Date fecha22SQL = new java.sql.Date(fecha22.getTime());
      
      String idPaciente = vista.RDC9.getSelectedItem().toString();
      
      consultaUno = conectar.prepareStatement("SELECT diagnostico.nombreDiagnostico, nivel, diagnostico_observaciones.observacion FROM diagnostico JOIN\n" +
            "diagnostico_observaciones ON diagnostico.nombreDiagnostico = diagnostico_observaciones.nombreDiagnostico\n" +
            "JOIN cita_registra_diagnostico ON diagnostico.nombreDiagnostico = cita_registra_diagnostico.nombreDiagnostico\n" +
            "JOIN cita ON cita_registra_diagnostico.identificadorCita = cita.identificadorCita JOIN\n" +
            "bitacora_cita ON cita.identificadorCita = bitacora_cita.identificadorCita JOIN\n" +
            "bitacora_fechamodificacion ON bitacora_cita.identificadorBitacora = bitacora_fechamodificacion.identificadorBitacora\n" +
            "JOIN citas_paciente ON citas_paciente.identificadorCita = cita.identificadorCita\n" +
            "WHERE identificadorPaciente =  "+ idPaciente+"AND (SELECT fechaModificacion FROM bitacora_fechamodificacion JOIn bitacora_cita ON\n" +
            "bitacora_fechamodificacion.identificadorBitacora = bitacora_cita.identificadorBitacora\n" +
            "JOIN bitacora_cita_estado ON bitacora_cita.identificadorCita = bitacora_cita_estado.identificadorCita\n" +
            "WHERE estado = 'REALIZADA') between ? AND ?");
      consultaUno.setDate(1, fecha11SQL);
      consultaUno.setDate(2, fecha22SQL);
      resultadoUno = consultaUno.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultadoUno;
  }
  
  /**
  * Método ReporteDoctorSeis: Método que busca los diagnósticos asociados a un paciente por el nivel del diagnóstico.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorSeis(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
        String idPaciente = vista.RDC9.getSelectedItem().toString();
      consulta = conectar.prepareStatement("SELECT DISTINCT diagnostico.nombreDiagnostico, nivel, diagnostico_observaciones.observacion FROM diagnostico JOIN\n" +
        "diagnostico_observaciones ON diagnostico.nombreDiagnostico = diagnostico_observaciones.nombreDiagnostico\n" +
        "JOIN cita_registra_diagnostico ON diagnostico.nombreDiagnostico = cita_registra_diagnostico.nombreDiagnostico\n" +
        "JOIN cita ON cita_registra_diagnostico.identificadorCita = cita.identificadorCita JOIN\n" +
        "citas_paciente ON cita.identificadorCita = citas_paciente.identificadorCita\n" +
        " WHERE citas_paciente.identificadorPaciente = " + idPaciente + " AND nivel = ?");
      String nivel = vista.RDC2.getSelectedItem().toString();
      consulta.setString(1, nivel);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;        
  }
  
  /**
  * Método ReporteDoctorSiete: Método que busca los diagnósticos asociados a un paciente por el nombre del diagnóstico.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorSiete(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      String idPaciente = vista.RDC9.getSelectedItem().toString();
      consulta = conectar.prepareStatement("SELECT DISTINCT diagnostico.nombreDiagnostico, nivel, diagnostico_observaciones.observacion FROM diagnostico JOIN\n" +
            "diagnostico_observaciones ON diagnostico.nombreDiagnostico = diagnostico_observaciones.nombreDiagnostico\n" +
            "JOIN cita_registra_diagnostico ON diagnostico.nombreDiagnostico = cita_registra_diagnostico.nombreDiagnostico\n" +
            "JOIN cita ON cita_registra_diagnostico.identificadorCita = cita.identificadorCita JOIN\n" +
            "citas_paciente ON cita.identificadorCita = citas_paciente.identificadorCita\n" +
            " WHERE citas_paciente.identificadorPaciente = " + idPaciente + " AND diagnostico.nombreDiagnostico\n" +
            " = ?");
      String nombre = vista.RDC3.getSelectedItem().toString();
      consulta.setString(1, nombre);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;        
  }
  
  /**
  * Método SegunReporteDoctor: Método que exporta el reporte de los diagnósticos asociados a un paciente al formato indicado por el usuario.
  */
  public void SegunReporteDoctor(){
    if(vista.ReportesPacienteFormato1.getSelectedItem().equals("CSV")){
      //REPORTE PARA FECHAS 
      if(vista.RDD3.getDate() != null && vista.RDD4.getDate() != null){     
        reportes.exportarCSV(ReporteDoctorCinco());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");
      }
      //REPORTE PARA NIVEL DIAGNOSTICO
      else if(vista.RDC2.getSelectedItem().toString() != null){
        reportes.exportarCSV(ReporteDoctorSeis());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }  
      //REPORTE PARA NOMBRE DIAGNOSTICO
      else if(vista.RDC3.getSelectedItem().toString() != null){
        reportes.exportarCSV(ReporteDoctorSiete());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }    
    }   
    else if(vista.ReportesPacienteFormato1.getSelectedItem().equals("PDF")){
      //REPORTE PARA FECHAS 
      if(vista.RDD3.getDate() != null && vista.RDD4.getDate() != null){     
        reportes.exportarPDF(ReporteDoctorCinco());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");
      }
      //REPORTE PARA NIVEL DIAGNOSTICO
      else if(vista.RDC2.getSelectedItem().toString() != null){
        reportes.exportarPDF(ReporteDoctorSeis());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }  
      //REPORTE PARA NOMBRE DIAGNOSTICO
      else if(vista.RDC3.getSelectedItem().toString() != null){
        reportes.exportarPDF(ReporteDoctorSiete());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }    
    }  
    else if(vista.ReportesPacienteFormato1.getSelectedItem().equals("HTML")){
      //REPORTE PARA FECHAS 
      if(vista.RDD3.getDate() != null && vista.RDD4.getDate() != null){     
        reportes.exportarHTML(ReporteDoctorCinco());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");
      }
      //REPORTE PARA NIVEL DIAGNOSTICO
      else if(vista.RDC2.getSelectedItem().toString() != null){
        reportes.exportarHTML(ReporteDoctorSeis());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }  
      //REPORTE PARA NOMBRE DIAGNOSTICO
      else if(vista.RDC3.getSelectedItem().toString() != null){
        reportes.exportarHTML(ReporteDoctorSiete());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }    
    }  
  }
  
  /**
  * Método ReporteDoctorOcho: Método que busca los tratamiento asociados a un paciente por rango de fechas.
  * @return resultadoUno: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorOcho(){
    ResultSet resultadoUno = null;   
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
        
      Date fecha1 = vista.RDD5.getDate();
      Date fecha2 = vista.RDD6.getDate();
      SimpleDateFormat formato= new SimpleDateFormat("yyy-mm-dd");
      String fecha1String= formato.format(fecha1);
      String fecha2String= formato.format(fecha2);
      Date fecha11=formato.parse(fecha1String);
      Date fecha22=formato.parse(fecha2String);

      java.sql.Date fecha11SQL = new java.sql.Date(fecha11.getTime());
      java.sql.Date fecha22SQL = new java.sql.Date(fecha22.getTime());
      
      String idPaciente = vista.RDC10.getSelectedItem().toString();
      
      consultaUno = conectar.prepareStatement("SELECT tratamiento.nombreTratamiento, tipo, dosis FROM tratamiento JOIN\n" +
            "diagnostico_contiene_tratamiento ON tratamiento.nombreTratamiento = diagnostico_contiene_tratamiento.nombreTratamiento\n" +
            "JOIN diagnostico ON diagnostico_contiene_tratamiento.nombreDiagnostico = diagnostico.nombreDiagnostico \n" +
            "JOIN cita_registra_diagnostico ON diagnostico.nombreDiagnostico = cita_registra_diagnostico.nombreDiagnostico\n" +
            "JOIN cita ON cita_registra_diagnostico.identificadorCita = cita.identificadorCita JOIN\n" +
            "bitacora_cita ON cita.identificadorCita = bitacora_cita.identificadorCita JOIN\n" +
            "bitacora_fechamodificacion ON bitacora_cita.identificadorBitacora = bitacora_fechamodificacion.identificadorBitacora\n" +
            "JOIN citas_paciente ON citas_paciente.identificadorCita = cita.identificadorCita\n" +
            "WHERE identificadorPaciente = " + idPaciente + " AND (SELECT fechaModificacion FROM bitacora_fechamodificacion JOIn bitacora_cita ON\n" +
            "bitacora_fechamodificacion.identificadorBitacora = bitacora_cita.identificadorBitacora\n" +
            "JOIN bitacora_cita_estado ON bitacora_cita.identificadorCita = bitacora_cita_estado.identificadorCita\n" +
            "WHERE estado = 'Realizada') between ? AND ?");
      consultaUno.setDate(1, fecha11SQL);
      consultaUno.setDate(2, fecha22SQL);
      resultadoUno = consultaUno.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultadoUno;    
  }
  
  /**
  * Método ReporteDoctorNueve: Método que busca los tratamiento asociados a un paciente por el tipo de tratamiento.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorNueve(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
        String idPaciente = vista.RDC10.getSelectedItem().toString();
      consulta = conectar.prepareStatement("SELECT tratamiento.nombreTratamiento, tipo, dosis FROM tratamiento JOIN\n" +
            "diagnostico_contiene_tratamiento ON tratamiento.nombreTratamiento = diagnostico_contiene_tratamiento.nombreTratamiento\n" +
            "JOIN diagnostico ON diagnostico_contiene_tratamiento.nombreDiagnostico = diagnostico.nombreDiagnostico \n" +
            "JOIN cita_registra_diagnostico ON diagnostico.nombreDiagnostico = cita_registra_diagnostico.nombreDiagnostico\n" +
            "JOIN cita ON cita_registra_diagnostico.identificadorCita = cita.identificadorCita JOIN\n" +
            "citas_paciente ON citas_paciente.identificadorCita = cita.identificadorCita WHERE\n" +
            "identificadorPaciente =" + idPaciente + " AND tipo = ?");
      String tipo = vista.RDT5.getText();
      consulta.setString(1, tipo);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;      
  }
  
  /**
  * Método ReporteDoctorDiez: Método que busca los tratamiento asociados a un paciente por el nombre del tratamiento.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorDiez(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
        String idPaciente = vista.RDC10.getSelectedItem().toString();
      consulta = conectar.prepareStatement("SELECT tratamiento.nombreTratamiento, tipo, dosis FROM tratamiento JOIN\n" +
        "diagnostico_contiene_tratamiento ON tratamiento.nombreTratamiento = diagnostico_contiene_tratamiento.nombreTratamiento\n" +
        "JOIN diagnostico ON diagnostico_contiene_tratamiento.nombreDiagnostico = diagnostico.nombreDiagnostico \n" +
        "JOIN cita_registra_diagnostico ON diagnostico.nombreDiagnostico = cita_registra_diagnostico.nombreDiagnostico\n" +
        "JOIN cita ON cita_registra_diagnostico.identificadorCita = cita.identificadorCita JOIN\n" +
        "citas_paciente ON citas_paciente.identificadorCita = cita.identificadorCita WHERE\n" +
        "identificadorPaciente = " + idPaciente + " AND tratamiento.nombreTratamiento = ?");
      String nombreTratamiento = vista.RDC4.getSelectedItem().toString();
      consulta.setString(1, nombreTratamiento);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;     
  }
  
  /**
  * Método TercerReporteDoctor: Método que exporta el reporte de los tratamientos asociados a un paciente al formato indicado por el usuario.
  */
  public void TercerReporteDoctor(){
    if(vista.ReportesPacienteFormato1.getSelectedItem().equals("CSV")){
      //REPORTE PARA FECHAS 
      if(vista.RDD5.getDate() != null && vista.RDD6.getDate() != null){     
        reportes.exportarCSV(ReporteDoctorOcho());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");
      }
      //REPORTE PARA TIPO
      else if(vista.RDT5.getText().length()!=0){
        reportes.exportarCSV(ReporteDoctorNueve());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }  
      //REPORTE PARA NOMBRE 
      else if(vista.RDC4.getSelectedItem().toString() != null){
        reportes.exportarCSV(ReporteDoctorDiez());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }    
    } 
    else if(vista.ReportesPacienteFormato1.getSelectedItem().equals("PDF")){
      //REPORTE PARA FECHAS 
      if(vista.RDD5.getDate() != null && vista.RDD6.getDate() != null){     
        reportes.exportarPDF(ReporteDoctorOcho());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");
      }
      //REPORTE PARA TIPO
      else if(vista.RDT5.getText().length()!=0){
        reportes.exportarPDF(ReporteDoctorNueve());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }  
      //REPORTE PARA NOMBRE 
      else if(vista.RDC4.getSelectedItem().toString() != null){
        reportes.exportarPDF(ReporteDoctorDiez());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }    
    } 
    else if(vista.ReportesPacienteFormato1.getSelectedItem().equals("HTML")){
      //REPORTE PARA FECHAS 
      if(vista.RDD5.getDate() != null && vista.RDD6.getDate() != null){     
        reportes.exportarHTML(ReporteDoctorOcho());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");
      }
      //REPORTE PARA TIPO
      else if(vista.RDT5.getText().length()!=0){
        reportes.exportarHTML(ReporteDoctorNueve());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }  
      //REPORTE PARA NOMBRE 
      else if(vista.RDC4.getSelectedItem().toString() != null){
        reportes.exportarHTML(ReporteDoctorDiez());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }    
    } 
  }
  
  /**
  * Método ReporteDoctorOnce: Método que busca la cantidad de citas registradas en el sistema por rango de fechas.
  * @return resultadoUno: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorOnce(){
    ResultSet resultadoUno = null;   
    PreparedStatement consultaUno;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
        
      Date fecha1 = vista.RDD7.getDate();
      Date fecha2 = vista.RDD8.getDate();
      SimpleDateFormat formato= new SimpleDateFormat("yyy-mm-dd");
      String fecha1String= formato.format(fecha1);
      String fecha2String= formato.format(fecha2);
      Date fecha11=formato.parse(fecha1String);
      Date fecha22=formato.parse(fecha2String);

      java.sql.Date fecha11SQL = new java.sql.Date(fecha11.getTime());
      java.sql.Date fecha22SQL = new java.sql.Date(fecha22.getTime());
      
      
      consultaUno = conectar.prepareStatement("SELECT count(cita.identificadorCita) FROM cita JOIN citas_paciente\n" +
        "ON cita.identificadorCita = citas_paciente.identificadorCita WHERE\n" +
        "cita.fecha between ?  AND ?");
      consultaUno.setDate(1, fecha11SQL);
      consultaUno.setDate(2, fecha22SQL);
      resultadoUno = consultaUno.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultadoUno;    
  }
  
  /**
  * Método ReporteDoctorDoce: Método que busca la cantidad de citas registradas en el sistema por la especialidad.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorDoce(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{

      consulta = conectar.prepareStatement("SELECT count(cita.identificadorCita) FROM cita JOIN citas_paciente\n" +
            "ON cita.identificadorCita = citas_paciente.identificadorCita WHERE\n" +
            "cita.area = ?");
      String especialidad = vista.RDT6.getText();
      consulta.setString(1, especialidad);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;      
  }
  
  /**
  * Método ReporteDoctorTrece: Método que busca la cantidad de citas registradas en el sistema por el estado de la cita.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorTrece(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consulta = conectar.prepareStatement("SELECT count(cita.identificadorCita) FROM cita JOIN citas_paciente\n" +
            "ON cita.identificadorCita = citas_paciente.identificadorCita WHERE\n" +
            "cita.estado = ?");
      String estadoCita = vista.RDC5.getSelectedItem().toString();
      consulta.setString(1, estadoCita);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;     
  }
  
  /**
  * Método CuartoReporteDoctor: Método que exporta el reporte de la cantidad de citas registradas en el sistema al formato indicado por el usuario.
  */
  public void CuartoReporteDoctor(){
    if(vista.ReportesPacienteFormato1.getSelectedItem().equals("CSV")){
      //REPORTE PARA FECHAS 
      if(vista.RDD7.getDate() != null && vista.RDD8.getDate() != null){     
        reportes.exportarCSV(ReporteDoctorOnce());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");
      }
      //REPORTE PARA ESPECIALIDAD
      else if(vista.RDT6.getText().length()!=0){
        reportes.exportarCSV(ReporteDoctorDoce());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }  
      //REPORTE PARA ESTADO CITA  
      else if(vista.RDC5.getSelectedItem().toString() != null){
        reportes.exportarCSV(ReporteDoctorTrece());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }    
    }     
    else if(vista.ReportesPacienteFormato1.getSelectedItem().equals("PDF")){
      //REPORTE PARA FECHAS 
      if(vista.RDD7.getDate() != null && vista.RDD8.getDate() != null){     
        reportes.exportarPDF(ReporteDoctorOnce());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");
      }
      //REPORTE PARA ESPECIALIDAD
      else if(vista.RDT6.getText().length()!=0){
        reportes.exportarPDF(ReporteDoctorDoce());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }  
      //REPORTE PARA ESTADO CITA  
      else if(vista.RDC5.getSelectedItem().toString() != null){
        reportes.exportarPDF(ReporteDoctorTrece());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }    
    }  
    else if(vista.ReportesPacienteFormato1.getSelectedItem().equals("HTML")){
      //REPORTE PARA FECHAS 
      if(vista.RDD7.getDate() != null && vista.RDD8.getDate() != null){     
        reportes.exportarHTML(ReporteDoctorOnce());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");
      }
      //REPORTE PARA ESPECIALIDAD
      else if(vista.RDT6.getText().length()!=0){
        reportes.exportarHTML(ReporteDoctorDoce());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }  
      //REPORTE PARA ESTADO CITA 
      else if(vista.RDC5.getSelectedItem().toString() != null){
        reportes.exportarHTML(ReporteDoctorTrece());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }    
    }  
  }
  
  /**
  * Método ReporteDoctorCatorce: Método que busca la cantidad de diagnósticos registrados en el sistema por el nivel.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorCatorce(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{

      consulta = conectar.prepareStatement("SELECT count(nombreDiagnostico) FROM diagnostico WHERE nivel = ?");
      String nivel = vista.RDC6.getSelectedItem().toString();
      consulta.setString(1, nivel);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;      
  }
  
  /**
  * Método ReporteDoctorQuince: Método que busca la cantidad de diagnósticos registrados en el sistema por la especialidad.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorQuince(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{

      consulta = conectar.prepareStatement("SELECT count(diagnostico.nombreDiagnostico) FROM diagnostico JOIN cita_registra_diagnostico ON\n" +
            "diagnostico.nombreDiagnostico = cita_registra_diagnostico.nombreDiagnostico\n" +
            "JOIN cita ON cita_registra_diagnostico.identificadorCita = cita.identificadorCita\n" +
            "WHERE cita.area = ?");
      String especialidad = vista.RDT7.getText();
      consulta.setString(1, especialidad);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;      
  }
  
  /**
  * Método ReporteDoctorDieciseis: Método que busca la cantidad de diagnósticos registrados en el sistema por el paciente.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorDieciseis(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consulta = conectar.prepareStatement("SELECT count(diagnostico.nombreDiagnostico) FROM diagnostico JOIN cita_registra_diagnostico ON\n" +
            "diagnostico.nombreDiagnostico = cita_registra_diagnostico.nombreDiagnostico\n" +
            "JOIN cita ON cita_registra_diagnostico.identificadorCita = cita.identificadorCita\n" +
            "JOIN citas_paciente ON cita.identificadorCita = citas_paciente.identificadorPaciente\n" +
            "WHERE identificadorPaciente = ?");
      int paciente = Integer.parseInt(vista.RDC7.getSelectedItem().toString());
      consulta.setInt(1, paciente);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;     
  }
  
  /**
  * Método QuintoReporteDoctor: Método que exporta el reporte de la cantidad de diagnósticos registrados en el sistema al formato indicado por el usuario.
  */
  public void QuintoReporteDoctor(){
    if(vista.ReportesPacienteFormato1.getSelectedItem().equals("CSV")){
      //REPORTE PARA NIVEL 
      if(vista.RDC6.getSelectedItem().toString() != null){     
        reportes.exportarCSV(ReporteDoctorCatorce());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");
      }
      //REPORTE PARA ESPECIALIDAD
      else if(vista.RDT7.getText().length()!=0){
        reportes.exportarCSV(ReporteDoctorQuince());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }  
      //REPORTE PARA PACIENTE  
      else if(vista.RDC7.getSelectedItem().toString() != null){
        reportes.exportarCSV(ReporteDoctorDieciseis());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }  
      else if(vista.RDC6.getSelectedItem().toString().equals("") && vista.RDC7.getSelectedItem().toString().equals("") && vista.RDT7.getText().equals("")){
        reportes.exportarCSV(ReporteDoctorVeintiuno());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");     
      }
    }   
    else if(vista.ReportesPacienteFormato1.getSelectedItem().equals("PDF")){
      //REPORTE PARA NIVEL 
      if(vista.RDC6.getSelectedItem().toString() != null){     
        reportes.exportarPDF(ReporteDoctorCatorce());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");
      }
      //REPORTE PARA ESPECIALIDAD
      else if(vista.RDT7.getText().length()!=0){
        reportes.exportarPDF(ReporteDoctorQuince());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }  
      //REPORTE PARA PACIENTE  
      else if(vista.RDC7.getSelectedItem().toString() != null){
        reportes.exportarPDF(ReporteDoctorDieciseis());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }   
      else if(vista.RDC6.getSelectedItem().toString().equals("") && vista.RDC7.getSelectedItem().toString().equals("") && vista.RDT7.getText().equals("")){
        reportes.exportarPDF(ReporteDoctorVeintiuno());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");     
      }
    } 
    else if(vista.ReportesPacienteFormato1.getSelectedItem().equals("HTML")){
      //REPORTE PARA NIVEL 
      if(vista.RDC6.getSelectedItem().toString() != null){     
        reportes.exportarHTML(ReporteDoctorCatorce());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");
      }
      //REPORTE PARA ESPECIALIDAD
      else if(vista.RDT7.getText().length()!=0){
        reportes.exportarHTML(ReporteDoctorQuince());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }  
      //REPORTE PARA PACIENTE  
      else if(vista.RDC7.getSelectedItem().toString() != null){
        reportes.exportarHTML(ReporteDoctorDieciseis());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      } 
      else if(vista.RDC6.getSelectedItem().toString().equals("") && vista.RDC7.getSelectedItem().toString().equals("") && vista.RDT7.getText().equals("")){
        reportes.exportarHTML(ReporteDoctorVeintiuno());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");     
      }
    } 
  
  }
  
  /**
  * Método ReporteDoctorDiecisiete: Método que busca la cantidad de tratamientos registrados en el sistema por el tipo.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorDiecisiete(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{

      consulta = conectar.prepareStatement("SELECT count(nombreTratamiento) FROM tratamiento WHERE tipo = ?");
      String tipo = vista.RDT8.getText();
      consulta.setString(1, tipo);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;      
  }
  
  /**
  * Método ReporteDoctorDieciOcho: Método que busca la cantidad de tratamientos registrados en el sistema por la especialidad.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorDieciocho(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{

      consulta = conectar.prepareStatement("SELECT count(tratamiento.nombreTratamiento) FROM tratamiento JOIN diagnostico_contiene_tratamiento ON\n" +
            "tratamiento.nombreTratamiento = diagnostico_contiene_tratamiento.nombreTratamiento JOIN\n" +
            "diagnostico ON diagnostico.nombreDiagnostico = diagnostico_contiene_tratamiento.nombreDiagnostico\n" +
            "JOIN cita_registra_diagnostico ON diagnostico.nombreDiagnostico = cita_registra_diagnostico.nombreDiagnostico\n" +
            "JOIN cita ON cita_registra_diagnostico.identificadorCita = cita.identificadorCita\n" +
            "WHERE cita.area = ?");
      String especialidad = vista.RDT9.getText();
      consulta.setString(1, especialidad);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;      
  }
  
  /**
  * Método ReporteDoctorDieciNueve: Método que busca la cantidad de tratamientos registrados en el sistema por el paciente
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorDiecinueve(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consulta = conectar.prepareStatement("SELECT count(tratamiento.nombreTratamiento) FROM tratamiento JOIN diagnostico_contiene_tratamiento ON\n" +
            "tratamiento.nombreTratamiento = diagnostico_contiene_tratamiento.nombreTratamiento JOIN\n" +
            "diagnostico ON diagnostico.nombreDiagnostico = diagnostico_contiene_tratamiento.nombreDiagnostico\n" +
            "JOIN cita_registra_diagnostico ON diagnostico.nombreDiagnostico = cita_registra_diagnostico.nombreDiagnostico\n" +
            "JOIN cita ON cita_registra_diagnostico.identificadorCita = cita.identificadorCita JOIN\n" +
            "citas_paciente ON cita.identificadorCita = citas_paciente.identificadorPaciente\n" +
            "WHERE identificadorPaciente = ?");
      int paciente = Integer.parseInt(vista.RDC8.getSelectedItem().toString());
      consulta.setInt(1, paciente);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;     
  }
  
  /**
  * Método SextoReporteDoctor: Método que exporta el reporte de la cantidad de tratamientos registrados en el sistema al formato indicado por el usuario.
  */
  public void SextoReporteDoctor(){
    if(vista.ReportesPacienteFormato1.getSelectedItem().equals("CSV")){
      //REPORTE PARA NIVEL 
      if(vista.RDT8.getText().length()!=0){     
        reportes.exportarCSV(ReporteDoctorDiecisiete());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");
      }
      //REPORTE PARA ESPECIALIDAD
      else if(vista.RDT9.getText().length()!=0){
        reportes.exportarCSV(ReporteDoctorDieciocho());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }  
      //REPORTE PARA PACIENTE  
      else if(vista.RDC8.getSelectedItem().toString() != null){
        reportes.exportarCSV(ReporteDoctorDiecinueve());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }
      else if(vista.RDT8.getText().equals("") && vista.RDT9.getText().equals("") && vista.RDC8.getSelectedItem().toString().equals("")){
        reportes.exportarCSV(ReporteDoctorVeintidos());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");   
      }
    }   
    else if(vista.ReportesPacienteFormato1.getSelectedItem().equals("PDF")){
      //REPORTE PARA NIVEL 
      if(vista.RDT8.getText().length()!=0){     
        reportes.exportarPDF(ReporteDoctorDiecisiete());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");
      }
      //REPORTE PARA ESPECIALIDAD
      else if(vista.RDT9.getText().length()!=0){
        reportes.exportarPDF(ReporteDoctorDieciocho());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }  
      //REPORTE PARA PACIENTE  
      else if(vista.RDC8.getSelectedItem().toString() != null){
        reportes.exportarPDF(ReporteDoctorDiecinueve());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }   
      else if(vista.RDT8.getText().equals("") && vista.RDT9.getText().equals("") && vista.RDC8.getSelectedItem().toString().equals("")){
        reportes.exportarPDF(ReporteDoctorVeintidos());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");   
      }
    } 
    else if(vista.ReportesPacienteFormato1.getSelectedItem().equals("HTML")){
      //REPORTE PARA NIVEL 
      if(vista.RDT8.getText().length()!=0){     
        reportes.exportarHTML(ReporteDoctorDiecisiete());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");
      }
      //REPORTE PARA ESPECIALIDAD
      else if(vista.RDT9.getText().length()!=0){
        reportes.exportarHTML(ReporteDoctorDieciocho());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }  
      //REPORTE PARA PACIENTE  
      else if(vista.RDC8.getSelectedItem().toString() != null){
        reportes.exportarHTML(ReporteDoctorDiecinueve());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }   
      else if(vista.RDT8.getText().equals("") && vista.RDT9.getText().equals("") && vista.RDC8.getSelectedItem().toString().equals("")){
        reportes.exportarHTML(ReporteDoctorVeintidos());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");   
      }
    } 
  
  }
  
  /**
  * Método ReporteDoctorVeinte: Método que busca el detalle de la hospitalización de un paciente por el nombre.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorVeinte(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{

      consulta = conectar.prepareStatement("SELECT hospitalizacion.identificadorHospitalizacion, hospitalizacion.fechaInicio, hospitalizacion.fechaFin,\n" +
            "centroatencion.nombre, diagnostico.nombreDiagnostico, cita.area, \n" +
            "funcionario.identificadorFuncionario, fechaSeguimiento, seguimiento_observacion.observacion, \n" +
            "nombreTratamiento FROM hospitalizacion JOIN centroatencion_recibe_hospitalizacion\n" +
            "ON centroatencion_recibe_hospitalizacion.identificadorHospitalizacion = hospitalizacion.identificadorHospitalizacion\n" +
            "JOIN centroatencion ON centroatencion_recibe_hospitalizacion.codigoCentro = centroatencion.codigoCentro\n" +
            "JOIN cita_hospitalizacion ON hospitalizacion.identificadorHospitalizacion =\n" +
            "cita_hospitalizacion.identificadorHospitalizacion JOIN cita ON cita_hospitalizacion.identificadorCita\n" +
            "= cita.identificadorCita JOIN cita_registra_diagnostico ON cita.identificadorCita =\n" +
            "cita_registra_diagnostico.identificadorCita JOIN diagnostico ON cita_registra_diagnostico.nombreDiagnostico\n" +
            "= diagnostico.nombreDiagnostico JOIN funcionario_gestiona_cita ON cita.identificadorCita =\n" +
            "funcionario_gestiona_cita.identificadorCita JOIN funcionario ON funcionario_gestiona_cita.identificadorFuncionario =\n" +
            "funcionario.identificadorFuncionario JOIN hospitalizacion_necesita_seguimiento ON\n" +
            "hospitalizacion.identificadorHospitalizacion = hospitalizacion_necesita_seguimiento.identificadorHospitalizacion\n" +
            "JOIN seguimiento ON hospitalizacion_necesita_seguimiento.identificadorSeguimiento =\n" +
            "seguimiento.identificadorSeguimiento JOIN seguimiento_fecha ON seguimiento.identificadorSeguimiento =\n" +
            "seguimiento_fecha.identificadorSeguimiento JOIN seguimiento_observacion ON seguimiento.identificadorSeguimiento =\n" +
            "seguimiento_observacion.identificadorSeguimiento JOIN seguimiento_requiere_tratamiento ON\n" +
            "seguimiento.identificadorSeguimiento = seguimiento_requiere_tratamiento.identificadorSeguimiento JOIN\n" +
            "citas_paciente ON cita.identificadorCita = citas_paciente.identificadorCita JOIN paciente\n" +
            "ON citas_paciente.identificadorPaciente = paciente.identificadorPaciente JOIN paciente_usuario\n" +
            "ON paciente.identificadorPaciente = paciente_usuario.identificadorPaciente JOIN usuario ON\n" +
            "paciente_usuario.cedula = usuario.cedula WHERE usuario.nombre = ? AND usuario.apellido1 = ? AND\n" +
            "usuario.apellido2 = ?");
      String nombre = vista.RDT10.getText();
      String apellido1 = vista.RDT11.getText();
      String apellido2 = vista.RDT12.getText();
      consulta.setString(1, nombre);
      consulta.setString(2, apellido1);
      consulta.setString(3, apellido2);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;      
  }
  
  /**
  * Método SeptimoReporteDoctor: Método que exporta el reporte del detalle de la hospitalización de un paciente al formato indicado por el usuario.
  */
  public void SeptimoReporteDoctor(){
    if(vista.ReportesPacienteFormato1.getSelectedItem().equals("CSV")){
      //REPORTE PARA NOMBRE PACIENTE 
      if(vista.RDT10.getText().length()!=0 && vista.RDT11.getText().length()!=0 && vista.RDT12.getText().length()!=0){     
        reportes.exportarCSV(ReporteDoctorVeinte());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");
      }   
    }
    else if(vista.ReportesPacienteFormato1.getSelectedItem().equals("PDF")){
      //REPORTE PARA NOMBRE PACIENTE 
      if(vista.RDT10.getText().length()!=0 && vista.RDT11.getText().length()!=0 && vista.RDT12.getText().length()!=0){     
        reportes.exportarPDF(ReporteDoctorVeinte());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");
      }   
    }
    else if(vista.ReportesPacienteFormato1.getSelectedItem().equals("HTML")){
      //REPORTE PARA NOMBRE PACIENTE 
      if(vista.RDT10.getText().length()!=0 && vista.RDT11.getText().length()!=0 && vista.RDT12.getText().length()!=0){     
        reportes.exportarHTML(ReporteDoctorVeinte());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");
      }   
    }
  }

  /**
  * Método ReporteDoctorVeintiuno: Método que busca la cantidad de diagnósticos en el sistema.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorVeintiuno(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{

      consulta = conectar.prepareStatement("SELECT count(nombreDiagnostico) FROM diagnostico");
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;      
  }
  
  /**
  * Método ReporteDoctorVeintidos: Método que busca la cantidad de tratamientos en el sistema.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteDoctorVeintidos(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{

      consulta = conectar.prepareStatement("SELECT count(nombreTratamiento) FROM tratamiento");
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;      
  }
  
  //REPORTES SECRETARIO
  
  /**
  * Método ReporteSecretarioUno: Método que busca las citas registradas en el sistema por rango de fechas.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteSecretarioUno(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
        
      Date fecha1 = vista.RSD1.getDate();
      Date fecha2 = vista.RSD2.getDate();
      SimpleDateFormat formato= new SimpleDateFormat("yyy-mm-dd");
      String fecha1String= formato.format(fecha1);
      String fecha2String= formato.format(fecha2);
      Date fecha11=formato.parse(fecha1String);
      Date fecha22=formato.parse(fecha2String);

      java.sql.Date fecha11SQL = new java.sql.Date(fecha11.getTime());
      java.sql.Date fecha22SQL = new java.sql.Date(fecha22.getTime());
      
      consulta = conectar.prepareStatement("SELECT cita.identificadorCita, fecha, hora, area, observacion, estado, identificadorPaciente FROM cita JOIN citas_paciente\n" +
        "ON cita.identificadorCita = citas_paciente.identificadorCita WHERE\n" +
        "cita.fecha between ? AND ?");
      consulta.setDate(1, fecha11SQL);
      consulta.setDate(2, fecha22SQL);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;   
  }
  
  /**
  * Método ReporteSecretarioDos: Método que busca las citas registradas en el sistema por el estado.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteSecretarioDos(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consulta = conectar.prepareStatement("SELECT cita.identificadorCita, fecha, hora, area, observacion, estado, identificadorPaciente FROM cita JOIN citas_paciente\n" +
        "ON cita.identificadorCita = citas_paciente.identificadorCita WHERE\n" +
        "cita.estado = ?");
      String estadoCita = vista.RSC1.getSelectedItem().toString();
      consulta.setString(1, estadoCita);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;       
  }
  
  /**
  * Método ReporteSecretarioTres: Método que busca las citas registradas en el sistema por la especialidad.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteSecretarioTres(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consulta = conectar.prepareStatement("SELECT cita.identificadorCita, fecha, hora, area, observacion, estado, identificadorPaciente FROM cita JOIN citas_paciente\n" +
            "ON cita.identificadorCita = citas_paciente.identificadorCita WHERE\n" +
            "cita.area = ?");
      String especialidad = vista.RST1.getText();
      consulta.setString(1, especialidad);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;       
  }
  
  /**
  * Método ReporteSecretarioCuatro: Método que busca las citas registradas en el sistema por el nombre del paciente.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteSecretarioCuatro(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consulta = conectar.prepareStatement("SELECT cita.identificadorCita, fecha, hora, area, observacion, estado, nombre, apellido1, apellido2 FROM cita JOIN citas_paciente\n" +
            "ON cita.identificadorCita = citas_paciente.identificadorCita JOIN paciente ON\n" +
            "citas_paciente.identificadorPaciente = paciente.identificadorPaciente JOIN paciente_usuario\n" +
            "ON paciente.identificadorPaciente = paciente_usuario.identificadorPaciente\n" +
            "JOIN usuario ON paciente_usuario.cedula = usuario.cedula WHERE nombre = ? AND\n" +
            "apellido1 = ? AND apellido2 = ?");
      String nombre = vista.RST2.getText();
      String apellido1 = vista.RST3.getText();
      String apellido2 = vista.RST4.getText();
      consulta.setString(1, nombre);
      consulta.setString(2, apellido1);
      consulta.setString(3, apellido2);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;       
  }
  
  /**
  * Método PrimerReporteSecretario: Método que exporta el reporte de las citas registradas en el sistema al formato indicado por el usuario.
  */
  public void PrimerReporteSecretario(){
    if(vista.RSFormato.getSelectedItem().equals("CSV")){
      //REPORTE PARA FECHA 
      if(vista.RSD1.getDate() != null && vista.RSD2.getDate() != null){     
        reportes.exportarCSV(ReporteSecretarioUno());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");
      }
      //REPORTE PARA ESTADO
      else if(vista.RSC1.getSelectedItem().toString() != null){
        reportes.exportarCSV(ReporteSecretarioDos());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }  
      //REPORTE PARA ESPECIALIDAD  
      else if(vista.RST1.getText().length()!=0){
        reportes.exportarCSV(ReporteSecretarioTres());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }
      //REPORTE PARA NOMBRE
      else if(vista.RST2.getText().length()!=0 && vista.RST3.getText().length()!=0 && vista.RST4.getText().length()!=0){
        reportes.exportarCSV(ReporteSecretarioCuatro());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");   
      }
    } 
    else if(vista.RSFormato.getSelectedItem().equals("PDF")){
      //REPORTE PARA FECHA 
      if(vista.RSD1.getDate() != null && vista.RSD2.getDate() != null){     
        reportes.exportarPDF(ReporteSecretarioUno());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");
      }
      //REPORTE PARA ESTADO
      else if(vista.RSC1.getSelectedItem().toString() != null){
        reportes.exportarPDF(ReporteSecretarioDos());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }  
      //REPORTE PARA ESPECIALIDAD  
      else if(vista.RST1.getText().length()!=0){
        reportes.exportarPDF(ReporteSecretarioTres());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }
      //REPORTE PARA NOMBRE
      else if(vista.RST2.getText().length()!=0 && vista.RST3.getText().length()!=0 && vista.RST4.getText().length()!=0){
        reportes.exportarPDF(ReporteSecretarioCuatro());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");   
      }
    } 
    else if(vista.RSFormato.getSelectedItem().equals("HTML")){
      //REPORTE PARA FECHA 
      if(vista.RSD1.getDate() != null && vista.RSD2.getDate() != null){     
        reportes.exportarHTML(ReporteSecretarioUno());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");
      }
      //REPORTE PARA ESTADO
      else if(vista.RSC1.getSelectedItem().toString() != null){
        reportes.exportarHTML(ReporteSecretarioDos());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }  
      //REPORTE PARA ESPECIALIDAD  
      else if(vista.RST1.getText().length()!=0){
        reportes.exportarHTML(ReporteSecretarioTres());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }
      //REPORTE PARA NOMBRE
      else if(vista.RST2.getText().length()!=0 && vista.RST3.getText().length()!=0 && vista.RST4.getText().length()!=0){
        reportes.exportarHTML(ReporteSecretarioCuatro());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");   
      }
    } 
  }
  
  /**
  * Método ReporteSecretarioCinco: Método que busca las hospitalizaciones registradas en el sistema por el rango de fechas.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteSecretarioCinco(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
        
      Date fecha1 = vista.RSD3.getDate();
      Date fecha2 = vista.RSD4.getDate();
      SimpleDateFormat formato= new SimpleDateFormat("yyy-mm-dd");
      String fecha1String= formato.format(fecha1);
      String fecha2String= formato.format(fecha2);
      Date fecha11=formato.parse(fecha1String);
      Date fecha22=formato.parse(fecha2String);

      java.sql.Date fecha11SQL = new java.sql.Date(fecha11.getTime());
      java.sql.Date fecha22SQL = new java.sql.Date(fecha22.getTime());
      
      consulta = conectar.prepareStatement("SELECT hospitalizacion.identificadorHospitalizacion, hospitalizacion.fechaInicio, hospitalizacion.fechaFin,\n" +
            "centroatencion.nombre, diagnostico.nombreDiagnostico, cita.area, \n" +
            "funcionario.identificadorFuncionario, fechaSeguimiento, seguimiento_observacion.observacion, \n" +
            "nombreTratamiento, usuario.nombre, usuario.apellido1, usuario.apellido2 FROM hospitalizacion JOIN centroatencion_recibe_hospitalizacion\n" +
            "ON centroatencion_recibe_hospitalizacion.identificadorHospitalizacion = hospitalizacion.identificadorHospitalizacion\n" +
            "JOIN centroatencion ON centroatencion_recibe_hospitalizacion.codigoCentro = centroatencion.codigoCentro\n" +
            "JOIN cita_hospitalizacion ON hospitalizacion.identificadorHospitalizacion =\n" +
            "cita_hospitalizacion.identificadorHospitalizacion JOIN cita ON cita_hospitalizacion.identificadorCita\n" +
            "= cita.identificadorCita JOIN cita_registra_diagnostico ON cita.identificadorCita =\n" +
            "cita_registra_diagnostico.identificadorCita JOIN diagnostico ON cita_registra_diagnostico.nombreDiagnostico\n" +
            "= diagnostico.nombreDiagnostico JOIN funcionario_gestiona_cita ON cita.identificadorCita =\n" +
            "funcionario_gestiona_cita.identificadorCita JOIN funcionario ON funcionario_gestiona_cita.identificadorFuncionario =\n" +
            "funcionario.identificadorFuncionario JOIN hospitalizacion_necesita_seguimiento ON\n" +
            "hospitalizacion.identificadorHospitalizacion = hospitalizacion_necesita_seguimiento.identificadorHospitalizacion\n" +
            "JOIN seguimiento ON hospitalizacion_necesita_seguimiento.identificadorSeguimiento =\n" +
            "seguimiento.identificadorSeguimiento JOIN seguimiento_fecha ON seguimiento.identificadorSeguimiento =\n" +
            "seguimiento_fecha.identificadorSeguimiento JOIN seguimiento_observacion ON seguimiento.identificadorSeguimiento =\n" +
            "seguimiento_observacion.identificadorSeguimiento JOIN seguimiento_requiere_tratamiento ON\n" +
            "seguimiento.identificadorSeguimiento = seguimiento_requiere_tratamiento.identificadorSeguimiento JOIN\n" +
            "citas_paciente ON cita.identificadorCita = citas_paciente.identificadorCita JOIN paciente\n" +
            "ON citas_paciente.identificadorPaciente = paciente.identificadorPaciente JOIN paciente_usuario\n" +
            "ON paciente.identificadorPaciente = paciente_usuario.identificadorPaciente JOIN usuario ON\n" +
            "paciente_usuario.cedula = usuario.cedula JOIN bitacora_cita ON cita.identificadorCita = bitacora_cita.identificadorCita JOIN\n" +
            "bitacora_fechamodificacion ON bitacora_cita.identificadorBitacora = bitacora_fechamodificacion.identificadorBitacora\n" +
            "WHERE (SELECT fechaModificacion FROM bitacora_fechamodificacion JOIn bitacora_cita ON\n" +
            "bitacora_fechamodificacion.identificadorBitacora = bitacora_cita.identificadorBitacora\n" +
            "JOIN bitacora_cita_estado ON bitacora_cita.identificadorCita = bitacora_cita_estado.identificadorCita\n" +
            "WHERE estado = 'REALIZADA') between ? AND ?");
      consulta.setDate(1, fecha11SQL);
      consulta.setDate(2, fecha22SQL);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;   
  }
  
  /**
  * Método ReporteSecretarioSeis: Método que busca las hospitalizaciones registradas en el sistema por la especialidad.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteSecretarioSeis(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consulta = conectar.prepareStatement("SELECT hospitalizacion.identificadorHospitalizacion, hospitalizacion.fechaInicio, hospitalizacion.fechaFin,\n" +
            "centroatencion.nombre, diagnostico.nombreDiagnostico, cita.area, \n" +
            "funcionario.identificadorFuncionario, fechaSeguimiento, seguimiento_observacion.observacion, \n" +
            "nombreTratamiento, usuario.nombre, usuario.apellido1, usuario.apellido2 FROM hospitalizacion JOIN centroatencion_recibe_hospitalizacion\n" +
            "ON centroatencion_recibe_hospitalizacion.identificadorHospitalizacion = hospitalizacion.identificadorHospitalizacion\n" +
            "JOIN centroatencion ON centroatencion_recibe_hospitalizacion.codigoCentro = centroatencion.codigoCentro\n" +
            "JOIN cita_hospitalizacion ON hospitalizacion.identificadorHospitalizacion =\n" +
            "cita_hospitalizacion.identificadorHospitalizacion JOIN cita ON cita_hospitalizacion.identificadorCita\n" +
            "= cita.identificadorCita JOIN cita_registra_diagnostico ON cita.identificadorCita =\n" +
            "cita_registra_diagnostico.identificadorCita JOIN diagnostico ON cita_registra_diagnostico.nombreDiagnostico\n" +
            "= diagnostico.nombreDiagnostico JOIN funcionario_gestiona_cita ON cita.identificadorCita =\n" +
            "funcionario_gestiona_cita.identificadorCita JOIN funcionario ON funcionario_gestiona_cita.identificadorFuncionario =\n" +
            "funcionario.identificadorFuncionario JOIN hospitalizacion_necesita_seguimiento ON\n" +
            "hospitalizacion.identificadorHospitalizacion = hospitalizacion_necesita_seguimiento.identificadorHospitalizacion\n" +
            "JOIN seguimiento ON hospitalizacion_necesita_seguimiento.identificadorSeguimiento =\n" +
            "seguimiento.identificadorSeguimiento JOIN seguimiento_fecha ON seguimiento.identificadorSeguimiento =\n" +
            "seguimiento_fecha.identificadorSeguimiento JOIN seguimiento_observacion ON seguimiento.identificadorSeguimiento =\n" +
            "seguimiento_observacion.identificadorSeguimiento JOIN seguimiento_requiere_tratamiento ON\n" +
            "seguimiento.identificadorSeguimiento = seguimiento_requiere_tratamiento.identificadorSeguimiento JOIN\n" +
            "citas_paciente ON cita.identificadorCita = citas_paciente.identificadorCita JOIN paciente\n" +
            "ON citas_paciente.identificadorPaciente = paciente.identificadorPaciente JOIN paciente_usuario\n" +
            "ON paciente.identificadorPaciente = paciente_usuario.identificadorPaciente JOIN usuario ON\n" +
            "paciente_usuario.cedula = usuario.cedula WHERE cita.area = '?");
      String especialidad = vista.RST5.getText();
      consulta.setString(1, especialidad);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;       
  }
  
  /**
  * Método ReporteSecretarioSiete: Método que busca las hospitalizaciones registradas en el sistema por el nombre del paciente.
  * @return resultado: Objeto de tipo ResultSet que contiene la consulta.
  */
  public ResultSet ReporteSecretarioSiete(){
    ResultSet resultado = null;   
    PreparedStatement consulta;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();  
    try{
      consulta = conectar.prepareStatement("SELECT hospitalizacion.identificadorHospitalizacion, hospitalizacion.fechaInicio, hospitalizacion.fechaFin,\n" +
            "centroatencion.nombre, diagnostico.nombreDiagnostico, cita.area, \n" +
            "funcionario.identificadorFuncionario, fechaSeguimiento, seguimiento_observacion.observacion, \n" +
            "nombreTratamiento FROM hospitalizacion JOIN centroatencion_recibe_hospitalizacion\n" +
            "ON centroatencion_recibe_hospitalizacion.identificadorHospitalizacion = hospitalizacion.identificadorHospitalizacion\n" +
            "JOIN centroatencion ON centroatencion_recibe_hospitalizacion.codigoCentro = centroatencion.codigoCentro\n" +
            "JOIN cita_hospitalizacion ON hospitalizacion.identificadorHospitalizacion =\n" +
            "cita_hospitalizacion.identificadorHospitalizacion JOIN cita ON cita_hospitalizacion.identificadorCita\n" +
            "= cita.identificadorCita JOIN cita_registra_diagnostico ON cita.identificadorCita =\n" +
            "cita_registra_diagnostico.identificadorCita JOIN diagnostico ON cita_registra_diagnostico.nombreDiagnostico\n" +
            "= diagnostico.nombreDiagnostico JOIN funcionario_gestiona_cita ON cita.identificadorCita =\n" +
            "funcionario_gestiona_cita.identificadorCita JOIN funcionario ON funcionario_gestiona_cita.identificadorFuncionario =\n" +
            "funcionario.identificadorFuncionario JOIN hospitalizacion_necesita_seguimiento ON\n" +
            "hospitalizacion.identificadorHospitalizacion = hospitalizacion_necesita_seguimiento.identificadorHospitalizacion\n" +
            "JOIN seguimiento ON hospitalizacion_necesita_seguimiento.identificadorSeguimiento =\n" +
            "seguimiento.identificadorSeguimiento JOIN seguimiento_fecha ON seguimiento.identificadorSeguimiento =\n" +
            "seguimiento_fecha.identificadorSeguimiento JOIN seguimiento_observacion ON seguimiento.identificadorSeguimiento =\n" +
            "seguimiento_observacion.identificadorSeguimiento JOIN seguimiento_requiere_tratamiento ON\n" +
            "seguimiento.identificadorSeguimiento = seguimiento_requiere_tratamiento.identificadorSeguimiento JOIN\n" +
            "citas_paciente ON cita.identificadorCita = citas_paciente.identificadorCita JOIN paciente\n" +
            "ON citas_paciente.identificadorPaciente = paciente.identificadorPaciente JOIN paciente_usuario\n" +
            "ON paciente.identificadorPaciente = paciente_usuario.identificadorPaciente JOIN usuario ON\n" +
            "paciente_usuario.cedula = usuario.cedula WHERE usuario.nombre = ? AND usuario.apellido1 = ? AND\n" +
            "usuario.apellido2 = ?");
      String nombre = vista.RST6.getText();
      String apellido1 = vista.RST7.getText();
      String apellido2 = vista.RST8.getText();
      consulta.setString(1, nombre);
      consulta.setString(2, apellido1);
      consulta.setString(3, apellido2);
      resultado = consulta.executeQuery();
    }
    catch(Exception error){ 
      System.out.println(error);
      error.printStackTrace();
    }
    return resultado;       
  }
  
  /**
  * Método SegundoReporteSecretario: Método que exporta el reporte de las hospitalizaciones registradas en el sistema al formato indicado por el usuario.
  */
  public void SegundoReporteSecretario(){
    if(vista.RSFormato.getSelectedItem().equals("CSV")){
      //REPORTE PARA FECHA 
      if(vista.RSD3.getDate() != null && vista.RSD4.getDate() != null){     
        reportes.exportarCSV(ReporteSecretarioCinco());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");
      }
      //REPORTE PARA ESPECIALIDAD  
      else if(vista.RST5.getText().length()!=0){
        reportes.exportarCSV(ReporteSecretarioSeis());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito"); 
      }
      //REPORTE PARA NOMBRE
      else if(vista.RST6.getText().length()!=0 && vista.RST7.getText().length()!=0 && vista.RST8.getText().length()!=0){
        reportes.exportarCSV(ReporteSecretarioSiete());
        JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");   
      }
    }
    else if(vista.RSFormato.getSelectedItem().equals("PDF")){
      //REPORTE PARA FECHA 
      if(vista.RSD3.getDate() != null && vista.RSD4.getDate() != null){     
        reportes.exportarPDF(ReporteSecretarioCinco());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");
      }
      //REPORTE PARA ESPECIALIDAD  
      else if(vista.RST5.getText().length()!=0){
        reportes.exportarPDF(ReporteSecretarioSeis());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito"); 
      }
      //REPORTE PARA NOMBRE
      else if(vista.RST6.getText().length()!=0 && vista.RST7.getText().length()!=0 && vista.RST8.getText().length()!=0){
        reportes.exportarPDF(ReporteSecretarioSiete());
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");   
      }
    } 
        if(vista.RSFormato.getSelectedItem().equals("HTML")){
      //REPORTE PARA FECHA 
      if(vista.RSD3.getDate() != null && vista.RSD4.getDate() != null){     
        reportes.exportarHTML(ReporteSecretarioCinco());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");
      }
      //REPORTE PARA ESPECIALIDAD  
      else if(vista.RST5.getText().length()!=0){
        reportes.exportarHTML(ReporteSecretarioSeis());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito"); 
      }
      //REPORTE PARA NOMBRE
      else if(vista.RST6.getText().length()!=0 && vista.RST7.getText().length()!=0 && vista.RST8.getText().length()!=0){
        reportes.exportarHTML(ReporteSecretarioSiete());
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");   
      }
    } 
  }
}//Fin de la clase Controlador
