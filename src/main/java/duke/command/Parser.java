package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

import java.io.IOException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * The Parser class handles the parsing of user commands and executes corresponding actions.
 * It interacts with the TaskList, Storage, and Ui classes to perform operations like adding tasks,
 * marking tasks as done, deleting tasks, and displaying the task list.
 */
public class Parser {

    private String command;
    private boolean hasChanged = false;
    private Ui ui = new Ui();
    private TaskList tasks;
    protected Storage storage;
    protected Scanner scanner;
    protected boolean isRunning;
    private String response;
    /**
     * Constructs a Parser instance with the specified TaskList, Storage, Scanner, and running status.
     *
     * @param tasks     The TaskList to be managed by the parser.
     * @param storage   The Storage object to handle data storage and retrieval.
     * @param scanner   The Scanner object to read user input.
     * @param isRunning The initial running status of the parser.
     */
    public Parser(TaskList tasks, Storage storage, Scanner scanner, boolean isRunning) {
        this.tasks = tasks;
        this.storage = storage;
        this.scanner = scanner;
        this.isRunning = isRunning;
    }

    /**
     * Gets the running status of the parser.
     *
     * @return True if the parser is running, false otherwise.
     */
    public boolean getRunningStatus() {
        return this.isRunning;
    }

    /**
     * Sets the command to be processed by the parser.
     *
     * @param command The user command to be processed.
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Processes the user command by executing the corresponding action.
     * It handles commands like "bye," "list," "mark," "unmark," "delete," "todo," "deadline," and "event."
     *
     * @throws DukeException If there is an issue processing the command.
     */
    public void processCommand() throws DukeException {
        String[] words = command.split("\\s+");

        try {
            ui.printDashes();
            switch (words[0].toLowerCase()) {
                case "bye":
                    this.response = ui.showGoodbyeMessage();
                    this.isRunning = false;
                    break;
                case "list":
                    this.response = Ui.showTaskList(tasks) + "\n" + TaskList.getList(tasks);
                    break;
                case "mark":
                    if (words.length > 1) {
                        int index = Integer.parseInt(words[1]) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            Task task = tasks.getTask(index);
                            this.response = tasks.markStatus(task);
                            storage.saveTasks(tasks);
                            hasChanged = true; //flag, no need to change
                        } else {
                            String reply = "You have not created a task " + words[1] + " yet!";
                            Ui.showError(reply);
                            this.response = reply;
                        }
                    }
                    break;
                case "unmark":
                    if (words.length > 1) {
                        int index = Integer.parseInt(words[1]) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            Task task = tasks.getTask(index);
                            this.response = tasks.unmarkStatus(task);
                            storage.saveTasks(tasks);
                            hasChanged = true; //flag, no need to change
                        } else {
                            String reply = "You have not created a task " + words[1] + " yet!";
                            Ui.showError(reply);
                            this.response = reply;                        }
                    }
                    break;
                case "delete":
                    if (words.length > 1) {
                        this.response = tasks.deleteTask(Integer.parseInt(words[1]) - 1)
                    + "\n" + Ui.printNumberOfTasks(tasks);
                        storage.saveTasks(tasks);
                        hasChanged = true; //flag, no need to change
                    }
                    break;
                case "todo":
                    if (command.length() <= 5) {
                        String reply = "You gotta enter some task TO DO!";
                        ui.showError(reply);
                        this.response = reply;
                    } else {
                        ToDo work = new ToDo(command.substring(5).trim(), ui);
                        tasks.addToDoTask(work);
                        storage.saveTasks(tasks);
                        this.response = work.getMessage() + "\n" + ui.printNumberOfTasks(tasks);
                        hasChanged = true;
                    }
                    break;
                case "deadline":
                    Matcher byMatcher = Pattern.compile("/by\\s+(\\S.+)").matcher(command);
                    String deadlineDescription = "";
                    Matcher deadlineDescMatcher = Pattern.compile("deadline\\s+(.+?)\\s*/by").matcher(command);
                    if (words.length > 1) {
                        if (deadlineDescMatcher.find()) {
                            deadlineDescription = deadlineDescMatcher.group(1).trim();
                        }
                    } else {
                        String reply = "Please include both task description and deadline correctly!";
                        ui.showError(reply);
                        this.response = reply;
                        break;
                    }
                    String by = byMatcher.find() ? byMatcher.group(1).trim() : "";
                    Deadline work = new Deadline(deadlineDescription, by, ui);
                    tasks.addDeadlineTask(work);
                    storage.saveTasks(tasks);
                    this.response = work.getMessage() + "\n" + Ui.printNumberOfTasks(tasks);
                    hasChanged = true;
                    break;
                case "event":
                    Matcher fromMatcher = Pattern.compile("/from\\s+(\\S+[^/]+)").matcher(command);
                    Matcher toMatcher = Pattern.compile("/to\\s+(\\S.+)").matcher(command);
                    String eventDescription = "";
                    Matcher descriptionMatcher = Pattern.compile("event\\s+(.+?)\\s*/from").matcher(command);
                    if (words.length > 1) {
                        if (descriptionMatcher.find()) {
                            eventDescription = descriptionMatcher.group(1).trim();
                        }
                    } else {
                        String reply = "You didn't enter the details or duration!";
                        Ui.showError(reply);
                        this.response = reply;
                        break;
                    }
                    String from = fromMatcher.find() ? fromMatcher.group(1).trim() : "";
                    String to = toMatcher.find() ? toMatcher.group(1).trim() : "";
                    Event job = new Event(eventDescription, from, to, ui);
                    tasks.addEventTask(job);
                    storage.saveTasks(tasks);
                    this.response = job.getMessage() + "\n" + Ui.printNumberOfTasks(tasks);
                    hasChanged = true;
                    break;
                case "find":
                    if (command.length() <= 5) {
                        String reply = "Find what? Please re-enter correctly!";
                        ui.showError(reply);
                        this.response = reply;
                    } else {
                        String taskToFind = command.substring(5).trim();
                        TaskList foundTasks = tasks.findTask(taskToFind);
                        if (foundTasks.size() > 0) {
                            this.response = ui.showFoundTasks(taskToFind) + "\n" +
                                    tasks.getList(foundTasks);
                        } else {
                            this.response = ui.showNoTasksFound(taskToFind);
                        }
                    }
                    break;
                default:
                    String reply = "I don't know what you are saying :(";
                    ui.showError(reply);
                    this.response = reply;
                    break;


            }
        } catch (DukeException | IOException e) {
            ui.showError(e.getMessage());
            this.response = e.getMessage();
        } finally {
            ui.printDashes();
        }

        assert response != null : "Response should not be null after processing the command.";
        assert isRunning == false || isRunning == true : "Running status should be either true or false.";
    }

    public String getResponse() {
        return this.response;
    }

}