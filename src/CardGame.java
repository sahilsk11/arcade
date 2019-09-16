import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Outlines and implements general principles for what a card game should include.
 */
public abstract class CardGame {
    /**
     * Creates a deck of cards. This deck does not have any suits.
     * 
     * @return an ArrayList of 52 cards (4 identical sets) in order (A, 2, 3 .... J, Q, K)
     */
    public ArrayList<Card> constructCardList(boolean includeJokers, int size) {
        ArrayList<Card> cards = new ArrayList<Card>();
        int cardIndex = 0;
        String cardName;
        int cardPower;
        for (int i = 0; i < size; i++) {
            switch (cardIndex) {
                case 0:
                    cardName = "ace";
                    cardPower = 14;
                    break;
                case 10:
                    cardName = "jack";
                    cardPower = 11;
                    break;
                case 11:
                    cardName = "queen";
                    cardPower = 12;
                    break;
                case 12:
                    cardName = "king";
                    cardPower = 13;
                    cardIndex = -1; //setting the index to -1 ensures it will be 0 when next round begins
                    break;
                default:
                    cardName = new Integer(cardIndex + 1).toString();
                    cardPower = cardIndex + 1;
            }
            Card c = new Card(cardPower, cardName);
            cards.add(c);
            cardIndex++;
        }
        if (includeJokers) {
            for (int i = 0; i < size / 13; i++) {
                cards.add(new Card(-1, "joker"));
            }
        }
        return cards;
    }

    public ArrayList<Card> constructCardList() {
        return constructCardList(false, 52);
    }


    public ArrayList<Card> constructCardList(int size) {
        return constructCardList(false, size);
    }
}