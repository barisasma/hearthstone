package game.hearthstone.gameplay;

import game.hearthstone.player.Player;

public final class Decider {

	private Decider() {

	}

	public static Player checkHealthsAndFindWinner(Board board) {
		if (board.getPlayer().getHealth() <= 0 && board.getOpponent().getHealth() >= 0)
			return board.getOpponent();
		return board.getPlayer();
	}
	
	public static String winPrint(Player player) {
		return player.getNick() + " Win! with HP: " + player.getHealth();
	}

}
