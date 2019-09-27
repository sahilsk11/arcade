import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Outlines and implements general principles for what a card game should
 * include.
 * 
 */
public abstract class CardGame {
  /**
   * Creates a deck of cards. This deck does not have any suits.
   * 
   * @return an ArrayList of 52 cards (4 identical sets) in order (A, 2, 3 .... J,
   *         Q, K)
   */
  public ArrayList<Card> constructCardList(boolean includeJokers, int sets) {
    ArrayList<Card> cards = new ArrayList<Card>();
    int cardIndex = 0;
    String cardName;
    int size = sets * 13;
    for (int i = 0; i < size; i++) {
      switch (cardIndex) {
      case 0:
        cardName = "ace";
        break;
      case 10:
        cardName = "jack";
        break;
      case 11:
        cardName = "queen";
        break;
      case 12:
        cardName = "king";
        cardIndex = -1; // setting the index to -1 ensures it will be 0 when next round begins
        break;
      default:
        cardName = new Integer(cardIndex + 1).toString(); // e.g. cardIndex = 2 means it's card 3 becaus of 0-index
      }
      Card c = new Card(cardName);
      cards.add(c);
      cardIndex++;
    }
    if (includeJokers) {
      for (int i = 0; i < size / 13; i++) {
        cards.add(new Card("joker"));
      }
    }
    return cards;
  }

  public ArrayList<Card> constructCardList() {
    return constructCardList(false, 4);
  }
}