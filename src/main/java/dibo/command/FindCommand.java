package dibo.command;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.exception.DiboException;

/**
 * Class to handle a command which finds tasks with a specified keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for the FindCommand class.
     *
     * @param keyword The keyword we are using to find the tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Run the find task command.
     *
     * @param taskList The TaskList object which contains all the tasks.
     * @param ui The Ui object which is responsible for printing the marked message.
     * @param storage The Storage object which is responsible to save the changes into a text file.
     * @throws DiboException when an error occurs when we cannot find the task.
     */
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        ui.showFound(taskList.getTasksWithKeyword(this.keyword));
    }

}
