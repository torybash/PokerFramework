package pf.game;

import java.util.ArrayList;
import java.util.Collections;

import pf.game.Card.CardSuit;

public class Deck {

	
	public ArrayList<Card> cardList;
	
	public Deck(){
		cardList = new ArrayList<Card>();
		for (int i = 0; i < 52; i++) {
			Card card = new Card();
			card.cardSuit = CardSuit.values()[i / 13];
			card.number = i % 13;
			
			cardList.add(card);
		}
	}
	
	
	
	
	public void Shuffle(){
		Collections.shuffle(cardList);
	}
	
	public Card TakeCard(){
		if (cardList.size() == 0){
			System.out.println("NO CARDS LEFT");
			return null;
		}
		Card card = cardList.get(cardList.size() - 1);
		cardList.remove(cardList.size() - 1);
		return card;
	}
}
