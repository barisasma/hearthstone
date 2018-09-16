package game.hearthstone.card;

public class Card {
	
	private String name;
	private int mana;
	
	public Card(String name,int mana) {
		this.name = name;
		this.mana = mana;
	}
	
	public String getName() {
		return name;
	}
	
	public int getMana() {
		return mana;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Card [name=").append(name).append(", mana=").append(mana).append("]");
		return builder.toString();
	}
	

}
