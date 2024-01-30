package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to add new Todo.
 */
public class TodoCommand implements Command {

    private String input;

    public TodoCommand(String input) {
        this.input = input;
    }

    /**
     * Creates a new Todo based on description extracted from input and adds to TaskList.
     *
     * @param list Holds the tasks added.
     * @param ui Display messages about executed operation.
     * @param storage Handles IO storage operation.
     * @throws DukeException Not thrown.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task t = new Todo(input.substring(5));
        list.add(t);
        ui.showAdded(t, list);
        storage.writeToFile(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object a) {
        TodoCommand tc = (TodoCommand) a;
        return this.input.equals(tc.input);
    }
}
