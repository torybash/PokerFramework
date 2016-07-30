package pf.util;

import java.util.Arrays;
import java.util.Comparator;

import pf.game.Card;
import pf.game.Card.CardSuit;

public class GameHelper {
			
	public enum HandType{
		HIGH_CARD(0),
		PAIR(1000000),
		TWO_PAIR(2000000),
		THREE_OF_A_KIND(3000000),
		STRAIGHT(4000000),
		FLUSH(5000000),
		FULL_HOUSE(6000000),
		FOUR_OF_A_KIND(7000000),
		STRAIGHT_FLUSH(8000000),
		;
		
	     private final int value;
	     private HandType(int value) {
	         this.value = value;
	     }
	     
	     public int GetValue(){
	    	 return value;
	     }
	}
	
	public static int GetBestHand(Card[] cards, Card[] resultCards){
		int bestHandVal = -1;
		for (int i = 0; i < cards.length; i++) {
			for (int j = i + 1; j < cards.length; j++) {
				
				Card[] hand = new Card[5];
				int count = 0;				
				for (int k = 0; k < cards.length; k++) {
					if (k == i || k == j) continue;
					hand[count++] = cards[k];
				}
				
				int val = GetHandValue(hand);
				if (val > bestHandVal){
					bestHandVal = val;
					for (int k = 0; k < resultCards.length; k++) {
						resultCards[k] = hand[k];
					}
				}
			}
		}
		return bestHandVal;
	}
	
	
	public static int GetHandValue(Card[] cards){
		int val = 0;
		HandType handType = HandType.HIGH_CARD;
		SortByValue(cards);

		if (IsStraightFlush(cards)){
			handType = HandType.STRAIGHT_FLUSH;
		}else if (IsOfSameType(cards, 4, 0)){
			handType = HandType.FOUR_OF_A_KIND;
		}else if (IsOfSameType(cards, 3, 2)){
			handType = HandType.FULL_HOUSE;
		}else if (IsFlush(cards)){
			handType = HandType.FLUSH;
		}else if (IsStraight(cards)){
			handType = HandType.STRAIGHT;
		}else if (IsOfSameType(cards, 3, 0)){
			handType = HandType.THREE_OF_A_KIND;
		}else if (IsOfSameType(cards, 2, 2)){
			handType = HandType.TWO_PAIR;
		}else if (IsOfSameType(cards, 2, 0)){
			handType = HandType.PAIR;
		}

		val += handType.GetValue();
			
		for (int i = 0; i < 5; i++) {
			val += cards[i].number * (int)Math.pow(13, 4 - i);
		}
				
		return val;
	}
	
	private static boolean IsStraightFlush(Card[] cards){
		if (IsStraight( cards) && IsFlush(cards)) return true;
		return false;
	}
	
//	private int GetFourOfAKind(Card[] cards){
//		return GetOfSameType(cards, 4);
//	}
//	
//	private int GetFullHouse(Card[] cards){
//		//TODO!!
//	}
	
	private static boolean IsStraight(Card[] cards){
		int startNumber = cards[0].number;
		for (int i = 1; i < cards.length; i++) {
			if (cards[i].number + i != startNumber) return false;
		}
		return true;
	}
	
	private static boolean IsFlush(Card[] cards){
		CardSuit suit = cards[0].cardSuit;
		for (int i = 1; i < cards.length; i++) {
			if (cards[i].cardSuit != suit) return false;
		}	
		return true;
	}
	
	


	
	private static boolean IsOfSameType(Card[] cards, int amountmajor, int amountminor){
		SortByValue(cards);
		Pair[] equals =  new Pair[5];
		
		
		
		for( int i=0; i<5; i++){
			equals[i] = new Pair(i, 0);
			for(int j=0; j<5; j++){
				if( cards[i].number == cards[j].number ) equals[i].b++;					
			}
		}

		Arrays.sort(equals, new Comparator<Pair>() {
		    public int compare(Pair o1, Pair o2) {
		        return o2.compareB(o1);
		    }
		});
		
		
		Card[] sortedCards = new Card[5];
		for (int i = 0; i < 5; i++) sortedCards[i] = cards[equals[i].a];
		for (int i = 0; i < 5; i++) cards[i] = sortedCards[i];
						
		if(equals[0].b == amountmajor && equals[amountmajor].b >= amountminor) return true;
		else return false;
	}
	
	
	private static void SortByValue(Card[] cards){
		Arrays.sort(cards, new Comparator<Card>() {
		    public int compare(Card o1, Card o2) {
		        return o2.compareTo(o1);
		    }
		});
	}
}


