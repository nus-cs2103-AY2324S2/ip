package yippee;
import java.util.ArrayList;
import java.util.List;

import yippee.commands.Command;
import yippee.commands.CreateTaskCommand;
import yippee.commands.DeleteCommand;
import yippee.commands.ExitCommand;
import yippee.commands.FindCommand;
import yippee.commands.ListCommand;
import yippee.commands.MarkCommand;
import yippee.exceptions.InvalidCommandException;

/**
 * Represents element to parse and unpack user command inputs.
 */
public class Parser {
    /** All valid task command names */
    private static final List<String> NUMBER_COMMANDS = new ArrayList<>(List.of("mark", "unmark", "delete"));
    private static final List<String> VALID_TASKS = new ArrayList<>(List.of("todo", "deadline", "event"));
    private Ui ui;

    /**
     * Instantiates parser instance.
     * Creates and assigns new ui instance to local variable.
     */
    public Parser() {
        this.ui = new Ui();
    }

    /**
     * Parses given command and instantiates corresponding command type.
     * @param command String representation of command from user.
     * @return Command object instantiated based on user input.
     * @throws InvalidCommandException If user input format is invalid.
     */
    public Command parseCommand(String command) throws InvalidCommandException {
        String[] split = command.split("\\s+", 2);
        String commandName = split[0].toLowerCase().trim();
        if (commandName.equals("bye")) {
            return new ExitCommand();
        } else if (commandName.equals("list")) {
            return new ListCommand();
        } else if (NUMBER_COMMANDS.contains(commandName)) {
            return parseEditNumber(split, commandName);
        } else if (commandName.equals("find")) {
            return parseFind(split);
        } else if (VALID_TASKS.contains(commandName)) {
            return parseCreate(split);
        } else {
            throw new InvalidCommandException("I don't recognize that command :( sorry!");
        }
    }

    private Command parseEditNumber(String[] split, String commandName) throws InvalidCommandException{
        if (split.length == 1) {
            throw new InvalidCommandException(
                    "Wrong format! Please include the number that you want me to unmark >:(");
        }
        int number = Integer.parseInt(split[1]);
        if (commandName.equals("mark")) {
            return new MarkCommand(false, number);
        } else if (commandName.equals("unmark")) {
            return new MarkCommand(true, number);
        } else if (commandName.equals("delete")) {
            return new DeleteCommand(number);
        } else {
            assert false;
            return null;
        }
    }

    private Command parseFind(String[] split) throws InvalidCommandException {
        String keyword = split[1].toLowerCase().trim();
        return new FindCommand(keyword);
    }

    private Command parseCreate(String[] split) throws InvalidCommandException {
        String taskType = split[0].toLowerCase().trim();
        String taskName = split[1].toLowerCase().trim();
        return new CreateTaskCommand(taskType, taskName);
    }
}

