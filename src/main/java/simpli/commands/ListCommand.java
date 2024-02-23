package simpli.commands;

import simpli.commands.base.Command;
import simpli.commands.base.CommandResult;
import simpli.commands.base.CommandWord;
import simpli.tasks.TaskList;
import simpli.ui.Ui;

public class ListCommand implements Command {
    private final Ui ui;
    private final TaskList taskList;

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
