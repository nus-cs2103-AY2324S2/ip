package common;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
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
    private TaskList taskList;

    /**
     * Creates an instance of a Parser object to parse the user input.
     * 
     * @param fullCommand The entire user input.
     * @param taskList The list of tasks.
     */
    public Parser(String fullCommand, TaskList taskList) {
        st = new StringTokenizer(fullCommand);
        command = st.nextToken().toLowerCase();
        this.taskList = taskList;
    }

    /**
     * Returns the command object after parsing through the user input.
     *
     * @param fullCommand The entire user input.
     * @return The command object created.
     * @throws IndexOutOfBoundException If the user input a number more/less than the number of tasks in the list.
     * @throws NumberFormatException If the user input is invalid
     * @throws NoSuchElementException If the user input is invalid
     * @throws DukeException If there are other unexpected errors
     */
    public Command parse() throws IndexOutOfBoundsException, NumberFormatException, NoSuchElementException, DukeException {
        Command cmd;
        switch (command) {
        case "list":
            cmd = new ListCommand(taskList);
            return cmd;
        case "mark":
            int indexOfTaskToMark = Integer.parseInt(st.nextToken());
            cmd = new MarkCommand(taskList, indexOfTaskToMark);
            return cmd;
        case "unmark":
            int indexOfTaskToUnmark = Integer.parseInt(st.nextToken());
            cmd = new UnmarkCommand(taskList, indexOfTaskToUnmark);
            return cmd;
        case "delete":
            int indexOfTaskToDelete = Integer.parseInt(st.nextToken());                
            cmd = new DeleteCommand(taskList, indexOfTaskToDelete);
            return cmd;
        case "todo":
        case "deadline":
        case "event":
            cmd = new AddCommand(command, taskList, st);
            return cmd;
        case "bye":
            cmd = new ExitCommand();
            return cmd;
        default:
            throw new DukeException("OOPS!! Pls try again. :)");
        }
    }
}

