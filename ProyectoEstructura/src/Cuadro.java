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
	
	

	public boolean getSide() {
		return side;
	}

	public void setSide(boolean side) {
		this.side = side;
	}
	public void revisaIndefensos() {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(this.pb.checkPiece(i, j)) {
					if(this.pb.getCuadros()[i][j].getPieza().getIndefenso()>0) {
						this.pb.getCuadros()[i][j].getPieza().setIndefenso(this.pb.getCuadros()[i][j].getPieza().getIndefenso()-1);
					
					}
				}
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
				if(this.pieza==null){ //si no hay pieza
					
					if(this.pb.getActual().pieza.valida(this.pb.getActual(),this.getEx(),this.getEy())){//si se hace la validación de movimiento
						this.setPieza(this.getBoard().getActual().getPieza());//muevete aqui
						this.getBoard().getActual().setPieza(null);
						//this.revisaIndefensos();
						
					}
					else if(this.pb.getActual().getPieza().getValue()==1) {//validación de la regla del peon de la quinta fila 
						int xA = this.pb.getActual().getEx();
						int yA = this.pb.getActual().getEy();
						if(yA==3 || yA==4) {//si estan en las casillas 5 de sus lados
							int dx = Math.abs(xA-this.getEx());
							int dy = xA-this.getEx();
							if(this.pb.checkPiece(this.getEx(), yA)) {//si no es null la casilla de a lado
								System.out.println("hola");
								if(this.pb.getCuadros()[this.getEx()][yA].getPieza().getValue()==1 && !this.pb.getCuadros()[this.getEx()][yA].getSide()==this.side) {// si la pieza de a lado es un peon y es del color opuesto
									if(((this.side && dx==1 && dy==1) || (!this.side && dx==1 && dy==-1)) /*&& this.pb.getCuadros()[this.getEx()][yA].getPieza().indefenso==2*/) {
										this.setPieza(this.getBoard().getActual().getPieza());
										this.pb.getCuadros()[this.getEx()][yA].setPieza(null);
										this.getBoard().getActual().setPieza(null);
									}
								}
							}
						}
					}
				}
				else if(this.pieza.getSide()!=this.pb.getActual().pieza.getSide()){ // si es del otro bando
					if(this.pb.getActual().pieza.valida(this.pb.getActual(),this.getEx(),this.getEy())){
						this.setPieza(this.getBoard().getActual().getPieza());//mata al otro
						this.getBoard().getActual().setPieza(null);
						//this.revisaIndefensos();
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
