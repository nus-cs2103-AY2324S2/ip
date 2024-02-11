package duke;

import java.io.IOException;

/**
 * The Duke class is the main entry point for the bot application itself
 * It uses an Operator to connect the user to the bot
 * The operator is responsible for handling user input and bot output
 * It's the entry point for the bot
 */
public class Duke {
    /**
     * The main method to start the bot.
     * It initializes the bot, prints a greeting, and starts the operator to parse
     * user input
     */
    public static void main(String[] args) throws BotException, IOException {
        // Initial
        System.out.println(Ui.wrapWithSepLine(Bot.getGreeting()));

        // Starting parser to work with bot
        Parser operator = new Parser();
        operator.startOperator();
    }
}