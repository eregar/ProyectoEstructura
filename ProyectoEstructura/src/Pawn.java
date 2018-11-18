
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Pawn extends Pieza{
	private static Image rey= new ImageIcon("peonBlanco.png").getImage();
	private static Image reyB= new ImageIcon("peonNegro.png").getImage();
	private boolean firstTurn;
	
	
	public Pawn(boolean side){
		this.side=side;
		firstTurn=true;
		this.value=1;

	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
	public boolean valida(Cuadro actual, int nextX,int nextY){
		int signo;
		int cX=actual.getEx();
		int cY=actual.getEy();
		int dx=Math.abs(cX-nextX);
		int dy=cY-nextY;
		
		if(this.side) signo = 1;
		else signo = -1;
		
		if (signo*dy<=2 && dx==0){//si se quiere mover uno o dos espacios adelante
			if(signo*dy==1 && !actual.getBoard().checkPiece(nextX, nextY)) {//si la distancia es 1 y no hay piezas en el lugar
				return true;
			}
			else if (signo*dy==2 && !actual.getBoard().checkPiece(nextX, nextY) && firstTurn && !actual.getBoard().checkPiece(cX, cY+(-1*signo))) {//si la distancia es 2 y es su primer turno y no hay piezas en el lugar ni en el lugar antes de ese
				this.firstTurn = false;
				this.indefenso=2;
				actual.getBoard().setIndefenso(actual.getBoard().getCuadro(nextX,nextY));//dobabesxdxdxd
				return true;
			}
			else {
				return false;
			}
		}
		else if (signo*dy==1 && dx==1) {//para matar a alguien en diagonal
			if (actual.getBoard().checkPiece(nextX, nextY)) {
				return true;
			}
			else {
				return false;
			}
		}
		
		else {
			return false;
		}
		
	}
	
	
	
	public void drawPieza(Graphics g){
		if(this.side) g.drawImage(rey, 0, 0,75,75, null);//draw blanca
		else g.drawImage(reyB, 0, 0,75,75, null); //draw negra
		//melapelas
	}
	public void move(){
		//cambias pocision
		//haces repaint
	}
}
