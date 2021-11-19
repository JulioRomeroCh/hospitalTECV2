package controlador;

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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.Formulario;


public class controlador implements ActionListener{

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
    this.vista.BotonReportePaciente1.addActionListener(this);
    this.vista.BotonDoctorCargarCitas.addActionListener(this);
    this.vista.BotonDoctorAsignarCita.addActionListener(this);
    this.vista.BotonPacienteSolicitar.addActionListener(this);
    this.vista.BotonPacienteCancelar.addActionListener(this);
    this.vista.BotonMPCancelarCita.addActionListener(this);//CHECK THIS
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
    this.vista.BotonEnfermeroCancelarCita.addActionListener(this);
    this.vista.BotonEnfermeroCargarCitasPaciente.addActionListener(this);
    this.vista.BotonEnfermeroCancelarCita2.addActionListener(this);
    this.vista.BotonEnfermeroAsignarCita.addActionListener(this);
    this.vista.BotonEnfermeroAsignarCita2.addActionListener(this);
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
  
  
  public void iniciar(){
      
  }
  
  
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
          //for(int indice = 1; indice!= resultado.getMetaData().getColumnCount()+1; indice++){
            //System.err.println("SALIDA: " + resultado.getObject(indice)); 
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
          //}
        }  
        
      }
      catch(Exception error){ 
        System.out.println(error);
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
          int pIndice = Integer.parseInt(resultado.getObject(11).toString());
          int pCodigoSecretario= Integer.parseInt(resultado.getObject(12).toString());   
          secretarioActivo = new Secretario(pCedula, pNombre, pApellido1, pApellido2, pRol, pNombreUsuario, pContraseña, pIdentificadorFuncionario, pTipo, pFechaIngreso, pIndice, pCodigoSecretario);
        }   
      }
      catch(Exception error){ 
        System.out.println(error);
      }    
    }
  }
 
  
  @Override
  public void actionPerformed(ActionEvent evento){
    if(evento.getSource() == vista.BotonReportePaciente1){
      PrimerRegistroPaciente();
    }  
    //INICIO
    else if (evento.getSource() == vista.BotonIniciarSesion){
      usuarioActual(vista.TextoUsuario.getText(), vista.ComboRolInicioSesion.getSelectedItem().toString());
      //DEBERÍA MOVER ESTO A OTRO BOTÓN
      cargarDoctorComboBox(vista.ComboDoctorCitasCanceladasCM, vista.ComboDoctorPacienteCitaAsignada, vista.ComboDoctorCitasRA);
      cargarDoctorCitasRAAtender(vista.ComboDoctorCitasRA);
    }
    else if(evento.getSource() == vista.BotonMDAsignarCita){
      cargarDoctorComboBox(vista.ComboDoctorCitasCanceladasCM, vista.ComboDoctorPacienteCitaAsignada, vista.ComboDoctorCitasRA);   
    }
    else if(evento.getSource() == vista.BotonEnfermeroCancelarCita){
        cargaEnfermeroCancelarComboBox(vista.ComboEnfermeroPacienteCitaAsignada);
    }
    else if(evento.getSource() == vista.BotonEnfermeroAsignarCita){
        cargarEnfermeroAsignarComboBox(vista.ComboEnfermeroCitasAsignar);
    }

    else if(evento.getSource() == vista.BotonAdminRegistrarSecretario){
      Secretario nuevoSecretario = new Secretario(vista.TextSecretarioCedula.getText(), vista.TextSecretarioNombre.getText(), vista.TextSecretarioApellido1.getText(), vista.TextSecretarioApellido2.getText(), "Secretario", vista.TextSecretarioUsuario.getText(), vista.TextSecretarioContraseña.getText(), Integer.parseInt(vista.TextSecretarioIDFuncionario.getText()), TipoFuncionario.SECRETARIO, vista.DateSecretarioFechaIngreso.getDate(), vista.ComboSecretarioArea.getText(), Integer.parseInt(vista.TextSecretarioCodigo.getText()));
      usuarios.add(nuevoSecretario);
      funcionarios.add(nuevoSecretario);
      secretarios.add(nuevoSecretario);
      nuevoSecretario.insertarSecretario(vista.TextSecretarioCedula.getText(), vista.TextSecretarioNombre.getText(), vista.TextSecretarioApellido1.getText(), vista.TextSecretarioApellido2.getText(), "Secretario", vista.TextSecretarioUsuario.getText(), vista.TextSecretarioContraseña.getText(), Integer.parseInt(vista.TextSecretarioIDFuncionario.getText()), TipoFuncionario.SECRETARIO, vista.DateSecretarioFechaIngreso.getDate(), vista.ComboSecretarioArea.getText(), Integer.parseInt(vista.TextSecretarioCodigo.getText()));
      JOptionPane.showMessageDialog(null, "Secretario registrado con éxito");
    }
    else if(evento.getSource() == vista.BotonAdminConsultaBitacora){
     cargarCitaComboBox(vista.ComboAdminCitaConsultarBitacora);   
    }
    else if(evento.getSource() == vista.BotonDoctorCargarCitas){
        ////////////////////
      cargarDoctorCitasRACancelar(vista.ComboDoctorCitas, Integer.parseInt(vista.ComboDoctorPacienteCitaAsignada.getSelectedItem().toString()));    
    }
    else if(evento.getSource() == vista.BotonEnfermeroCargarCitasPaciente){
        cargarEnfermeroCitasRACancelar(vista.ComboEnfermeroCitaCancelar, Integer.parseInt(vista.ComboEnfermeroPacienteCitaAsignada.getSelectedItem().toString()));
    }
    else if(evento.getSource() == vista.BotonRegistrarCentro){
      cargarTipoCentroComboBox(vista.ComboAdminTipoCentro);   
    }
    //*************************
    else if(evento.getSource() == vista.BotonRegistrarDoctor){
      Doctor nuevoDoctor = new Doctor(vista.TextCedulaDoctor.getText(), vista.TextNombreDoctor.getText(), vista.TextApellido1Doctor.getText(), vista.TextApellido2Doctor.getText(), "Doctor", vista.TextUsuarioDoctor.getText(), vista.TextContraeñaDoctor.getText(), Integer.parseInt(vista.TextIDFuncionarioDoctor.getText()), TipoFuncionario.DOCTOR, vista.CalendarioDoctor.getDate(), vista.TextAreaDoctor.getText(), Integer.parseInt(vista.TextCodigoDoctor2.getText()));
      usuarios.add(nuevoDoctor);
      funcionarios.add(nuevoDoctor);
      doctores.add(nuevoDoctor);
      nuevoDoctor.insertarDoctor(vista.TextCedulaDoctor.getText(), vista.TextNombreDoctor.getText(), vista.TextApellido1Doctor.getText(), vista.TextApellido2Doctor.getText(), "Doctor", vista.TextUsuarioDoctor.getText(), vista.TextContraeñaDoctor.getText(), Integer.parseInt(vista.TextIDFuncionarioDoctor.getText()), TipoFuncionario.DOCTOR, vista.CalendarioDoctor.getDate(), vista.TextAreaDoctor.getText(), Integer.parseInt(vista.TextCodigoDoctor2.getText()));
      JOptionPane.showMessageDialog(null, "Doctor registrado con éxito");
    }
    else if(evento.getSource() == vista.BotonEspecialidadDoctor){
      buscarDoctor(Integer.parseInt(vista.TextCodigoDoctor2.getText())).insertarEspecialidad(Integer.parseInt(vista.TextCodigoDoctor2.getText()), vista.TextEspecialidadDoctor.getText());
      buscarDoctor(Integer.parseInt(vista.TextCodigoDoctor2.getText())).añadirEspecialidad(vista.TextEspecialidadDoctor.getText());
      JOptionPane.showMessageDialog(null, "Especialidad asociada con éxito");
    }
    //***********************
    else if(evento.getSource() == vista.BotonRegistrarPaciente){
      Paciente nuevoPaciente = new Paciente(vista.TextCedulaPaciente.getText(), vista.TextNombrePaciente.getText(), vista.TextApellido1Paciente.getText(), vista.TextApellido2Paciente.getText(), "Paciente", vista.TextUsuarioPaciente.getText(), vista.TextContraeñaPaciente.getText(), Integer.parseInt(vista.TextIDPaciente1.getText()), vista.TextNacionalidadPaciente.getText(), vista.TextResidenciaPaciente.getText(), vista.DateNacimientoPaciente.getDate(), TipoSangre.valueOf(vista.BoxSangrePaciente.getSelectedItem().toString()));
      usuarios.add(nuevoPaciente);
      pacientes.add(nuevoPaciente);
      nuevoPaciente.insertarPaciente(vista.TextCedulaPaciente.getText(), vista.TextNombrePaciente.getText(), vista.TextApellido1Paciente.getText(), vista.TextApellido2Paciente.getText(), "Paciente", vista.TextUsuarioPaciente.getText(), vista.TextContraeñaPaciente.getText(), Integer.parseInt(vista.TextIDPaciente1.getText()), vista.TextNacionalidadPaciente.getText(), vista.TextResidenciaPaciente.getText(), vista.DateNacimientoPaciente.getDate(), TipoSangre.valueOf(vista.BoxSangrePaciente.getSelectedItem().toString()));
      JOptionPane.showMessageDialog(null, "Paciente registrado con éxito");
    }
    else if(evento.getSource() == vista.BotonTelefonoPaciente){
        buscarPaciente(Integer.parseInt(vista.TextIDPaciente1.getText())).insertarTelefono(vista.TextTelefonoPaciente.getText());
        buscarPaciente(Integer.parseInt(vista.TextIDPaciente1.getText())).setTelefono(vista.TextTelefonoPaciente.getText());
        JOptionPane.showMessageDialog(null, "Teléfono asociado con éxito");
    }
    else if(evento.getSource() == vista.BotonRegistrarEnfermero){
      Enfermero nuevoEnfermero = new Enfermero(vista.TextCedulaEnfermero.getText(), vista.TextNombreEnfermero.getText(), vista.TextApellido1Enfermero.getText(), vista.TextApellido2Enfermero.getText(), "Enfermero", vista.TextUsuarioEnfermero.getText(), vista.TextContraeñaEnfermero.getText(), Integer.parseInt(vista.TextIDFuncionarioEnfermero.getText()), TipoFuncionario.ENFERMERO, vista.CalendarioEnfermero.getDate(), vista.TextAreaEnfermero.getText(), Integer.parseInt(vista.TextCodigoEnfermero.getText()));
      usuarios.add(nuevoEnfermero);
      funcionarios.add(nuevoEnfermero);
      enfermeros.add(nuevoEnfermero);
      nuevoEnfermero.insertarEnfermero(vista.TextCedulaEnfermero.getText(), vista.TextNombreEnfermero.getText(), vista.TextApellido1Enfermero.getText(), vista.TextApellido2Enfermero.getText(), "Enfermero", vista.TextUsuarioEnfermero.getText(), vista.TextContraeñaEnfermero.getText(), Integer.parseInt(vista.TextIDFuncionarioEnfermero.getText()), TipoFuncionario.ENFERMERO, vista.CalendarioEnfermero.getDate(), vista.TextAreaEnfermero.getText(), Integer.parseInt(vista.TextCodigoEnfermero.getText()), vista.BoxPersonasEnfermero.getSelectedItem().toString(), vista.BoxExperienciaEnfermero.getSelectedItem().toString());
      JOptionPane.showMessageDialog(null, "Enfermero registrado con éxito");
    }
    else if(evento.getSource() == vista.BotonAdminConsultarBitacora){
      consultaBitacora(vista.tablaConsultaBitacora, Integer.parseInt(vista.ComboAdminCitaConsultarBitacora.getSelectedItem().toString()));
    }
    else if(evento.getSource() == vista.BotonDoctorHospitalizarPositivo){
        cargarHospitalizacionComboBox(vista.ComboTratamientoHospitalizar, vista.ComboCentroHospitalizar, vista.ComboDoctorDiagnostico.getSelectedItem().toString());
    }
    else if(evento.getSource() == vista.BotonRegistrarTipo){
      CentroAtencion centroTemporal = new CentroAtencion();
      centroTemporal.insertarTipoCentro(Integer.parseInt(vista.TextAdminIDTipo.getText()), vista.TextAdminTipo.getText());
      JOptionPane.showMessageDialog(null, "Tipo centro registrado con éxito");
    }
    else if(evento.getSource() == vista.BotonAdminRegistrarCentro){
      CentroAtencion centroTemporal = new CentroAtencion();
      centroTemporal.insertarCentro(vista.TextAdminNombreCentro.getText(), vista.TextAdminLugarCentro.getText(), Integer.parseInt(vista.TextAdminCapacidadCentro.getText()), Integer.parseInt(vista.ComboAdminTipoCentro.getSelectedItem().toString()));
      JOptionPane.showMessageDialog(null, "Centro médico registrado con éxito");
    }
    else if(evento.getSource() == vista.BotonAdminRegistrarTratamiento){
      Tratamiento tratamientoTemporal = new Tratamiento(vista.TextAdminNombreTratamiento.getText(), vista.TextAdminTipoTratamiento.getText(), Integer.parseInt(vista.TextAdminDosisTratamiento.getText()));
      tratamientoTemporal.insertarTratamiento(vista.TextAdminNombreTratamiento.getText(), vista.TextAdminTipoTratamiento.getText(), Integer.parseInt(vista.TextAdminDosisTratamiento.getText()));
      Diagnostico diagnosticoTemporal = buscarDiagnostico(vista.ComboAdminDiagnostico.getSelectedItem().toString());
      diagnosticoTemporal.insertarDiagnosticoTratamiento(tratamientoTemporal);
      JOptionPane.showMessageDialog(null, "Tratamiento registrado y asociado con éxito");
    }
    else if(evento.getSource() == vista.BotonTratamientoAdmin){
        cargarNombreDiagnosticosComboBox(vista.ComboAdminDiagnostico);
    }
    else if(evento.getSource() == vista.BotonRegistrarDiagnostico){
      Diagnostico diagnosticoTemporal = new Diagnostico();
      diagnosticoTemporal.insertarDiagnostico(vista.TextAdminNombreDiagnostico.getText(), NivelDiagnostico.valueOf(vista.ComboAdminNivelDiagnostico.getSelectedItem().toString()));
      JOptionPane.showMessageDialog(null, "Diagnóstico registrado con éxito");
    }
    else if(evento.getSource() == vista.BotonDoctorHospitalizarNegativo){
      JOptionPane.showMessageDialog(null, "El paciente no será hospitalizado");    
    }
    else if(evento.getSource() == vista.BotonMDAtenderCita){
      cargarNombreDiagnostico(vista.ComboDoctorDiagnostico);
    }
    else if(evento.getSource() == vista.BotonVacunarEnfermero){
      try {
        enfermeroActivo.vacunar(buscarPaciente(Integer.parseInt(vista.ComboPacienteVacunar.getSelectedItem().toString())), buscarVacuna(Integer.parseInt(vista.ComboNumeroLoteVacunar.getSelectedItem().toString())));
      } 
      catch (ParseException ex) {
        Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    else if(evento.getSource() == vista.BotonVacunar){
      cargarPacienteVacunaComboBox(vista.ComboPacienteVacunar);
      cargarNumerLoteComboBox(vista.ComboNumeroLoteVacunar);
    }
    else if(evento.getSource() == vista.BotonDoctorAtenderCita){
      doctorActivo.atenderCita(buscarCita(Integer.parseInt(vista.ComboDoctorCitasRA.getSelectedItem().toString())));
      //System.out.println("LO QUE BUSCA: " + buscarCita(Integer.parseInt(vista.ComboDoctorCitasRA.getSelectedItem().toString())).toString());
      JOptionPane.showMessageDialog(null, "Paciente atentido con éxito");
    }
    else if(evento.getSource() == vista.BotonDoctorHospitalizar){
      doctorActivo.hospitalizar(Integer.parseInt(vista.TextIDHospitalizacion.getText()), vista.FechaHospitalizacion.getDate(), buscarCita(Integer.parseInt(vista.ComboDoctorCitasRA.getSelectedItem().toString())), buscarCentro(Integer.parseInt(vista.ComboCentroHospitalizar.getSelectedItem().toString())), vista.TextObservaciónHospitalizacion.getText(), buscarTratamiento(vista.ComboTratamientoHospitalizar.getSelectedItem().toString()));
      JOptionPane.showMessageDialog(null, "El paciente será hospitalizado");
    }
    else if(evento.getSource() == vista.BotonDoctorCargarPaciente){
        cargarIDPaciente(Integer.parseInt(vista.ComboDoctorCitasRA.getSelectedItem().toString()), vista.ComboDoctorIDPaciente);
    }
    else if(evento.getSource() == vista.BotonDoctorCargarTratamiento){
      cargarTratamiento(vista.ComboDoctorDiagnostico.getSelectedItem().toString(), vista.ComboDoctorTratamiento);          
    }
    else if(evento.getSource() == vista.BotonDoctorInsertarDiagnostico){
      doctorActivo.añadirObservacionCita(buscarCita(Integer.parseInt(vista.ComboDoctorCitasRA.getSelectedItem().toString())),buscarDiagnostico(vista.ComboDoctorDiagnostico.getSelectedItem().toString()), vista.TextDoctorObservacion.getText(), buscarTratamiento(vista.ComboDoctorTratamiento.getSelectedItem().toString()));
      JOptionPane.showMessageDialog(null, doctorActivo.getNombre() + ": diagnóstico registrado con éxito"); 
    }
    else if(evento.getSource() == vista.BotonDoctorCancelarCita){
      CancelarCitaDoctor(Integer.parseInt(vista.ComboDoctorCitas.getSelectedItem().toString()));
    }
    else if(evento.getSource() == vista.BotonEnfermeroCancelarCita2){
      CancelarCitaEnfermero(Integer.parseInt(vista.ComboEnfermeroCitaCancelar.getSelectedItem().toString()));
    }
    else if(evento.getSource() == vista.BotonDoctorAsignarCita){
      doctorActivo.asignarCita(buscarCita(Integer.parseInt(vista.ComboDoctorCitasCanceladasCM.getSelectedItem().toString())));
      JOptionPane.showMessageDialog(null, "Cita asignada con éxito");
    }
    else if(evento.getSource() == vista.BotonEnfermeroAsignarCita2){
      enfermeroActivo.asignarCita(buscarCita(Integer.parseInt(vista.ComboEnfermeroCitasAsignar.getSelectedItem().toString())));
      JOptionPane.showMessageDialog(null, "Cita asignada con éxito");      
    }
    else if(evento.getSource() == vista.BotonMPCancelarCita){
        cargarCitasPendientes(vista.ComboPacienteCitasPendientes, pacienteActivo.getNombreUsuario());
    }
    else if(evento.getSource() == vista.BotonPacienteCancelar){
      //SELECT fecha, hora FROM cita WHERE identificadorCita = '1'
      Lista<Date> fechayhora = ObtenerFechayHora(Integer.parseInt(vista.ComboPacienteCitasPendientes.getSelectedItem().toString()));
      Date fechaCita = fechayhora.get(0);
      Date horaCita = fechayhora.get(1);
      System.err.println("????: " + new Date().before(fechaCita));
      System.err.println("FECHA ACTUAL: " + new Date());
      System.err.println("FECHA CITA: " + fechaCita);
      System.err.println("HORA ACTUAL: " + new Date().getHours());
      System.err.println("HORA CITA: " + horaCita.getHours());
      System.err.println("MINUTO ACTUAL: " + new Date().getMinutes());
      System.err.println("MINUTO CITA: " + horaCita.getMinutes());
      if(new Date().before(fechaCita) == false){  
        if(new Date().getHours() <= horaCita.getHours()){
          if(new Date().getMinutes() != horaCita.getMinutes()){
            pacienteActivo.cancelarCita(Integer.parseInt(vista.ComboPacienteCitasPendientes.getSelectedItem().toString()));
            JOptionPane.showMessageDialog(null, "Cita cancelada con éxito");
          }
        }
      }
      else{
        JOptionPane.showMessageDialog(null, "Se presentó un error, la cita está programada para dentro de las próximas 24 horas");    
      }
    
    }
    else if(evento.getSource() == vista.BotonPacienteSolicitar){
      SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");      
        try {
          pacienteActivo.solicitarCita(Integer.parseInt(vista.TextPacienteIDCitaSolicitar.getText()), vista.DatePacienteFecha.getDate(), formato.parse(vista.ComboDoctorHora.getSelectedItem().toString()), vista.TextPacienteEspecialidad.getText(),vista.TextPacienteObservacion.getText(), EstadoCita.REGISTRADA);
          Cita nuevaCita = new Cita(Integer.parseInt(vista.TextPacienteIDCitaSolicitar.getText()), vista.DatePacienteFecha.getDate(), formato.parse(vista.ComboDoctorHora.getSelectedItem().toString()), vista.TextPacienteEspecialidad.getText(),vista.TextPacienteObservacion.getText(), EstadoCita.REGISTRADA);
          Bitacora nuevaBitacora = new Bitacora(Integer.parseInt(vista.TextPacienteIDCitaSolicitar.getText()));
          nuevaCita.setBitacora(nuevaBitacora);
          citas.add(nuevaCita);
        } 
        catch (ParseException ex) {
          Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
      JOptionPane.showMessageDialog(null, pacienteActivo.getNombre() + ": Gracias por utilizar nuestros servicios, su cita ha sido registrada con éxito");
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
  
  public void CancelarCitaDoctor(int pIdentificadorCita){
    doctorActivo.cancelarCita(pIdentificadorCita);
    Cita nuevaCita = buscarCita(pIdentificadorCita);
    nuevaCita.setEstado(EstadoCita.CANCELADA_POR_CENTRO_MEDICO);
    nuevaCita.modificarEstadoCita(pIdentificadorCita, EstadoCita.CANCELADA_POR_CENTRO_MEDICO);
    nuevaCita.getBitacora().insertarBitacoraCitaEstado(pIdentificadorCita, EstadoCita.CANCELADA_POR_CENTRO_MEDICO);
    nuevaCita.getBitacora().insertarBitacoraFechayHora(pIdentificadorCita);
    JOptionPane.showMessageDialog(null, doctorActivo.getNombre() + ": cita cancelada con éxito");
  }
  
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
   * ----------------------------------------------------------------CARGA JCOMBOBOX------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA JCOMBOBOX------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA JCOMBOBOX------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA JCOMBOBOX------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA JCOMBOBOX------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA JCOMBOBOX------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA JCOMBOBOX------------------------------------------------------------------------------------------
   * ----------------------------------------------------------------CARGA JCOMBOBOX------------------------------------------------------------------------------------------
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
  
   public void cargaEnfermeroCancelarComboBox(JComboBox ComboEnfermeroPacienteCitaAsignada){
    ResultSet resultadoDos;
    PreparedStatement consultaDos;
    Conexion nuevaConexion = new Conexion();
    Connection conectar = nuevaConexion.conectar();
    ComboEnfermeroPacienteCitaAsignada.removeAllItems();
    try{
      consultaDos = conectar.prepareStatement("SELECT DISTINCT paciente.identificadorPaciente FROM paciente JOIN citas_paciente ON paciente.identificadorPaciente = citas_paciente.identificadorPaciente JOIN cita ON citas_paciente.identificadorCita = cita.identificadorCita WHERE cita.estado = 'REGISTRADA'");
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
        //System.out.println(error);    
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
  
  int codigopaciente = 15;
  
  public void PrimerRegistroPaciente(){
        ResultSet resultado = null;
        PreparedStatement consultaUno;
        Conexion nuevaConexion = new Conexion();
        Connection conectar = nuevaConexion.conectar();    
      
        try{
          consultaUno = conectar.prepareStatement("SELECT cita.identificadorCita, fecha, hora, area, observacion FROM cita JOIN citas_paciente\n" +
              "ON cita.identificadorCita = citas_paciente.identificadorCita WHERE identificadorPaciente = "+codigopaciente+"\n" +
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
          resultado = consultaUno.executeQuery(); 
        }
        catch(Exception error){ 
          System.out.println(error);
        }
    if(vista.ReportesPacienteFormato.getSelectedItem().equals("CSV")){
      //REPORTE PARA FECHAS 
      if(vista.ReportesPacienteFecha1.getDate() != null && vista.ReportesPacienteFecha2.getDate() != null && vista.ReportesPacienteEspecialidad1.getText().equals("") && vista. ReportesPacienteEstado1.getSelectedItem().equals("")){
          reportes.exportarCSV(resultado);
          JOptionPane.showMessageDialog(null, "Reporte CSV creado con éxito");
      }
      //REPORTE PARA ESPECIALIDAD
      else if(vista.ReportesPacienteFecha1.getDate()  == null && vista.ReportesPacienteFecha2.getDate()  == null && vista.ReportesPacienteEspecialidad1.getText() != null && vista. ReportesPacienteEstado1.getSelectedItem() == null){
       
      }  
      //REPORTE PARA ESTADO
      else if(vista.ReportesPacienteFecha1.getDate()  == null && vista.ReportesPacienteFecha2.getDate()  == null && vista.ReportesPacienteEspecialidad1.getText() == null && vista. ReportesPacienteEstado1.getSelectedItem() != null){
        
      }      
    }
    
    else if(vista.ReportesPacienteFormato.getSelectedItem().equals("PDF")){
        //REPORTE PARA FECHAS 
      if(vista.ReportesPacienteFecha1.getDate() != null && vista.ReportesPacienteFecha2.getDate() != null && vista.ReportesPacienteEspecialidad1.getText().equals("") && vista. ReportesPacienteEstado1.getSelectedItem().equals("")){
        reportes.exportarPDF(resultado);
        JOptionPane.showMessageDialog(null, "Reporte PDF creado con éxito");
      }
      //REPORTE PARA ESPECIALIDAD
      else if(vista.ReportesPacienteFecha1.getDate()  == null && vista.ReportesPacienteFecha2.getDate()  == null && vista.ReportesPacienteEspecialidad1.getText() != null && vista. ReportesPacienteEstado1.getSelectedItem() == null){
       
      }  
      //REPORTE PARA ESTADO
      else if(vista.ReportesPacienteFecha1.getDate()  == null && vista.ReportesPacienteFecha2.getDate()  == null && vista.ReportesPacienteEspecialidad1.getText() == null && vista. ReportesPacienteEstado1.getSelectedItem() != null){
        
      }      
    }
    
    else if(vista.ReportesPacienteFormato.getSelectedItem().equals("HTML")){
       //REPORTE PARA FECHAS 
      if(vista.ReportesPacienteFecha1.getDate() != null && vista.ReportesPacienteFecha2.getDate() != null && vista.ReportesPacienteEspecialidad1.getText().equals("") && vista. ReportesPacienteEstado1.getSelectedItem().equals("")){
        reportes.exportarHTML(resultado);
        JOptionPane.showMessageDialog(null, "Reporte HTML creado con éxito");
      }
      //REPORTE PARA ESPECIALIDAD
      else if(vista.ReportesPacienteFecha1.getDate()  == null && vista.ReportesPacienteFecha2.getDate()  == null && vista.ReportesPacienteEspecialidad1.getText() != null && vista. ReportesPacienteEstado1.getSelectedItem() == null){
       
      }  
      //REPORTE PARA ESTADO
      else if(vista.ReportesPacienteFecha1.getDate()  == null && vista.ReportesPacienteFecha2.getDate()  == null && vista.ReportesPacienteEspecialidad1.getText() == null && vista. ReportesPacienteEstado1.getSelectedItem() != null){
        
      }      
    }
  }
  
  //ADELANTE COMPAÑEROS, QUE SE NOTE EL TRI-PROGRAMMING
  
  
    
}
