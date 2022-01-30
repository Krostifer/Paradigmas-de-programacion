package views;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import models.Stack;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.SystemColor;

public class RegisterWindow{

	private JFrame frmRegistrarUsuario;
	private Stack foro;
	private JFrame originFrame;
	private JTextField textFieldName;
	private JPasswordField passwordFieldPass;
	
	
	/**
	 * Construye un objeto de la clase RegisterView con los atributos entregados.
	 * @param origin Frame de la ventana anterior a la ventana RegisterWindow.
	 * @param foro Stack que contiene la informacion del foro.
	 */
	public RegisterWindow(JFrame origin, Stack foro) {
		this.foro = foro;
		this.originFrame = origin;
		initialize();
	}

	/**
	 * Inicializa el frame con el contenido de la ventana RegisterWindow.
	 */
	private void initialize() {
		frmRegistrarUsuario = new JFrame();
		frmRegistrarUsuario.getContentPane().setBackground(SystemColor.inactiveCaption);
		frmRegistrarUsuario.setTitle("Stack Overflow - Registrar usuario");
		
		getFrame().setBounds(550, 250, 450, 300);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistrarUsuario.getContentPane().setLayout(null);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(193, 76, 129, 20);
		frmRegistrarUsuario.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblName = new JLabel("Nombre de usuario:");
		lblName.setBounds(31, 79, 129, 14);
		frmRegistrarUsuario.getContentPane().add(lblName);
		
		JLabel lblPass = new JLabel("Clave: ");
		lblPass.setBounds(31, 135, 104, 14);
		frmRegistrarUsuario.getContentPane().add(lblPass);
		
		passwordFieldPass = new JPasswordField();
		passwordFieldPass.setBounds(193, 132, 129, 20);
		frmRegistrarUsuario.getContentPane().add(passwordFieldPass);
		
		JLabel lblNewLabel = new JLabel("Registro");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(181, 11, 74, 31);
		frmRegistrarUsuario.getContentPane().add(lblNewLabel);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarUsuario();
			}
		});
		
		btnRegistrarse.setBounds(312, 216, 112, 23);
		frmRegistrarUsuario.getContentPane().add(btnRegistrarse);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnVolver.setBounds(10, 216, 89, 23);
		frmRegistrarUsuario.getContentPane().add(btnVolver);
		
		
	}

	/**
	 * Obtiene el frame de la ventana RegisterWindow.
	 * @return frmRegistrarUsuario Retorna el frame de la ventana.
	 */
	public JFrame getFrame() {
		return frmRegistrarUsuario;
	}

	/**
	 * Comprueba que no existan errores, registrar al nuevo usuario en el foro, crea la ventana con el menu principal y la hace visible.
	 */
	private void registrarUsuario() {
		String name = textFieldName.getText();
		String pass = passwordFieldPass.getText();
		
		if(name.isBlank() || name == null || pass.isBlank() || pass == null) {
			JOptionPane.showMessageDialog(frmRegistrarUsuario, "Datos ingresados invalidos.", "Datos invalidos", 0);
			return;
		}
		
		if(foro.getListaUsuarios().usuarioUnico(name) == false) {
			JOptionPane.showMessageDialog(frmRegistrarUsuario, "El nombre de usuario ya esta registrado, favor ingresar otro.", "Datos invalidos", 0);
			return;
		}
		
		foro.register(name, pass);
		foro.setSesionIniciada(name);
		OptionView ventanaMenu = new OptionView(foro);
		ventanaMenu.getFrmMenuPrincipal().setVisible(true);
		JOptionPane.showMessageDialog(ventanaMenu.getFrmMenuPrincipal(), "Se ha registrado al nuevo usuario exitosamente.", "Accion ejecutada correctemente", 1);
		frmRegistrarUsuario.setVisible(false);
	}

	/**
	 * Vuelve a la ventana anterior a la ventana RegisterWindow.
	 */
	private void volver() {
		frmRegistrarUsuario.setVisible(false);
		originFrame.setVisible(true);
	}
}
