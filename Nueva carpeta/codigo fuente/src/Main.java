import java.awt.EventQueue;

import models.Stack;
import views.PrincipalView;

/**
 * Clase que ejecuta el programa.
 * @version 1.13, 10/01/2021
 * @author Christofer Rodriguez
 */

public class Main {
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stack foro = new Stack();
					PrincipalView window = new PrincipalView(foro);
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

