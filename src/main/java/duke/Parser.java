package duke;

import duke.commands.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.utils.Ui;

import java.sql.SQLOutput;

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
    public static void parse(TaskList userTasks, String[] currInput) {
        try {
            String cmd = currInput[0];

            // list tasks
            if (cmd.equals("list")) {
                System.out.println(new ListCommand(currInput, userTasks).execute());
            // find tasks
            } else if (cmd.equals("find")) {
                System.out.println(new FindCommand(currInput, userTasks).execute());
            // mark tasks
            } else if (cmd.contains("mark")) {
                System.out.println(new MarkCommand(currInput, userTasks).execute());
            // delete tasks
            } else if (cmd.equals("delete")) {
                System.out.println(new DeleteCommand(currInput, userTasks).execute());
            // add tasks
            } else if (cmd.equals("deadline") || cmd.equals("event") || cmd.equals("todo")) {
                System.out.println(new AddCommand(currInput, userTasks).execute());
            // unknown commands
            } else {
                throw new DukeException(String.format(DukeException.UNKNOWN_CMD, cmd));
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(Ui.LINE);
        }
    }
}
