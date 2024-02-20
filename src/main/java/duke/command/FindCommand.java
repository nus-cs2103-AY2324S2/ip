package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.TaskListException;

/**
 * Class to run Find Command.
 *
 * @author Koh Guan Zeh
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a Command that finds for tasks matching the keywords.
     *
     * @param input Keyword to match.
     * @throws CommandException Exception when parameter is empty.
     */
    public FindCommand(String input) throws CommandException {
        if (input.isEmpty()) {
            throw new CommandException("Error. Parameter for find Command cannot be empty.");
        }
        this.keyword = input;
    }

    @Override
    public String run(TaskList taskList, Storage storage) throws IOException, CommandException, TaskListException {
        return taskList.findTasks(this.keyword);
    }
}
