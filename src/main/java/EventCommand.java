import java.time.LocalDateTime;

public class EventCommand extends Command {
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;

    public EventCommand(String[] commands) {
        String[] eventSplit = commands[1].split(" /from | /to ");
        this.name = eventSplit[0];
        this.start = Command.parseDateTime(eventSplit[1]);
        this.end = Command.parseDateTime(eventSplit[2]);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event newEvent = new Event(this.name, this.start, this.end);
        tasks.addTask(newEvent);
        ui.printTaskAdded(newEvent, tasks.getSize());
        storage.save(tasks);
    }
}
