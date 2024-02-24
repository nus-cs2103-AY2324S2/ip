package duke;

import java.time.format.DateTimeParseException;
/**
 * Handles parsing of user inputs and encoded task data.
 * This class provides static methods to decode task information from a string format and to interpret user commands
 *      to interact with the task list and storage.
 */
public class Parser {
    private static final String TASK_TYPE_TODO = "T";
    private static final String TASK_TYPE_DEADLINE = "D";
    private static final String TASK_TYPE_EVENT = "E";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";

    private static final String ERROR_UNKNOWN_TASK_TYPE = "Unknown task type: ";
    private static final String ERROR_DESCRIPTION_TODO_EMPTY = "The description of a todo cannot be empty.";
    private static final String ERROR_DESCRIPTION_DEADLINE_EMPTY =
            "The description of a deadline or its due time cannot be empty.";
    private static final String ERROR_FORMAT_DATES = "Please use the format"
            + Task.DATE_TIME_FORMAT_INPUT + "for dates.";
    private static final String ERROR_DESCRIPTION_EVENT_EMPTY = "The description of an event cannot be empty.";
    private static final String ERROR_START_END_TIME_EVENT_EMPTY = "The start or end time of an event cannot be empty.";
    private static final String ERROR_INVALID_TASK_NUMBER = "Invalid task number.";
    private static final String ERROR_UNKNOWN_COMMAND = "I'm sorry, but I don't know what that means :-(";

    /**
     * Decodes a single line of encoded task data into a Task object.
     * The encoded format is expected to be a delimited string containing task type, status, description,
     * and optionally, date/time information.
     *
     * @param line The encoded string representing a task.
     * @return A Task object corresponding to the encoded task data.
     * @throws IllegalArgumentException If the task type is unknown.
     */
    public static Task decodeTask(String line) {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
        case TASK_TYPE_TODO:
            return new Todo(parts[2], Boolean.parseBoolean(parts[1]));
        case TASK_TYPE_DEADLINE:
            return new Deadline(parts[2], Boolean.parseBoolean(parts[1]), parts[3]);
        case TASK_TYPE_EVENT:
            String[] timeParts = parts[3].split("-");
            return new Event(parts[2], Boolean.parseBoolean(parts[1]), timeParts[0], timeParts[1]);
        default:
            throw new IllegalArgumentException(ERROR_UNKNOWN_TASK_TYPE + parts[0]);
        }
    }
    /**
     * Parses user input to perform operations on the task list, such as adding, deleting, and marking tasks.
     * This method interprets commands to manipulate the task list and interacts with storage as needed.
     *
     * @param userInput The raw input string from the user.
     * @param taskList The TaskList object to be manipulated based on the user command.
     * @param storage The Storage object used for saving tasks after modifications.
     * @throws ChatbotException If the user input represents an unknown command or fails to provide necessary details.
     * @throws DateTimeParseException If date/time information for deadline or event tasks is improperly formatted.
     */
    public static String parse(String userInput, TaskList taskList, Storage storage) throws ChatbotException {
        if (userInput.trim().toLowerCase().startsWith(COMMAND_TODO)) {
            return handleTodoCommand(userInput, taskList, storage);
        } else if (userInput.trim().toLowerCase().startsWith(COMMAND_DEADLINE)) {
            return handleDeadlineCommand(userInput, taskList, storage);
        } else if (userInput.trim().toLowerCase().startsWith(COMMAND_EVENT)) {
            return handleEventCommand(userInput, taskList, storage);
        } else if (COMMAND_LIST.equalsIgnoreCase(userInput)) {
            return taskList.printTasks();
        } else if (userInput.trim().toLowerCase().startsWith(COMMAND_MARK)) {
            return handleMarkCommand(userInput, taskList, storage);
        } else if (userInput.trim().toLowerCase().startsWith(COMMAND_UNMARK)) {
            return handleUnmarkCommand(userInput, taskList, storage);
        } else if (userInput.trim().toLowerCase().startsWith(COMMAND_DELETE)) {
            return handleDeleteCommand(userInput, taskList, storage);
        } else if (userInput.trim().toLowerCase().startsWith(COMMAND_FIND)) {
            String keyword = userInput.substring(COMMAND_FIND.length()).trim();
            return taskList.findTask(keyword);
        } else {
            throw new ChatbotException(ERROR_UNKNOWN_COMMAND);
        }
    }

    private static String handleTodoCommand(String userInput, TaskList taskList, Storage storage)
            throws ChatbotException {
        String taskDetails = userInput.substring(COMMAND_TODO.length()).trim();

        if (taskDetails.isEmpty()) {
            throw new ChatbotException(ERROR_DESCRIPTION_TODO_EMPTY);
        }

        Task todo = new Todo(taskDetails, false);
        return taskList.addTask(todo, storage);
    }

    private static String handleDeadlineCommand(String userInput, TaskList taskList, Storage storage)
            throws ChatbotException {
        String taskDetails = userInput.substring(COMMAND_DEADLINE.length()).trim();
        String[] details = taskDetails.split(" /by ", 2);

        if (details.length < 2 || details[0].isEmpty() || details[1].isEmpty()) {
            throw new ChatbotException(ERROR_DESCRIPTION_DEADLINE_EMPTY);
        }

        try {
            Task deadline = new Deadline(details[0], false, details[1]);
            return taskList.addTask(deadline, storage);
        } catch (DateTimeParseException e) {
            throw new ChatbotException(ERROR_FORMAT_DATES);
        }
    }

    private static String handleEventCommand(String userInput, TaskList taskList, Storage storage)
            throws ChatbotException {
        String taskDetails = userInput.substring(COMMAND_EVENT.length()).trim();
        String[] details = taskDetails.split(" /from ", 2);

        if (details.length < 2 || details[0].isEmpty()) {
            throw new ChatbotException(ERROR_DESCRIPTION_EVENT_EMPTY);
        }

        String[] times = details[1].split(" /to ", 2);

        if (times.length < 2 || times[0].isEmpty() || times[1].isEmpty()) {
            throw new ChatbotException(ERROR_START_END_TIME_EVENT_EMPTY);
        }

        try {
            Task event = new Event(details[0], false, times[0], times[1]);
            return taskList.addTask(event, storage);
        } catch (DateTimeParseException e) {
            throw new ChatbotException(ERROR_FORMAT_DATES);
        }
    }

    private static String handleMarkCommand(String userInput, TaskList taskList, Storage storage)
            throws ChatbotException {
        int taskNumber = extractTaskNumber(userInput, COMMAND_MARK);
        return taskList.markTask(taskNumber, true, storage);
    }

    private static String handleUnmarkCommand(String userInput, TaskList taskList, Storage storage)
            throws ChatbotException {
        int taskNumber = extractTaskNumber(userInput, COMMAND_UNMARK);
        return taskList.markTask(taskNumber, false, storage);
    }

    private static String handleDeleteCommand(String userInput, TaskList taskList, Storage storage)
            throws ChatbotException {
        int taskNumber = extractTaskNumber(userInput, COMMAND_DELETE);
        return taskList.deleteTask(taskNumber, storage);
    }

    private static int extractTaskNumber(String userInput, String command) throws ChatbotException {
        try {
            return Integer.parseInt(userInput.substring(command.length()).trim());
        } catch (NumberFormatException e) {
            throw new ChatbotException(ERROR_INVALID_TASK_NUMBER);
        }
    }

}
