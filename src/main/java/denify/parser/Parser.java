package denify.parser;

import denify.core.Command;
import denify.exception.DenifyException;
import denify.task.Deadline;
import denify.task.Event;
import denify.task.Todo;

/**
 * The `Parser` class handles the parsing of user commands in Denify.
 * It interprets commands and converts them into corresponding Denify actions.
 */
public class Parser {
    /**
     * The command to be parsed.
     */
    private final String rawInput;
    /**
     * Constructs a `Parser` with the given command.
     *
     * @param rawInput The command to be parsed.
     */
    public Parser(String rawInput) {
        assert rawInput != null : "Raw input must not be null";
        this.rawInput = rawInput;
    }
    /**
     * Parses the command and returns the corresponding Command enum value.
     *
     * @return The Command enum value representing the parsed command.
     * @throws DenifyException If the command is not recognized or is in an invalid format.
     */
    public Command parseCommand() throws DenifyException {
        String[] parts = rawInput.split(" ", 2);
        String commandString = parts[0].trim().toUpperCase();
        for (Command cmd : Command.values()) {
            if (cmd.name().equals(commandString)) {
                return cmd;
            }
        }
        throw new DenifyException("Invalid command. Please use a valid command such as 'bye', 'list', 'find', "
                + "'delete', 'mark', 'unmark', 'event', 'todo', or 'deadline'.");
    }
    /**
     * Parses the "bye" command.
     *
     * @throws DenifyException If the input is not a valid "bye" command.
     */
    public void parseBye() throws DenifyException {
        String[] parts = rawInput.split(" ");
        if (parts.length != 1 || !parts[0].equalsIgnoreCase(Command.BYE.name())) {
            throw new DenifyException("Invalid format. Please use 'bye'.");
        }
    }
    /**
     * Parses the "list" command.
     *
     * @throws DenifyException If the input is not a valid "list" command.
     */
    public void parseList() throws DenifyException {
        String[] parts = rawInput.split(" ");
        if (parts.length != 1 || !parts[0].equalsIgnoreCase(Command.LIST.name())) {
            throw new DenifyException("Invalid format. Please use 'list'.");
        }
    }
    /**
     * Parses the "find" command to search for tasks with a keyword.
     *
     * @return The keyword to search for.
     * @throws DenifyException If the command format is invalid.
     */
    public String parseFind() throws DenifyException {
        String[] parts = rawInput.split(" ", 2);
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new DenifyException("Invalid format. Please use 'find <keyword>'.");
        }
        return parts[1].trim();
    }
    /**
     * Parses the delete command and returns the index of the task to be deleted.
     *
     * @return The index of the task to be deleted.
     * @throws DenifyException If the command is in an invalid format.
     */
    public int parseDelete() throws DenifyException {
        String[] parts = rawInput.split(" ");
        if (parts.length == 2) {
            String index = parts[1].trim();
            try {
                return Integer.parseInt(index) - 1;
            } catch (NumberFormatException e) {
                throw new DenifyException("Invalid format. Please use integers only.");
            }
        } else {
            throw new DenifyException("Invalid format. Please use 'delete <index>'.");
        }
    }
    /**
     * Parses the mark command and returns the index of the task to be marked as done.
     *
     * @return The index of the task to be marked as done.
     * @throws DenifyException If the command is in an invalid format.
     */
    public int parseMark() throws DenifyException {
        String[] part = rawInput.split(" ");
        if (part.length == 2) {
            String index = part[1].trim();
            try {
                return Integer.parseInt(index) - 1;
            } catch (NumberFormatException e) {
                throw new DenifyException("Invalid format. Please use integers only.");
            }
        } else {
            throw new DenifyException("Invalid format. Please use 'mark <index>'.");
        }
    }
    /**
     * Parses the unmark command and returns the index of the task to be marked as not done.
     *
     * @return The index of the task to be marked as not done.
     * @throws DenifyException If the command is in an invalid format.
     */
    public int parseUnmark() throws DenifyException {
        String[] part = rawInput.split(" ");
        if (part.length == 2) {
            String index = part[1].trim();
            try {
                return Integer.parseInt(index) - 1;
            } catch (NumberFormatException e) {
                throw new DenifyException("Invalid format. Please use integers only.");
            }
        } else {
            throw new DenifyException("Invalid format. Please use 'unmark <index>'.");
        }
    }
    /**
     * Parses the event command and returns the corresponding Event.
     *
     * @return The Event created based on the event command.
     * @throws DenifyException If the command is in an invalid format.
     */
    public Event parseEvent() throws DenifyException {
        String input = rawInput.substring(Command.EVENT.name().length()).trim();
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
        throw new DenifyException("Invalid format. Please use 'event <description> /from <datetime> /to <datetime>'.\n"
                + " Datetime format: <yyyy-MM-dd HH:mm>.");
    }
    /**
     * Parses the todo command and returns the corresponding Todo.
     *
     * @return The Todo created based on the todo command.
     * @throws DenifyException If the command is in an invalid format.
     */
    public Todo parseTodo() throws DenifyException {
        String input = rawInput.substring(Command.TODO.name().length()).trim();
        String description = input.trim();
        if (input.isEmpty()) {
            throw new DenifyException("Invalid format. Please use 'todo <description>'.");
        }
        return new Todo(description);
    }
    /**
     * Parses the deadline command and returns the corresponding Deadline.
     *
     * @return The Deadline created based on the deadline command.
     * @throws DenifyException If the command is in an invalid format.
     */
    public Deadline parseDeadline() throws DenifyException {
        String input = rawInput.substring(Command.DEADLINE.name().length()).trim();
        int byIndex = input.indexOf(" /by ");
        if (byIndex != 0 && byIndex != -1) {
            String description = input.substring(0, byIndex).trim();
            String by = input.substring(byIndex + 4).trim();
            return new Deadline(description, by);
        } else {
            throw new DenifyException("Invalid format. Please use 'deadline <description> /by <datetime>'.\n"
                    + " Datetime format: <yyyy-MM-dd HH:mm>.");
        }
    }
}
