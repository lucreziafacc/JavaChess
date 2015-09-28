package model;

import javax.swing.SwingUtilities;

import view.AdvancedmentFrame;
import view.Chronometer;
import view.View;
import view.popup.CheckGeneratedPopUp;
import view.popup.IllegalMovePopUp;
import view.popup.WrongRoundPopUp;
import controller.*;
import controller.pieces.Chessman;
import controller.pieces.King;

public class ChessModel implements Model {
	
	private static boolean is_white_turn = true;
	private static Graveyard graveyard = new Graveyard();
	private static boolean is_jump = false;
	private static boolean there_is_a_check = false;
	private static boolean make_a_check = false;
	private Stats[] stats = new Stats[2];
	private ChessControl controller;
	private static Chronometer chrono;
	//The ChessTable
	private static Chessman[][] OriginalTable = new Chessman[8][8];
	//Save the ChessTable for the Undo 
	private static Chessman[][] prevTable = new Chessman[8][8];
	//is true when Undo is available
	private static boolean undoAvailable = false;
	private static boolean stop = false;
	private View view;	
	
	public Chessman[][] getTable(){
		return OriginalTable;
	}
	
	public void setTable(Chessman[][] Table){
		OriginalTable = Table;
	}
	
	public ChessModel (){
		stats[0] = new Stats(Teams.WHITE);
		stats[1] = new Stats(Teams.BLACK);
	}
	
	@Override
	public Piece at(int x, int y) {
		return OriginalTable[x][y].getName();
		
	}

	@Override
	public void setView(View other) {
		this.view = other;
	}
	
	public View getView(){
		return view;
	}
	
	private void antiCheck(int x, int y, int x1, int y1){
		
		Chessman temp = OriginalTable[x][y];
		OriginalTable[x][y] = OriginalTable[x1][y1];
		OriginalTable[x1][y1] = temp;
		
		Teams color = !is_white_turn ? Teams.BLACK : Teams.WHITE;
		
		int xk = controller.getXKing(color);
		int yk = controller.getYKing(color);
		
		if(OriginalTable[xk][yk] instanceof King)
				if(((King) OriginalTable[xk][yk]).king_check_control(xk, yk, color, this))
						there_is_a_check = true;
		
		temp = OriginalTable[x][y];
		OriginalTable[x][y] = OriginalTable[x1][y1];
		OriginalTable[x1][y1] = temp;
	}
	
	public void effective_move(int x, int y, int x1, int y1){
		
		if(x1 != x || y1 != y){
			
			antiCheck(x,y,x1,y1);
			
			if(OriginalTable[x][y].mkMove(x, y, x1, y1, view.getModel()) && !make_a_check){
					setUndo();
					OriginalTable[x][y].movement(x, y, x1, y1, view.getModel());
					stats[teamsToInt(OriginalTable[x1][y1].getColor())].setMoves();
			}else if(make_a_check){
				new CheckGeneratedPopUp();
				stats[teamsToInt(OriginalTable[x1][y1].getColor())].setCheck();
			}else if(is_jump){
				stats[(teamsToInt(OriginalTable[x1][y1].getColor())+1)%2].setDeath();
			}else if(!(there_is_a_check))
				new IllegalMovePopUp();
		}
		
		view.onConfigurationChange();
		
		getController().redefineHistory();
		setIsJump(false);
		there_is_a_check = false;
		setMakeACheck(false);
	
		checkAdvancement(!is_white_turn ? Teams.BLACK : Teams.WHITE);
		
	}

	public boolean isWrongTurn(int x, int y){
		if((is_white_turn && OriginalTable[x][y].getColor()==Teams.BLACK)||
				(!is_white_turn && OriginalTable[x][y].getColor()==Teams.WHITE)){
			new WrongRoundPopUp();
			return true;
		}
		return false;
	}
	
	public boolean isWhiteTurn(){
		return is_white_turn;
	}
	
	public void setTurn(boolean other) {
		is_white_turn = other;		
	}
	
	public Graveyard getGraveyard(){
		return graveyard;
	}
	
	public void setGraveyard(Graveyard other){
		graveyard = other;
	}
	
	public boolean isThereACheck(){
		return there_is_a_check;
	}
	
	public boolean isJump(){
		return is_jump;
	}
	
	public boolean makeACheck(){
		return make_a_check;
	}
	
	public void thereIsACheck(boolean other){
		there_is_a_check = other;
	}
	
	public void setIsJump(boolean other){
		is_jump = other;
	}
	
	public void setMakeACheck(boolean other){
		make_a_check = other;
	}
	
	private int teamsToInt(Teams color){
		return color == Teams.WHITE ? 0 : 1;
	}
	
	public Stats[] getStats(){
		return stats;
	}
	
	public void checkAdvancement(Teams color){
		
		int i = color == Teams.WHITE ? 0 : 7;
		int x = 0;
		int y = 0;
		boolean check = false;
		
		for(int j=0; j<8; j++){
			if((OriginalTable[i][j]).getName()==Piece.PAWN){
				check = true;
				x = i;
				y = j;
				break;
			}
		}
		
		if(check) {
			new AdvancedmentFrame(color,x,y,this);
			stats[teamsToInt(OriginalTable[x][y].getColor())].setPawn();
		}
			
	}
	
	public void setChrono() {
		chrono.setVisibleChrono(true);
		
	}

	public Chronometer getChrono() {
		return chrono;
	}

	public void chronStart() {
		chrono = new Chronometer();
		
		SwingUtilities.invokeLater (new Runnable () {
			public void run () {
				chrono.start();
			}
		});
	}
	
	public void setController(ChessControl other){
		controller = other;
	}
	
	public ChessControl getController(){
		return controller;
	}
	
	public void setUndo(){
		undoAvailable = true;
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++)
				prevTable[i][j] = OriginalTable[i][j];
	}
	
	public void undo(){
		if(undoAvailable && prevTable != null){
			OriginalTable = prevTable;
			is_white_turn = !is_white_turn;	
			undoAvailable = false;
			controller.getHistory().deleteLastLine(is_white_turn);
		}
	}
	
	public void stopGame() {
		stop = true;		
	}
	
	public boolean gameIsStopped(){
		return stop;
	}
}
