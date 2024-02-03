package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.exceptions.InvalidArgumentException;
import duke.tasks.EventTask;
import duke.tasks.Task;

import java.time.DateTimeException;

/**
 * Represents a command to add an event task.
 */
public class EventCommand extends Command {
    /**
     * The command word to add an event task.
     */
    public static final String COMMAND_WORD = "event";
    private static final String MESSAGE = "added this task for you liao:\n%s";

    private final String name;
    private final String from;
    private final String to;

    /**
     * Constructs a command to add an event task with the given name, start time and end time.
     *
     * @param name The name of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
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
