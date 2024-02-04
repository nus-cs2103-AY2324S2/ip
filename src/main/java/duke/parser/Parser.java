package duke.parser;

import duke.core.Duke;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * The `Parser` class handles the parsing of user commands in Duke.
 * It interprets commands and converts them into corresponding Duke actions.
 */
public class Parser {

    /**
     * The command to be parsed.
     */
    protected String command;

    /**
     * Constructs a `Parser` with the given command.
     *
     * @param command The command to be parsed.
     */
    public Parser(String command) {
        this.command = command;
    }

    /**
     * Parses the add command and returns the corresponding Task.
     *
     * @return The Task created based on the add command.
     * @throws DukeException If the command is in an invalid format.
     */
    public Task parseAdd() throws DukeException {
        if (command.toUpperCase().startsWith(Duke.Command.TODO.name())) {
            return parseTodo();
        } else if (command.toUpperCase().startsWith(Duke.Command.DEADLINE.name())) {
            return parseDeadline();
        } else if (command.toUpperCase().startsWith(Duke.Command.EVENT.name())) {
            return parseEvent();
        } else {
            throw new DukeException("Invalid format. Please use 'todo', 'deadline', or 'event'.");
        }
    }

    /**
     * Parses the delete command and returns the index of the task to be deleted.
     *
     * @return The index of the task to be deleted.
     * @throws DukeException If the command is in an invalid format.
     */
    public int parseDelete() throws DukeException {
        String[] part = command.split(" ");

        if (part.length == 2) {
            String index = part[1].trim();

            try {
                return Integer.parseInt(index) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid format. Please use integers only.");
            }
        } else {
            throw new DukeException("Invalid format. Please use 'delete <index>'.");
        }
    }

    /**
     * Parses the mark command and returns the index of the task to be marked as done.
     *
     * @return The index of the task to be marked as done.
     * @throws DukeException If the command is in an invalid format.
     */
    public int parseMark() throws DukeException {
        String[] part = command.split(" ");

        if (part.length == 2) {
            String index = part[1].trim();
            try {
                return Integer.parseInt(index) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid format. Please use integers only.");
            }
        } else {
            throw new DukeException("Invalid format. Please use 'mark <index>'.");
        }
    }

    /**
     * Parses the unmark command and returns the index of the task to be marked as not done.
     *
     * @return The index of the task to be marked as not done.
     * @throws DukeException If the command is in an invalid format.
     */
    public int parseUnMark() throws DukeException {
        String[] part = command.split(" ");

        if (part.length == 2) {
            String index = part[1].trim();
            try {
                return Integer.parseInt(index) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid format. Please use integers only.");
            }
        } else {
            throw new DukeException("Invalid format. Please use 'unmark <index>'.");
        }
    }

    /**
     * Parses the event command and returns the corresponding Event.
     *
     * @return The Event created based on the event command.
     * @throws DukeException If the command is in an invalid format.
     */
    public Event parseEvent() throws DukeException {
        String input = command.substring(Duke.Command.EVENT.name().length()).trim();
        int byIndex = input.indexOf(" /from ");
        if (byIndex != 0 && byIndex != -1) {
            String description = input.substring(0, byIndex).trim();
            String fromTo = input.substring(byIndex + 6).trim();
            String[] part = fromTo.split(" /to ", 2);
            if (part.length == 2) {
                String from = part[0].trim();
                String to = part[1].trim();
                return new Event(description, from, to);
            }
        }
        throw new DukeException("Invalid format. Please use 'event <description> /from <datetime> /to <datetime>'.\n" +
                " Datetime format: <yyyy-MM-dd HH:mm>.");
    }

    /**
     * Parses the todo command and returns the corresponding Todo.
     *
     * @return The Todo created based on the todo command.
     * @throws DukeException If the command is in an invalid format.
     */
    public Todo parseTodo() throws DukeException {
        String input = command.substring(Duke.Command.TODO.name().length()).trim();
        String description = input.trim();

        if (input.isEmpty()) {
            throw new DukeException("Invalid format. Please use 'todo <description>'.");
        }

        return new Todo(description);
    }

    /**
     * Parses the deadline command and returns the corresponding Deadline.
     *
     * @return The Deadline created based on the deadline command.
     * @throws DukeException If the command is in an invalid format.
     */
    public Deadline parseDeadline() throws DukeException {
        String input = command.substring(Duke.Command.DEADLINE.name().length()).trim();
        int byIndex = input.indexOf(" /by ");
        if (byIndex != 0 && byIndex != -1) {
            String description = input.substring(0, byIndex).trim();
            String by = input.substring(byIndex + 4).trim();
            return new Deadline(description, by);
        } else {
            throw new DukeException("Invalid format. Please use 'deadline <description> /by <datetime>'.\n" +
                    " Datetime format: <yyyy-MM-dd HH:mm>.");
        }
    }
}
