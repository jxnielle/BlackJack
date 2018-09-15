package core;

public class Card 
{
	private char suit;
	private String rank;
	
	/*
	 * Function  : Card
	 * Parameter : the suit and rank of the card 
	 * Purpose   : a constructor 
	 */			
	public Card(char suit, String rank) 
	{
		this.suit = suit;
		this.rank = rank;
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
	 * Function  : getValue
	 * Purpose   : get value of the rank
	 * Returns   : rank value
	 */			
	public int getValue() 
	{
		String rank = this.getRank();
		
		if (rank == "J" || rank == "Q" || rank == "K") 
		{
			return 10;
		}
		else if (rank == "A") 
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
