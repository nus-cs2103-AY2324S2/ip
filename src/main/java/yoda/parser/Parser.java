package yoda.parser;
import yoda.constants.Replies;
import yoda.exceptions.DescriptionAndTimeMissingException;
import yoda.exceptions.UnknownCommandException;
import yoda.exceptions.InvalidTaskException;
import yoda.exceptions.InvalidDateTimeFormatException;
import yoda.exceptions.TimeMissingException;
import yoda.task.Deadline;
import yoda.task.Event;
import yoda.task.Todo;
import yoda.task.TaskList;
import yoda.ui.YodaUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import yoda.datetimeutil.DateTimeUtil;


public class Parser {

    private final YodaUI YODA_UI;

    /**
     * Constructor for CommandParser.
     * @param yodaUI The instance of YodaUI to interact with the task list.
     */
    public Parser(YodaUI yodaUI) {
        this.YODA_UI = yodaUI;
    }

    /**
     * Parses the user input and executes the corresponding command.
     * @param input The input string provided by the user.
     * @throws Exception if an error occurs during command execution.
     */
    public String parseAndExecute(String input) throws Exception {
        UpdateState updateState = YODA_UI.getUpdateState();
        if (updateState != UpdateState.NONE) {
            return YODA_UI.handleUpdateState(input);
        }
        String[] parts = getSplitParts(input);
        Command command;
        try {
            command = Command.fromString(parts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }

        try {
            switch (command) {
            case BYE:
                return Replies.BYE;
            case LIST:
                return YODA_UI.showTasks();
            case SAVE:
                TaskList taskList = YODA_UI.getTaskList();
                return YODA_UI.saveTasks(taskList);
            case DELETE:
                return performTaskOperation(parts, YODA_UI::deleteTask);
            case FIND:
                if (parts.length > 1) {
                    return YODA_UI.findTasks(parts[1]);
                } else {
                    return Replies.UNKNOWN_SEARCH_TERM;
                }
            case MARK:
                return performTaskOperation(parts, YODA_UI::markTaskAsDone);
            case UNMARK:
                return performTaskOperation(parts, YODA_UI::markTaskAsUndone);
            case TODO:
                return YODA_UI.addTask(new Todo(parts[1]));
            case DEADLINE:
                return addTaskWithDateTime(parts, Command.DEADLINE);
            case EVENT:
                return addTaskWithDateTime(parts, Command.EVENT);
            case UPDATE:
                if (parts.length > 1) {
                    int taskNumber = Integer.parseInt(parts[1]);
                    return YODA_UI.startUpdateProcess(taskNumber, input);
                } else {
                    return "Task number, specify you must.";
                }
            default:
                return Replies.UNKNOWN_COMMAND;
            }
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage();
        }
    }

    /**
     * Splits the input string into two parts based on the first occurrence of whitespace.
     * @param input The input string to be split.
     * @return The split input string.
     */
    private static String[] getSplitParts(String input) {
        String[] parts = input.trim().split("\\s+", 2);
        assert parts.length > 0 : "Input should be split into at least one part";
        return parts;
    }

    /**
     * Adds a task with a date and time to the task list.
     * @param parts The split input containing the command and task description.
     * @param commandType The type of task to add (deadline or event).
     * @throws Exception if the task description or time is missing.
     */
    private String addTaskWithDateTime(String[] parts, Command commandType) throws Exception {
        if (parts.length < 2) {
            throw new DescriptionAndTimeMissingException();
        }

        String[] taskParts = getTaskParts(parts);
        if (taskParts.length < 2) {
            throw new TimeMissingException();
        }

        switch (commandType) {
        case DEADLINE:
            return addDeadlineTask(taskParts);
        case EVENT:
            return addEventTask(taskParts);
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses the date and time from the input.
     * @param dateTimeStr The date and time string to be parsed.
     * @return The parsed date and time.
     * @throws InvalidDateTimeFormatException if the date and time string is invalid.
     */
    private LocalDateTime parseDateTime(String dateTimeStr) throws InvalidDateTimeFormatException {
        try {
            return DateTimeUtil.parseDateTime(dateTimeStr);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
    }

    /**
     * Adds a deadline task to the task list.
     * @param taskParts The split input containing the task description and time.
     * @return The task description and time parts.
     * @throws Exception if the task description or time is missing.
     */
    private String addDeadlineTask(String[] taskParts) throws Exception {
        LocalDateTime by = parseDateTime(taskParts[1]);
        return YODA_UI.addTask(new Deadline(taskParts[0], by));
    }

    /**
     * Adds an event task to the task list.
     * @param taskParts The split input containing the task description and time.
     * @return The task description and time parts.
     * @throws Exception if the task description or time is missing.
     */
    private String addEventTask(String[] taskParts) throws Exception {
        String[] timeParts = getTimeParts(taskParts);
        if (timeParts.length < 2) {
            throw new TimeMissingException("Required, the event end time is, yes.");
        }
        LocalDateTime from = parseDateTime(timeParts[0]);
        LocalDateTime to = parseDateTime(timeParts[1]);
        return YODA_UI.addTask(new Event(taskParts[0], from, to));
    }



    /**
     * Parses the time parts from the input.
     * @param taskParts The split input containing the task description and time.
     * @return The time parts of the task.
     */
    private static String[] getTimeParts(String[] taskParts) {
        return taskParts[1].split(" /to ", 2);
    }

    /**
     * Parses the task description and time from the input.
     * @param parts The split input containing the command and task description.
     * @return The task description and time parts.
     */
    private static String[] getTaskParts(String[] parts) {
        String[] taskParts = parts[1].split(" /by | /from ", 2);
        assert taskParts.length == 2 : "Task description and time should be present";
        return taskParts;
    }


    /**
     * Parses the task number from the user input.
     * @param input The user input containing the task number.
     * @return The task number parsed from the input.
     * @throws InvalidTaskException if the task number is invalid.
     */
    private int parseTaskNumber(String input) throws InvalidTaskException {
        try {
            int taskNumber = Integer.parseInt(input);
            assert taskNumber > 0 : "Task number should be positive";
            boolean taskLargerThanSize = taskNumber > YODA_UI.getTaskListSize();
            if (taskLargerThanSize) {
                throw new InvalidTaskException();
            }
            return taskNumber;
        } catch (InvalidTaskException e) {
            throw new InvalidTaskException();
        }
    }


    /**
     * Performs a task operation (delete, mark, unmark) based on the user input.
     * @param parts The split input containing the command and task number.
     * @param operation The operation to be performed on the task.
     * @throws InvalidTaskException if the task number is invalid.
     */
    private String performTaskOperation(String[] parts, TaskOperation operation) throws InvalidTaskException {
        if (parts.length > 1) {
            int taskNumber = this.parseTaskNumber(parts[1]);
            return operation.perform(taskNumber);
        } else {
            throw new InvalidTaskException();
        }
    }



    /**
     * Functional interface for task operations like delete, mark, and unmark.
     */
    @FunctionalInterface
    private interface TaskOperation {
        String perform(int taskNumber) throws InvalidTaskException;
    }

}
