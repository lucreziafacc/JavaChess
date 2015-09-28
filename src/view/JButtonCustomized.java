package view;

import javax.swing.Icon;
import javax.swing.JButton;

public class JButtonCustomized extends JButton {
	
	private static final long serialVersionUID = 1L;

	private int x;
	private int y;
	
	public JButtonCustomized(Icon i, int x, int y) {
		super(i);
		this.x = x;
		this.y = y;
		
	}
	
	public int getButtonX()	{
		return x;
	}
	
	public int getButtonY()	{
		return y;
	}
	
}
