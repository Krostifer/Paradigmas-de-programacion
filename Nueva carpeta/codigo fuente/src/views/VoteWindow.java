package views;


import javax.swing.JFrame;

import models.Stack;
import java.awt.List;
import javax.swing.JTextPane;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;

public class VoteWindow {

	private JFrame frmVote;
	private Stack foro;
	private JFrame originFrame;
	private ArrayList<Integer> preguntasVote;

	/**
	 * Construye un objeto de la clase VoteWindow con los atributos entregados.
	 * @param foro Stack que contien la informacion del foro.
	 * @param origin Frame de la ventana anterior a la ventana VoteWindow.
	 * @param preguntas Lista que contiene el id de las preguntas que se pueden votar o contienen respuestas que se pueden votar por el usuario.
	 */
	public VoteWindow(Stack foro, JFrame origin, ArrayList<Integer> preguntas) {
		this.foro = foro;
		this.originFrame = origin;
		this.preguntasVote = preguntas;
		initialize();
	}

	/**
	 * Inicializa el frame con el contenido de la ventana VoteWindow.
	 */
	private void initialize() {
		frmVote = new JFrame();
		frmVote.getContentPane().setBackground(SystemColor.inactiveCaption);
		getFrmVote().setTitle("Stack Overflow - Votar");
		getFrmVote().setBounds(550, 250, 846, 685);
		getFrmVote().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVote.getContentPane().setLayout(null);
		
		JTextPane textPaneDetallePregunta = new JTextPane();
		textPaneDetallePregunta.setEditable(false);
		textPaneDetallePregunta.setBounds(350, 83, 470, 235);
		frmVote.getContentPane().add(textPaneDetallePregunta);
		
		List listRespuestas = new List();
		listRespuestas.setMultipleMode(false);
		listRespuestas.setBounds(10, 423, 810, 138);
		frmVote.getContentPane().add(listRespuestas);
		
		List listPreguntas = new List();
		listPreguntas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				mostrarContendioYRespuestas(textPaneDetallePregunta, listRespuestas, listPreguntas);
			}
		});
		listPreguntas.setMultipleMode(false);
		listPreguntas.setBounds(10, 84, 284, 234);
		frmVote.getContentPane().add(listPreguntas);
		
		for(int i = 0; i < preguntasVote.size(); i++){
			int indiceActual = preguntasVote.get(i) - 1;
			String titulo = foro.getListaPreguntas().getPreguntas().get(indiceActual).getTitulo();
		 	listPreguntas.add(titulo);
		 }
		
		
		
		///////////////////////////////////////////
		//Etiquetas
		
		JLabel lblTituloPregunta = new JLabel("Titulo preguntas");
		lblTituloPregunta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTituloPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloPregunta.setBounds(10, 57, 284, 14);
		frmVote.getContentPane().add(lblTituloPregunta);
		
		JLabel lblContenidoPregunta = new JLabel("Contenido pregunta");
		lblContenidoPregunta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContenidoPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		lblContenidoPregunta.setBounds(351, 57, 469, 14);
		frmVote.getContentPane().add(lblContenidoPregunta);
		
		JLabel lblRespuestas = new JLabel("Respuestas");
		lblRespuestas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRespuestas.setHorizontalAlignment(SwingConstants.CENTER);
		lblRespuestas.setBounds(10, 403, 810, 14);
		frmVote.getContentPane().add(lblRespuestas);
		
		JLabel lblIndicacionVotarRespuesta = new JLabel("Si desea votar una respuesta, debe seleccionar primero la pregunta que la contiene");
		lblIndicacionVotarRespuesta.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndicacionVotarRespuesta.setBounds(10, 362, 810, 14);
		frmVote.getContentPane().add(lblIndicacionVotarRespuesta);
		
		JLabel lblIndicacionVotarPregunta = new JLabel("Si desea votar una pregunta, solo seleccione la pregunta deseada y presione el tipo de voto");
		lblIndicacionVotarPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndicacionVotarPregunta.setBounds(10, 21, 810, 14);
		frmVote.getContentPane().add(lblIndicacionVotarPregunta);
		
		///////////////////////////////////////////////////////
		//Botones
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnVolver.setBounds(10, 612, 89, 23);
		frmVote.getContentPane().add(btnVolver);
		
		JButton btnPositivo = new JButton("Positivo +");
		btnPositivo.setBackground(new Color(204, 255, 204));
		btnPositivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				votoPositivo(listRespuestas, listPreguntas);
			}
		});
		btnPositivo.setBounds(723, 578, 97, 23);
		frmVote.getContentPane().add(btnPositivo);
		
		JButton btnNegativo = new JButton("Negativo -");
		btnNegativo.setBackground(new Color(255, 153, 153));
		btnNegativo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				votoNegativo(listRespuestas, listPreguntas);
			}
		});
		btnNegativo.setBounds(723, 612, 97, 23);
		frmVote.getContentPane().add(btnNegativo);
		
	}

	/**
	 * Obtiene el frame de la ventana VoteWindow
	 * @return frmVote Retorna del frame de la ventana.
	 */
	public JFrame getFrmVote() {
		return frmVote;
	}

	/**
	 * Muestra el contenido de la pregunta seleccionada y las respuestas a esta pregunta.
	 * @param textPaneDetallePregunta Elemento grafico que muestra el contenido de la pregunta seleccionada en forma de panel de texto.
	 * @param listRespuestas Elemento grafico que muestra las respuestas de la pregunta seleccionada en forma de lista.
	 * @param listPreguntas Elemento grafico que muestra las preguntas del foro en forma de lista.
	 */
	private void mostrarContendioYRespuestas(JTextPane textPaneDetallePregunta, List listRespuestas, List listPreguntas) {
		//Se asegura de que la lista este vacia
		listRespuestas.removeAll();
		int indicePreguntaSelect = preguntasVote.get(listPreguntas.getSelectedIndex()) - 1;
		textPaneDetallePregunta.setText(foro.getListaPreguntas().getPreguntas().get(indicePreguntaSelect).toString());
		
		ArrayList<Integer> respuestasVote = foro.getListaPreguntas().getPreguntas().get(indicePreguntaSelect).getListaRespuestas().respuestasVote(foro.getSesionIniciada());
		System.out.println(respuestasVote);
		
		for(int i = 0; i < respuestasVote.size(); i++) {
			int indiceRespuesta = foro.getListaPreguntas().getPreguntas().get(indicePreguntaSelect).getListaRespuestas().buscarIndiceRespuesta(respuestasVote.get(i));
			listRespuestas.add(foro.getListaPreguntas().getPreguntas().get(indicePreguntaSelect).getListaRespuestas().getRespuestas().get(indiceRespuesta).getContenido());
		}
	}

	/**
	 * Vuelve a la ventana anterior a la ventana VoteWindow
	 */
	private void volver() {
		originFrame.setVisible(true);
		frmVote.setVisible(false);
	}

	/**
	 * Comprueba que no existan errores, registra el voto positivo a la pregunta o respuesta seleccionada, realiza el cambio en las reputaciones y vuelve al menu principal.
	 * @param listRespuestas Elemento grafico que muestra las respuestas de la pregunta seleccionada en forma de lista.
	 * @param listPreguntas Elemento grafico que muestra las preguntas del foro en forma de lista.
	 */
	private void votoPositivo(List listRespuestas, List listPreguntas) {
		int indicePregunta = preguntasVote.get(listPreguntas.getSelectedIndex()) - 1;
		
		if(listPreguntas.getSelectedIndex() == -1 && listRespuestas.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(frmVote, "Necesita seleccionar una pregunta a votar o una pregunta con la respuesta que desea votar antes de continuar.", "Pregunta no seleecionada", 0);
			return;
		}
		
			
		else if(listRespuestas.getSelectedIndex() == -1) {
			if(foro.getListaPreguntas().getPreguntas().get(indicePregunta).getAutor().equals(foro.getSesionIniciada())) {
				JOptionPane.showMessageDialog(frmVote, "No puede votar esta pregunta ya que usted es el autor, pero si puede votar una de sus respuestas.", "Autor de la pregunta a votar", 0);
				return;
			}
			
			foro.vote(1, 1, foro.getSesionIniciada(), indicePregunta, -1);
		}
		
		else if(listPreguntas.getSelectedIndex() != -1 && listRespuestas.getSelectedIndex() != -1) {
			ArrayList<Integer> respuestasVote = foro.getListaPreguntas().getPreguntas().get(indicePregunta).getListaRespuestas().respuestasVote(foro.getSesionIniciada());
			int indiceRespuesta = foro.getListaPreguntas().getPreguntas().get(indicePregunta).getListaRespuestas().buscarIndiceRespuesta(respuestasVote.get(listRespuestas.getSelectedIndex()));
			foro.vote(2, 1, foro.getSesionIniciada(), indicePregunta, indiceRespuesta);
		}
		
		
		frmVote.setVisible(false);
		OptionView ventanaMenu = new OptionView(foro);
		ventanaMenu.getFrmMenuPrincipal().setVisible(true);
		JOptionPane.showMessageDialog(ventanaMenu.getFrmMenuPrincipal(), "Se ha registrado su voto exitosamente.", "Accion ejecutada correctemente", 1);
		
	}

	/**
	 * Comprueba que no existan errores, registra el voto negativo a la pregunta o respuesta seleccionada, realiza el cambio en las reputaciones y vuelve al menu principal.
	 * @param listRespuestas Elemento grafico que muestra las respuestas de la pregunta seleccionada en forma de lista.
	 * @param listPreguntas Elemento grafico que muestra las preguntas del foro en forma de lista.
	 */
	private void votoNegativo(List listRespuestas, List listPreguntas) {
		int indicePregunta = preguntasVote.get(listPreguntas.getSelectedIndex()) - 1;
		
		if(listPreguntas.getSelectedIndex() == -1 && listRespuestas.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(frmVote, "Necesita seleccionar una pregunta a votar o una pregunta con la respuesta que desea votar antes de continuar.", "Pregunta no seleecionada", 0);
			return;
		}
		
		else if(listRespuestas.getSelectedIndex() == -1) {
			if(foro.getListaPreguntas().getPreguntas().get(indicePregunta).getAutor().equals(foro.getSesionIniciada())) {
				JOptionPane.showMessageDialog(frmVote, "No puede votar esta pregunta ya que usted es el autor, pero si puede votar una de sus respuestas.", "Autor de la pregunta a votar", 0);
				return;
			}
			
			foro.vote(1, 0, foro.getSesionIniciada(), indicePregunta, -1);
		}
		
		else if(listPreguntas.getSelectedIndex() != -1 && listRespuestas.getSelectedIndex() != -1) {
			ArrayList<Integer> respuestasVote = foro.getListaPreguntas().getPreguntas().get(indicePregunta).getListaRespuestas().respuestasVote(foro.getSesionIniciada());
			int indiceRespuesta = foro.getListaPreguntas().getPreguntas().get(indicePregunta).getListaRespuestas().buscarIndiceRespuesta(respuestasVote.get(listRespuestas.getSelectedIndex()));
			foro.vote(2, 0, foro.getSesionIniciada(), indicePregunta, indiceRespuesta);
		}
		
		frmVote.setVisible(false);
		OptionView ventanaMenu = new OptionView(foro);
		ventanaMenu.getFrmMenuPrincipal().setVisible(true);
		JOptionPane.showMessageDialog(ventanaMenu.getFrmMenuPrincipal(), "Se ha registrado su voto exitosamente.", "Accion ejecutada correctemente", 1);
		
	}
}
