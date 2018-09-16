package game.hearthstone.params;

import java.io.BufferedReader;

import game.hearthstone.player.Player;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GameParams {
	
	private BufferedReader bufferedReader;
	private Player player;
	private Player opponent;
	private int turn;
	
//	public GameParams(BufferedReader bufferedReader,Player player,Player opponent,int turn) {
//		this.bufferedReader = bufferedReader;
//		this.player = player;
//		this.opponent = opponent;
//		this.turn = turn;
//	}
//	
//	public BufferedReader getBufferedReader() {
//		return bufferedReader;
//	}
//	public void setBufferedReader(BufferedReader bufferedReader) {
//		this.bufferedReader = bufferedReader;
//	}
//	public Player getPlayer() {
//		return player;
//	}
//	public Player getOpponent() {
//		return opponent;
//	}
//	public int getTurn() {
//		return turn;
//	}
//	
//	
//	static class Builder{
//		private BufferedReader bufferedReader;
//		private Player player;
//		private Player opponent;
//		private int turn;
//	}
	
}
