package duke;
import java.io.IOException;

public class Duke {
    // Main method to start the bot
    // Duke uses the Operator to connect the user to the bot
    // A separate operator gives us flexibility to change the way the bot is
    // operated
    // and even add more bots in the future
    public static void main(String[] args) throws BotException, IOException {
        // Initial
        System.out.println(Ui.wrapWithSepLine(Bot.getGreeting()));

        // Starting parser to work with bot
        Parser operator = new Parser();
        operator.startOperator();
    }
}
