package cruisey.command;
import static com.sun.javafx.application.PlatformImpl.exit;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cruisey.exception.DukeException;
import cruisey.storage.Storage;
import cruisey.task.Deadline;
import cruisey.task.Event;
import cruisey.task.Task;
import cruisey.task.TaskList;
import cruisey.task.TaskPriority;
import cruisey.task.ToDo;
import cruisey.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * The Parser class handles the parsing of user commands and executes corresponding actions.
 * It interacts with the TaskList, Storage, and Ui classes to perform operations like adding tasks,
 * marking tasks as done, deleting tasks, and displaying the task list.
 */
public class Parser {

    static final int VALID_COMMAND_LENGTH = 1;
    static final int FULL_TODO_COMMAND_LENGTH = 5;
    static final int FULL_FIND_COMMAND_LENGTH = 5;
    protected Storage storage;
    protected Scanner scanner;
    protected boolean isRunning;
    private String command;
    private boolean hasChanged = false;
    private Ui ui = new Ui();
    private TaskList tasks;
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
     * Processes the user command by splitting it into words, executing the corresponding action,
     * and handling any exceptions that may occur. The processed response is then printed with
     * appropriate formatting.
     *
     * @throws DukeException If there is an issue processing the command.
     */
    public void processCommand() throws DukeException {
        String[] words = command.split("\\s+");
        try {
            ui.printDashes();
            handleCommand(words);
        } catch (DukeException | IOException e) {
            handleError(e);
        } finally {
            ui.printDashes();
        }
        assert response != null : "Response should not be null after processing the command.";
        assert isRunning == false || isRunning == true : "Running status should be either true or false.";
    }

    /**
     * Handles the specific user command by identifying the first word and dispatching
     * the execution to the corresponding method.
     *
     * @param words An array of words obtained by splitting the user command.
     * @throws DukeException If there is an issue handling the command.
     * @throws IOException  If an I/O error occurs during command execution.
     */
    private void handleCommand(String[] words) throws DukeException, IOException {
        final String firstWord = words[0].toLowerCase();
        switch (firstWord) {
        case "bye":
            handleByeCommand();
            exitWithDelay();
            break;
        case "list":
            handleListCommand();
            break;
        case "mark":
            handleMarkCommand(words);
            break;
        case "unmark":
            handleUnmarkCommand(words);
            break;
        case "delete":
            handleDeleteCommand(words);
            break;
        case "todo":
            handleToDoCommand();
            break;
        case "deadline":
            handleDeadlineCommand(words);
            break;
        case "event":
            handleEventCommand(words);
            break;
        case "find":
            handleFindCommand();
            break;
        default:
            handleDefaultCommand();
            break;
        }
    }

    /**
     * Exits the application after introducing a delay.
     *
     * This method schedules the exit code to run on the JavaFX Application Thread
     * after a specified delay using a {@link PauseTransition}.
     *
     * Note: The delay is set to 2 seconds (2000 milliseconds) by default.
     */
    private void exitWithDelay() {
        Duration delayDuration = Duration.seconds(2);
        PauseTransition pause = new PauseTransition(delayDuration);
        pause.setOnFinished(event -> exit());
        pause.play();
    }

    /**
     * Handles the 'bye' command, sets the response to the goodbye message, and marks the application
     * as not running.
     */
    private void handleByeCommand() {
        this.response = ui.showGoodbyeMessage();
        isRunning = false;
    }

    /**
     * Handles the 'list' command, sets the response to the formatted task list, and prints the
     * number of tasks.
     *
     * @throws DukeException If there is an issue handling the 'list' command.
     */
    private void handleListCommand() throws DukeException {
        this.response = Ui.showTaskList(tasks) + "\n" + TaskList.getList(tasks);
    }

    /**
     * Handles the 'mark' command by updating the status of a task to done, saves the tasks, and sets
     * the hasChanged flag.
     *
     * @param words An array of words obtained by splitting the user command.
     * @throws IOException  If an I/O error occurs during command execution.
     * @throws DukeException If there is an issue handling the 'mark' command.
     */
    private void handleMarkCommand(String[] words) throws IOException, DukeException {
        if (words.length <= VALID_COMMAND_LENGTH) {
            String error = "mark what? Please re-enter correctly with the task number!";
            Ui.showError(error);
            this.response = error;
            return;
        }
        int taskNumber = Integer.parseInt(words[1]) - 1;
        boolean isExistingIndex = taskNumber >= 0;
        boolean isPresent = taskNumber < tasks.size();
        if (!(isExistingIndex && isPresent)) {
            String reply = "you have not created a task " + words[1] + " yet!";
            Ui.showError(reply);
            this.response = reply;
        } else {
            Task task = tasks.getTask(taskNumber);
            this.response = tasks.markStatus(task);
            storage.saveTasks(tasks);
            hasChanged = true; //flag, no need to change
        }
    }

