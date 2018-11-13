import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class Cuadro extends JPanel implements MouseListener{
	Pieza pieza;
	public Cuadro(boolean white){
		super();
		this.setPreferredSize(new Dimension(75,75));
		this.pieza= new King(white,this);// quita esto men es solo prueba
		this.addMouseListener(this);
		/*addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				vDJ.nuke();
			}});*/
		
		if(white) this.setBackground(Color.ORANGE);
		else this.setBackground(Color.WHITE);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.pieza.drawPieza(g);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// can frikking start running?
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("click!");
	}
}
