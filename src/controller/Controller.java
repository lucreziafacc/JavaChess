package controller;

import view.HistoryPanel;
import model.*;

public interface Controller {

	ChessModel start();
	
	ChessModel getModel();
	
	/**
	 * @param color 
	 * @return the opposite king's horizontal position
	 */
	int getXOppositeKing(Teams color);
	
	/**
	 * @param color
	 * @return the opposite king's vertical position
	 */
	int getYOppositeKing(Teams color);
	
	/**
	 * @param color
	 * @return the king's horizontal position
	 */
	int getXKing(Teams color);
	
	/**
	 * @param color
	 * @return the king's vertical position
	 */
	int getYKing(Teams color);
	
	/**
	 * @param color
	 * @param x
	 * @param y
	 * 
	 * @return void: Set the king position
	 */
	void setKing(Teams color, int x, int y);
	
	/**
	 * @param turn --> true = white turn
	 * @return true if there is a check
	 */
	boolean checkMate(boolean turn);
	
	void there_is_a_advancement();
	
	void advancement(Piece name);
	
	HistoryPanel getHistory();
	
	void setHistory(HistoryPanel other);
	
	/**
	 * Call the method <b>redefine</b> from the <i>HistoryPanel</i> class.
	 */
	void redefineHistory();
	
	/**
	 * @param variable
	 * can be:
	 * <UL><LI> <b>checking</b> it's <b>true</b> if there is a check;
	 * <LI> <b>possible</b> it's <b>true</b> if the move can be done;
	 * <LI> <b>mutual</b> is used to avoid loops;
	 * <LI> <b>mutual_king</b> is used to avoid loops while the checkmate's control.
	 * </UL> 
	 */
	//boolean getVariable(int variable);
		
	/**	
	 * @param variable
	 * @param value
	 * set the variable's value matching to <b>variable</b>, to <b>value</b>. 
	 */
	void setVariable(int variable, boolean value);
	
}
