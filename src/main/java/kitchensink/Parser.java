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
     * @return String to output in UI.
     * @throws IOException As storage reads/writes save file.
     */
    public String parse(String input, List taskList, Ui ui, Storage storage) throws IOException {
        String command = input.split(" ")[0].toLowerCase();
        switch (command) {
        case "bye":
            if (!input.equalsIgnoreCase("bye")) {
                return new InvalidSyntaxException("bye").toString();
            }
            assert input.equals("bye") || input.equals("Bye") || input.equals("bYe") || input.equals("byE")
                    || input.equals("BYe") || input.equals("ByE") || input.equals("bYE") || input.equals("BYE");
            return ui.sayGoodBye();
        case "list":
            if (!input.equalsIgnoreCase("list")) {
                return new InvalidSyntaxException("list").toString();
            }
            assert input.equals("list") || input.equals("List") || input.equals("lIst") || input.equals("liSt")
                    || input.equals("lisT") || input.equals("LIst") || input.equals("LiSt") || input.equals("LisT")
                    || input.equals("lISt") || input.equals("lIsT") || input.equals("liST") || input.equals("LISt")
                    || input.equals("LIsT") || input.equals("LiST") || input.equals("lIST") || input.equals("LIST");
            return ui.displayTasks(taskList);
        case "mark": {
            if (input.split(" ").length != 2) {
                return new InvalidSyntaxException("mark").toString();
            }
            try {
                Integer.parseInt(input.split(" ")[1]);
            } catch (NumberFormatException e) {
                return new InvalidSyntaxException("mark").toString();
            }
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            if (!taskList.isValidTaskNum(taskNum)) {
                return new TaskNotFoundException(taskList).toString();
            }
            return taskList.markTask(taskNum - 1, storage, ui);
        }
        case "unmark": {
            if (input.split(" ").length != 2) {
                return new InvalidSyntaxException("unmark").toString();
            }
            try {
                Integer.parseInt(input.split(" ")[1]);
            } catch (NumberFormatException e) {
                return new InvalidSyntaxException("unmark").toString();
            }
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            if (!taskList.isValidTaskNum(taskNum)) {
                return new TaskNotFoundException(taskList).toString();
            }
            return taskList.unmarkTask(taskNum - 1, storage, ui);
        }
        case "todo":
            if (input.split(" ").length <= 1) {
                return new InvalidSyntaxException("todo").toString();
            }
            return taskList.addTask(new ToDo(input.substring(5)), storage, ui);
        case "deadline": {
            if (!Pattern.matches("deadline .+ /by .+", input)
                    || input.split("/by").length != 2) {
                return new InvalidSyntaxException("deadline").toString();
            }
            try {
                toLocalDateTime(input.split("/by ")[1]);
            } catch (Exception e) {
                return new InvalidDateTimeException().toString();
            }
            String description = input.substring(9).split(" /by")[0];
            String dueDate = input.split("/by ")[1];
            return taskList.addTask(new Deadline(description, toLocalDateTime(dueDate)), storage, ui);
        }
        case "event":
            if (!Pattern.matches("event .+ /from .+ /to .+", input)
                    || input.split("/from").length != 2
                    || input.split("/to").length != 2) {
                return new InvalidSyntaxException("event").toString();
            }
            try {
                toLocalDateTime(input.split("/from ")[1].split(" /to")[0]);
                toLocalDateTime(input.split("/to ")[1]);
            } catch (Exception e) {
                return new InvalidDateTimeException().toString();
            }
            String description = input.substring(6).split(" /from")[0];
            String startDate = input.split("/from ")[1].split(" /to")[0];
            String endDate = input.split("/to ")[1];
            return taskList.addTask(new Event(description, toLocalDateTime(startDate), toLocalDateTime(endDate)),
                    storage, ui);
        case "delete":
            if (input.split(" ").length != 2) {
                return new InvalidSyntaxException("delete").toString();
            }
            try {
                Integer.parseInt(input.split(" ")[1]);
            } catch (NumberFormatException e) {
                return new InvalidSyntaxException("delete").toString();
            }
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            if (!taskList.isValidTaskNum(taskNum)) {
                return new TaskNotFoundException(taskList).toString();
            }
            return taskList.deleteTask(taskNum - 1, storage, ui);
        case "find":
            if (input.split(" ").length != 2) {
                return new InvalidSyntaxException("find").toString();
            }
            String keywords = input.substring(5);
            return taskList.findTasks(keywords, ui);
        default:
            assert !input.equals("bye") && !input.equals("list") && !input.equals("mark") && !input.equals("unmark")
                    && !input.equals("todo") && !input.equals("deadline") && !input.equals("event")
                    && !input.equals("delete") && !input.equals("find");
            return new UnknownCommandException().toString();
        }
    }
}
