package lemona.command;

import lemona.oop.TaskList;
import lemona.task.Event;
import lemona.task.Task;

public class EventCommand extends Command{

    private String[] input;

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
