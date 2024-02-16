package duke.command;

import duke.conversation.Conversation;
import duke.task.TaskDisplay;
import duke.task.TaskManager;

/**
 * The CommandParser class is responsible for parsing user commands and interacting with the TaskManager.
 * It interprets the input commands and delegates the execution to appropriate command handlers.
 */
public class CommandParser {
    private final TaskManager taskManager;
    private final Conversation conversation;
    private final TaskDisplay taskDisplay;

    public CommandParser(String username) {
        this.taskManager = new TaskManager(username);
        this.conversation = new Conversation(username);
        this.taskDisplay = new TaskDisplay();
    }

    public String parseInput(String input) {
        String[] userMessage = input.split(" ");

        if (input.equalsIgnoreCase("delete all")) {
            return handleDeleteAllCommand();
        }

        CommandHandler handler = getCommandHandler(userMessage[0].toLowerCase());
        return handler != null ? handler.handle(userMessage) : conversation.printDialogue(input);
    }

    private String handleDeleteAllCommand() {
        taskManager.deleteAllTasks();
        return "Okay, noted. I've removed all tasks from the list.\n";
    }

    private CommandHandler getCommandHandler(String command) {
        switch (command) {
            case "find":
                return new FindCommandHandler(taskManager, taskDisplay);
            case "list":
                return new ListCommandHandler(taskManager);
            case "mark":
                return new MarkCommandHandler(taskManager);
            case "unmark":
                return new UnmarkCommandHandler(taskManager);
            case "todo":
            case "deadline":
            case "event":
                return new AddCommandHandler(taskManager);
            case "delete":
                return new DeleteCommandHandler(taskManager);
            default:
                return null;
        }
    }
}
