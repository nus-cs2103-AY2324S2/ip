package eggy.command;

import eggy.exception.EggyException;
import eggy.exception.IncompleteTaskException;
import eggy.storage.Storage;
import eggy.task.TaskList;
import eggy.task.Event;
import eggy.ui.Ui;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;

    public EventCommand(String[] commands) throws EggyException {
        if (commands.length < 2) {
            throw new IncompleteTaskException("event");
        }
        String[] eventSplit = commands[1].split(" /from | /to ");
        if (eventSplit.length < 3) {
            throw new IncompleteTaskException("event");
        }
        this.name = eventSplit[0];
        this.start = Command.parseDateTime(eventSplit[1]);
        this.end = Command.parseDateTime(eventSplit[2]);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EggyException {
        Event newEvent = new Event(this.name, this.start, this.end);
        tasks.addTask(newEvent);
        ui.printTaskAdded(newEvent, tasks.getSize());
        storage.save(tasks);
    }
}
