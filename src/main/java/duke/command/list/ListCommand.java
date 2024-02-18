package duke.command.list;

import duke.command.Command;
import duke.command.CommandResult;
import duke.task.TaskList;

public class ListCommand extends Command {
    public static final String COMMAND = "list";

    private static final String COMMAND_SUCCESS =
            "-------------------------------- \n" +
                    "Here are the tasks in your list:" +
                    TaskList.getTaskListInString() +
                    "-------------------------------- \n"
            ;

    public CommandResult execute() {
        return new CommandResult(COMMAND_SUCCESS);
    }

}
