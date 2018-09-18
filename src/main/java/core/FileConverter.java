package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileConverter {

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
		
		if (deck.findCard(aCard) >= 0) {
			return aCard;
		}
		
		return null;
	}
	
	public String[] splitLine(String fileName) throws IOException {
		BufferedReader br;
		br = new BufferedReader(new FileReader(fileName));
		String line;
		line = br.readLine();
		br.close();
		
		String[] moves = line.split(" ");
		return moves;				
	}	
}
