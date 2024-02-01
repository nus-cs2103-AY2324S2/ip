package duke.command;

import duke.task.Task;
import duke.utility.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import java.util.ArrayList;

public class FindTaskCommand extends Command{
    private String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> filteredList = taskList.findTask(this.keyword);
        ui.showFilteredTask(filteredList, this.keyword);
    }

    public boolean isExit() {
        return false;
    }
}
