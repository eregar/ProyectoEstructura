
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Bishop extends Pieza{
	private static Image rey= new ImageIcon("alfilBlanco.png").getImage();
	private static Image reyB= new ImageIcon("alfilNegro.png").getImage();
	
	public Bishop(boolean side){
		this.side=side;
	}
	
	public boolean valida(Cuadro cuadro, int nextX,int nextY) {
		int signo;
		int sX;
		int sY;
		int cX=cuadro.getEx();
		int cY=cuadro.getEy();
		int dx=cX-nextX;
		int dy=cY-nextY;
		
		if((cuadro.getBoard().checkPiece(nextX, nextY) || cuadro.getBoard().getCuadros()[nextY][nextX].getPieza()==null)) {
			System.out.println(cX+","+cY);
			if(dx==dy) {
				if(dx>0) {
					signo=-1;
				}
				else {
					signo=1;
				}
				
				cY=cY+signo;
				cX=cX+signo;
				System.out.println(cX+","+cY);
				while(cX!=nextX) {
					if(cuadro.getBoard().checkPiece(cX, cY)) {
						return false;
					}
					cY=cY+signo;
					cX=cX+signo;
					System.out.println(cX+","+cY);
				}
				return true;
			}
			else if(dx==-dy) {
				if(dy>0) {
					sY=1;
				}
				else {
					sY=-1;
				}
				if(dx>0) {
					sX=-1;
				}
				else {
					sX=1;
				}
				
				cY+=sY;
				cX+=sX;
				System.out.println(cX+","+cY);
				while(cX!=nextX) {
					if(cuadro.getBoard().checkPiece(cX, cY)) {
						return false;
					}
					cY+=sY;
					cX+=sX;
					System.out.println(cX+","+cY);
				}
				return true;
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