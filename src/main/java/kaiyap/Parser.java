package kaiyap;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

    private final ArrayList<String> actions = new ArrayList<>(Arrays.asList("mark", "unmark", "find", "delete"));
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
