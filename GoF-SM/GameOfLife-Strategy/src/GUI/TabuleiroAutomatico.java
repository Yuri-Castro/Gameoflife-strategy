package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.unb.cic.lp.gol.Interfaces.IGameController;
import br.unb.cic.lp.gol.Interfaces.IGameEngine;

public class TabuleiroAutomatico extends JFrame {

	private int i, j;
	private JButton[][] butoes;
	private JButton back = new JButton("Back");
	private JButton quit = new JButton("Quit");
	private JButton revivedcells;
	private JPanel panel, paneld, panels;
	private ButtonGroup grupo2;
	public IGameEngine engine;
	public IGameController controller;
	private Timer timer;
	public static int COLUNAS;
	public static int LINHAS;
	private boolean pausado = true;
	public JButton play_pause = new JButton("Play");
	

	public TabuleiroAutomatico(IGameEngine engine, IGameController controller) {
		super("Game of Life");
		this.engine = engine;
		this.controller = controller;
		ButtonHandlers handlerButtons = new ButtonHandlers();
		JButton revivedcells = new JButton("Revived Cells: " // +
																// String.valueOf(statistics.revivedCells)
		);
		JButton killedcells = new JButton("Killed Cells: " // +
															// String.valueOf(statistics.killedcCells)
		);

		butoes = new JButton[LINHAS][COLUNAS];
		JPanel panel = new JPanel();
		JPanel paneld = new JPanel(); 
		JPanel panels = new JPanel();
		JPanel panelf = new JPanel();
		JLabel label = new JLabel();
		JLabel label2 = new JLabel();
		

		// panels.add(revivedcells);B
		// panels.add(killedcells);
		label.setText("Revived Cells: \n" // +
											// String.valueOf(statistics.revivedCells
		);

		label2.setText("Killed Cells: " // +
										// String.valueOf(statistics.killedcCells
		);
		panels.setLayout(new GridLayout(3, 1));
		panels.add(label);
		panels.add(label2);
		//add(panels, BorderLayout.BEFORE_FIRST_LINE);
		play_pause.setBackground(Color.GREEN);
		panels.add(play_pause);
		
		add(panels,BorderLayout.NORTH);
		
		play_pause.addActionListener(handlerButtons);
	
		// add(panels,BorderLayout.NORTH);

		for (i = 0; i < LINHAS; i++) {
			for (j = 0; j < COLUNAS; j++) {
				butoes[i][j] = new JButton();
				if (engine.isCellAlive(i, j))
					butoes[i][j].setBackground(Color.ORANGE);
				else
					butoes[i][j].setBackground(Color.BLUE);

				butoes[i][j].setContentAreaFilled(false);
				butoes[i][j].setOpaque(true);

			}
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(new GridLayout(LINHAS, COLUNAS));
		for (i = 0; i < LINHAS; i++) {
			for (j = 0; j < COLUNAS; j++) {
				panel.add(butoes[i][j]);
			}
		}
		add(panel);

		back.addActionListener(handlerButtons);
		quit.addActionListener(handlerButtons);
		paneld.setLayout(new GridLayout(1, 2));
		paneld.add(back);
		paneld.add(quit);

		add(paneld, BorderLayout.SOUTH);

		pack();
		setExtendedState(MAXIMIZED_BOTH);
		simulacao();
		setVisible(true);

	}

	public void simulacao() {
		timer = new Timer(400, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!pausado) {
					controller.nextGeneration();

					for (i = 0; i < LINHAS; i++) {
						for (j = 0; j < COLUNAS; j++) {
							if (engine.isCellAlive(i, j))
								butoes[i][j].setBackground(Color.ORANGE);
							else
								butoes[i][j].setBackground(Color.BLUE);

							butoes[i][j].setContentAreaFilled(false);
							butoes[i][j].setOpaque(true);

						}
					}

					setVisible(true);
				}
			}
		});
		timer.start();
	}

	public class ButtonHandlers implements ActionListener {
		// trata evento de botao
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == back) {
				// JOptionPane.showMessageDialog(null, "Faz alguma coisa! 3");
				Menu radioButtonFrame = new Menu(engine, controller);

				dispose();

			}
			if (event.getSource() == quit) {

				dispose();

			}
			if (event.getSource() == play_pause) {
				if (pausado == true) {
					
					play_pause.setBackground(Color.RED);
					play_pause.setText("Pause");
					
					pausado = false;
				} else if (pausado == false) {
					play_pause.setBackground(Color.GREEN);
					play_pause.setText("PLAY");
					
					pausado = true;
				}

			}

		}

		// dispose();
		// Tabuleiro tab = new Tabuleiro(engine, controller);

	}
}
