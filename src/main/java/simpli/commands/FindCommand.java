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
    /** Handles UI messages that is to be displayed to user. */
    private final Ui ui;

    /** Contains all the tasks added by the user. */
    private final TaskList taskList;

    /**
     * Constructs the FindCommand with the specified fields.
     *
     * @param ui which handles display messages.
     * @param taskList which contains added tasks.
     */
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
