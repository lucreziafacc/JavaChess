package controller.pieces;

import model.ChessModel;

import controller.Piece;
import controller.Teams;

public class Queen extends Chessman {
	
	public Queen(Teams color){
		
		super(color, Piece.QUEEN);
	}

	public boolean mkMove(int ax, int ay, int bx, int by, ChessModel model){
		
		int vx = 0, vy = 0;
		int x, y;
		//set vx on -1 if the first coordinate comes before the second one
		//set vx on 1 if the first one comes after the second one
		//let vx set to 0 if they are on the same line			
		
		if(ax!=bx) vx = ax > bx ? -1 : 1;
		if(ay!=by) vy = ay > by ? -1 : 1;
		
		if((ax != bx && ay != by)&&(java.lang.Math.abs(ax-bx) != java.lang.Math.abs(ay-by)))
			return false;
			
			//check the path chosen
			for(x=ax+vx, y=ay+vy; ((vy==0 && x!=bx+vx)||(vx==0 && y!=by+vy)||(x!=bx+vx && y!=by+vy)); x+=vx, y+=vy){
				
				//check if there are any chessman
				if(model.getTable()[x][y].getName() != Piece.EMPTYMAN){
					
					//if the chessman has the same color return false
					if(!(model.getTable()[x][y].checkColor(model.getTable()[ax][ay]))) {
						
						/*
						
						if(model.getTable()[x][y].getName() == Piece.KING && model.getTable()[x][y].checkColor(model.getTable()[model.getController().getXOppositeKing(color)]
								[model.getController().getYOppositeKing(color)]))
							model.getController().setVariable(1, true);
						
						
						 */
						
						if(x==bx && y==by)
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
