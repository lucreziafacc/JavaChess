package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ChessModel;

public class TurnPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ChessModel model;
	private static JPanel pannello = new JPanel();
	private static JPanel blackTurn = new JPanel();
	private static JLabel blackCircle = new JLabel(new ImageIcon("img/turn/bordoscuro.png"));
	private static JLabel blackCircle2 = new JLabel(new ImageIcon("img/turn/bordochiaro.png"));
	private static JPanel turnleft = new JPanel();
	private static JLabel turnLabelL = new JLabel(new ImageIcon("img/turn/sinistra.png"));
	private static JPanel turnright = new JPanel();
	private static JLabel turnLabelR = new JLabel(new ImageIcon("img/turn/destra.png") );
	private static JPanel whiteTurn = new JPanel();
	private static JLabel whiteCircle = new JLabel(new ImageIcon("img/turn/lucescura.png"));
	private static JLabel whiteCircle2 = new JLabel(new ImageIcon("img/turn/lucechiara.png"));
	
	public TurnPanel(Color BACKGROUND_COLOR, ChessModel model){
		this.model = model;
		/* Panello dei turni */
		
		pannello.setLayout(new GridLayout(1, 4));
		pannello.setBackground(BACKGROUND_COLOR);
		
		/* Pannello di sinistra */
				
		blackTurn.setBackground(BACKGROUND_COLOR);
		blackTurn.add(blackCircle);
		blackTurn.add(blackCircle2);
		blackCircle.setVisible(true);
		blackCircle2.setVisible(false);
		pannello.add(blackTurn);
				
		/*****************************************/
		
		/* Pannello centralesinistro */
				
		turnLabelL.setBackground(BACKGROUND_COLOR);
		turnLabelL.setForeground(Color.WHITE);
		turnLabelL.setFont(new Font("Ubuntu Mono", Font.BOLD, 25));
		turnLabelL.setVisible(false);
		turnleft.setBackground(BACKGROUND_COLOR);
		turnleft.add(turnLabelL);
		pannello.add(turnleft);
				
		/******************************************/
		
		/* Pannello centralesinistro */
				
		turnLabelR.setBackground(BACKGROUND_COLOR);
		turnLabelR.setForeground(Color.WHITE);
		turnLabelR.setFont(new Font("Ubuntu Mono", Font.BOLD, 25));
		turnright.setBackground(BACKGROUND_COLOR);
		turnright.add(turnLabelR);
		pannello.add(turnright);
				
		/*********************************************/
		
		/* Pannello destro */
		
		whiteTurn.setBackground(BACKGROUND_COLOR);
		whiteTurn.add(whiteCircle);
		whiteTurn.add(whiteCircle2);
		whiteCircle.setVisible(false);
		whiteCircle2.setVisible(true);
		pannello.add(whiteTurn);
	}
	
	public JPanel getPanel(){
		return pannello;
	}
	
	public void turnChange(){
		if(model.isWhiteTurn())	{
			whiteCircle2.setVisible(true);
			whiteCircle.setVisible(false);
			blackCircle.setVisible(true);
			blackCircle2.setVisible(false);
			turnLabelL.setVisible(false);
			turnLabelR.setVisible(true);
			
		}
		else	{
			whiteCircle2.setVisible(false);
			whiteCircle.setVisible(true);
			blackCircle.setVisible(false);
			blackCircle2.setVisible(true);
			turnLabelL.setVisible(true);
			turnLabelR.setVisible(false);	
		}
	}
}
