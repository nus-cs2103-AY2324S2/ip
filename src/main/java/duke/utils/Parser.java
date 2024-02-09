package duke.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandException;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnknownCommandException;
import duke.command.UnmarkCommand;
import duke.storage.LoadException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Class to parse information from one type to another.
 * Types are bound to custom application types.
 */
public class Parser {
    /**
     * Returns a Command that corresponds to given input.
     *
     * @param input User input to application.
     * @return Command to be executed.
     * @throws CommandException Exception when input does not match with any known Command format.
     */
    public static Command parseInput(String input) throws CommandException {
        input = input.stripLeading();

        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("todo")) {
            return new ToDoCommand(input.substring(4));
        } else if (input.startsWith("deadline")) {
            return new DeadlineCommand(input.substring(8));
        } else if (input.startsWith("event")) {
            return new EventCommand(input.substring(5));
        } else if (input.startsWith("mark")) {
            return new MarkCommand(input.substring(4));
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input.substring(6));
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input.substring(6));
        } else if (input.startsWith("find ")) {
            // Account for space since parameters will not be trimmed.
            return new FindCommand(input.substring(5));
        }

        throw new UnknownCommandException();
    }

    /**
     * Returns a Task based on given String.
     * String should follow convention based on respective subclass.
     *
     * @param taskData Data String of task.
     * @return Task specified by given String.
     * @throws LoadException Exception when data cannot be loaded due to incompatible format.
     */
    public static Task parseData(String taskData) throws LoadException {
        try {
            String[] tokens = taskData.split("\\|", 3);
            Task task = null;
            boolean marked = Parser.parseInteger(tokens[1]) > 0;
            switch (tokens[0].trim()) {
            case "T":
                task = parseToDoInput(tokens[2]);
                break;
            case "D":
                task = parseDeadlineInput(tokens[2]);
                break;
            case "E":
                task = parseEventInput(tokens[2]);
                break;
            default:
                break;
            }

            if (task != null) {
                if (marked) {
                    task.markAsDone();
                }
                return task;
            }
        } catch (Exception e) {
            throw new LoadException("Task cannot be loaded due to incorrect format. Skipping.");
        }
        throw new LoadException("Task cannot be loaded due to incorrect format. Skipping.");
    }

    /**
     * Returns a ToDo task based on given input.
     *
     * @param input Input data of ToDo task.
     * @return ToDo task with given input data.
     * @throws FormatException Exception when input does not comply to format.
     */
    public static ToDo parseToDoInput(String input) throws FormatException {
        String description = input.trim();
        if (description.isEmpty()) {
            throw new FormatException("Description cannot be empty.");
        }
        return new ToDo(description);
    }

    /**
     * Returns a new Deadline task based on given input.
     *
     * @param input Input data of Deadline task.
     * @return Deadline task with given input data.
     * @throws FormatException Exception when input does not comply to format.
     */
    public static Deadline parseDeadlineInput(String input) throws FormatException {
        String[] tokens = input.trim().split("/by", 2);
        String description = tokens[0].trim();
        if (description.isEmpty()) {
            throw new FormatException("Description cannot be empty.");
        }
        try {
            LocalDateTime by = LocalDateTime.parse(tokens[1].trim(), Task.INPUT_DATETIME_FORMAT);
            return new Deadline(description, by);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new FormatException("/by format is incorrect");
        }
    }

    /**
     * Returns a new Deadline task based on given input.
     *
     * @param input Input data of Event task.
     * @return Event task with given input data.
     * @throws FormatException Exception when input does not comply to format.
     */
    public static Event parseEventInput(String input) throws FormatException {
        String[] tokens = input.trim().split("/from", 2);
        String description = tokens[0].trim();
        if (description.isEmpty()) {
            throw new FormatException("Description cannot be empty.");
        }
        try {
            String[] fromToInput = tokens[1].trim().split("/to", 2);
            LocalDateTime from = LocalDateTime.parse(fromToInput[0].trim(), Task.INPUT_DATETIME_FORMAT);
            LocalDateTime to = LocalDateTime.parse(fromToInput[1].trim(), Task.INPUT_DATETIME_FORMAT);
            return new Event(description, from, to);
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new FormatException("/from /to format is incorrect.");
        }
    }

    /**
     * Returns index based on given input.
     *
     * @param input String format of index.
     * @return Index as Integer format.
     * @throws NumberFormatException Exception when index passed is cannot be converted to an Integer.
     */
    public static int parseInteger(String input) throws NumberFormatException {
        return Integer.parseInt(input.trim());
    }
}
