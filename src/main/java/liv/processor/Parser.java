package liv.processor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Comparator;
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
        assert input != null: "Input cannot be null!";
        if (input.equalsIgnoreCase("bye")) {
            return parseByeCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return parseListCommand();
        } else if (input.equalsIgnoreCase("clear")) {
            return parseClearCommand();
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
    private static Command parseByeCommand() {
        return new ByeCommand();
    }

    private static Command parseClearCommand() {
        return new ClearCommand();
    }

    /**
     * Processes the input that includes the index of the task.
     * @param input The command that users input.
     * @return The index of the task that users want to process.
     * @throws LivException If the index input is not a number, or no index input is given.
     */
    private static ArrayList<Integer> parseNumbersInInput(String input) throws LivException {
        // Expect input in this form: "<command> <list of indices>"
        ArrayList<Integer> indicesList = new ArrayList<>();

        int firstSpaceIndex = input.indexOf(' ');
        if (firstSpaceIndex == -1) {
            throw new LivException("Please state the mission number!");
        }

        String indicesListString = input.substring(firstSpaceIndex + 1);

        String[] splitIndicesList = indicesListString.split(" ");
        for (String indexString: splitIndicesList) {
            if (!indexString.matches("\\d+")) {
                throw new LivException("Please enter a positive integer as the mission number!");
            }
            int index = Integer.parseInt(indexString);
            assert index > 0: "Input number should be a positive integer!";

            if (indicesList.contains(index)) {
                throw new LivException("Cannot enter duplicate mission number!");
            }

            indicesList.add(index);
        }

        indicesList.sort(Comparator.naturalOrder());
        return indicesList;
    }

    /**
     * Creates a ListCommand object to be executed.
     * @return A ListCommand object.
     */
    private static Command parseListCommand() {
        return new ListCommand();
    }

    private static boolean isValidIndex(int index) {
        int currentListSize = TaskList.getListSize();
        if ((index < 0) || (index >= currentListSize)) {
            return false;
        }
        return true;
    }

    /**
     * Creates a MarkCommand or a UnmarkCommand object depends on the user input.
     * @param input A String that was read from the user input.
     * @param state A boolean to decide whether to create a MarkCommand or UnmarkCommand.
     * @return A MarkCommand or a UnmarkCommand, depends on the boolean state.
     * @throws LivException If no task index was given.
     */
    private static Command parseMarkOrUnmarkCommand(String input, boolean state) throws LivException {
        ArrayList<Integer> indices = parseNumbersInInput(input);
        indices.replaceAll(index -> index - 1);

        for (int index: indices) {
            if (!isValidIndex(index)) {
                throw new LivException("Mission number " + (index + 1) + " does not exist in the list!");
            }
        }

        if (state) {
            return new MarkCommand(indices);
        }
        return new UnmarkCommand(indices);
    }

    /**
     * Creates a DeleteCommand to be executed.
     * @param input A string that the users input.
     * @return A DeleteCommand object.
     * @throws LivException If no task index was given.
     */
    private static Command parseDeleteCommand(String input) throws LivException {
        ArrayList<Integer> indices = parseNumbersInInput(input);
        indices.replaceAll(index -> index - 1);

        for (int index: indices) {
            if (!isValidIndex(index)) {
                throw new LivException("Mission number " + (index + 1) + " does not exist in the list!");
            }
        }

        return new DeleteCommand(indices);
    }

    /**
     * Creates a TodoCommand to be executed.
     * @param input A string that the users input.
     * @return A TodoCommand object.
     * @throws LivException If the description is empty.
     */
    private static Command parseTodoCommand(String input) throws LivException {
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
    private static Command parseDeadlineCommand(String input) throws LivException {
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
    private static Command parseEventCommand(String input) throws LivException {
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

        assert time1 != null: "Incorrect time format!";
        assert time2 != null: "Incorrect time format!";
      
        LocalDateTime from = LocalDateTime.parse(time1, INPUT_PATTERN);
        LocalDateTime to = LocalDateTime.parse(time2, INPUT_PATTERN);
        Event newEvent = new Event(description, from, to);

        return new EventCommand(newEvent);
    }

    private static Command parseFindCommand(String input) throws LivException {
        int spaceIndex = input.indexOf(' ');
        if (spaceIndex == -1) {
            throw new LivException("Keyword cannot be empty!");
        }

        String keyword = input.substring(spaceIndex + 1);
        if (keyword.equals("")) {
            throw new LivException("Keyword cannot be empty!");
        }

        return new FindCommand(keyword);
    }
}
