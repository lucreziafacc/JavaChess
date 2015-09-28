package controller.pieces;

import model.ChessModel;

import controller.Piece;
import controller.Teams;

public class Knight extends Chessman {
	
	/**
	 * 
	 * @param color
	 */
	public Knight(Teams color){
		
		super(color, Piece.KNIGHT);
	}
	
	/**
	 * check if the move is L-shape
	 * 		<UL><LI><b>if it's false</b> the move is impossible;
	 * 		<LI><b>if it's true</b> check if there is any chessmen in the destination:
	 * 			<UL><LI> if there is a chessman with same color <b>the move is impossible</b>;
	 * 			<LI>if there is a chessman with different color <b>jump the enemy chessman</b>; 
	 * 			<LI>if there is a chessman belongs to the team empty <b>the move is possible</b>.
	 * 			</UL>
	 * 		</UL> 
	 * 
	 */
	public boolean mkMove(int ax, int ay, int bx, int by, ChessModel model){
		
		if(check(ax,ay,bx,by)){	//check if the move is legal
			if(model.getTable()[bx][by].getColor()!=Teams.EMPTY){//if the chessman meets another chessman
				if (!(model.getTable()[ax][ay].checkColor(model.getTable()[bx][by]))){//with different color
					jump(bx, by, ax, ay, model);
					return false;
				}
				else
					return false;
			}
			return true;
		}
		else
			return false;
		
	}

	/**
	 * Support method for checking the move.
	 * @param ax start x-point
	 * @param ay start y-point
	 * @param bx destination x-point
	 * @param by destination y-point
	 * @return true if the move is L-shaped.
	 */
	private boolean check (int ax, int ay, int bx, int by){
		
		if((ax == bx - 2 || ax == bx + 2)&&(ay == by - 1 || ay == by + 1))
			return true;
		else if((ax == bx - 1 || ax == bx + 1)&&(ay == by - 2 || ay == by + 2))
			return true;
		else
			return false;
	}
	
}
