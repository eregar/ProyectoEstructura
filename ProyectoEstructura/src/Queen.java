
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Queen extends Pieza{
	private static Image rey= new ImageIcon("reinaBlanca.png").getImage();
	private static Image reyB= new ImageIcon("reinaNegra.png").getImage();
	
	public Queen(boolean side){
		this.side=side;
		this.value=10;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	/*public boolean valida(Cuadro cuadro, int nextX,int nextY) {
		int cX=cuadro.getEx();
		int cY=cuadro.getEy();
		int dx=Math.abs(cX-nextX);
		int dy=Math.abs(cY-nextY);
		
		if(dx==dy){
			if(cX>nextX){
				if(cY>nextY){
					//arriba izquierda
					cY--;
					cX--;
					if(cX>=0 && cY>=0){
						while(cX!=nextX) {
							if(cuadro.getBoard().checkPiece(cX, cY))return false;
							cY--;
							cX--;
						}
						return true;
					}return false;
				}else{
					//abajo izquierda
					cY++;
					cX--;
					while(cX!=nextX) {
						if(cuadro.getBoard().checkPiece(cX, cY))return false;
						cY++;
						cX--;
					}
					return true;
				}
			}else{
				if(cY>nextY){
					//arriba derecha
					cY--;
					cX++;
					if(cX<8 && cY>=0){
						while(cX!=nextX) {
						if(cuadro.getBoard().checkPiece(cX, cY))return false;
						cY--;
						cX++;
						}
						return true;
					}return false;
					
				}else{
					//abajo derecha
					cY++;
					cX++;
					
						while(cX!=nextX) {
							if(cX<8 && cY<8){
								if(cuadro.getBoard().checkPiece(cX, cY))return false;
								cY++;
								cX++;
								}
								return true;
						
					}
					return false;
				}
			}
		}
		else if(dx==0){
			if(dy>0){
				cY--;
				while(cY>nextY){
					if(cuadro.getBoard().checkPiece(cX, cY)) return false;
					//checa pieza
					cY--;
				}
			}else{
				cY++;
				while(cY<nextY){
					if(cuadro.getBoard().checkPiece(cX, cY)) return false;
					//checa pieza
					cY++;
				}
			}
			return true;
		}
		else if(dy==0){
			if(dx>0){
				cX--;
				while(cX>nextX){
					if(cuadro.getBoard().checkPiece(cX, cY)) return false;
					//checa pieza
					cX--;
				}
			}else{
				cX++;
				while(cX<nextX){
					if(cuadro.getBoard().checkPiece(cX, cY)) return false;
					//checa pieza
					cX++;
				}
			}
			return true;
		}
		else return false;
		
	}
	*/
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
		
	
	
	public void drawPieza(Graphics g){
		if(this.side) g.drawImage(rey, 0, 0,75,75, null);//draw blanca
		else g.drawImage(reyB, 0, 0,75,75, null); //draw negra
		//melapelas
	}
}