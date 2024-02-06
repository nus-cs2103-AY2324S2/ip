package podz.commands;

import podz.ui.Ui;

/**
 * Represents a command to find tasks based on a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand object with the specified keyword.
     *
     * @param keyword the keyword to search for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command to search for tasks with the keyword.
     *
     * @param ui The user interface to interact with the user.
     */
    @Override
    public void execute(Ui ui) {
        ui.printToUser(
                super.taskList
                        .findTasks(this.keyword));
    }
}
