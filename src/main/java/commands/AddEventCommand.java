package commands;

import services.Storage;
import services.TaskList;
import services.UI;
import tasks.Event;

import java.io.IOException;
import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;

    public AddEventCommand(String name, LocalDateTime start, LocalDateTime end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        Event event = new Event(this.name, this.start, this.end);
        taskList.addTask(event);
        storage.saveTasks(taskList);
    }
}
