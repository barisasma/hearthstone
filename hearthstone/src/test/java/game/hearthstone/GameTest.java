package game.hearthstone;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import game.hearthstone.card.Card;
import game.hearthstone.player.Player;

public class GameTest {

	private List<Card> cards = new ArrayList<>();
	private Card sampleCard = new Card("Ancestral Healing", 0);
	Random random;

	@Before
	public void initCards() {
		Card card1 = new Card("Animal Companion", 3);
		Card card2 = new Card("Acidic Swamp Ooze", 2);
		Card card3 = new Card("Dalaran Mage", 1);
		Card card4 = new Card("Guardian of Kings", 4);
		Card card5 = new Card("Gurubashi Berserker", 5);
		Card card6 = new Card("Hammer of Wrath", 6);
		Card card7 = new Card("Hand of Protection", 7);
		Card card8 = new Card("Heroic Strike", 8);
		cards.add(sampleCard);
		cards.add(sampleCard);
		cards.add(card1);
		cards.add(card1);
		cards.add(card2);
		cards.add(card2);
		cards.add(card2);
		cards.add(card3);
		cards.add(card3);
		cards.add(card3);
		cards.add(card3);
		cards.add(card3);
		cards.add(card4);
		cards.add(card4);
		cards.add(card4);
		cards.add(card5);
		cards.add(card5);
		cards.add(card6);
		cards.add(card7);
		cards.add(card8);
		random = new Random();
	}

	@Test
	public void playertests() {
		randomPlay();
	}

	private void randomPlay() {

		Player firstPlayer = new Player("BARIS", cards);
		Player secondPlayer = new Player("TRENDYOL", cards);
		String sampleToString = "Card [name="+sampleCard.getName()+", mana=" + sampleCard.getMana()+"]";

		Assert.assertEquals("BARIS", firstPlayer.getNick());
		Assert.assertEquals(sampleCard.getName(), firstPlayer.getDeck().get(0).getName());
		Assert.assertEquals(sampleToString, sampleCard.toString());
		
		int turns = 100;

		for (int turnNumber = 1; turnNumber < turns; turnNumber++) {
			firstPlayer.turn(turnNumber);
			play(firstPlayer, secondPlayer,turnNumber);
			if(checkHealthsAndisFinished(firstPlayer, secondPlayer, turnNumber)) {
				break;
			}
			secondPlayer.turn(turnNumber);
			play(secondPlayer, firstPlayer,turnNumber);
			if(checkHealthsAndisFinished(firstPlayer, secondPlayer, turnNumber)) {
				break;
			}
		}

	}
	
	private void play(Player attackPlayer, Player defensePlayer,int turnNumber) {
		System.out.println("------------- Turn: "+ turnNumber + "---------------------------");
		int randomCardIndex = 1;
		Card playedCard = null;
		if (attackPlayer.getHand().size() > 1) {
			randomCardIndex = random.nextInt(attackPlayer.getHand().size());
			if (randomCardIndex == 0) {
				randomCardIndex = 1;
			}
			playedCard = attackPlayer.getHand().get(randomCardIndex-1);
			System.out.println("Defender: "+ defensePlayer.getNick() +" Health Before Attack: "+defensePlayer.getHealth());
			System.out.println("Attacker Hand Before Attack: ");
			attackPlayer.getHand().forEach(System.out::println);
			System.out.println("");
			System.out.println("Attacker: " + attackPlayer.getNick() + " Health: " + attackPlayer.getHealth()
			+ " Attacker Mana: " + attackPlayer.getMana() + " Last Played Card: " + playedCard);
			attackPlayer.playCard(defensePlayer, randomCardIndex);
			System.out.println("Attacker Hand After Attack: ");
			attackPlayer.getHand().forEach(System.out::println);
		}
		System.out.println("Remaining Mana: " +attackPlayer.getMana());
		System.out.println("Defender: "+ defensePlayer.getNick() +" Health After Attack: "+defensePlayer.getHealth());
		System.out.println("------------- Turn: "+ turnNumber + "---------------------------");
		System.out.println("*****************************************************************");
	}
	
	private static boolean checkHealthsAndisFinished(Player firstPlayer, Player secondPlayer,int turnNumber){
		if (secondPlayer.getHealth() <= 0) {
			Assert.assertEquals(firstPlayer.getNick() + " Win!", winPrint(firstPlayer));
			System.out.println("Turn: " + turnNumber + winPrint(firstPlayer));
			return true;
		} else if (firstPlayer.getHealth() <= 0) {
			Assert.assertEquals(secondPlayer.getNick() + " Win!", winPrint(secondPlayer));
			System.out.println("Turn: " + turnNumber + winPrint(secondPlayer));
			return true;
		}
		return false;
	}

	private static String winPrint(Player player) {
		return player.getNick() + " Win!";
	}

}
