package core;

import junit.framework.TestCase;

public class BlackjackTest extends TestCase{
	public void testDeck() {
		Deck deck = new Deck();
		Hand aHand = new Hand();
		
		assertEquals(52, deck.getCards().size());
		
		Card drawedCard = deck.drawCard();
		aHand.add(drawedCard);	
		
		assertEquals(null, deck.findCard(drawedCard));			
	}
	
	public void testHand() {
		Deck deck = new Deck();
		Hand aHand = new Hand();
		Card drawedCard = deck.drawCard();
		
		aHand.add(drawedCard);

		assertEquals(null, aHand.findCard(drawedCard));
		
	}
}
