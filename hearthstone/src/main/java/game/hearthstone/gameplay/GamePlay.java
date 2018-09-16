package game.hearthstone.gameplay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import game.hearthstone.initializers.DeckCreater;
import game.hearthstone.player.Player;

public final class GamePlay {

	private static BufferedReader bufferedReader;

	public static void main(String[] args) throws IOException {
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			giveInstructions();
			Player player1 = chooseNamesAndCreate(1);
			
			Player player2 = chooseNamesAndCreate(2);
			initAndStartGame(player1, player2);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bufferedReader.close();
		}
	}

	private static Player chooseNamesAndCreate(int playerNum) throws IOException {
		System.out.println("Enter nick for " + playerNum + ". player: ");
		return createPlayer(bufferedReader.readLine());
	}

	private static Player createPlayer(String nick) {
		Player player = new Player(nick, DeckCreater.getDeck());
		System.out.println("Player initialized: " + player.getNick());
		return player;
	}

	private static void initAndStartGame(Player player, Player opponent) throws IOException {
		int roll = initBeginningPlayer();
		if (roll != 0) {
			Player tempplayer = player;
			player = opponent;
			opponent = tempplayer;
		}
		startTheGame(player, opponent);
	}

	private static void startTheGame(Player player, Player opponent) throws IOException {
		int turn = 1;
		Board board = new Board(player, opponent);
		while (board.isOngoing()) {
			if (board.isOngoing()) {
				playAndSwitch(board, turn);
			}
			if (board.isOngoing()) {
				playAndSwitch(board, turn);
			}
			turn++;
		}
		Player winner = Decider.checkHealthsAndFindWinner(board);
		System.out.println(Decider.winPrint(winner));
	}

	private static void playAndSwitch(Board board, int turn) throws IOException {
		playGame(board, turn);
		board.switchPlayers();
	}

	private static int initBeginningPlayer() throws IOException {
		String key;
		Random random = new Random();
		printReRollLog();
		while (!(key = bufferedReader.readLine()).equalsIgnoreCase("R")) {
			System.out.println("Not valid key");
			printReRollLog();
		}
		return random.nextInt(2);
	}
	
	private static void printReRollLog() {
		System.out.println("Press R for roll and start the game");
	}

	private static void playGame(Board board, int turn) throws IOException {
		board.turn(turn);
		playerTurnLog(board);
		System.out.println("Play your Card: ");
		System.out.println(board.getPlayer().printHand());
		String key;
		while (!(key = bufferedReader.readLine()).equalsIgnoreCase("E")) {
			playerTurn(board, key);
			System.out.println("");
			System.out.println(board.getPlayer().getNick() + " turn: ");
		}
	}
	
	private static void playerTurnLog(Board board) {
		System.out.println("");
		System.out.println(board.getPlayer().getNick() + " turn: " + " Health: " + board.getPlayer().getHealth() + 
				" Mana: " + board.getPlayer().getMana()+
				" Opponent HP: " + board.getOpponent().getHealth());
	}

	private static void playerTurn(Board board, String key) {
		try {
			Integer valueOf = Integer.valueOf(key);
			board.playCard(valueOf);
			System.out.println(board.getPlayer().printHand());
		} catch (Exception e) {
			System.out.println("There is no such a card! ");
		}
	}

	private static void giveInstructions() {
		System.out.println("Simple instruction about game: ");
		System.out.println("R for rolling decide which one starts the game");
		System.out.println("Use 1-5 numbers to play your cards");
		System.out.println("E for ending your turn");
	}

}
