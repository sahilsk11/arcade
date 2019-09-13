import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Defines a playing Card
 */
public class Card {
    private int value; // the "power" of a card (e.g. A -> 13, 2 = 2)
    private String cardName;
    private String cardString; // the visual String of the card (with the *'s and emojis)

    /**
     * Creates a new card
     * 
     * @param value the "power" of the card
     * @param name  the name of the card (e.g. 10, 9, queen, ace)
     */
    public Card(int value, String name) {
        cardName = name;
        this.value = value;
        this.cardString = buildCardString();
    }

    /**
     * Used to compare values of two cards
     * 
     * @param c the second card to compare to
     * @return 1 if the first card (this) has greater value, -1 if the second card
     *         is greater, or 0 if they are the same
     */
    public int compareTo(Card c) {
        if (this.value > c.getValue()) {
            return 1;
        }
        if (this.value == c.getValue()) {
            return 0;
        }
        return -1;
    }

    /**
     * Gets the value of the card
     * 
     * @return the value of the card
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets the name of the card
     * 
     * @return the name of the card
     */
    public String getName() {
        return cardName;
    }

    /**
     * Finds the corresponding file to the card and loads the "image." Typically
     * invoked during construction.
     * 
     * @return a string representation of the card visual
     * 
     * @throws IllegalArgumentException if the name of the card is invalid (not
     *                                  matching filename), or if the card could not
     *                                  be constructed
     */
    public String buildCardString() {
        try {
            String filename = "cards/" + cardName + ".txt";
            File f = new File(filename);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String out = "";
            String st;

            while ((st = br.readLine()) != null) {
                out += st + "\n";
            }
            return out;
        } catch (FileNotFoundException f) {
            throw new IllegalArgumentException("Input card is not valid");
        } catch (IOException e) {
            throw new IllegalArgumentException("Error constructing card");
        }
    }

    /**
     * Returns the visual representation of the card
     */
    public String toString() {
        return cardString;
    }
}