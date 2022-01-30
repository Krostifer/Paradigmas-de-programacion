package views;

import javax.swing.JFrame;

import models.ListaEtiquetas;
import models.Stack;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class AskLabelsWindow {

	private JFrame frmEtiquetas;
	private JFrame originFrame;
	private Stack foro;
	private String title;
	private String content;
	
	/**
	 * Construye un objeto de la clase AskLabelsWindow con los atributos entregados.
	 * @param origin Frame de la ventana anterior a la ventana AskLabels.
	 * @param foro Stack que contiene la informacion del foro.
	 * @param title String que contiene el titulo de la pregunta que se esta creando.
	 * @param content String que contiene el contenido de la pregunta que se esta creando.
	 */
	public AskLabelsWindow(JFrame origin, Stack foro, String title, String content) {
		this.originFrame = origin;
		this.foro = foro;
		this.title = title;
		this.content = content;
		initialize();
	}

	/**
	 * Inicializa el contenido del frame de la ventan AskLabels
	 */
	private void initialize() {
		frmEtiquetas = new JFrame();
		frmEtiquetas.getContentPane().setBackground(SystemColor.inactiveCaption);
		frmEtiquetas.setTitle("Stack Overflow - Etiquetas");
		getFrame().setBounds(550, 250, 574, 349);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEtiquetas.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Descripcion de la etiqueta");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(315, 11, 233, 14);
		frmEtiquetas.getContentPane().add(lblNewLabel);
		
		JLabel lblIndicaciones = new JLabel("Seleccione las etiquetas que desea agregar");
		lblIndicaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndicaciones.setBounds(10, 11, 261, 14);
		frmEtiquetas.getContentPane().add(lblIndicaciones);
		
		JTextArea textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setEditable(false);
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);
		textAreaDescripcion.setBounds(315, 36, 233, 206);
		frmEtiquetas.getContentPane().add(textAreaDescripcion);
		
		List listLabels = new List();
		listLabels.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {	
				mostrarDescripcionEtiqueta(textAreaDescripcion, listLabels, e);
			}
		});
		listLabels.setMultipleMode(true);
		listLabels.setBounds(10, 31, 253, 211);
		frmEtiquetas.getContentPane().add(listLabels);
		
		//Mostrar las etiquetas existentes
		
		for(int i = 0; i < foro.getTags().getEtiquetas().size(); i++){
		 	listLabels.add(foro.getTags().getEtiquetas().get(i).getNombre());
		 }
		
		
		
		JButton btnPublicar = new JButton("Publicar");
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				publicarPreguntaCreada(listLabels);
			}
		});
		
		
		btnPublicar.setBounds(459, 276, 89, 23);
		frmEtiquetas.getContentPane().add(btnPublicar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnVolver.setBounds(10, 276, 89, 23);
		frmEtiquetas.getContentPane().add(btnVolver);	
	}

	/**
	 * Obtiene el frame de la ventana AskLabels.
	 * @return frmEtiquetas frame de la ventana.
	 */
	public JFrame getFrame() {
		return frmEtiquetas;
	}

	/**
	 * Muestra la descripcion de la ultima etiqueta seleccionada en un panel de texto.
	 * @param textAreaDescripcion Elemento grafico donde se muestra de descripcion de la etiqueta en forma de un panel de texto.
	 * @param listLabels Elemento grafico que muestra las etiquetas registradas en el foro en forma de lista.
	 * @param e Elemento que acciono el manejador de eventos.
	 */
	private void mostrarDescripcionEtiqueta(JTextArea textAreaDescripcion, List listLabels, ItemEvent e) {
		if(e.getStateChange() == 1) {
			
			int selectedTag = listLabels.getSelectedIndexes()[listLabels.getSelectedIndexes().length - 1];
			textAreaDescripcion.setText(foro.getTags().getEtiquetas().get(selectedTag).getDescripcion());
		}
		
		
		else if (e.getStateChange() == 2) {
			textAreaDescripcion.setText("");
		}
	}

	/**
	 * Comprueba que no existan errores, luego publica en el foro la pregunta creada y vuelve al menu principal.
	 * @param listLabels Elemento grafico que muestra las etiquetas registradas en el foro en forma de lista.
	 */
	private void publicarPreguntaCreada(List listLabels) {
		//Lista con los elementos seleccionados
		int[] etiquetasSeleccionadas = listLabels.getSelectedIndexes();
		
		ListaEtiquetas etiquetasPregunta = new ListaEtiquetas();
		for(int i = 0; i < etiquetasSeleccionadas.length ; i++) {
			etiquetasPregunta.agregarEtiqueta(foro.getTags().getEtiquetas().get(etiquetasSeleccionadas[i]));
		}
		
		foro.getListaPreguntas().ask( title, content, etiquetasPregunta, foro.getSesionIniciada() );
		int lastQuestion = foro.getListaPreguntas().getPreguntas().size() - 1;
		System.out.println(foro.getListaPreguntas().getPreguntas().get(lastQuestion).toString());

		//Lanzar la nueva ventana
		
		OptionView ventanaMenu = new OptionView(foro);
		ventanaMenu.getFrmMenuPrincipal().setVisible(true);
		JOptionPane.showMessageDialog(ventanaMenu.getFrmMenuPrincipal(), "La nueva pregunta se ha creado exitosamente.", "Accion ejecutada correctemente", 1);
		frmEtiquetas.setVisible(false);
	}

	/**
	 * Vuelve a la ventana anterior a la ventana AskLabels
	 */
	private void volver() {
		frmEtiquetas.setVisible(false);
		originFrame.setVisible(true);
	}
}
