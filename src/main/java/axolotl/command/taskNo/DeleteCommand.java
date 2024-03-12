package axolotl.command.taskNo;

import axolotl.command.CommandEnum;
import axolotl.command.CommandResult;

public class DeleteCommand extends TaskNoCommand {
    public static final String COMMAND = "delete";

    private static final String COMMAND_SUCCESS = "-------------------------------- \n" +
            "Okay, I will delete this task: \n" +
            "%s \n" +
            "You now have %d tasks in the list. \n" +
            "-------------------------------- \n";

    public static final String COMMAND_INVALID_INTEGER = "-------------------------------- \n" +
            "Oops, I'm not sure which task you are referring to! " +
            "Please indicate a task number: \n" +
            CommandEnum.DELETE.COMMAND_SIGNATURE + "\n" +
            "(e.g. " + COMMAND + " 1) \n" +
            "-------------------------------- \n";

    public DeleteCommand(int taskNo) {
        super(taskNo);
    }


    public CommandResult execute() throws NullPointerException {
        storage.deleteTask(taskNo - 1);
        return new CommandResult(String.format(COMMAND_SUCCESS, t.toString(), storage.getNumOfTasks()));
    }
}
