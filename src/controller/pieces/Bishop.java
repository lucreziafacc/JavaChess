package controller.pieces;

import model.ChessModel;
import controller.Piece;
import controller.Teams;

public class Bishop extends Chessman {
	/**
	 * @param color
	 */
	public Bishop(Teams color){
		
		super(color, Piece.BISHOP);
		
	}

	
	/**
	 * check if the coordinates are on the same diagonal:
	 * 		<UL><LI><b>if it's false</b> the move is impossible;
	 * 		<LI><b>if it's true</b> check if there are any chessmen on the diagonal:
	 * 			<UL><LI> if there is a chessman with same color <b>the move is impossible</b>;
	 * 			<LI>if there is a chessman with different color <b>jump the enemy chessman</b>; 
	 * 			<LI>if there is a chessman which belongs to the team empty <b>the move is possible</b>.
	 * 			</UL>
	 * 		</UL>
	 */
	public boolean mkMove(int ax, int ay, int bx, int by, ChessModel model){
		
		if(java.lang.Math.abs(ax-bx) != java.lang.Math.abs(ay-by)) //check if the coordinates are on the same diagonal
			return false;
		else{							 //if they are on the same diagonal:
			int vx = ax > bx ? (-1) : 1; //set vx on -1 if the first coordinate comes before the second one
			int vy = ay > by ? (-1) : 1; //on 1 if the first one comes after the second one.
			int x, y;
			
			//check the path chosen
			for(x=ax+vx, y=ay+vy; x!=bx+vx && y!=by+vy; x+=vx, y+=vy){  
				//check if there are any chessmen
						if((model.getTable()[x][y].getName() != Piece.EMPTYMAN)){
							//if the chessman has the same color return false
							if(!(model.getTable()[x][y].checkColor(model.getTable()[ax][ay]))){ 
								
								if((x==bx && y==by)) //(model.getTable()[bx][by].getName() != Piece.KING)
									jump(x, y, ax, ay, model);
								else
									model.getController().setVariable(1, false);
							}
							return false;
							}
					
				}
				return true;
			}
		}
		
	}


