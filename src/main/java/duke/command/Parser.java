package duke.command;

import duke.conversation.Conversation;
import duke.task.TaskManager;
import duke.task.TaskType;

/**
 * The Parser class is responsible for parsing user commands and interacting with the TaskManager.
 * It interprets the input commands and executes corresponding actions.
 */
public class Parser {
    TaskManager taskManager;
    Conversation conversation;
    TaskType taskType;
    String userName;

    /**
     * Constructs a Parser with the specified username. Initializes the TaskManager and Conversation.
     *
     * @param username The username for which the Parser is created.
     */
    public Parser(String username) {
        taskManager = new TaskManager(username);
        conversation = new Conversation(username);
        this.userName = username;
        taskManager.autoSaveTask();
    }

    /**
     * Parses the user input and executes the corresponding action.
     *
     * @param input The user input to be parsed and executed.
     */
    public void parse(String input) {

        String[] userMessage = input.split(" ");

        if (input.equalsIgnoreCase("delete all")) {
            taskManager.deleteAllTasks();
            return;
        }

        switch (userMessage[0].toLowerCase()) {
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
        if (userMessage.length == 1 || isNumeric(userMessage[1])) {
            printError(input);
            return;
        }
        taskManager.markAsComplete(Integer.parseInt(userMessage[1]) - 1);
        taskManager.displayTask(input);
    }

    private void handleUnmarkCommand(String[] userMessage, TaskManager taskManager, String input) {
        if (userMessage.length == 1 || isNumeric(userMessage[1])) {
            printError(input);
            return;
        }
        taskManager.markAsIncomplete(Integer.parseInt(userMessage[1]) - 1);
        taskManager.displayTask(input);
    }

    private void handleDeleteCommand(String[] userMessage, TaskManager taskManager, String input) {
        if (userMessage.length == 1 || isNumeric(userMessage[1])) {
            printError(input);
            return;
        }
        taskManager.displayTask(input);
        taskManager.deleteTask(Integer.parseInt(userMessage[1]) - 1);
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
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    /**
     * Prints an error message indicating that the TASK NUMBER is missing after the specified command.
     *
     * @param input The input command causing the error.
     */
    private void printError(String input) {
        System.out.println(TaskManager.LINE);
        System.out.println(TaskManager.INDENTATION + "Sorry " + userName + ", the TASK NUMBER is missing after " + input.toLowerCase() + ".");
        System.out.println(TaskManager.INDENTATION + "Can you please specify a valid duke.task number from the list?");
        System.out.println(TaskManager.LINE);
    }
}

