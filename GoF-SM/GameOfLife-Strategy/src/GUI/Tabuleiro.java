package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.unb.cic.lp.gol.Interfaces.IGameController;
import br.unb.cic.lp.gol.Interfaces.IGameEngine;

public class Tabuleiro extends JFrame {

	private int i, j;
	private JButton[][] butoes;
	private JButton back = new JButton("Back");
	private JButton quit = new JButton("Quit");
	private JButton next = new JButton("Next generation");

	private JButton revivedcells;
	private JButton killedcells = new JButton("Next generation");
	private JPanel panel, paneld, panels;
	private ButtonGroup grupo2;
	public IGameEngine engine;
	public IGameController controller;
	public static int COLUNAS;
	public static int LINHAS;

	public Tabuleiro(IGameEngine engine, IGameController controller) {
		super("Game of Life");
		this.engine = engine;
		this.controller = controller;
		ButtonHandlers handlerButtons = new ButtonHandlers();
		
		

		butoes = new JButton[LINHAS][COLUNAS];
		JPanel panel = new JPanel();
		JPanel paneld = new JPanel();
		JPanel panels = new JPanel();
		JLabel label = new JLabel();
		JLabel label2 = new JLabel();
	
		//panels.add(revivedcells);B
		//panels.add(killedcells);
		label.setText("Revived Cells: \n" // + String.valueOf(statistics.revivedCells
				);
		
		label2.setText("Killed Cells: " //+ String.valueOf(statistics.killedcCells
		);
		panels.add(label);
		panels.add(label2);
		add(panels,BorderLayout.NORTH);
		//add(panels,BorderLayout.NORTH);

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
		next.addActionListener(handlerButtons);
		paneld.setLayout(new GridLayout(1, 2));
		paneld.add(back);
		paneld.add(quit);
		paneld.add(next);
		
		add(paneld,BorderLayout.SOUTH);

		pack();
		setExtendedState(MAXIMIZED_BOTH); 
		setVisible(true);

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
			if (event.getSource() == next) {
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

				// dispose();
				// Tabuleiro tab = new Tabuleiro(engine, controller);

			}
		}
	}

}
