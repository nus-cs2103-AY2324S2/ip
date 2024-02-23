package duke.command.sort;

import duke.DukeException;
import duke.command.Command;
import duke.command.CommandResult;
import duke.command.InvalidCommand;

public class SortCommand extends Command {
    public static final String COMMAND = "sort";
    private String type;

    public SortCommand(String type) {
        this.type = type;
    }

    private static final String COMMAND_SUCCESS =
            "-------------------------------- \n" +
                    "Your list is now sorted by %s. \n" +
                    "Here are the tasks in your list: \n" +
                    "%s \n" +
                    "-------------------------------- \n"
            ;
    private static final String COMMAND_INVALID_TYPE =
            "-------------------------------- \n" +
                    "Oops, can only sort by name. \n" +
                    "Please enter 'sort name' to sort list by task name.\n"
            ;

    public CommandResult execute() {
        try {
            storage.sortTasksByType(type);
        } catch (DukeException e) {
            return new CommandResult(COMMAND_INVALID_TYPE);
        }
        return new CommandResult(String.format(COMMAND_SUCCESS, type, storage.getTasksInString()));
    }

}
