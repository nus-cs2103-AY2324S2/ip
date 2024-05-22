package dude.commands;

import java.util.ArrayList;

import dude.exceptions.InvalidFormatException;
import dude.tasks.Task;
import dude.tasks.TaskList;
import dude.utils.Utils;

/**
 * The FindCommand class represents a command to find tasks in the TaskList object.
 */
public class FindCommand extends Command {
    static final String COMMAND_FORMAT = "find <keyword>";

    private final String input;
    private final TaskList taskList;

    /**
     * Constructor for the FindCommand class. Returns a command object to find tasks with the given input
     * in the TaskList object upon execution.
     *
     * @param input    The input string that resulted in the creation of this command.
     * @param taskList The TaskList object from which the tasks are to be found.
     */
    public FindCommand(String input, TaskList taskList) {
        super(COMMAND_FORMAT, "find .*");

        assert(input != null);
        assert(taskList != null);
        assert(input.contains("find"));

        this.input = input;
        this.taskList = taskList;
    }

    /**
     * Finds tasks in the TaskList object.
     *
     * @return The string message of the tasks found from the execution of the command.
     */
    public String execute() throws InvalidFormatException {
        String keyword = Utils.discardFirstWord(input.trim()).trim();

        if (keyword.isEmpty()) {
            throw new InvalidFormatException("find", COMMAND_FORMAT);
        }
        ArrayList<Task> tasks = this.taskList.find(keyword);

        if (tasks.isEmpty()) {
            return "No matching tasks found! :(";
        }

        String msg = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            msg += "\t" + (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return msg.trim();
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.FIND;
    }
}
