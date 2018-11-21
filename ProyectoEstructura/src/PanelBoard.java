//notas: IA.

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.NoSuchElementException;
import javax.swing.JPanel;

public class PanelBoard extends JPanel {
	
	private Cuadro[][] cuadros;
	private Cuadro actual;
	private Cuadro indefenso;
	private boolean turno;
	private int counter;
	private VentanaProyecto vp;
	private Cuadro maxInicial,maxFinal;

	
	
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public PanelBoard(VentanaProyecto vp, Cuadro[][] cuadros, Cuadro actual, Cuadro indefenso, boolean turno, int counter) {
		super();
		this.vp=vp;
		this.cuadros=cuadros;
		this.actual=actual;
		this.indefenso=indefenso;
		this.turno=turno;
		this.counter=counter;
	}
	public PanelBoard(VentanaProyecto vp){
		super();
		this.vp=vp;
		this.maxInicial=null;
		this.maxFinal=null;
		this.cuadros=new Cuadro[8][8];
		this.actual=null;
		this.indefenso=null;
		this.turno=true;
		this.setPreferredSize(new Dimension(600,600));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		boolean iswhite=false;
		
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				cuadros[i][j]=new Cuadro(iswhite,this,j,i);
				this.add(cuadros[i][j]);
			iswhite=!iswhite;
			}
			iswhite=!iswhite;
		}
		setup(0,false);
		setup(7,true);
	}
	public Cuadro getMaxInicial() {
		return maxInicial;
	}
	public void setMaxInicial(Cuadro maxInicial) {
		this.maxInicial = maxInicial;
	}
	public Cuadro getMaxFinal() {
		return maxFinal;
	}
	public void setMaxFinal(Cuadro maxFinal) {
		this.maxFinal = maxFinal;
	}
	public Cuadro getCuadro(int x, int y) {
		return this.cuadros[y][x];
	}
	public Cuadro getIndefenso() {
		return indefenso;
	}
	public void setIndefenso(Cuadro indefenso) {
		this.indefenso = indefenso;
	}
	public boolean checkPiece(int x,int y){
		if(this.cuadros[y][x].getPieza()!=null){
			return true;
		}
		return false;
	}
	public Cuadro getActual(){
		return this.actual;
	}
	public void setActual(Cuadro cuadro){
		this.actual=cuadro;
	}
	public boolean getTurno(){
		return this.turno;
	}
	public void nextTurn(){
		this.turno=!this.turno;
	}
	
	
	public boolean checkJaqueMate(boolean side){
		int pieceX=0;
		int	pieceY=0;
		Pieza temp=null;
		for(Cuadro[] x:cuadros){
			for(Cuadro y:x){
				if(y.getPieza()!=null){
					if(y.getPieza().getSide()==side){
						pieceX=y.getEx();
						pieceY=y.getEy();
						for(Cuadro[] a:cuadros){
							for(Cuadro b:a){
								if(b!=y){
									if(this.getCuadro(pieceX,pieceY).getPieza().valida(this.getCuadro(pieceX,pieceY), b.getEx(), b.getEy())){
										if(b.getPieza()!=null){
											if(b.getPieza().getSide()==side){
												continue;
											}else{
												temp=b.getPieza();
											}
										}
										this.getCuadro(b.getEx(),b.getEy()).moveHere(this.getCuadro(pieceX,pieceY));
										if(!this.checkJaque(side)){//checar jaque de nuevo
											this.getCuadro(pieceX,pieceY).moveHere(this.getCuadro(b.getEx(),b.getEy()));
											this.getCuadro(b.getEx(),b.getEy()).setPieza(temp);
											return false;
										}
										else{
											this.getCuadro(pieceX,pieceY).moveHere(this.getCuadro(b.getEx(),b.getEy()));
											this.getCuadro(b.getEx(),b.getEy()).setPieza(temp);
											temp=null;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	public boolean checkJaque(boolean side){// una optimizacion estaria bien :v
		int kingX=-1;
		int kingY=-1;
		for(Cuadro[] x:cuadros){
			for(Cuadro y:x){
				if(y.getPieza()!=null){
					if(y.getPieza().getValue()==200 && y.getPieza().getSide()==side){
						kingX=y.getEx();
						kingY=y.getEy();
						break;
					}
				}
			}
		}
		if(kingX==-1 || kingY==-1) throw new NoSuchElementException("Rey inexistente");
		
		for(int i=kingX+1;i<8;i++){//pa la derecha
			if(checkPiece(i,kingY)){
				if(cuadros[kingY][i].getPieza().getSide()!=side){
					if(cuadros[kingY][i].getPieza().getValue()==5) return true;
					else if(cuadros[kingY][i].getPieza().getValue()==10) return true;
					else break;
				}else break;
			}
		}
		for(int i=kingX-1;i>=0;i--){//pa la izquierda
			if(checkPiece(i,kingY)){
				if(cuadros[kingY][i].getPieza().getSide()!=side){
					if(cuadros[kingY][i].getPieza().getValue()==5) return true;
					else if(cuadros[kingY][i].getPieza().getValue()==10) return true;
					else break;
				}else break;
			}
		}
		for(int i=kingY-1;i>=0;i--){//pa arriba
			if(checkPiece(kingX,i)){
				if(cuadros[i][kingX].getPieza().getSide()!=side){
					if(cuadros[i][kingX].getPieza().getValue()==5) return true;
					else if(cuadros[i][kingX].getPieza().getValue()==10) return true;
					else break;
				}else break;
			}
		}
		for(int i=kingY+1;i<8;i++){//pa abajo
			if(checkPiece(kingX,i)){
				if(cuadros[i][kingX].getPieza().getSide()!=side){
					if(cuadros[i][kingX].getPieza().getValue()==5) return true;
					else if(cuadros[i][kingX].getPieza().getValue()==10) return true;
					else break;
				}else break;
			}
		}
		int dx=kingX+1;
		int dy=kingY+1;
		if(dx<8 && dy<8){
			if(!side && checkPiece(dx,dy)){// peon
				if(cuadros[dy][dx].getPieza().getSide()==true && cuadros[dy][dx].getPieza().getValue()==1){
					return true;
				}
			}
		}
		while(dx<8 && dy<8){//pa diagonal derecha abajo
			if(checkPiece(dx,dy)){
				if(cuadros[dy][dx].getPieza().getSide()!=side){
					if(cuadros[dy][dx].getPieza().getValue()==3) return true;
					else if(cuadros[dy][dx].getPieza().getValue()==10) return true;
					else break;
				}else break;
			}
			dx++;
			dy++;
		}
		dx=kingX+1;
		dy=kingY-1;
		if(dx<8 && dy>=0){
			if(side &&checkPiece(dx,dy)){//peon
				if(cuadros[dy][dx].getPieza().getSide()==false && cuadros[dy][dx].getPieza().getValue()==1){
					return true;
				}
			}
		}
		while(dx<8 && dy>=0){//pa diagonal derecha arriba
			if(checkPiece(dx,dy)){
				if(cuadros[dy][dx].getPieza().getSide()!=side){
					if(cuadros[dy][dx].getPieza().getValue()==3) return true;
					else if(cuadros[dy][dx].getPieza().getValue()==10) return true;
					else break;
				}else break;
			}
			dx++;
			dy--;
		}
		dx=kingX-1;
		dy=kingY+1;
		if(dx>=0 && dy<8){
			if(!side &&checkPiece(dx,dy)){//peon
				if(cuadros[dy][dx].getPieza().getSide()==true && cuadros[dy][dx].getPieza().getValue()==1){
					return true;
				}
			}
		}
		while(dx>=0 && dy<8){//pa diagonal izquierda abajo
			if(checkPiece(dx,dy)){
				if(cuadros[dy][dx].getPieza().getSide()!=side){
					if(cuadros[dy][dx].getPieza().getValue()==3) return true;
					else if(cuadros[dy][dx].getPieza().getValue()==10) return true;
					else break;
				}else break;
			}
			dx--;
			dy++;
		}
		dx=kingX-1;
		dy=kingY-1;
		if(dx>=0 && dy>=0){
			if(side && checkPiece(dx,dy)){//peon
				if(cuadros[dy][dx].getPieza().getSide()==false && cuadros[dy][dx].getPieza().getValue()==1){
					return true;
				}
			}
		}
		while(dx>=0 && dy>=0){//pa diagonal izquierda arriba
			if(checkPiece(dx,dy)){
				if(cuadros[dy][dx].getPieza().getSide()!=side){
					if(cuadros[dy][dx].getPieza().getValue()==3) return true;
					else if(cuadros[dy][dx].getPieza().getValue()==10) return true;
					else break;
				}else break;
			}
			dx--;
			dy--;
		}
		for(int i=-2;i<3;i++){// checa posibles casillas de caballos
			for(int j=-2;j<3;j++){
				dx=kingX+i;
				dy=kingY+j;
				if(dx<8 && dy<8 && dx>=0 && dy>=0){
					//System.out.println(i);
					//System.out.println(j);
					if((Math.abs(i)==1 && Math.abs(j)==2) || (Math.abs(i)==2 && Math.abs(j)==1)){
						
						if(checkPiece(dx,dy)){
							if(cuadros[dy][dx].getPieza().getSide()!=side){
								if(cuadros[dy][dx].getPieza().getValue()==4) return true;
							}
						}	
					}
					else if((Math.abs(i)==1 || i==0) && (Math.abs(j)==1 || j==0)){
						if(checkPiece(dx,dy)){
							if(cuadros[dy][dx].getPieza().getSide()!=side){
								if(cuadros[dy][dx].getPieza().getValue()==200) return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	//private boolean check
	public void setup(int pos, boolean side){
		cuadros[pos][0].setPieza(new Rook(side));
		cuadros[pos][7].setPieza(new Rook(side));
		cuadros[pos][1].setPieza(new Knight(side));
		cuadros[pos][6].setPieza(new Knight(side));
		cuadros[pos][2].setPieza(new Bishop(side));
		cuadros[pos][5].setPieza(new Bishop(side));
		cuadros[pos][4].setPieza(new King(side));
		cuadros[pos][3].setPieza(new Queen(side));
		if(!side){
			for(int i=0;i<8;i++){
				cuadros[pos+1][i].setPieza(new Pawn(side));
			}
		}
		else{
			for(int i=0;i<8;i++){
				cuadros[pos-1][i].setPieza(new Pawn(side));
			}
		}
	}
	public void close() {
		this.vp.dispose();
	}
	
	public void bot() {
		bot(0,false);
	}
	
	public int bot(int counter, boolean turno) {
		Cuadro cdi = null;
		Cuadro cdf = null;
		int signo;//variable para restar o sumar
		int puntMax=575757;
		int puntTemp=0;
		
		if(counter==5) {//caso base
			return 0;
		}
		else {
			if(turno==true) {//validación del signo
				signo=-1;
			}
			else {
				signo=1;
			}
			//System.out.println(counter);
			for(int i = 0; i < this.cuadros.length; i++) {
				for(int j = 0; j < this.cuadros.length; j++) {//doble for para recorrer el inicial
					
					if(this.getCuadro(i, j).getPieza() != null) {//si el inicial no es null
						
						Cuadro inicial = this.getCuadro(i, j);//asignamos nombre al cuadro inicial para mejor acceso
						
						if(inicial.getPieza().getSide() == turno) {//si es negro
							for(int k = 0; k<this.cuadros.length; k++) {
								for(int l = 0; l < this.cuadros.length; l++) {
									
									Cuadro finale = this.getCuadro(k, l);//asignamos el cuadro final
									//System.out.println(inicial.getPieza());
									//System.out.println(finale.getPieza());
									if(inicial.getPieza().valida(inicial, finale.getEx(), finale.getEy())) {//validamos que se pueda mover ahi
										if (finale.getPieza()==null) {//si no hay pieza en el lugar
											finale.moveHere(inicial);
											
											puntTemp = bot(counter+1,!turno);
											if(puntMax==575757 || puntMax<puntTemp) {
												puntMax=puntTemp;
												this.maxInicial=inicial;
												this.maxFinal=finale;
												/*if(counter==0) {
													cdi = inicial;
													cdf = finale;
												}*/
												
											}
											inicial.moveHere(finale);
										}
										else if(finale.getPieza().side!=turno) {
											Pieza enemigo = finale.getPieza();
											finale.moveHere(inicial);
											if(signo==1) {
												puntTemp+=enemigo.getValue();
											}
											else {
												puntTemp-=enemigo.getValue();
											}
											puntTemp+=bot(counter+1, !turno);
											
											if(puntMax==575757 || puntMax<puntTemp) {
												puntMax=puntTemp;
												this.maxInicial=inicial;
												this.maxFinal=finale;
												/*if(counter==0) {
													cdi = inicial;
													cdf = finale;
												}*/
												
											}
											inicial.moveHere(finale);
											finale.setPieza(enemigo);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		return puntMax;
	}
	
}


