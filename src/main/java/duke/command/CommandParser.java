package duke.command;

import duke.command.handler.*;
import duke.conversation.Conversation;
import duke.task.TaskDisplay;
import duke.task.TaskManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The CommandParser class is responsible for parsing user commands and interacting with the TaskManager.
 * It interprets the input commands and delegates the execution to appropriate command handlers.
 */
public class CommandParser {
    private final TaskManager taskManager;
    private final Conversation conversation;
    private final TaskDisplay taskDisplay;
    private final Map<String, CommandHandler> commandMap;
    private final Set<String> defaultCommands;

    /**
     * Constructs a CommandParser.
     *
     * @param username the username
     */
    public CommandParser(String username) {
        this.taskManager = new TaskManager(username);
        this.conversation = new Conversation(username);
        this.taskDisplay = new TaskDisplay();
        this.commandMap = new HashMap<>();
        this.defaultCommands = Set.of("f", "find", "lst", "list", "m", "mark", "um", "unmark", "t",
                "todo", "e", "event", "dl", "deadline", "del", "delete");

        defineDefaultCommands();
    }

    /**
     * Parses the input command.
     *
     * @param input the input command
     * @return the response to the input command
     */
    public String parseInput(String input) {
        input = input.trim().toLowerCase();

        if ("help".equals(input) || "commands".equals(input)) {
            return listAllCommands();
        } else if (input.startsWith("define ")) {
            return handleDefineCommand(input.substring(7));
        } else if ("delete all".equals(input)) {
            return handleDeleteAllCommand();
        } else {
            return processUserCommand(input);
        }
    }

    private String processUserCommand(String input) {
        String[] userMessage = input.split(" ");
        CommandHandler handler = commandMap.get(userMessage[0]);
        if (handler != null) {
            return handler.handle(userMessage);
        } else {
            return conversation.generateResponse(input);
        }
    }

    private void defineDefaultCommands() {
        commandMap.put("f", new FindCommandHandler(taskManager, taskDisplay));
        commandMap.put("find", new FindCommandHandler(taskManager, taskDisplay));
        commandMap.put("lst", new ListCommandHandler(taskManager, taskDisplay));
        commandMap.put("list", new ListCommandHandler(taskManager, taskDisplay));
        commandMap.put("m", new MarkCommandHandler(taskManager, taskDisplay));
        commandMap.put("mark", new MarkCommandHandler(taskManager, taskDisplay));
        commandMap.put("um", new UnmarkCommandHandler(taskManager, taskDisplay));
        commandMap.put("unmark", new UnmarkCommandHandler(taskManager, taskDisplay));
        commandMap.put("t", new AddCommandHandler(taskManager, taskDisplay));
        commandMap.put("todo", new AddCommandHandler(taskManager, taskDisplay));
        commandMap.put("e", new AddCommandHandler(taskManager, taskDisplay));
        commandMap.put("event", new AddCommandHandler(taskManager, taskDisplay));
        commandMap.put("dl", new AddCommandHandler(taskManager, taskDisplay));
        commandMap.put("deadline", new AddCommandHandler(taskManager, taskDisplay));
        commandMap.put("del", new DeleteCommandHandler(taskManager, taskDisplay));
        commandMap.put("delete", new DeleteCommandHandler(taskManager, taskDisplay));
    }

    private boolean mapNewCommand(String shortcut, String commandName) {
        if (isDefaultCommand(shortcut) || commandMap.containsKey(shortcut)) {
            return false;
        }

        CommandHandler handler = createHandlerForCommand(commandName);
        if (handler != null) {
            define(shortcut, handler);
            return true;
        }
        return false;
    }

    private CommandHandler createHandlerForCommand(String commandName) {
        switch (commandName.toLowerCase()) {
            case "todo":
            case "event":
            case "deadline":
                return new AddCommandHandler(taskManager, taskDisplay);
            case "find":
                return new FindCommandHandler(taskManager, taskDisplay);
            case "list":
                return new ListCommandHandler(taskManager, taskDisplay);
            case "mark":
                return new MarkCommandHandler(taskManager, taskDisplay);
            case "unmark":
                return new UnmarkCommandHandler(taskManager, taskDisplay);
            case "delete":
                return new DeleteCommandHandler(taskManager, taskDisplay);
            default:
                return null;
        }
    }

    private boolean isDefaultCommand(String commandName) {
        return defaultCommands.contains(commandName.toLowerCase());
    }

    private String handleDefineCommand(String defineInput) {
        String[] parts = defineInput.split("=");
        if (parts.length != 2) {
            return "Oops! 🙈 The format seems off. Try 'define shortcut=command'.";
        }
        String shortcut = parts[0].trim();
        String command = parts[1].trim();

        if (isDefaultCommand(shortcut)) {
            return "Nice try! 😜 But '" + command + "' is already a default command. Pick something unique!";
        }

        if (mapNewCommand(shortcut, command)) {
            return "Woohoo! 🎉 Shortcut '" + shortcut + "' is all set up for '" + command + "'.";
        } else {
            return "Uh-oh! 😕 Looks like that shortcut/command combo is already taken or it's a default command.";
        }
    }

    /**
     * Lists all available commands.
     *
     * @return a string containing all available commands
     */
    public String listAllCommands() {
        StringBuilder commandsList = new StringBuilder("Here are all the magical spells... I mean commands you can use: 🪄\n");
        for (Map.Entry<String, CommandHandler> entry : commandMap.entrySet()) {
            String command = entry.getKey();
            String description = entry.getValue().getDescription();
            commandsList.append(command).append(": ").append(description).append("\n");
        }
        return commandsList.toString();
    }

    /**
     * Defines a new command with a shortcut.
     *
     * @param shortcut the shortcut for the new command
     * @param handler the handler for the new command
     */
    public void define(String shortcut, CommandHandler handler) {
        if (commandMap.containsKey(shortcut)) {
            System.out.println("Switcheroo! 🔄 Replacing the spell... erm, command for shortcut: " + shortcut);
        }
        commandMap.put(shortcut, handler);
    }

    private String handleDeleteAllCommand() {
        taskManager.deleteAllTasks();
        return "All clear! 🧹✨ I've removed every single task from the list.\n";
    }

}
