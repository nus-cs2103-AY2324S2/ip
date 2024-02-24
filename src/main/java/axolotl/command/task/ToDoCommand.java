package axolotl.command.task;

import axolotl.command.CommandResult;
import axolotl.task.ToDo;

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
