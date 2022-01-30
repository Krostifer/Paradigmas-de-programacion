package views;


import javax.swing.JFrame;
import javax.swing.SwingConstants;

import models.Stack;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.Color;

public class OptionView{

	private JFrame frmMenuPrincipal;
	private Stack foro;
	
	/**
	 * Construye un objeto de la clase OptionView con los atributos etregados.
	 * @param foro Stack que contiene la informacion del foro.
	 */
	public OptionView(Stack foro) {
		this.foro = foro;
		initialize();
	}

	/**
	 * Inicializa el frame con el contenido de la ventana OptionView.
	 */
	private void initialize() {
		frmMenuPrincipal = new JFrame();
		frmMenuPrincipal.getContentPane().setBackground(SystemColor.inactiveCaption);
		getFrmMenuPrincipal().setTitle("Menu principal");
		getFrmMenuPrincipal().setBounds(550, 250, 755, 554);
		getFrmMenuPrincipal().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmMenuPrincipal().getContentPane().setLayout(null);
		
		JMenuItem menuItemAsk = new JMenuItem("Publicar una pregunta");
		menuItemAsk.setBackground(UIManager.getColor("Button.background"));
		menuItemAsk.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblLogin = new JLabel("Bienvenido: " + foro.getSesionIniciada());
		lblLogin.setBounds(27, 11, 246, 14);
		frmMenuPrincipal.getContentPane().add(lblLogin);
		
		
		//Presionar la opcion para publicar una pregunta
		menuItemAsk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuAsk();
			}
		});
		menuItemAsk.setBounds(10, 397, 174, 26);
		getFrmMenuPrincipal().getContentPane().add(menuItemAsk);
		
		//Presionar la opcion para responder una pregunta
		JMenuItem menuItemAnswer = new JMenuItem("Responder una pregunta");
		menuItemAnswer.setBackground(UIManager.getColor("Button.background"));
		menuItemAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuAnswer();
			}
		});
		menuItemAnswer.setBounds(10, 434, 174, 26);
		getFrmMenuPrincipal().getContentPane().add(menuItemAnswer);
		
		//Ofrecer una recompensa
		JMenuItem menuItemReward = new JMenuItem("Ofrecer recompensa");
		menuItemReward.setBackground(UIManager.getColor("Button.background"));
		menuItemReward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuReward();
			}
		});
		menuItemReward.setBounds(253, 396, 200, 26);
		getFrmMenuPrincipal().getContentPane().add(menuItemReward);
		
		JMenuItem menuItemAccept = new JMenuItem("Aceptar una respuesta");
		menuItemAccept.setBackground(UIManager.getColor("Button.background"));
		menuItemAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuAccept();
			}
		});
		menuItemAccept.setBounds(253, 434, 200, 26);
		getFrmMenuPrincipal().getContentPane().add(menuItemAccept);
		
		JMenuItem menuItemVote = new JMenuItem("Votar una pregunta o respuesta");
		menuItemVote.setBackground(UIManager.getColor("Button.background"));
		menuItemVote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuVote();
			}
		});
		menuItemVote.setBounds(519, 396, 210, 26);
		getFrmMenuPrincipal().getContentPane().add(menuItemVote);
		
		JMenuItem menuItemNewTag = new JMenuItem("Agregar una nueva etiqueta");
		menuItemNewTag.setBackground(UIManager.getColor("Button.background"));
		menuItemNewTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuNewLabel();
			}
		});
		menuItemNewTag.setBounds(519, 434, 210, 26);
		frmMenuPrincipal.getContentPane().add(menuItemNewTag);
		
		
		JButton btnLogout = new JButton("Cerrar sesion");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		btnLogout.setBackground(new Color(255, 153, 153));
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnLogout.setBounds(626, 11, 103, 21);
		frmMenuPrincipal.getContentPane().add(btnLogout);
		
		if(foro.getSesionIniciada() == null) {
			lblLogin.setText("Bienvenido: Invitado");
			menuItemAsk.setEnabled(false);
			menuItemAnswer.setEnabled(false);
			menuItemReward.setEnabled(false);
			menuItemAccept.setEnabled(false);
			menuItemVote.setEnabled(false);
			menuItemNewTag.setEnabled(false);
		}
		
		JLabel lblTitulo = new JLabel("Stack Overflow");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblTitulo.setBounds(10, 36, 692, 30);
		frmMenuPrincipal.getContentPane().add(lblTitulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 77, 719, 301);
		frmMenuPrincipal.getContentPane().add(scrollPane);
		
		
		JTextArea textAreaForo = new JTextArea();
		textAreaForo.setWrapStyleWord(true);
		textAreaForo.setLineWrap(true);
		textAreaForo.setEditable(false);
		scrollPane.setViewportView(textAreaForo);
		textAreaForo.setText(foro.toString());
		textAreaForo.setSelectionStart(0);
		textAreaForo.setSelectionEnd(0);
		
		
	} 

	/**
	 * Obtiene el frame de la ventana OptionView.
	 * @return frmMenuPrincipal Retorna el frame de la ventana.
	 */
	public JFrame getFrmMenuPrincipal() {
		return frmMenuPrincipal;
	}

	/**
	 * Crea la ventana para publicar una pregunta y la hace visible.
	 */
	private void menuAsk() {
		AskView ventanaPregunta = new AskView(frmMenuPrincipal, foro);
		ventanaPregunta.getFrame().setVisible(true);
		frmMenuPrincipal.setVisible(false);
	}

	/**
	 * Crea una ventana para publicar una respuesta y la hace visible.
	 */
	private void menuAnswer() {
		AnswerWindow ventanaRespuesta = new AnswerWindow(foro, frmMenuPrincipal);
		ventanaRespuesta.getFrame().setVisible(true);
		frmMenuPrincipal.setVisible(false);
	}

	/**
	 * Crea una ventana para ofrecer una recompensa por una pregunta y la hace visible.
	 */
	private void menuReward() {
		RewardWindow ventanaRecompensa = new RewardWindow(foro, frmMenuPrincipal);
		ventanaRecompensa.getFrmReward().setVisible(true);
		frmMenuPrincipal.setVisible(false);
	}

	/**
	 * Obtiene las preguntas del usuario que se encuentren abiertas y con respuestas por aceptar, comprueba que no existan errores, crea la ventana para aceptar una respuesta y la hace visible.
	 */
	private void menuAccept() {
		ArrayList<Integer> preguntasUsuario = foro.preguntasDelUsuario();
		
		if(preguntasUsuario.size() <= 0) {
			JOptionPane.showMessageDialog(frmMenuPrincipal, "Usted no posee una pregunta en la que se pueda aceptar una respuesta.", "No puede aceptar una respuesta", 0);
			return;
		}
		
		else {
			AcceptWindow ventanaAceptar = new AcceptWindow(foro, frmMenuPrincipal, preguntasUsuario);
			ventanaAceptar.getFrmAccept().setVisible(true);
			frmMenuPrincipal.setVisible(false);
		}
	}

	/**
	 * Crea la ventana para votar por una pregunta o respuesta y la hace visible.
	 */
	private void menuVote() {
		ArrayList<Integer> preguntasVote = foro.getListaPreguntas().preguntasVote(foro.getSesionIniciada());
		
		if(preguntasVote.size() <= 0) {
			JOptionPane.showMessageDialog(frmMenuPrincipal, "No hay preguntas disponibles para que usted pueda votar.", "No se puede votar", 0);
			return;
		}
		
		else {
			VoteWindow ventanaVotar = new VoteWindow(foro, frmMenuPrincipal, preguntasVote);
			ventanaVotar.getFrmVote().setVisible(true);
			frmMenuPrincipal.setVisible(false);
		}
		
	}

	/**
	 * Crea la ventana para crear una nueva etiqueta y la hace visible.
	 */
	private void menuNewLabel() {
		frmMenuPrincipal.setVisible(false);
		CreateLabelWindow ventanaCrearEtiqueta = new CreateLabelWindow(foro, frmMenuPrincipal);
		ventanaCrearEtiqueta.getFrmCreateLabel().setVisible(true);
	}

	/**
	 * Cierra la sesion del usuario actualmente loggueado y vuelve a la ventana para ingresar al foro.
	 */
	private void logout() {
		foro.setSesionIniciada(null);
		PrincipalView ventanaPrincipal = new PrincipalView(foro);
		ventanaPrincipal.getFrame().setVisible(true);
		frmMenuPrincipal.setVisible(false);
	}
}
