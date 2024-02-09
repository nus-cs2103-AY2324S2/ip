package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The Parser class represents a parser that parses the user input.
 */
public class Parser {

    /**
     * Parses the user input for a "todo" command and adds a new Todo task to the task list.
     * This method expects the input to follow the format "todo [description]" where [description]
     * is the task's description. It throws an exception if the input does not contain a description
     * for the todo after the command keyword.
     *
     * @param list The TaskList to which the new todo task is added. This list is modified by adding a
     *             new Todo task with the provided description.
     *
     * @param message The user input string, expected to start with "todo" followed by the task's description.
     *                The method parses this input to extract and add the task.
     *
     * @return A string response indicating that the new Todo task has been added to the list,
     *                including a summary of the task and the total number of tasks in the list.
     *
     * @throws DukeException If the input string does not contain a description for the todo or
     *                       if the description is empty. The exception message provides feedback
     *                       to the user about the missing description.
     */
    public static String handleTodo(TaskList list, String message) throws DukeException {
        if (message.trim().equals("todo")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty buddy.");
        }
        String description = message.substring(5).trim();
        Task task = new Task(description);
        list.add(task);
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + list.size() + " tasks in the list.";
    }

    /**
     * Parses the user input for a "deadline" command, creating and adding a new Deadline task to the task list.
     * The command should follow the format "deadline [description] /by [date]" where [date]
     * is in the "yyyy-MM-dd" format.
     * Throws an exception if the command syntax is incorrect or the date format does not match the expected pattern.
     *
     * @param list The task list to which the new Deadline task will be added.
     * @param message The user input containing the command and its arguments.
     * @return A string response indicating the successful addition of the Deadline task to the list,
     *         along with a count of the total tasks now present.
     * @throws DukeException If the deadline date/time is missing, or the date format is incorrect.
     */
    public static String handleDeadline(TaskList list, String message) throws DukeException {
        String[] parts = message.split("/by", 2);
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! The deadline date/time is missing buddy.");
        }
        String description = parts[0].substring(9).trim();
        String by = parts[1].trim();

