package gmo.command;

import java.time.LocalDateTime;

import gmo.task.Events;
import gmo.task.Task;
import gmo.ui.Ui;
import gmo.util.Storage;
import gmo.util.TaskList;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Events(this.desc, this.start, this.end);
        tasks.add(newTask);
        return ui.printAddTask(newTask, tasks.size());
    }
}
