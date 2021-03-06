package core;

import java.io.IOException;

public class Blackjack {
	private Hand dealerHand;
	private Hand playerHand;
	private Deck deck;
	
	public Blackjack() {
		this.dealerHand = new Hand();
		this.playerHand = new Hand();
		this.deck = new Deck();
	}
	
	public Blackjack(Hand dealerHand, Hand playerHand) {
		this.dealerHand = dealerHand;
		this.playerHand = playerHand;
		this.deck = new Deck();
	}
	
	public Hand getPlayerHand() {
		return this.playerHand;
	}
	
	public Hand getDealerHand() {
		return this.dealerHand;
	}
	
	public Deck getDeck() {
		return this.deck;
	}
	
	public Hand getWinner() {
		if (dealerHand.hasBlackjack() || playerHand.isBust() || 
				(dealerHand.getHandValue() > playerHand.getHandValue() && !dealerHand.isBust()) || 
				dealerHand.getHandValue() == playerHand.getHandValue()) 
		{	
			return dealerHand;
		}
		else
		{
			return playerHand;
		}
	}
	
	public void setHandsFromFile(String fileName) throws IOException{
		FileConverter fileConvert = new FileConverter();	
		String[] cards = fileConvert.splitLine(fileName);
		
		if (cards.length >= 4) {
			Card card1 = this.deck.drawCard(fileConvert.toCard(cards[0]));
			Card card2 = this.deck.drawCard(fileConvert.toCard(cards[1]));
			Card card3 = this.deck.drawCard(fileConvert.toCard(cards[2]));
			Card card4 = this.deck.drawCard(fileConvert.toCard(cards[3]));	
			
			if (card1 != null && card2 != null && card3 != null && card4 != null) {
				this.playerHand.add(card1);
				this.playerHand.add(card2);		
				this.dealerHand.add(card3);
				this.dealerHand.add(card4);		
			}
		}
	
		if (cards.length > 4) {
			boolean isStand = false;
			for (int i = 4; i < cards.length; i++) {
				if (cards[i].equals("S")) {
					isStand = true;
				}else if (cards[i].equals("H")) {
					isStand = false;
				}else if (!isStand && (deck.drawCard(fileConvert.toCard(cards[i])) != null)) {
					this.playerHand.add(fileConvert.toCard(cards[i]));
				}else if (isStand && (deck.drawCard(fileConvert.toCard(cards[i])) != null)) {
					this.dealerHand.add(fileConvert.toCard(cards[i]));
				}
				else {
					//Error
				}
			}		
		}	
	}
	
	public void addInitialCards() 
	{	
	    playerHand.add(deck.drawCard());
	    playerHand.add(deck.drawCard());
	    
	    Card dealerCard1 = deck.drawCard();
	    Card dealerCard2 = deck.drawCard();
	    dealerCard2.setVisibility(false);
	    
	    dealerHand.add(dealerCard1);
	    dealerHand.add(dealerCard2);
	}
	
	public void hitUntilStand() 
	{		
		while ((dealerHand.getHandValue() == 17 && dealerHand.hasAce()) 
				|| dealerHand.getHandValue() <= 16) 
		{
			dealerHand.add(deck.drawCard());
		}
	}
}















