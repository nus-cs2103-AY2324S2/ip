package commands;

import exception.InvalidDeadlineException;
import objects.Deadlines;
import objects.Task;
import objects.TaskList;
import view.EncaseLines;

import java.time.LocalDateTime;

import static utils.InputUtil.convertToDateTime;

public class CreateDeadline implements Command {
    private final TaskList tasks;
    private final String[] input;

    public CreateDeadline(TaskList tasks, String[] input) {
        this.tasks = tasks;
        this.input = input;
    }

    @Override
    public void execute() throws InvalidDeadlineException {
        String name = input[0];
        LocalDateTime by = convertToDateTime(input[1]);

        Task t = new Deadlines(name, by);
        tasks.add(t);

        String o = String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", t, tasks.size());
        EncaseLines.display(o);
    }
}
