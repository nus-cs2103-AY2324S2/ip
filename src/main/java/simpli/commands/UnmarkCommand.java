package simpli.commands;

import simpli.commands.base.Command;
import simpli.commands.base.CommandResult;
import simpli.commands.base.CommandWord;
import simpli.exceptions.CommandException;
import simpli.tasks.TaskList;
import simpli.ui.Ui;

public class UnmarkCommand implements Command {
    private final Ui ui;
    private final TaskList taskList;

    public UnmarkCommand(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }
    @Override
    public CommandResult execute(String[] tokens) throws CommandException {
        int taskNum = Integer.parseInt(tokens[2]);
        taskList.mark(taskNum);
        return new CommandResult(
                CommandWord.MARK,
                ui.getUnmarkedTaskMessage(taskList.getTask(taskNum))
        );
    }
}
