package gui.manager;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GUIManager extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public GUIManager() {
		this.setSize(300, 300);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	

}
