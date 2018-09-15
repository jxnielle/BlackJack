package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.table.TableStringConverter;

import junit.framework.TestCase;

public class BlackjackTest extends TestCase{
	public void testDeck() {
		Deck deck = new Deck();
		Hand aHand = new Hand();
		
		assertEquals(52, deck.getCards().size());
		
		Card drawedCard = deck.drawCard();
		assertNotNull(drawedCard);
		aHand.add(drawedCard);	
		
		assertFalse(deck.findCard(drawedCard));			
	}
	
	public void testHand() {
		Deck deck = new Deck();
		Hand aHand = new Hand();
		Card drawedCard = deck.drawCard();

		aHand.add(drawedCard);

		assertTrue(aHand.getCards().contains(drawedCard));		
	}
	
	public void testShuffle() {
		Deck deck1 = new Deck();
		Deck deck2 = new Deck();
		
		assertFalse(deck1.same(deck2));	
		assertTrue(deck1.same(deck1));
		
	}
	
	public void testHandFromFile() {
		try {
			
			String[] cards = readFile("test.txt");
			assertTrue(cards.length > 4);

			Hand dealerHand = new Hand();
			Hand playerHand = new Hand();

			playerHand.add(File.toCard(cards[0]));
			playerHand.add(File.toCard(cards[1]));
			
			dealerHand.add(File.toCard(cards[2]));
			dealerHand.add(File.toCard(cards[3]));
			
			
			assertEquals(File.toCard(cards[0].getValue()), 10);
			assertTrue(dealerHand.hasAce());
			assertFalse(dealerHand.hasBlackjack());
			assertFalse(dealerHand.isBust());
			
			assertTrue(playerHand.hasAce());
			assertTrue(playerHand.hasBlackjack());
									
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}	
	
	private String[] readFile(String fileName) throws IOException {

		BufferedReader br;
		br = new BufferedReader(new FileReader(fileName));
		String line;
		line = br.readLine();
		br.close();
		
		String[] moves = line.split(" ");
		return moves;				
	}
}
