/*************************** DA PASSARE A SKAP *****************************************/

package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.Teams;
import model.ChessModel;

public class TileListener implements ActionListener {

	private JButtonCustomized button;
	private ChessModel model;
	private static int clickCount = 0;
	private static int x, x1, y, y1;
	
	public TileListener(JButtonCustomized button, ChessModel model){
		this.button = button;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {				
		
		if(!model.gameIsStopped()){
			clickCount++;
	
			
			if(clickCount == 1){
				x = button.getButtonX();
				y = button.getButtonY();
			}
			
			if(model.getTable()[x][y].getColor() == Teams.EMPTY){
				clickCount = 0;
			}else if(model.isWrongTurn(x,y))
				clickCount = 0;
			else{
				button.setBackground(model.getTable()[x][y].getColor() == Teams.WHITE ?
						(new Color(0,0,0,150)) : (new Color(255,255,255,200)));
				possible_moves();
			}
			
			if(clickCount == 2){
				x1 = button.getButtonX();
				y1 = button.getButtonY();
	
				model.effective_move(x,y,x1,y1);
	
				clickCount = 0;
			}
		}
		
		main.Main.global.frame.repaint();

	}

	public int setX(){
		return button.getButtonX();
	}
	
	public int setY(){
		return button.getButtonY();
	}

	
	private void possible_moves(){
		model.getController().setVariable(0,true);

		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++){
				if((model.getTable()[x][y].mkMove(x, y, i, j, model)||model.getController().getVariable(1))&&(!model.makeACheck()))
					if((i+j) % 2 != 0)
						ChessTable.button[i][j].setBackground(new Color(0,102,51,150));
					else
						ChessTable.button[i][j].setBackground(new Color(0,153,76,150));

				model.getController().setVariable(1,false);
				
			}
		
		model.setIsJump(false);
		model.thereIsACheck(false);
		model.setMakeACheck(false);
		
		model.getController().setVariable(0,false);
	}
}
