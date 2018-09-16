package core;

import java.util.ArrayList;
import java.util.Collections;

public class Deck 
{
	private ArrayList<Card> cards;
	private char[] suits = {'C', 'D', 'H', 'S'};
	private String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
		
	/*
	 * Function  : Deck
	 * Purpose   : a constructor 
	 */			
	public Deck() 
	{
		this.cards = new ArrayList<Card>(52);
		this.setDeck();
	}
	
	/*
	 * Function  : getCards
	 * Purpose   : get the cards in the deck 
	 * Returns   : an ArrayList of Card objects
	 */	
	public ArrayList<Card> getCards() 
	{
		return this.cards;
	}
	
	/*
	 * Function  : setDeck
	 * Purpose   : populates the ArrayList of Card objects
	 */		
	public void setDeck() 
	{		
		for (char suit: suits) 
		{			
			for ( String rank : ranks) 
			{
				cards.add(new Card (suit, rank));
			}
		}
		
		Collections.shuffle(cards);
	}
	
	/*
	 * Function  : findCard
	 * Parameter : a Card object
	 * Purpose   : checks if a Card object exists in the deck
	 * Returns   : true/false
	 */		
	public boolean findCard(Card aCard) 
	{
		for (Card card: this.cards) 
		{
			if(card.getRank().equals(aCard.getRank()) && card.getSuit() == aCard.getSuit()) 
			{
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * Function  : drawCard
	 * Purpose   : pulls a card from the deck
	 * Returns   : the Card object removed from the deck
	 */			
	public Card drawCard() 
	{
		Card aCard = this.cards.remove(cards.size() - 1);
		
		return aCard;
	}
	
	/*
	 * Function  : same
	 * Parameter : a Deck
	 * Purpose   : compare decks to see whether they contain the same card objects in the same order
	 * Returns   : true/false
	 */			
	public boolean same(Deck aDeck) 
	{
        for (int x = 0; x < cards.size(); x++) 
        {
        	if (cards.size() != aDeck.getCards().size() || 
        			(cards.get(x).getRank() != aDeck.getCards().get(x).getRank() && 
        			cards.get(x).getSuit() != aDeck.getCards().get(x).getSuit())) 
        	{
        		return false;
        	}
        }
        
        return true;
	}
}
