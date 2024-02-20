package lemona.command;

import lemona.oop.TaskList;
import lemona.task.Task;
import lemona.task.Todo;

/**
 * A TodoCommand to handle todo command.
 */
public class TodoCommand extends Command {

    private String[] input;

    /**
     * Constructs TodoCommand object to handle todo command.
     */
    public TodoCommand(String[] input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) {
        Task task = new Todo(input[1]);
        String str = tasks.add(task);
        return str;
    }
}
