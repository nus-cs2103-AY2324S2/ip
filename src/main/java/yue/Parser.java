package yue;

import yue.command.Command;
import yue.command.TodoCommand;
import yue.command.EventCommand;
import yue.command.ExitCommand;
import yue.command.ListCommand;
import yue.command.MarkCommand;
import yue.command.UnmarkCommand;
import yue.command.DeleteCommand;
import yue.command.DeadlineCommand;
import yue.command.FindCommand;
import yue.command.DetectCommand;
import yue.command.GreetCommand;


import java.util.Arrays;

public class Parser {
    String input = null;

    public Parser(String input) {
        this.input = input;

    }

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input to be parsed.
     * @return The command corresponding to the user input.
     * @throws YueException If the input cannot be parsed or an invalid command is entered.
     */
    public static Command parse(String input) throws YueException {
        if (input == null || input.isBlank()) {
            throw new YueException("OOPS!!! Please enter a valid command.");
        }
        input = input.trim();
        String[] parts = input.split(" ", 2);
        assert parts.length >= 1 : "Input string must contain at least one part";
        String command = parts[0].toLowerCase().trim();

        switch (command) {
            case "hi":
                return new GreetCommand();
            case "bye":
                return new ExitCommand();
            case "detect":
                return new DetectCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return parseMarkCommand(parts);
            case "unmark":
                return parseUnmarkCommand(parts);
            case "delete":
                return parseDeleteCommand(parts);
            case "todo":
                return parseTodoCommand(parts);
            case "deadline":
                return parseDeadlineCommand(parts);
            case "event":
                return parseEventCommand(parts);
            case "find":
                return parseFindCommand(parts);
            default:
                throw new YueException("OOPS!!! I'm sorry, I don't know what that means :-(");
        }
    }



    /**
     * Parses the user input to create a MarkCommand.
     *
     * @param parts The input split into parts.
     * @return The MarkCommand corresponding to the user input.
     * @throws YueException If the input cannot be parsed or an invalid command is entered.
     */
    private static Command parseMarkCommand(String[] parts) throws YueException {
        assert parts.length >= 2 : "Input parts must contain at least two elements";

        if (parts.length < 2) {
            throw new YueException("OOPS!!! Please specify the task index to mark as done.");
        }

        try {
            int index = Integer.parseInt(parts[1].trim());
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new YueException("OOPS!!! Please enter a valid task index to mark as done.");
        }
    }

    /**
     * Parses the user input to create a FindCommand.
     *
     * @param parts The input split into parts.
     * @return The FindCommand corresponding to the user input.
     * @throws YueException If the input cannot be parsed or an invalid command is entered.
     */
    private static Command parseFindCommand(String[] parts) throws YueException {
        assert parts.length >= 2 : "Input parts must contain at least two elements";

        if (parts.length < 2) {
            throw new YueException("OOPS!!! Please specify the keyword to find.");
        }

        return new FindCommand(parts[1]);

    }

    /**
     * Parses the user input to create an UnmarkCommand.
     *
     * @param parts The input split into parts.
     * @return The UnmarkCommand corresponding to the user input.
     * @throws YueException If the input cannot be parsed or an invalid command is entered.
     */
    private static Command parseUnmarkCommand(String[] parts) throws YueException {
        if (parts.length < 2) {
            throw new YueException("OOPS!!! Please specify the task index to mark as not done.");
        }

        try {
            int index = Integer.parseInt(parts[1].trim());
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new YueException("OOPS!!! Please enter a valid task index to mark as not done.");
        }
    }


    /**
     * Parses the user input to create a DeleteCommand.
     *
     * @param parts The input split into parts.
     * @return The DeleteCommand corresponding to the user input.
     * @throws YueException If the input cannot be parsed or an invalid command is entered.
     */
    private static Command parseDeleteCommand(String[] parts) throws YueException {
        if (parts.length < 2) {
            throw new YueException("OOPS!!! Please specify the task index to delete.");
        }
        try {
            int index = Integer.parseInt(parts[1].trim());
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new YueException("OOPS!!! Please enter a valid task index to delete.");
        }
    }


    /**
     * Parses the user input to create a TodoCommand.
     *
     * @param parts The input split into parts.
     * @return The TodoCommand corresponding to the user input.
     * @throws YueException If the input cannot be parsed or an invalid command is entered.
     */
    private static Command parseTodoCommand(String[] parts) throws YueException {
        if (parts.length < 2) {
            throw new YueException("OOPS!!! The description of a todo task cannot be empty.\n" +
                    "The correct format of input should be: todo <your task>.");

        }

        String description = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
        return new TodoCommand(description);
    }


    /**
     * Parses the user input to create a DeadlineCommand.
     *
     * @param parts The input split into parts.
     * @return The DeadlineCommand corresponding to the user input.
     * @throws YueException If the input cannot be parsed or an invalid command is entered.
     */
    private static Command parseDeadlineCommand(String[] parts) throws YueException {

        if (parts.length < 2) {
            throw new YueException("OOPS!!! The description of a deadline task cannot be empty.\n"
            +" The correct format of input should be: \n deadline <description> /by <deadline>\n"
            +"the time format should be: dd/mm/yyyy or yyyy-mm-dd.");
        }
        String[] details = parts[1].split("/by", 2);
        if (details.length < 2) {
            throw new YueException("OOPS!!! The deadline of a deadline task cannot be empty.\n"
                    + " The correct format of input should be: \n deadline <description> /by <deadline>\n"
                    +"the time format should be: dd/mm/yyyy or yyyy-mm-dd.");
        }

        return new DeadlineCommand(details[0], details[1].trim());
    }


    /**
     * Parses the user input to create an EventCommand.
     *
     * @param parts The input split into parts.
     * @return The EventCommand corresponding to the user input.
     * @throws YueException If the input cannot be parsed or an invalid command is entered.
     */
    private static Command parseEventCommand(String[] parts) throws YueException {
        String task = null;
        String from = null;
        String to = null;
        String correctFormat = " The correct format of input should be:\n event <description> /from <start time> /to <end time>\n"
                +"The time format should be: dd/mm/yyyy HH:mm or yyyy-mm-dd or dd/mm/yyyy";
        if (parts.length < 2) {
            throw new YueException("OOPS!!! The description of an event task cannot be empty.\n" + correctFormat);
        }
        String[] split = parts[1].split("/from", 2);
        if (split.length < 2) {
            throw new YueException("OOPS!!! The time of an event task cannot be empty.\n" + correctFormat);
        }
        if (split.length == 2) {
            task = split[0];
            String[] details = split[1].split("/to", 2);
            if (details.length == 2) {
                from = details[0].trim();
                to = details[1].trim();
            } else {
                throw new YueException("OOPS!!! The end time of an event task cannot be empty.\n" + correctFormat);
            }
        }
        return new EventCommand(task, from, to);
    }
}
