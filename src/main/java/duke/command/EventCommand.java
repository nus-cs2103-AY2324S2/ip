package duke.command;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;
import duke.utils.DukeException;

import java.time.LocalDate;

public class EventCommand extends Command {
    private final String task;

    private final LocalDate from;
    private final LocalDate to;

    public EventCommand(String task, LocalDate from, LocalDate to) {
        this.task = task;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        Task curr = new Event(task, from, to);
        tasks.add(curr);
        ui.showTask(curr, tasks.size());
    }
}
