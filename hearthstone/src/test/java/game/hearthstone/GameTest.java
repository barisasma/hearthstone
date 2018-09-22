package game.hearthstone;

import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import game.hearthstone.card.Card;
import game.hearthstone.gameplay.Board;
import game.hearthstone.gameplay.Decider;
import game.hearthstone.initializers.DeckCreater;
import game.hearthstone.player.Player;

public class GameTest {

	private Random random;

	@Before
	public void init() {
		random = new Random();
	}

	@Test
	public void randomPlaytests() {
		randomPlay();
	}
	
	@Test
	public void bleedAndDiscardTest() {
		System.out.println("**");
		System.out.println("**");
		System.out.println("**");
		System.out.println("*************BLEED AND DISCARD TESTS************************");
		Player firstPlayer = new Player("BARIS", DeckCreater.getDeck());
		Player secondPlayer = new Player("TRENDYOL", DeckCreater.getDeck());
		Board board = new Board(firstPlayer, secondPlayer);
		int turns = 1;
		while (board.isOngoing()) {
			if (board.isOngoing()) {
				board.turn(turns);
				board.switchPlayers();
			}
			if (board.isOngoing()) {
				board.turn(turns);
				board.switchPlayers();
			}
			turns++;
		}
		Player winner =  Decider.checkHealthsAndFindWinner(board);
		declareWinner(winner,turns);
	}

	private void randomPlay() {
		Player firstPlayer = new Player("BARIS", DeckCreater.getDeck());
		Player secondPlayer = new Player("TRENDYOL", DeckCreater.getDeck());
		String sampleToString = "Card [name=Ancestral Healing, mana=0]";
		Assert.assertEquals("BARIS", firstPlayer.getNick());
		Assert.assertEquals(sampleToString, DeckCreater.getDeck().get(0).toString());
		Assert.assertEquals(17, firstPlayer.getDeck().size());
		Assert.assertEquals(17, secondPlayer.getDeck().size());
		int turns = 1;
		Board board = new Board(firstPlayer, secondPlayer);
		while (board.isOngoing()) {
			isGoindAndPlay(turns, board);
			isGoindAndPlay(turns, board);
			turns++;
		}
		Player winner = Decider.checkHealthsAndFindWinner(board);
		declareWinner(winner, turns);
	}

	private void isGoindAndPlay(int turns, Board board) {
		if (board.isOngoing()) {
			play(board, turns);
			board.switchPlayers();
		}
	}

	private void play(Board board, int turnNumber) {
		System.out.println("------------- Turn: " + turnNumber + "---------------------------");
		board.turn(turnNumber);
		int randomCardIndex = 1;
		if (board.getPlayer().getHand().size() > 1) {
			randomCardIndex = random.nextInt(board.getPlayer().getHand().size());
			if (randomCardIndex == 0) {
				randomCardIndex = 1;
			}
			attack(board, randomCardIndex);
		}
		System.out.println("Remaining Mana: " + board.getPlayer().getMana());
		System.out.println("Defender: " + board.getOpponent().getNick() + " Health After Attack: " + board.getOpponent().getHealth());
		System.out.println("------------- Turn: " + turnNumber + "---------------------------");
		System.out.println("*****************************************************************");
	}

	private static void attack(Board board, int randomCardIndex) {
		Card playedCard = board.getPlayer().getHand().get(randomCardIndex - 1);
		System.out.println("Defender: " + board.getOpponent().getNick() + " Health Before Attack: " + board.getOpponent().getHealth());
		System.out.println("Attacker Hand Before Attack: ");
		board.getPlayer().printHand();
		System.out.println("Attacker: " + board.getPlayer().getNick() + " Health: " + board.getPlayer().getHealth()
				+ " Attacker Mana: " + board.getPlayer().getMana() + " Last Played Card: " + playedCard);
		board.playCard(randomCardIndex);
		System.out.println("Attacker Hand After Attack: ");
		board.getPlayer().printHand();
	}

	private static void declareWinner(Player player, int turnNumber) {
		System.out.println("Turn: " + turnNumber + " ");
		Decider.winPrint(player);
		Assert.assertEquals(true, player.getHealth() >= 0);
	}


}
