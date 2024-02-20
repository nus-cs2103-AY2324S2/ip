package lemona.command;

import lemona.oop.TaskList;
import lemona.task.Task;
import lemona.task.Deadline;

/**
 * A DeadlineCommand to handle deadline command.
 */
public class DeadlineCommand extends Command {
    private String[] input;

    /**
     * Constructs DeadlineCommand object to handle deadline command.
     */
    public DeadlineCommand(String[] input) {
        this.input = input;
    }
    @Override
    public String execute(TaskList tasks) {
        Task task = new Deadline(input[1], input[2]);
        String str = tasks.add(task);
        return str;
    }
}
