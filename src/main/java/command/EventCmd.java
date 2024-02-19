package command;
import task.Event;
import java.time.LocalDateTime;

public class EventCmd extends Command {
    private Event task;
    @Override
    public String execute() {
        tasks.add(task);
        response = ui.addedResponse(task.toString());
        return response;
    }

    public Event getTask() {
        return task;
    }

    public EventCmd(String description, LocalDateTime from, LocalDateTime to) {
        task = new Event(description, from, to);
    }
}
