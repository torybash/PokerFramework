package pf.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {

	ArrayList<Player> playerList;
	
	Deck deck;
	
	Blinds blinds;
	
	int dealerPosition;
	
	
	public Game(){
	}
	
	
	public void StartGame(int playerCount){

		playerList = new ArrayList<Player>();
		for (int i = 0; i < playerCount; i++) {
			Player player = new Player();
			playerList.add(player);
		}
		blinds = new Blinds(10, 20);
		
		dealerPosition = new Random().nextInt(playerCount);
		
		//while(){
		PlayTurn();
		//}
		
	}
	
	private boolean BettingRound(){
		return false;
	}
	
	private void PlayTurn(){
		
		//Make deck
		deck = new Deck();
		deck.Shuffle();
		
		//Deal hole cards
		for (int i = 0; i < playerList.size(); i++) {
			Player player = playerList.get(i);
			Card[] cards = new Card[2];
			for (int j = 0; j < cards.length; j++) {
				Card card = deck.TakeCard();
				cards[j] = card;
			}
			System.out.println("Dealt player " + i + " cards: "+ cards[0] + " : " + cards[1]);
			player.DealCards(cards);
		}
		
		
		try {
			int red = System.in.read();
			System.out.println("red: "+ red);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Player blinds
		
		//Betting round
		//if !Complete 
		//	Deal flop
		//	Betting round
		//	if !Complete 
		//		Deal turn
		//		Betting round
		//		if !Complete 
		//			Deal river
		//			Betting round
		
		//Move money around
		
		//Remove players
		
		//Change blinds
		
		//Change dealerPosition !;
		
	}
}
