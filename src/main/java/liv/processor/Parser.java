package liv.processor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import liv.exception.LivException;
import liv.task.Deadline;
import liv.task.Event;
import liv.container.TaskList;
import liv.task.TodoTask;

/**
 * Process the user command input.
 */
public class Parser {
    /**
     * The input format for date time.
     * Uses for deadline and event task command.
     */
    private static final DateTimeFormatter INPUT_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Creates a command object from the user input.
     * @param input The input of the user.
     * @return A command object with the type depends on the users input.
     * @throws LivException If the command is not valid.
     */
    public static Command parse(String input) throws LivException {
        if (input.equalsIgnoreCase("bye")) {
            return parseByeCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return parseListCommand();
        } else if (input.startsWith("mark")) {
            return parseMarkOrUnmarkCommand(input, true);
        } else if (input.startsWith("unmark")) {
            return parseMarkOrUnmarkCommand(input, false);
        } else if (input.startsWith("delete")) {
            return parseDeleteCommand(input);
        } else if (input.startsWith("find")) {
            return parseFindCommand(input);
        } else if (input.startsWith("todo")) {
            return parseTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return parseDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return parseEventCommand(input);
        } else {
            throw new LivException("Invalid input!");
        }
    }

    /**
     * Creates a ByeCommand object to be executed.
     * @return A ByeCommand object.
     */
    public static Command parseByeCommand() {
        return new ByeCommand();
    }

    /**
     * Processes the input that includes the index of the task.
     * @param input The command that users input.
     * @return The index of the task that users want to process.
     * @throws LivException If the index input is not a number, or no index input is given.
     */
    private static int parseNumberInInput(String input) throws LivException {
        // Expect input.txt in this form: "<command> <number>"
        int spaceIndex = input.indexOf(' ');
        if (spaceIndex == -1) {
            throw new LivException("Please state the mission number!");
        }
        String numberString = input.substring(spaceIndex + 1);
        if (!numberString.matches("\\d+")) {
            throw new LivException("Please enter a number as the mission number!");
        }
        return Integer.parseInt(numberString);
    }

    /**
     * Creates a ListCommand object to be executed.
     * @return A ListCommand object.
     */
    public static Command parseListCommand() {
        return new ListCommand();
    }

    /**
     * Creates a MarkCommand or a UnmarkCommand object depends on the user input.
     * @param input A String that was read from the user input.
     * @param state A boolean to decide whether to create a MarkCommand or UnmarkCommand.
     * @return A MarkCommand or a UnmarkCommand, depends on the boolean state.
     * @throws LivException If no task index was given.
     */
    public static Command parseMarkOrUnmarkCommand(String input, boolean state) throws LivException {
        int index = parseNumberInInput(input);
        int trueIndex = index - 1;
        if ((trueIndex < 0) || (trueIndex >= TaskList.getListSize())) {
            throw new LivException("That mission number does not exist in the list!");
        }
        if (state) {
            return new MarkCommand(index);
        }
        return new UnmarkCommand(index);
    }

    /**
     * Creates a DeleteCommand to be executed.
     * @param input A string that the users input.
     * @return A DeleteCommand object.
     * @throws LivException If no task index was given.
     */
    public static Command parseDeleteCommand(String input) throws LivException {
        int index = parseNumberInInput(input);
        int trueIndex = index - 1;
        if ((trueIndex < 0) || (trueIndex >= TaskList.getListSize())) {
            throw new LivException("That mission number does not exist in the list!");
        }
        return new DeleteCommand(index);
    }

    /**
     * Creates a TodoCommand to be executed.
     * @param input A string that the users input.
     * @return A TodoCommand object.
     * @throws LivException If the description is empty.
     */
    public static Command parseTodoCommand(String input) throws LivException {
        int spaceIndex = input.indexOf(' ');
        if (spaceIndex == -1) {
            throw new LivException("Description cannot be empty!");
        }
        String description = input.substring(spaceIndex + 1);
        TodoTask newTodoTask = new TodoTask(description);
        return new TodoCommand(newTodoTask);
    }

    /**
     * Creates a DeadlineCommand to be executed.
     * @param input A string that the users input.
     * @return A DeadlineCommand object.
     * @throws LivException If the description or time is empty.
     */
    public static Command parseDeadlineCommand(String input) throws LivException {
        // deadline <description> /by <time>
        int spaceIndex = input.indexOf(' ');
        if (spaceIndex == -1) {
            throw new LivException("Description cannot be empty!");
        }
        int timeIndex = input.indexOf('/');
        if (timeIndex == -1) {
            throw new LivException("Time cannot be empty!");
        }
        String description = input.substring(spaceIndex + 1, timeIndex - 1);
        String time = input.substring(timeIndex + 4);
        LocalDateTime by = LocalDateTime.parse(time, INPUT_PATTERN);
        Deadline newDeadline = new Deadline(description, by);
        return new DeadlineCommand(newDeadline);
    }

    /**
     * Creates a EventCommand to be executed.
     * @param input A string that the users input.
     * @return A EventCommand object.
     * @throws LivException If the description or time is empty.
     */
    public static Command parseEventCommand(String input) throws LivException {
        int spaceIndex = input.indexOf(' ');
        if (spaceIndex == -1) {
            throw new LivException("Description cannot be empty!");
        }
        int timeIntervalIndex = input.indexOf('/');
        if (timeIntervalIndex == -1) {
            throw new LivException("Time cannot be empty!");
        }
        String description = input.substring(spaceIndex + 1, timeIntervalIndex - 1);
        String timeInterval = input.substring(timeIntervalIndex + 6);
        int splitterIndex = timeInterval.indexOf('/');
        if (splitterIndex == -1) {
            throw new LivException("Please enter the correct format for time!");
        }
        String time1 = timeInterval.substring(0, splitterIndex - 1);
        String time2 = timeInterval.substring(splitterIndex + 4);
        LocalDateTime from = LocalDateTime.parse(time1, INPUT_PATTERN);
        LocalDateTime to = LocalDateTime.parse(time2, INPUT_PATTERN);
        Event newEvent = new Event(description, from, to);
        return new EventCommand(newEvent);
    }

    public static Command parseFindCommand(String input) throws LivException {
        int spaceIndex = input.indexOf(' ');
        if (spaceIndex == -1) {
            throw new LivException("Keyword cannot be empty!");
        }
        String keyword = input.substring(spaceIndex + 1);
        return new FindCommand(keyword);
    }
}
