package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.ArrayList;

/**
 * Represents a command to find keyword.
 */
public class FindCommand implements Command {

    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Searches through the list of tasks for matching keyword given in input.
     *
     * @param list Holds all tasks added.
     * @param ui Displays message of operation.
     * @param storage Handles IO operations.
     * @throws DukeException No thrown.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String[] s = input.split("\\s");
        String keyword = s[1];
        ArrayList<Task> arr = new ArrayList<>();
        for (Task t : list.getList()) {
            if (t.getTask().contains(keyword)) {
                arr.add(t);
            }
        }
        ui.showMatchedTasks(new TaskList(arr));
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object a) {
        FindCommand fc = (FindCommand) a;
        return this.input.equals(fc.input);
    }
}
