package controller;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.pieces.Chessman;
import java.awt.Font;

public class Graveyard {
	
	private HashSet<Chessman> Wgraves= new HashSet<>();
	private HashSet<Chessman> Bgraves= new HashSet<>();
	private int Wdeath= 0;
	private int Bdeath= 0;
		
	public Graveyard (){
	}
	
	public void add(Chessman piece){
		if(piece.getColor()==Teams.WHITE){
			if(contains(piece)==0)
				Wdeath++;
			Wgraves.add(piece);
		}else{
			if(contains(piece)==0)
				Bdeath++;
			Bgraves.add(piece);
		}
	}
	
	private int contains(Chessman piece) {
		return contains(piece.getName(), piece.getColor());
	}
	
	private int contains(Piece name, Teams color) {
		int count=0;
		
		HashSet<Chessman> graves = color == Teams.WHITE ? Wgraves : Bgraves;
	
		for(Chessman b:graves)
			if(b.getName() == name)	
				count++;
		
		return count;
	}

	public void clear(){
		Wgraves.clear();
		Bgraves.clear();
		Wdeath= 0;
		Bdeath= 0;
	}
	
	public JPanel toVideo(Teams color){
		JPanel panel = new JPanel();
		
		Color col = color == Teams.WHITE ? Color.WHITE : Color.BLACK;
		Color inv = color == Teams.BLACK ? Color.WHITE : Color.BLACK;
				
		int length = color == Teams.WHITE ? Wdeath : Bdeath;
		ImageIcon image = null;
		
		int count;
		
		panel.setBackground(col);
		
		if(length == 0){
			JLabel label = new JLabel("The "+ color.toString().toLowerCase() +" graveyard is still Empty");
			label.setFont(new Font("Georgia", Font.ITALIC, 20));
			label.setForeground(inv);
			panel.add(label);
			
		}else{
			panel.setLayout(new GridLayout(length,1));
			
				for(Piece a : Piece.values()){
					count=contains(a,color);
					image= new ImageIcon("img/piece/"+color+"_"+a+".png"); 
					
					JPanel tile = null;
					if(count!=0){
						tile = new JPanel();
						tile.setLayout(new GridLayout(1,3));
						tile.setBackground(col);
						//JPanel num = new JPanel();
						//num.setAlignmentX(JPanel.CENTER_ALIGNMENT);
						//num.setAlignmentY(JPanel.CENTER_ALIGNMENT);
						//JPanel ima = new JPanel();
						//JPanel name = new JPanel();
						JLabel label1= new JLabel(count+" x");
						label1.setFont(new Font("Georgia", Font.ITALIC, 20));
						label1.setHorizontalAlignment(JLabel.CENTER);
						label1.setVerticalAlignment(JLabel.CENTER);
						label1.setBackground(col);
						JLabel label2= new JLabel(a+"");
						label2.setFont(new Font("Georgia", Font.ITALIC + Font.CENTER_BASELINE, 20));
						label2.setVerticalAlignment(JLabel.CENTER);
						label2.setBackground(col);

							//num.setBackground(col);
							label1.setForeground(inv);
							//ima.setBackground(col);
							//name.setBackground(col);
							label2.setForeground(inv);
						
						tile.add(label1);
						tile.add(new JLabel(image));
						tile.add(label2);
						//tile.add(num);
						//tile.add(ima);
						//tile.add(name);
						panel.add(tile);
					}
				}
			
		}
		return panel;
	}

}
