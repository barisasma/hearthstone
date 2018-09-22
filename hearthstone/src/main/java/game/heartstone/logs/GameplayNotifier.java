package game.heartstone.logs;

import game.hearthstone.gameplay.Board;
import game.hearthstone.player.Player;

public class GameplayNotifier extends Notifier{
	
	public static void playerTurnLog(Board board) {
		System.out.println("");
		System.out.println(board.getPlayer().getNick() + " turn: " + " Health: " + board.getPlayer().getHealth() + 
				" Mana: " + board.getPlayer().getMana()+
				" Opponent HP: " + board.getOpponent().getHealth());
	}
	
	public static void notifyNickName(int playerNum) {
		System.out.println("Enter nick for " + playerNum + ". player: ");
	}
	
	public static void notifyInvalidKey() {
		System.out.println("Not valid key");
	}
	
	public static void notifyReRoll() {
		System.out.println("Press R for roll and start the game");
	}
	
	public static void giveInstructions() {
		System.out.println("Simple instruction about game: ");
		System.out.println("R for rolling decide which one starts the game");
		System.out.println("Use 1-5 numbers to play your cards");
		System.out.println("E for ending your turn");
	}
	
	public static void notifyNoCard() {
		System.out.println("There is no such a card! ");
	}
	
	public static void notifyPlayerInit(Player player) {
		System.out.println("Player initialized: " + player.getNick());
	}
	
	public static void notifyPlayYourCard() {
		System.out.println("Play your Card: ");
	}
	
	public static void notifyPlayerTurn(Board board) {
		System.out.println("");
		System.out.println(board.getPlayer().getNick() + " turn: ");
	}

}
