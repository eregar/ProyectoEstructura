
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class King extends Pieza{
	private static Image rey= new ImageIcon("reyBlanco.png").getImage();
	private static Image reyB= new ImageIcon("reyNegro.png").getImage();
	private boolean firstTurn;
	
	public King(boolean side){
		this.side=side;
		firstTurn=true;
	}
	public boolean valida(int cX, int cY, int nextX,int nextY){
		int dx=Math.abs(cX-nextX);
		int dy=Math.abs(cY-nextY);
		if(dx>1){
			return false;
		}
		else if(dy>1){
			return false;
		}
		else{
			return true;
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
