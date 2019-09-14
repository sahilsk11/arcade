import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class MagicJacques extends CardGame {
    Scanner s;
    ArrayList<Card> cards;

    public MagicJacques() {
        s = new Scanner(System.in);
        cards = constructCardList();
    }

    public void printCommands() {
        System.out.println("Commands:\n\toptions - prints out all available moves\n\thint - the best move\n\tquit - end the game\t");
    }

    public String readInput() {
        System.out.print("> ");
        String in = s.nextLine();
        if (in.equals("quit")) {
            System.exit(0);
        }
        return in;
    }

    /**
     * Prints the next commands
     * 
     */
    public void out(String command, String hint, String[] options) {
        System.out.println("\n" + command + "\n");
        if (command.equals("quit")) {
            System.out.println();
            System.exit(0);
        }
    }

    public void asciiPrint(String image) {
        try {
            String filename = "images/" + image + ".txt";
            File f = new File(filename);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String out = "";
            String st;

            while ((st = br.readLine()) != null) {
                out += st + "\n";
            }
            System.out.println(out);
        } catch (FileNotFoundException f) {
            throw new IllegalArgumentException("Input image is not valid");
        } catch (IOException e) {
            throw new IllegalArgumentException("Error constructing image");
        }
    }

    public void playStageOne() {
        out("You wake up. It's pitch black.");
        readInput();
        out("The lights turn on with a click. As you wait for your eyes to adjust, you feel your pockets. There's two rectangular objects in each pocket...");
        readInput();
        out("Your eyes adjust. You're in a small cell-like room with a single door, wearing a black shirt and jeans and no shoes. You reach for the objects in your pocket.");
        readInput();
        out("You find a wooden name tag in your left pocket. It says \"Magic Jacques\". The name sounds familiar. You put it on.");
        readInput();
        out("In your right pocket, you pull out a small box. It's full of playing cards. You look them over. It's a brand new deck. You notice Jokers, Aces, and all the other cards. Those Jokers look especially sharp...");
        readInput();
        out("The door slams open, and you jump to your feet in shock. An ominous red balloon floats by outside...");
        readInput();
        out("You take a step closer. You hear children singing in the distance...");
        readInput();
        out("You take another step closer, subconsciously gripping the box of cards tighter...");
        readInput();
        out("You take a final step and pause. The singing pauses.");
        readInput();
        out("The ballon pops.");
        readInput();
        asciiPrint("clown");
        out("Pennywise the killer clown appears in front of you, hands out to your throat. You reach for the only possible weapon you have - the deck of cards. What card do you pick up?");
        String cardPickedUp = readInput();
        if (isValidCard(cardPickedUp)) {

        } else {
            System.out.println("That doesn't look like a valid card. As you search for this non-existant card in the pile, the last thing you hear is a shrill laugh blasting in your ear");
            userLost();
            return;
        }
    }

    public boolean isValidCard(String card) {
        while (card.indexOf(" ") >= 0) {
            card = card.substring(card.indexOf(" ") + 1);
        }
        for (Card c : cards) {
            if (c.getName().equalsIgnoreCase(card)) {
                return true;
            }
        }
        return false;
    }

    public void userLost() {
        System.out.println("You died. Would you like to try again? (yes to continue)");
        String in = readInput();
        if (in.equals("yes")) {
            playStageOne();
        }
    }

    public void endGame() {
        s.close();
    }

    public static void main(String[] args) {
        MagicJacques game = new MagicJacques();
        game.printCommands();
        game.playStageOne();
        game.endGame();
    }

}