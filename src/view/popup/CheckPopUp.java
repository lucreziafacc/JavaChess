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

import model.ChessModel;


public class CheckPopUp extends JFrame {
		
	private static final long serialVersionUID = 1L;
	private Color colorful = new Color(34,54,68);

	public CheckPopUp(ChessModel model)	{
		
		/* Setting center panel */
		
		String color = "Hei " + (model.isWhiteTurn() ? "BLACK! " : "WHITE! ");
		
		JPanel center = new JPanel();
		center.setBackground(colorful);
		setTitle("OH NO!");
		
		JLabel pic = new JLabel(new ImageIcon("img/chesses.png"));
		pic.setBackground(colorful);
		center.add(pic);
		
		/* South panel */
		
		JPanel south = new JPanel();
				
		south.setLayout(new GridLayout(3, 1));
		south.setBackground(colorful);
		
		JPanel southFirst = new JPanel();
		southFirst.setBackground(colorful);
		southFirst.setLayout(new GridLayout(2, 1));
		
		JPanel southButton = new JPanel();
		southButton.setBackground(colorful);
	
		JLabel message = new JLabel(color+"Warning: THERE'S A CHECK.");
		message.setFont(new Font("Georgia", Font.BOLD, 20));
		message.setForeground(Color.WHITE);
		message.setHorizontalAlignment(JLabel.CENTER);
		southFirst.add(message);
		south.add(southFirst);
		
		JLabel doom = new JLabel("Suggest: move away your king!");
		doom.setFont(new Font("Tahoma", Font.ITALIC, 15));
		doom.setHorizontalAlignment(JLabel.CENTER);
		doom.setForeground(Color.WHITE);
		southFirst.add(doom);
		south.add(southFirst);
		
		final JButton OK = new JButton("OK");
		OK.setBorderPainted(false);
		OK.setFocusable(false);
		OK.setBackground(Color.WHITE);
		OK.setPreferredSize(new Dimension(200, 50));
		OK.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
			
		});
		
		southButton.add(OK);
		south.add(southButton);
		
	
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
