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
	
	
	public void bot(){
		int maxScore,maxX,maxY,temp,signo;
		Cuadro[][] cuadros= new Cuadro[8][8];
		System.arraycopy(this.cuadros, 0, cuadros, 0, 8);
		Cuadro actual = this.actual;
		Cuadro indefenso = this.indefenso;
		boolean turno = this.turno;
		int counter = this.counter;
		VentanaProyecto vp = this.vp;
		maxX=0;
		maxY=0;
		temp=0;
		maxScore=0;
		int pieceX=0;
		int pieceY=0;
		
		for(Cuadro[] a:cuadros){
			for(Cuadro b:a){
				if(b.getPieza()!=null){
					if(b.getPieza().getSide()==false){
						for(Cuadro[] x:cuadros){
							for(Cuadro y:x){
								temp=0;
								pieceX=b.getEx();
								pieceY=b.getEy();
								if(b!=y){
									//mueve la pieza a un lugar disponible
									if(this.getCuadro(pieceX,pieceY).getPieza().valida(this.getCuadro(pieceX,pieceY), y.getEx(), y.getEy())){
										if(y.getPieza()!=null){
											if(y.getPieza().getSide()==false){
												continue;
											}else{
												//calcular score (tiene que hacerse simultaneamente) 
												temp=y.getPieza().getValue();
												//mover pieza
											}
										}else{
											//mover pieza 
										}
										temp=bot(temp,true,this.cuadros,actual,indefenso,1);
										if(temp>maxScore){
											maxScore=temp;
											maxX=pieceX;
											maxY=pieceY;
										}
										
									}
								}
								//(reseteas)regresas la pieza
							}
						}
					}
				}
			}
		}
		//mueve la pieza realmente, haz repaint, turno cambialo a true
	}
	public int bot(int maxScore, boolean turno, Cuadro[][] cuadros, Cuadro actual, Cuadro indefenso, int counter ){
		int temp,signo;
		temp=0;
		int maxScore2=0;
		int pieceX=0;
		int pieceY=0;
		
		if(turno)signo=1;
		else signo=-1;
		
		if(counter==3){
			return 0;
		}else{
			for(Cuadro[] a:cuadros){//for doble
				for(Cuadro b:a){
					if(b.getPieza()!=null){// si hay una pieza
						if(b.getPieza().getSide()==turno){//si es del color del turno
							for(Cuadro[] x:cuadros){//for doble (para checar espacios en donde s epuede mover)
								for(Cuadro y:x){
									pieceX=b.getEx(); //coordenadas actuales
									pieceY=b.getEy();
									if(b!=y){
										//mueve la pieza a un lugar disponible
										if(this.getCuadro(pieceX,pieceY).getPieza().valida(this.getCuadro(pieceX,pieceY), y.getEx(), y.getEy())){
											if(y.getPieza()!=null){
												if(y.getPieza().getSide()==turno){
													continue;
												}else{
													temp+=-signo*y.getPieza().getValue();
													//mover pieza
												}
											}else{
												//mover pieza 
												//checar si estas cubriendo otra pieza
											}
											if(temp>maxScore2){
												maxScore2=temp;
											}
										}
									}
									//(reseteas)regresas la pieza
								}
							}						
							//llama recursiva para calcular score
							// checa si score es mayor y si si pos la regresa
						}
					}
				}
			}
			return maxScore2+maxScore+bot(maxScore,!turno,cuadros,actual,indefenso,counter+1);
		}
	}
	
	
	
	
	
	
		/*temp=0;
		for(Cuadro[] a:cuadros){
			for(Cuadro b:a){
				if(b.getPieza()!=null){
					temp=calculaScore(b.getEx(),b.getEy());
					//llamar a la funcion recursiva que te saca el score de esa pieza
				}
				if(maxScore<temp){
					maxScore=temp;
					//set variable maxCuadro		
				}
			}
		}
		//mover las piezas
		//cambiar turno a true
	}
	private int calculaScore(int x, int y){
		PanelBoard newBoard = new PanelBoard(this.vp,this.cuadros, this.actual, this.indefenso, this.turno, this.counter);
		int maxScore=-500;
		for(Cuadro[] a:cuadros){
			for(Cuadro b:a){
				if(b!=this.getCuadro(x, y)) {
					if(this.getCuadro(x, y).getPieza().valida(this.getCuadro(x, y), b.getEx(), b.getEy())) {
						int scoreTmp= this.calculaScore(x, y,b.getEx(),b.getEy(),0,0);
						if(maxScore<scoreTmp) {
							maxScore=scoreTmp;
						}
					}
				}
			}
		}
		
		return maxScore;
		//recursividad
	}
	
	private int calculaScore(int x, int y, int nX, int nY, int counter, int score){
		int scoreComb=0;
		int xNew=0;
		int yNew=0;
		if(counter==3) {
			return score; 
		}
		else {
			
			//todo el cagadero
			scoreComb=2;
			scoreComb+=score;
			return calculaScore(xNew,yNew,counter+1,scoreComb);
		}
		
		
		
	}*/
}


