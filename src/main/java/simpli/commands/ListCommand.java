package simpli.commands;

import simpli.commands.base.Command;
import simpli.commands.base.CommandResult;
import simpli.commands.base.CommandWord;
import simpli.tasks.TaskList;
import simpli.ui.Ui;

public class ListCommand implements Command {
    /** Handles UI messages that is to be displayed to user. */
    private final Ui ui;

    /** Contains all the tasks added by the user. */
    private final TaskList taskList;

    /**
     * Constructs the ListCommand with the specified fields.
     *
     * @param ui which handles display messages.
     * @param taskList which contains added tasks.
     */
    public ListCommand(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    @Override
    public CommandResult execute(String[] tokens) {
        return new CommandResult(
                CommandWord.LIST,
                ui.getListTasksMessage(taskList)
        );
    }
}
