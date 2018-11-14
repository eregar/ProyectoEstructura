
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Queen extends Pieza{
	private static Image rey= new ImageIcon("reinaBlanca.png").getImage();
	private static Image reyB= new ImageIcon("reinaNegra.png").getImage();
	
	public Queen(boolean side,int x,int y){
		this.side=side;
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