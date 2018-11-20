
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class King extends Pieza{
	private static Image rey= new ImageIcon("reyBlanco.png").getImage();
	private static Image reyB= new ImageIcon("reyNegro.png").getImage();
	//private boolean firstTurn;
	
	public King(boolean side){
		this.side=side;
		firstTurn=true;
		this.value=200;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean valida(Cuadro cuadro, int nextX,int nextY){
		int dx=nextX-cuadro.getEx();
		int dy=Math.abs(cuadro.getEy()-nextY);
		int lado;
		if(dx>=0) lado=1;
		else lado=-1;
		
		//enroque
		if(dx==lado*2 && dy==0 && this.firstTurn && (!cuadro.getBoard().checkJaque(this.side))){
			boolean res=true;
			if(!cuadro.getBoard().checkPiece(cuadro.getEx()+lado, cuadro.getEy())){
				cuadro.getBoard().getCuadro(cuadro.getEx()+lado, cuadro.getEy()).moveHere(cuadro);
				if(cuadro.getBoard().checkJaque(this.side)) res= false;
				else if(lado==1){
					if(cuadro.getBoard().checkPiece(7, cuadro.getEy())){
						if(!cuadro.getBoard().getCuadro(7, cuadro.getEy()).getPieza().getFirstTurn()){
							res=false;
						}
					}else res=false;
				}
				else{
					if(cuadro.getBoard().checkPiece(0, cuadro.getEy())){
						if(!cuadro.getBoard().getCuadro(0, cuadro.getEy()).getPieza().getFirstTurn()){
							res=false;
						}
						if(cuadro.getBoard().checkPiece(1, cuadro.getEy())) res=false;
					}else res=false;
				}
				cuadro.moveHere(cuadro.getBoard().getCuadro(cuadro.getEx()+lado, cuadro.getEy()));
			}else return false;
			return res;
		}
		else if(dx*lado>1){
			return false;
		}
		else if(dy>1){
			return false;
		}
		else{
			return true;
		}
		
	}
	
	public void realMove(Cuadro cuadro){
		if(this.firstTurn){
			this.firstTurn=false;
			if(cuadro.getEy()==0 || cuadro.getEy()==7){
				if(cuadro.getEx()==2){
					cuadro.getBoard().getCuadro(3, cuadro.getEy()).moveHere(cuadro.getBoard().getCuadro(0, cuadro.getEy()));
					cuadro.getBoard().getCuadro(3, cuadro.getEy()).getPieza().realMove(null);
				}
				else if(cuadro.getEx()==6){
					cuadro.getBoard().getCuadro(5, cuadro.getEy()).moveHere(cuadro.getBoard().getCuadro(7, cuadro.getEy()));
					cuadro.getBoard().getCuadro(5, cuadro.getEy()).getPieza().realMove(null);
				}
			}
		}
		
	}
	
	public void drawPieza(Graphics g){
		if(this.side) g.drawImage(rey, 0, 0,75,75, null);//draw blanca
		else g.drawImage(reyB, 0, 0,75,75, null); //draw negra
	}
}
