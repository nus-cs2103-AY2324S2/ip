package duke.command.task;

import duke.command.CommandResult;
import duke.task.ToDo;

public class ToDoCommand extends TaskCommand {

    public static final String COMMAND = "todo";
    private final ToDo t;

//    private final String MESSAGE_SUCCESS =  "-------------------------------- \n" +
//            "Sure, I've added this task: \n" +
//            "%s \n" +
//            "Now you have %d task(s) in the list. \n" +
//            "-------------------------------- \n";

    public ToDoCommand(ToDo t) {
        this.t = t;
    }
    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_SUCCESS, t.toString(), 100));
    }
}
