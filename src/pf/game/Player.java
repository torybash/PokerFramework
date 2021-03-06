package pf.game;

public class Player {
	
	public enum BettingState{
		NORMAL,
		FOLDED
	}
	
	public Card[] holeCards;
	public BettingState bettingsState;

	private int chips;
	private int currentBet;
	
	
	public int getChips() {
		return chips;
	}

	public int getCurrentBet() {
		return currentBet;
	}
	

	public Player(int startChips){
		chips = startChips;
	}
	
	public void DealCards(Card[] cards){
		holeCards = cards;
	}
	
	public void PutBet(int bet){
		int chipsBet = chips >= bet ? bet : chips;
		chips -= chipsBet;
		currentBet += chipsBet;
	}
}
