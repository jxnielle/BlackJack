package core;

public class FileConvert {

	/*
	 * Function  : toCard
	 * Parameter : a string (e.g. "C3", "S10")
	 * Purpose   : converts a string representative of a card to a card object
	 * Returns   : a Card object
	 */
	public Card toCard(String aString) {
		char suit = aString.charAt(0);
		String rank = aString.substring(1).trim();
		Card aCard = new Card(suit, rank);
		Deck deck = new Deck();	 	
		
		if (deck.findCard(aCard)) {
			return aCard;
		}
		
		return null;
	}
}
