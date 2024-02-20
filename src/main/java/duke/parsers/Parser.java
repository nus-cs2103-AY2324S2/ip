package duke.parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.commands.CreateDeadlineCommand;
import duke.commands.CreateEventCommand;
import duke.commands.CreateTodoCommand;
import duke.commands.DeleteCommand;
import duke.commands.ErrorCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.MarkTaskCommand;
import duke.commands.SaveCommand;
import duke.commands.UnmarkTaskCommand;
import duke.exceptions.DukeException;
import duke.utils.Utils;

/**
 * Parses user input and generates corresponding Command objects.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param userInput The user input to be parsed.
     * @return The Command object corresponding to the parsed user input.
     */
    public static Command parse(String userInput) {
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String commandArguments = "";
        if (parts.length == 2) {
            commandArguments = Utils.removeExtraSpaces(parts[1]);
        }
        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return handleMarkTask(commandArguments);
        case "unmark":
            return handleUnmarkTask(commandArguments);
        case "todo":
            return handleAddTodo(commandArguments);
        case "deadline":
            return handleAddDeadline(commandArguments);
        case "event":
            return handleAddEvent(commandArguments);
        case "delete":
            return handleDelete(commandArguments);
        case "save":
            return new SaveCommand();
        case "find":
            return new FindCommand(commandArguments);
        default:
            return new HelpCommand();
        }
    }

    /**
     * Parses the task number string and returns the corresponding task index.
     *
     * @param number The string representing the task number.
     * @param type The type of task (e.g., "mark", "unmark", "delete").
     * @return The index of the task.
     * @throws DukeException If the task number is blank or cannot be parsed as an integer.
     * @throws NumberFormatException If the task number cannot be parsed as an integer.
     */
    public static int prepareTask(String number, String type) throws DukeException, NumberFormatException {
        if (number.isBlank()) {
            throw new DukeException("Please state which task you want to " + type + ".");
        }
        try {
            return Integer.parseInt(number) - 1;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    /**
     * Checks if the task argument is empty or only consist of spaces.
     *
     * @param arguments The task arguments.
     * @param taskType The task type.
     * @throws DukeException If the task arguments is empty.
     */
    public static void isTaskArgumentBlank(String arguments, String taskType) throws DukeException {
        if (arguments.isBlank()) {
            throw new DukeException("OOPS! The description of a " + taskType + " cannot be empty.");
        }
    }

    /**
     * Parses the task description string and returns a command to create a todo task.
     *
     * @param arguments The arguments provided for parsing.
     * @return The CreateTodoCommand object corresponding to the provided arguments.
     * @throws DukeException If the description of the todo task is empty.
     */
    public static Command prepareCreateTodo(String arguments) throws DukeException {
        isTaskArgumentBlank(arguments, "todo");
        return new CreateTodoCommand(arguments);
    }

    /**
     * Parses the user input to corresponding parameters and returns a command to create a deadline task.
     *
     * @param arguments The arguments provided for creating the deadline task.
     * @return The CreateDeadlineCommand object corresponding to the provided arguments.
     * @throws DukeException If the description of the deadline task is empty or if the input format is incorrect.
     * @throws DateTimeParseException If the input date and time cannot be parsed.
     */
    public static Command prepareCreateDeadline(String arguments) throws DukeException {
        isTaskArgumentBlank(arguments, "deadline");
        if (!arguments.contains("by")) {
            throw new DukeException("OOPS! 'by' keyword is missing. You are required "
                    + "to state the deadline using the 'by' keyword.");
        }
        String[] instruction = arguments.split(" by ", 2);
        if (instruction.length < 2) {
            if (arguments.startsWith("by")) {
                throw new DukeException("OOPS! You forget to write the task description. "
                        + "Please follow this format: '<task_description> by <deadline>' "
                        + "in yyyy-mm-dd HHmm 24-hr format");
            }
            throw new DukeException("OOPS! You forget to write do the task by when");
        }
        String description = instruction[0];
        LocalDateTime deadline = Utils.convertToLocalDateTime(instruction[1]);
        return new CreateDeadlineCommand(description, deadline);
    }

    /**
     * Parses the user input to corresponding parameters and returns a command to create an event task.
     *
     * @param arguments The arguments provided for creating the event task.
     * @return The CreateEventCommand object corresponding to the provided arguments.
     * @throws DukeException If the description of the event task is empty or if the input format is incorrect.
     * @throws DateTimeParseException If the input start and end time cannot be parsed.
     */
    public static Command prepareCreateEvent(String arguments) throws DukeException {
        isTaskArgumentBlank(arguments, "event");
        if (!arguments.contains("from") || !arguments.contains("to")) {
            throw new DukeException("OOPS! 'from' and/or 'to' keywords are missing. You are required to "
                    + "state the starting and ending time using these two keywords.");
        }
        String[] instruction = arguments.split(" from ", 2);
        if (instruction.length < 2) {
            if (arguments.startsWith("from")) {
                throw new DukeException("OOPS! You forget to write the task description. "
                        + "Please follow this format: '<task_description> from <start_time> to <end_time>' "
                        + "in yyyy-mm-dd HHmm 24-hr format ");
            }
        } else if (instruction.length < 3 && instruction[1].startsWith("to")) {
            throw new DukeException("OOPS! You forget to write the starting time of this event.");
        }
        String description = instruction[0];
        String[] subInstruction = instruction[1].split(" to ", 2);
        if (subInstruction.length < 2) {
            throw new DukeException("OOPS! You forget to write the ending time of this event.");
        }
        LocalDateTime startTime = Utils.convertToLocalDateTime(subInstruction[0]);
        LocalDateTime endTime = Utils.convertToLocalDateTime(subInstruction[1]);
        if (!startTime.isBefore(endTime)) {
            throw new DukeException("The start time of the event has to be before the end time.");
        }
        return new CreateEventCommand(description, startTime, endTime);
    }

    public static Command handleMarkTask(String commandArguments) {
        try {
            return new MarkTaskCommand(prepareTask(commandArguments, "mark"));
        } catch (DukeException e) {
            return new ErrorCommand(e.getMessage());
        } catch (NumberFormatException e) {
            return new ErrorCommand("Unable to parse the input as an integer. "
                    + "Please put a number after mark.");
        }
    }

    public static Command handleUnmarkTask(String commandArguments) {
        try {
            return new UnmarkTaskCommand(prepareTask(commandArguments, "unmark"));
        } catch (DukeException e) {
            return new ErrorCommand(e.getMessage());
        } catch (NumberFormatException e) {
            return new ErrorCommand("Unable to parse the input as an integer. "
                    + "Please put a number after unmark.");
        }
    }

    public static Command handleAddTodo(String commandArguments) {
        try {
            return prepareCreateTodo(commandArguments);
        } catch (DukeException e) {
            return new ErrorCommand(e.getMessage());
        }
    }

    public static Command handleAddDeadline(String commandArguments) {
        try {
            return prepareCreateDeadline(commandArguments);
        } catch (DukeException e) {
            return new ErrorCommand(e.getMessage());
        } catch (DateTimeParseException e) {
            return new ErrorCommand("OPPS! The format for the inputted deadline is not accepted here."
                    + " Please follow this format: 'yyyy-MM-dd HHmm' when you are creating the task.");
        }
    }

    public static Command handleAddEvent(String commandArguments) {
        try {
            return prepareCreateEvent(commandArguments);
        } catch (DukeException e) {
            return new ErrorCommand(e.getMessage());
        } catch (DateTimeParseException e) {
            return new ErrorCommand("OPPS! The format for the inputted start and end time is not "
                    + "accepted here. Please follow this format: 'yyyy-MM-dd HHmm' "
                    + "when you are creating the task.");
        }
    }

    public static Command handleDelete(String commandArguments) {
        try {
            return new DeleteCommand(prepareTask(commandArguments, "delete"));
        } catch (DukeException e) {
            return new ErrorCommand(e.getMessage());
        } catch (NumberFormatException e) {
            return new ErrorCommand("Unable to parse the input as an integer. "
                    + "Please put a number after 'delete'.");
        }
    }
}

