package duke.utils;

import java.time.LocalDateTime;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandException;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
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
            String description = "";
            boolean marked = Integer.parseInt(tokens[1].trim()) > 0;
            switch (tokens[0].trim()) {
            case "T":
                description = tokens[2].trim();
                if (description.isEmpty()) {
                    throw new LoadException("Description cannot be empty.");
                }
                task = new ToDo(description);
                break;
            case "D":
                tokens = tokens[2].trim().split("/by");
                description = tokens[0].trim();
                if (description.isEmpty()) {
                    throw new LoadException("Description cannot be empty.");
                }
                LocalDateTime by = LocalDateTime.parse(tokens[1].trim(), Task.INPUT_DATETIME_FORMAT);
                task = new Deadline(description, by);
                break;
            case "E":
                tokens = tokens[2].split("/from");
                description = tokens[0].trim();
                if (description.isEmpty()) {
                    throw new LoadException("Description cannot be empty.");
                }
                tokens = tokens[1].split("/to");
                LocalDateTime from = LocalDateTime.parse(tokens[0].trim(), Task.INPUT_DATETIME_FORMAT);
                LocalDateTime to = LocalDateTime.parse(tokens[1].trim(), Task.INPUT_DATETIME_FORMAT);
                task = new Event(description, from, to);
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
}
