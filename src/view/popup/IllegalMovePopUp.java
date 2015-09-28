package view.popup;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IllegalMovePopUp extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private final int DEFAULT_WIDTH = 250;
	private final int DEFAULT_HEIGHT = 100;
	private Color color = new Color(34,54,68);
	private ImageIcon image = new ImageIcon("img/wrong.png");
	
	public IllegalMovePopUp() {
		
		setTitle("THIS IS ILLEGAL");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setBackground(Color.WHITE);
		
		
		JPanel center = new JPanel();
		center.setBackground(color);
		center.setLayout(new GridBagLayout());
		
		
		JLabel cross = new JLabel(image);
		cross.setBackground(color);
		center.add(cross);
		cross.setBorder(new EmptyBorder(10, 10, 0, 10));
		cross.setHorizontalAlignment(JLabel.CENTER);
		cross.setVerticalAlignment(JLabel.CENTER);
		
		JPanel doubleColumn = new JPanel();
		doubleColumn.setBackground(color);
		doubleColumn.setLayout(new GridLayout(2,1));
		center.add(doubleColumn);
		
		
		JLabel invalid = new JLabel("invalid move ");
		invalid.setBackground(color);
		invalid.setForeground(Color.WHITE);
		invalid.setFont(new Font("Georgia", Font.BOLD, 30));
		doubleColumn.add(invalid);
		invalid.setHorizontalAlignment(JLabel.CENTER);
		invalid.setVerticalAlignment(JLabel.CENTER);
		
		JLabel tryAgain = new JLabel("Try Again!");
		tryAgain.setBackground(color);
		tryAgain.setForeground(Color.WHITE);
		tryAgain.setFont(new Font("Courier New", Font.PLAIN, 20));
		doubleColumn.add(tryAgain);
		tryAgain.setHorizontalAlignment(JLabel.CENTER);
		tryAgain.setVerticalAlignment(JLabel.CENTER);
		
		JPanel south = new JPanel();
		south.setBackground(color);
		
		JPanel forTheButton = new JPanel();
		forTheButton.setBackground(color);
		
		JButton OK = new JButton("OK");
		OK.setBorderPainted(false);
        OK.setFocusPainted(false); 
        OK.setBackground(Color.BLACK);
        OK.setForeground(Color.WHITE);
        
        OK.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
			
		});
        
        forTheButton.add(OK);
        south.add(forTheButton);
        
		
		
		
		/*JPanel totalPanel = new JPanel();
		totalPanel.setBackground(color);
		totalPanel.setLayout(new GridLayout(1,2));
		
		JPanel left = new JPanel();
		left.setBackground(color);
		JLabel cross = new JLabel(new ImageIcon("img/wrong.png"));
		cross.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		cross.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		cross.setBackground(color);
		left.add(cross);
		
		totalPanel.add(left);
		
		JPanel right = new JPanel();
		right.setBackground(color);
		right.setLayout(new GridLayout(3,1));
				
		
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(2,1));
		JPanel south = new JPanel();
		
		JPanel p2 = new JPanel();
		//JPanel p2a = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		
		final JButton OK = new JButton("OK");
		OK.setBorderPainted(false);
        OK.setFocusPainted(false); 
        OK.setBackground(Color.BLACK);
        OK.setForeground(Color.WHITE);
		
		
		JLabel Invalid = new JLabel("Illegal.");
		Invalid.setForeground(Color.WHITE);
		Invalid.setFont(new Font("Georgia", Font.BOLD, 25));
		JLabel tryAgain = new JLabel("Try Again!");
		tryAgain.setForeground(Color.WHITE);
		tryAgain.setFont(new Font("Courier New", Font.PLAIN, 15));
		
		north.setBackground(color);
		center.setBackground(color);
		south.setBackground(color);
		
		south.setLayout(new GridLayout(2,1));
		//Color backgroundColor = new Color(255,255,255);
		
		//SsetBackground(backgroundColor);
		
	
		p2.setBackground(color);
		p2.add(Invalid);
		north.add(p2);
		
		p3.setBackground(color);
		p3.add(tryAgain);
		center.add(p3);
		
		OK.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
			
		});
		
		p4.add(OK);
		p4.setBackground(color);
		south.add(p4);
		
		right.add(p2);
		right.add(p3);
		right.add(p4);
		
		totalPanel.add(right);
		
	//	add(right, BorderLayout.WEST);
	//	add(north, BorderLayout.NORTH);
	//	add(center, BorderLayout.CENTER);
	//	add(south, BorderLayout.SOUTH);
		
		add(totalPanel, BorderLayout.CENTER);*/
		
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		pack();
		setSize(450, 200);
		setVisible(true);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		
	}

}
