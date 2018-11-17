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

	//blanco o negro, el tablero, coordenada x, coordenada y
	public Cuadro(boolean side, PanelBoard pb, int x, int y){
		super();
		this.pb=pb;
		this.side=side;
		this.pieza=null;
		this.x=x;
		this.y=y;
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
		return this.x;
	}

	public int getEy() {
		return this.y;
	}
	public PanelBoard getBoard(){ // ????
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

	public boolean getSide() {
		return side;
	}

	public void setSide(boolean side) {
		this.side = side;
	}
	
	public void revisaIndefensos() {//checa si alguna pieza tiene indefenso y le baja al counter de turnos 1.
		if(this.pb.getIndefenso()!=null) {
			if(this.pb.getIndefenso().getPieza().getIndefenso()>0) {
				this.pb.getIndefenso().getPieza().setIndefenso(this.pb.getIndefenso().getPieza().getIndefenso()-1);
			}
			else{
				this.pb.setIndefenso(null);
			}
		}
		
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
				if(this.pieza==null){ //si no hay pieza en casilla picada
					if(this.pb.getActual().pieza.valida(this.pb.getActual(),this.getEx(),this.getEy())){//se hace la validacion de movimiento, si es peon se vuelve indefenso
						this.setPieza(this.getBoard().getActual().getPieza());//muevete aqui
						if(this.pb.getActual().equals(this.pb.getIndefenso())) {
							this.pb.setIndefenso(this);//refresh indefenso
						}
						this.getBoard().getActual().setPieza(null);// quita la pieza del de antes
						this.revisaIndefensos();
						
					}
					else if(this.pb.getActual().getPieza().getValue()==1) {//validacion de la regla del peon de la quinta fila 
						int yA = this.pb.getActual().getEy();
						if(yA==3 || yA==4) {//si estan en las casillas 5 de sus lados
							int dy = yA-this.getEy();
							if(this.pb.checkPiece(this.getEx(), yA)) {//si no es null la casilla de a lado
								Cuadro adyacente=this.pb.getCuadro(this.getEx(),yA);
								boolean lado=this.pb.getActual().getPieza().getSide();
								if(adyacente.getPieza().getValue()==1 && adyacente.getSide()!=this.side) {// si la pieza de a lado es un peon y es del color opuesto
									if(((lado && dy==1) || (!lado && dy==-1)) && adyacente.equals(this.pb.getIndefenso())) {
										this.setPieza(this.getBoard().getActual().getPieza());
										adyacente.setPieza(null);
										this.getBoard().getActual().setPieza(null);
										this.pb.setIndefenso(null);
									}
								}
							}
						}
					}
				}
				else if(this.pieza.getSide()!=this.pb.getActual().pieza.getSide()){ // si es del otro bando
					if(this.pb.getActual().pieza.valida(this.pb.getActual(),this.getEx(),this.getEy())){
						this.setPieza(this.getBoard().getActual().getPieza());//mata al otro
						if(this.pb.getActual().equals(this.pb.getIndefenso())) {
							this.pb.setIndefenso(this);
						}
						this.getBoard().getActual().setPieza(null);
						this.revisaIndefensos();
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
