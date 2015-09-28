package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.*;

public class ChessTable extends JFrame implements View{

	private static final long serialVersionUID = 1L;
	
	public static JButtonCustomized[][] button = new JButtonCustomized[8][8];
	JPanel ChessGrid;
	private ChessModel model;
	private Color black = new Color(34,54,68,100);
	private Color white = new Color(184,190,189,100);
	private ChessFrame frame;

	public ChessTable(ChessModel model, ChessFrame frame){
		
		this.model = model;
		this.ChessGrid = Chesspanel();
		this.frame = frame;
		model.setView(this);
		
	}
	
	private JPanel Chesspanel(){
		
		Dimension d = new Dimension(80,80);
		
		JPanel p1 = new JPanel();
		p1.setBackground(new Color(0,0,0,0));
		
		p1.setLayout(new GridLayout(9,9));
		
		String letters = "ABCDEFGH";
		String numbers = "12345678";
		
		p1.add(new JLabel(""));
		for(int i = 0; i < 8; i++)	{
			JLabel letter = new JLabel("" + numbers.substring(i, i+1));
			letter.setFont(new Font("Ubuntu Mono", Font.BOLD, 30));
			letter.setHorizontalAlignment(JLabel.CENTER);
			letter.setForeground(Color.WHITE);
			p1.add(letter);
		}
		
		for(int i=0; i<8; i++)
			for(int j=0; j<9; j++){
				
				if(j == 0)	{
					JLabel letter = new JLabel("" + letters.substring(i, i+1));
					letter.setFont(new Font("Ubuntu Mono", Font.BOLD, 30));
					letter.setVerticalAlignment(JLabel.CENTER);
					letter.setHorizontalAlignment(JLabel.CENTER);
					letter.setForeground(Color.WHITE);
					p1.add(letter);
				}
				else{
				j--;
				
					
					button[i][j] = new JButtonCustomized(model.getTable()[i][j].printImage(), i, j);
		
					button[i][j].setBackground((i+j)%2!=0 ? black : white);
					button[i][j].setMinimumSize(d);
					button[i][j].setMaximumSize(d);
					button[i][j].setPreferredSize(d);
					button[i][j].setRolloverEnabled(false);
					button[i][j].addActionListener(new TileListener(button[i][j], model));
					button[i][j].setBorderPainted(false);
			        button[i][j].setFocusPainted(false); 
					
					
					p1.add(button[i][j]);
					j++;
				}
				
			}
		
		return p1;
		
	}
	
	public JPanel getPanel(){
		return ChessGrid;
	}
	

	@Override
	public ChessModel getModel() {
		return model;
	}
	
	@Override
	public void onConfigurationChange() {
		
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++){
				button[i][j].setIcon(model.getTable()[i][j].printImage());
				button[i][j].setRolloverEnabled(false);
				button[i][j].setBackground((i+j)%2!=0 ? black : white);
			}
		
		frame.refreshHistoryPanel();

		main.Main.global.frame.revalidate();
		main.Main.global.frame.repaint();
	}
		
	public void setPanel(TurnPanel panel){
	}

	
}
