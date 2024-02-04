package duke.commands;

import java.time.DateTimeException;

import duke.TaskList;
import duke.Ui;
import duke.exceptions.InvalidArgumentException;
import duke.tasks.EventTask;
import duke.tasks.Task;


public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE = "added this task for you liao:\n%s";

    private final String name;
    private final String from;
    private final String to;

    public EventCommand(String name, String from, String to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            Task task = new EventTask(name, from, to);
            tasks.add(task);
            ui.print(String.format(MESSAGE, task));
        } catch (DateTimeException | InvalidArgumentException e) {
            ui.print(e.getMessage());
        }
    }
}
