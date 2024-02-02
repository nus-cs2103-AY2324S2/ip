package duke.parser;//package duke;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * The Parser class parses user input and executes corresponding commands.
 */
public class Parser {
    Ui ui;
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
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Validates if a given string represents a valid date in the format "yyyy-MM-dd".
     *
     * @param dateString the string representation of the date
     * @return true if the string is a valid date, false otherwise
     */
    private static boolean isValidDate(String dateString) {
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
    private static boolean isValidDates(LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(endDate);
    }

    /**
     * Executes a user command based on the provided input.
     *
     * @param userInput the user input command
     * @throws IOException if an I/O error occurs
     */
    public void execute(String userInput) throws IOException {
        String[] words = userInput.split(" ");
        String command = words[0].toLowerCase();

        switch (command) {
            case "bye":
                this.storage.saveTasksToFile(taskList.getTasks());
                this.ui.sendGoodbye();
                return;
            case "list":
                this.ui.showTaskList(taskList.getTasks());
                break;
            case "done":
                if (words.length > 1) {
                    int taskIndex = Integer.parseInt(words[1]);
                    this.taskList.markTaskAsDone(taskIndex);
                } else {
                    this.ui.noIndexMarkAsDone();
                }
                break;
            case "undone":
                if (words.length > 1) {
                    int taskIndex = Integer.parseInt(words[1]);
                    this.taskList.markTaskAsUndone(taskIndex);
                } else {
                    this.ui.noIndexMarkAsUndone();
                }
                break;
            case "delete":
                if (words.length > 1) {
                    int taskIndex = Integer.parseInt(words[1]);
                    this.taskList.deleteTask(taskIndex);
                } else {
                    this.ui.noIndexDeleteTask();
                }
                break;
            case "todo":
                if (words.length > 1) {
                    String todoDescription = userInput.substring(command.length()).trim();
                    Task newTodo = new TodoTask(todoDescription);
                    this.taskList.addTask(newTodo);
                } else {
                    this.ui.insufficientTodoDescription();
                }
                break;
            case "deadline":
                if (words.length > 1 && userInput.contains("/by")) {
                    String deadlineDescription = userInput.substring(command.length() + 1, userInput.indexOf("/by")).trim();
                    String byString = userInput.substring(userInput.indexOf("/by") + 3).trim();
                    if (!isValidDate(byString)) {
                        this.ui.invalidDateInput();
                        return;
                    }
                    LocalDate by = LocalDate.parse(byString);
                    Task newDeadline = new DeadlineTask(deadlineDescription, by);
                    this.taskList.addTask(newDeadline);
                } else {
                    this.ui.insufficientDeadline();
                }
                break;
            case "event":
                if (words.length > 1 && userInput.contains("/from") && userInput.contains("/to")) {
                    String eventDescription = userInput.substring(command.length() + 1, userInput.indexOf("/from")).trim();
                    String fromString = userInput.substring(userInput.indexOf("/from") + 5, userInput.indexOf("/to")).trim();
                    String toString = userInput.substring(userInput.indexOf("/to") + 3).trim();
                    LocalDate from = LocalDate.parse(fromString);
                    LocalDate to = LocalDate.parse(toString);
                    if (!isValidDates(from, to)) {
                        this.ui.invalidEventStartingTimeAndEndingTime();
                        return;
                    }
                    Task newEvent = new EventTask(eventDescription, from, to);
                    this.taskList.addTask(newEvent);
                } else {
                    this.ui.insufficientEventStartTimeEndTime();
                }
                break;
            case "search":
                if (words.length > 1) {
                    String keyword = userInput.substring(command.length()).trim();
                    this.taskList.searchTasks(keyword);
                } else {
                    this.ui.noKeywordsForSearching();
                }
                break;
            default:
                ui.badUserInput();
                break;
        }
    }
}

