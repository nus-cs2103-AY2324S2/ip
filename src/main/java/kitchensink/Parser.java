package kitchensink;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import kitchensink.exception.InvalidDateTimeException;
import kitchensink.exception.InvalidSyntaxException;
import kitchensink.exception.TaskNotFoundException;
import kitchensink.exception.UnknownCommandException;
import kitchensink.task.Deadline;
import kitchensink.task.Event;
import kitchensink.task.ToDo;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Converts a String to LocalDateTime, if it is in "yyyy-MM-dd HH:mm" format.
     * @param date The Date (in String type) that the user inputted.
     * @return Date in LocalDateTime type.
     */
    public LocalDateTime toLocalDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Parses the user input - if it is valid, executes it; otherwise, throws an exception.
     *
     * @param input    User input.
     * @param taskList The list that contains the user's tasks. User can manipulate the tasks it contains.
     * @param ui       The ui that confirms the user's action.
     * @param storage  The storage that saves any change to the save file.
     * @return Whether the program should exit; true if the command is "bye", false otherwise.
     * @throws UnknownCommandException  If the first word is not a valid command.
     * @throws TaskNotFoundException    If the task is not found, as the task number is out of bounds.
     * @throws IOException              As storage reads/writes save file.
     * @throws InvalidSyntaxException   If the command is valid, but not in the correct format.
     * @throws InvalidDateTimeException If the date/time given is not in the correct format.
     */
    public String parse(String input, List taskList, Ui ui, Storage storage) throws UnknownCommandException,
            TaskNotFoundException, IOException, InvalidSyntaxException, InvalidDateTimeException {
        String command = input.split(" ")[0].toLowerCase();
        switch (command) {
        case "bye":
            if (!input.equalsIgnoreCase("bye")) {
                throw new InvalidSyntaxException("bye");
            }
            return ui.sayGoodBye();
        case "list":
            if (!input.equalsIgnoreCase("list")) {
                throw new InvalidSyntaxException("list");
            }
            return ui.displayTasks(taskList);
        case "mark": {
            if (input.split(" ").length != 2) {
                throw new InvalidSyntaxException("mark");
            }
            try {
                Integer.parseInt(input.split(" ")[1]);
            } catch (Exception e) {
                throw new InvalidSyntaxException("mark");
            }
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            if (!taskList.isValidTaskNum(taskNum)) {
                throw new TaskNotFoundException(taskList);
            }
            return taskList.markTask(taskNum - 1, storage, ui);
        }
        case "unmark": {
            if (input.split(" ").length != 2) {
                throw new InvalidSyntaxException("unmark");
            }
            try {
                Integer.parseInt(input.split(" ")[1]);
            } catch (Exception e) {
                throw new InvalidSyntaxException("unmark");
            }
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            if (!taskList.isValidTaskNum(taskNum)) {
                throw new TaskNotFoundException(taskList);
            }
            return taskList.unmarkTask(taskNum - 1, storage, ui);
        }
        case "todo":
            if (input.split(" ").length <= 1) {
                throw new InvalidSyntaxException("todo");
            }
            return taskList.addTask(new ToDo(input.substring(5)), storage, ui);
        case "deadline": {
            if (!Pattern.matches("deadline .+ /by .+", input)
                    || input.split("/by").length != 2) {
                throw new InvalidSyntaxException("deadline");
            }
            try {
                toLocalDateTime(input.split("/by ")[1]);
            } catch (Exception e) {
                throw new InvalidDateTimeException();
            }
            String description = input.substring(9).split(" /by")[0];
            String dueDate = input.split("/by ")[1];
            return taskList.addTask(new Deadline(description, toLocalDateTime(dueDate)), storage, ui);
        }
        case "event":
            if (!Pattern.matches("event .+ /from .+ /to .+", input)
                    || input.split("/from").length != 2
                    || input.split("/to").length != 2) {
                throw new InvalidSyntaxException("event");
            }
            try {
                toLocalDateTime(input.split("/from ")[1].split(" /to")[0]);
                toLocalDateTime(input.split("/to ")[1]);
            } catch (Exception e) {
                throw new InvalidDateTimeException();
            }
            String description = input.substring(6).split(" /from")[0];
            String startDate = input.split("/from ")[1].split(" /to")[0];
            String endDate = input.split("/to ")[1];
            return taskList.addTask(new Event(description, toLocalDateTime(startDate), toLocalDateTime(endDate)),
                    storage, ui);
        case "delete":
            if (input.split(" ").length != 2) {
                throw new InvalidSyntaxException("delete");
            }
            try {
                Integer.parseInt(input.split(" ")[1]);
            } catch (Exception e) {
                throw new InvalidSyntaxException("delete");
            }
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            if (!taskList.isValidTaskNum(taskNum)) {
                throw new TaskNotFoundException(taskList);
            }
            return taskList.deleteTask(taskNum - 1, storage, ui);
        case "find":
            if (input.split(" ").length != 2) {
                throw new InvalidSyntaxException("find");
            }
            String keywords = input.substring(5);
            return taskList.findTasks(keywords, ui);
        default:
            throw new UnknownCommandException();
        }
    }
}
