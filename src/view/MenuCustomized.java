package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import view.popup.Exit;
import view.popup.GraveyardPopUp;
import view.popup.StatsPopUp;


import model.ChessModel;

import controller.ChessControl;
import controller.Teams;


//import view.popup.StatsPopUp;

public class MenuCustomized extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public MenuCustomized(final ChessControl controller, final ChessModel model, final ChessFrame chessframe)	{
		
		JMenu graveyards = new JMenu("  Graveyards      ");
		graveyards.setFont(new Font("Ubuntu Mono", Font.ITALIC,17));
		graveyards.setHorizontalAlignment(JMenuItem.CENTER);
		add(graveyards);
		
		final JMenuItem white = new JMenuItem("White Graveyard");
		white.setFont(new Font("Ubuntu Mono", Font.ITALIC,17));
		white.setHorizontalAlignment(JMenuItem.CENTER);
		graveyards.add(white);
		
		final JMenuItem black = new JMenuItem("Black Graveyard");
		black.setFont(new Font("Ubuntu Mono", Font.ITALIC,17));
		black.setHorizontalAlignment(JMenuItem.CENTER);
		graveyards.add(black);
		
		white.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new GraveyardPopUp(Teams.WHITE, model);	
			}
			
		});
		
		black.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new GraveyardPopUp(Teams.BLACK, model);	
			}
			
		});
		
		JMenuItem timer = new JMenuItem("Timer");
		timer.setFont(new Font("Ubuntu Mono", Font.ITALIC, 17));
		timer.setHorizontalAlignment(JMenuItem.CENTER);
		add(timer);
		
		timer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!model.gameIsStopped())
					model.setChrono();
			}
			
		});
		
		JMenuItem restart = new JMenuItem("Restart");
		restart.setHorizontalAlignment(JMenuItem.CENTER);
		restart.setFont(new Font("Ubuntu Mono", Font.ITALIC, 17));
		add(restart);
		
		restart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				chessframe.setController(new ChessControl());
				controller.getHistory().restart();
				controller.start();
				
				chessframe.getTable().onConfigurationChange();
				
			}
			
		});
		
		JMenuItem undo = new JMenuItem("Undo");
		undo.setHorizontalAlignment(JMenuItem.CENTER);
		undo.setFont(new Font("Ubuntu Mono", Font.ITALIC, 17));
		add(undo);
		
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!model.gameIsStopped()){
					model.undo();
					chessframe.getTable().onConfigurationChange();
				}
			}
			
		});
		
		JMenuItem credits = new JMenuItem("Credits");
		credits.setHorizontalAlignment(JMenuItem.CENTER);
		credits.setFont(new Font("Ubuntu Mono", Font.ITALIC, 17));
		add(credits);
		
		
		
		JMenuItem stats = new JMenuItem("Stats");
		stats.setHorizontalAlignment(JMenuItem.CENTER);
		stats.setFont(new Font("Ubuntu Mono", Font.ITALIC, 17));
		add(stats);
		
		stats.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new StatsPopUp(model);
				
			}
			
		});
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.setFont(new Font("Ubuntu Mono", Font.ITALIC, 17));
		add(exit);
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Exit();
			}
			
		});
		
		
		
		}
	
	public MenuCustomized getMenu()	{
		return this;
	}

}
