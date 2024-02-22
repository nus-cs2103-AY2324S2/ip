package duke;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;

/**
 * The Parser class handles parsing of user input for Duke chatbot.
 * It interprets user commands and executes corresponding actions.
 */
public class Parser {
    /**
     * Keywords to terminate the Duke chatbot.
     */
    private static final String[] terminateKeywords = {"bye", "BYE", "Bye"};
    /**
     * List of keywords that trigger the termination of the Duke chatbot.
     */
    private static final List<String> exitProgramme = Arrays.asList(terminateKeywords);

    /**
     * Retrieves user input.
     *
     * @return The user input as String.
     */
    public static String getUserInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Parses the user input to determine if user wishes to exit.
     *
     * @param input The current user input.
     * @return The boolean value for whether the user wishes to exit.
     */
    public static boolean isExit(String input) {
        String[] currInput = input.split(" ", 2);
        if (exitProgramme.contains(currInput[0])) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Parses the user input and executes the corresponding actions.
     *
     * @param userTasks The TaskList containing the user's tasks.
     * @param input The current user input.
     */
    public static Command parse(String input, TaskList userTasks) {
        String[] currInput = input.split(" ", 2);
        String cmd = currInput[0];

        // list tasks
        if (cmd.equals("list")) {
            return new ListCommand(currInput, userTasks);
        // find tasks
        } else if (cmd.equals("find")) {
            return new FindCommand(currInput, userTasks);
        // mark tasks
        } else if (cmd.contains("mark")) {
            return new MarkCommand(currInput, userTasks);
        // delete tasks
        } else if (cmd.equals("delete")) {
            return new DeleteCommand(currInput, userTasks);
        // add tasks
        } else if (cmd.equals("deadline") || cmd.equals("event") || cmd.equals("todo")) {
            return new AddCommand(currInput, userTasks);
        // unknown commands
        } else {
            throw new DukeException(String.format(DukeException.UNKNOWN_CMD, cmd));
        }
    }
}
