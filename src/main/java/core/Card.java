package core;

public class Card 
{
	private char suit;
	private String rank;
	private boolean visible;
	
	/*
	 * Function  : Card
	 * Parameter : the suit and rank of the card 
	 * Purpose   : a constructor 
	 */			
	public Card(char suit, String rank) 
	{
		this.suit = suit;
		this.rank = rank;
		this.visible = true;
	}	
	
	/*
	 * Function  : getSuit
	 * Purpose   : returns the suit of a card
	 * Returns   : the suit 
	 */			
	public char getSuit() 
	{
		return this.suit;
	}
	
	/*
	 * Function  : getRank
	 * Purpose   : returns the rank of a card
	 * Returns   : the rank
	 */			
	public String getRank() 
	{
		return this.rank;
	}
		
	/*
	 * Function  : toString
	 * Purpose   : 
	 * Returns   : String representation of card
	 */		
	public String toString() 
	{
		return this.suit + this.rank;
	}
	
	/*
	 * Function  : getValue
	 * Purpose   : get value of the rank
	 * Returns   : rank value
	 */			
	public int getValue() 
	{		
		if (rank.equalsIgnoreCase("J") || rank.equalsIgnoreCase("Q") || rank.equalsIgnoreCase("K")) 
		{
			return 10;
		}
		else if (rank.equalsIgnoreCase("A"))
		{
			return 11;
		}
		else if (isInteger(rank) && Integer.parseInt(rank) <= 10) 
		{
			return Integer.parseInt(rank);
		}
		else 
		{
			return 0;
		}
	}
	
	/*
	 * Function  : setVisibility
	 * Parameter : visible
	 * Purpose   : sets the visibility of the card
	 */		
	public void setVisibility(boolean visible) 
	{
		this.visible = visible;
	}
	
	/*
	 * Function  : isVisible
	 * Purpose   : returns visibility of card
	 * Returns   : true/false
	 */		
	public boolean isVisible() {
		return this.visible;
	}
	
	/*
	 * Function  : isAce
	 * Purpose   : determines if card is an Ace
	 * Returns   : true/false
	 */	
	public boolean isAce() {
		if (rank.equalsIgnoreCase("A")) {
			return true;
		}
		
		return false;
	}
	
	/*
	 * Function  : isInteger
	 * Parameter : a String
	 * Purpose   : determines if a string can be converted to an integer
	 * Returns   : true/false
	 */			
	private boolean isInteger(String aString) 
	{
		boolean valid = false;
		try
		{
			Integer.parseInt(aString);		 
			valid = true;
		}
		catch (NumberFormatException ex)
		{
		}
 
		return valid;      
	}	
}
