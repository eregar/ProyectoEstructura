
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Knight extends Pieza{
	private static Image rey= new ImageIcon("caballoBlanco.png").getImage();
	private static Image reyB= new ImageIcon("caballoNegro.png").getImage();
	
	public Knight(boolean side){
		this.side=side;
		this.value=3;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean valida(Cuadro cuadro, int nextX,int nextY) {
		int cX=cuadro.getEx();
		int cY=cuadro.getEy();
		int dx=Math.abs(cX-nextX);
		int dy=Math.abs(cY-nextY);
		
		if((cuadro.getBoard().checkPiece(nextX, nextY) || cuadro.getBoard().getCuadro(nextX,nextY).getPieza()==null)) {
			if(dy==1) {
				if(dx==2) {
					return true;
				}
				else return false;
			}
			else if(dy==2) {
				if(dx==1) {
					return true;
				}
				else return false;
			}
			else if(dx==1) {
				if(dy==2) {
					return true;
				}
				else return false;
			}
			else if(dx==2) {
				if(dy==1) {
					return true;
				}
				else return false;
			}
			else return false;
		}
		else return false;
		
		
		
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
