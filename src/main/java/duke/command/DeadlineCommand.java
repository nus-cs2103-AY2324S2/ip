package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;
import duke.utils.DukeException;

import java.time.LocalDate;

public class DeadlineCommand extends Command {

    private final String task;

    private final LocalDate by;

    public DeadlineCommand(String task, LocalDate by) {
        this.task = task;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        Task curr = new Deadline(task, by);
        tasks.add(curr);
        ui.showTask(curr, tasks.size());
    }
}
