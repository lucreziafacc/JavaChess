package controller.pieces;

import model.ChessModel;

import controller.Piece;
import controller.Teams;

public class Rook extends Chessman {
	
	public Rook(Teams color){
		
		super(color, Piece.ROOK);
	}

	public boolean mkMove(int ax, int ay, int bx, int by, ChessModel model){
		
		if(ax == bx || ay == by){
			
			int vx = 0, vy = 0;
			int x, y;
			
			//set vx on -1 if the first coordinate comes before the second one
			//set vx on 1 if the first one comes after the second one
			//let vx set to 0 if they are on the same line			
			
			if(ax!=bx)
				vx = ax > bx ? -1 : 1;
			if(ay!=by) 
				vy = ay > by ? -1 : 1;	
						
			for(x=ax+vx, y=ay+vy; ((vy==0 && x!=bx+vx)||(vx==0 && y!=by+vy)); x+=vx, y+=vy){	
					if(model.getTable()[x][y].getName() != Piece.EMPTYMAN){
						//if the chessman has the same color throws an error
						if(!(model.getTable()[x][y].checkColor(model.getTable()[ax][ay]))) {
							
							if(model.getTable()[x][y].getName() == Piece.KING)
								model.getController().setVariable(0, true);
							
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
			
		return false;
		
	}

}
