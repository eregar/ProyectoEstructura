import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class King extends Pieza{
	private static Image rey= new ImageIcon("reyBlanco.png").getImage();
	private static Image reyB= new ImageIcon("reyNegro.png").getImage();
	
	public King(boolean side,Cuadro position){
		this.side=side;
		this.position=position;
	}
	
	public void drawPieza(Graphics g){
		if(this.side) g.drawImage(rey, 0, 0, null);//draw blanca
		else g.drawImage(reyB, 0, 0, null); //draw negra
	}
}
