package hammy.parser;

import hammy.storage.Storage;
import hammy.task.*;
import hammy.response.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
        case "hi":
        case "hello":
            return this.response.sendWelcome();
        case "save":
            this.storage.saveTasksToFile(taskList.getTasks());
            return response.savedTasks();
        case "list":
        case "/ls":
            ArrayList<Task> tasks = taskList.getTasks();
            if (words.length == 1) {
                return response.showTaskList(tasks);
            }
            String listMethod = userInput.substring(command.length()).trim();
            if (listMethod.equals("done")) {
                return response.showDoneTaskList(tasks);
            } else if (listMethod.equals("undone")) {
                return response.showUndoneTaskList(tasks);
            } else if (listMethod.equals("undone top") || listMethod.equals("top undone")) {
                return response.undoneDoneList(tasks);
            } else if (listMethod.equals("done top") || listMethod.equals("top done")) {
                return response.doneUndoneList(tasks);
            } else if (listMethod.equals("a") || listMethod.equals("alphabetically")) {
                return response.sortAlphabetically(tasks);
            } else {
                return response.showTaskList(tasks);
            }
        case "done":
            if (words.length == 1) {
                return response.noIndexMarkAsDone();
            }
            int doneTaskIndex = Integer.parseInt(words[1]);
            return this.taskList.markTaskAsDone(doneTaskIndex);
        case "undone":
            if (words.length == 1) {
                return response.noIndexMarkAsUndone();
            }
            int undoneTaskIndex = Integer.parseInt(words[1]);
            return this.taskList.markTaskAsUndone(undoneTaskIndex);
        case "delete":
        case "/del":
            if (words.length == 1) {
                return response.noIndexDeleteTask();
            }
            int deleteTaskIndex = Integer.parseInt(words[1]);
            return this.taskList.deleteTask(deleteTaskIndex);
        case "clear":
            return this.taskList.clearTasks();
        case "todo":
        case "/t":
            if (words.length == 1) {
                return response.insufficientTodoDescription();
            }
            String todoDescription = userInput.substring(command.length()).trim();
            Task newTodo = new TodoTask(todoDescription);
            return this.taskList.addTask(newTodo);
        case "deadline":
        case "/d":
            if (words.length == 1 || !userInput.contains("/by")) {
                return response.insufficientDeadline();
            }
            String deadlineDescription = userInput.substring(command.length() + 1, userInput.indexOf("/by")).trim();
            String byString = userInput.substring(userInput.indexOf("/by") + 3).trim();
            if (!checkValidDate(byString)) {
                return response.invalidDateInput();
            }
            LocalDate by = LocalDate.parse(byString);
            Task newDeadline = new DeadlineTask(deadlineDescription, by);
            return this.taskList.addTask(newDeadline);
        case "event":
        case "/e":
            if (words.length == 1 || (!userInput.contains("/from") && !userInput.contains("/to"))) {
                return response.insufficientEventStartTimeEndTime();
            }
            if (!userInput.contains("/from")) {
                return response.insufficientEventStartTime();
            }
            if (!userInput.contains("/to")) {
                return response.insufficientEventEndTime();
            }
            String eventDescription = userInput.substring(command.length() + 1, userInput.indexOf("/from")).trim();
            String fromString = userInput.substring(userInput.indexOf("/from") + 5, userInput.indexOf("/to")).trim();
            String toString = userInput.substring(userInput.indexOf("/to") + 3).trim();
            LocalDate from = LocalDate.parse(fromString);
            LocalDate to = LocalDate.parse(toString);
            if (!checkValidDates(from, to)) {
                return response.invalidEventStartingTimeAndEndingTime();
            }
            Task newEvent = new EventTask(eventDescription, from, to);
            return this.taskList.addTask(newEvent);
        case "search":
        case "/s":
            if (words.length == 1) {
                return response.noKeywordsForSearching();
            }
            String keyword = userInput.substring(command.length()).trim();
            return this.taskList.searchTasks(keyword);
        case "help":
            return response.help();
        case "hammy":
            return response.getHammyMessage();
        default:
            return response.badUserInput();

        }
    }
}

