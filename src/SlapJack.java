import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

/**
 * Defines a game of SlapJack. Subclass of a CardGame.
 */
public class SlapJack extends CardGame {
  int currentPlayer; // indicates the current player (0 for user, 1 for CPU)
  boolean gameFinished; // flags the finishing of the game
  Player user; // the player representing the user (human)
  Player cpu; // the player representing the opponent (cpu)
  ArrayList<Card> cardStack; // the pile of cards users add their cards to
  Scanner s; // accepts input
  int numRounds; // keeps track of how many rounds the game takes
  boolean userWon;
  String cpuName;

  /**
   * Constructor for the SlapJack game
   * 
   * Initializes instance variables, sets up and shuffles the deck, and deals the
   * cards
   */
  public SlapJack(Scanner s) {
    currentPlayer = 0;
    gameFinished = false;
    ArrayList<Card> gameCards = constructCardList(52);
    Collections.shuffle(gameCards);
    user = new Player(gameCards.subList(0, gameCards.size() / 2)); // automatically removes cards from the ArrayList
    cpu = new Player(gameCards); // gameCards will have half of the original elements before, and then none
    cpuName = "CPU";
    numRounds = 0;
    this.s = s;
    System.out.println();
    cardStack = new ArrayList<Card>();
  }

  /**
   * Constructs the plural/singular form of a noun as needed
   * 
   * @param n    the number of occurances of the noun (e.g. 5 cards)
   * @param noun the singular form of the noun (e.g. card)
   * @return the appropriate form of the noun
   */
  public String determineNounForm(int n, String noun) {
    return (n == 1) ? noun : noun + "s";
  }

  /**
   * Determines the winner of a single round, if there is one. Invoked after each
   * round.
   * 
   * @param isJack        whether the most recent card is a jack
   * @param userSlapped   whether the user slapped the most recent card or not
   * @param timeElapsed   how long the user took to slap the card
   * @param timeTreshhold the threshold for accepted slap time (or, the time for
   *                      the opponent to slap)
   * @return 0 if the player won, 1, if the CPU won, -1 if both players passed
   */
  public int determineRoundWinner(boolean isJack, boolean userSlapped, double timeElapsed, double timeTreshhold) {
    String pileCardTense = determineNounForm(cardStack.size(), "card");
    System.out.println();
    if (userSlapped) {
      if (isJack) {
        if (timeElapsed < timeTreshhold) {
          // if the user slapped a jack within the time, they win the round
          System.out.printf("You slapped the jack first in just %.2f seconds! You pick up %d %s. ", timeElapsed,
              cardStack.size(), pileCardTense);
          return 0;
        } else {
          // User failed to slap in time
          System.out.printf("Too slow! You took %.2f seconds. CPU slapped first and picks up %d %s. ", timeElapsed,
              cardStack.size(), pileCardTense);
          return 1;
        }
      } else {
        // User slapped the wrong card
        System.out.printf("That's not a jack! Opponent picks up %d %s. ", cardStack.size(), pileCardTense);
        return 1;
      }
    } else if (isJack) {
      // User did not slap a jack
      System.out.printf("You missed a jack! Opponent slapped first and picks up %d %s. ", cardStack.size(),
          pileCardTense);
      return 1;
    } else {
      // card was not jack and user did not slap
      return -1;
    }
  }

  /**
   * Prints the updated numbers for each player after the round and confirms
   * update
   * 
   * @param winner the winner of the round
   */
  public void printRoundUpdate(int winner) {
    if (winner != -1) {
      if (winner == 0) {
        user.addCards(cardStack);
        String nounTense = determineNounForm(user.getNumCards(), "card");
        System.out.printf("You now have %d %s. %s has %d.\n", user.getNumCards(), nounTense, cpuName,
            cpu.getNumCards());
      } else {
        cpu.addCards(cardStack);
        String nounTense = determineNounForm(cpu.getNumCards(), "card");
        System.out.printf("%s now has %d %s. You have %d.\n", cpuName, cpu.getNumCards(), nounTense,
            user.getNumCards());
      }
      System.out.println("Hit enter to continue...");
      s.nextLine();
    }
  }

