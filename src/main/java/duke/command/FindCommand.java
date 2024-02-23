package duke.command;

import duke.DukeException;
import duke.MyList;
import duke.Ui;

/**
 * Represents a command to find tasks in the task list based on a specified keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand by finding tasks containing the specified keyword in the task list
     * and adding response to the UI.
     *
     * @param myList The list to search for tasks.
     * @param ui     The user interface for displaying results.
     */
    public void execute(MyList myList, Ui ui) {
        try {
            String foundListMessage = myList.findByKeyword(keyword);
            ui.addResponse(foundListMessage);
        } catch (DukeException e) {
            ui.addResponse(e.getMessage());
        }
    }
}
