package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class LabelCustomized extends JLabel {
	
	private static final long serialVersionUID = 1L;

	public LabelCustomized(String string, Color invert, Color color, boolean center){
		super(" "+string);
		this.setForeground(invert);
		this.setBackground(color);
		this.setHorizontalAlignment(center ? LEFT : RIGHT);
		this.setFont(new Font("Sans Serif", Font.PLAIN, 20));
	}

}
