package core;

import java.io.IOException;

import junit.framework.TestCase;

public class BlackjackTest extends TestCase
{
	public void testDeck() 
	{
		Deck deck = new Deck();
		Hand aHand = new Hand();
		
		assertEquals(52, deck.getCards().size());
		
		Card drawedCard = deck.drawCard();
		assertNotNull(drawedCard);
		aHand.add(drawedCard);	
		
		assertEquals(-1, deck.findCard(drawedCard));			
	}
	
	public void testHand() 
	{
		Deck deck = new Deck();
		Hand aHand = new Hand();
		Card drawedCard = deck.drawCard();

		aHand.add(drawedCard);

		assertTrue(aHand.getCards().contains(drawedCard));		
	}
	
	public void testShuffle() 
	{
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		
		assertFalse(deck1.same(deck2));	
		assertTrue(deck1.same(deck1));		
	}
	
	public void testCardVisibility() 
	{
		Deck deck = new Deck();
		Hand playerHand = new Hand();
		Hand dealersHand = new Hand();
		
		Card card1 = deck.drawCard();
		Card card2 = deck.drawCard();
		Card card3 = deck.drawCard();
		Card card4 = deck.drawCard();
		
		playerHand.add(card1);
		playerHand.add(card2);
		
		assertTrue(playerHand.getCard(card1).isVisible());
		assertTrue(playerHand.getCard(card1).isVisible());
		
		dealersHand.add(card3);
		dealersHand.add(card4);
		
		dealersHand.getCard(card4).setVisibility(false);
		
		assertTrue(dealersHand.getCard(card3).isVisible());
		assertFalse(dealersHand.getCard(card4).isVisible());	
	}
	
	public void testCardValues() {
		Hand aHand = new Hand();
		
		aHand.add(new Card('C', "A"));
		aHand.add(new Card('D', "9"));		
		
		assertEquals(20, aHand.getHandValue());
		
		aHand.add(new Card('S', "A"));
		
		assertEquals(21, aHand.getHandValue());
		assertTrue(aHand.hasBlackjack());
			
		Card aKing = new Card('H', "K");
		Card aQueen = new Card('D', "Q");
		Card aJack =  new Card('S', "J");

		assertEquals(10, aKing.getValue());
		assertEquals(10, aQueen.getValue());
		assertEquals(10, aJack.getValue());
		
		Hand aHand2 = new Hand();
		aHand2.add(new Card('H', "10"));
		aHand2.add(new Card('C', "9"));
		aHand2.add(new Card('H', "A"));
		aHand2.add(new Card('D', "A"));
		
		assertEquals(21, aHand2.getHandValue());		
	}
	
