
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Knight extends Pieza{
	private static Image rey= new ImageIcon("caballoBlanco.png").getImage();
	private static Image reyB= new ImageIcon("caballoNegro.png").getImage();
	
	public Knight(boolean side){
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
