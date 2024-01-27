package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command{

    private final String event;
    private final LocalDateTime dueBy;

    public DeadlineCommand(String event, LocalDateTime dueBy) {
        this.dueBy = dueBy;
        this.event = event;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new duke.task.Deadline(this.event, this.dueBy);
        tasks.add(task);
        return tasks.standardize(task);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DeadlineCommand otherCommand)) {
            return false;
        }
        return this.event.equals(otherCommand.event)
                && this.dueBy.equals(otherCommand.dueBy);
    }
}
