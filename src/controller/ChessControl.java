package controller;

import controller.pieces.*;
import model.*;
import view.HistoryPanel;

import java.lang.Math;

	/**
	 * 
	 * */
public class ChessControl implements Controller {
	
	private static int x_king_WHITE = 7;
	private static int y_king_WHITE = 4;
	private static int x_king_BLACK = 0;
	private static int y_king_BLACK = 4;
	private static int x,y;
	private static ChessModel model;
	private static HistoryPanel history = new HistoryPanel();
	private Chessman[][] Table = null;
	
	private static boolean checking = false;
	private static boolean possible = false;
	private static boolean mutual = false;
	private static boolean mutual_king = false;
	
	/**
	 * <i>Constructor.</i> <br>
	 */
	public ChessControl(){
		model = new ChessModel();
	}
	
	/**
	 * Returns the chessmodel. It takes the table from the model and it sets the pieces into the squares. 
	 * 
	 * @param table : contains how the table is made (matrix 8x8)
	 */
	public ChessModel start() {
		
		Table = model.getTable();
		
		Table[0][0]=new Rook(Teams.BLACK);
		Table[0][1]=new Knight(Teams.BLACK);
		Table[0][2]=new Bishop(Teams.BLACK);
		Table[0][3]=new Queen(Teams.BLACK);
		Table[0][4]=new King(Teams.BLACK);
		Table[0][5]=new Bishop(Teams.BLACK);
		Table[0][6]=new Knight(Teams.BLACK);
		Table[0][7]=new Rook(Teams.BLACK);
		
		for(int i=0; i<8; i++)
			Table[1][i]=new Pawn(Teams.BLACK);
		
		Table[7][0]=new Rook(Teams.WHITE);
		Table[7][1]=new Knight(Teams.WHITE);
		Table[7][2]=new Bishop(Teams.WHITE);
		Table[7][3]=new Queen(Teams.WHITE);
		Table[7][4]=new King(Teams.WHITE);		
		Table[7][5]=new Bishop(Teams.WHITE);
		Table[7][6]=new Knight(Teams.WHITE);
		Table[7][7]=new Rook(Teams.WHITE);
		
		for(int i=0; i<8; i++)
			Table[6][i] = new Pawn(Teams.WHITE);

				
		for(int i=2; i<6; i++)
			for(int j=0; j<8; j++)
				Table[i][j]= new EmptyMan();
		
		model.setTable(Table);
		model.chronStart();
		model.setTurn(true);
		model.setController(this);
		model.getGraveyard().clear();
		
		return model;
	}
	
	public ChessModel getModel(){
		return model;
	}
	
	/**
	 * @param color : team's color who's movin'
	 * @Return the opposite king's horizontal position. <br>
	 * If white is moving, then it returns the black one position.
	 */	
	public int getXOppositeKing(Teams color){
		return getXKing(color == Teams.WHITE ? Teams.BLACK : Teams.WHITE);	
	}
	
	/**
	 * @Return the opposite king's vertical position. <br>
	 * If white is moving, then it returns the black one position.
	 */
	public int getYOppositeKing(Teams color){
		return getYKing(color == Teams.WHITE ? Teams.BLACK : Teams.WHITE);
	}
	
	/**
	 * Returns the current king's horizontal position. <br>
	 * If black is moving, then it returns the black one position.
	 */
	public int getXKing(Teams color){
		if(color == Teams.BLACK)
			return x_king_BLACK;
		else
			return x_king_WHITE;
	}
	
	/**
	 * Returns the current king's vertical position. <br>
	 * If black is moving, then it returns the black one position.
	 */
	public int getYKing(Teams color){
		if(color == Teams.BLACK)
			return y_king_BLACK;
		else
			return y_king_WHITE;
	}

	/**
	 * Sets the king's position by getting parameters. 
	 * 
	 * @param color : team's color
	 * @param x : horizontal position
	 * @param y : vertical position
	 * 
	 * @return void
	 */
	public void setKing(Teams color, int x, int y){
		if(color == Teams.BLACK){
			x_king_BLACK = x;
			y_king_BLACK = y;
		}else{
			x_king_WHITE = x;
			y_king_WHITE = y;
		}
	}
		
