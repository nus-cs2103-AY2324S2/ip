package duke.command.delete;

import duke.command.Command;
import duke.command.CommandResult;
import duke.task.Task;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private Task t;

    private static final String MESSAGE_SUCCESS = "-------------------------------- \n" +
            "Okay, I will delete this task: \n" +
            "%s \n" +
            "You now have %d in the list. \n" +
            "-------------------------------- \n";

    public DeleteCommand(Task t) {
        this.t = t;
    }

    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_SUCCESS, t.toString(), -1));
    }

}
