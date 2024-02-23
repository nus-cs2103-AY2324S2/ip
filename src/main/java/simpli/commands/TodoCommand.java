package simpli.commands;

import simpli.commands.base.Command;
import simpli.commands.base.CommandResult;
import simpli.commands.base.CommandWord;
import simpli.exceptions.CommandException;
import simpli.tasks.Task;
import simpli.tasks.TaskList;
import simpli.ui.Ui;

public class TodoCommand implements Command {
    private final Ui ui;
    private final TaskList taskList;

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
