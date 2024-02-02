package duke.parser;

import duke.task.Task;
import duke.task.ToDos;
import duke.task.Deadlines;
import duke.task.Events;
import duke.tasklist.TaskList;
import duke.exception.DukeException;
import duke.ui.Ui;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Parser class is responsible for interpreting user input and converting it into
 * commands that can be executed by the Duke application. It also handles parsing
 * of task data from and to the storage format.
 */
public class Parser {
    
    /**
     * Parses the input command to identify the task to be marked as done.
     *
     * @param input The user input string.
     * @param list The TaskList containing all tasks.
     * @return The Task to be marked as done.
     */
    public Task getTaskTobeMarked(String input, TaskList list) {
        int index = Integer.parseInt(input.substring(5)) - 1;
        return list.getTask(index);
    }
    
    /**
     * Parses the input command to identify the task to be unmarked (marked as not done).
     *
     * @param input The user input string.
     * @param list The TaskList containing all tasks.
     * @return The Task to be unmarked.
     */
    public Task getTaskToBeUnmarked(String input, TaskList list) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        return list.getTask(index);
    }
    
    /**
     * Parses the input command to identify the task to be deleted.
     *
     * @param input The user input string.
     * @param list The TaskList containing all tasks.
     * @return The Task to be deleted.
     */
    public Task getTaskToDelete(String input, TaskList list) {
        int indexOfTaskToDelete = Integer.parseInt(input.substring(7)) - 1;
        return list.getTask(indexOfTaskToDelete);
    }
    
    /**
     * Creates a ToDo task from the input command.
     *
     * @param input The user input string.
     * @return A new ToDo Task.
     */
    public Task createToDo(String input) {
        String todoDescription = input.substring(5);
        return new ToDos(todoDescription);
    }
    
    /**
     * Creates a Deadline task from the input command, including parsing the due date.
     *
     * @param input The user input string.
     * @param ui The UI instance to handle user interactions.
     * @return A new Deadline Task.
     * @throws DukeException If the input date is invalid or the input format is incorrect.
     */
    public Task createDeadline(String input, Ui ui) throws DukeException{
        int indexOfBy = input.indexOf("/by");
        String deadlineDescription = input.substring(9, indexOfBy - 1);
        String deadline = input.substring(indexOfBy + 4);
        LocalDate by = this.formatInputDate(deadline);
        ui.handleInvalidInputDate(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy"), by);
        return new Deadlines(deadlineDescription, by);
    }
    
    /**
     * Creates an Event task from the input command, including parsing the start and end dates.
     *
     * @param input The user input string.
     * @param ui The UI instance to handle user interactions.
     * @return A new Event Task.
     * @throws DukeException If any input date is invalid or the input format is incorrect.
     */
    public Task createEvent(String input, Ui ui) throws DukeException{
        int indexOfFrom = input.indexOf("/from");
        int indexOfTo = input.indexOf("/to");
        String eventDescription = input.substring(6, indexOfFrom - 1);
        String startString = input.substring(indexOfFrom + 6, indexOfTo - 1);
        String endString = input.substring(indexOfTo + 4);
        LocalDate start = this.formatInputDate(startString);
        LocalDate end = this.formatInputDate(endString);
        ui.handleInvalidInputDate(startString, DateTimeFormatter.ofPattern("dd/MM/yyyy"), start);
        ui.handleInvalidInputDate(endString, DateTimeFormatter.ofPattern("dd/MM/yyyy"), end);
        return new Events(eventDescription, start, end);
    }
    
    /**
     * Parses a string representation of a task from the storage file into a Task object.
     *
     * @param line The string representation of the task from the storage file.
     * @return The corresponding Task object.
     */
    public Task parseStringToTask(String line) {
        char typeOfTask = line.charAt(1);
        char status = line.charAt(4);
        Task t = null;
        if (typeOfTask == 'T') {
            String description = line.substring(7);
            t = new ToDos(description);
        } else if (typeOfTask == 'D') {
            int indexOfTime = line.indexOf("(by: ");
            String description = line.substring(7, indexOfTime - 1);
            String byString = line.substring(indexOfTime + 5, line.indexOf(")"));
            LocalDate by = parseFileDateToStorageDate(byString);
            t = new Deadlines(description, by);
        } else if (typeOfTask == 'E') {
            int indexOfStartTime = line.indexOf("(from");
            int indexOfEndTime = line .indexOf("to");
            String description = line.substring(7, indexOfStartTime - 1);
            String startString = line.substring(indexOfStartTime + 6, indexOfEndTime - 1);
            String endString = line.substring(indexOfEndTime + 3, line.indexOf(")"));
            LocalDate start = parseFileDateToStorageDate(startString);
            LocalDate end = parseFileDateToStorageDate(endString);
            t = new Events(description, start, end);
        }
        boolean doneOrNot = (status == 'X');
        t.setStatus(doneOrNot);
        return t;
    }
    
    /**
     * Converts a date string from the file storage format into a LocalDate object.
     *
     * @param fileDate The date string in the file storage format.
     * @return The LocalDate object corresponding to the date string.
     */
    public LocalDate parseFileDateToStorageDate(String fileDate) {
        DateTimeFormatter fileDateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate date = LocalDate.parse(fileDate, fileDateFormatter);
        DateTimeFormatter storageDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String acceptedDate = date.format(storageDateFormatter);
        return LocalDate.parse(acceptedDate);
    }
    
    /**
     * Formats an input date string from the user into a LocalDate object.
     *
     * @param input The date string input by the user.
     * @return The LocalDate object formatted according to the application's storage format.
     * @throws DukeException If the input date format is invalid.
     */
    public LocalDate formatInputDate(String input) throws DukeException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(input, inputFormatter);
        DateTimeFormatter storageFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String acceptedDate = date.format(storageFormatter);
        return LocalDate.parse(acceptedDate);
    }
}
