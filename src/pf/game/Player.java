package pf.game;

public class Player {
	
	public Card[] holeCards;
	
	public Player(){
		
	}
	
	public void DealCards(Card[] cards){
		holeCards = cards;
	}
}
