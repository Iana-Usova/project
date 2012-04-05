package FIT_9202_Usova.SinForm;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Main {
	public static void main(String argc[]) {
		final MainWindow frame = new MainWindow();
		frame.setTitle("SinForm");
		frame.createGUI();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
