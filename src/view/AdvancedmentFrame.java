package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import controller.Teams;
import controller.pieces.Bishop;
import controller.pieces.Chessman;
import controller.pieces.Knight;
import controller.pieces.Queen;
import controller.pieces.Rook;
import model.ChessModel;



public class AdvancedmentFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private int x;
	private int y;
	private static String the_name = null;
	private ChessModel model;
	private Chessman[][] Table = null;


	public AdvancedmentFrame(Teams color, int x, int y, ChessModel other){
		this.x = x;
		this.y = y;
		this.model = other;
		
		setTitle("Advancement of " + color.toString().toLowerCase() + " pawn");
		
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		JPanel south = new JPanel();
		Color col = color == Teams.WHITE ? Color.WHITE : Color.BLACK;
		Color inv = color == Teams.BLACK ? Color.WHITE : Color.BLACK;
		
		JLabel one = new JLabel("the "+color.toString().toLowerCase()+" pedestrian has reached the");
		one.setFont(new Font("Georgia", Font.ITALIC, 20));
		JLabel two = new JLabel("end of the board. What do you make it?");
		two.setFont(new Font("Georgia", Font.ITALIC, 20));

		north.setLayout(new GridLayout(2,1));
		north.setBackground(inv);
		one.setForeground(col);
		two.setForeground(col);
		
		one.setHorizontalAlignment(JLabel.CENTER);
		one.setVerticalAlignment(JLabel.CENTER);
		two.setHorizontalAlignment(JLabel.CENTER);
		two.setVerticalAlignment(JLabel.CENTER);

		north.add(one);
		north.add(two);
		
		center.setBackground(inv);
		
		JPanel queen = panel("Queen", color);
		JPanel bishop = panel("Bishop", color);
		JPanel rook = panel("Rook", color);
		JPanel knight = panel("Knight", color);
		
		south.setLayout(new GridLayout(1,4));
		
		south.add(queen);
		south.add(bishop);
		south.add(rook);
		south.add(knight);
		
		south.setBackground(inv);
		
		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		pack();
		
		setVisible(true);
		setLocationRelativeTo(null);
	
	}
	
	private JPanel panel (final String name, final Teams color){
		Table = model.getTable();
		JPanel panel = new JPanel();
		Color col = color == Teams.WHITE ? Color.WHITE : Color.BLACK;
		Color inv = color == Teams.BLACK ? Color.WHITE : Color.BLACK;
		
		JLabel label = new JLabel(name);
		ImageIcon image = new ImageIcon("img/piece/"+color+"_"+name.toUpperCase()+".png");
		
		label.setForeground(col);
		label.setBackground(inv);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		JButton button = new JButton(image);
		button.setBorderPainted(false);
		button.setFocusable(false);
		button.setBackground(inv);
		
		button.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				the_name = name.toUpperCase();
				Table[x][y] = getChessman(color);
				model.getView().onConfigurationChange();
				dispose();
			}
			
		});
		
		panel.setLayout(new GridLayout(2,1));
		
		panel.add(button);
		panel.add(label);
		
		panel.setBackground(inv);
		
		model.setTable(Table);
		
		return panel;
	}
	
	private Chessman getChessman(Teams color){
		switch(the_name){
		case "ROOK":
			return new Rook(color);
		case "QUEEN":
			return new Queen(color);
		case "BISHOP":
			return new Bishop(color);
		default:
			return new Knight(color);

		}
		
	}
	
}
