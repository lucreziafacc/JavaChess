package controller.pieces;

import controller.Piece;
import controller.Teams;
import java.lang.Math;

import model.ChessModel;

public class King extends Chessman {
	
	/**
	 * @param color
	 */
	public King(Teams color){
		
		super(color, Piece.KING);
	}
		
	public Teams getKingColor(Teams color)	{
		return color;
	}
	
	public Teams getOppositeKingColor(Teams color)	{
		if(color == Teams.WHITE)
			return Teams.BLACK;
		
		return Teams.WHITE;
	}
	
	/**
	 * This method checks that the move proposal is only one tile and if the move does not procure a chessmate.</p>
	 * If these conditions are met, it checks if:
	 *		<UL><LI>there is a chessman with same color <b>the move is impossible</b>;
	 * 		<LI>ithere is a chessman with different color <b>jump the enemy chessman</b>; 
	 * 		<LI>there is a chessman belongs to the team empty <b>the move is possible</b>.
	 * 		</UL>
	 */
	public boolean mkMove(int ax, int ay, int bx, int by, ChessModel model){

		if((((Math.abs(ax-bx) == 0)||(Math.abs(ax-bx) == 1))&&		 //check if the starting point and end point
			((Math.abs(ay-by) == 0)||(Math.abs(ay-by) == 1)))){		 //have distance is about one or zero tiles.
			
			if(!king_check_control(bx,by,model.getTable()[ax][ay].getColor(), model))	 //check if there is a Check*/
				if((model.getTable()[bx][by].getName() != Piece.EMPTYMAN)){
						
					if((!(model.getTable()[bx][by].checkColor(model.getTable()[ax][ay])) && (model.getTable()[bx][by].getName() != Piece.KING))) 
						jump(bx, by, ax, ay, model);
					return false;
				}
				return true;

			
		}	
		return false;		
		
	}
	
	/**
	 * 
	 * @param i : horizontal point
	 * @param j	: vertical point
	 * @param color 
	 * @param model
	 * @return <UL><LI><i> true </i></LI>  if the move makes effectively a check, by setting makeACheck() function. </UL>
	 */
	
	public boolean king_check_control(int i, int j, Teams color, ChessModel model) {
		
		/*
		 * temp : è un nuovo scacco temporaneo.
		 * Table : è la tavola così come è sistemata in quel momento
		 * */
		
		Chessman temp = null;
		Chessman[][] Table = model.getTable();
		
		/*
		 * Se lo scacco selezionato non è un re, allora temp prende quel pezzo. 
		 * Invece, table[i][j] prendere un nuovo re.
		 * */
		
		if(Table[i][j].getName()!=Piece.KING){
			temp = Table[i][j];
			Table[i][j] = new King(color);
		}
		
		model.getController().setVariable(3,true);
		
		/*
		 * Viene fatto un ciclo fino a che non vengono esauriti gli scacchi E non non vi è scacco. 
		 * Se anche solo una delle due condizioni non è verificata si esce dal ciclo. 
		 * */
		
			for(int x=0; x<8 && !model.makeACheck(); x++)
				for(int y=0; y<8 && !model.makeACheck(); y++)
					/*
					 * A questo punto, se il pezzo con indice x e y è diverso dal re E diverso dallo scacco vuoto
					 * Allora fai la mossa corretta, relativa a quello scacco 
					 * */
					if((Table[x][y].getName() != Piece.KING) && (Table[x][y].getName() != Piece.EMPTYMAN))
						Table[x][y].mkMove(x, y, i, j, model);
												
		model.getController().setVariable(3,false);
		
		/* Se temp non è nulla, cioè se viene inizializzata allora lo scacco[i][j] prende quello che c'è dentro temp */
		
		if(temp != null)			
			Table[i][j] = temp;
			
		model.setTable(Table);
		
		/* Viene settata  la funzione makeACheck() (booleana) che setta a true la variabile make_a_check */
		
		return model.makeACheck();
	}
}
