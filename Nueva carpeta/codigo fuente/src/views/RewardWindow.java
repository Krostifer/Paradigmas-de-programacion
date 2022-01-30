package views;

import javax.swing.JFrame;

import models.Stack;
import java.awt.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.TextArea;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;

public class RewardWindow {

	private JFrame frmReward;
	private Stack foro;
	private JFrame originFrame;
	private JTextField textFieldReward;

	/**
	 * Construye un objeto de la clase RewardWindow con los atributos entregados.
	 * @param foro Stack que contiene la informacion del foro.
	 * @param origin Frame de la ventana anterior a la ventana RewardWindow.
	 */
	public RewardWindow(Stack foro, JFrame origin) {
		this.foro = foro;
		this.originFrame = origin;
		initialize();
	}

	/**
	 * Inicializa el frame con el contenido de la ventana RewardWindow.
	 */
	private void initialize() {
		frmReward = new JFrame();
		frmReward.getContentPane().setBackground(SystemColor.inactiveCaption);
		getFrmReward().setTitle("Stack Overflow - Ofrecer recompensa");
		getFrmReward().setBounds(550, 250, 769, 548);
		getFrmReward().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmReward().getContentPane().setLayout(null);
		
		JLabel lblIndicacionesPregunta = new JLabel("Seleccione la pregunta por la que desea ofrecer una recompensa");
		lblIndicacionesPregunta.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndicacionesPregunta.setBounds(10, 11, 733, 14);
		getFrmReward().getContentPane().add(lblIndicacionesPregunta);
		
		JLabel lblPreguntas = new JLabel("Preguntas");
		lblPreguntas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPreguntas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreguntas.setBounds(10, 47, 236, 14);
		getFrmReward().getContentPane().add(lblPreguntas);
		
		JLabel lblNewLabel = new JLabel("Contenido de la pregunta");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(286, 47, 436, 14);
		getFrmReward().getContentPane().add(lblNewLabel);
		
		TextArea textAreaContenidoPregunta = new TextArea();
		textAreaContenidoPregunta.setEditable(false);
		textAreaContenidoPregunta.setBounds(286, 67, 436, 238);
		getFrmReward().getContentPane().add(textAreaContenidoPregunta);
		
		List listQuestions = new List();
		listQuestions.setMultipleMode(false);
		
		ArrayList<Integer> preguntasAbiertas = foro.getListaPreguntas().preguntasAbiertas();
		
		for(int i = 0; i < preguntasAbiertas.size(); i++){
				int indiceActual = preguntasAbiertas.get(i) - 1;
				String titulo = foro.getListaPreguntas().getPreguntas().get(indiceActual).getTitulo();
			 	listQuestions.add(titulo);
		 }
		
		listQuestions.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				mostrarContenidoPregunta(textAreaContenidoPregunta, listQuestions, e);
			}
		});
		listQuestions.setBounds(10, 67, 236, 238);
		getFrmReward().getContentPane().add(listQuestions);
		
		
		
		JLabel lblIndicacionReward = new JLabel("Ingrese la cantidad de puntos de reputacion que desea ofrecer como recompensa.");
		lblIndicacionReward.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndicacionReward.setBounds(10, 342, 733, 14);
		frmReward.getContentPane().add(lblIndicacionReward);
		
		int indiceUsuario = foro.getListaUsuarios().buscarIndiceUsuario(foro.getSesionIniciada());
		int reputacion = foro.getListaUsuarios().getUsuarios().get(indiceUsuario).getReputacion();
		JLabel lblCantReputacion = new JLabel("Usted posee actualmente " + reputacion + " puntos de reputacion para ofrecer como recompensa.");
		lblCantReputacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantReputacion.setBounds(10, 367, 733, 14);
		frmReward.getContentPane().add(lblCantReputacion);
		
		JLabel lblRecompensa = new JLabel("Recompensa: ");
		lblRecompensa.setBounds(244, 412, 100, 14);
		frmReward.getContentPane().add(lblRecompensa);
		
		textFieldReward = new JTextField();
		textFieldReward.setBounds(352, 409, 86, 20);
		frmReward.getContentPane().add(textFieldReward);
		textFieldReward.setColumns(10);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnVolver.setBounds(10, 475, 89, 23);
		frmReward.getContentPane().add(btnVolver);
		
		JButton btnOfrecer = new JButton("Ofrecer");
		btnOfrecer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ofrecerRecompensa(listQuestions, preguntasAbiertas, indiceUsuario, reputacion);
			}
		});
		btnOfrecer.setBounds(654, 475, 89, 23);
		frmReward.getContentPane().add(btnOfrecer);
	}

	/**
	 * Obtiene el frame de la ventana RewardWindow.
	 * @return frmReward Retorna el frame de la ventana.
	 */
	public JFrame getFrmReward() {
		return frmReward;
	}

	/**
	 * Muestra el contenido de la pregunta seleccionada.
	 * @param textAreaContenidoPregunta Elemento grafico que muestra el contenido de la pregunta seleccionada en forma de un panel de texto.
	 * @param listQuestions Elemento grafico que muestra las preguntas abiertas del foro en forma de lista.
	 * @param e Elemento que acciono el manejador de eventos.
	 */
	private void mostrarContenidoPregunta(TextArea textAreaContenidoPregunta, List listQuestions, ItemEvent e) {
		if(e.getStateChange() == 1) {
			int preguntaSeleccionada = listQuestions.getSelectedIndex();
			textAreaContenidoPregunta.setText(foro.getListaPreguntas().getPreguntas().get(preguntaSeleccionada).toString());
		}
	}

	/**
	 * Vuelve a la ventana anterior a la ventana RewardWindow
	 */
	private void volver() {
		originFrame.setVisible(true);
		frmReward.setVisible(false);
	}

	/**
	 * Comprueba que no existan errores, descuenta la reputacion del usuario, agrega la recompensa a la pregunta y vuelve al menu principal.
	 * @param listQuestions Elemento grafico que muestra las preguntas abiertas del foro en forma de lista.
	 * @param preguntasAbiertas Arreglo de enteros que contiene el id de las preguntas abiertas del foro.
	 * @param indiceUsuario Entero que representa el indice que tiene el usuario loggueado dentro de la lista de usuarios del foro.
	 * @param reputacion Entero que representa la reputacion actual del usuario con sesion iniciada.
	 */
	private void ofrecerRecompensa(List listQuestions, ArrayList<Integer> preguntasAbiertas, int indiceUsuario, int reputacion) {
		int recompensa;
		int indicePregunta = listQuestions.getSelectedIndex();
		
		//Comprobar que se seleecione pregunta
		if(indicePregunta == - 1) {
			JOptionPane.showMessageDialog(frmReward, "Debe seleccionar una pregunta antes de continuar.", "Pregunta no seleccionada", 0);
			return;
		}
		
		//Comprobar que se ingrese un numero como recompensa
		try {
			recompensa = Integer.parseInt((textFieldReward.getText().trim()));
		}
		catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(frmReward, "Favor solamente ingresar numeros.", "Datos invalidos", 0);
			return;
		}
		
		//Comprobar que se tenga suficiente reputacion
		if(recompensa <= reputacion && recompensa > 0){
		 	foro.reward(preguntasAbiertas.get(indicePregunta) , recompensa, indiceUsuario);
		 	OptionView ventanaMenu = new OptionView(foro);
		 	ventanaMenu.getFrmMenuPrincipal().setVisible(true);
		 	JOptionPane.showMessageDialog(ventanaMenu.getFrmMenuPrincipal(), "Se ha ofrecido exitomente la recompensa ofrecida.", "Accion ejecutada correctemente", 1);
		 	frmReward.setVisible(false);
		 }
		
		//No se tiene suficiente reputacion o ingresa un numero menor a 0
		else {
			JOptionPane.showMessageDialog(frmReward, "Las recompensa ofrecida supera sus puntos de reputacion o no posee reputacion.", "Reputacion insuficiente", 0);
			return;
		}
	}
}
