package game.hearthstone.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.hearthstone.card.Card;
import game.hearthstone.gameplay.Board;

public final class Player {

	private String nick;
	private int health;
	private int mana;
	private List<Card> deck;
	private List<Card> hand;
	Random random = new Random();

	public Player(String nick, List<Card> deck) {
		this.nick = nick;
		health = 30;
		mana = 0;
		random = new Random();
		this.deck = new ArrayList<>(deck);
		hand = new ArrayList<>();
		Board.shuffle(this);
	}
	
	public void setMana(int mana) {
		this.mana = mana;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isNotOverload() {
		return hand.size() < 5;
	}

	public int getHealth() {
		return health;
	}

	public int getMana() {
		return mana;
	}

	public String getNick() {
		return nick;
	}

	public List<Card> getHand() {
		return hand;
	}

	public List<Card> getDeck() {
		return deck;
	}

	public String printHand() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < hand.size(); i++) {
			builder.append(i + 1).append(". Name: ").append(hand.get(i).getName()).append(" Mana: ")
					.append(hand.get(i).getMana()).append("\n");
		}
		return builder.toString();
	}

	

}
