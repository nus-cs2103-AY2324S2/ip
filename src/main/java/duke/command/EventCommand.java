package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.time.LocalDateTime;

public class EventCommand extends Command{

    private final String event;
    private final LocalDateTime fromDate;
    private final LocalDateTime toDate;

    public EventCommand(String event, LocalDateTime fromDate, LocalDateTime toDate) {
        this.event = event;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new duke.task.Event(this.event, this.fromDate, this.toDate);
        tasks.add(task);
        return tasks.standardize(task);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof EventCommand otherCommand)) {
            return false;
        }
        return this.event.equals(otherCommand.event)
                && this.fromDate.equals(otherCommand.fromDate)
                && this.toDate.equals(otherCommand.toDate);
    }
}
