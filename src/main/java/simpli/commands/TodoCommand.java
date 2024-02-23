package simpli.commands;

import simpli.commands.base.Command;
import simpli.commands.base.CommandResult;
import simpli.commands.base.CommandWord;
import simpli.exceptions.CommandException;
import simpli.tasks.Task;
import simpli.tasks.TaskList;
import simpli.ui.Ui;

public class TodoCommand implements Command {
    /** Handles UI messages that is to be displayed to user. */
    private final Ui ui;

    /** Contains all the tasks added by the user. */
    private final TaskList taskList;

    /**
     * Constructs the TodoCommand with the specified fields.
     *
     * @param ui which handles display messages.
     * @param taskList which contains added tasks.
     */
    public TodoCommand(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    @Override
    public CommandResult execute(String[] tokens) throws CommandException {
        Task addedTask = taskList.addTodo(tokens);
        taskList.sort();
        return new CommandResult(
                CommandWord.TODO,
                ui.getAddedTaskMessage(addedTask)
        );
    }
}
