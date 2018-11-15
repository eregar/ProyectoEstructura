
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
		int cX=cuadro.getEx();
		int cY=cuadro.getEy();
		int dx=Math.abs(cX-nextX);
		int dy=Math.abs(cY-nextY);
		
		if(dx==dy){
			if(cX>nextX){
				if(cY>nextY){
					//arriba izquierda
					cY--;
					cX--;
					while(cX!=nextX) {
						if(cuadro.getBoard().checkPiece(cX, cY))return false;
						cY--;
						cX--;
					}
					return true;
				}else{
					//abajo izquierda
					cY++;
					cX--;
					while(cX!=nextX) {
						if(cuadro.getBoard().checkPiece(cX, cY))return false;
						cY++;
						cX--;
					}
					return true;
				}
			}else{
				if(cY>nextY){
					//arriba derecha
					cY--;
					cX++;
					while(cX!=nextX) {
						if(cuadro.getBoard().checkPiece(cX, cY))return false;
						cY--;
						cX++;
					}
					return true;
				}else{
					//abajo derecha
					cY++;
					cX++;
					while(cX!=nextX) {
						if(cuadro.getBoard().checkPiece(cX, cY))return false;
						cY++;
						cX++;
					}
					return true;
				}
			}
		}else return false;
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