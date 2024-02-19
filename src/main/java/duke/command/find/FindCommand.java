package duke.command.find;

import duke.command.Command;
import duke.command.CommandResult;
import duke.task.TaskList;

public class FindCommand extends Command {
    public static final String COMMAND = "find";
    private static String keyword;

    private static final String COMMAND_SUCCESS = "-------------------------------- \n" +
            "Here are the matching tasks in your list: \n" +
            "%s \n" +
            "-------------------------------- \n";

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public CommandResult execute() {
        TaskList taskList = storage.filterListByKeyword(keyword);
        return new CommandResult(String.format(COMMAND_SUCCESS, taskList.toString()));
    }
}
