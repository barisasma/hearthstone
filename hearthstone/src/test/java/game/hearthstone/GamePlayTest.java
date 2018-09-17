package game.hearthstone;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import game.hearthstone.gameplay.Board;
import game.hearthstone.gameplay.Decider;
import game.hearthstone.gameplay.GamePlay;
import game.hearthstone.initializers.DeckCreater;
import game.hearthstone.player.Player;

public class GamePlayTest {

	@Test
	public void test() throws IOException {
		Player player = GamePlay.chooseNamesAndCreate(1);
		Player opponent = GamePlay.chooseNamesAndCreate(2);
		Board board = new Board(player, opponent);
		Assert.assertEquals(new Player("BARIS",DeckCreater.getDeck()), player);
		Assert.assertEquals(new Player("TRENDYOL",DeckCreater.getDeck()), opponent);
		GamePlay.initAndStartGame(player, opponent);
		Player winner = Decider.checkHealthsAndFindWinner(board);
		Assert.assertEquals(true, winner.getHealth() > 0);
	}

}
