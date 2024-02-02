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

public class Parser {
    public LocalDateTime toLocalDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
    public boolean parse(String input, List taskList, Ui ui, Storage storage) throws UnknownCommandException,
            TaskNotFoundException, IOException, InvalidSyntaxException, InvalidDateTimeException {
        String command = input.split(" ")[0].toLowerCase();
        switch (command) {
        case "bye":
            if (!input.equalsIgnoreCase("bye")) {
                throw new InvalidSyntaxException("bye");
            }
            ui.sayGoodBye();
            return true;
        case "list":
            if (!input.equalsIgnoreCase("list")) {
                throw new InvalidSyntaxException("list");
            }
            ui.displayTasks(taskList);
            break;
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
            if (!taskList.validTaskNum(taskNum)) {
                throw new TaskNotFoundException(taskList);
            }
            taskList.markTask(taskNum - 1, storage);
            break;
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
            if (!taskList.validTaskNum(taskNum)) {
                throw new TaskNotFoundException(taskList);
            }
            taskList.unmarkTask(taskNum - 1, storage);
            break;
        }
        case "todo":
            if (input.split(" ").length <= 1) {
                throw new InvalidSyntaxException("todo");
            }
            taskList.addTask(new ToDo(input.substring(5)), storage, ui);
            break;
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
            taskList.addTask(new Deadline(description, toLocalDateTime(dueDate)), storage, ui);
            break;
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
            taskList.addTask(new Event(description, toLocalDateTime(startDate), toLocalDateTime(endDate)), storage, ui);
            break;
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
            if (!taskList.validTaskNum(taskNum)) {
                throw new TaskNotFoundException(taskList);
            }
            taskList.deleteTask(taskNum - 1, storage, ui);
            break;
        default:
            throw new UnknownCommandException();
        }
        return false;
    }
}