	public void testWinner() {
		// -- Both player and dealer have blackjack --
		Blackjack blackjack = new Blackjack();
		blackjack.getPlayerHand().add(blackjack.getDeck().drawCard(new Card('C', "A")));
		blackjack.getPlayerHand().add(blackjack.getDeck().drawCard(new Card('D', "10")));		
		
		blackjack.getDealerHand().add(blackjack.getDeck().drawCard(new Card('S', "A")));
		blackjack.getDealerHand().add(blackjack.getDeck().drawCard(new Card('H', "K")));
		
		assertEquals(21, blackjack.getPlayerHand().getHandValue());
		assertEquals(21, blackjack.getDealerHand().getHandValue());
		
		assertTrue(blackjack.getDealerHand().hasBlackjack());
		assertTrue(blackjack.getPlayerHand().hasBlackjack());
			
		assertEquals(blackjack.getDealerHand(), blackjack.getWinner());
		assertEquals(blackjack.getPlayerHand(), blackjack.getWinner());	
		
		// -- Player has blackjack and dealer does not --
		Blackjack blackjack2 = new Blackjack();
		blackjack2.getPlayerHand().add(blackjack2.getDeck().drawCard(new Card('C', "A")));
		blackjack2.getPlayerHand().add(blackjack2.getDeck().drawCard(new Card('D', "10")));
		
		blackjack2.getDealerHand().add(blackjack2.getDeck().drawCard(new Card('S', "A")));
		blackjack2.getDealerHand().add(blackjack2.getDeck().drawCard(new Card('H', "2")));
		
		assertEquals(21, blackjack2.getPlayerHand().getHandValue());
		assertEquals(13, blackjack2.getDealerHand().getHandValue());	
		
		assertEquals(blackjack2.getDealerHand(), blackjack2.getWinner());
		assertEquals(blackjack2.getPlayerHand(), blackjack2.getWinner());	
		
		// -- Player Bust -- 
		Blackjack blackjack3 = new Blackjack();
		blackjack3.getPlayerHand().add(blackjack3.getDeck().drawCard(new Card('C', "K")));
		blackjack3.getPlayerHand().add(blackjack3.getDeck().drawCard(new Card('D', "5")));
		blackjack3.getPlayerHand().add(blackjack3.getDeck().drawCard(new Card('H', "3")));
		blackjack3.getPlayerHand().add(blackjack3.getDeck().drawCard(new Card('S', "Q")));
				
		blackjack3.getDealerHand().add(blackjack3.getDeck().drawCard(new Card('S', "A")));
		blackjack3.getDealerHand().add(blackjack3.getDeck().drawCard(new Card('H', "2")));	
		
		assertTrue(blackjack3.getPlayerHand().isBust());
		assertEquals(28, blackjack3.getPlayerHand().getHandValue());
		
		assertFalse(blackjack3.getDealerHand().isBust());
		assertEquals(13, blackjack3.getDealerHand().getHandValue());
		
		assertEquals(blackjack3.getDealerHand(), blackjack2.getWinner());
		assertEquals(blackjack3.getPlayerHand(), blackjack2.getWinner());

		// -- Dealer Bust -- 
		Blackjack blackjack4 = new Blackjack();
		blackjack4.getPlayerHand().add(blackjack4.getDeck().drawCard(new Card('C', "K")));
		blackjack4.getPlayerHand().add(blackjack4.getDeck().drawCard(new Card('D', "K")));
				
		blackjack4.getDealerHand().add(blackjack4.getDeck().drawCard(new Card('S', "10")));
		blackjack4.getDealerHand().add(blackjack4.getDeck().drawCard(new Card('H', "2")));	
		blackjack4.getDealerHand().add(blackjack4.getDeck().drawCard(new Card('C', "10")));	
		
		assertFalse(blackjack4.getPlayerHand().isBust());
		assertEquals(20, blackjack4.getPlayerHand().getHandValue());
		
		assertTrue(blackjack4.getDealerHand().isBust());
		assertEquals(22, blackjack4.getDealerHand().getHandValue());
		
		assertEquals(blackjack4.getDealerHand(), blackjack2.getWinner());
		assertEquals(blackjack4.getPlayerHand(), blackjack2.getWinner());
		
	}
	
	public void testFromFile() {
		try {
			FileConverter fileConvert = new FileConverter();
			String[] cards = fileConvert.splitLine("src/test/resources/core/testFiles/test.txt");
			
			assertEquals(cards.length, 4);
			Hand dealerHand = new Hand();
			Hand playerHand = new Hand();

			playerHand.add(fileConvert.toCard(cards[0]));
			playerHand.add(fileConvert.toCard(cards[1]));
			
			dealerHand.add(fileConvert.toCard(cards[2]));
			dealerHand.add(fileConvert.toCard(cards[3]));
						
			assertEquals(fileConvert.toCard(cards[0]).getValue(), 10);
			assertTrue(dealerHand.hasAce());
			assertTrue(dealerHand.hasBlackjack());
			assertFalse(dealerHand.isBust());
			
			assertTrue(playerHand.hasAce());
			assertTrue(playerHand.hasBlackjack());
									
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}	
}
;