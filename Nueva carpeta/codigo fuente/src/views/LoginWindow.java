package views;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import models.Stack;

import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class LoginWindow {

	private JFrame frmIniciarSesion;
	private JFrame originFrame;
	private Stack foro;
	private JTextField textFieldName;
	private JPasswordField passwordFieldPass;

	/**
	 * Construye un objeto de la clase LoginWindow con los atributos entregados.
	 * @param origin Frame de la ventana anterior a la ventana LoginWindow.
	 * @param foro Stack que contiene la informacion del foro.
	 */
	public LoginWindow(JFrame origin, Stack foro) {
		this.foro = foro;
		this.originFrame = origin;
		initialize();
	}

	/**
	 * Inicializa el frame con el contenido de la ventana LoginWindow.
	 */
	private void initialize() {
		frmIniciarSesion = new JFrame();
		frmIniciarSesion.getContentPane().setBackground(SystemColor.inactiveCaption);
		frmIniciarSesion.setTitle("Stack Overflow - Iniciar sesion");
		getFrame().setBounds(550, 250, 450, 300);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIniciarSesion.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Nombre de usuario: ");
		lblName.setBounds(47, 104, 148, 14);
		frmIniciarSesion.getContentPane().add(lblName);
		
		JLabel lblPass = new JLabel("Clave: ");
		lblPass.setBounds(47, 151, 102, 14);
		frmIniciarSesion.getContentPane().add(lblPass);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(205, 101, 126, 20);
		frmIniciarSesion.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		passwordFieldPass = new JPasswordField();
		passwordFieldPass.setBounds(205, 148, 126, 20);
		frmIniciarSesion.getContentPane().add(passwordFieldPass);
		
		JLabel lblNewLabel = new JLabel("Iniciar sesion");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 21, 414, 14);
		frmIniciarSesion.getContentPane().add(lblNewLabel);
		
		JButton btnLogin = new JButton("Iniciar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarSesionDelUsuario();	
			}
		});
		
		btnLogin.setBounds(335, 227, 89, 23);
		frmIniciarSesion.getContentPane().add(btnLogin);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnVolver.setBounds(10, 227, 89, 23);
		frmIniciarSesion.getContentPane().add(btnVolver);
		
		
	}

	/**
	 * Obtiene el frame de la ventana Login.
	 * @return frmIniciarSesion Retorna el frame de la ventana.
	 */
	JFrame getFrame() {
		return frmIniciarSesion;
	}

	/**
	 * Comprueba que no existan errores, inicia la sesion con los datos ingresados y crea la ventana del menu principal.
	 */
	private void iniciarSesionDelUsuario() {
		String name = textFieldName.getText();
		String pass = passwordFieldPass.getText();
		if(foro.getListaUsuarios().verificarLogin(name, pass)) {
			foro.setSesionIniciada(name);
			OptionView ventanaMenu = new OptionView(foro);
			ventanaMenu.getFrmMenuPrincipal().setVisible(true);
			frmIniciarSesion.setVisible(false);
			return;
		}
		JOptionPane.showMessageDialog(frmIniciarSesion, "Datos incorrectos o el usuario no existe.", "Datos invalidos", 0);
	}

	/**
	 * Vuelve a la ventana anterior a la ventana Login.
	 */
	private void volver() {
		frmIniciarSesion.setVisible(false);
		originFrame.setVisible(true);
	}
}
