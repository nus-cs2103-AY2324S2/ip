package duke.parser;//package duke;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class Parser {
    Ui ui;
    Storage storage;
    TaskList taskList;

    public Parser(Ui ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    private static boolean isValidDate(String dateString) {
        String parsedInPattern = "\\d{4}-\\d{2}-\\d{2}";
        return Pattern.matches(parsedInPattern, dateString);
    }
    private static boolean isValidDates(LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(endDate);
    }

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
            default:
                ui.badUserInput();
                break;
        }
    }
}

