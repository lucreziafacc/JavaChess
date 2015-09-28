package controller.pieces;

import model.ChessModel;

import controller.Piece;
import controller.Teams;

public class Pawn extends Chessman {

	/**
	 * 
	 * @param color
	 */
	public Pawn(Teams color){
		
		super(color, Piece.PAWN);
	}

	 /**
	 * If move is from one tile to another on diagonal, check if there is another chessman <BR>
	 * else check move is one tile forward
	 */
	public boolean mkMove(int ax, int ay, int bx, int by, ChessModel model){
		
		int i = model.getTable()[ax][ay].getColor()==Teams.WHITE ? -1 : 1;
		//check if in the diagonals tiles there is a chessman
		if(((by == ay+1 || by == ay-1) && bx == ax+i)
				&&(model.getTable()[bx][by].getName() != Piece.EMPTYMAN)
				&& !(model.getTable()[ax][ay].checkColor(model.getTable()[bx][by]))){
			jump(bx, by, ax, ay, model);
			return false;
		}
		else if ((by != ay || bx!= ax+i)||(model.getTable()[bx][by].getName()!=Piece.EMPTYMAN))
		 //check if pawn moves just for one tile in vertical
			return false;
		
		return true;
		
		
	}
}
