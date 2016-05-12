package br.unb.cic.lp.gol;

import com.google.inject.Guice;
import com.google.inject.Injector;


import GUI.ComboBox;
import br.unb.cic.lp.gol.Interfaces.IGameView;
import br.unb.cic.lp.gol.estrategias.Conway;

/**
 * Classe que define o metodo principal do programa; ou 
 * seja, o metodo que vai ser executado pela JVM para inicializar 
 * o programa. 
 * 
 * @author rbonifacio
 */
public class Main {

	public static void main(String args[]) {

		/* Injecao de dependencia; */
		Injector injector = Guice.createInjector(new BindingModule());
		IGameView board = injector.getInstance(GameView.class);
		//mensagem
	
		
		board.start();
		
		
	}
}

/*
Injector injector = Guice.createInjector(new BindingController());
GameController controller = injector.getInstance(GameController.class);
//GameController controller = new GameController();

Statistics statistics = new Statistics();

GameEngine engine = new GameEngine(10, 10, statistics);	

//nessa implementacao, a estrategia do Conway eh 
//configurada como a estrategia inicial. 
engine.setEstrategia(new Conway());

GameView board = new GameView(controller, engine);

controller.setBoard(board);
controller.setEngine(engine);
controller.setStatistics(statistics);

controller.start();*/