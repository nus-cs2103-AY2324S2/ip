package duke;

import java.io.IOException;

public class TerminalDuke {
    // Entry point for the bot on terminal
    public static void main(String[] args) throws BotException, IOException {
        // Initial
        System.out.println(Ui.wrapWithSepLine(Bot.getGreeting()));

        // Starting parser to work with bot
        Parser operator = new Parser();
        operator.startOperator();

    }
}