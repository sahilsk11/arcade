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

    public MagicJacques() {
        s = new Scanner(System.in);
        cards = constructCardList(true, 13);
    }

    public void printCommands() {
        System.out.println(
                "Commands:\n\thelp - explain what to do\n\toptions - prints out all available moves\n\thint - the best move\n\tquit - end the game\t");
    }

    /**
     * Prints the next commands
     * 
     */
    public String lineOut(String command, String hint, String[] options) {
        System.out.println("\n" + command + "\n");
        return readInput(hint, options);
    }

    public String readInput(String hint, String[] options) {
        System.out.print("> ");
        String in = s.nextLine().toLowerCase();
        if (in.equals("quit")) {
            System.out.println();
            System.exit(0);
        }
        if (in.equals("hint")) {
            System.out.println("\t" + hint + "\n");
            return readInput(hint, options);
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

    public String readInput() {
        System.out.print("> ");
        String in = s.nextLine();
        if (in.equals("quit")) {
            System.out.println();
            System.exit(0);
        }
        return in;
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

    public void setHelp(String help) {
        this.help = help;
    }

    public boolean playStageOne() {
        setHelp("\nWelcome to stage one. Most lines require you to do nothing. Just hit [enter]. To view all available moves at any point, type \"options\"");
        lineOut("You wake up. It's pitch black.", "What do you need a hint for? Just hit enter!",
                new String[] { "Do nothing - [enter]" });
        lineOut("The lights turn on with a click. As you wait for your eyes to adjust, you feel your pockets. There's two rectangular objects in each pocket...",
                "What do you need a hint for? Just hit enter!", new String[] { "Do nothing - [enter]" });
        lineOut("Your eyes adjust. You're in a small cell-like room with a single door, wearing a black shirt and jeans and no shoes. You reach for the objects in your pocket.",
                "What do you need a hint for? Just hit enter!", new String[] { "Do nothing - [enter]" });
        lineOut("You find a wooden name tag in your left pocket. It says \"Magic Jacques\". The name sounds familiar. You put it on.",
                "What do you need a hint for? Just hit enter!", new String[] { "Do nothing - [enter]" });
        lineOut("In your right pocket, you pull out a small box. It's full of playing cards. You look them over. It's a brand new deck. You notice Jokers, Aces, and all the other cards. Those Jokers look especially sharp...",
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
                "Pennywise the killer clown appears in front of you, hands out to your throat. You reach for the only possible weapon you have - the deck of cards. What card do you pick up?",
                "You saw a particularly sharp card earlier when looking over the deck...something about a clown...perhaps try re-reading that line",
                new String[] { "Do nothing and die - [enter]", "Pick a card - [enter name of card]" });
        if (isValidCard(cardPickedUp)) {
            if (cardPickedUp.equalsIgnoreCase("joker")) {
                System.out.println(
                        "\nYou fling the Joker with all of your might at Pennywise. The card flies through the air - as if the scene was from Now You See Me - hits the clown square on the nose, and the world freezes. With a second pop, the clown turns into a pile of confetti. Only his shoes are left.\n");
                return true;
            } else {
                System.out.println(
                        "The card flies straight at Pennywise, hits him, and flutters to the ground. The lights turn off, and it feels very cold.");
                userLost(1);
                return false;
            }
        } else {
            System.out.println(
                    "That doesn't look like a valid card. As you search for this non-existant card in the pile, the last thing you hear is a shrill laugh blasting in your ear.");
            userLost(1);
            return false;
        }
    }

    public boolean playStageTwo() {
        setHelp("Stage 2 requires a move (besides [enter]) at nearly every line. Type \"options\" to view valid moves.");
        lineOut("\n(You passed stage one. Hit enter to continue...)",
                "You're asking for a hint now? You just passed the stage. Just hit enter.",
                new String[] { "Do nothing - [enter]" });
        String nextInp = lineOut(
                "You stare at the empty shoes where the clown was, recovering from the shock. You remember you're still not wearing shoes.",
                "You still don't have shoes. Why not put the clown's shoes on?",
                new String[] { "Wear the clown's shoes - \"wear shoes\"" });
        while (!(nextInp.equals("wear the shoes") || nextInp.contains("wear") || nextInp.contains("shoes"))) {
            nextInp = lineOut("Hm, I think you should try wearing the shoes.", "Type \"wear the shoes\".",
                    new String[] { "Wear the clown's shoes - \"wear shoes\"" });
        }
        System.out.println(
                "\nAh, so much better. They fit perfectly. You slowly walk into the hallway and reach three doors:");
        asciiPrint("doors");
        nextInp = lineOut("Which door do you choose?", "Try picking a door first.",
                new String[] { "Pick the very scary door - \"very scary\"",
                        "Pick the kind of scary door - \"kind of scary\"", "Pick the not scary door - \"not scary\"" });
        while (!nextInp.contains("kind of scary")) {
            while (!(nextInp.contains("very scary") || nextInp.contains("kind of scary")
                    || nextInp.contains("not scary"))) {
                nextInp = lineOut(
                        "Hm, that doesn't look like an option. Try typing the name of the door, exactly as it appears on the door.",
                        "Seriously? Just type the name of a door!",
                        new String[] { "Pick the very scary door - \"very scary\"",
                                "Pick the kind of scary door - \"kind of scary\"",
                                "Pick the not scary door - \"not scary\"" });
            }
            if (nextInp.contains("very scary")) {
                nextInp = lineOut(
                        "You enter a luxurious conference room, full of people. You notice the words \"Kleiner Perkins\" on the wall and two nervous presenters, sweating through their shirts. You quickly realize this is a Series B investment meeting and the company is about to raise a downround. You shudder and quickly close the door.",
                        "Let's try another door.",
                        new String[] { "Pick the very scary door - \"very scary\"",
                                "Pick the kind of scary door - \"kind of scary\"",
                                "Pick the not scary door - \"not scary\"" });
            } else if (nextInp.contains("not scary")) {
                System.out.println(
                        "\nYou open the door and step inside, only to realize there's no floor. As you fall, the cards fly out of your pocket, forming a beautiful image of fluttering cards through the filtered light...\n");
                userLost(2);
                return false;
            }
        }
        lineOut("\nYou step into a dark room. As your eyes adjust, you hear a voice come through a speaker...\n\nAND UP NEXT, WE HAVE THE INCREDIBLE MAGIC JACQUES! You can faintly hear people cheering.",
                "Hit enter.", new String[] { "Do nothing - [enter]" });
        nextInp = lineOut(
                "Your eyes adjust. You find yourself in what looks like the backstage of a theater. You notice a rabbit sitting on the table next to a tall hat. It seems to be looking at you...",
                "Don't be rude. Walk over to the rabbit",
                new String[] { "Walk over to the rabbit - \"approach rabbit\"", "Ignore the rabbit - \"ignore\"" });
        while (!(nextInp.contains("rabbit") || nextInp.contains("approach")
                || (nextInp.contains("talk") && nextInp.contains("rabbit")))) {
            nextInp = lineOut("The rabbit continues to sit on the table. Perhaps you should approach it.",
                    "Don't be rude. Walk over to the rabbit",
                    new String[] { "Walk over to the rabbit - \"approach rabbit\"" });
        }
        nextInp = lineOut("The rabbit begins to talks:\n\"Hello, Jacques. Do you want to play a game?\"",
                "Seems you have nothing else to do; play the game",
                new String[] { "Play the game - \"yes\"", "Ignore the rabbit - \"no\"" });
        while (!(nextInp.contains("play") || nextInp.contains("yes") || nextInp.contains("ok")
                || nextInp.contains("sure"))) {
            nextInp = lineOut("You and the rabbit awkwardly stare at each other. Perhaps you should play the game.",
                    "Seems you have nothing else to do; play the game", new String[] { "Play the game - \"play\"" });
        }
        lineOut("You nod your head, completley bewildered. The rabbit begins:\n\"Hand me your card deck.\" You quitely obey. The rabbit quickly forms two piles of 13 cards, removing the jokers. He hands one pile back to you.\n Well, Jacques. Let's play some slap jack.\"",
                "You don't have an option anymore. Hit enter!", new String[] { "Start Game - [enter]" });
        SlapJack game = new SlapJack(26, "Rabbit", s);
        int rounds = 1;
        while (!game.playGame() && rounds < 3) {
            rounds++;
            lineOut("Angry that you lost to a rabbit, you decide you must play again. Hit enter!",
                    "Look, if you lose again, you can give up.", new String[] { "Start Game - [enter]" });
        }
        return true;
    }

    public void playStageThree() {
        lineOut("(you passed stage 2. One stage left! Hit enter to continue...)",
                "You're asking for a hint now? You just passed the stage. Just hit enter.",
                new String[] { "Do nothing - [enter]" });
        lineOut("Just as you are ready to celebrate your hard-earned victory against the rabbit, a short, plump man swings open a door. \"JACQUES? WHAT ARE YOU DOING? GET ON STAGE!\"",
                "You better go on stage.", new String[] { "Get on stage - [enter]", "Run away - \"run\"" });
        lineOut("Before you can react, the plump man runs over and grabs your hand. The rabbit jumps on top of your head and offers you your hat.\n\"Goodness, are those Michael's shoes? Where is that clown?!\" the plump man exclaims. You're not sure whether to tell the man what happened.",
                "Idk man, go with the flow.", new String[] { "Follow the man - [enter]" });
        lineOut("The plump man leads you to the stage. You hear the audiences cries grow lowder. Your heart thumps as the thought of performing. You try to remember some magic trick - any magic trick - that you can perform, but your mind draws a blank.",
                "Wait for it...", new String[] { "Keep walking - [enter]" });
        lineOut("The roar of the audience echoes in your chest as you move closer to the stage. You begin wishing that clown ended it for you before you got here...",
                "No hint available", new String[] { "Do nothing - [enter]" });
        String nextInp = lineOut("The man lets you go and walks away ... and suddenly you see your chance! You see an exit sign...",
                "No hint available", new String[] { "Run away - run", "Get on stage - enter stage" });
        if (nextInp.contains("exit") || nextInp.contains("run")) {
            System.out.println("\nYou sprint to the exit, dropping your")
        }
        lineOut("\"Jacques! Jacques! Jacques!\" The audience screams.", "No hint available",
                new String[] { "Do nothing - enter" });
        lineOut("\"Jacques! Jac...! Ja..!\"", "No hint available", new String[] { "Do nothing - enter" });
        lineOut("\"Jack!!\"\nYour eyes bolt open.", "No hint available", new String[] { "Do nothing - enter" });
        lineOut("\"What on earth are you doing? Did you sleep here last night?\"", "No hint available",
                new String[] { "Do nothing - enter" });
        lineOut("You look up. You see the green vines of the Airbnb office and realize...you're at work! You look back and see your manager with the face of someone who's seen a ghost.",
                "No hint available", new String[] { "Do nothing - enter" });
        lineOut("You look at your laptop, covered in drool (gross). It's 10AM ... ", "No hint available",
                new String[] { "Do nothing - enter" });
        lineOut("After apologizing profously to your manager, you get up and walk back to your desk.",
                "No hint available", new String[] { "Do nothing - enter" });
        lineOut("You sit down, deeply breathing to remind yourself it was all a dream.", "No hint available",
                new String[] { "Do nothing - enter" });
        lineOut("Just as you calm down, you look to your co-workers desk and see ... a rabbit playing with cards??!!",
                "No hint available", new String[] { "Do nothing - enter" });
        lineOut("You pass out.", "No hint available", new String[] { "Do nothing - enter" });
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

    public void userLost(int round) {
        System.out.println("You died. Would you like to try again? (yes to continue)");
        String in = readInput();
        if (in.equals("yes")) {
            switch (round) {
            case 1:
                playStageOne();
                break;
            case 2:
                playStageTwo();
                break;
            }
        }
    }

    public void endGame() {
        System.out.println(
                "\nThank you for playing Magic Jacques! Making this game has been one of the oddest yet most satisfying projects, but I hope it was all worth it. I hope you had fun exploring a new world, and come again soon!\n");
        s.close();
    }

    public static void main(String[] args) {
        MagicJacques game = new MagicJacques();
        game.printCommands();
        game.playStageOne();
        game.playStageTwo();
        game.playStageThree();
        game.endGame();
    }

}