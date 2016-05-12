package br.unb.cic.lp.gol;

import Exceptions.noStrategySetted;
import GUI.Regra;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import br.unb.cic.lp.gol.Interfaces.EstrategiaDeDerivacao;
import br.unb.cic.lp.gol.Interfaces.IGameEngine;
import br.unb.cic.lp.gol.Interfaces.IStatistics;
import br.unb.cic.lp.gol.estrategias.Conway;
import br.unb.cic.lp.gol.estrategias.HighLife;



/**
 * Representa um ambiente (environment) do jogo GameOfLife.
 * 
 * Essa implementacao segue o padrao de projeto Strategy, e a 
 * classe GameEngine possui uma referencia para uma estrategia de 
 * derivacao que pode ser alterada durante a execucao do jogo. 
 * @author rbonifacio
 */

@Singleton
public class GameEngine implements IGameEngine {
	
	private static final int CONWAY = 3;
	private static final int HIGH_LIFE = 4;
	
	public int height;
	public int width;
	private Cell[][] cells;
	private IStatistics statistics;
	private EstrategiaDeDerivacao estrategia;
	
	private Regra regra = new Regra();;
	private Injector injector;
	
	/**
	 * Construtor da classe Environment.
	 * 
	 * @param height
	 *            dimensao vertical do ambiente
	 * @param width
	 *            dimensao horizontal do ambiente
	 */

	  // TODO: Aqui eu tive que tirar os argumentos height e width do construtor; Colocar a leitura do tamanho em algum lugar;
	public GameEngine(/*IStatistics statistics*/) {
		int height, width;
		
		height = 100; //12
		width = 100; //12
		
		this.height = height;
		this.width = width;

		cells = new Cell[height][width];

		for (int i = 0; i < height; i++) { //i = 1; i < height - 1;
			for (int j = 0; j < width; j++) { //j = 1; j < width - 1;
				cells[i][j] = new Cell();
			}
		}
		
		//setEstrategia(new Conway());
		injector = Guice.createInjector(new BindingsForGame());
		estrategia = (injector.getInstance(Key.get(EstrategiaDeDerivacao.class, Names.named("conway"))));
		
	}
	/*
	public void chooseEstrategia(int option) {
		switch (option) {
		case HIGH_LIFE:
			//setEstrategia(new HighLife());
			estrategia = (injector.getInstance(Key.get(EstrategiaDeDerivacao.class, Names.named("highlife"))));
			break;
		default:
			//setEstrategia(new Conway());
			estrategia = (injector.getInstance(Key.get(EstrategiaDeDerivacao.class, Names.named("conway"))));
			break;
		}
	}*/
	
	public void chooseEstrategia() {
	
		try {
			String str = regra.getRegra();
			
			
			estrategia = (injector.getInstance(Key.get(EstrategiaDeDerivacao.class, Names.named(str))));
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Metodo Inexistente");
			
			chooseEstrategia();
		}
	}
	
	public void setEstrategia(EstrategiaDeDerivacao e) {
		estrategia = e;
	}

	public EstrategiaDeDerivacao getEstrategia() {
		return estrategia;
	}
	
	public IStatistics getStatistics() {
		return statistics;
	}

	@Inject
	public void setStatistics(IStatistics statistics) {
		this.statistics = statistics;
	}

	/**
	 * Calcula uma nova geracao do ambiente. Essa implementacao delega para 
	 * a estrategia de derivacao a logica necessaria para identificar 
	 * se uma celula deve se tornar viva ou ser mantida viva na proxima 
	 * geracao. 
	 */
	public void nextGeneration() {
		List<Cell> mustRevive = new ArrayList<Cell>();
		List<Cell> mustKill = new ArrayList<Cell>();
		
		for (int i = 0; i < height; i++) { // i = 1; i < height -1;
			for (int j = 0; j < width; j++) { // j = 1; j < width -1;
				if (estrategia.shouldRevive(i, j, this)) {
					mustRevive.add(cells[i][j]);
				} 
				else if ((!estrategia.shouldKeepAlive(i, j, this)) && cells[i][j].isAlive()) {
					mustKill.add(cells[i][j]);
				}
			}
		}
		
		updateStatistics(mustRevive, mustKill);
	}

