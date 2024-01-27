package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class UnMarkCommand extends Command{

    private final int idx;

    public UnMarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(idx);
        task.unmark();
        return "OK, I've marked this task as not done yet:\n" + task + "\n";
    }
}
