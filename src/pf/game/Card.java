package pf.game;

public class Card {

	public enum CardSuit{
		SPADES, CLUBS, HEARTS, DIAMONDS
	}
	
	public CardSuit cardSuit;
	public int number; //1-13
	
	public Card(){
		
	}
	
	public Card(CardSuit suit, int num){
		cardSuit = suit;
		number = num;
	}
	
	@Override
	public String toString() {
		return "Card[" + cardSuit + ":" + CardName() + "]";
	}
	
	
	private String CardName(){
		String name = "";
		if (number < 9) name = "" + (number + 2);
		else if (number == 9) name = "J";
		else if (number == 10) name = "Q";
		else if (number == 11) name = "K";
		else if (number == 12) name = "A";
		
		return name;
	}

	public int compareTo(Card o1) {
		// TODO Auto-generated method stub
		if (number > o1.number) return 1;
		else if (number == o1.number) return 0;
		else return -1;
	}
}
