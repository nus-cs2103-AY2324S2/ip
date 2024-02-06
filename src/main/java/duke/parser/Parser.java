package duke.parser;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.NoSuchElementException;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String mainCommand = fullCommand.split(" ")[0];
        String subCommands = fullCommand.substring(fullCommand.indexOf(' ') + 1);
        boolean isSingleCommand = fullCommand.indexOf(' ') == -1;

        switch (mainCommand) {
        case "list":
            if (!isSingleCommand) {
                throw new DukeException("OOPS!!! Too many arguments provided.");
            }

            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "mark":
        case "unmark": {
            if (isSingleCommand) {
                throw new DukeException("OOPS!!! Some arguments are missing.");
            }
            int index = Integer.parseInt(subCommands) - 1;
            return new MarkCommand(index, mainCommand);
        }
        case "todo":
            if (isSingleCommand) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }

            return new AddCommand(mainCommand, subCommands);
        case "deadline":
            if (isSingleCommand) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            String[] deadlineCommands = subCommands.split(" /by ");
            if (deadlineCommands.length != 2) {
                throw new DukeException("OOPS!!! Invalid arguments provided.");
            }

            return new AddCommand(mainCommand, deadlineCommands[0], deadlineCommands[1]);
        case "event":
            if (isSingleCommand) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            String[] eventCommands = subCommands.split(" /from ");
            if (eventCommands.length != 2) {
                throw new DukeException("OOPS!!! Invalid arguments provided.");
            }
            String[] startEnd = eventCommands[1].split(" /to ");
            if (startEnd.length != 2) {
                throw new DukeException("OOPS!!! Invalid arguments provided.");
            }

            return new AddCommand(mainCommand, eventCommands[0], startEnd[0], startEnd[1]);
        case "delete":
            if (isSingleCommand) {
                throw new DukeException("OOPS!!! Some arguments are missing.");
            }
            int index = Integer.parseInt(subCommands) - 1;
            return new DeleteCommand(index);
        default:
            System.out.println("default");
        }

        return new ExitCommand(); // delete after implementing all cases
    }
}
