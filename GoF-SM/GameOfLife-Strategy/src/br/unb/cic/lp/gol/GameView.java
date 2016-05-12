package br.unb.cic.lp.gol;

import java.lang.Exception;
import java.lang.Thread;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import Exceptions.noStrategySetted;
import GUI.ComboBox;
import GUI.Regra;
import br.unb.cic.lp.gol.Interfaces.IGameController;
import br.unb.cic.lp.gol.Interfaces.IGameEngine;
import br.unb.cic.lp.gol.Interfaces.IGameView;
import br.unb.cic.lp.gol.estrategias.Conway;
import br.unb.cic.lp.gol.estrategias.HighLife;

/**
 * Atua como um componente de apresentacao (view), exibindo o estado atual do
 * game com uma implementacao baseada em caracteres ASCII.
 * 
 * @author rbonifacio
 */
@Singleton
public class GameView implements IGameView {
	private static final String LINE = "+-----+";
	private static final String DEAD_CELL = "|     |";
	private static final String ALIVE_CELL = "|  o  |";

	private static final int INVALID_OPTION = 0;
	private static final int MAKE_CELL_ALIVE = 1;
	private static final int NEXT_GENERATION = 2;
	private static final int CONWAY = 3;
	private static final int HIGH_LIFE = 4;
	private static final int AUTOMATIC = 5;
	private static final int HALT = 6;
	private static final Regra regra = new Regra();

	private IGameEngine engine;
	private IGameController controller;

	/**
	 * Construtor da classe GameBoard
	 */

	@Inject
	public GameView(GameController controller, GameEngine engine) {
		this.controller = controller;
		this.engine = engine;
	}

	public void start() {
		controller.start();
	}

	/**
	 * Atualiza o componente view (representado pela classe GameBoard),
	 * possivelmente como uma resposta a uma atualizacao do jogo.
	 */
	public void update() {// TODO apagar impressao da tela

		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			System.out
					.println("Erro ao definir modo de compatibilidade com a plataforma MAC OS");
		}
		JOptionPane
				.showMessageDialog(
						null,
						"                                        GAME OF LIFE BEGINS.\n"
								+ "                                        REGRAS E ESTRUTURA DO JOGO DO JOGO:\n\n"
								+ "*****************************************************************************************************************************************************************************\n\n"
								+ " 1) ESTRUTURA DO JOGO:\n"
								+ "     1.1) Apos essa mensagem, deve-se setar o tamanho desejado para o tabuleiro, o qual será usado no restante do jogo.\n"
								+ "     1.2) Com o tamanho do tabuleiro setado, sera mostrado um menu de opcoes com as acoes que se pode realizar.\n"
								+ "     1.3) Caso a opcaoo leve ao tabuleiro, havera duas configuracoess possiveis:\n"
								+ "         1.3.1) A primeira pode-se gerar uma configuracoes das celulas atraves de clicks do mouse. Concluida a configuracao pode-se voltar ao menu.\n"
								+ "         1.3.2) A segunda não permite que o estado da célula seja alterado e permite controlar o calculo das geracoes e voltar para o menu.\n"
								+ "     1.4) e possivel escolher a regra que sera� utilizada para o calculo dos proximos estados.\n"
								+ "     1.5) Pode-se escolher a forma de execucao: sem interrupcoes ou controlada pelo usuario.\n"
								+ "     1.6) Pode-se sair da aplicacao.\n\n"
								+ " 2) REGRAS DO JOGO: A regra Conway e setada como default.\n"
								+ "     2.1) Conway:\n"
								+ "         2.1.1) A celula fica viva se houver dois ou tres vizinhos vivos.\n"
								+ "         2.1.2) A celula ressucita se houver tres vizinhos vivos.\n"
								+ "         2.1.3) Em todos os outros casos a celula morre.\n"
								+ "     2.2) Highlife:\n"
								+ "         2.2.1) A celula fica viva se houver dois ou tres vizinhos vivos.\n"
								+ "         2.2.2) A celula ressucita se houver tres ou seis vizinhos vivos.\n"
								+ "         2.2.3) Em todos os outros casos a celula morres.\n"
								+ "     2.3) LiveFreeOrDie:\n"
								+ "         2.3.1) A celula fica se todos os seus vizinhos estiverem mortos.\n"
								+ "         2.3.2) A celula ressucita se houver dois vizinhos vivos.\n"
								+ "         2.3.3) Em todos os outros casos a celula morre.\n\n"
								+ "*****************************************************************************************************************************************************************************\n");

		new ComboBox(engine, controller, engine.getStatistics());
	}

	public void update(int time) {
		printFirstRow();
		printLine();
		for (int i = 0; i < engine.getHeight(); i++) { // int i = 1; i <
														// (engine.getHeight() -
														// 1); i++;
			for (int j = 0; j < engine.getWidth(); j++) { // int j = 1; j <
															// (engine.getHeight()
															// - 1); j++
				System.out.print(engine.isCellAlive(i, j) ? ALIVE_CELL
						: DEAD_CELL);
			}
			System.out.println("   " + i);
			printLine();
		}
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void nonStopGame() {
		controller.nonStopGame();
	}

	public void makeCellAlive() {
		int i, j = 0;
		Scanner s = new Scanner(System.in);

		do {
			System.out.print("\n Inform the row number (0 - "
					+ (engine.getHeight() - 1) + "): "); // -1;

			i = s.nextInt();

			System.out.print("\n Inform the column number (0 - "
					+ (engine.getWidth() - 1) + "): ");

			j = s.nextInt();
		} while (!validPosition(i, j));

		controller.makeCellAlive(i, j);
	}

	public void nextGeneration() {
		controller.nextGeneration();
	}

	public void halt() {
		controller.halt();
	}

	public boolean validPosition(int i, int j) { // TODO: Mudar esse método;
		System.out.println(i);
		System.out.println(j);
		return i >= 0 && i < engine.getHeight() && j >= 0
				&& j < engine.getWidth();
	}

	public int parseOption(String option) {
		if (option.equals("1")) {
			return MAKE_CELL_ALIVE;
		} else if (option.equals("2")) {
			return NEXT_GENERATION;
		} else if (option.equals("3")) {
			return CONWAY;
		} else if (option.equals("4")) {
			return HIGH_LIFE;
		} else if (option.equals("5")) {
			return AUTOMATIC;
		} else if (option.equals("6")) {
			return HALT;
		} else
			return INVALID_OPTION;
	}

	/* Imprime uma linha usada como separador das linhas do tabuleiro */
	public void printLine() {
		for (int j = 0; j < engine.getWidth(); j++) { // int i = 1; i <
														// (engine.getHeight() -
														// 1); i++
			System.out.print(LINE);
		}
		System.out.print("\n");
	}

	/*
	 * Imprime os identificadores das colunas na primeira linha do tabuleiro
	 */
	public void printFirstRow() {
		System.out.println("\n \n");
		for (int j = 0; j < engine.getWidth(); j++) { // int i = 1; i <
														// (engine.getHeight() -
														// 1); i++
			System.out.print("   " + j + "   ");
		}
		System.out.print("\n");
	}

	@Override
	public void printOptions() {
		// TODO Auto-generated method stub

	}
}
