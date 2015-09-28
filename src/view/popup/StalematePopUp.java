package view.popup;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class StalematePopUp extends JFrame {

	private static final long serialVersionUID = 1L;

	public StalematePopUp()	{
		JPanel center = new JPanel();
		center.setBackground(new Color(99,35,91));
		setTitle("TIE");
		
		
		JLabel pic = new JLabel(new ImageIcon("img/chesses4.png"));
		pic.setBackground(new Color(99,35,91));
		center.add(pic);
		
		/* South panel */
		
		JPanel south = new JPanel();
				
		south.setLayout(new GridLayout(3, 1));
		south.setBackground(new Color(99,35,91));
		
		JPanel southFirst = new JPanel();
		southFirst.setBackground(new Color(99,35,91));
		southFirst.setLayout(new GridLayout(3, 1));
		
		JPanel centralMenu = new JPanel();
		centralMenu.setBackground(new Color(99,35,91));
	
		JLabel message = new JLabel("Warning: THERE'S A STALEMATE.");
		message.setFont(new Font("Ubuntu Mono", Font.BOLD, 30));
		message.setForeground(Color.WHITE);
		message.setHorizontalAlignment(JLabel.CENTER);
		south.add(message);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(99,35,91));
		
		
		//String color = "Sorry " + ((controller.getModel()).is_white_turn() ? "BLACK! " : "WHITE! ");		
		JLabel tie = new JLabel("There's a tie.");
		tie.setFont(new Font("Ubuntu Mono", Font.PLAIN, 30));
		tie.setForeground(Color.WHITE);
		tie.setHorizontalAlignment(JLabel.CENTER);
		south.add(tie);
		south.add(panel);
		
	
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);

		
		this.setLocation(584, 184);
	}
}
