package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a command that searches certain keywords in all the commands
 */
public class FindKeywordCommand extends Command {
    private final String keyword;

    /**
     * Constructs the FindKeywordCommand.
     *
     * @param keyword The keyword typed in by the user
     */
    public FindKeywordCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Judges if 2 commands are searching the same keyword.
     *
     * @param other Another command
     * @return True if another command is also searching the same keyword
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof FindKeywordCommand) {
            return keyword.equals(((FindKeywordCommand) other).keyword);
        }
        return false;
    }

    /**
     * Search all the content of the tasks and list all that contain the keywords.
     *
     * @param storage  Involved in file management
     * @param taskList Active during the program execution
     * @return A string that contains all matching tasks
     * @throws DukeException if the execution is unsuccessful
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException {
        return taskList.searchKeyword(keyword);
    }
}
