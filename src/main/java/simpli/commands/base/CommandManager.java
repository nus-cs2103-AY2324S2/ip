package simpli.commands.base;

import java.util.HashMap;

import simpli.commands.*;
import simpli.exceptions.CommandException;
import simpli.exceptions.TaskException;
import simpli.interpreter.Interpreter;
import simpli.tasks.TaskList;
import simpli.ui.Ui;


/**
 * Manages and executes the different types of commands.
 */
public class CommandManager {
    /** Handles UI messages that is to be displayed to user. */
    private final Ui ui;

    /** Stores all the tasks throughout the entire session. */
    private final TaskList taskList;

    /** Interprets the meaning of the tokens parsed from the parser. */
    private final Interpreter intrpr;

    /** Stores all the commands for the program. */
    private final HashMap<CommandWord, Command> commandRegistry;

    /**
     * Constructs a CommandManager with the specified fields.
     */
    public CommandManager(Interpreter intrpr, TaskList taskList) {
        this.ui = new Ui();
        this.taskList = taskList;
        this.intrpr = intrpr;
        this.commandRegistry = new HashMap<>();

        initializeCommandRegistry();
    }

    /**
     * Prepare the CommandManager to be used.
     */
    public void initializeCommandRegistry() {
        commandRegistry.put(CommandWord.LIST, new ListCommand(ui, taskList));
        commandRegistry.put(CommandWord.MARK, new MarkCommand(ui, taskList));
        commandRegistry.put(CommandWord.UNMARK, new UnmarkCommand(ui, taskList));
        commandRegistry.put(CommandWord.DELETE, new DeleteCommand(ui, taskList));
        commandRegistry.put(CommandWord.FIND, new FindCommand(ui, taskList));
        commandRegistry.put(CommandWord.TODO, new TodoCommand(ui, taskList));
        commandRegistry.put(CommandWord.DEADLINE, new DeadlineCommand(ui, taskList, intrpr));
        commandRegistry.put(CommandWord.EVENT, new EventCommand(ui, taskList, intrpr));
        commandRegistry.put(CommandWord.GREET, new GreetCommand(ui));
        commandRegistry.put(CommandWord.BYE, new ByeCommand(ui));
    }

    /**
     * Executes the different commands and return the outcome.
     *
     * @param tokens which is parsed from the command String.
     * @return outcome of the execution.
     * @throws CommandException If an error occurs when command is executing.
     * @throws TaskException If an error occurs when an invalid task is being added.
     */
    public CommandResult execute(String[] tokens) throws CommandException, TaskException {
        CommandWord commandType = CommandWord.valueOf(tokens[0].toUpperCase());
        return commandRegistry.get(commandType).execute(tokens);
    }
}
