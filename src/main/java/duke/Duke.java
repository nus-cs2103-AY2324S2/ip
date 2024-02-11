package duke;

import duke.command.CommandParser;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * The Duke class represents the main application that interacts with the user.
 * It initializes the User Interface (UI), runs the conversation loop, and handles user input.
 */
public class Duke  {

    private final Ui ui;
    CommandParser parser = new CommandParser("tester");

    /**
     * Constructs a Duke instance and initializes the UI.
     */
    public Duke() {
       ui = new Ui();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return parser.parseInput(input);
    }

}