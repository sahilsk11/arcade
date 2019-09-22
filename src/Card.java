import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Defines a playing Card
 */
public class Card {
    private String cardName;
    private String cardString; // the visual String of the card (with the *'s and emojis)

    /**
     * Creates a new card
     * 
     * @param name  the name of the card (e.g. 10, 9, queen, ace)
     */
    public Card(String name) {
        cardName = name;
        this.cardString = buildCardString();
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
            br.close();
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