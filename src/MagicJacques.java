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
  String help;

  /**
   * Generates a new game of Magic Jacques. Initializes scanner and card deck
   */
  public MagicJacques() {
    s = new Scanner(System.in);
    cards = constructCardList(true, 1); // generates a deck of cards for the user that includes jokers
  }

  /**
   * Print out the commands the user can choose at any point
   */
  public void printCommands() {
    System.out.println(
        "Commands:\n\toptions - prints out all available moves\n\thint - the best move\n\tquit - end the game\t");
  }

  /**
   * Prints one line of the game and asks user for response
   * 
   * @param command the next line of the game
   * @param hint    a hint if the user is stuck
   * @param options all of the options the user has
   * 
   * @return the input from the user
   */
  public String lineOut(String command, String hint, String[] options) {
    System.out.println("\n" + command + "\n");
    return readInput(hint, options);
  }

  /**
   * Display the prompt for the user to enter text and read user response. If a
   * known command is entered, display the appropriate output and ask for prompt
   * again
   * 
   * @param hint    a hint if the user is stuck
   * @param options all of the options a user has
   * 
   * @return the input from the user
   */
  public String readInput(String hint, String[] options) {
    System.out.print("> ");
    String in = s.nextLine().toLowerCase();
    if (in.equals("quit")) {
      System.out.println();
      System.exit(0);
    }
    // if the user calls a command, display the result and ask the question again
    if (in.equals("hint")) {
      System.out.println("\t" + hint + "\n");
      return readInput(hint, options); // recursively calling function is a way to "ask user again" without loops
    }
    if (in.equals("help")) {
      System.out.println("\t" + help + "\n");
      return readInput(hint, options);
    }
    if (in.equals("options")) {
      for (int a = 0; a < options.length; a++) {
        System.out.println("\t" + options[a]);
      }
      System.out.println();
      return readInput(hint, options);
    }
    return in;
  }

  /**
   * Display an ASCII image
   * 
   * @param image the name of the image
   */
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
      br.close();
    } catch (FileNotFoundException f) {
      throw new IllegalArgumentException("Input image is not valid");
    } catch (IOException e) {
      throw new IllegalArgumentException("Error constructing image");
    }
  }

  /**
   * Plays all of stage one. Most of the commands are simply lineOuts, since stage
   * one only requires enter for nearly every step
   */
  public void playStageOne() {
    lineOut("You wake up. It's pitch black.", "What do you need a hint for? Just hit enter!",
        new String[] { "Do nothing - [enter]" });
    lineOut(
        "The lights turn on with a click. As you wait for your eyes to adjust, you feel your pockets. There's two\nrectangular objects in each pocket...",
        "What do you need a hint for? Just hit enter!", new String[] { "Do nothing - [enter]" });
    lineOut(
        "Your eyes adjust. You're in a small cell-like room with a single door, wearing a black shirt and jeans\nand no shoes. You reach for the objects in your pocket.",
        "What do you need a hint for? Just hit enter!", new String[] { "Do nothing - [enter]" });
    lineOut(
        "You find a wooden name tag in your left pocket. It says \"Magic Jacques\". The name sounds familiar. You\nput it on.",
        "What do you need a hint for? Just hit enter!", new String[] { "Do nothing - [enter]" });
    lineOut(
        "In your right pocket, you pull out a small box. It's full of playing cards. You look them over. It's a\nbrand new deck. You notice Jokers, Aces, and all the other cards. Those Jokers look especially sharp...",
        "What do you need a hint for? Just hit enter!", new String[] { "Do nothing - [enter]" });
    lineOut("The door slams open, and you jump to your feet in shock. An ominous red balloon floats by outside...",
        "What do you need a hint for? Just hit enter!", new String[] { "Do nothing - [enter]" });
    lineOut("You take a step closer. You hear children singing in the distance...",
        "What do you need a hint for? Just hit enter!", new String[] { "Do nothing - [enter]" });
    lineOut("You take another step closer, subconsciously gripping the box of cards tighter...",
        "What do you need a hint for? Just hit enter!", new String[] { "Do nothing - [enter]" });
    lineOut("You take a final step and pause. The singing pauses.", "What do you need a hint for? Just hit enter!",
        new String[] { "Do nothing - [enter]" });
    lineOut("The ballon pops.", "What do you need a hint for? Just hit enter!",
        new String[] { "Do nothing - [enter]" });
    asciiPrint("clown");
    String cardPickedUp = lineOut(
        "A killer clown appears in front of you, hands out to your throat. You reach for the only\npossible weapon you have - the deck of cards. What card do you pick up?",
        "You saw a particularly sharp card earlier when looking over the deck...perhaps try re-reading the line where you looked at your right pocket.",
        new String[] { "Do nothing and die - [enter]", "Pick a card - [enter name of card]" });

    int invalidTries = 0;
    while (!isValidCard(cardPickedUp) && invalidTries <= 3) { // allows three invalid tries
      if (invalidTries == 3) {
        System.out.println(
            "\nAs you search for this non-existant playing card in the deck, the lights go dark. The last thing you hear is a shrill laugh...\n\nNext time, read the contents of your pocket closer, and search for a sharp card!\n");
        userLost(1);
        return;
      }
      cardPickedUp = lineOut("That doesn't look like a valid card. Try entering the full name of valid playing card.",
          "You saw a particularly sharp card earlier when looking over the deck...perhaps try re-reading the line where you looked at your right pocket.",
          new String[] { "Do nothing and die - [enter]", "Pick a card - [enter name of card]" });
      invalidTries++;
    }

    if (!checkClownCard(cardPickedUp)) { // allow one wrong guess
      cardPickedUp = lineOut(
          "Wrong choice! When you looked over the deck earlier, you saw a particularly sharp card. Try re-reading the line where you looked over the deck!",
          "The card has a clown on it.",
          new String[] { "Do nothing and die - [enter]", "Pick a card - [enter name of card]" });
      if (!checkClownCard(cardPickedUp)) {
        System.out.println(
            "\nYou fling the card at the clown, only to realize it did nothing. The last thing you hear is a shrill laugh in your ear. Try picking a different card!");
        userLost(1);
        return;
      }
    }
  }

  /**
   * Checks if the card is a joker and prints the next step.
   * 
   * The print statement is included in a boolean check because it reduced clutter
   * in code. Alternative was to have the same print statement after both checks
   * above.
   * 
   * @param card the user's choice of card
   * @return true if the card is a joker; false if not
   */
  public boolean checkClownCard(String card) {
    if (card.contains("joker")) {
      System.out.println(
          "\nYou fling the Joker with all of your might at the clown. The card flies through the air and hits the clown square on the nose. With a second pop, the clown turns into a pile of confetti. Only his shoes are left.\n");
      return true;
    } else {
      return false;
    }
  }

  /**
   * Plays stage 2
   * 
   * This stage requires more guesses and user input
   */
  public void playStageTwo() {
    lineOut("(You passed stage one. Hit enter to continue...)",
        "You're asking for a hint now? You just passed the stage. Just hit enter.",
        new String[] { "Do nothing - [enter]" });
    String nextInp = lineOut(
        "You stare at the empty shoes where the clown was, recovering from the shock. You remember you're still not wearing shoes.\n\n1. Wear the shoes - wear shoes\n2. Do nothing - [enter]",
        "You still don't have shoes. Why not put the clown's shoes on?",
        new String[] { "Wear the clown's shoes - \"wear shoes\"", "Do nothing - [enter]" });
    while (!(nextInp.equals("wear the shoes") || nextInp.contains("wear") || nextInp.contains("shoes")
        || nextInp.equals("1"))) {
      nextInp = lineOut("Hm, I think you should try wearing the shoes.", "Type \"wear the shoes\".",
          new String[] { "Wear the clown's shoes - \"wear shoes\"" });
    }
    System.out
        .println("\nAh, so much better. They fit perfectly. You slowly walk into the hallway and reach three doors:");
    asciiPrint("doors");
    nextInp = lineOut("Which door do you choose?", "Try picking a door first.",
        new String[] { "Pick the very scary door - \"very scary\"", "Pick the kind of scary door - \"kind of scary\"",
            "Pick the not scary door - \"not scary\"" });
    while (!nextInp.contains("kind")) {
      while (!(nextInp.contains("very") || nextInp.contains("kind") || nextInp.contains("not"))) {
        nextInp = lineOut(
            "Hm, that doesn't look like an option. Try typing the name of the door, exactly as it appears on the door.",
            "Seriously? Just type the name of a door!", new String[] { "Pick the very scary door - \"very scary\"",
                "Pick the kind of scary door - \"kind of scary\"", "Pick the not scary door - \"not scary\"" });
      }
      if (nextInp.contains("very")) {
        nextInp = lineOut(
            "You enter a luxurious conference room, full of people. You notice the words \"Kleiner Perkins\" on the wall and two nervous presenters, sweating through their shirts. Confused, you exit quietly close the door.\n\nTry picking another door.",
            "Let's try another door.", new String[] { "Pick the very scary door - \"very scary\"",
                "Pick the kind of scary door - \"kind of scary\"", "Pick the not scary door - \"not scary\"" });
      } else if (nextInp.contains("not")) {
        System.out.println(
            "\nYou open the door and step inside, only to realize there's no floor. As you fall, the cards fly out of your pocket, forming a beautiful image of fluttering cards through the filtered light...\n");
        userLost(2);
        return;
      }
    }
  }

  /**
   * Plays the final stage
   * 
   * This stage is more resolution and completion, so user has few choices to make
   */
  public void playStageThree() {
    lineOut("(You passed stage two. Hit enter to continue...)",
        "You're asking for a hint now? You just passed the stage. Just hit enter.",
        new String[] { "Do nothing - [enter]" });
    String response = lineOut(
        "You enter what appears to be the backstage of a theater. A short man with a bushy mustache runs toward you.\n\n\"Jacques! What are you doing? Get in costume! You're going on in 2 minutes!\" He points to the clown\ncostume hanging in the corner.\n\nYou wonder what to do. Should you follow directions or run away?\n1. Run away - run\n2. Put on the costume - costume",
        "Enter \"1\" or \"2\"", new String[] { "1. Run away - run", "2. Put on the costume - costume" });
    if (response.equals("1") || response.contains("run")) {
      userWon(false);
      return;
    }
    response = lineOut(
        "Confused and freaked out, you wear the clown costume. As you change, the short man with the bushy mustache\nleaves. As you glance in the mirror, you realize you look exactly like the clown that tried to kill you!\n\n1. Change back to your clothes - change\n2. Run away - run",
        "Things are getting weird.", new String[] { "Change out of the clown costume - change", "Run away - run" });
    if (response.equals("1") || response.contains("change")) {
      response = lineOut("You change back to your original clothes.\n\n1. Wait - enter\n2. Run away - run",
          "I'd get out of there if I were you.", new String[] { "Do nothing - [enter]", "Run away - run" });
      if (response.contains("run")) {
        userWon(false);
        return;
      } else {
        System.out.println(
            "\nAs you wait, you hear children singing. It suddenly gets dark and cold. The last thing you hear is a shrill laugh in your ear.\n");
        userLost(3);
      }
    }
    userWon(true);
  }

  /**
   * Prints one of the two outcomes of the user completing the game
   * 
   * @param inCostume true if the user is in costume. This prints the scary ending
   *                  false if the user is not in the clown costume. This prints
   *                  the preferred ending
   */
  public void userWon(boolean inCostume) {
    if (inCostume) {
      lineOut("Still wearing the clown costume, you run back into the hallway.", "Keep running!",
          new String[] { "Do nothing - [enter]" });
      String choice = lineOut(
          "As you run, you hear children singing and see a red balloon...\n\n1. Run faster - \"run\"\n2. Hide - \"hide\"",
          "Definitely hide.", new String[] { "Run faster - \"run\"", "Hide - \"hide\"" });
      if (choice.contains("run") || choice.contains("2")) {
        System.out.println("\nYou run even faster!");
      }
      lineOut("The singing grows louder. You decide to run into the first room you see...",
          "Ready to be blown away? Hit enter!", new String[] { "Enter the room - [enter]" });
      lineOut(
          "You run into the room. A young, barefoot person with the nametag \"Magic Jacques\" looks back at you in\ncomplete shock. Wait...isn't that...you?",
          "It's too late.", new String[] { "Do nothing - [enter]" });

      lineOut(
          "You try to say something to explain! Before you can respond, the other Jacques pulls out a Joker. You know what happens next...",
          "No hint available.", new String[] { "Do nothing - [enter]" });

      System.out.println("\nYou close your eyes and hear the card flying at you.");
    } else {
      lineOut(
          "You run out the door, and decide to enter the door \"Very Scary\". The room is a well-lit conference room full of people, with the words \"Kleiner Perkins\" on the wall. It looks like a meeting had just finished.",
          "We're confused as well, friend. Just hit enter.", new String[] { "Do nothing - enter" });
      System.out.println(
          "\nA stern woman approaches you. \"Well, Jacques, it looks like you've played your cards right. Kleiner\nPerkins would be more than happy to lead the Series A on this one. Glad to have you.\" Before you can respond, you wake up.");
    }

    System.out
        .println("\nThank you for playing Magic Jacques! If you have time, try exploring an alternate ending :)\n");
  }

  /**
   * Finds out if the entered card is a real card value
   * 
   * Though iterating through an array of strings would be quicker, checking
   * against the whole deck of cards is easier because it is already assigned.
   * 
   * @param card the name of the card to check validity
   * @return true if the card name is valid (exists in deck); false if not
   */
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

  /**
   * Prints out the user's fate and allows to retry from the last completed stage
   * 
   * @param round the stage where the user lost
   */
  public void userLost(int round) {
    System.out.print("You died. Would you like to try again? (yes to continue)\n> ");
    String in = s.nextLine().toLowerCase();
    if (in.equals("yes") || in.equals("")) {
      switch (round) {
      case 1:
        playStageOne();
        break;
      case 2:
        playStageTwo();
        break;
      case 3:
        playStageThree();
        break;
      }
    } else {
      System.exit(1);
    }
  }

  /**
   * Sends users to another dimension
   * 
   * @param args command line arguments
   */
  public static void main(String[] args) {
    MagicJacques game = new MagicJacques();
    game.printCommands();
    game.playStageOne();
    game.playStageTwo();
    game.playStageThree();
  }
}