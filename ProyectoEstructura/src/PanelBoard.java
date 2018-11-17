//notas: enroque, peon que se transforma, 50 turno, jaques y jaques mate.

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class PanelBoard extends JPanel {
	
	private Cuadro[][] cuadros;
	private Cuadro actual;
	private Cuadro indefenso;
	
	public Cuadro getCuadro(int x, int y) {
		return this.cuadros[y][x];
	}
	public void setCuadros(Cuadro[][] cuadros) {
		this.cuadros = cuadros;
	}
	public PanelBoard(){
		super();
		this.cuadros=new Cuadro[8][8];
		this.actual=null;
		this.indefenso=null;
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
	public Cuadro getIndefenso() {
		return indefenso;
	}
	public void setIndefenso(Cuadro indefenso) {
		this.indefenso = indefenso;
	}
	public boolean checkPiece(int x,int y){
		//if((x<=7 && x>=0)&&(y<=7 && y>=0))
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
}
