package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import models.Stack;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class PrincipalView {

	private JFrame frmPrincipalView;
	private Stack foro;

	/**
	 * Construye un objeto de la clase PrincipalView con los atributos entregados.
	 * @param foro Stack que contiene la informacion del foro.
	 */
	public PrincipalView(Stack foro) {
		this.foro = foro;
		initialize();
	}

	/**
	 * Inicializa el frame con el contenido de la ventana PrincipalView
	 */
	private void initialize() {
		frmPrincipalView = new JFrame();
		frmPrincipalView.setTitle("Stack Overflow - Inicio");
		frmPrincipalView.getContentPane().setBackground(SystemColor.inactiveCaption);
		getFrame().setBounds(550, 250, 450, 300);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipalView.getContentPane().setLayout(null);
		JLabel lblWelcome = new JLabel("Bienvenido a Stack Overflow");
		lblWelcome.setBounds(0, 38, 434, 34);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 28));
		frmPrincipalView.getContentPane().add(lblWelcome);
		
		JButton btnRegister = new JButton("Nuevo usuario");
		btnRegister.setToolTipText("Stack Overflow");
		
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		btnRegister.setBackground(UIManager.getColor("Button.background"));
		btnRegister.setBounds(54, 159, 130, 23);
		frmPrincipalView.getContentPane().add(btnRegister);
		
		JButton btnLogin = new JButton("Iniciar sesion");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnLogin.setBackground(UIManager.getColor("Button.background"));
		btnLogin.setBounds(272, 159, 130, 23);
		frmPrincipalView.getContentPane().add(btnLogin);
		
		JButton btnInvitado = new JButton("Invitado");
		btnInvitado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ingresarComoInvitado();
			}
		});
		btnInvitado.setBackground(UIManager.getColor("Button.background"));
		btnInvitado.setBounds(182, 203, 89, 23);
		frmPrincipalView.getContentPane().add(btnInvitado);
	}

	/**
	 * Obtiene el frame de la ventana PrincipalView.
	 * @return frmPrincipalView Retorna el frame de la ventana.
	 */
	public JFrame getFrame() {
		return frmPrincipalView;
	}

	/**
	 * Crea la ventana para resgistrar a un nuevo usuario en el foro y la hace visible.
	 */
	private void register() {
		RegisterWindow ventanaRegister = new RegisterWindow(frmPrincipalView, foro);
		frmPrincipalView.setVisible(false);
		ventanaRegister.getFrame().setVisible(true);
	}

	/**
	 * Crea una ventana para iniciar la sesion de un usuario previamente registrado y la hace visible.
	 */
	private void login() {
		LoginWindow ventanaLogin = new LoginWindow(frmPrincipalView, foro);
		frmPrincipalView.setVisible(false);
		ventanaLogin.getFrame().setVisible(true);
	}

	/**
	 * Crea la ventana del menu principal e ingresa al usuario como un invitado, permitiendole solo ver el foro.
	 */
	private void ingresarComoInvitado() {
		OptionView ventanaMenu = new OptionView(foro);
		ventanaMenu.getFrmMenuPrincipal().setVisible(true);
		frmPrincipalView.setVisible(false);
	}
}
