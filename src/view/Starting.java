package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Starting extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Color color = new Color(255,255,255);
	public ChessFrame frame;
	
	public Starting()	{
		
		
		
		setTitle("Let's play");
		
		/* Main panel in which we have everything */
		
		//JPanel panel = new JPanel();
		//panel.setLayout(new GridLayout(3,1));
		//panel.setBackground(color);
		
		JPanel north = new JPanel();
		north.setLayout(new GridLayout(1,1));
		north.setBackground(color);
		JLabel image = new JLabel(new ImageIcon("img/iconStart.png"));
		image.setBackground(color);
		north.add(image);
		
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(2,1));
		center.setBackground(color);
		
		JPanel textarea = new JPanel();
		textarea.setBackground(color);
		
		JLabel text = new JLabel(new ImageIcon("img/font.png"));
		textarea.add(text);
		center.add(text);
		
		JPanel credits = new JPanel();
		credits.setBackground(color);
		JLabel creditsArea = new JLabel();
		creditsArea.setFont(new Font("Thaoma", Font.PLAIN, 10));
		creditsArea.setText("<html><center>Powered by <b>Fabio Scapini</b> and <b>Lucrezia Facciotti</b> - Programming II course project. <br> " +
				"2015, <i> University of Verona - Computer Science</i><br> Credits <b>Fabio Scapini</b> and <b>Lucrezia Facciotti</b>. <br><b><i> ALL " +
				"RIGHTS RESERVED</i></b></center></html>");
		creditsArea.setBackground(Color.WHITE);
		creditsArea.setForeground(Color.BLACK);
		creditsArea.setAutoscrolls(false);
		creditsArea.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		creditsArea.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
		credits.add(creditsArea);
		center.add(credits);
		
		
		JPanel south = new JPanel();
		south.setBackground(color);
		south.setLayout(new GridLayout(2,1));
		
		JPanel buttonPlay = new JPanel();
		buttonPlay.setBackground(color);
		JButton play = new JButton("Play");
		//play.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.BLACK), BorderFactory.createMatteBorder(0, 7, 0, 0, Color.RED)));
		play.setFont(new Font("Georgia", Font.BOLD, 25));
		play.setForeground(Color.WHITE);
		play.setBackground(Color.BLACK);
		play.setFocusable(false);
		play.setBorderPainted(false);
		
		play.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				frame = new ChessFrame();
				dispose();
				}
			});
		
		buttonPlay.add(play);
		south.add(buttonPlay);
		
		
		
		JPanel buttonExit = new JPanel();
		buttonExit.setBackground(color);
		JButton exit = new JButton("Exit");
		exit.setFont(new Font("Georgia", Font.BOLD + Font.ITALIC, 25));
		exit.setForeground(Color.WHITE);
		exit.setBackground(Color.BLACK);
		exit.setFocusable(false);
		exit.setBorderPainted(false);
		
		exit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				dispose();
				}
			});
		
		buttonExit.add(exit);
		south.add(buttonExit);
		
		
		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		//setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
	}

}
