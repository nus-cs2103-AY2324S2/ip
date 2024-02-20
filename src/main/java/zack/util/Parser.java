package zack.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import zack.Zack;
import zack.ZackException;
import zack.commands.AddTaskCommand;
import zack.commands.ByeCommand;
import zack.commands.Command;
import zack.commands.DateCommand;
import zack.commands.DeleteCommand;
import zack.commands.FindCommand;
import zack.commands.ListCommand;
import zack.commands.MarkCommand;
import zack.commands.SortCommand;

/**
 * The Parser class is responsible for parsing user input and converting it into
 * executable commands for the Zack task management application. It handles the
 * interpretation of various command types and their associated parameters.
 *
 * This class provides a method to parse user input and determine the appropriate
 * command to be executed based on the input provided by the user.
 *
 * @see Command
 * @see ZackException
 */
public class Parser {

    /**
     * Parses the user input into a command object.
     * This method determines the type of command based on the input and delegates
     * the command creation to the specific method responsible for handling that command type.
     *
     * @param input The full user input string.
     * @return The command to be executed.
     * @throws ZackException If the command is unknown or the input is invalid.
     */
    public static Command parse(String input) throws ZackException {
        assert input != null && !input.isEmpty() : "Input to parse cannot be null or empty";

        String[] sections = input.split(" ", 2);
        String commandWord = sections[0].toLowerCase();

        try {
            Zack.TaskType taskType = Zack.TaskType.valueOf(commandWord.toUpperCase());
            return dispatchCommand(taskType, sections);
        } catch (IllegalArgumentException e) {
            throw new ZackException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Dispatches the command based on the task type.
     * This method routes the input to the appropriate handler based on the determined
     * task type. It supports various commands such as adding tasks, marking tasks as done,
     * listing tasks, and more.
     *
     * @param taskType The type of task derived from the user input.
     * @param sections The user input split into command and arguments.
     * @return The specific command to execute.
     * @throws ZackException If the command cannot be processed or is invalid.
     */
    private static Command dispatchCommand(Zack.TaskType taskType, String[] sections) throws ZackException {
        switch (taskType) {
        case BYE:
            return processByeCommand(sections);
        case MARK:
        case UNMARK:
            return processMarkCommand(sections, taskType);
        case LIST:
            return processListCommand(sections);
        case TODO:
        case DEADLINE:
        case EVENT:
            return processAddTaskCommand(sections, taskType);
        case DELETE:
            return processDeleteCommand(sections);
        case DATE:
            return processDateCommand(sections);
        case FIND:
            return processFindCommand(sections);
        case SORT:
            return processSortCommand(sections);
        default:
            throw new ZackException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Processes the 'bye' command.
     * This method creates a ByeCommand if the input is valid. It checks that no additional
     * arguments are provided with the 'bye' command.
     *
     * @param sections The user input split into command and arguments.
     * @return A ByeCommand to terminate the application.
     * @throws ZackException If any arguments are provided with the 'bye' command.
     */
    private static Command processByeCommand(String[] sections) throws ZackException {
        if (sections.length > 1) {
            throw new ZackException("Please only type 'bye' if you wana exit!");
        }
        return new ByeCommand();
    }

    /**
     * Processes the 'mark' or 'unmark' commands.
     * This method creates a MarkCommand based on the input provided. It checks the validity
     * of the task index provided by the user.
     *
     * @param sections The user input split into command and arguments.
     * @param taskType The type of task, either MARK or UNMARK.
     * @return A MarkCommand to mark a task as done or not done.
     * @throws ZackException If the task index is invalid or not provided.
     */
    private static Command processMarkCommand(String[] sections, Zack.TaskType taskType)
            throws ZackException {
        if (sections.length < 2) {
            throw new ZackException("You gotta provide the task index for me to mark or unmark!");
        }
        int index = parseIndex(sections[1]);
        boolean isDone = taskType == Zack.TaskType.MARK;
        return new MarkCommand(index, isDone);
    }

    /**
     * Processes the 'list' command.
     * This method creates a ListCommand to list all tasks. It checks that no additional
     * arguments are provided with the 'list' command.
     *
     * @param sections The user input split into command and arguments.
     * @return A ListCommand to display all tasks.
     * @throws ZackException If any arguments are provided with the 'list' command.
     */
    private static Command processListCommand(String[] sections) throws ZackException {
        if (sections.length > 1) {
            throw new ZackException("Please only type 'list' to view the list of tasks!");
        }
        return new ListCommand();
    }

    /**
     * Processes task addition commands ('todo', 'deadline', 'event').
     * This method creates an AddTaskCommand with the appropriate task type and details.
     *
     * @param sections The user input split into command and arguments.
     * @param taskType The type of task to add (TODO, DEADLINE, EVENT).
     * @return An AddTaskCommand to add a new task.
     * @throws ZackException If the task description is empty.
     */
    private static Command processAddTaskCommand(String[] sections, Zack.TaskType taskType) throws ZackException {
        if (sections.length < 2) {
            throw new ZackException("Hey now, the description of a " + sections[0] + " can't be empty.");
        }
        return new AddTaskCommand(sections[1], taskType);
    }

    /**
     * Processes the 'delete' command.
     * This method creates a DeleteCommand to delete a specified task. It validates the
     * task index provided by the user.
     *
     * @param sections The user input split into command and arguments.
     * @return A DeleteCommand to remove a task.
     * @throws ZackException If the task index is invalid or not provided.
     */
    private static Command processDeleteCommand(String[] sections) throws ZackException {
        if (sections.length < 2) {
            throw new ZackException("You gotta provide the task index for me to delete!");
        }
        int deleteIndex = parseIndex(sections[1]);
        return new DeleteCommand(deleteIndex);
    }

    /**
     * Processes the 'date' command.
     * This method creates a DateCommand to list tasks on a specific date. It validates
     * the date format provided by the user.
     *
     * @param sections The user input split into command and arguments.
     * @return A DateCommand to list tasks by date.
     * @throws ZackException If the date format is invalid or not provided.
     */
    private static Command processDateCommand(String[] sections) throws ZackException {
        if (sections.length < 2) {
            throw new ZackException("You gotta provide a date for me man!");
        }
        LocalDate specificDate = parseDate(sections[1]);
        return new DateCommand(specificDate);
    }

    /**
     * Processes the 'find' command.
     * This method creates a FindCommand to search for tasks containing a specified keyword.
     *
     * @param sections The user input split into command and arguments.
     * @return A FindCommand to search tasks by keyword.
     * @throws ZackException If the keyword is not provided.
     */
    private static Command processFindCommand(String[] sections) throws ZackException {
        if (sections.length < 2) {
            throw new ZackException("You have to tell me what you're looking for, silly😂");
        }
        return new FindCommand(sections[1]);
    }

    private static Command processSortCommand(String[] sections) throws ZackException {
        if (sections.length < 2) {
            throw new ZackException("You might've forgotten to tell me what exactly to sort by😅");
        }
        return new SortCommand(sections[1]);
    }

    /**
     * Parses the task index from a string.
     * This method validates and converts a string argument into a task index integer.
     *
     * @param section The string containing the task index.
     * @return The parsed task index.
     * @throws ZackException If the task index is not a valid integer.
     */
    private static int parseIndex(String section) throws ZackException {
        try {
            return Integer.parseInt(section.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new ZackException("You gotta gimme a proper number for the task index!");
        }
    }

    /**
     * Parses a date from a string.
     * This method validates and converts a string argument into a LocalDate object.
     *
     * @param section The string containing the date.
     * @return The parsed LocalDate object.
     * @throws ZackException If the date format is invalid.
     */
    private static LocalDate parseDate(String section) throws ZackException {
        try {
            return LocalDate.parse(section.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new ZackException("Sorry, but your date format has to be exactly like this: yyyy-MM-dd. "
                    + "Take it up with the big man, I don't set the rules!");
        }
    }
}
