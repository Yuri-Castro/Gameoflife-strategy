package br.unb.cic.lp.gol.Interfaces;

import java.util.Scanner;

import br.unb.cic.lp.gol.estrategias.Conway;
import br.unb.cic.lp.gol.estrategias.HighLife;

public interface IGameView {

	public void start();
	public void update();
	public void update(int time);
	public void printOptions();
	public void nonStopGame();
	public void makeCellAlive();
	public void nextGeneration();
	public void halt();
	public boolean validPosition(int i, int j);
	public int parseOption(String option);
	public void printLine();
	public void printFirstRow();
	
}
