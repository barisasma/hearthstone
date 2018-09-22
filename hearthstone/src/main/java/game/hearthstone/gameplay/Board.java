package game.hearthstone.gameplay;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import game.hearthstone.card.Card;
import game.hearthstone.player.Player;
import game.heartstone.logs.Notifier;

public class Board {

	private Player player;
	private Player opponent;
	private boolean ongoing;

	public Board(Player player, Player opponent) {
		this.player = player;
		this.opponent = opponent;
		this.ongoing = true;
	}

	public void playCard(int index) {
		Card card = player.getHand().get(index - 1);
		if (hasEnoughMana(card)) {
			consumeMana(card);
			player.getHand().remove(index - 1);
			damage(card);
			Notifier.notifyPlayingCard(player, opponent, card);
		} else
			System.out.println("Not enough mana");
		setOngoing();
	}

	private boolean hasEnoughMana(Card card) {
		return card.getMana() <= player.getMana();
	}

	private void consumeMana(Card card) {
		player.setMana(player.getMana() - card.getMana());
	}

	private void damage(Card card) {
		opponent.setHealth(opponent.getHealth() - card.getMana());
	}

	public static void shuffle(Player player) {
		for (int i = 0; i < 3; i++) {
			int randomPick = randomInt(player.getDeck());
			Optional<Card> pickFromDeck = pickFromDeck(player, randomPick);
			player.getHand().add(pickFromDeck.get());
		}
	}

	public void turn(int turnNumber) {
		fillMana(turnNumber);
		Optional<Card> cardPicked = randomPickAndBleed();
		if (player.isNotOverload() && cardPicked.isPresent()) {
			player.getHand().add(cardPicked.get());
		} else if (!player.isNotOverload() && cardPicked.isPresent()) {
			Notifier.notifyDiscard(cardPicked.get());
		}
	}

	private Optional<Card> randomPickAndBleed() {
		if (!player.getDeck().isEmpty()) {
			int randomIndex = randomInt(player.getDeck());
			return pickFromDeck(player, randomIndex);
		}
		bleed();
		return Optional.empty();
	}

	private static Optional<Card> pickFromDeck(Player player, int index) {
		Card card = player.getDeck().get(index - 1);
		player.getDeck().remove(index - 1);
		return Optional.of(card);
	}

	private void bleed() {
		player.setHealth(player.getHealth() - 1);
		setOngoing();
		Notifier.notifyBleed(player);
	}

	private void setOngoing() {
		if (player.getHealth() <= 0 || opponent.getHealth() <= 0) {
			ongoing = false;
		}
	}

	private static int randomInt(List<Card> deck) {
		Random random = new Random();
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
		player.setMana(turnNumber);
	}

	public void switchPlayers() {
		Player tempPlayer = player;
		player = opponent;
		opponent = tempPlayer;
	}

	public Player getPlayer() {
		return player;
	}

	public Player getOpponent() {
		return opponent;
	}

	public boolean isOngoing() {
		return ongoing;
	}

}
