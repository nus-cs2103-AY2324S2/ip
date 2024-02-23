package simpli.commands.base;

import java.util.HashMap;

import simpli.commands.*;
import simpli.exceptions.CommandException;
import simpli.exceptions.TaskException;
import simpli.interpreter.Interpreter;
import simpli.tasks.TaskList;
import simpli.ui.Ui;


public class CommandManager {
    private final Ui ui;
    private final TaskList taskList;

    private final Interpreter intrpr;
    private final HashMap<CommandWord, Command> commandRegistry;

    public CommandManager(Interpreter intrpr, TaskList taskList) {
        this.ui = new Ui();
        this.taskList = taskList;
        this.intrpr = intrpr;
        this.commandRegistry = new HashMap<>();

        initializeCommandRegistry();
    }

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

    public CommandResult execute(String[] tokens) throws CommandException, TaskException {
        CommandWord commandType = CommandWord.valueOf(tokens[0].toUpperCase());
        return commandRegistry.get(commandType).execute(tokens);
    }
}
