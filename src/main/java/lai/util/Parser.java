package lai.util;

import java.util.Scanner;

/**
 * Provides parsing functionality for user input in the Lai application.
 * This class is responsible for interpreting user commands and their associated details.
 */
public class Parser {
    /**
     * Parses a line of input from the user into a command and its associated description.
     * The input is expected to be in the format "command description", where "command" is the action to be performed,
     * and "description" is the additional detail of the command. If no description is provided, an empty string is used
     * as the description.
     *
     * @param scanner A Scanner object used to read the user's input.
     * @return An array of two Strings, where the first element is the command and the second element is the
     * description.
     */
    public static String[] parse (Scanner scanner) {
        // Separating the command and description from user input
        String[] commandDesc = scanner.nextLine().split("\\s+", 2);
        String command = commandDesc[0];
        String desc = "";

        if (commandDesc.length > 1) {
            desc = commandDesc[1];
        }

        return new String[]{ command, desc };
    }

    public static String[] parse (String input) {
        // Separating the command and description from user input
        String[] commandDesc = input.split("\\s+", 2);
        String command = commandDesc[0];
        String desc = "";

        if (commandDesc.length > 1) {
            desc = commandDesc[1];
        }

        return new String[]{ command, desc };
    }

    /**
     * Checks if the provided task description is empty and throws an exception if it is.
     * This method is used to ensure that tasks have descriptions when needed.
     *
     * @param desc The task description to be checked.
     * @throws LaiException If the task description is empty.
     */
    public static void checkDescription(String desc) throws LaiException {
        if (desc.equals("")) {
            throw new LaiException("Dude. What am I supposed to do with an empty description?");
        }
    }

}
