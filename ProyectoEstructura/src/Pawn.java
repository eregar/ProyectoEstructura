
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Pawn extends Pieza{
	private static Image rey= new ImageIcon("peonBlanco.png").getImage();
	private static Image reyB= new ImageIcon("peonNegro.png").getImage();
	private boolean firstTurn;
	
	public Pawn(boolean side){
		this.side=side;
		firstTurn=true;
	}
	public boolean valida(Cuadro actual, int nextX,int nextY){
		int signo;
		int cX=actual.getEx();
		int cY=actual.getEy();
		int dx=Math.abs(cX-nextX);
		int dy=cY-nextY;
		
		if(this.side) {
			signo = 1;
		}
		else {
			signo = -1;
		}
		
		
		if (signo*dy<=2 && dx==0){
			if(signo*dy==1 && !actual.getBoard().checkPiece(nextX, nextY)) {
				return true;
			}
			else if (signo*dy==2 && !actual.getBoard().checkPiece(nextX, nextY) && firstTurn) {
				this.firstTurn = false;
				return true;
			}
			else {
				return false;
			}
		}
		else if (signo*dy==1 && dx==1) {
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
