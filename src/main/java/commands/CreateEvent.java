package commands;

import exception.InvalidEventException;
import objects.Deadlines;
import objects.Events;
import objects.Task;
import objects.TaskList;
import view.EncaseLines;

import java.time.LocalDateTime;

import static utils.InputUtil.convertToDateTime;

public class CreateEvent implements Command {
    private final TaskList tasks;
    private final String[] input;

    public CreateEvent(TaskList tasks, String[] input) {
        this.tasks = tasks;
        this.input = input;
    }

    @Override
    public void execute() throws InvalidEventException {
        String name = input[0];
        LocalDateTime from = convertToDateTime(input[1]);
        LocalDateTime to = convertToDateTime(input[2]);

        Task t = new Events(name, from, to);
        tasks.add(t);

        String o = String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", t, tasks.size());
        EncaseLines.display(o);
    }
}