    /**
     * Handles the 'unmark' command by updating the status of a task to undone, saves the tasks, and sets
     * the hasChanged flag.
     *
     * @param words An array of words obtained by splitting the user command.
     * @throws IOException  If an I/O error occurs during command execution.
     * @throws DukeException If there is an issue handling the 'unmark' command.
     */
    private void handleUnmarkCommand(String[] words) throws IOException, DukeException {
        if (words.length <= VALID_COMMAND_LENGTH) {
            String error = "unmark what? Please re-enter correctly with the task number!";
            Ui.showError(error);
            this.response = error;
            return;
        }
        int taskNumber = Integer.parseInt(words[1]) - 1;
        boolean isExistingIndex = taskNumber >= 0;
        boolean isPresent = taskNumber < tasks.size();
        if (!(isExistingIndex && isPresent)) {
            String reply = "you have not created a task " + words[1] + " yet!";
            Ui.showError(reply);
            this.response = reply;
        } else {
            Task task = tasks.getTask(taskNumber);
            this.response = tasks.unmarkStatus(task);
            storage.saveTasks(tasks);
            hasChanged = true; //flag, no need to change
        }
    }

    /**
     * Processes a given task by adding it to the appropriate task list (ToDo, Deadline, or Event) in the TaskList.
     * The method then saves the updated task list to the storage file, updates the response message, prints the
     * number of tasks, and sets the 'hasChanged' flag to true.
     *
     * @param work The task to be processed.
     * @throws IOException If an I/O error occurs during the processing of the task.
     */
    private void processTask(Task work) throws IOException {
        if (work instanceof ToDo) {
            tasks.addToDoTask((ToDo) work);
        } else if (work instanceof Deadline) {
            tasks.addDeadlineTask((Deadline) work);
        } else if (work instanceof Event) {
            tasks.addEventTask((Event) work);
        }
        storage.saveTasks(tasks);
        this.response = work.getMessage() + "\n" + Ui.printNumberOfTasks(tasks);
        hasChanged = true;
    }

    /**
     * Handles the 'delete' command by removing a task, saves the tasks, and sets the hasChanged flag.
     *
     * @param words An array of words obtained by splitting the user command.
     * @throws DukeException If there is an issue handling the 'delete' command.
     * @throws IOException  If an I/O error occurs during command execution.
     */
    private void handleDeleteCommand(String[] words) throws DukeException, IOException {
        int taskNumber = Integer.parseInt(words[1]) - 1;
        if (words.length <= VALID_COMMAND_LENGTH) {
            String error = "delete what? Please re-enter correctly with the task number!";
            Ui.showError(error);
            this.response = error;
            return;
        } else if (taskNumber > tasks.size()) {
            String error = "you have not created a task " + words[1] + " for me to delete yet!";
            Ui.showError(error);
            this.response = error;
            return;
        }
        this.response = tasks.deleteTask(taskNumber)
                + "\n" + Ui.printNumberOfTasks(tasks);
        storage.saveTasks(tasks);
        hasChanged = true;
    }

    /**
     * Handles the 'todo' command by creating a new ToDo task, adding it to the task list,
     * saving the tasks, and setting the hasChanged flag.
     *
     * @throws DukeException If there is an issue handling the 'todo' command.
     * @throws IOException  If an I/O error occurs during command execution.
     */
    private void handleToDoCommand() throws DukeException, IOException {
        Matcher priorityMatcher = Pattern.compile("/p\\s+(\\S.+)").matcher(command);
        String priority = priorityMatcher.find() ? priorityMatcher.group(1).trim() : "";

        Matcher toDoMatcher = Pattern.compile("todo\\s+(\\S.+?)(?:\\s*/p|$)").matcher(command);
        String todoTask = toDoMatcher.find() ? toDoMatcher.group(1).trim() : "";

        if (command.length() <= FULL_TODO_COMMAND_LENGTH) {
            String reply = "you gotta enter some task TO DO!";
            ui.showError(reply);
            this.response = reply;
            return;
        }

        ToDo work = new ToDo(todoTask, ui, priority.isEmpty() ? null : TaskPriority.valueOf(priority.toUpperCase()));
        processTask(work);
    }

    /**
     * Handles the 'deadline' command by creating a new Deadline task, adding it to the task list,
     * saving the tasks, and setting the hasChanged flag.
     *
     * @param words An array of words obtained by splitting the user command.
     * @throws DukeException If there is an issue handling the 'deadline' command.
     * @throws IOException  If an I/O error occurs during command execution.
     */
    private void handleDeadlineCommand(String[] words) throws DukeException, IOException {
        Matcher priorityMatcher = Pattern.compile("/p\\s+(\\S.+)").matcher(command);
        String priority = priorityMatcher.find() ? priorityMatcher.group(1).trim() : "";

        Matcher byMatcher = Pattern.compile("/by\\s+(\\S.+?)(?:\\s*/p|$)").matcher(command);
        String deadlineDescription = findDeadlineDescription(command);

        if (words.length <= 1) {
            String reply = "please include both task description and deadline correctly!";
            ui.showError(reply);
            this.response = reply;
            return;
        }

        String by = byMatcher.find() ? byMatcher.group(1).trim() : "";
        Deadline work = new Deadline(deadlineDescription, by, ui, priority.isEmpty() ? null
                : TaskPriority.valueOf(priority.toUpperCase()));
        processTask(work);
    }

