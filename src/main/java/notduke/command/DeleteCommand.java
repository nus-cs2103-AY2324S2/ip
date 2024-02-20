package notduke.command;

import notduke.notdukeexception.NotDukeException;
import notduke.notdukeexception.NotDukeInvalidArgument;
import notduke.notdukeexception.NotDukeMissingArgument;
import notduke.storage.Storage;
import notduke.tasklist.TaskList;

public class DeleteCommand extends Command {
    private String inputs;

    public DeleteCommand(String command) {
        this.inputs = command;
    }
    public String execute(TaskList tasks, Storage storage) throws NotDukeException {
        if (inputs.isEmpty()) {
            throw new NotDukeMissingArgument( "delete");
        }

        try {
            int index = Integer.valueOf(inputs);
            return tasks.delete(index);
        } catch (NumberFormatException e) {
            throw new NotDukeInvalidArgument(inputs);
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
