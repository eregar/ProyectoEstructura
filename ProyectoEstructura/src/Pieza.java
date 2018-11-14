import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Pieza {
	protected boolean side;
	
	
	public boolean getSide() {
		return side;
	}
	public void setSide(boolean side) {
		this.side = side;
	}
	public boolean valida(Cuadro elactual,int nextX,int nextY){
		return false;
	}
	//solo para que se puedan guardar en una variable, no tienen metodos en comun aun
	//public void pintaPieza(Graphics g);
	public void move(){
	}


	public void drawPieza(Graphics g) {
		// TODO Auto-generated method stub
		//notghin
	}
}