    /**
     * Finds and extracts the description from a 'deadline' command.
     *
     * The method uses a regular expression to locate the description specified in the 'deadline' command.
     * It searches for the substring following the "deadline" keyword and ending just before the "/by" keyword.
     *
     * @param command The user input command containing the 'deadline' task details.
     * @return The extracted description from the 'deadline' command, or an empty string if not found.
     */
    private String findDeadlineDescription(String command) {
        Matcher deadlineDescMatcher = Pattern.compile("deadline\\s+(.+?)\\s*/by").matcher(command);
        return deadlineDescMatcher.find() ? deadlineDescMatcher.group(1).trim() : "";
    }

    /**
     * Handles the 'event' command by creating a new Event task, adding it to the task list,
     * saving the tasks, and setting the hasChanged flag.
     *
     * @param words An array of words obtained by splitting the user command.
     * @throws DukeException If there is an issue handling the 'event' command.
     * @throws IOException  If an I/O error occurs during command execution.
     */
    private void handleEventCommand(String[] words) throws DukeException, IOException {
        Matcher priorityMatcher = Pattern.compile("/p\\s+(\\S.+)").matcher(command);
        String priority = priorityMatcher.find() ? priorityMatcher.group(1).trim() : "";

        Matcher fromMatcher = Pattern.compile("/from\\s+(\\S+[^/]+)").matcher(command);
        String eventDescription = findEventDescription(command);

        if (words.length <= 1) {
            String reply = "you didn't enter the details or duration!";
            Ui.showError(reply);
            this.response = reply;
            return;
        }

        String from = fromMatcher.find() ? fromMatcher.group(1).trim() : "";
        String to = findEventTo(command, priority);

        Event job = new Event(eventDescription, from, to, ui, priority.isEmpty() ? null
                : TaskPriority.valueOf(priority.toUpperCase()));
        processTask(job);
    }

    /**
     * Finds and extracts the event description from an 'event' command.
     *
     * The method uses a regular expression to locate the description specified in the 'event' command.
     * It searches for the substring following the "event" keyword and ending just before the "/from" keyword.
     *
     * @param command The user input command containing the 'event' task details.
     * @return The extracted event description from the 'event' command, or an empty string if not found.
     */
    private String findEventDescription(String command) {
        Matcher descriptionMatcher = Pattern.compile("event\\s+(.+?)\\s*/from").matcher(command);
        return descriptionMatcher.find() ? descriptionMatcher.group(1).trim() : "";
    }

    /**
     * Finds and extracts the 'to' information from an 'event' command.
     *
     * The method uses a regular expression to locate the 'to' information specified in the 'event' command.
     * It searches for the substring following the "/to" keyword and ending either at the next specified marker ("/p")
     * or at the end of the command.
     *
     * @param command  The user input command containing the 'event' task details.
     * @param priority The priority extracted from the command.
     * @return The extracted 'to' information from the 'event' command, or an empty string if not found.
     */
    private String findEventTo(String command, String priority) {
        Matcher toMatcher = Pattern.compile("/to\\s+(\\S.+?)(?:\\s*/p|$)").matcher(command);
        return toMatcher.find() ? toMatcher.group(1).trim() : "";
    }

    /**
     * Handles the 'find' command by searching for tasks containing a specific keyword,
     * displaying the found tasks, and printing the number of tasks.
     *
     * @throws DukeException If there is an issue handling the 'find' command.
     */
    private void handleFindCommand() throws DukeException {
        if (command.length() <= FULL_FIND_COMMAND_LENGTH) {
            String reply = "find what? Please re-enter correctly!";
            ui.showError(reply);
            this.response = reply;
        } else {
            String taskToFind = command.substring(5).trim();
            TaskList foundTasks = tasks.findTask(taskToFind);
            if (foundTasks.size() > 0) {
                this.response = ui.showFoundTasks(taskToFind) + "\n" + tasks.getList(foundTasks);
            } else {
                this.response = ui.showNoTasksFound(taskToFind);
            }
        }
    }

    /**
     * Handles default commands by displaying an error message indicating that the command is not recognized.
     *
     * @throws DukeException If there is an issue handling the default command.
     */
    private void handleDefaultCommand() throws DukeException {
        String reply = "I don't know what you are saying :(";
        ui.showError(reply);
        this.response = reply;
    }

    /**
     * Handles exceptions by displaying the error message to the user and setting the response.
     *
     * @param e The exception that occurred.
     * @throws DukeException If there is an issue handling the error.
     */
    private void handleError(Exception e) throws DukeException {
        ui.showError(e.getMessage());
        this.response = e.getMessage();
    }

    /**
     * Retrieves the response generated after processing the user command.
     *
     * @return The response message generated during command processing.
     */
    public String getResponse() {
        return this.response;
    }

}
