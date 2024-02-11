package duke.parser;

import duke.storage.Storage;
import duke.task.*;
import duke.response.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * The Parser class parses user input and executes corresponding commands.
 */
public class Parser {
    Ui response;
    Storage storage;
    TaskList taskList;

    /**
     * Constructs a Parser object with the specified Ui, Storage, and TaskList.
     *
     * @param ui        the user interface
     * @param storage   the storage handler
     * @param taskList  the task list
     */
    public Parser(Ui ui, Storage storage, TaskList taskList) {
        this.response = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Validates if a given string represents a valid date in the format "yyyy-MM-dd".
     *
     * @param dateString the string representation of the date
     * @return true if the string is a valid date, false otherwise
     */
    private static boolean checkValidDate(String dateString) {
        String parsedInPattern = "\\d{4}-\\d{2}-\\d{2}";
        return Pattern.matches(parsedInPattern, dateString);
    }

    /**
     * Validates if the start date is before the end date.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return true if the start date is before the end date, false otherwise
     */
    private static boolean checkValidDates(LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(endDate);
    }

    /**
     * Executes a user command based on the provided input.
     *
     * @param userInput the user input command
     * @throws IOException if an I/O error occurs
     */
    public String execute(String userInput) throws IOException {
        String[] words = userInput.split(" ");
        String command = words[0].toLowerCase();

        switch (command) {
        case "bye":
            storage.saveTasksToFile(taskList.getTasks());
            return response.sendGoodbye();
        case "list":
            return response.showTaskList(taskList.getTasks());
        case "done":
            if (words.length > 1) {
                int taskIndex = Integer.parseInt(words[1]);
                return taskList.markTaskAsDone(taskIndex);
            } else {
                return response.noIndexMarkAsDone();
            }
        case "undone":
            if (words.length > 1) {
                int taskIndex = Integer.parseInt(words[1]);
                return taskList.markTaskAsUndone(taskIndex);
            } else {
                return response.noIndexMarkAsUndone();
            }
        case "delete":
            if (words.length > 1) {
                int taskIndex = Integer.parseInt(words[1]);
                return taskList.deleteTask(taskIndex);
            } else {
                return response.noIndexDeleteTask();
            }
        case "todo":
            if (words.length > 1) {
                String todoDescription = userInput.substring(command.length()).trim();
                Task newTodo = new TodoTask(todoDescription);
                return taskList.addTask(newTodo);
            } else {
                return response.insufficientTodoDescription();
            }
        case "deadline":
            if (words.length > 1 && userInput.contains("/by")) {
                String deadlineDescription = userInput.substring(command.length() + 1, userInput.indexOf("/by")).trim();
                String byString = userInput.substring(userInput.indexOf("/by") + 3).trim();
                if (!checkValidDate(byString)) {
                    return response.invalidDateInput();
                }
                LocalDate by = LocalDate.parse(byString);
                Task newDeadline = new DeadlineTask(deadlineDescription, by);
                return taskList.addTask(newDeadline);
            } else {
                return response.insufficientDeadline();
            }
        case "event":
            if (words.length > 1 && userInput.contains("/from") && userInput.contains("/to")) {
                String eventDescription = userInput.substring(command.length() + 1, userInput.indexOf("/from")).trim();
                String fromString = userInput.substring(userInput.indexOf("/from") + 5, userInput.indexOf("/to")).trim();
                String toString = userInput.substring(userInput.indexOf("/to") + 3).trim();
                LocalDate from = LocalDate.parse(fromString);
                LocalDate to = LocalDate.parse(toString);
                if (!checkValidDates(from, to)) {
                    return response.invalidEventStartingTimeAndEndingTime();
                }
                Task newEvent = new EventTask(eventDescription, from, to);
                return taskList.addTask(newEvent);
            } else {
                return response.insufficientEventStartTimeEndTime();
            }
        case "search":
            if (words.length > 1) {
                String keyword = userInput.substring(command.length()).trim();
                return taskList.searchTasks(keyword);
            } else {
                return response.noKeywordsForSearching();
            }
        default:
            return response.badUserInput();
        }
    }
}

