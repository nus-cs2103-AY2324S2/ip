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

public class Parser {
    private StringTokenizer st;
    private String command;
    private TaskList tasks;

    public Parser(String fullCommand, TaskList tasks) {
        st = new StringTokenizer(fullCommand);
        command = st.nextToken().toLowerCase();
        this.tasks = tasks;
    }

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

        case "bye":
            cmd = new ExitCommand();
            return cmd;

        default:
            throw new DukeException("OOPS!! Pls try again. :)");
        }
    }
}

