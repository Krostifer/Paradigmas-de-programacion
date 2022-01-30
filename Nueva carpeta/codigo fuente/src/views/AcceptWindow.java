package views;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.Stack;
import java.awt.List;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;

public class AcceptWindow {

	private JFrame frmAccept;
	private Stack foro;
	private JFrame originFrame;
	private ArrayList<Integer> preguntasDelUsuario;

	/**
	 * Construye un objeto de la clase AcceptWindow con los atributos entregados.
	 * @param foro Stack que contiene la informacion del foro. 
	 * @param origin Frame de la ventana anterior a la venatana accept
	 * @param preguntas Lista que contiene las preguntas del usuario esten que abiertas y contienen respuestas que se puedan aceptar.
	 */
	public AcceptWindow(Stack foro, JFrame origin, ArrayList<Integer> preguntas) {
		this.foro = foro;
		this.originFrame = origin;
		this.preguntasDelUsuario = preguntas;
		initialize();
	}

	/**
	 * Inicializa los contenidos del frame de la ventana
	 */
	private void initialize() {
		frmAccept = new JFrame();
		frmAccept.getContentPane().setBackground(SystemColor.inactiveCaption);
		getFrmAccept().setTitle("Stack Overflow - Aceptar una respuesta");
		getFrmAccept().setBounds(550, 250, 670, 399);
		getFrmAccept().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAccept.getContentPane().setLayout(null);
		
		List listRespuestas = new List();
		listRespuestas.setMultipleMode(false);
		listRespuestas.setBounds(325, 55, 319, 224);
		frmAccept.getContentPane().add(listRespuestas);
		
		
		List listPreguntas = new List();
		listPreguntas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				mostrarRespuestasDePregunta(listRespuestas, listPreguntas, e);
			}
		});
		listPreguntas.setMultipleMode(false);
		listPreguntas.setBounds(10, 55, 244, 224);
		frmAccept.getContentPane().add(listPreguntas);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				pulsarBotonAceptar(listRespuestas, listPreguntas);
			}
		});
		btnAceptar.setBounds(555, 326, 89, 23);
		frmAccept.getContentPane().add(btnAceptar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnVolver.setBounds(10, 326, 89, 23);
		frmAccept.getContentPane().add(btnVolver);
		
		JLabel lblPreguntas = new JLabel("Preguntas");
		lblPreguntas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPreguntas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreguntas.setBounds(10, 30, 244, 14);
		frmAccept.getContentPane().add(lblPreguntas);
		
		JLabel lblNewRespuestas = new JLabel("Respuestas a la pregunta");
		lblNewRespuestas.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewRespuestas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewRespuestas.setBounds(325, 30, 319, 14);
		frmAccept.getContentPane().add(lblNewRespuestas);
		
		for(int i = 0; i < preguntasDelUsuario.size(); i++) {
			int idActual = preguntasDelUsuario.get(i);
			listPreguntas.add(foro.getListaPreguntas().getPreguntas().get(idActual-1).getTitulo());
		}
		
		
	}

	/**
	 * Obtiene el frame (JFrame) de la ventana aceptar.
	 * @return frmAccept frame de la ventana.
	 */
	public JFrame getFrmAccept() {
		return frmAccept;
	}

	/**
	 * Al momento de accionar el boton "Aceptar" comprueba que no existan errores, luego acepta la respuesta a una pregunta del usuario y vuelve al menu principal.
	 * @param listRespuestas Elemento grafico que se encarga de mostrar las respuestas de la pregunta seleccionada en forma de lista.
	 * @param listPrteguntas Elemento grafico que se encarga de mostrar las preguntas pertenecientes al usuario que posean una pregunta para aceptar.
	 */
	private void pulsarBotonAceptar(List listRespuestas, List listPreguntas) {
		if(listPreguntas.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(frmAccept, "Debe selecionar una de sus preguntas antes de continuar.", "Pregunta no seleccionada", 0);
			return;
		}
		
		int idPregunta = preguntasDelUsuario.get(listPreguntas.getSelectedIndex());
		int indiceRespuesta = listRespuestas.getSelectedIndex();
		
		if(indiceRespuesta == -1) {
			JOptionPane.showMessageDialog(frmAccept, "Debe selecionar al respuesta que desea aceptar antes de continuar.", "Respuesta no seleccionada", 0);
			return;
		}
		
		foro.accept(idPregunta, indiceRespuesta, foro.getSesionIniciada());
		
		OptionView ventanaMenu = new OptionView(foro);
		ventanaMenu.getFrmMenuPrincipal().setVisible(true);
		JOptionPane.showMessageDialog(ventanaMenu.getFrmMenuPrincipal(), "La respuesta se ha aceptado exitosamente.", "Accion ejecutada correctemente", 1);
		frmAccept.setVisible(false);
	}

	/**
	 * Muestra las respuestas pertenecientes a la pregunta seleccionada de la lista de preguntas
	 * @param listRespuestas Elemento grafico que se encarga de mostrar las respuestas de la pregunta seleccionada en forma de lista.
	 * @param listPrteguntas Elemento grafico que se encarga de mostrar las preguntas pertenecientes al usuario que posean una pregunta para aceptar.
	 * @param e Elemento que acciono el manejador de eventos.
	 */
	private void mostrarRespuestasDePregunta(List listRespuestas, List listPreguntas, ItemEvent e) {
		if(e.getStateChange() == 1) {
			int indicePreguntaSeleccionada = listPreguntas.getSelectedIndex();
			int idPreguntaSeleccionada = preguntasDelUsuario.get(indicePreguntaSeleccionada);
			
			for(int i = 0; i < foro.getListaPreguntas().getPreguntas().get(idPreguntaSeleccionada).getListaRespuestas().getRespuestas().size(); i++) {
				listRespuestas.add(foro.getListaPreguntas().getPreguntas().get(idPreguntaSeleccionada).getListaRespuestas().getRespuestas().get(i).getContenido());
			}
		}
	}

	/**
	 * Vuelve a la ventana anterior
	 */
	private void volver() {
		originFrame.setVisible(true);
		frmAccept.setVisible(false);
	}
}
