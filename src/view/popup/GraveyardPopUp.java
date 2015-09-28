package view.popup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.ChessModel;

import controller.Teams;

public class GraveyardPopUp extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public GraveyardPopUp(Teams color, ChessModel model){
		
		setTitle(color.toString().toUpperCase()+" GRAVEYARD");
		
		JPanel panel = (model.getGraveyard()).toVideo(color);	
		JPanel ok = new JPanel();
		JButton OK = new JButton("OK");
		OK.setBorderPainted(false);
		OK.setFocusable(false);
		
		ok.setBackground(color == Teams.WHITE ? Color.WHITE : Color.BLACK);
		OK.setBackground(color == Teams.WHITE ? Color.BLACK : Color.WHITE);
		OK.setForeground(color == Teams.WHITE ? Color.WHITE : Color.BLACK);
		OK.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
			
		});
		ok.add(OK);
			
		add(panel, BorderLayout.NORTH);
		add(ok, BorderLayout.SOUTH);
		setAlwaysOnTop(true);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}

}