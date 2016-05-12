package br.unb.cic.lp.gol;

import br.unb.cic.lp.gol.Interfaces.EstrategiaDeDerivacao;
import br.unb.cic.lp.gol.Interfaces.IGameController;
import br.unb.cic.lp.gol.Interfaces.IGameEngine;
import br.unb.cic.lp.gol.Interfaces.IGameView;
import br.unb.cic.lp.gol.Interfaces.IStatistics;
import br.unb.cic.lp.gol.estrategias.Conway;
import br.unb.cic.lp.gol.estrategias.HighLife;
import br.unb.cic.lp.gol.estrategias.LiveFreeOrDie;

import com.google.inject.AbstractModule;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class BindingModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(IGameView.class).to(GameView.class);
		bind(IGameController.class).to(GameController.class);
		bind(IGameEngine.class).to(GameEngine.class);
		bind(IStatistics.class).to(Statistics.class);
	}

	
}
