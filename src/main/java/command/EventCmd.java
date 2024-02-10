package command;
import task.Event;
import java.time.LocalDateTime;

public class EventCmd extends Command {
    public Event task;
    @Override
    public void execute() {
        tasks.add(task);
        ui.addedResponse(task.toString());
    }

    public EventCmd(String description, LocalDateTime from, LocalDateTime to) {
        task = new Event(description, from, to);
    }
}
