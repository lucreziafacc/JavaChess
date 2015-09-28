package view.popup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Teams;

import view.LabelCustomized;

import model.ChessModel;
import model.Stats;

public class StatsPopUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private ChessModel model;
	
	public StatsPopUp(ChessModel model){
		this.model = model;
		
		setTitle("Stats");
		
		JPanel time = new JPanel();
		JLabel l1 = new JLabel("Duration of the Game: ");
		JLabel l2 = new JLabel(model.getChrono().lengthGametoString());
		
		l1.setFont(new Font("Ubuntu Mono", Font.BOLD, 30));
		l2.setFont(new Font("Tlwg Mono", Font.BOLD, 30));
		l1.setForeground(new Color(50,190,50));
		l2.setForeground(new Color(50,190,50));

		time.add(l1);
		time.add(l2);
		
		JPanel center = new JPanel();
		JPanel center2 = new JPanel();
		center2.setLayout(new GridLayout(1,2));
		JPanel white = statsPanel((model.getStats())[teamsToInt(Teams.WHITE)]);
		JPanel black = statsPanel((model.getStats())[teamsToInt(Teams.BLACK)]);
		
		center2.add(white);
		center2.add(black);
		
		center.add(center2);
		center2.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		center2.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		
		JPanel south = endPanel();

		add(time, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		setVisible(true);
		pack();
		
	}
	
	private JPanel statsPanel(Stats stats){
		
		Color color = stats.getColor() == Teams.WHITE ? Color.WHITE : Color.BLACK;
		Color invert = stats.getColor() == Teams.BLACK ? Color.WHITE : Color.BLACK;
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6,2));
		
		panel.add(new LabelCustomized("Time:", invert, color, false));
		panel.add(new LabelCustomized(model.getChrono().getTime(stats.getColor()), invert, color, true));
		
		panel.add(new LabelCustomized("Moves:", invert, color, false));
		panel.add(new LabelCustomized(stats.getMoves() + "", invert, color, true));
		
		panel.add(new LabelCustomized("Pieces Lost:", invert, color, false));
		panel.add(new LabelCustomized(stats.getDeath() + "", invert, color, true));
		
		panel.add(new LabelCustomized("Advanced Pawns:", invert, color, false));
		panel.add(new LabelCustomized(stats.getPawn() + "", invert, color, true));
		
		panel.add(new LabelCustomized("Checks:", invert, color, false));
		panel.add(new LabelCustomized(stats.getCheck() + "", invert, color, true));
		
		panel.add(new LabelCustomized("CheckMate:", invert, color, false));
		panel.add(new LabelCustomized(stats.getCheckmate() ? "Yes" : "No", invert, color, true));
		
		panel.setBackground(color);
		
		return panel;
	}
	
	private int teamsToInt(Teams color){
		return color == Teams.WHITE ? 0 : 1;
	}
	
	private JPanel endPanel(){
		JPanel panel = new JPanel();
		
		JButton OK = new JButton("OK");
		
		OK.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
			
		});
				
		panel.add(OK);
				
		return panel;
	}
	
}
