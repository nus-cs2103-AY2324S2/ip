package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("OOPS!!! There are no tasks to delete.");
        }
        Task removed = taskList.remove(index);
        System.out.println("Noted. I've removed this task");
        System.out.println("  " + removed);
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
    }
}
