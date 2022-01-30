package views;

import javax.swing.JFrame;

import models.Stack;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class CreateLabelWindow {

	private JFrame frmCreateLabel;
	private Stack foro;
	private JFrame originFrame;
	
	/**
	 * Construye un objeto de la clase CreateLabelWindow con los atributos entregados.
	 * @param foro Stack que contiene la informacion del foro.
	 * @param origin Frame de la ventana anterior a la ventana CreateLabel
	 */
	public CreateLabelWindow(Stack foro, JFrame origin) {
		this.foro = foro;
		this.originFrame = origin;
		initialize();
	}

	/**
	 * Inicializa el frame con el contenido de la ventana CreateLabel
	 */
	private void initialize() {
		frmCreateLabel = new JFrame();
		frmCreateLabel.getContentPane().setBackground(SystemColor.inactiveCaption);
		getFrmCreateLabel().setTitle("Stack Overflow - Nueva etiqueta");
		getFrmCreateLabel().setBounds(550, 250, 450, 300);
		getFrmCreateLabel().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmCreateLabel().getContentPane().setLayout(null);
		
		JLabel lblLabelName = new JLabel("Nombre: ");
		lblLabelName.setBounds(10, 59, 63, 14);
		getFrmCreateLabel().getContentPane().add(lblLabelName);
		
		JTextField textFieldLabelName = new JTextField();
		textFieldLabelName.setBounds(99, 56, 278, 20);
		getFrmCreateLabel().getContentPane().add(textFieldLabelName);
		textFieldLabelName.setColumns(10);
		
		JLabel lblLabelDescription = new JLabel("Descripcion: ");
		lblLabelDescription.setBounds(10, 104, 79, 14);
		getFrmCreateLabel().getContentPane().add(lblLabelDescription);
		
		JTextPane textPaneDescription = new JTextPane();
		textPaneDescription.setBounds(99, 98, 278, 122);
		getFrmCreateLabel().getContentPane().add(textPaneDescription);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearEtiquetaNueva(textFieldLabelName, textPaneDescription);
			}
		});
		btnAgregar.setBounds(335, 231, 89, 23);
		getFrmCreateLabel().getContentPane().add(btnAgregar);
		
		JLabel lblNewLabel = new JLabel("Ingrese el nombre de la nueva etiqueta y una breve descripcion de esta.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 414, 34);
		getFrmCreateLabel().getContentPane().add(lblNewLabel);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnVolver.setBounds(10, 231, 89, 23);
		frmCreateLabel.getContentPane().add(btnVolver);
	}

	/**
	 * Obtiene el frame de la ventana CreateLabel.
	 * @return frmCreateLabel Retorna el frame de la ventana.
	 */
	public JFrame getFrmCreateLabel() {
		return frmCreateLabel;
	}

	/**
	 * Comprueba que no existan errores, agrega la nueva etiqueta creada al foro y vuelve al menu principal.
	 * @param textPaneDescription Elemento grafico donde se escribe la descripcion de la nueva etiqueta en forma de panel de texto.
	 */
	private void crearEtiquetaNueva(JTextField textFieldLabelName, JTextPane textPaneDescription) {
		String name = textFieldLabelName.getText();
		String description = textPaneDescription.getText();
		
		if(name == null || name.isBlank() || description == null || description.isBlank()) {
			JOptionPane.showMessageDialog(frmCreateLabel, "Los datos ingresados son invalidos.", "Datos invalidos", 0);
			return;
		}
		
		foro.agregarEtiquetaAlRegistro(name, description);
		
		frmCreateLabel.setVisible(false);
		OptionView ventanaMenu = new OptionView(foro);
		JOptionPane.showMessageDialog(ventanaMenu.getFrmMenuPrincipal(), "La etiqueta se ha creado exitosamente.", "Accion ejecutada correctemente", 1);
		ventanaMenu.getFrmMenuPrincipal().setVisible(true);
	}

	/**
	 * Vuelve a la venatana anterior a la ventana CreateLabel.
	 */
	private void volver() {
		frmCreateLabel.setVisible(false);
		originFrame.setVisible(true);
	}
}
