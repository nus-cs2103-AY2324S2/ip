package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command{

    private final int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(idx);
        tasks.remove(idx);
        return "Noted. I've removed this task:\n" + task + "\n" + tasks.trailer() + "\n";
    }
}
