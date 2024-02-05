package commands;

import util.Ui;
import util.TaskList;
import util.Storage;
import java.time.LocalDateTime;
import tasks.Event;

public class EventCommand extends Command {
    private String event;
    private LocalDateTime start;
    private LocalDateTime end;

    public EventCommand(String event, LocalDateTime start, LocalDateTime end) {
        this.event = event;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Event event = new Event(this.event, this.start, this.end);
        taskList.addTask(event);
        storage.saveToFile(taskList);
    }
}