  /**
   * Dynamically adjusts the response time required to win the round! The CPU
   * automatically gets faster over time.
   * 
   * @return the time threshold to win a round
   */
  public double calculateTimeTreshold() {
    if (numRounds < 10) {
      return 1.5;
    } else if (numRounds < 20) {
      return 1.3;
    } else if (numRounds < 40) {
      return 1;
    } else if (numRounds < 60) {
      return 0.8;
    }
    return 0.6; // you better win before it hits 0.6!
  }

  /**
   * Displays a helpful message when starting the game
   */
  public void displayHint() {
    if (numRounds < 3) {
      System.out.println("(Hint: only slap the card if it is a jack!)");
    }
  }

  /**
   * Puts the next card down for the player
   * 
   * @return the next card in the user's deck
   */
  public Card putNextCard() {
    Card nextCard;
    if (currentPlayer == 0) {
      nextCard = user.getNextCard();
      String userCardTense = determineNounForm(user.getNumCards(), "card");
      String pileCardTense = determineNounForm(cardStack.size() + 1, "card");
      System.out.printf("\nYou put down the following card and have %d %s remaining. The pile has %d %s...\n\n",
          user.getNumCards(), userCardTense, cardStack.size() + 1, pileCardTense);
    } else {
      nextCard = cpu.getNextCard();
      String cpuCardTense = determineNounForm(cpu.getNumCards(), "card");
      String pileCardTense = determineNounForm(cardStack.size() + 1, "card");
      System.out.printf("\n%s puts down the following card and has %d %s remaining. The pile now has %d %s...\n\n",
          cpuName, cpu.getNumCards(), cpuCardTense, cardStack.size() + 1, pileCardTense);
    }
    return nextCard;
  }

  /**
   * Plays a single round of the game.
   * 
   * Procedures: 1. Calls putNextCard() to get next card and print the card 2.
   * Wait for user's reaction and time them. This is not it's own function because
   * we need a) user's input and b) response time. Easier to have within this
   * method. 3. Determine the winner of the round by calling determineRoundWinner
   * and prints a post-round update 4. Increases counters and ensures game is not
   * over
   */
  public void playRound() {
    Card nextCard = putNextCard();
    cardStack.add(nextCard);
    System.out.println(nextCard);
    // end step 1

    System.out.println("Hit the enter key to slap the card, or type \"c\" then enter to continue...");
    displayHint();
    System.out.print("> ");
    double startTime = System.nanoTime() / 1e9;
    String in = s.nextLine();
    if (in.equals("quit")) {
      System.out.println();
      System.exit(0);
    }
    double endTime = System.nanoTime() / 1e9;
    double timeElapsed = endTime - startTime;
    // end step 2

    boolean isJack = nextCard.getName().equals("jack");
    boolean userSlapped = in.replace(" ", "").equals(""); // remove whitespace
    double timeTreshhold = calculateTimeTreshold();
    int winner = determineRoundWinner(isJack, userSlapped, timeElapsed, timeTreshhold);
    printRoundUpdate(winner);
    // end step 3

    currentPlayer = (currentPlayer + 1) % 2;
    gameFinished = (user.getNumCards() == 0 || cpu.getNumCards() == 0);
    numRounds++;
    // end step 4
  }

  /**
   * Checks the gameFinished flag if the game is over. This flag is set to "true"
   * in playRound.
   * 
   * @return true if the game is finished, false if not
   */
  public boolean isFinished() {
    if (gameFinished) {
      if (cpuName.equals("CPU")) { // hard-code check to make sure we don't close scanner if we invoke from another
                                   // game
      }
      if (user.getNumCards() > 0) {
        System.out.printf("%s ran out of cards. You won!\nThe game took %d turns to finish. Congrats! 👏🎉\n\n",
            cpuName, numRounds);
        userWon = true;
      } else {
        System.out.printf("You ran out of cards. %s won!\nThe game took %d turns to finish. Try again! 😿😭\n\n",
            cpuName, numRounds);
        userWon = false;
      }
    }
    return gameFinished;
  }

  public boolean playGame() {
    while (!isFinished()) {
      playRound();
    }
    return userWon;
  }

  /**
   * Runs the entire SlapJack game
   * 
   * @param args command line arguments
   */
  public static void main(String[] args) {
    boolean cont = true;
    Scanner s = new Scanner(System.in);
    do {
      SlapJack game = new SlapJack(s);
      game.playGame();
      System.out.print("\nWould you like to play again?\n> ");
      cont = s.nextLine().toLowerCase().contains("yes");
    } while (cont);
    s.close();
  }
}