        Deadline task;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(by, formatter);
            task = new Deadline(description, date);
            list.add(task);
            Ui.printWithLines("Got it. I've added this task:", task.toString(),
                    "Now you have " + list.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! The deadline date format is incorrect. Please use yyyy-MM-dd format.");
        }

        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                task, list.size());
    }

    /**
     * Parses the user input for an "event" command, creating and adding a new Event task to the task
     * list with specified start and end times.
     * The command should follow the format "event [description] /from [start time] /to [end time]"
     * where the time is in the "yyyy-MM-dd HH:mm" format.
     * Throws an exception if the command syntax is incorrect or the time format does not match the expected pattern.
     *
     * @param list The task list to which the new Event task will be added.
     * @param message The user input containing the command and its arguments.
     * @return A string response indicating the successful addition of the Event task to the list,
     *         along with a count of the total tasks now present.
     * @throws DukeException If the event time is missing, incomplete, or incorrectly formatted.
     */
    public static String handleEvent(TaskList list, String message) throws DukeException {
        String[] parts = message.split(" /from ", 2);
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new DukeException("OOPS!!! The event time is missing or incomplete buddy.");
        }
        String description = parts[0].substring(6).trim();
        String[] timeParts = parts[1].split(" /to ", 2);
        String fromTime = timeParts[0].trim();
        String toTime = timeParts[1].trim();
        Event task;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTimeFrom = LocalDateTime.parse(fromTime, formatter);
            LocalDateTime dateTimeTo = LocalDateTime.parse(toTime, formatter);
            task = new Event(description, dateTimeFrom, dateTimeTo);
            list.add(task);
            Ui.printWithLines("Got it. I've added this task:", task.toString(),
                    "Now you have " + list.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! The deadline date format is incorrect. "
                    + "Please use yyyy-MM-dd HH:mm format.");
        }
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                task, list.size());
    }

    /**
     * Generates a string representation of all tasks currently in the task list.
     * This method is used to handle the "list" command, displaying each task with its index.
     *
     * @param list The task list to be displayed.
     * @return A string listing all tasks in the task list, each prefixed by its index number.
     */
    public static String handleList(TaskList list) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            response.append(String.format("%d. %s\n", i + 1, list.get(i).toString()));
        }
        return response.toString().trim();
    }

    /**
     * Marks a specified task in the task list as done. The task to be marked is specified by an index number
     * following the "mark" command. Throws an exception if the index is missing or invalid.
     *
     * @param list The task list containing the task to mark.
     * @param message The user input containing the command and the index of the task to be marked.
     * @return A string response indicating the task has been successfully marked as done.
     * @throws DukeException If the task number is missing or invalid.
     */
    public static String handleMark(TaskList list, String message) throws DukeException {
        if (message.trim().equals("mark")) {
            throw new DukeException("OOPS!!! The task number is missing buddy.");
        }
        int index = Integer.parseInt(message.substring(5).trim()) - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeException("OOPS!!! Task number is invalid buddy.");
        }
        Task task = list.get(index);
        task.markAsDone();
        Ui.printWithLines("Nice! I've marked this task as done:", task.toString());
        return String.format("Nice! I've marked this task as done:\n%s", task);
    }

    /**
     * Marks a specified task in the task list as not done. The task to be unmarked is specified by an index number
     * following the "unmark" command. Throws an exception if the index is missing or invalid.
     *
     * @param list The task list containing the task to unmark.
     * @param message The user input containing the command and the index of the task to be unmarked.
     * @return A string response indicating the task has been successfully marked as not done.
     * @throws DukeException If the task number is missing or invalid.
     */
    public static String handleUnmark(TaskList list, String message) throws DukeException {
        if (message.trim().equals("unmark")) {
            throw new DukeException("OOPS!!! The task number is missing buddy.");
        }
        int index = Integer.parseInt(message.substring(7).trim()) - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeException("OOPS!!! Task number is invalid buddy.");
        }
        Task task = list.get(index);
        task.unMarkAsDone();
        Ui.printWithLines("OK, I've marked this task as not done yet:", task.toString());
        return String.format("OK, I've marked this task as not done yet:\n%s", task);
    }

    /**
     * Deletes a specified task from the task list. The task to be deleted is specified by an index number
     * following the "delete" command. Throws an exception if the index is missing or invalid.
     *
     * @param list The task list from which the task will be deleted.
     * @param message The user input containing the command and the index of the task to be deleted.
     * @return A string response indicating the task has been successfully deleted from the list.
     * @throws DukeException If the task number is missing or invalid.
     */
    public static String deleteTask(TaskList list, String message) throws DukeException {
        if (message.trim().equals("delete")) {
            throw new DukeException("OOPS!!! The task number is missing buddy.");
        }
        int index = Integer.parseInt(message.substring(7).trim()) - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeException("OOPS!!! Task number is invalid buddy.");
        }
        Task task = list.get(index);
        list.remove(index);
        Ui.printWithLines("OK, I've deleted this task:", task.toString());
        return String.format("OK, I've deleted this task:\n%s", task);
    }

    /**
     * Finds and lists all tasks in the task list that contain the specified keyword. The keyword follows
     * the "find" command. Throws an exception if the keyword is missing.
     *
     * @param list The task list to search for matching tasks.
     * @param message The user input containing the command and the keyword for the search.
     * @return A string listing all tasks that match the keyword, each prefixed by its index number.
     * @throws DukeException If the keyword is missing.
     */
    public static String findTask(TaskList list, String message) throws DukeException {
        if (message.trim().equals("find")) {
            throw new DukeException("OOPS!!! The keyword is missing buddy.");
        }
        String keyword = message.substring(5).trim();
        ArrayList<Task> matchingTasks = list.find(keyword);
        StringBuilder response = new StringBuilder();
        if (matchingTasks.size() == 0) {
            response.append("No matching task found.");
        } else {
            response.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append(String.format("%d. %s\n", i + 1, matchingTasks.get(i).toString()));
            }
        }
        return response.toString().trim();
    }

    /**
     * Adds a tag to a specific task in the task list. The tag is added with a "#" prefix.
     * The input message is expected to be in the format "[task number] /tag [tag]",
     * where [task number] is the 1-based index of the task in the list and [tag] is the tag to be added.
     *
     * @param list The task list to which the tag will be added.
     * @param message The input message containing the task number and tag.
     * @return A confirmation message indicating the tag has been added to the specified task.
     * @throws DukeException If the input message format is incorrect, the task number is missing,
     *                       the task number is invalid (less than 1 or greater than the size of the task list),
     *                       or the tag is missing from the input message.
     */
    public static String handleTag(TaskList list, String message) throws DukeException {
        // Split the message on " /tag " to separate the command with the task number from the tag
        String[] parts = message.split(" /tag ");
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! The tag is missing buddy.");
        }

        // Further split the first part to get the task number
        String[] commandParts = parts[0].split(" ");
        if (commandParts.length < 2) {
            throw new DukeException("OOPS!!! The task number is missing buddy.");
        }

        // Parse the task number and validate it
        int index = Integer.parseInt(commandParts[1].trim()) - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeException("OOPS!!! Task number is invalid buddy.");
        }

        // Retrieve the task and add the tag with a "#" prefix
        Task task = list.get(index);
        task.addTag("#" + parts[1].trim());

        // Print the result and return the confirmation message
        Ui.printWithLines("OK, I've added this tag to the task:", task.toString());
        return String.format("OK, I've added this tag to the task:\n%s", task);
    }

    /**
     * Removes a tag from a specific task in the task list. The task number is expected to be provided
     * in the input message in the format "removetag [task number]", where [task number] is the 1-based index
     * of the task in the list.
     *
     * @param list The task list from which the tag will be removed.
     * @param message The input message containing the task number from which the tag should be removed.
     * @return A confirmation message indicating the tag has been removed from the specified task.
     * @throws DukeException If the input message format is incorrect, the task number is missing,
     *                       or the task number is invalid (less than 1 or greater than the size of the task list).
     */
    public static String removeTag(TaskList list, String message) throws DukeException {
        if (message.trim().equals("removetag")) {
            throw new DukeException("OOPS!!! The task number is missing buddy.");
        }
        int index = Integer.parseInt(message.substring(10).trim()) - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeException("OOPS!!! Task number is invalid buddy.");
        }
        Task task = list.get(index);
        task.removeTag();
        Ui.printWithLines("OK, I've removed the tag from this task:", task.toString());
        return String.format("OK, I've removed the tag from this task:\n%s", task);
    }

}
