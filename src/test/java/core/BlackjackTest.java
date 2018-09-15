package core;

import java.awt.List;
import java.util.ArrayList;

import org.junit.validator.ValidateWith;

import junit.framework.TestCase;

public class BlackjackTest extends TestCase{
	public void testDeck() {
		Deck deck = new Deck();
		Hand aHand = new Hand();
		
		assertEquals(52, deck.getCards().size());
		
		Card drawedCard = deck.drawCard();
		aHand.add(drawedCard);	
		
		assertFalse(deck.findCard(drawedCard));			
	}
	
	public void testHand() {
		Deck deck = new Deck();
		Hand aHand = new Hand();
		Card drawedCard = deck.drawCard();
		
		aHand.add(drawedCard);

		assertTrue(aHand.findCard(drawedCard));		
	}
	
	public void testShuffle() {
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		
		assertFalse(deck1.same(deck2));	
		assertTrue(deck1.same(deck1));
		
	}
}
