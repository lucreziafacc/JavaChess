package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.Teams;

public class Chronometer extends JFrame
{

	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private JLabel time;

	private Timer timer;
	private long startTime = System.currentTimeMillis();
	private long white_time = 0;
	private long black_time = 0;
	
	public Chronometer ()
	{
		//this.startTime=startTime;
		frame = new JFrame ("Chronometer");
		frame.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
		frame.setSize (220, 140);
	
		time = new JLabel ("0:00:00.0");
		time.setFont (new Font ("Ubuntu Mono", Font.BOLD, 30));
		time.setHorizontalAlignment (JLabel.CENTER);
		time.setForeground(Color.BLACK);
	
		frame.add (time, BorderLayout.CENTER);
		
		restart();

		frame.setVisible (false);
		setAlwaysOnTop(true);
		this.setLocation(584, 184);
	
		}
	
		public void start(){
			timer.start();
				
		}
		
		public void counterTime(boolean turn){
			
			long passedTime = System.currentTimeMillis () - startTime;
			
			long time = passedTime - black_time - white_time;
			
			if(turn)
				white_time += time;
			else
				black_time += time;
		}
		

		public String lengthGametoString(){
			return toString(white_time + black_time);
			
		}
		
		private String toString(long time){
			
			int seconds = (int) (time / 1000 % 60);
			int minutes = (int) (time / 60000 % 60);
			int hours = (int) (time / 3600000);
		
			return String.format ("%d:%02d:%02d", hours, minutes, seconds);
		}
		
		public void setVisibleChrono(boolean b) {
			frame.setVisible(true);
			
		}

		public String getTime(Teams color) {
			return toString(color == Teams.WHITE ? white_time : black_time);
		}
		
		public void restart(){
			timer = new Timer (50, new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					
					long diffTime = System.currentTimeMillis () - startTime;
				
					int decSeconds = (int) (diffTime % 1000 / 100);
					int seconds = (int) (diffTime / 1000 % 60);
					int minutes = (int) (diffTime / 60000 % 60);
					int hours = (int) (diffTime / 3600000);
				
					String s = String.format ("%d:%02d:%02d.%d", hours, minutes, seconds, decSeconds);
				
					time.setText (s);
				}
			});
		}

}
