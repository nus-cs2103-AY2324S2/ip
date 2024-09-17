package brian.command;

import java.time.LocalDate;

import brian.storage.Storage;
import brian.task.Event;
import brian.task.Task;
import brian.task.TaskList;
import brian.ui.TextUi;

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
        assert tasks != null;
        assert ui != null;

        Task curr = new Event(task, from, to);
        tasks.add(curr);
        ui.showTask(curr, tasks.size());
    }
}
