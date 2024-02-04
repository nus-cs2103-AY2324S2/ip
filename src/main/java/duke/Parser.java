package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The Parser class is responsible for interpreting and processing user commands.
 * It provides various static methods to handle different types of tasks and commands.
 */
public class Parser {

    /**
     * Prints all tasks in the TaskList.
     * Outputs a formatted list of tasks or a message if the list is empty.
     *
     * @param tl The TaskList containing the tasks to be printed.
     */
    public static String printLst(TaskList tl) {
        StringBuilder toPrint = new StringBuilder();
        if (tl.getLst().isEmpty()) {
            toPrint.append(" Whoops! Your list is empty :(");
        } else {
            toPrint.append(" Here are the tasks in your list:");
            for (int i = 0; i < tl.size(); i++) {
                String taskPrintout = "\n   " + (i + 1) + "." + tl.get(i).toString();
                toPrint.append(taskPrintout);
            }
        }
        return toPrint.toString();
    }

    /**
     * Adds a Todo task to the TaskList.
     * Validates and extracts task details from the command before adding the task.
     *
     * @param tl The TaskList to add the Todo task to.
     * @param commandArr The array of strings representing the split user command.
     * @param cmd The full user command string.
     * @throws DukeException If the Todo task description is empty.
     */
    public static String addTodo(TaskList tl, String[] commandArr, String cmd) throws DukeException {
        if (commandArr.length == 1) {
            throw new DukeException(" Sorry, the description of a todo cannot be empty :(");
        }
        String todo = cmd.substring(5);
        Task newTodo = new Todo(todo);
        tl.addTask(newTodo);
        String toPrint = " Got it. I've added this task:\n";
        toPrint += "   " + newTodo + "\n";
        toPrint += " Now you have " + tl.size() + " tasks in the list.";
        return toPrint;
    }

    /**
     * Adds a Deadline task to the TaskList.
     * Validates and extracts task details including the deadline date from the command.
     *
     * @param tl The TaskList to add the Deadline task to.
     * @param commandArr The array of strings representing the split user command.
     * @param cmd The full user command string.
     * @throws DukeException If the Deadline description or date is empty or incorrectly formatted.
     */
    public static String addDeadline(TaskList tl, String[] commandArr, String cmd) throws DukeException {
        if (commandArr.length == 1) {
            throw new DukeException(" Sorry, the description and date of a deadline cannot be empty :(");
        }
        String[] deadlineArr = cmd.substring(9).split(" /by ");
        if (deadlineArr.length == 1) {
            throw new DukeException(" Sorry, the date of a deadline cannot be empty :(\n%s");
        }
        LocalDate by;
        try {
            by = LocalDate.parse(deadlineArr[1]);
        } catch (DateTimeParseException dateTimeParseE) {
            throw new DukeException(" Sorry, please input the date of a deadline in the format YYYY-MM-DD");
        }
        Task newDeadline = new Deadline(deadlineArr[0], by);
        tl.addTask(newDeadline);
        String toPrint = " Got it. I've added this task:\n";
        toPrint += "   " + newDeadline + "\n";
        toPrint += " Now you have " + tl.size() + " tasks in the list.";
        return toPrint;
    }

    /**
     * Adds an Event task to the TaskList.
     * Validates and extracts task details including the event start and end dates from the command.
     *
     * @param tl The TaskList to add the Event task to.
     * @param commandArr The array of strings representing the split user command.
     * @param cmd The full user command string.
     * @throws DukeException If the Event description or dates are empty or incorrectly formatted.
     */
    public static String addEvent(TaskList tl, String[] commandArr, String cmd) throws DukeException {
        if (commandArr.length == 1) {
            throw new DukeException(" Sorry, the description and start and end dates of an event cannot be empty :(");
        }
        String[] eventFromArr = cmd.substring(6).split(" /from ");
        if (eventFromArr.length == 1) {
            throw new DukeException(" Sorry, the start and end dates of an event cannot be empty :(");
        }
        String[] eventToArr = eventFromArr[1].split(" /to ");
        if (eventToArr.length == 1) {
            throw new DukeException(" Sorry, the end date of an event cannot be empty :(");
        }
        LocalDate from;
        LocalDate to;
        try {
            from = LocalDate.parse(eventToArr[0]);
            to = LocalDate.parse(eventToArr[1]);
        } catch (DateTimeParseException dateTimeParseE) {
            throw new DukeException(" Sorry, please input the dates of an event in the format YYYY-MM-DD");
        }
        if (from.isAfter(to)) {
            throw new DukeException(" Sorry, please input the start date of an event before/on the end date");
        }
        Task newEvent = new Event(eventFromArr[0], from, to);
        tl.addTask(newEvent);
        String toPrint = " Got it. I've added this task:\n";
        toPrint += "   " + newEvent + "\n";
        toPrint += " Now you have " + tl.size() + " tasks in the list.";
        return toPrint;
    }

