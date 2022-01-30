package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import models.Stack;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class CreateAnswerWindow {

	private JFrame frmCreateAnswer;
	private JFrame originFrame;
	private Stack foro;
	private int idPregunta;
	
	/**
	 * Construye un objeto de la clase CreateAnserWindow con los atributos entregados.
	 * @param origin Frame de la ventana anterior a la ventana CreateAnswer.
	 * @param foro Stack que contiene la informacion del foro.
	 * @param idPregunta Identificador numerico de la pregunta que se desea responder.
	 */
	public CreateAnswerWindow(JFrame origin, Stack foro, int idPregunta) {
		this.originFrame = origin;
		this.foro = foro;
		this.idPregunta = idPregunta;
		initialize();
	}

	/**
	 * Inicializa el frame con el contenido de la ventana CreateAnswer.
	 */
	private void initialize() {
		frmCreateAnswer = new JFrame();
		frmCreateAnswer.getContentPane().setBackground(SystemColor.inactiveCaption);
		getFrmCreateAnswer().setTitle("Stack Overflow - Crear respuesta");
		getFrmCreateAnswer().setBounds(550, 250, 505, 354);
		getFrmCreateAnswer().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmCreateAnswer().getContentPane().setLayout(null);
		
		JLabel lblContent = new JLabel("Ingrese el contenido de su respuesta: ");
		lblContent.setBounds(30, 22, 414, 14);
		getFrmCreateAnswer().getContentPane().add(lblContent);
		
		JTextPane textPaneContent = new JTextPane();
		textPaneContent.setBounds(30, 55, 424, 197);
		getFrmCreateAnswer().getContentPane().add(textPaneContent);
		
		JButton btnPublicar = new JButton("Publicar");
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				publicarPregunta(textPaneContent);
			}
		});
		
		btnPublicar.setBounds(390, 281, 89, 23);
		getFrmCreateAnswer().getContentPane().add(btnPublicar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnVolver.setBounds(10, 281, 89, 23);
		getFrmCreateAnswer().getContentPane().add(btnVolver);
	}

	/**
	 * Obtiene el frame de la ventana CreateAnswer.
	 * @return frmCreateAnswer Retorna el frame de la ventana.
	 */
	public JFrame getFrmCreateAnswer() {
		return frmCreateAnswer;
	}

	/**
	 * Comprueba que no existan errores, publica la respuesta creada y vuelve al menu principal.
	 * @param textPaneContent Elemento grafico donde se escribe el contenido de la respuestas en forma de panel de texto.
	 */
	private void publicarPregunta(JTextPane textPaneContent) {
		String contenido = textPaneContent.getText();
		
		if(contenido == null || contenido.isBlank()) {
			JOptionPane.showMessageDialog(frmCreateAnswer, "Datos ingresados invalidos.", "Datos invalidos", 0);
			return;
		}
		
		foro.getListaPreguntas().answer( idPregunta-1 , contenido, foro.getSesionIniciada() );
		OptionView ventanaMenu = new OptionView(foro);
		ventanaMenu.getFrmMenuPrincipal().setVisible(true);
		JOptionPane.showMessageDialog(ventanaMenu.getFrmMenuPrincipal(), "La respuesta se ha creado exitosamente.", "Accion ejecutada correctemente", 1);
		frmCreateAnswer.setVisible(false);
	}

	/**
	 * Vuelve a la ventana anterior a la ventana CreateAnswer.
	 */
	private void volver() {
		getFrmCreateAnswer().setVisible(false);
		originFrame.setVisible(true);
	}
}
