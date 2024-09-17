package brian.command;

import java.time.LocalDate;

import brian.storage.Storage;
import brian.task.Deadline;
import brian.task.Task;
import brian.task.TaskList;
import brian.ui.TextUi;

public class DeadlineCommand extends Command {

    private final String task;

    private final LocalDate by;

    public DeadlineCommand(String task, LocalDate by) {

        this.task = task;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        assert tasks != null;
        assert ui != null;

        Task curr = new Deadline(task, by);
        tasks.add(curr);
        ui.showTask(curr, tasks.size());
    }
}
