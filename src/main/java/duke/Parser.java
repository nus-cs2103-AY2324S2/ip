package duke;

import duke.Command.Command;
import duke.Command.TodoCommand;
import duke.Command.EventCommand;
import duke.Command.ExitCommand;
import duke.Command.ListCommand;
import duke.Command.MarkCommand;
import duke.Command.UnmarkCommand;
import duke.Command.DeleteCommand;
import duke.Command.DeadlineCommand;
import duke.Command.FindCommand;
import duke.Command.DetectCommand;
import duke.Command.GreetCommand;


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
     * @throws DukeException If the input cannot be parsed or an invalid command is entered.
     */
    public static Command parse(String input) throws DukeException {
        if (input == null || input.isBlank()) {
            throw new DukeException("OOPS!!! Please enter a valid command.");
        }

        String[] parts = input.split(" ", 2);
        assert parts.length >= 1 : "Input string must contain at least one part";
        String command = parts[0].toLowerCase();

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
                return new FindCommand(parts[1]);
            default:
                throw new DukeException("OOPS!!! I'm sorry, I don't know what that means :-(");
        }
    }



    /**
     * Parses the user input to create a MarkCommand.
     *
     * @param parts The input split into parts.
     * @return The MarkCommand corresponding to the user input.
     * @throws DukeException If the input cannot be parsed or an invalid command is entered.
     */
    private static Command parseMarkCommand(String[] parts) throws DukeException {
        assert parts.length >= 2 : "Input parts must contain at least two elements";

        if (parts.length < 2) {
            throw new DukeException("OOPS!!! Please specify the task index to mark as done.");
        }

        try {
            int index = Integer.parseInt(parts[1].trim());
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid task index to mark as done.");
        }
    }

    /**
     * Parses the user input to create an UnmarkCommand.
     *
     * @param parts The input split into parts.
     * @return The UnmarkCommand corresponding to the user input.
     * @throws DukeException If the input cannot be parsed or an invalid command is entered.
     */
    private static Command parseUnmarkCommand(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! Please specify the task index to mark as not done.");
        }

        try {
            int index = Integer.parseInt(parts[1].trim());
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid task index to mark as not done.");
        }
    }


    /**
     * Parses the user input to create a DeleteCommand.
     *
     * @param parts The input split into parts.
     * @return The DeleteCommand corresponding to the user input.
     * @throws DukeException If the input cannot be parsed or an invalid command is entered.
     */
    private static Command parseDeleteCommand(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! Please specify the task index to delete.");
        }
        try {
            int index = Integer.parseInt(parts[1].trim());
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please enter a valid task index to delete.");
        }
    }


    /**
     * Parses the user input to create a TodoCommand.
     *
     * @param parts The input split into parts.
     * @return The TodoCommand corresponding to the user input.
     * @throws DukeException If the input cannot be parsed or an invalid command is entered.
     */
    private static Command parseTodoCommand(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! The description of a todo task cannot be empty.\n" +
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
     * @throws DukeException If the input cannot be parsed or an invalid command is entered.
     */
    private static Command parseDeadlineCommand(String[] parts) throws DukeException {

        if (parts.length < 2) {
            throw new DukeException("OOPS!!! The description of a deadline task cannot be empty.\n"
            +" The correct format of input should be: \n deadline <your task> /by yyyy-mm-dd\n"
            +"Or: deadline <your task> /by dd/mm/yyyy");
        }

        String[] details = parts[1].split("/by", 2);
        if (details.length < 2) {
            throw new DukeException("OOPS!!! The deadline of a deadline task cannot be empty.\n"
                    + "The correct format of input should be:\n deadline <your task> /by yyyy-mm-dd\n"
                    +"Or: deadline <your task> /by dd/mm/yyyy");
        }

        return new DeadlineCommand(details[0], details[1].trim());
    }


    /**
     * Parses the user input to create an EventCommand.
     *
     * @param parts The input split into parts.
     * @return The EventCommand corresponding to the user input.
     * @throws DukeException If the input cannot be parsed or an invalid command is entered.
     */
    private static Command parseEventCommand(String[] parts) throws DukeException {
        String task = null;
        String from = null;
        String to = null;

        if (parts.length < 2) {
            throw new DukeException("OOPS!!! The time of an event task cannot be empty.\n"
                    +" The correct format of input should be:\n event <your task> /from yyyy-mm-dd /to yyyy-mm-dd\n"
                    +"Or: event <your task> /from dd/mm/yyyy HH:mm /to dd/mm/yyyy HH:mm");
        }

        String[] split = parts[1].split("/from", 2);
        if (split.length < 2) {
            throw new DukeException("OOPS!!! The time of an event task cannot be empty.\n"
                    +" The correct format of input should be:\n event <your task> /from yyyy-mm-dd /to yyyy-mm-dd\n"
                    +"Or: event <your task> /from dd/mm/yyyy HH:mm /to dd/mm/yyyy HH:mm");
        }

        if (split.length == 2) {
            task = split[0];
            String[] details = split[1].split("/to", 2);
            if (details.length == 2) {
                from = details[0].trim();
                to = details[1].trim();
            } else {
                throw new DukeException("OOPS!!! The end time of an event task cannot be empty.\n"
                        +" The correct format of input should be:\n event <your task> /from yyyy-mm-dd /to yyyy-mm-dd\n"
                        +"Or: event <your task> /from dd/mm/yyyy HH:mm /to dd/mm/yyyy HH:mm");
            }
        }
        return new EventCommand(task, from, to);
    }
}
