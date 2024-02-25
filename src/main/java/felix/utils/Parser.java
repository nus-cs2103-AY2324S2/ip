package felix.utils;

import java.time.format.DateTimeParseException;

import felix.command.AddTaskCommand;
import felix.command.Command;
import felix.command.DeleteCommand;
import felix.command.ExitCommand;
import felix.command.FindCommand;
import felix.command.ListCommand;
import felix.command.MarkCommand;
import felix.command.UnmarkCommand;
import felix.command.UpdateTaskCommand;
import felix.exception.FelixException;
import felix.task.Deadline;
import felix.task.Event;
import felix.task.Task;
import felix.task.ToDo;

/**
 * Class representing a parser that processes user commands
 */
public class Parser {
    /**
     * Parses a line of user input and returns the corresponding Command.
     *
     * @return Command.
     * @throws FelixException If command is unrecognised or command formatting is wrong.
     */
    public Command getCommand(String line) throws FelixException {
        // separate first word from rest of words
        String[] words = line.split(" ", 2);
        switch (words[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            try {
                return new MarkCommand(Integer.parseInt(words[1]) - 1);
            } catch (NumberFormatException err) {
                throw new FelixException("Enter a number after \"mark\"");
            }
        case "unmark":
            try {
                return new UnmarkCommand(Integer.parseInt(words[1]) - 1);
            } catch (NumberFormatException err) {
                throw new FelixException("Enter a number after \"unmark\"");
            }
        case "delete":
            try {
                return new DeleteCommand(Integer.parseInt(words[1]) - 1);
            } catch (NumberFormatException err) {
                throw new FelixException("Enter a number after \"delete\"");
            }
        case "todo":
        case "deadline":
        case "event":
            return new AddTaskCommand(this.generateTask(words));
        case "find":
            try {
                return new FindCommand(words[1]);
            } catch (IndexOutOfBoundsException err) {
                throw new FelixException("keyword cannot be empty");
            }
        case "update":
            try {
                String[] updateParams = words[1].split(" ", 2);
                return new UpdateTaskCommand(Integer.parseInt(updateParams[0]) - 1,
                        updateParams[1]);
            } catch (NumberFormatException err) {
                throw new FelixException("Enter a number after \"update\"");
            } catch (IndexOutOfBoundsException e) {
                throw new FelixException("Insufficient parameters provided");
            }
        default:
            throw new FelixException("Unrecognised command");
        }
    }

    private Task generateTask(String[] words) throws FelixException {
        Task task;
        if (words[0].equals("todo")) {
            try {
                task = new ToDo(words[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new FelixException("The description of a todo cannot be empty");
            }
        } else if (words[0].equals("deadline")) {
            try {
                String[] remainder = words[1].split(" /by ");
                task = new Deadline(remainder[0], remainder[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new FelixException("Command does not follow this format: "
                        + "deadline {description} by {end_datetime}");
            } catch (DateTimeParseException e) {
                throw new FelixException("datetime for deadline is not in the format \"yyyy-MM-dd HHmm\"");
            }
        } else {
            try {
                String[] remainder = words[1].split(" /from | /to ");
                task = new Event(remainder[0], remainder[1], remainder[2]);
            } catch (IndexOutOfBoundsException e) {
                throw new FelixException("Command does not follow this format: event {description}"
                        + " /from {start_datetime} /to {end_datetime}");
            } catch (DateTimeParseException e) {
                throw new FelixException("datetime not in the format \"yyyy-MM-dd HHmm\"");
            }
        }
        return task;
    }
}
