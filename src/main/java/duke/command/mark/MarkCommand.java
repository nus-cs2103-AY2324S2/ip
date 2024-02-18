package duke.command.mark;

import duke.command.Command;
import duke.command.CommandResult;
import duke.task.Task;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private Task t;
    private int taskNo;

    private static final String MESSAGE_SUCCESS = "-------------------------------- \n" +
            "Nice! I've marked task %d as done: \n" +
            "%s \n" +
            "-------------------------------- \n";

    public MarkCommand(Task t, int taskNo) {
        this.t = t;
        this.taskNo = taskNo;
    }

    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_SUCCESS, taskNo + 1, t.toString()));
    }

}
