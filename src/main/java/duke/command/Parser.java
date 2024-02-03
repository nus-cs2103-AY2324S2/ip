package duke.command;

import duke.conversation.Conversation;
import duke.task.Task;
import duke.task.TaskDisplay;
import duke.task.TaskManager;
import duke.task.TaskType;
import duke.ui.Ui;

import java.util.List;

/**
 * The Parser class is responsible for parsing user commands and interacting with the TaskManager.
 * It interprets the input commands and executes corresponding actions.
 */
public class Parser {
    TaskManager taskManager;
    Conversation conversation;
    private final String username;

    TaskDisplay taskDisplay = new TaskDisplay();

    private static final String DELETE_ALL_COMMAND = "delete all";

    /**
     * Constructs a Parser with the specified username. Initializes the TaskManager and Conversation.
     *
     * @param username The username for which the Parser is created.
     */
    public Parser(String username) {
        taskManager = new TaskManager(username);
        conversation = new Conversation(username);
        this.username = username;
    }

    /**
     * Parses the user input and executes the corresponding action.
     *
     * @param input The user input to be parsed and executed.
     */
    public void parse(String input) {

        String[] userMessage = input.split(" ");

        if (input.equalsIgnoreCase(DELETE_ALL_COMMAND)) {
            taskManager.deleteAllTasks();
            return;
        }

        switch (userMessage[0].toLowerCase()) {
            case "find":
                handleFindCommand(userMessage, taskManager);
                break;
            case "list":
                taskManager.displayTask(input);
                break;
            case "mark":
                handleMarkCommand(userMessage, taskManager, input);
                break;
            case "unmark":
                handleUnmarkCommand(userMessage, taskManager, input);
                break;
            case "todo":
                taskManager.addTask(input, TaskType.Todo);
                taskManager.displayTask(input);
                break;
            case "deadline":
                taskManager.addTask(input, TaskType.Deadline);
                taskManager.displayTask(input);
                break;
            case "event":
                taskManager.addTask(input, TaskType.Event);
                taskManager.displayTask(input);
                break;
            case "delete":
                handleDeleteCommand(userMessage, taskManager, input);
                break;
            default:
                conversation.printDialogue(input);
                break;
        }
    }

    private void handleMarkCommand(String[] userMessage, TaskManager taskManager, String input) {
        if (userMessage.length == 1 || !isNumeric(userMessage[1])) {
            printError(input);
            return;
        }
        taskManager.markAsComplete(Integer.parseInt(userMessage[1]) - 1);
        taskManager.displayTask(input);
    }

    private void handleUnmarkCommand(String[] userMessage, TaskManager taskManager, String input) {
        if (userMessage.length == 1 || !isNumeric(userMessage[1])) {
            printError(input);
            return;
        }
        taskManager.markAsIncomplete(Integer.parseInt(userMessage[1]) - 1);
        taskManager.displayTask(input);
    }

    private void handleDeleteCommand(String[] userMessage, TaskManager taskManager, String input) {
        if (userMessage.length == 1 || !isNumeric(userMessage[1])) {
            printError(input);
            return;
        }
        taskManager.displayTask(input);
        taskManager.deleteTask(Integer.parseInt(userMessage[1]) - 1);
    }

    private void handleFindCommand(String[] userMessage, TaskManager taskManager) {
        if (userMessage.length == 1) {
            System.out.println(Ui.LINE);
            System.out.println(Ui.INDENTATION + "Can you specify a keyword after the find command" +
                    "so that I can help you better?");
            System.out.println(Ui.LINE);
            return;
        }

        String keyword = userMessage[1];
        List<Task> matchingTasks = taskManager.findTask(keyword);

        if (matchingTasks.isEmpty()) {
            System.out.println(Ui.LINE);
            System.out.println(Ui.INDENTATION + "I don't think there is anything in your list " +
                    "that matches: " + keyword);
            System.out.println(Ui.LINE);
        } else {
            taskDisplay.printFindTaskList(matchingTasks);
        }
    }

    public void saveAllTasks() {
        taskManager.autoSaveTask();
    }

    /**
     * Checks if a given string is numeric.
     *
     * @param str The string to check.
     * @return True if the string is numeric, false otherwise.
     */
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Prints an error message indicating that the TASK NUMBER is missing after the specified command.
     *
     * @param input The input command causing the error.
     */
    private void printError(String input) {
        System.out.println(Ui.LINE);
        System.out.println(Ui.INDENTATION + "Sorry " + username + ", the TASK NUMBER " +
                "is missing after " + input.toLowerCase() + ".");
        System.out.println(Ui.INDENTATION + "Can you please specify a valid task number" +
                " from the list?");
        System.out.println(Ui.LINE);
    }
}

