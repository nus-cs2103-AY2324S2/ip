package command;

import duke.Storage;
import duke.TaskList;
import duke.DukeException;
import duke.Task;

public class DeleteCommand extends Command {
    private final int num;

    public DeleteCommand(int num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof DeleteCommand) {
            return ((DeleteCommand) other).num == num;
        }
        return false;
    }

    @Override
    public void execute(Storage storage, TaskList taskList) throws DukeException {
        Task task = taskList.remove(num);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        taskList.countSize();
        storage.deleteLine(num);
    }
}
