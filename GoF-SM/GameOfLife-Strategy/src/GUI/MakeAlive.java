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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.unb.cic.lp.gol.Interfaces.IGameController;
import br.unb.cic.lp.gol.Interfaces.IGameEngine;
import br.unb.cic.lp.gol.Interfaces.IStatistics;

public class MakeAlive extends JFrame {

	private int i, j;
	private JButton[][] butoes;
	private JButton test = new JButton("back");
	private JPanel panel, paneld;
	private ButtonGroup grupo2;
	public static int COLUNAS;
	public static int LINHAS;
	private IGameEngine engine;
	public IGameController controller;
	
	public MakeAlive(IGameEngine engine, IGameController controller) {
		super("Game of Life");
		this.engine = engine;
		this.controller = controller;
		
		ButtonHandler handlerButton = new ButtonHandler();
		ButtonHandlers handlerButtons = new ButtonHandlers();

		butoes = new JButton[LINHAS][COLUNAS];
		JPanel panel = new JPanel();
		JPanel paneld = new JPanel();
		

		for (i = 0; i < LINHAS; i++) {
			for (j = 0; j < COLUNAS; j++) {
				butoes[i][j] = new JButton();
				if(engine.isCellAlive(i, j))
					butoes[i][j].setBackground(Color.ORANGE);
				else /* Celula morta*/
					butoes[i][j].setBackground(Color.BLUE);

				butoes[i][j].setContentAreaFilled(false);
				butoes[i][j].setOpaque(true);

				butoes[i][j].addActionListener(handlerButton);

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

		test.addActionListener(handlerButtons);
		paneld.setLayout(new GridLayout(1, 2));
		paneld.add(test);

		add(paneld, BorderLayout.NORTH);

		pack();
		setExtendedState(MAXIMIZED_BOTH);  
		setVisible(true);

	}

	private class ButtonHandler implements ActionListener {

		// trata evento de botao
		public void actionPerformed(ActionEvent event) {
			for (i = 0; i < LINHAS; i++) {
				for (j = 0; j < COLUNAS; j++) {
					if (event.getSource() == butoes[i][j]) {
						if (butoes[i][j].getBackground() == Color.ORANGE) {
							butoes[i][j].setBackground(Color.BLUE);
							engine.makeCellDead(i, j);
							

						} else if (butoes[i][j].getBackground() == Color.BLUE) {
							butoes[i][j].setBackground(Color.ORANGE);
							engine.makeCellAlive(i, j);

						}
					}
					
				}
			}

		}

	}

	private class ButtonHandlers implements ActionListener {

		// trata evento de botao
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == test) {
				Menu radioButtonFrame = new Menu(engine, controller, engine.getStatistics());

				dispose();

			}

		}

	}

}
