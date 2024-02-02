package duke.command;

import duke.dukeexception.DukeException;
import duke.dukeexception.DukeMissingArgument;
import duke.storage.Storage;
import duke.task.ToDos;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class TodoCommand extends Command {
    private String inputs;

    public TodoCommand(String command) {
        inputs = command;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputs.isEmpty()) {
            throw new DukeMissingArgument(1, "todo");
        }
        ToDos newTask = new ToDos(inputs, false);
        ui.sendReply(tasks.add(newTask));
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
