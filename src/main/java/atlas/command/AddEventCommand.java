package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;
import atlas.task.Event;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    public AddEventCommand(TaskList tasks, Ui ui, Storage storage, String description, LocalDateTime start, LocalDateTime end) {
        super(tasks, ui, storage);
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() {
        Event event = new Event(description, start, end);
        tasks.addTask(event);
        ui.showTaskAdded(tasks);
    }
}
