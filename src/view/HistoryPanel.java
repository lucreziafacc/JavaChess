package view;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.ChessModel;

import controller.Teams;

public class HistoryPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JTextArea textArea = new JTextArea(12, 46);
	private JScrollPane scrollPane = new JScrollPane(textArea);
	private final String letter = "ABCDEFGH";
	private boolean check = true;
	private String story = "";

	public HistoryPanel(){
	
		scrollPane.setOpaque(false);
		scrollPane.setBorder(null);
		
	
		add(scrollPane);
		setBackground(new Color(184,190,189));
		textArea.append(" At " + getTime() + " the game starts\n");
		textArea.append("\n --- now is WHITE turn ---\n");
		textArea.setFont(new Font("Courier 10 Pitch", Font.PLAIN, 20));
		textArea.setBackground(new Color(0,0,0,50));
		textArea.setForeground(new Color(255,255,255));
		textArea.setBorder(null);
		
		
		//textArea.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		//textArea.setAlignmentY(JTextArea.CENTER_ALIGNMENT);

		
		story = textArea.getText();

	}
	
	private String getTime(){
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	return sdf.format(cal.getTime()); 
	}
	
	private String create_String(int ax, int ay, int bx, int by, int what_happened, ChessModel model){

		String a =" > ";
	
		if(what_happened != 4){
			a+= "The " + model.getTable()[ax][ay].getColor().toString().toLowerCase() + " " +
					model.getTable()[ax][ay].getName().toString().toLowerCase();
			switch(what_happened){
			
			case 1:
				a+= " (" + letter.charAt(ax) + "," + (ay+1) + ") " + "jump the " + model.getTable()[bx][by].getColor().toString().toLowerCase() + " " +
						model.getTable()[bx][by].getName().toString().toLowerCase() +
						" (" + letter.charAt(bx) + "," + (by+1) + ") ";
				check = false;
				return a + "\n";
				
			case 2:
				a+= " gives the CHECKMATE to" + 
						(model.getTable()[bx][by].getColor()==Teams.BLACK ? " white" : " black")+
						" king and WIN!!\n";
				a+= "At " + getTime() + " the game finish";
				break;
				
			case 3:
				a+= " (" + letter.charAt(by) + "," + (bx+1) + ") gives a check to" +
						(model.getTable()[bx][by].getColor()==Teams.BLACK ? " white" : " black")+
						" king\n";
				break;
			
			default:
					a+= " (" + letter.charAt(bx) + "," + (by+1) + ") " + "moves in (" + letter.charAt(ax) + "," + (ay+1) + ")";
			}
		}else
			a+= "The game is in the STALEMATE. At " + getTime() + " the game finish";
		
		if(check)
			return a + "\n";
		else
			return "";
		
	}
	
	public void add(int ax, int ay, int bx, int by, int what_happened, ChessModel model){
		deleteTurnLine();
		textArea.append(create_String(ax,ay,bx,by,what_happened, model));
		story = textArea.getText();
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}

	public void restart(){
		textArea.setText(" At " + getTime() + " the game starts\n");
		textArea.append("\n --- now is WHITE turn ---\n");
		story = textArea.getText();
	}

	public void deleteLastLine(boolean white) {
		
		deleteTurnLine();
		
		story = story.substring(0, story.length()-1);
		
		story = story.substring(0, story.lastIndexOf('\n'));
		
		textArea.setText(story+"\n");
		setTurn(white);
		
	}
	
	private void deleteTurnLine() {
		
		story = story.substring(0, story.length()-1);
		
		story = story.substring(0, story.lastIndexOf('\n'));
		textArea.setText(story);
		
	}
	
	public void setTurn(boolean white){
		textArea.append("\n --- now is "+ (white ? "WHITE" : "BLACK") + " turn ---\n");
		story = textArea.getText();
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}

	public void redefine() {
		
		check = true;
		
	}
}
