package duke;

import duke.command.CommandParser;

/**
 * The Duke class represents the main application that interacts with the user.
 * It initializes the User Interface (UI), runs the conversation loop, and handles user input.
 */
public class Duke  {

    CommandParser parser;

    /**
     * Constructs a Duke instance and initializes the UI.
     */
    public Duke() {
        parser = new CommandParser("tester");
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parser.parseInput(input);
    }

}