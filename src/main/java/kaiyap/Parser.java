package kaiyap;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Parser class in the KaiYap application is responsible for interpreting user inputs.
 * It analyzes the command entered by the user and determines the appropriate action to take.
 * This class maintains a list of known actions that can be performed within the application.
 */
public class Parser {

    private final ArrayList<String> actions;

    public Parser() {
        actions = new ArrayList<>(Arrays.asList("mark", "unmark", "find", "delete"));
    }

    /**
     * Decides the action to be taken based on the user's input.
     * This method interprets the command entered by the user and returns a string representing the action to be taken.
     *
     * @param input The user's input command.
     * @return A string representing the action to be taken.
     */
    public String decideAction(String input) {
        String choice = input.split(" ")[0];
        if (input.equals("list")) {
            return "listInputs";
        } else if (actions.contains(choice)) {
            return choice;
        } else {
            return "default";
        }
    }
}
