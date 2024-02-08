package yoda.parser;
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
    public void parseAndExecute(String input) throws Exception {
        String[] parts = input.split("\\s+", 2);
        Command command = Command.fromString(parts[0]);

        try {
            switch (command) {
            case BYE:
                YODA_UI.printMessage("Farewell. See you again, I hope!");
                YODA_UI.stopChatting();
                break;
            case LIST:
                YODA_UI.showTasks();
                break;
            case SAVE:
                TaskList taskList = YODA_UI.getTaskList();
                YODA_UI.saveTasks(taskList);
                break;
            case DELETE:
                performTaskOperation(parts, YODA_UI::deleteTask);
                break;
            case FIND:
                if (parts.length > 1) {
                    YODA_UI.findTasks(parts[1]);
                } else {
                    throw new Exception("Search term, provide you must.");
                }
                break;
            case MARK:
                performTaskOperation(parts, YODA_UI::markTaskAsDone);
                break;
            case UNMARK:
                performTaskOperation(parts, YODA_UI::markTaskAsUndone);
                break;
            case TODO:
                YODA_UI.addTask(new Todo(parts[1]));
                break;
            case DEADLINE:
                addTaskWithDateTime(parts, Command.DEADLINE);
                break;
            case EVENT:
                addTaskWithDateTime(parts, Command.EVENT);
                break;
            default:
                YODA_UI.printMessage("Sorry, I am. What that means, I do not know :-(");
            }
        } catch (Exception e) {
            YODA_UI.printMessage(e.getMessage());
        }
    }

    /**
     * Adds a task with a date and time to the task list.
     * @param parts The split input containing the command and task description.
     * @param commandType The type of task to add (deadline or event).
     * @throws Exception if the task description or time is missing.
     */
    private void addTaskWithDateTime(String[] parts, Command commandType) throws Exception {
        if (parts.length < 2) {
            throw new Exception("Required, a task description and time are, hmm.");
        }

        String[] taskParts = parts[1].split(" /by | /from ", 2);
        if (taskParts.length < 2) {
            throw new Exception("Required, the task time is, yes.");
        }

        try {
            if (commandType == Command.DEADLINE) {
                LocalDateTime by = DateTimeUtil.parseDateTime(taskParts[1]);
                YODA_UI.addTask(new Deadline(taskParts[0], by));
            } else if (commandType == Command.EVENT) {
                String[] timeParts = taskParts[1].split(" /to ", 2);
                if (timeParts.length < 2) {
                    throw new Exception("Required, the event end time is, yes.");
                }
                LocalDateTime from = DateTimeUtil.parseDateTime(timeParts[0]);
                LocalDateTime to = DateTimeUtil.parseDateTime(timeParts[1]);
                YODA_UI.addTask(new Event(taskParts[0], from, to));
            }
        } catch (DateTimeParseException e) {
            throw new Exception("Invalid, the date format is. Use one of the accepted formats, you must.");
        }
    }

    /**
     * Parses the task number from the user input.
     * @param input
     * @return
     * @throws Exception
     */
    private int parseTaskNumber(String input) throws Exception {
        try {
            int taskNumber = Integer.parseInt(input);
            if (taskNumber <= 0 || taskNumber > YODA_UI.getTaskListSize()) {
                throw new Exception("Valid task number, provide you must.");
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new Exception("A number, enter you must.");
        }
    }

    /**
     * Performs a task operation (delete, mark, unmark) based on the user input.
     * @param parts The split input containing the command and task number.
     * @param operation The operation to be performed on the task.
     * @throws Exception if the task number is invalid.
     */
    private void performTaskOperation(String[] parts, TaskOperation operation) throws Exception {
        if (parts.length > 1) {
            int taskNumber = this.parseTaskNumber(parts[1]);
            operation.perform(taskNumber);
        } else {
            throw new Exception("Specify a task number, you must.");
        }
    }

    /**
     * Functional interface for task operations like delete, mark, and unmark.
     */
    @FunctionalInterface
    private interface TaskOperation {
        void perform(int taskNumber) throws Exception;
    }

}
