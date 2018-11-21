import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
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
	
	public void choose(boolean side){
		String[] piezas = {"Reina", "Torre", "Caballo", "Alfil"};
		int x = JOptionPane.showOptionDialog(null, "Elige una pieza",
		        "",
		        JOptionPane.DEFAULT_OPTION,
		        JOptionPane.INFORMATION_MESSAGE,
		        null, piezas, piezas[0]);
		switch(x){
		case -1: this.setPieza(new Queen(side)); break;
		case 0: this.setPieza(new Queen(side)); break;
		case 1: this.setPieza(new Rook(side)); break;
		case 2: this.setPieza(new Knight(side)); break;
		case 3: this.setPieza(new Bishop(side)); break;
		}
		
	}
	
	public void revisaIndefensos() {//checa si alguna pieza tiene indefenso y le baja al counter de turnos 1.
		if(this.pb.getIndefenso()!=null) {//hice una mexicanada AIUDAAAAAAAAAAAAAAAA
			if(this.pb.getIndefenso().getPieza()!=null){
				if(this.pb.getIndefenso().getPieza().getIndefenso()>0) {
					this.pb.getIndefenso().getPieza().setIndefenso(this.pb.getIndefenso().getPieza().getIndefenso()-1);
				}
				else{
					this.pb.setIndefenso(null);
				}
			}
			else{
				this.pb.setIndefenso(null);
			}
		}
		
	}
	public void moveHere(Cuadro cuadro){
		this.setPieza(cuadro.getPieza());//muevete aqui
		cuadro.setPieza(null);
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
		Pieza temp;
		// TODO Auto-generated method stub
			if(this.pb.getActual()!=null){// si ya hay un verde seleccionado
				this.pb.getActual().redraw(); // quita lo verde(sigue seleccionado)
				if(this.pieza==null){ //si no hay pieza en casilla picada
					if(this.pb.getActual().pieza.valida(this.pb.getActual(),this.getEx(),this.getEy())){//se hace la validacion de movimiento,
						this.setPieza(this.getBoard().getActual().getPieza());//muevete aqui
						if(this.pb.getActual().equals(this.pb.getIndefenso())) {
							this.pb.setIndefenso(this);//refresh indefenso
						}
						this.getBoard().getActual().setPieza(null);// quita la pieza del de antes
						if(this.pb.checkJaque(this.pb.getTurno())){//ve jaques, regresa a estado normal
							this.getBoard().getActual().setPieza(this.pieza);
							this.setPieza(null);
							if(this.equals(this.pb.getIndefenso())) {
								this.pb.setIndefenso(this.pb.getActual());//undo indefenso
							}
							this.pb.setActual(null);//deselecciona
							System.out.println("ESTARIAS EN JAQUE");
							return;
						}
						if(this.pieza.getValue()==1){// es peon?
							 if(this.y==7 || this.y==0) choose(this.pieza.getSide()); // si llego a ultima de su color cambia
						}
						this.pb.setCounter(this.pb.getCounter()+1);
						this.pieza.realMove(this);//hace cosas como quitar el firstTurn o asignar indefenso
						this.revisaIndefensos();
						if(this.pb.checkJaque(!this.pb.getTurno())){//del siguiente turno
							if(this.pb.checkJaqueMate(!this.pb.getTurno())){
								String dialog;
								if(this.pb.getTurno()) dialog="El Jugador";
								else dialog="La maquina";
								JOptionPane.showMessageDialog(this.pb, "Jaque mate! Gana "+dialog);
								this.pb.close();
							}
						}
						else if(this.pb.checkJaqueMate(!this.pb.getTurno())){
							JOptionPane.showMessageDialog(this.pb, "Tablas! ya no hay movimientos dosponibles");
							this.pb.close();
						}
						this.pb.nextTurn();
						this.pb.bot();
						this.pb.nextTurn();

						this.pb.getMaxFinal().moveHere(this.pb.getMaxInicial());
						//this.pb.getMaxFinal().getPieza().realMove(this.pb.getMaxFinal());
						
						//System.out.println("turno: "+this.pb.getTurno());
						
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
										this.setPieza(this.getBoard().getActual().getPieza()); //muevete aqui
										temp=adyacente.getPieza();
										adyacente.setPieza(null); //mata a la pieza
										this.getBoard().getActual().setPieza(null); //quita a la pieza de antes
										if(this.pb.checkJaque(this.pb.getTurno())){//ve jaques, regresa a estado normal
											this.getBoard().getActual().setPieza(this.pieza);//undo quitar pieza de antes
											this.setPieza(null);// undo muevete aqui
											adyacente.setPieza(temp);
											this.pb.setActual(null);//deselecciona
											System.out.println("ESTARIAS EN JAQUE");
											return;
										}
										this.pb.setCounter(this.pb.getCounter()+1);
										this.pb.setIndefenso(null); //quita indefenso
										if(this.pb.checkJaque(!this.pb.getTurno())){//del siguiente turno
											if(this.pb.checkJaqueMate(!this.pb.getTurno())){
												String dialog;
												if(this.pb.getTurno()) dialog="El Jugador!";
												else dialog="La maquina!";
												JOptionPane.showMessageDialog(this.pb, "Jaque mate! Gana "+dialog);
												this.pb.close();
											}
										}
										else if(this.pb.checkJaqueMate(!this.pb.getTurno())){
											JOptionPane.showMessageDialog(this.pb, "Tablas! ya no hay movimientos dosponibles");
											this.pb.close();
										}
										this.pb.nextTurn();
										this.pb.bot();
										this.pb.nextTurn();
										this.pb.getMaxFinal().moveHere(this.pb.getMaxInicial());
										//this.pb.getMaxFinal().getPieza().realMove(this.pb.getMaxFinal());

										/*this.pb.bot();
										System.out.println(this.pb.getMaxFinal().getPieza());
										System.out.println(this.pb.getMaxInicial().getPieza());
										this.pb.getMaxFinal().moveHere(this.pb.getMaxInicial());*/
										//System.out.println("Turno: "+this.pb.getTurno());
									}
								}
							}
						}
					}
				}
				else if(this.pieza.getSide()!=this.pb.getActual().pieza.getSide()){ // si es del otro bando
					if(this.pb.getActual().pieza.valida(this.pb.getActual(),this.getEx(),this.getEy())){
						temp=this.getPieza();
						this.setPieza(this.getBoard().getActual().getPieza());//mata al otro, muevete
						if(this.pb.getActual().equals(this.pb.getIndefenso())) {
							this.pb.setIndefenso(this);
						}
						this.getBoard().getActual().setPieza(null);// quita pieza de antes
						if(this.pb.checkJaque(this.pb.getTurno())){//ve jaques, regresa a estado normal
							this.getBoard().getActual().setPieza(this.pieza); //undo muevete aqui
							this.setPieza(temp); //undo matar pieza
							if(this.equals(this.pb.getIndefenso())) {
								this.pb.setIndefenso(this.pb.getActual());//undo indefenso
							}
							this.pb.setActual(null);//deselecciona
							System.out.println("ESTARIAS EN JAQUE");
							return;
						}
						if(this.pieza.getValue()==1){// es peon?
							 if(this.y==7) choose(this.pieza.getSide()); // si llego a ultima de su color cambia
							 else if(this.y==0) choose(this.pieza.getSide()); //x2
						}
						this.pb.setCounter(0);
						this.pieza.realMove(this);// hace cosas como quitar el firstTurn o asignar indefenso
						this.revisaIndefensos();
						if(this.pb.checkJaque(!this.pb.getTurno())){//del siguiente turno
							if(this.pb.checkJaqueMate(!this.pb.getTurno())){
								String dialog;
								if(this.pb.getTurno()) dialog="El Jugador";
								else dialog="La maquina";
								JOptionPane.showMessageDialog(this.pb, "Jaque mate! Gana "+dialog);
								this.pb.close();
							}
						}
						else if(this.pb.checkJaqueMate(!this.pb.getTurno())){
							JOptionPane.showMessageDialog(this.pb, "Tablas! ya no hay movimientos dosponibles");
							this.pb.close();
						}
						this.pb.nextTurn();
						this.pb.bot();
						this.pb.nextTurn();
						this.pb.getMaxFinal().moveHere(this.pb.getMaxInicial());
						//this.pb.getMaxFinal().getPieza().realMove(this.pb.getMaxFinal());
						//System.out.println("Turno: "+this.pb.getTurno());
					}
				}
				this.pb.setActual(null);//deselecciona, es al ultimo
				
			}else{
				if(this.pieza!=null){//si tiene pieza
					if(this.pieza.getSide()){
						this.pb.setActual(this);//selecciona verde
						this.setBackground(Color.GREEN);
					}
					
				}
			}
			if(this.pb.getCounter()==50) {
				JOptionPane.showMessageDialog(this.pb, "Perdiste we xdxd");
				this.pb.close();
			}
			/*if(this.pb.getTurno()==false) {
				this.repaint();
				this.pb.bot();
				System.out.println(this.pb.getMaxFinal().getEx());
				System.out.println(this.pb.getMaxFinal().getEy());
				System.out.println(this.pb.getMaxInicial().getPieza());
				this.pb.getMaxFinal().moveHere(this.pb.getMaxInicial());
				
			}*/
	}
}
