package duke.command.task;

import duke.command.CommandResult;
import duke.task.Deadline;

public class DeadlineCommand extends TaskCommand {
    public static final String COMMAND = "deadline";
    private final Deadline t;

    public DeadlineCommand(Deadline t) {
        this.t = t;
    }
    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(COMMAND_SUCCESS, t.toString(), 100));
    }
}
