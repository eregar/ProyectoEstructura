import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class PanelBoard extends JPanel {
	
	private Cuadro[][] cuadros=new Cuadro[8][8];
	
	public PanelBoard(){
		super();
		this.setPreferredSize(new Dimension(600,600));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		boolean iswhite=false;
		
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				this.add(cuadros[i][j]=new Cuadro(iswhite));
			iswhite=!iswhite;
			}
			iswhite=!iswhite;
		}
		cuadros[0][0].setPieza(new Queen(true,1,1));
	}
	
	public void setup(){
		//cuadros[0][0].setPieza(pieza);
	}
}
