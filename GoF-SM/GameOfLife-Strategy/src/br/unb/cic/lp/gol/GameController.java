package br.unb.cic.lp.gol;

import java.security.InvalidParameterException;
import java.lang.Thread;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import br.unb.cic.lp.gol.Interfaces.IGameController;
import br.unb.cic.lp.gol.Interfaces.IGameEngine;
import br.unb.cic.lp.gol.Interfaces.IGameView;
import br.unb.cic.lp.gol.Interfaces.IStatistics;


/**
 * Classe que atua como um controlador do 
 * padrao MVC, separando os componentes da 
 * camada de apresentacao e model. 
 * 
 * @author rbonifacio
 */
@Singleton
public class GameController implements IGameController {

	private IGameEngine engine;
	private IGameView board;
	private IStatistics statistics;
	private static final boolean TRUE = true;
	private static final int TIME = 2500;
	
	/*@Inject
	public GameController(GameEngine engine, GameView board) {
		this.engine = engine;
		this.board = board;
	}*/
	
	public IGameEngine getEngine() {
		return engine;
	}
	
	@Inject
	public void setEngine(IGameEngine engine) {
		this.engine = engine;
	}
	
	public IGameView getBoard() {
		return board;
	}
	
	@Inject
	public void setBoard(IGameView board) {
		this.board = board;
	}
	
	@Inject
	public void setStatistics(IStatistics statistics) {
		this.statistics = statistics;
	}
	
	public void start() {
		board.update();
	}
	
	public void halt() {
		//oops, nao muito legal fazer sysout na classe Controller
		System.out.println("\n \n");
		statistics.display();
		System.exit(0);
	}
	
	public void makeCellAlive(int i, int j) {
		try {
			engine.makeCellAlive(i, j);
			board.update();
		}
		catch(InvalidParameterException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void nextGeneration() {
		engine.nextGeneration();
	}
	
	public void nextGeneration(int time) {
		engine.nextGeneration();
		board.update(time);
	}

	public void nonStopGame() {
		while(TRUE) {
			nextGeneration(TIME);		
		}
	}
		
}
