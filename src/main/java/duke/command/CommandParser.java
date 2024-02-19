package duke.command;

import duke.command.handler.*;
import duke.conversation.Conversation;
import duke.task.TaskDisplay;
import duke.task.TaskManager;

import java.util.HashMap;
import java.util.Map;

/**
 * The CommandParser class is responsible for parsing user commands and interacting with the TaskManager.
 * It interprets the input commands and delegates the execution to appropriate command handlers.
 */
public class CommandParser {
    private final TaskManager taskManager;
    private final Conversation conversation;
    private final TaskDisplay taskDisplay;
    private final Map<String, CommandHandler> commandMap;

    public CommandParser(String username) {
        this.taskManager = new TaskManager(username);
        this.conversation = new Conversation(username);
        this.taskDisplay = new TaskDisplay();
        this.commandMap = new HashMap<>();

        defineDefaultCommands();
    }

    public String parseInput(String input) {
        if (input.equalsIgnoreCase("help") || input.equalsIgnoreCase("commands")) {
            return listAllCommands();
        } else if (input.toLowerCase().startsWith("define ")) {
            return handleDefineCommand(input.substring(7)); // Process dynamic command definition
        } else if (input.equalsIgnoreCase("delete all")) {
            return handleDeleteAllCommand();
        } else {
            String[] userMessage = input.split(" ");
            CommandHandler handler = commandMap.get(userMessage[0].toLowerCase());
            if (handler != null) {
                return handler.handle(userMessage);
            } else {
                return conversation.printDialogue(input); // Handle unrecognized commands
            }
        }
    }

    private void defineDefaultCommands() {
        commandMap.put("f", new FindCommandHandler(taskManager, taskDisplay));
        commandMap.put("find", new FindCommandHandler(taskManager, taskDisplay));

        commandMap.put("lst", new ListCommandHandler(taskManager));
        commandMap.put("list", new ListCommandHandler(taskManager));

        commandMap.put("m", new MarkCommandHandler(taskManager));
        commandMap.put("mark", new MarkCommandHandler(taskManager));

        commandMap.put("um", new UnmarkCommandHandler(taskManager));
        commandMap.put("unmark", new UnmarkCommandHandler(taskManager));

        commandMap.put("t", new AddCommandHandler(taskManager));
        commandMap.put("todo", new AddCommandHandler(taskManager));

        commandMap.put("e", new AddCommandHandler(taskManager));
        commandMap.put("event", new AddCommandHandler(taskManager));

        commandMap.put("d", new AddCommandHandler(taskManager));
        commandMap.put("deadline", new AddCommandHandler(taskManager));

        commandMap.put("del", new DeleteCommandHandler(taskManager));
        commandMap.put("delete", new DeleteCommandHandler(taskManager));
    }

    private String handleDefineCommand(String defineInput) {
        String[] parts = defineInput.split("=");
        if (parts.length != 2) {
            return "Invalid define command format. Please use 'define shortcut=command'.";
        }
        String shortcut = parts[0].trim();
        String command = parts[1].trim();

        if (mapNewCommand(shortcut, command)) {
            return "Shortcut '" + shortcut + "' defined successfully for command '" + command + "'.";
        } else {
            return "Failed to define shortcut. Please check the command name.";
        }
    }

    private boolean mapNewCommand(String shortcut, String commandName) {
        CommandHandler handler = null;
        switch (commandName.toLowerCase()) {
            case "todo":
            case "event":
            case "deadline":
                handler = new AddCommandHandler(taskManager);
                break;
            case "find":
                handler = new FindCommandHandler(taskManager, taskDisplay);
                break;
            case "delete":
                handler = new DeleteCommandHandler(taskManager);
                break;
            case "list":
                handler = new ListCommandHandler(taskManager);
                break;
            case "mark":
                handler = new MarkCommandHandler(taskManager);
                break;
            case "unmark":
                handler = new UnmarkCommandHandler(taskManager);
                break;
        }

        if (handler != null) {
            define(shortcut, handler);
            return true;
        }
        return false;
    }

    public String listAllCommands() {
        StringBuilder commandsList = new StringBuilder("Available Commands:\n");
        for (Map.Entry<String, CommandHandler> entry : commandMap.entrySet()) {
            String command = entry.getKey();
            String description = entry.getValue().getDescription();
            commandsList.append(command).append(": ").append(description).append("\n");
        }
        return commandsList.toString();
    }


    public void define(String shortcut, CommandHandler handler) {
        if (commandMap.containsKey(shortcut)) {
            // Optionally notify about the replacement of an existing command
            System.out.println("Replacing existing command for shortcut: " + shortcut);
        }
        commandMap.put(shortcut, handler);
    }

    private String handleDeleteAllCommand() {
        taskManager.deleteAllTasks();
        return "Okay, noted. I've removed all tasks from the list.\n";
    }
}