    /**
     * Finds and lists all tasks in the TaskList that contain the given keyword.
     *
     * @param tl The TaskList to search for tasks.
     * @param commandArr The array of strings representing the split user command.
     * @throws DukeException If the search keyword is not provided.
     */
    public static String findTask(TaskList tl, String[] commandArr) throws DukeException {
        if (commandArr.length == 1) {
            throw new DukeException(" Sorry, please input what you want me to find");
        }
        String keyword = commandArr[1];
        ArrayList<Task> foundTasks = tl.find(keyword);
        StringBuilder toPrint = new StringBuilder();
        toPrint.append(" Here are the matching tasks in your list:");
        int count = 0;
        for (Task t : foundTasks) {
            String taskPrintout = "\n   " + (count + 1) + "." + t.toString();
            toPrint.append(taskPrintout);
            count++;
        }
        if (count == 0) {
            toPrint = new StringBuilder();
            toPrint.append("\n Whoops! No matching tasks found :(");
        }
        return toPrint.toString();
    }

    /**
     * Marks a task in the TaskList as done.
     * Validates the task index before marking it.
     *
     * @param tl The TaskList containing the task to mark.
     * @param commandArr The array of strings representing the split user command.
     * @throws DukeException If the task index is invalid or not provided.
     */
    public static String markTask(TaskList tl, String[] commandArr) throws DukeException {
        if (commandArr.length == 1) {
            throw new DukeException(" Sorry, please input the list index for me to mark");
        }
        if (!commandArr[1].matches("\\d+")) {
            throw new DukeException(" Sorry, please input a valid list index for me to mark");
        }
        int toMark = Integer.parseInt(commandArr[1]);
        if (toMark > tl.size()) {
            throw new DukeException(" Sorry, please input a valid list index for me to mark");
        }
        tl.get(toMark - 1).markAsDone();
        String toPrint = " Nice! I've marked this task as done:\n";
        toPrint += "   " + tl.get(toMark - 1).toString();
        return toPrint;
    }

    /**
     * Marks a task in the TaskList as not done.
     * Validates the task index before unmarking it.
     *
     * @param tl The TaskList containing the task to unmark.
     * @param commandArr The array of strings representing the split user command.
     * @throws DukeException If the task index is invalid or not provided.
     */
    public static String unmarkTask(TaskList tl, String[] commandArr) throws DukeException {
        if (commandArr.length == 1) {
            throw new DukeException(" Sorry, please input the list index for me to unmark");
        }
        if (!commandArr[1].matches("\\d+")) {
            throw new DukeException(" Sorry, please input a valid list index for me to unmark");
        }
        int toUnmark = Integer.parseInt(commandArr[1]);
        if (toUnmark > tl.size()) {
            throw new DukeException(" Sorry, please input a valid list index for me to unmark");
        }
        tl.get(toUnmark - 1).markAsUndone();
        String toPrint = " OK! I've marked this task as not done yet:\n";
        toPrint += "   " + tl.get(toUnmark - 1).toString();
        return toPrint;
    }

    /**
     * Deletes a task from the TaskList.
     * Validates the task index before deleting it.
     *
     * @param tl The TaskList containing the task to delete.
     * @param commandArr The array of strings representing the split user command.
     * @throws DukeException If the task index is invalid or not provided.
     */
    public static String deleteTask(TaskList tl, String[] commandArr) throws DukeException {
        if (commandArr.length == 1) {
            throw new DukeException(" Sorry, please input the list index for me to delete");
        }
        if (!commandArr[1].matches("\\d+")) {
            throw new DukeException(" Sorry, please input a valid list index for me to delete");
        }
        int toDelete = Integer.parseInt(commandArr[1]);
        if (toDelete > tl.size()) {
            throw new DukeException(" Sorry, please input a valid list index for me to delete");
        }
        String toPrint = " Noted. I've removed this task\n";
        toPrint += "   " + tl.get(toDelete - 1).toString() + "\n";
        tl.deleteTask(toDelete - 1);
        toPrint += " Now you have " + tl.size() + " tasks in the list.";
        return toPrint;
    }
}