	/*
	 * Metodo auxiliar que atualiza as estatisticas das celulas 
	 * que foram mortas ou se tornaram vivas entre duas geracoes. 
	 */
	public void updateStatistics(List<Cell> mustRevive, List<Cell> mustKill) {
		for (Cell cell : mustRevive) {
			cell.revive();
			statistics.recordRevive();
		}
		
		for (Cell cell : mustKill) {
			cell.kill();
			statistics.recordKill();
		}
	}
	
	/**
	 * Torna a celula de posicao (i, j) viva
	 * 
	 * @param i posicao vertical da celula
	 * @param j posicao horizontal da celula
	 * 
	 * @throws InvalidParameterException caso a posicao (i, j) nao seja valida.
	 */
	public void makeCellAlive(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			cells[i][j].revive();
			statistics.recordRevive();
		}
		else {
			new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}
	
	public void makeCellDead(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			cells[i][j].kill();
			statistics.recordKill();
		}
		else {
			new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}
	/**
	 * Verifica se uma celula na posicao (i, j) estah viva.
	 * 
	 * @param i Posicao vertical da celula
	 * @param j Posicao horizontal da celula
	 * @return Verdadeiro caso a celula de posicao (i,j) esteja viva.
	 * 
	 * @throws InvalidParameterException caso a posicao (i,j) nao seja valida. 
	 */
	public boolean isCellAlive(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			return cells[i][j].isAlive();
		}
		else {
			throw new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}

	/**
	 * Retorna o numero de celulas vivas no ambiente. 
	 * Esse metodo eh particularmente util para o calculo de 
	 * estatisticas e para melhorar a testabilidade.
	 * 
	 * @return  numero de celulas vivas.
	 */
	public int numberOfAliveCells() {
		int aliveCells = 0;
		for(int i = 0; i < height; i++) { // i = 1; i < height -1;
			for(int j = 0; j < width; j++) { // j = 1; j < height -1;
				if(isCellAlive(i,j)) {
					aliveCells++;
				}
			}
		}
		return aliveCells;
	}

	/*
	 * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
	 * de referencia identificada pelos argumentos (i,j).
	 */
	public int numberOfNeighborhoodAliveCells(int i, int j) {
		int alive = 0;
		int x, y; // Váriaveis auxiliares;
		for (int a = i - 1; a <= i + 1; a++) {
			for (int b = j - 1; b <= j + 1; b++) {
				if (validPosition(a, b)  && (!(a == i && b == j)) && cells[a][b].isAlive()) { /* Não há problema nas bordas; */
					alive++;
				}
				else { /* Houve problema nas bordas; */
					x = a;
					y = b;
					if((x < 0 && y < 0) || (x >= getHeight() && y < 0) ||
					   (x < 0 && y >= getWidth()) || (x >= getHeight() && y >= getWidth())) { 
						/* Não faz nada; */
					}
					else {
						if(x < 0 || x >= getHeight())
							x = retRightHeight(a);
						else if(y < 0 || y >= getWidth())
							y = retRightWidth(b);
							
						if(validPosition(x, y) && (!(x == i && y == j)) && cells[x][y].isAlive()) {
							alive++;
						}
					}
				}
			}
		}
		return alive;
	}

	/*
	 * Verifica se uma posicao (a, b) referencia uma celula valida no tabuleiro.
	 */
	public boolean validPosition(int a, int b) {
		return a >= 0 && a < height && b >= 0 && b < width; //TODO: a > 0 && a < (height-1) && b > 0 && b < (width - 1);
	}

	public int retRightWidth(int index) {
		int aux = 0;
		if(index >= getWidth()) {
			aux = 0; 
		}
		else if(index < 0) {
			aux = (getWidth() - 1);
		}
		return aux;
	}
	
	public int retRightHeight(int index) {
		int aux = 0;
		if(index >= getHeight()) {
			aux = 0; 
		}
		else if(index < 0) {
			aux = (getHeight() - 1);
		}
		return aux;
	}
	/* Metodos de acesso as propriedades height e width; */
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	
	
}
