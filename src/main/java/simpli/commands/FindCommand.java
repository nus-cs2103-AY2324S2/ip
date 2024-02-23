package simpli.commands;

import simpli.commands.base.Command;
import simpli.commands.base.CommandResult;
import simpli.commands.base.CommandWord;
import simpli.exceptions.CommandException;
import simpli.tasks.Task;
import simpli.tasks.TaskList;
import simpli.ui.Ui;

import java.util.ArrayList;

public class FindCommand implements Command {
    private final Ui ui;
    private final TaskList taskList;

    public FindCommand(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }
    @Override
    public CommandResult execute(String[] tokens) throws CommandException {
        ArrayList<Task> tasks = taskList.findTasks(tokens[2]);
        TaskList foundTasks = new TaskList(tasks);
        return new CommandResult(
                CommandWord.FIND,
                ui.getFoundTasks(foundTasks)
        );
    }
}