	/**
	 * @param turn : it is true, if the white turn is on. 
	 *  
	 * 
	 * @return boolean : if there's a checkmate <b>TRUE</b> else <b>FALSE</b>
	 * 
	 * 
	 */
	public boolean checkMate(boolean turn){
		
		Teams invor = turn ? Teams.BLACK : Teams.WHITE;
		Teams color = turn ? Teams.WHITE : Teams.BLACK;
		int xk = getXOppositeKing(color);
		int yk = getYOppositeKing(color);
		int xmin= xk>0 ? xk-1 : xk;
		int xMAX= xk<7 ? xk+1 : xk;
		int ymin= yk>0 ? yk-1 : yk;
		int yMAX= yk<7 ? yk+1 : yk;
		int result = 0;
		boolean c = false;
			
		Chessman temp = Table[xk][yk];
		Table[xk][yk] = new EmptyMan();
		
		for(int i = xmin; i <= xMAX; i++)
			for(int j = ymin; j <= yMAX; j++){
				if(Table[i][j].getColor()==Teams.EMPTY){
					if(chessScan(i, j, color, invor,false)){
						c = true;
						result++;
					}
				}else if(Table[i][j].getColor()==invor){
					result++;
				}
			}
		Table[xk][yk] = temp;
		
		if ((result == Math.abs((xMAX-xmin+1)*(yMAX-ymin+1))) && c)
			return true;
		else
			return false;
	}
	
	private boolean chessScan (int xk, int yk, Teams color, Teams invor, boolean mmg){
		model.getController().setVariable(0,true);
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++)
				if(Table[i][j].getColor()==color)
					if(Table[i][j].mkMove(i, j, xk, yk, model)){
						model.getController().setVariable(0,false);
						if((!jumpEnemy(i,j,invor) && !(fightEnemy(i,j,color))) || mmg)
							return true;
					}
		model.getController().setVariable(0,false);
		return false;
	}
	
	private boolean jumpEnemy (int x, int y, Teams color){
		
		Chessman temp = Table[x][y];
		Table[x][y] = new EmptyMan();
		
		model.getController().setVariable(0,true);
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++)
				if(Table[i][j].getColor() == color)
					if(Table[i][j].mkMove(i, j, x, y, model)){
						model.getController().setVariable(0,false);
						Table[x][y] = temp;
						return true;
					}
		
		model.getController().setVariable(0,false);
		Table[x][y] = temp;
		
		return false;
	}
	
	private boolean fightEnemy (int x, int y, Teams color){
		
		int xk = getXKing(color);
		int yk = getYKing(color);
		int mx = 0; //movimento x
		int my = 0; //movimento y		
		
		/*Switch che seleziona la mossa da usare*/
		
		switch(Table[x][y].getName().toString()){
		
			case "ROOK":
			case "QUEEN":
				if(x!=xk)
					mx = x > xk ? -1 : 1;
				if(y!=yk) 
					my = y > yk ? -1 : 1;
				break;
				
			case "BISHOP":
				mx = x > xk ? (-1) : 1;
				my = y > yk ? (-1) : 1; 
				break;
			
			case "PAWN":
			case "KING":
			case "KNIGHT":
				return false; // ritornano falso, perchè nulla può mettersi tra la pedina e il re.	
				
		}
		
		for(int i=x+mx; i!=xk; i+=mx)
			for(int j=y+my; j!=yk; j+=my)
				for(int a=0; a<8; a++)
					for(int b=0; b<8; b++)
						if(Table[a][b].getColor()==color)
							if(Table[a][b].mkMove(a, b, i, j, model))
								return true;
		
		return false;
	}
	
	
	@Override
	public void there_is_a_advancement(){
		
		Table = model.getTable();
				
		int i= model.isWhiteTurn() ? 0 : 7;
		
		for(int j=0; j<8; j++)
			if(Table[i][j].getName()==Piece.PAWN){
				//new AdvancementPopUp();
				x=i;
				y=j;
			}
		
		model.setTable(Table);
	}
	
	@Override
	public void advancement(Piece name){
		
		Table = model.getTable();
		
		Chessman other = null;
		Teams color = Table[x][y].getColor();
		
		if(name == Piece.QUEEN)
			other = new Queen(color);
		else if(name == Piece.ROOK)
			other = new Rook(color);
		else if(name ==  Piece.BISHOP)
			other = new Bishop(color);
		else if(name ==  Piece.KNIGHT)
			other = new Knight(color);
		
		Table[x][y]=other;
		
		model.setTable(Table);
		
	}
	
	
	@Override
	public HistoryPanel getHistory(){
		return history;
	}
	
	@Override
	public void setHistory(HistoryPanel other){
		history = other;
	}
	
	@Override
	public void redefineHistory() {		
		history.redefine();		
	}
	
	//@Override
	public boolean getVariable(int variable){
		if(variable==0)
			return checking;
		else if (variable==1)
			return possible;
		else if (variable==2)
			return mutual;
		else
			return mutual_king;
	}

	@Override
	public void setVariable(int variable, boolean value){
		if(variable==0)
			checking = value;
		else if (variable==1)
			possible = value;
		else if (variable==2)
			mutual = value;
		else
			mutual_king = value;
	}

}
