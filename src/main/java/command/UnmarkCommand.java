package command;

import duke.Storage;
import duke.TaskList;
import duke.DukeException;
import duke.Task;

public class UnmarkCommand extends Command {
    private final int num;

    public UnmarkCommand(int num) {
        this.num = num;
    }

    @Override
    public void execute(Storage storage, TaskList taskList) throws DukeException {
        Task task = taskList.retrieve(num);
        if (!task.isDone()) {
            System.out.println("Not done in the first place.");
        } else {
            task.unmark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task + '\n');
            storage.editLine(num, task);
        }
    }
}
