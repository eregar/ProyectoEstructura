
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Queen extends Pieza{
	private static Image rey= new ImageIcon("reinaBlanca.png").getImage();
	private static Image reyB= new ImageIcon("reinaNegra.png").getImage();
	
	public Queen(boolean side){
		this.side=side;
		this.value=10;
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
		}
		else if(dx==0){
			if(dy>0){
				cY--;
				while(cY>nextY){
					if(cuadro.getBoard().checkPiece(cX, cY)) return false;
					//checa pieza
					cY--;
				}
			}else{
				cY++;
				while(cY<nextY){
					if(cuadro.getBoard().checkPiece(cX, cY)) return false;
					//checa pieza
					cY++;
				}
			}
			return true;
		}
		else if(dy==0){
			if(dx>0){
				cX--;
				while(cX>nextX){
					if(cuadro.getBoard().checkPiece(cX, cY)) return false;
					//checa pieza
					cX--;
				}
			}else{
				cX++;
				while(cX<nextX){
					if(cuadro.getBoard().checkPiece(cX, cY)) return false;
					//checa pieza
					cX++;
				}
			}
			return true;
		}
		else return false;
		
	}
	
		
	
	
	public void drawPieza(Graphics g){
		if(this.side) g.drawImage(rey, 0, 0,75,75, null);//draw blanca
		else g.drawImage(reyB, 0, 0,75,75, null); //draw negra
		//melapelas
	}
}