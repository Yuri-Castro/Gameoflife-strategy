package Exceptions;

public class noStrategySetted extends Exception {
	
	@Override
	public String getMessage() {
		return "A estratégia ainda não foi escolhida!\n";
	}

}
