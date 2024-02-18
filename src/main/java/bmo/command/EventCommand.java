package bmo.command;

import bmo.util.Storage;
import bmo.util.TaskList;
import bmo.ui.Ui;
import bmo.task.Task;
import bmo.task.Events;

import java.time.LocalDateTime;

/**
 * Represents an event command to be executed.
 */
public class EventCommand extends Command {

    private final String desc;
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructor for an event command.
     *
     * @param desc  The description of the task.
     * @param start The start date and time of the task.
     * @param end   The end date and time of the task.
     */
    public EventCommand(String desc, LocalDateTime start, LocalDateTime end) {
        this.desc = desc;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the event command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Events(this.desc, this.start, this.end);
        tasks.add(newTask);
        ui.printAddTask(newTask, tasks.size());
    }
}
