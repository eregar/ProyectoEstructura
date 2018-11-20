
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Rook extends Pieza{
	private static Image rey= new ImageIcon("torreBlanca.png").getImage();
	private static Image reyB= new ImageIcon("torreNegra.png").getImage();
	
	public Rook(boolean side){
		this.side=side;
		this.value=5;
		this.firstTurn=true;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public void realMove(Cuadro cuadro){
		this.firstTurn=false;
	}
	public boolean valida(Cuadro cuadro, int nextX,int nextY){
		int cX=cuadro.getEx();
		int cY=cuadro.getEy();
		int dx=cX-nextX;
		int dy=cY-nextY;
		if(dx==0){
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
		
	}
}
