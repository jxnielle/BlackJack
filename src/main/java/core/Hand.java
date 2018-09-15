package core;

import java.util.ArrayList;

public class Hand 
{
	private ArrayList<Card> cards;
	private int value;
	
	/*
	 * Function  : Hand
	 * Purpose   : a constructor 
	 */			
	public Hand() 
	{
		value = 0;
		cards = new ArrayList<Card>();
	}
	
	/*
	 * Function  : getCards
	 * Purpose   : gets the cards currently in the hand 
	 * Returns   : An ArrayList of Card objects
	 */			
	public ArrayList<Card> getCards()
	{
		return this.cards;
	}
	
	/*
	 * Function  : add
	 * Parameter : a Card object
	 * Purpose   : Adds a Card object to the hand 
	 */			
	public void add(Card card) 
	{
		cards.add(card);
	}
	
	/*
	 * Function  : hasAce
	 * Purpose   : determines if hand has an Ace
	 * Returns   : true/false
	 */			
	public boolean hasAce() 
	{
		return false;
	}
	
	/*
	 * Function  : isBust
	 * Purpose   : determines if hand value is greater than 21
	 * Returns   : true/false
	 */			
	public boolean isBust() 
	{
		return false;
	}
	
	/*
	 * Function  : hasBlackjack
	 * Purpose   : determines if hand value is a Blackjack
	 * Returns   : true/false
	 */			
	public boolean hasBlackjack() 
	{
		return false;
	}	
	
	/*
	 * Function  : getHandValue 
	 * Purpose   : add card values in hand
	 * Returns   : the total value of all cards currently in the hand 
	 */			
	{
		for (Card card : cards) 
		{
			value = value + card.getValue();
		}
		
		return this.value;
	}
}