package core;

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
	
	public Deck getDeck() {
		return this.deck;
	}
}















