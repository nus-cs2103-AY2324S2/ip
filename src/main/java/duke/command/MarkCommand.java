package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class MarkCommand extends Command{

    private final int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(this.idx);
        task.mark();
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }
}
