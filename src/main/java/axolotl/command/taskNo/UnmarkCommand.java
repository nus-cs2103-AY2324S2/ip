package axolotl.command.taskNo;

import axolotl.command.Command;
import axolotl.command.CommandEnum;
import axolotl.command.CommandResult;

public class UnmarkCommand extends TaskNoCommand {
    public static final String COMMAND = "unmark";

    private static final String COMMAND_SUCCESS = "-------------------------------- \n" +
            "Sure, I've unmarked task %d: \n" +
            "%s \n" +
            "-------------------------------- \n";

    public static final String COMMAND_INVALID_INTEGER = "-------------------------------- \n" +
            "Oops, I'm not sure which task you are referring to! " +
            "Please indicate a task number: \n" +
            CommandEnum.UNMARK.COMMAND_SIGNATURE + "\n" +
            "(e.g. " + COMMAND + " 1) \n" +
            "-------------------------------- \n";

    public UnmarkCommand(int taskNo) {
        super(taskNo);
    }


    public CommandResult execute() throws NullPointerException {
        Command.storage.unmarkTask(taskNo - 1);
        return new CommandResult(String.format(COMMAND_SUCCESS, taskNo, t.toString()));
    }
}
