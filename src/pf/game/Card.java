package pf.game;

public class Card {

	public enum CardSuit{
		SPADES, CLUBS, HEARTS, DIAMONDS
	}
	
	public CardSuit cardSuit;
	public int number; //1-13
	
	public Card(){
		
	}
	
	@Override
	public String toString() {
		return "Card[" + cardSuit + ":" + number + "]";
	}
	
}
