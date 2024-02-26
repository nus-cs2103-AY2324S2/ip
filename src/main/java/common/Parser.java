package common;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import task.TaskList;

/**
 * Parses the user input and creates a command accordingly.
 */
public class Parser {
    private StringTokenizer st;
    private String command;
    private TaskList tasks;

    /**
     * Creates an instance of a Parser object to parse the user input.
     *
     * @param fullCommand The entire user input.
     * @param tasks The list of tasks.
     */
    public Parser(String fullCommand, TaskList tasks) {
        st = new StringTokenizer(fullCommand);
        command = st.nextToken().toLowerCase();
        this.tasks = tasks;
    }

    /**
     * Returns the command object after parsing through the user input.
     *
     * @return The command object created.
     * @throws IndexOutOfBoundException If the user input a number more/less than the number of tasks in the list.
     * @throws NumberFormatException If the user input is invalid
     * @throws NoSuchElementException If the user input is invalid
     * @throws DukeException If there are other unexpected errors
     */
    public Command parse() throws IndexOutOfBoundsException, NumberFormatException,
            NoSuchElementException, DukeException {

        Command cmd;
        switch (command) {
        case "list":
            cmd = new ListCommand(tasks);
            return cmd;

        case "mark":
            int indexOfTaskToMark = Integer.parseInt(st.nextToken());
            cmd = new MarkCommand(tasks, indexOfTaskToMark);
            return cmd;

        case "unmark":
            int indexOfTaskToUnmark = Integer.parseInt(st.nextToken());
            cmd = new UnmarkCommand(tasks, indexOfTaskToUnmark);
            return cmd;

        case "delete":
            int indexOfTaskToDelete = Integer.parseInt(st.nextToken());
            cmd = new DeleteCommand(tasks, indexOfTaskToDelete);
            return cmd;

        case "todo":
        case "deadline":
        case "event":
            cmd = new AddCommand(command, tasks, st);
            return cmd;

        case "find":
            cmd = new FindCommand(tasks, st);
            return cmd;

        case "bye":
            cmd = new ExitCommand();
            return cmd;

        default:
            throw new DukeException("OOPS!! Pls try again. :)");
        }
    }
}

