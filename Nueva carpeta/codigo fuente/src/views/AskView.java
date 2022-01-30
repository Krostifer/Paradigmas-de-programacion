package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import models.Stack;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;

public class AskView {

	private JFrame frmPreguntar;
	private Stack foro;
	private JFrame originFrame;
	
	/**
	 * Construye un objeto de la clase AskView con los atributos entregados.
	 * @param origin Frame de la ventana anterior a la ventana AskView.
	 * @param foro Stack que contiene la informacion del foro.
	 */
	public AskView(JFrame origin, Stack foro) {
		this.foro = foro;
		this.originFrame = origin;
		initialize();
	}

	/**
	 * Inicializa el contenido del frame de la ventana AskView
	 */
	private void initialize() {
		frmPreguntar = new JFrame();
		frmPreguntar.getContentPane().setBackground(SystemColor.inactiveCaption);
		frmPreguntar.setTitle("Preguntar");
		getFrame().setBounds(550, 250, 450, 300);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPreguntar.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Titulo:");
		lblTitle.setBounds(46, 56, 46, 14);
		frmPreguntar.getContentPane().add(lblTitle);
		
		JTextField textFieldTitle = new JTextField();
		textFieldTitle.setBounds(116, 53, 276, 20);
		frmPreguntar.getContentPane().add(textFieldTitle);
		textFieldTitle.setColumns(10);
		
		JLabel lblContent = new JLabel("Contenido:");
		lblContent.setBounds(46, 104, 64, 14);
		frmPreguntar.getContentPane().add(lblContent);
		
		JTextPane textPaneContent = new JTextPane();
		textPaneContent.setBounds(116, 98, 276, 118);
		frmPreguntar.getContentPane().add(textPaneContent);
		
		JButton btnNextAsk = new JButton("Siguiente");
		btnNextAsk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarPreguntaResponder(textFieldTitle, textPaneContent);
			}
		});
		btnNextAsk.setBounds(335, 227, 89, 23);
		frmPreguntar.getContentPane().add(btnNextAsk);
		
		JLabel lblCrearPregunta = new JLabel("Crear pregunta");
		lblCrearPregunta.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCrearPregunta.setBounds(170, 11, 124, 20);
		frmPreguntar.getContentPane().add(lblCrearPregunta);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnVolver.setBounds(10, 227, 89, 23);
		frmPreguntar.getContentPane().add(btnVolver);
	}

	/**
	 * Obtiene el frame de la ventana AskView.
	 * @return frmPreguntar Retorna el frame de la ventna.
	 */
	public JFrame getFrame() {
		return frmPreguntar;
	}

	/**
	 * Comprueba que no existan errores y crea la ventana para seleccionar las etiquetas de la pregunta a crear.
	 * @param textPaneContent Elemento grafico en el que se escribe el contenido de la pregunta a crear.
	 */
	private void seleccionarPreguntaResponder(JTextField textFieldTitle, JTextPane textPaneContent) {
		String title = textFieldTitle.getText();
		String content = textPaneContent.getText();
		
		if(title.isBlank() || title == null || content.isBlank() || content == null) {
			JOptionPane.showMessageDialog(frmPreguntar, "Datos ingresados invalidos, favor volver a intentar introduciendo datos validos.", "Datos invalidos", 0);
			return;
		}
		
		AskLabelsWindow ventanaEtiquetasPregunta = new AskLabelsWindow(frmPreguntar, foro, title, content);
		frmPreguntar.setVisible(false);
		ventanaEtiquetasPregunta.getFrame().setVisible(true);
	}

	/**
	 * Vuelve a la ventana anterior a la ventana AskView.
	 */
	private void volver() {
		originFrame.setVisible(true);
		frmPreguntar.setVisible(false);
	}
}
