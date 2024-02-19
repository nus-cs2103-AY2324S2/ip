package duke.command.taskNo;

import duke.command.CommandEnum;
import duke.command.CommandResult;

public class MarkCommand extends TaskNoCommand {
    public static final String COMMAND = "mark";

    private static final String COMMAND_SUCCESS = "-------------------------------- \n" +
            "Nice! I've marked task %d as done: \n" +
            "%s \n" +
            "-------------------------------- \n";

    public static final String COMMAND_INVALID_INTEGER = "-------------------------------- \n" +
            "Oops, I'm not sure which task you are referring to! " +
            "Please indicate a task number: \n" +
            CommandEnum.MARK.COMMAND_SIGNATURE + "\n" +
            "(e.g. " + COMMAND + " 1) \n" +
            "-------------------------------- \n";

    public MarkCommand(int taskNo) {
        super(taskNo);
    }


    public CommandResult execute() throws NullPointerException {
        storage.markTask(taskNo - 1);
        return new CommandResult(String.format(COMMAND_SUCCESS, taskNo, t.toString()));
    }
}
