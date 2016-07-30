package pf.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Random;

import pf.game.Card.CardSuit;
import pf.util.GameHelper;

public class Game {
	
	
	


	
	ArrayList<Player> playerList;
	
	Deck deck;
	
	Blinds blinds;
	
	int dealerPosition;
	int potSize;
	
	ArrayList<Card> tableCardList;
	
	
//	
//	public static void main(String[] args) {
//		Game game = new Game();
////		Card[] cards = new Card[]{ 
////				new Card(CardSuit.CLUBS, 7), 
////				new Card(CardSuit.HEARTS, 2), 
////				new Card(CardSuit.CLUBS, 2), 
////				new Card(CardSuit.CLUBS, 12), 
////				new Card(CardSuit.CLUBS, 7)
////		};
////		
////		boolean hasOfType = game.IsOfSameType(cards, 2, 2);
////		System.out.println("hasOfType: " + hasOfType);
////		System.out.println(Arrays.toString(cards));
//		
//		GameHelper helper = new GameHelper();
//		Deck deck = new Deck();
//		deck.Shuffle();
//		for (int i = 0; i < 100; i++) {
//			Card[] hand = new Card[5];
//			for (int j = 0; j < hand.length; j++) {
//				if (deck.cardList.size() == 0){
//					deck = new Deck();
//					deck.Shuffle();
//				}
//				hand[j] = deck.TakeCard();
//			}
//			
//			
//			int val = helper.GetHandValue(hand);
//			System.out.println("Hand: " + Arrays.toString(hand) + ", val: "+ val);
//		}
//		
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Game(){
//		HandType handType = HandType.Two_Pair;
	}
	
	
	public void StartGame(int playerCount){
		System.out.println("----STARTED GAME-----");
		
		playerList = new ArrayList<Player>();
		for (int i = 0; i < playerCount; i++) {
			Player player = new Player(1200);
			playerList.add(player);
		}
		blinds = new Blinds(10, 20);
		potSize = 0;
		tableCardList = new ArrayList<Card>();		
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
		
		//Player blinds
		int smallBlindIdx = (dealerPosition + 1) % playerList.size();
		int bigBlindIdx = (dealerPosition + 2) % playerList.size();
		playerList.get(smallBlindIdx).PutBet(blinds.currentSmallBlind);
		playerList.get(bigBlindIdx).PutBet(blinds.currentBigBlind);
		
		
		///TESTING
		for (int i = 0; i < 5; i++) {
			tableCardList.add(deck.TakeCard());
		}
		
		System.out.println("Table cards: " + Arrays.toString(tableCardList.toArray()));
		
		int bestVal = -1; int wonPlayerIdx = -1; //TODO multiple winners
		for (int i = 0; i < playerList.size(); i++) {
			Player p = playerList.get(i);
			
			Card[] playerHand = new Card[7];
			for (int j = 0; j < 2; j++) playerHand[j] = p.holeCards[j];
			for (int j = 2; j < 7; j++) playerHand[j] = tableCardList.get(j - 2);
			
			Card[] resultCards = new Card[5];
			int val = GameHelper.GetBestHand((Card[]) playerHand, resultCards);
			
			if (val > bestVal){
				bestVal = val;
				wonPlayerIdx = i;
			}
			
			System.out.println("Player " + i + " - best cards: "+ Arrays.toString(resultCards) + ", val: " + val);
		}
		
		System.out.println("Player " + wonPlayerIdx + " WON!!!!");
		
//		PrintState();
		
		///TESTING

		
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
		
		try {
			int red = System.in.read();
			System.out.println("red: "+ red);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void PrintState(){
		System.out.println("Game State - players: ");
		for (int i = 0; i < playerList.size(); i++) {
			Player p = playerList.get(i);
			System.out.println("Player " + i + " - cards: " + Arrays.toString(p.holeCards) + ", chips: "+ p.getChips() + ", bet: " + p.getCurrentBet() + (dealerPosition == i ? " <--- DEALER" : ""));
		}
	}
}
