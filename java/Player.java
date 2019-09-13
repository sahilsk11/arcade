import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Defines a single player in a card game
 */
public class Player {
    private Queue<Card> cards; //the players deck of cards

    /**
     * Constructs a new player
     * @param cards a List of cards
     */
    public Player(List<Card> cards) {
        this.cards = new LinkedList<Card>(); //Create a new Queue (FILO) based on a LinkedList implementation
        addCards(cards);
    }

    /**
     * "Pops" the first card in the queue and returns it
     * @return the first card in the queue
     */
    public Card getNextCard() {
        return cards.poll();
    }

    /**
     * Adds a list of cards to the user's deck. The first element in the array will be added first.
     * This will also REMOVE elements from cardsToAdd as they are added, affecting the original list.
     * 
     * e.g. (left is beginning of queue)
     * player's deck before = {A, 2, 3}
     * cardsToAdd before = {K, J, 7}
     * 
     * user's deck after = {A, 2, 3, K, J, 7}
     * cardsToAdd after = {}
     * 
     * @param cardsToAdd the list of cards to add to the player's deck
     */
    public void addCards(List<Card> cardsToAdd) {
        for (int i = cardsToAdd.size() - 1; i >= 0; i--) {
            Card c = cardsToAdd.remove(i);
            cards.add(c);
        }
    }

    /**
     * Gets the number of cards of the player
     * @return the number of cards of the player
     */
    public int getNumCards() {
        return cards.size();
    }
}