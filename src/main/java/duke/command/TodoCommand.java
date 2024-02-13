package duke.command;

import duke.dukeexception.DukeException;
import duke.dukeexception.DukeMissingArgument;
import duke.storage.Storage;
import duke.task.ToDos;
import duke.tasklist.TaskList;

public class TodoCommand extends Command {
    private String inputs;

    public TodoCommand(String command) {
        this.inputs = command;
    }
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (inputs.isEmpty()) {
            throw new DukeMissingArgument(1, "todo");
        }

        ToDos newTask = new ToDos(inputs, false);
        return tasks.add(newTask);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
