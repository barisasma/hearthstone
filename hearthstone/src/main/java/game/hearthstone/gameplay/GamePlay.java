package game.hearthstone.gameplay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import game.hearthstone.initializers.DeckCreater;
import game.hearthstone.player.Player;
import game.heartstone.logs.GameplayNotifier;

public final class GamePlay {

	private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

//	public static void main(String[] args) throws IOException {
//		try {
//			GameplayNotifier.giveInstructions();
//			Player player1 = chooseNamesAndCreate(1);
//			Player player2 = chooseNamesAndCreate(2);
//			initAndStartGame(player1, player2);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			bufferedReader.close();
//		}
//	}

	public static Player chooseNamesAndCreate(int playerNum) throws IOException {
		GameplayNotifier.notifyNickName(playerNum);
		return createPlayer(bufferedReader.readLine());
	}

	private static Player createPlayer(String nick) {
		Player player = new Player(nick, DeckCreater.getDeck());
		GameplayNotifier.notifyPlayerInit(player);
		return player;
	}

	public static void initAndStartGame(Player player, Player opponent) throws IOException {
		int roll = initBeginningPlayer();
		if (roll != 0) {
			Player tempplayer = player;
			player = opponent;
			opponent = tempplayer;
		}
		startTheGame(player, opponent);
	}

	public static void startTheGame(Player player, Player opponent) throws IOException {
		int turn = 1;
		Board board = new Board(player, opponent);
		while (board.isOngoing()) {
			isGoingAndPlay(turn, board);
			isGoingAndPlay(turn, board);
			turn++;
		}
		Player winner = Decider.checkHealthsAndFindWinner(board);
		Decider.winPrint(winner);
	}

	private static void isGoingAndPlay(int turn, Board board) throws IOException {
		if (board.isOngoing()) {
			playAndSwitch(board, turn);
		}
	}

	private static void playAndSwitch(Board board, int turn) throws IOException {
		playGame(board, turn);
		board.switchPlayers();
	}

	private static int initBeginningPlayer() throws IOException {
		String key;
		Random random = new Random();
		GameplayNotifier.notifyReRoll();
		while (!(key = bufferedReader.readLine()).equalsIgnoreCase("R")) {
			GameplayNotifier.notifyInvalidKey();
			GameplayNotifier.notifyReRoll();
		}
		return random.nextInt(2);
	}

	private static void playGame(Board board, int turn) throws IOException {
		board.turn(turn);
		GameplayNotifier.playerTurnLog(board);
		GameplayNotifier.notifyPlayYourCard();
		board.getPlayer().printHand();
		String key;
		while (!(key = bufferedReader.readLine()).equalsIgnoreCase("E")) {
			playerTurn(board, key);
			GameplayNotifier.notifyPlayerTurn(board);
		}
	}

	private static void playerTurn(Board board, String key) {
		try {
			Integer index = Integer.valueOf(key);
			board.playCard(index);
			board.getPlayer().printHand();
		} catch (Exception e) {
			e.printStackTrace();
			GameplayNotifier.notifyNoCard();
		}
	}

	

}
