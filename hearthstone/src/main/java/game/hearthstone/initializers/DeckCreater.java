package game.hearthstone.initializers;

import java.util.ArrayList;
import java.util.List;

import game.hearthstone.card.Card;

public final class DeckCreater {
	
	private static List<Card> deck = new ArrayList<>();
	
	static {
		Card card0 = new Card("Ancestral Healing", 0);
		Card card1 = new Card("Animal Companion", 3);
		Card card2 = new Card("Acidic Swamp Ooze", 2);
		Card card3 = new Card("Dalaran Mage", 1);
		Card card4 = new Card("Guardian of Kings", 4);
		Card card5 = new Card("Gurubashi Berserker", 5);
		Card card6 = new Card("Hammer of Wrath", 6);
		Card card7 = new Card("Hand of Protection", 7);
		Card card8 = new Card("Heroic Strike", 8);
		deck.add(card0);
		deck.add(card0);
		deck.add(card1);
		deck.add(card1);
		deck.add(card2);
		deck.add(card2);
		deck.add(card2);
		deck.add(card3);
		deck.add(card3);
		deck.add(card3);
		deck.add(card3);
		deck.add(card3);
		deck.add(card4);
		deck.add(card4);
		deck.add(card4);
		deck.add(card5);
		deck.add(card5);
		deck.add(card6);
		deck.add(card7);
		deck.add(card8);
	}
	
	public static List<Card> getDeck() {
		return deck;
	}
	
}
