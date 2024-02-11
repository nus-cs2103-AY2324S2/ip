package duke.command;

import duke.conversation.Conversation;
import duke.task.Task;
import duke.task.TaskDisplay;
import duke.task.TaskManager;
import duke.task.TaskType;

import java.util.List;

/**
 * The Parser class is responsible for parsing user commands and interacting with the TaskManager.
 * It interprets the input commands and executes corresponding actions.
 */
public class CommandParser {
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
    public CommandParser(String username) {
        taskManager = new TaskManager(username);
        conversation = new Conversation(username);
        this.username = username;
    }

    /**
     * Parses the user input and executes the corresponding action.
     *
     * @param input The user input to be parsed and executed.
     */
    public String parseInput(String input) {

        String[] userMessage = input.split(" ");

        if (input.equalsIgnoreCase(DELETE_ALL_COMMAND)) {
            taskManager.deleteAllTasks();
            return "okay, noted. I've removed all tasks from the list.";
        }

        switch (userMessage[0].toLowerCase()) {
            case "find":
                return handleFindCommand(userMessage, taskManager);
            case "list":
                return taskManager.displayTask(input);
            case "mark":
                return handleMarkCommand(userMessage, taskManager, input);
            case "unmark":
                return handleUnmarkCommand(userMessage, taskManager, input);
            case "todo":
                taskManager.addTask(input, TaskType.Todo);
                return taskManager.displayTask(input);
            case "deadline":
                taskManager.addTask(input, TaskType.Deadline);
                return taskManager.displayTask(input);
            case "event":
                taskManager.addTask(input, TaskType.Event);
                return taskManager.displayTask(input);
            case "delete":
                return handleDeleteCommand(userMessage, taskManager, input);
            default:
                return conversation.printDialogue(input);
        }
    }

    private String handleMarkCommand(String[] userMessage, TaskManager taskManager, String input) {
        if (userMessage.length == 1 || !isNumeric(userMessage[1])) {
            return printError(input);
        }
        taskManager.markAsComplete(Integer.parseInt(userMessage[1]) - 1);
       return taskManager.displayTask(input);
    }

    private String handleUnmarkCommand(String[] userMessage, TaskManager taskManager, String input) {
        if (userMessage.length == 1 || !isNumeric(userMessage[1])) {
            return printError(input);
        }
        taskManager.markAsIncomplete(Integer.parseInt(userMessage[1]) - 1);
        return taskManager.displayTask(input);
    }

    private String handleDeleteCommand(String[] userMessage, TaskManager taskManager, String input) {
        String deletedTask = "";
        if (userMessage.length == 1 || !isNumeric(userMessage[1])) {
            return printError(input);
        } else {
            deletedTask = taskManager.displayTask(input);
            taskManager.deleteTask(Integer.parseInt(userMessage[1]) - 1);
        }
        return deletedTask;
    }

    private String handleFindCommand(String[] userMessage, TaskManager taskManager) {
        if (userMessage.length == 1) {
            return "Can you specify a keyword after the find command" +
                    "so that I can help you better?";
        }

        String keyword = userMessage[1];
        List<Task> matchingTasks = taskManager.findTask(keyword);

        String findTask = "";
        if (!matchingTasks.isEmpty()) {
           findTask = taskDisplay.printFindTaskList(matchingTasks);
        }
        return findTask;
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
    private String printError(String input) {
        return "Sorry " + username.toUpperCase() + ", the TASK NUMBER" +
                "is missing after " + input.toLowerCase() + "." +
                "\nCan you please specify a valid task number\n from the list?";
    }
}

