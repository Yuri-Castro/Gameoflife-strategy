package br.unb.cic.lp.gol.Interfaces;

import java.security.InvalidParameterException;

import br.unb.cic.lp.gol.GameEngine;
import br.unb.cic.lp.gol.GameView;
import br.unb.cic.lp.gol.Statistics;

import com.google.inject.Inject;

public interface IGameController {

	public IGameEngine getEngine();
	public void setEngine(IGameEngine engine);
	public IGameView getBoard();
	public void setBoard(IGameView board);
	public void setStatistics(IStatistics statistics);
	public void start();
	public void halt();
	public void makeCellAlive(int i, int j);
	public void nextGeneration();
	public void nextGeneration(int time);
	public void nonStopGame();
	
}
