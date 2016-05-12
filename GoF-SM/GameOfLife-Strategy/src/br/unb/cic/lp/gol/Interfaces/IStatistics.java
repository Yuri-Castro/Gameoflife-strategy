package br.unb.cic.lp.gol.Interfaces;

public interface IStatistics {

	public int getRevivedCells();
	public void recordRevive();
	public int getKilledCells();
	public void recordKill();
	public void display();

}
