package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;
import atlas.task.Deadline;

import java.time.LocalDateTime;


public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;

    public AddDeadlineCommand(TaskList tasks, Ui ui, Storage storage, String description, LocalDateTime by) {
        super(tasks, ui, storage);
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute() {
        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        ui.showTaskAdded(tasks);

    }
}
