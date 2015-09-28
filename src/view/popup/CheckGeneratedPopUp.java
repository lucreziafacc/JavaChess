package view.popup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CheckGeneratedPopUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private Color color = new Color(34, 54, 68);

	public CheckGeneratedPopUp ()	{
			
	JPanel center = new JPanel();
	center.setBackground(color);
	
	setTitle("GAME OVER!");
			
	JLabel pic = new JLabel(new ImageIcon("img/chesses.png"));
	pic.setBackground(new Color(204, 84, 68));
	center.add(pic);
			
	/* South panel */
			
	JPanel south = new JPanel();
					
	south.setLayout(new GridLayout(3, 1));
	south.setBackground(color);
			
	JPanel southFirst = new JPanel();
	southFirst.setBackground(color);
	/*southFirst.setLayout(new GridLayout(2, 1));*/
			
	JPanel southSecond = new JPanel();
	southSecond.setBackground(color);	
			
	JPanel southThird = new JPanel();
	southThird.setBackground(color);
		
	JLabel message = new JLabel("Warning!");
	message.setFont(new Font("Georgia", Font.BOLD, 30));
	message.setForeground(Color.RED);
	message.setHorizontalAlignment(JLabel.CENTER);
	southFirst.add(message);
	south.add(southFirst);
			
	JLabel messageBelow = new JLabel("This move generates a check!");
	messageBelow.setFont(new Font("Georgia", Font.ITALIC, 18));
	messageBelow.setForeground(Color.WHITE);
	messageBelow.setHorizontalAlignment(JLabel.CENTER);
	southSecond.add(messageBelow);
	south.add(southSecond);
			
	final JButton OK = new JButton("OK");
	OK.setBackground(Color.WHITE);
	OK.setBorderPainted(false);
	OK.setFocusable(false);
	OK.setPreferredSize(new Dimension(200, 50));
	OK.addActionListener(new ActionListener(){

	public void actionPerformed(ActionEvent arg0) {
		dispose();
		}
	});
			
	southThird.add(OK);
	south.add(southThird);
		
	add(center, BorderLayout.CENTER);
	add(south, BorderLayout.SOUTH);
			
	pack();
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setAlwaysOnTop(true);
	setLocationRelativeTo(null);
	
	}
}
		


