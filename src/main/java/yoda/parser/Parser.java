package yoda.parser;
import yoda.constants.Replies;
import yoda.exceptions.*;
import yoda.yodaUI.YodaUI;
import yoda.task.Deadline;
import yoda.task.Event;
import yoda.task.TaskList;
import yoda.task.Todo;
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
        String[] parts = getSplit(input);
        Command command;
        try {
            command = Command.fromString(parts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return "Unknown command. Try again, you must.";
        }

        try {
            switch (command) {
                case BYE:
                    YODA_UI.stopChatting();
                    return Replies.BYE;
                case LIST:
                    return YODA_UI.showTasks();
                case SAVE:
                    TaskList taskList = YODA_UI.getTaskList();
                    YODA_UI.saveTasks(taskList);
                    return Replies.TASKS_SAVED;
                case DELETE:
                    performTaskOperation(parts, YODA_UI::deleteTask);
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
    private static String[] getSplit(String input) {
        return input.trim().split("\\s+", 2);
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

        try {
            if (commandType == Command.DEADLINE) {
                LocalDateTime by = DateTimeUtil.parseDateTime(taskParts[1]);
                return YODA_UI.addTask(new Deadline(taskParts[0], by));
            } else if (commandType == Command.EVENT) {
                String[] timeParts = getTimeParts(taskParts);
                if (timeParts.length < 2) {
                    throw new TimeMissingException("Required, the event end time is, yes.");
                }
                LocalDateTime from = DateTimeUtil.parseDateTime(timeParts[0]);
                LocalDateTime to = DateTimeUtil.parseDateTime(timeParts[1]);
                return YODA_UI.addTask(new Event(taskParts[0], from, to));
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
        return "Unexpected error occurred.";
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
        return parts[1].split(" /by | /from ", 2);
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
            if (taskNumber <= 0 || taskNumber > YODA_UI.getTaskListSize()) {
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
