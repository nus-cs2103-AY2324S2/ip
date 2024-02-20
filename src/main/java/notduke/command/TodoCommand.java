package notduke.command;

import notduke.notdukeexception.NotDukeException;
import notduke.notdukeexception.NotDukeMissingArgument;
import notduke.storage.Storage;
import notduke.task.ToDos;
import notduke.tasklist.TaskList;

public class TodoCommand extends Command {
    private String inputs;

    public TodoCommand(String command) {
        this.inputs = command;
    }
    public String execute(TaskList tasks, Storage storage) throws NotDukeException {
        if (inputs.isEmpty()) {
            throw new NotDukeMissingArgument("todo");
        }

        ToDos newTask = new ToDos(inputs, false);
        return tasks.add(newTask);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
