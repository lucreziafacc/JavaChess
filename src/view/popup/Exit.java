package view.popup;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.*;

public class Exit extends JFrame {

	
	private static final long serialVersionUID = 1L;


	public Exit()	{
		
		Color color = new Color(34,54,68);
		
		setTitle("Are you sure?");
		
		JPanel north = new JPanel();
		north.setLayout(new GridLayout(2, 1));
		north.setBackground(color);
		
		JLabel message = new JLabel("Do you really wanna exit?");
		message.setFont(new Font("Georgia", Font.PLAIN, 20));
		message.setForeground(Color.WHITE);
		message.setBackground(color);
		message.setHorizontalAlignment(JLabel.CENTER);
		message.setVerticalAlignment(JLabel.CENTER);
		north.add(message);
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(2,3));
		
		
		JPanel col1 = new JPanel();
		col1.setBackground(color);
		center.add(col1);
		
		JButton YES = new JButton("Sure.");
		YES.setBackground(Color.WHITE);
		YES.setForeground(Color.BLACK);
		YES.setBorderPainted(false);
		YES.setFocusPainted(false);
		
		
		JPanel col2 = new JPanel();
		col2.setBackground(color);
		
		
		JButton NO = new JButton("Just Kidding!");
		NO.setBackground(Color.BLACK);
		NO.setForeground(Color.WHITE);
		NO.setBorderPainted(false);
		NO.setFocusPainted(false);
		
		YES.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
			
		});
		
		center.add(YES);
		center.add(col2);
		
		NO.addActionListener(new ActionListener()	{

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		
		JPanel col3 = new JPanel();
		col3.setBackground(color);
		center.add(col3);
		
		center.add(NO);
		
		JPanel col4 = new JPanel();
		col4.setBackground(color);
		center.add(col4);
		
		JPanel south = new JPanel();
		south.setBackground(color);
		
		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		
		
		//setResizable(false);
		setAlwaysOnTop(true);
		pack();
		setSize(450, 150);
		setLocationRelativeTo(null);
		setVisible(true);
	
		
	}



}
