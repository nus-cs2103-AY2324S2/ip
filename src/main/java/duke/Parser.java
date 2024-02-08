package duke;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.utils.Ui;

/**
 * The Parser class handles parsing of user input for Duke chatbot.
 * It interprets user commands and executes corresponding actions.
 */
public class Parser {
    /**
     * Parses the user input and executes the corresponding actions.
     *
     * @param userTasks The TaskList containing the user's tasks.
     * @param currInput The current user input.
     */
    public static Command parse(String[] currInput, TaskList userTasks) {
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
