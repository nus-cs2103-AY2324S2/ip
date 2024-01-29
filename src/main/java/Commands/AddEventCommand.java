package Commands;

import Exceptions.InvalidInputException;
import TaskList.Tasks.Task;
import TaskList.Tasks.Event;
import Storage.Storage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class AddEventCommand extends Command{
    public static final String COMMAND_WORD = "event";
    private final Task event;
    public AddEventCommand(String eventName, LocalDateTime start, LocalDateTime end) throws InvalidInputException {
        this.event = new Event(eventName, start, end);
    }
    @Override
    public String execute() throws IOException {
        this.taskList.addTask(this.event);
        return "I have added this task:\n" + this.event + "\n" +
                "Now you have " + this.taskList.size() + " tasks in your list.";

    }
}
