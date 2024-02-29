package gmo.command;

import gmo.util.Storage;
import gmo.util.TaskList;
import gmo.ui.Ui;
import gmo.task.Task;
import gmo.task.Deadlines;

import java.time.LocalDateTime;

/**
 * Represents a due command to be executed.
 */
public class DueCommand extends Command {

    private final String desc;
    private final LocalDateTime by;

    /**
     * Constructor for a due command.
     *
     * @param desc The description of the task.
     * @param by   The date and time of the task.
     */
    public DueCommand(String desc, LocalDateTime by) {
        this.desc = desc;
        this.by = by;
    }

    /**
     * Executes the due command.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Deadlines(this.desc, this.by);
        tasks.add(newTask);
        return ui.printAddTask(newTask, tasks.size());
    }
}
