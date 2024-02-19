package lemona.command;

import lemona.oop.TaskList;
import lemona.task.Task;
import lemona.task.Deadline;

public class DeadlineCommand extends Command {
    private String[] input;

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
