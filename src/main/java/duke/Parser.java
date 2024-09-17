package duke;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.CompleteTaskCommand;
import duke.command.DeleteTaskCommand;
import duke.command.EditTaskCommand;
import duke.command.ExitTaskCommand;
import duke.command.FindTaskCommand;
import duke.command.ListTasksCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Parser class responsible for interpreting user input and translating it
 * into commands that can be executed by the Duke application.
 */
public class Parser {

    /**
     * Parses the user input into a Command object representing the action to be taken.
     * This method interprets the user input and translates it into specific
     * commands understood by the Duke application, such as adding, deleting, or completing tasks.
     *
     * @param userInput The user input string to be parsed.
     * @return Command object representing the action to be taken.
     * @throws JamieException If the user input cannot be understood or is invalid.
     */
    public static Command parse(String userInput) throws JamieException {
        String[] words = userInput.trim().split("\\s+", 2); // Split by whitespace, limit to 2 parts
        String commandWord = words[0].toLowerCase();
        String commandArgs = words.length > 1 ? words[1] : "";

        switch (commandWord) {
        case "bye":
            return new ExitTaskCommand();
        case "list":
            return new ListTasksCommand();
        case "complete":
            return parseCompleteTaskCommand(commandArgs);
        case "delete":
            return parseDeleteTaskCommand(commandArgs);
        case "todo":
            return parseAddTodoCommand(commandArgs);
        case "deadline":
            return parseAddDeadlineCommand(commandArgs);
        case "event":
            return parseAddEventCommand(commandArgs);
        case "find":
            return new FindTaskCommand(commandArgs);
        case "edit":
            return parseEditTaskCommand(commandArgs);
        default:
            throw new JamieException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Command parseCompleteTaskCommand(String args) throws JamieException {
        try {
            int index = Integer.parseInt(args);
            return new CompleteTaskCommand(index);
        } catch (NumberFormatException e) {
            throw new JamieException("Invalid task number for completion.");
        }
    }

    private static Command parseDeleteTaskCommand(String args) throws JamieException {
        try {
            int index = Integer.parseInt(args);
            return new DeleteTaskCommand(index);
        } catch (NumberFormatException e) {
            throw new JamieException("Invalid task number for deletion.");
        }
    }

    private static Command parseAddTodoCommand(String details) throws JamieException {
        if (details.isBlank()) {
            throw new JamieException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new AddTaskCommand(new ToDo(details));
    }

    private static Command parseAddDeadlineCommand(String details) throws JamieException {
        String[] parts = details.split(" /by ", 2);
        if (parts.length < 2 || parts[0].isBlank() || parts[1].isBlank()) {
            throw new JamieException("OOPS!!! Invalid format for deadline. "
                    + "Please use: deadline <description> /by <date>");
        }
        return new AddTaskCommand(new Deadline(parts[0], parts[1]));
    }

    private static Command parseAddEventCommand(String details) throws JamieException {
        String[] eventDetails = details.split(" /from ", 2);
        if (eventDetails.length < 2 || eventDetails[0].isBlank() || eventDetails[1].isBlank()) {
            throw new JamieException("OOPS!!! Invalid format for event. "
                    + "Please use: event <description> /from <date/time> /to <data/time>");
        }
        String[] eventTiming = eventDetails[1].split(" /to ");
        return new AddTaskCommand(new Event(eventDetails[0], eventTiming[0], eventTiming[1]));
    }

    private static Command parseEditTaskCommand(String details) throws JamieException {
        String[] editDetails = details.split(" ", 3);
        if (editDetails.length < 3) {
            throw new JamieException("OOPS!!! Invalid format for editing a task. "
                    + "Please use: edit <index> <attribute> <new value>");
        }
        int index = Integer.parseInt(editDetails[0]);
        String attribute = editDetails[1];
        String newValue = editDetails[2];
        return new EditTaskCommand(index, attribute, newValue);
    }
}
