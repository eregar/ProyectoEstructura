import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Pieza {
	protected boolean side;
	protected int value;
	protected int indefenso;
	protected boolean firstTurn;

	
	
	
	public int getIndefenso() {
		return indefenso;
	}
	public void setIndefenso(int indefenso) {
		this.indefenso = indefenso;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean getSide() {
		return side;
	}
	public void setSide(boolean side) {
		this.side = side;
	}
	public boolean getFirstTurn() {
		return firstTurn;
	}
	public boolean valida(Cuadro elactual,int nextX,int nextY){
		return false;
	}
	public void realMove(Cuadro cuadro){
		return;
	}


	public void drawPieza(Graphics g) {
		// TODO Auto-generated method stub
		//notghin
	}
}
