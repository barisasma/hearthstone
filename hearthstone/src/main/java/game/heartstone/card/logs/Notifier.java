package game.heartstone.card.logs;

import game.hearthstone.card.Card;
import game.hearthstone.player.Player;

public class Notifier {

	public static void notifyDiscard(Card card) {
		System.out.println("Your hand is full, card is discarded :" + card);
	}
	
	public static void notifyBleed(Player player) {
		System.out.println("Your deck is empty, bleed out reamining health: " + player.getHealth());
	}
	
	public static void notifyPlayingCard(Player player, Player opponent, Card card) {
		System.out.println("Damage done: " + card.getMana());
		System.out.println("Opponent remaining health: " + opponent.getHealth());
		System.out.println("Your Mana: " + player.getMana());
	}
	
}
