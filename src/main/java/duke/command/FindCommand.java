package duke.command;

import duke.Storage;
import duke.task.TaskList;

/**
 * Represents a command to find a phrase/word.
 */
public class FindCommand extends Command {
    /** Word to find */
    private String searchWord;

    public FindCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    /**
     * Executes command, retrieving tasks from list and updating response.
     *
     * @param list TaskList object to search from.
     * @param storage Not used.
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        String out = list.find(searchWord);

        super.setResponse(out);
    }
}
