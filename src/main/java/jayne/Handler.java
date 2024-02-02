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
    private static final String dash = "___________________________________";
    /**
     * Handles the deletion of a task from the task list.
     *
     * @param parts    the array containing the input command and its parts.
     * @param taskList the TaskList containing the list of tasks.
     * @throws JayneException if the task number is invalid or does not exist.
     */
    public static void handleDelete(String[] parts, TaskList taskList) throws JayneException {
        if (parts.length < 2) {
            throw JayneException.deleteEmptyException();
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            if (taskNumber <= 0 || taskNumber > taskList.getTaskCount()) {
                throw JayneException.deleteExistException(taskNumber);
            }
            Task removedTask = taskList.deleteTask(taskNumber);
            System.out.println(dash + "\nNoted. I've removed this task:\n  " + removedTask + "\nNow you have "
                    + taskList.getTaskCount() + " tasks in the list.\n" + dash);
        } catch (NumberFormatException e) {
            throw JayneException.deleteInvalidException();
        }
    }
    /**
     * Handles unmarking a task as not done.
     *
     * @param parts    the array containing the input command and its parts.
     * @param taskList the TaskList containing the list of tasks.
     * @throws JayneException if the task number is invalid or does not exist.
     */
    public static void handleUnmark(String[] parts, TaskList taskList) throws JayneException {
        if (parts.length < 2) {
            throw JayneException.unmarkEmptyException();
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            if (taskNumber <= 0 || taskNumber > taskList.getTaskCount()) {
                throw JayneException.unmarkTaskExistException(taskNumber);
            }
            taskList.markTaskAsNotDone(taskNumber);
            System.out.println(dash + "\nOK, I've marked this task as not done yet:\n  " + taskList.getTask(taskNumber)
                    + "\n" + dash);
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
    public static void handleDeadline(String[] parts, TaskList taskList) throws JayneException {
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new JayneException("The description of a deadline cannot be empty.");
        }
        String[] deadlineParts = parts[1].split(" /by ", 2);
        if (deadlineParts.length < 2 || deadlineParts[1].isEmpty()) {
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
        System.out.println(dash + "\nGot it. I've added this task:");
        System.out.println("  " + newDeadline);
        System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list." + "\n" + dash);
    }
    /**
     * Handles creating a todo task and adding it to the task list.
     *
     * @param parts    the array containing the input command and its parts.
     * @param taskList the TaskList containing the list of tasks.
     * @throws JayneException if the todo description is empty.
     */
    public static void handleTodo(String[] parts, TaskList taskList) throws JayneException {
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw JayneException.todoException();
        }
        Todo newTodo = new Todo(parts[1]);
        taskList.addTask(newTodo);
        System.out.println(dash + "\nGot it. I've added this task:");
        System.out.println("  " + newTodo);
        System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list." + "\n"
                + dash);
    }
    /**
     * Handles creating an event task and adding it to the task list.
     *
     * @param parts the array containing the input command and its parts.
     * @param taskList the TaskList containing the list of tasks.
     * @throws JayneException if the event description or time is empty or in an invalid format.
     */
    public static void handleEvent(String[] parts, TaskList taskList) throws JayneException {
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw JayneException.emptyEventException();
        }
        String[] eventParts = parts[1].split(" /from ", 2);
        if (eventParts.length < 2 || eventParts[1].isEmpty()) {
            throw JayneException.eventStartException();
        }
        String[] times = eventParts[1].split(" /to ", 2);
        if (times.length < 2 || times[1].isEmpty()) {
            throw JayneException.eventEndException();
        }
        Event newEvent = new Event(eventParts[0], times[0], times[1]);
        taskList.addTask(newEvent);
        System.out.println(dash + "\nGot it. I've added this task:");
        System.out.println("  " + newEvent);
        System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list." + "\n" + dash);
    }
    /**
     * Handles marking a task as done.
     *
     * @param parts the array containing the input command and its parts.
     * @param taskList the TaskList containing the list of tasks.
     * @throws JayneException if the task number is invalid or does not exist.
     */
    public static void handleMark(String[] parts, TaskList taskList) throws JayneException {
        if (parts.length < 2) {
            throw JayneException.markEmptyException();
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            if (taskNumber <= 0 || taskNumber > taskList.getTaskCount()) {
                throw JayneException.markTaskExistException(taskNumber);
            }
            taskList.markTaskAsDone(taskNumber);
            System.out.println(dash + "\nNice! I've marked this task as done:\n  "
                    + taskList.getTask(taskNumber) + "\n" + dash);
        } catch (NumberFormatException e) {
            throw JayneException.markInvalidTaskException();
        }
    }
    /**
     * Prints bye
     */
    public static void handleBye() {
        System.out.println(dash);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(dash);

    }
    /**
     * prints task
     */
    public static void handleList(TaskList taskList) {
        System.out.println(dash);
        taskList.display();
        System.out.println(dash);
    }
}
