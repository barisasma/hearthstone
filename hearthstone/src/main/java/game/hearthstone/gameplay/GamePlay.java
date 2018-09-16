package game.hearthstone.gameplay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import game.hearthstone.initializers.DeckCreater;
import game.hearthstone.player.Player;

public class GamePlay {

	private static BufferedReader bufferedReader;
	
	public static void main(String[] args) throws IOException {
		System.out.println("Enter nick for first player: ");
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			String nick = bufferedReader.readLine();
			Player player1 = new Player(nick, DeckCreater.getDeck());
			System.out.println("First player initialized: " + player1.getNick());
			System.out.println("Enter nick for second player: ");
			nick = bufferedReader.readLine();
			Player player2 = new Player(nick, DeckCreater.getDeck());
			System.out.println("Second player initialized: " + player2.getNick());
			initGameAndStartGame(player1,player2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			bufferedReader.close();
		}
	}

	private static void initGameAndStartGame(Player player, Player opponent) throws IOException {
		Player myPlayer = player;
		Player myOpponent = opponent;
		giveInstructions();
		int roll = initFirstPlayer();
		if (roll != 0) {
			Player tempplayer = myPlayer;
			myPlayer = myOpponent;
			myOpponent = tempplayer;
		}
		startTheGame(player,opponent);
	}

	private static void startTheGame(Player player,Player opponent) throws IOException {
		int turn = 1;
		Player myPlayer = player;
		Player myOpponent = opponent;
		while (player.getHealth() > 0 && opponent.getHealth() > 0) {
			playGame(myPlayer, myOpponent, turn);
			playGame(myOpponent, myPlayer, turn);
			turn++;
		}
	}

	private static int initFirstPlayer() throws IOException {
		String key;
		Random random = new Random();

		System.out.println("Press R for roll and start the game");
		while (!(key = bufferedReader.readLine()).equalsIgnoreCase("R")) {
			System.out.println("Not valid key");
			System.out.println("Press R for roll and start the game");
		}
		return random.nextInt(1);
	}

	private static void playGame(Player player, Player opponent, int turn) throws IOException {
		player.turn(turn);
		System.out.println(
				player.getNick() + " turn: " + " Health: " + player.getHealth() + " Mana: " + player.getMana());
		System.out.println(" Play your Card: ");
		System.out.println(player.printHand());
		String key;
		while (!(key = bufferedReader.readLine()).equalsIgnoreCase("E")) {
			playerTurn(player, opponent, key);
			System.out.println(player.getNick() + " turn: ");
		}
	}

	private static void playerTurn(Player player, Player opponent, String key) {
		try {
			Integer valueOf = Integer.valueOf(key);
			player.playCard(opponent, valueOf);
			System.out.println(player.printHand());
		} catch (Exception e) {
			System.out.println("There is no such a card! ");
		}
	}

	private static void giveInstructions() {
		System.out.println("Simple instruction about game: ");
	}

}
