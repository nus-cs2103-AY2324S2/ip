package jayne;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jayne.task.Deadline;
import jayne.task.Event;
import jayne.task.Task;
import jayne.task.TaskList;
import jayne.task.Todo;

/**
 * Handles the execution of user commands.
 * This class is responsible for processing user input commands
 * and invoking the appropriate actions on the TaskList.
 * It acts as a bridge between the user interface and the data model.
 */
public class Handler {
    private static final int TARGET_LIMT = 2;
    /**
     * Handles the deletion of a task from the task list.
     *
     * @param parts    the array containing the input command and its parts.
     * @param taskList the TaskList containing the list of tasks.
     * @throws JayneException if the task number is invalid or does not exist.
     */
    public static String handleDelete(String[] parts, TaskList taskList) throws JayneException {
        String startText = "\nNoted. I've removed this task:\n  ";
        String endText = " tasks in the list.\n";
        assert parts != null : "Input command parts cannot be null";
        assert taskList != null : "TaskList cannot be null";
        if (parts.length < TARGET_LIMT) {
            throw JayneException.deleteEmptyException();
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            if (taskNumber <= 0 || taskNumber > taskList.getTaskCount()) {
                throw JayneException.deleteExistException(taskNumber);
            }
            Task removedTask = taskList.deleteTask(taskNumber);
            assert removedTask != null : "Removed task should not be null";
            return startText + removedTask + "\nNow you have "
                    + taskList.getTaskCount() + endText;
        } catch (NumberFormatException e) {
            throw JayneException.deleteInvalidException();
        }
    }
    /**
     * Handles finding tasks by keyword.
     *
     * @param parts the array containing the input command and its parts.
     * @param taskList the TaskList containing the list of tasks.
     */
    public static String handleFind(String[] parts, TaskList taskList) throws JayneException {
        if (checkIsEmptyOrLessThanLimit(parts)) {
            throw new JayneException("The search keyword cannot be empty.");
        }
        return taskList.findTask(parts[1]);
    }
    /**
     * Handles unmarking a task as not done.
     *
     * @param parts    the array containing the input command and its parts.
     * @param taskList the TaskList containing the list of tasks.
     * @throws JayneException if the task number is invalid or does not exist.
     */
    public static String handleUnmark(String[] parts, TaskList taskList) throws JayneException {
        if (parts.length < TARGET_LIMT) {
            throw JayneException.unmarkEmptyException();
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            if (taskNumber <= 0 || taskNumber > taskList.getTaskCount()) {
                throw JayneException.unmarkTaskExistException(taskNumber);
            }
            taskList.markTaskAsNotDone(taskNumber);
            return "\nOK, I've marked this task as not done yet:\n  " + taskList.getTask(taskNumber)
                    + "\n";
        } catch (NumberFormatException e) {
            throw JayneException.unmarkException();
        }
    }
    /**
     * Handles creating a deadline task and adding it to the task list.
     *
     * @param parts the array containing the input command and its parts.
     * @param taskList the TaskList containing the list of tasks.
     * @throws JayneException if the deadline description or date is empty or in an invalid format.
     */
    public static String handleDeadline(String[] parts, TaskList taskList) throws JayneException {
        assert parts != null : "Input command parts cannot be null";
        assert taskList != null : "TaskList cannot be null";
        if (checkIsEmptyOrLessThanLimit(parts)) {
            throw new JayneException("The description of a deadline cannot be empty.");
        }
        String[] deadlineParts = parts[1].split(" /by ", TARGET_LIMT);
        if (checkIsEmptyOrLessThanLimit(deadlineParts)) {
            throw JayneException.deadlineException();
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDate.parse(deadlineParts[1], formatter);
        } catch (DateTimeParseException e) {
            throw new JayneException("Deadline date is in the wrong format. Please use yyyy-mm-dd.");
        }
        Deadline newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
        taskList.addTask(newDeadline);
        return "Got it. I've added this task:\n"
                + "  " + newDeadline
                + "Now you have " + taskList.getTaskCount() + " tasks in the list.";
    }
    /**
     * Handles creating a todo task and adding it to the task list.
     *
     * @param parts    the array containing the input command and its parts.
     * @param taskList the TaskList containing the list of tasks.
     * @throws JayneException if the todo description is empty.
     */
    public static String handleTodo(String[] parts, TaskList taskList) throws JayneException {
        assert parts != null : "Input command parts cannot be null";
        assert taskList != null : "TaskList cannot be null";
        if (checkIsEmptyOrLessThanLimit(parts)) {
            throw JayneException.todoException();
        }
        Todo newTodo = new Todo(parts[1]);
        taskList.addTask(newTodo);
        return "Got it. I've added this task:\n" + newTodo + "\nNow you have "
                + taskList.getTaskCount() + " tasks in the list.";
    }
    /**
     * Handles creating an event task and adding it to the task list.
     *
     * @param parts the array containing the input command and its parts.
     * @param taskList the TaskList containing the list of tasks.
     * @throws JayneException if the event description or time is empty or in an invalid format.
     */
    public static String handleEvent(String[] parts, TaskList taskList) throws JayneException {
        assert parts != null : "Input command parts cannot be null";
        assert taskList != null : "TaskList cannot be null";
        if (checkIsEmptyOrLessThanLimit(parts)) {
            throw JayneException.emptyEventException();
        }
        String[] eventParts = parts[1].split(" /from ", TARGET_LIMT);
        if (checkIsEmptyOrLessThanLimit(eventParts)) {
            throw JayneException.eventStartException();
        }
        String[] times = eventParts[1].split(" /to ", TARGET_LIMT);
        if (checkIsEmptyOrLessThanLimit(times)) {
            throw JayneException.eventEndException();
        }
        Event newEvent = new Event(eventParts[0], times[0], times[1]);
        taskList.addTask(newEvent);
        return "Got it. I've added this task:\n"
                + "  " + newEvent
                + "\nNow you have " + taskList.getTaskCount() + " tasks in the list.";
    }
    private static boolean checkIsEmptyOrLessThanLimit(String[] input) {
        return input.length < TARGET_LIMT || input[1].isEmpty();
    }
    /**
     * Handles marking a task as done.
     *
     * @param parts the array containing the input command and its parts.
     * @param taskList the TaskList containing the list of tasks.
     * @throws JayneException if the task number is invalid or does not exist.
     */
    public static String handleMark(String[] parts, TaskList taskList) throws JayneException {
        assert parts != null : "Input command parts cannot be null";
        assert taskList != null : "TaskList cannot be null";
        if (parts.length < TARGET_LIMT) {
            throw JayneException.markEmptyException();
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            boolean isLessThanZero = taskNumber <= 0;
            boolean isOutOfRange = taskNumber > taskList.getTaskCount();
            if (isLessThanZero || isOutOfRange) {
                throw JayneException.markTaskExistException(taskNumber);
            }
            taskList.markTaskAsDone(taskNumber);
            return "\nNice! I've marked this task as done:\n  "
                    + taskList.getTask(taskNumber) + "\n";
        } catch (NumberFormatException e) {
            throw JayneException.markInvalidTaskException();
        }
    }
    /**
     * Prints bye
     */
    public static String handleBye() {
        return "Go to sleep ok, stop bullying Slurpee";
    }
    /**
     * prints task
     */
    public static String handleList(TaskList taskList) {
        if (taskList.getTaskCount() == 0) {
            return "There are no task!";
        } else {
            return taskList.display();
        }
    }
}
