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
	private Pieza pieza;
	private PanelBoard pb;
	private boolean side;
	private int x,y;

	//cada cuadro pasar un numero 1-6, boolean, coordenadas
	public Cuadro(boolean side, PanelBoard pb, int x, int y){
		super();
		this.pb=pb;
		this.side=side;
		this.setPreferredSize(new Dimension(75,75));
		this.addMouseListener(this);
		/*addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				vDJ.nuke();
			}});*/
		this.redraw();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(this.pieza!=null)this.pieza.drawPieza(g);
	}
	
	public int getEx() {
		return x;
	}

	public int getEy() {
		return y;
	}
	public PanelBoard getBoard(){
		return this.pb;
	}
	
	public void redraw(){
		if(side) this.setBackground(Color.ORANGE);
		else this.setBackground(Color.WHITE);
	}
	
	public void setPieza(Pieza pieza){
		this.pieza=pieza;
		repaint();
	}
	public Pieza getPieza(){
		return this.pieza;
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
			if(this.pb.getActual()!=null){// si ya hay un verde seleccionado
				this.pb.getActual().redraw(); // quita lo verde(sigue seleccionado)
				
				if(this.pieza==null){ //si no hay pieza
					if(this.pb.getActual().pieza.valida(this.pb.getActual(),this.getX(),this.getY())){
						//muevete aqui
						System.out.println("hola");
					}
				}
				else if(this.pieza.getSide()!=this.pb.getActual().pieza.getSide()){ // si es del otro bando
					if(this.pb.getActual().pieza.valida(this.pb.getActual(),this.getX(),this.getY())){
						//matar a esta pieza
						System.out.println("hola");
					}
				}
				this.pb.setActual(null);//deselecciona, es al ultimo
			}else{
				if(this.pieza!=null){//si tiene pieza
					this.pb.setActual(this);//selecciona verde
					this.setBackground(Color.GREEN);
				}
			}
	}
}
