package br.unb.cic.lp.gol.Interfaces;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import br.unb.cic.lp.gol.Cell;

public interface IGameEngine {

	public void chooseEstrategia();
	public void setEstrategia(EstrategiaDeDerivacao e);
	public EstrategiaDeDerivacao getEstrategia();
	public void nextGeneration();
	public void updateStatistics(List<Cell> mustRevive, List<Cell> mustKill);
	public void makeCellAlive(int i, int j) throws InvalidParameterException;
	public boolean isCellAlive(int i, int j) throws InvalidParameterException;
	public int numberOfAliveCells();
	public int numberOfNeighborhoodAliveCells(int i, int j);
	public boolean validPosition(int a, int b);
	public int retRightWidth(int index);
	public int retRightHeight(int index);
	public int getHeight();
	public void setHeight(int height);
	public int getWidth();
	public void setWidth(int width);
	public void makeCellDead(int i, int j);	

}
