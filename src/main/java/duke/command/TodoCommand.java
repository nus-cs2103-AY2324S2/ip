package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class TodoCommand implements Command {

    private String input;

    public TodoCommand(String input) {
        this.input = input;
    }

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
