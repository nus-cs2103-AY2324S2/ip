package duke.command.mark;

import duke.command.Command;
import duke.command.CommandResult;
import duke.task.Task;

public class UnmarkCommand extends ChangeisDoneCommand {
    public static final String COMMAND = "mark";
    private Task t;
    private int taskNo;

    private static final String COMMAND_SUCCESS = "-------------------------------- \n" +
            "Nice! I've marked task %d as not done yet: \n" +
            "%s \n" +
            "-------------------------------- \n";

    public UnmarkCommand(Task t, int taskNo) {
        this.t = t;
        this.taskNo = taskNo;
    }

    public CommandResult execute() {
        return new CommandResult(String.format(COMMAND_SUCCESS, taskNo + 1, t.toString()));
    }

}
