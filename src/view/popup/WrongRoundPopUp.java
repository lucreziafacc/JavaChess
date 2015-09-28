package view.popup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class WrongRoundPopUp extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public WrongRoundPopUp(/*final ChessControl controller */)	{
		
		this.setTitle("NOT YOUR TURN, DUDE!");
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		panel.setBackground(new Color(34,54,68));
		
		
		JPanel upper = new JPanel();
		upper.setBackground(new Color(34,54,68));
		JLabel label = new JLabel("It's not your turn!");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Ubuntu Mono", Font.BOLD, 30));
		label.setBackground(new Color(34,54,68));
		upper.add(label);
		panel.add(upper);
		
		JPanel lower = new JPanel();
		lower.setBackground(new Color(34,54,68));
		JLabel message = new JLabel("Check it out on the right!");
		message.setBackground(new Color(34,54,68));
		message.setFont(new Font("Ubuntu Mono", Font.ITALIC, 15));
		message.setHorizontalAlignment(JLabel.CENTER);
		message.setForeground(Color.WHITE);
		lower.add(message);
		panel.add(lower);
		
		JPanel button = new JPanel();
		button.setBackground(new Color(34,54,68));
		JButton OK = new JButton("Ok, sorry.");
		OK.setBackground(Color.WHITE);
		OK.setForeground(Color.BLACK);
		OK.setBorderPainted(false);
		OK.setFocusPainted(false);
		
		
		OK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
			
		});
		
		button.add(OK);
		
		
		add(panel, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
		
		setVisible(true);
		this.setAlwaysOnTop(true);
		this.setLocation(500, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		
	}

}
