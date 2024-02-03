package duke.command;

import duke.conversation.Conversation;
import duke.task.TaskManager;
import duke.task.TaskType;

public class Parser {
    private TaskManager taskManager;
    private Conversation conversation;
    private final String username;

    private static final String DELETE_ALL_COMMAND = "delete all";

    public Parser(String username) {
        taskManager = new TaskManager(username);
        conversation = new Conversation(username);
        this.username = username;
        taskManager.autoSaveTask();
    }

    public void parse(String input) {

        String[] userMessage = input.split(" ");

        if (input.equalsIgnoreCase(DELETE_ALL_COMMAND)) {
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

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void printError(String input) {
        System.out.println(TaskManager.LINE);
        System.out.println(TaskManager.INDENTATION + "Sorry " + username + ", the TASK NUMBER " +
                "is missing after " + input.toLowerCase() + ".");
        System.out.println(TaskManager.INDENTATION + "Can you please specify a valid task number" +
                " from the list?");
        System.out.println(TaskManager.LINE);
    }
}

