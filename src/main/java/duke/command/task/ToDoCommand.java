package duke.command.task;

import duke.command.CommandResult;
import duke.task.TaskList;
import duke.task.ToDo;

public class ToDoCommand extends TaskCommand {

    public static final String COMMAND = "todo";
    private final ToDo t;

//    private final String MESSAGE_SUCCESS =  "-------------------------------- \n" +
//            "Sure, I've added this task: \n" +
//            "%s \n" +
//            "Now you have %d task(s) in the list. \n" +
//            "-------------------------------- \n";

    public ToDoCommand(String task) {
        this.t = new ToDo(task, false);
    }

    @Override
    public CommandResult execute() {
        storage.addTask(t);
        return new CommandResult(String.format(COMMAND_SUCCESS, t.toString(), storage.getNumOfTasks()));
    }
}
