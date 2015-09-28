package controller.pieces;

import javax.swing.*;

import model.ChessModel;

import view.*;
import view.popup.CheckMatePopUp;
import view.popup.CheckPopUp;
import view.popup.JumpedPopUp;
import view.popup.StalematePopUp;
import controller.Graveyard;
import controller.Teams;
import controller.Piece;

//SUPERCLASS
public abstract class Chessman {

	protected Teams color;
	private Piece name;

	
	/**
	 * 
	 * @param color indicates the team
	 * @param name indicates the type
	 */
	public Chessman(Teams color, Piece name){
		this.color = color;
		this.name = name;
	}
	/**
	 * 
	 * @return an ImageIcon that matches the token corresponding type and team.
	 */
	public ImageIcon printImage(){
		
		return new ImageIcon("img/piece/"+this.getColor()+"_"+this.getName()+".png");
	}
	
	/**
	 * 
	 * @return the team
	 */
	public Teams getColor(){
		return color;
	}
	/**
	 * 
	 * @return the type
	 */
	public Piece getName(){
		return name;
	}
	/**
	 * This method checks if a move is legal. Inside  is called the method <b>jump()</b>.
	 * @param ax indicates the <b>x-coordinate</b> of the start point
	 * @param ay indicates the <b>y-coordinate</b> of the start point
	 * @param bx indicates the <b>x-coordinate</b> of the end point
	 * @param by indicates the <b>y-coordinate</b> of the end point
	 * @return <UL><LI><b>true</b> if the move is legal<LI><b>false</b> if the move is illegal</UL>
	 */
	public abstract boolean mkMove(int ax, int ay, int bx, int by, ChessModel model);

	
	/**
	 * 
	 * @param x indicates the <b>x-coordinate</b> of the chess man to jump.
	 * @param y indicates the <b>y-coordinate</b> of the chess man to jump.
	 */
	public void jump(int x, int y, int x1, int y1, ChessModel model){
		
		Chessman[][] Table = model.getTable();
	
		HistoryPanel history = model.getController().getHistory();
		Graveyard graveyard = model.getGraveyard();
		
		if(model.getController().getVariable(2) && Table[x][y].getName() == Piece.KING){
			model.thereIsACheck(true);
		}else if(model.getController().getVariable(3))
			model.setMakeACheck(true);
		else if (model.getController().getVariable(2)){ 					//do nothing
		}else if(model.getController().getVariable(0))
			model.getController().setVariable(1, true);
		else{
			graveyard.add(Table[x][y]);
			model.setGraveyard(graveyard);
			history.add(x1, y1, x, y, 1, model);
			new JumpedPopUp(x,y,model);
			Table[x][y] = new EmptyMan();
			Table[x][y].movement(x1, y1, x, y, model);
			model.getView().onConfigurationChange();
			model.setIsJump(true);	
		}
		model.getController().setHistory(history);
		model.setTable(Table);
	}
	
	/**
	 * This method check the color
	 * @param other
	 * @return <UL><LI><b>true</b> if the colors are equal<LI><b>false</b> if the colors are different</UL>
	 */
	public boolean checkColor(Chessman other){
		if(this.getColor() == other.getColor())
			return true;
		return false;
	}
	
	

	/**
	 * This method performs the actual move, swapping the two chessmen.
	 * @param ax indicates the <b>x-coordinate</b> of the start point
	 * @param ay indicates the <b>y-coordinate</b> of the start point
	 * @param bx indicates the <b>x-coordinate</b> of the end point
	 * @param by indicates the <b>y-coordinate</b> of the end point
	 */
	public void movement(int ax, int ay, int bx, int by, ChessModel model){
		
		Chessman[][] Table = model.getTable();
		HistoryPanel history = model.getController().getHistory();
		
		Chessman temp = Table[ax][ay];
		Table[ax][ay] = Table[bx][by];
		Table[bx][by] = temp;
				
		if(Table[bx][by].getName() == Piece.KING)
			model.getController().setKing(Table[bx][by].getColor(), bx, by);
		
		check_control((model.isWhiteTurn()), model);
		
		if(model.getController().checkMate(model.isWhiteTurn())){
			new CheckMatePopUp(model.getController());
			history.add(bx, by, bx, by, 2, model);
			model.stopGame();
		}else if(model.isThereACheck()){
			new CheckPopUp(model);
			history.add(bx, by, bx, by, 3, model);
		}else
			history.add(bx, by, ax, ay, 0, model);

		if(is_stalemate(model)){
			history.add(ax, ay, bx, by, 4, model);
			model.stopGame();
			new StalematePopUp();
		}
		

		model.getChrono().counterTime(model.isWhiteTurn());
		model.setTurn(!(model.isWhiteTurn()));
		model.getController().getHistory().setTurn(model.isWhiteTurn());
		
		model.getController().setHistory(history);
		model.setTable(Table);
			
	}
	
	private boolean check_control(boolean wt, ChessModel model) {
		Chessman[][] Table = model.getTable();
		Teams color = wt ? Teams.WHITE : Teams.BLACK;
		Teams invor = !wt ? Teams.WHITE : Teams.BLACK;
		int xk = model.getController().getXKing(invor);
		int yk = model.getController().getYKing(invor);
				
		model.getController().setVariable(2,true);
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++)
				if(Table[i][j].getColor()==color)
					if(Table[i][j].mkMove(i, j, xk, yk, model))
						return true;	
		model.getController().setVariable(2,false);

		return false;
		
	}
	
	/**
	 * 
	 * @param x indicates the hypothetical <b>x-coordinate</b> of analyzed king
	 * @param y indicates the hypothetical <b>y-coordinate</b> of analyzed king
	 * @param colorN indicates the <b>color</b> of the analyzed king
	 * @return <UL><LI><b>true</b> if there is a chessmate;<LI><b>false</b> if there is not a chessmate.</UL>
	 */

	public boolean is_stalemate(ChessModel model){
		
		int empty = 0;
		int bishop = 0;
		int knight = 0;
		
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++){
				if(model.getTable()[i][j].getName()==Piece.EMPTYMAN)
					++empty;
				else if (model.getTable()[i][j].getName()==Piece.BISHOP)
					++bishop;
				else if (model.getTable()[i][j].getName()==Piece.KNIGHT)
					++knight;
			}
		if((empty == 62) || (empty == 61 && (bishop == 1 ^ knight == 1))) // ^ ==> xor
			return true;
		else
			return false;
	}
	
}
