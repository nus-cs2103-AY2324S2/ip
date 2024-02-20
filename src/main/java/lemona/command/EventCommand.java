package lemona.command;

import lemona.oop.TaskList;
import lemona.task.Event;
import lemona.task.Task;

/**
 * An EventCommand to handle event command.
 */
public class EventCommand extends Command{

    private String[] input;

    /**
     * Constructs EventCommand object to handle event command.
     */
    public EventCommand(String[] input) {
        this.input = input;
    }
    @Override
    public String execute(TaskList tasks) {
        Task task = new Event(input[1], input[2], input[3]);
        String str = tasks.add(task);
        return str;
    }
}
