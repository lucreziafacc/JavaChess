package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import view.popup.Exit;
import model.*;
import controller.*;

public class ChessFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private ChessControl controller = new ChessControl();
	private ChessModel model;
	private ChessTable chessTable;
	
	Chronometer chrono = new Chronometer();

	private HistoryPanel historyPanel;

	private JPanel panelHistory = new HistoryPanel();
	
	public ChessFrame(){
		
		setTitle("Chess Game");
		
		
		/* BACKGROUND */
		
		ImageIcon background = new ImageIcon("img/legno2.png");
		/* LABEL -- 2 STRATO */
		
		JLabel label = new JLabel(background);
		label.setLayout(new GridLayout(1,1));
		
		/* FINSTRA -- 1 STRATO */
		
		this.setContentPane(new JLabel((background)));
		this.setLayout(new BorderLayout());
		
		//Content pane = new Content();
		
		model = controller.start();
		
		/* PANNELLO PRINCIPALE con 2 COLONNE E UNA RIGA */
		
		/***************************************/
		JPanel contentMainPanel = new JPanel();
		contentMainPanel.setLayout(new GridBagLayout());
		contentMainPanel.setBackground(new Color(0,0,0,0));
		/******************************************/
		
		JPanel mainpanel = new JPanel();
		mainpanel.setBackground(new Color(0,0,0,0));
		mainpanel.setLayout(new GridLayout(1,2));
		/*****************************************/
		contentMainPanel.add(mainpanel);
		/*******************************************/

		JPanel south = new JPanel();
		south.setBackground(new Color(0,0,0,0));
				
		chessTable = new ChessTable(model, this);
		//chessTable.pack();
		mainpanel.add((chessTable).getPanel());
		mainpanel.setAlignmentY(JPanel.TOP_ALIGNMENT);
		
		//parte destra
		
		JPanel secondaryPanel = new JPanel();
		//modificati i pannelli da 3 a 2
		secondaryPanel.setLayout(new GridLayout(2, 1));
		secondaryPanel.setBackground(new Color(0,0,0,0));
		
		
		JPanel title = new JPanel();
		title.add(new JLabel(new ImageIcon("img/title.png")));
		title.setBackground(new Color(0,0,0,0));
		secondaryPanel.add(title);
		
		/* Panello Cronologia */
		
		refreshHistoryPanel();
		panelHistory.setBackground(new Color(0,0,0,0));
		secondaryPanel.add(panelHistory);
		
		mainpanel.add(secondaryPanel);
		
		/* Pannello turni */
				
		/*turnPanel temp = new turnPanel(BACKGROUND_COLOR, model);
		chessTable.setPanel(temp);	
		JPanel turnPanel = temp.getPanel();
		right.add(turnPanel);*/
		
		/* FINE PANNELLO CENTRALE */
		
		/* INIZIO PANNELLO SUD*/
		
		JPanel powered = new JPanel();
		powered.setBackground(new Color(0,0,0,0));
		JLabel names = new JLabel("Powered by © Fabio Scapini & Lucrezia Facciotti");
		names.setBackground(new Color(0,0,0,0));
		names.setFont(new Font("Ubuntu Mono", Font.ITALIC, 12));
		names.setForeground(Color.WHITE);
		powered.add(names);
		south.add(powered);
		
		
		// Get the size of the screen
		
	
		
		
		
		/* FINE PANNELLO SUD */
		
		//this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				

		this.setJMenuBar(new MenuCustomized(controller, model, this).getMenu());
		
		
		/*SwingUtilities.invokeLater (new Runnable () {
			public void run () {
				chrono.start();
			}
		});*/
		
		/************************************************/
		add(contentMainPanel, BorderLayout.CENTER);
		//add(mainpanel, BorderLayout.CENTER);
		/************************************************/
		add(south, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);

		
		//this.setPreferredSize(this.getParent().getPreferredSize());
		
        
		
		addWindowListener(new WindowAdapter () {
			public void windowClosing (WindowEvent e)	{
				new Exit();
				
			}
				
		});
		
		this.pack();
	}

	public void setChrono() {
		chrono.setVisibleChrono(true);
		
	}

	public Chronometer getChrono() {
		return chrono;
	}
	
	public void refreshHistoryPanel()	{
		historyPanel = controller.getHistory();
		panelHistory = historyPanel;
	}

	public void setController(ChessControl chessControl) {
		this.controller = chessControl;
		
	}

	public ChessTable getTable() {
		return chessTable;
	}
}
