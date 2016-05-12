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
