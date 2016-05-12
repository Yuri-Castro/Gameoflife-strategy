package br.unb.cic.lp.gol;

import br.unb.cic.lp.gol.Interfaces.EstrategiaDeDerivacao;
import br.unb.cic.lp.gol.estrategias.Conway;
import br.unb.cic.lp.gol.estrategias.HighLife;
import br.unb.cic.lp.gol.estrategias.LiveFreeOrDie;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class BindingsForGame extends AbstractModule {

	@Override
	protected void configure() {
		bind(EstrategiaDeDerivacao.class).annotatedWith(Names.named("conway")).to(Conway.class);
		bind(EstrategiaDeDerivacao.class).annotatedWith(Names.named("highlife")).to(HighLife.class);
		bind(EstrategiaDeDerivacao.class).annotatedWith(Names.named("livefreeordie")).to(LiveFreeOrDie.class);
	}

	
}
