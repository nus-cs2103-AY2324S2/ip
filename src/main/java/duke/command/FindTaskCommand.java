package duke.command;

import duke.task.Task;
import duke.utility.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import java.util.ArrayList;

public class FindTaskCommand extends Command {
    /** String containing keyword to be used to search in the TaskList. */
    private String keyword;

    /**
     * Constructs a FindTaskCommand Object.
     *
     * @param keyword String containing keyword to be used to search in the TaskList.
     */
    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Runs the FindTaskCommand to Find Tasks in the TaskList that contain the keyword.
     *
     * @param taskList Existing TaskList to be updated.
     * @param ui Existing UserInterface Object.
     * @param storage Existing Storage to be updated
     * @throws DukeException
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> filteredList = taskList.findTask(this.keyword);
        ui.showFilteredTask(filteredList, this.keyword);
    }

    /**
     * Returns a boolean value of whether this command is an exit command.
     *
     * @return Boolean value of whether this command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
