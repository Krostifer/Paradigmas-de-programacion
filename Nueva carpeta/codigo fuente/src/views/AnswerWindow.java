package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import models.Stack;

import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.TextArea;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.SystemColor;

public class AnswerWindow {

	private JFrame frmAnswer;
	private Stack foro;
	private JFrame originFrame;

	/**
	 * Construye un objeto de la clase AnswerWindow con los atributos entregados.
	 * @param foro Stack que contiene toda la informacion del foro.
	 * @param origin Frame de la venta anterior a la ventan de answer.
	 */
	public AnswerWindow(Stack foro, JFrame origin) {
		this.foro = foro;
		this.originFrame = origin;
		initialize();
	}

	/**
	 * Inicializa los contenidos del frame de la ventana 
	 */
	private void initialize() {
		frmAnswer = new JFrame();
		frmAnswer.getContentPane().setBackground(SystemColor.inactiveCaption);
		getFrame().setTitle("Stack Overflow - Responder pregunta");
		getFrame().setBounds(550, 250, 850, 400);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAnswer.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Preguntas");
		lblTitle.setBounds(10, 11, 310, 16);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		getFrame().getContentPane().add(lblTitle);
		
		TextArea textAreaDetallePregunta = new TextArea();
		textAreaDetallePregunta.setEditable(false);
		textAreaDetallePregunta.setBounds(360, 33, 439, 253);
		frmAnswer.getContentPane().add(textAreaDetallePregunta);
		
		JLabel lblNewLabel = new JLabel("Contenido de la pregunta");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(360, 13, 439, 14);
		frmAnswer.getContentPane().add(lblNewLabel);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnVolver.setBounds(10, 309, 89, 23);
		frmAnswer.getContentPane().add(btnVolver);
		
		List listQuestions = new List();
		listQuestions.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				mostrarContenidoPregunta(textAreaDetallePregunta, listQuestions, e);
			}
		});
		listQuestions.setMultipleMode(false);
		listQuestions.setBounds(10, 33, 310, 242);
		frmAnswer.getContentPane().add(listQuestions);
		
		for(int i = 0; i < foro.getListaPreguntas().getPreguntas().size(); i++){
			String titulo = foro.getListaPreguntas().getPreguntas().get(i).getTitulo();
		 	listQuestions.add(titulo);
		 }
		
		
		
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pulsarBotonPublicarRespuesta(listQuestions);
			}
		});
		btnSiguiente.setBounds(735, 327, 89, 23);
		frmAnswer.getContentPane().add(btnSiguiente);
		
		
			
		
	}

	/**
	 * Obtiene el frame de la ventana answer
	 * @return frmAnswer Retorna el frame de la ventana
	 */
	public JFrame getFrame() {
		return frmAnswer;
	}

	/**
	 * Vuelve a la ventana anterior a la ventana answer.
	 */
	private void volver() {
		originFrame.setVisible(true);
		frmAnswer.setVisible(false);
	}

	/**
	 * @param textAreaDetallePregunta Elemento donde se muestra el contenido de la pregunta en un panel de texto.
	 * @param listQuestions Elemento grafico donde se muestran las preguntas del foro en forma de lista.
	 * @param e Elemento que acciono el manejador de eventos.
	 */
	private void mostrarContenidoPregunta(TextArea textAreaDetallePregunta, List listQuestions, ItemEvent e) {
		if(e.getStateChange() == 1) {
			int preguntaSeleccionada = listQuestions.getSelectedIndex();
			textAreaDetallePregunta.setText(foro.getListaPreguntas().getPreguntas().get(preguntaSeleccionada).toString());
		}
	}

	/**
	 * Verifica que no existan errores, luego crea la ventana para escribir la respuesta a la pregunta seleccionada y vuelve al menu principal.
	 * @param listQuestions Elemento grafico que muestra las preguntas del foro en forma de lista.
	 */
	private void pulsarBotonPublicarRespuesta(List listQuestions) {
		if(listQuestions.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(frmAnswer, "Favor seleccionar la pregunta que desea responder antes de continuar.", "Pregunta no seleccionada", 0);
			return;
		}
		
		int indicePregunta = listQuestions.getSelectedIndex();
		CreateAnswerWindow ventanaCrearRespuesta = new CreateAnswerWindow(frmAnswer, foro, indicePregunta + 1);
		ventanaCrearRespuesta.getFrmCreateAnswer().setVisible(true);
		frmAnswer.setVisible(false);
	}
}
