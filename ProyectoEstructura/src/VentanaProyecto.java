import java.awt.Dimension;

import javax.swing.JFrame;

public class VentanaProyecto extends JFrame {
	
	public VentanaProyecto(){
		super("chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new PanelBoard());
		this.pack();
		this.setResizable(false);
		//this.setMinimumSize(new Dimension(401,401));
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		VentanaProyecto ventana1 = new VentanaProyecto();
	}
}
