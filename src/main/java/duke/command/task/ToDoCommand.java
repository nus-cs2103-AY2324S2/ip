package duke.command.task;

import duke.command.CommandResult;
import duke.task.TaskList;
import duke.task.ToDo;

public class ToDoCommand extends TaskCommand {

    public static final String COMMAND = "todo";
    private final ToDo t;

    public ToDoCommand(String task) {
        this.t = new ToDo(task, false);
    }

    @Override
    public CommandResult execute() {
        storage.addTask(t);
        return new CommandResult(String.format(COMMAND_SUCCESS, t.toString(), storage.getNumOfTasks()));
    }
}
