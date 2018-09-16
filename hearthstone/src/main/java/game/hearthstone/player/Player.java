package game.hearthstone.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import game.hearthstone.card.Card;

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
		shuffle();
	}


	public void playCard(Player opponent, int index) {
		Card card = hand.get(index - 1);
		if (hasEnoughMana(card)) {
			consumeMana(card);
			hand.remove(index - 1);
			opponent.takeDamage(card);
			System.out.println("Damage done: " + card.getMana());
			System.out.println("Opponent remaining health: " + opponent.getHealth());
			System.out.println("Your Mana: " + mana);
		}
		else
			System.out.println("Not enough mana");
	}

	private boolean hasEnoughMana(Card card) {
		return card.getMana() <= mana;
	}

	private void consumeMana(Card card) {
		mana -= card.getMana();
	}

	private void takeDamage(Card card) {
		health -= card.getMana();
	}

	private void shuffle() {
		for (int i = 0; i < 3; i++) {
			int randomPick = randomInt();
			Optional<Card> pickFromDeck = pickFromDeck(randomPick);
			hand.add(pickFromDeck.get());
		}
	}

	public void turn(int turnNumber) {
		fillMana(turnNumber);
		Optional<Card> cardPicked = randomPickAndBleed();
		if (isNotOverload() && cardPicked.isPresent()) {
			hand.add(cardPicked.get());
		} else if (!isNotOverload() && cardPicked.isPresent()) {
			System.out.println("Your hand is full, card is discarded :" +  cardPicked.get());
		}
	}

	private Optional<Card> randomPickAndBleed() {
		if (!deck.isEmpty()) {
			int randomIndex = randomInt();
			return pickFromDeck(randomIndex);
		}
		bleed();
		return Optional.empty();
	}

	private Optional<Card> pickFromDeck(int index) {
		Card card = deck.get(index - 1);
		deck.remove(index - 1);
		Optional<Card> optionalCard = Optional.of(card);
		return optionalCard;

	}

	private void bleed() {
		health--;
		System.out.println("Your deck is empty, bleed out reamining health: " + health);
	}

	private int randomInt() {
		int randomIndex = random.nextInt(deck.size());
		if (randomIndex == 0)
			return 1;
		return randomIndex;
	}

	private void fillMana(int turnNumber) {
		if (turnNumber < 10)
			incrementMana(turnNumber);
		else {
			incrementMana(10);
		}
	}

	private void incrementMana(int turnNumber) {
		mana = turnNumber;
	}

	private boolean isNotOverload() {
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
