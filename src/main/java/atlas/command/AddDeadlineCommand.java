package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;
import atlas.task.Deadline;

import java.time.LocalDateTime;


/**
 * This command adds a new deadline to the task list.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime by;
    private int priority;

    /**
     * Constructs an {@code AddDeadlineCommand} with the specified description and deadline time.
     *
     * @param tasks       The {@code TaskList} to which the deadline will be added.
     * @param ui          The {@code Ui} instance for user interaction.
     * @param storage     The {@code Storage} instance for saving the updated task list.
     * @param description The description of the deadline.
     * @param by          The time by which the task is due.
     */
    public AddDeadlineCommand(TaskList tasks, Ui ui, Storage storage, String description, LocalDateTime by, int priority) {
        super(tasks, ui, storage);
        this.description = description;
        this.by = by;
        this.priority = priority;
    }

    /**
     * Executes the addition of a new deadline task.
     */
    @Override
    public String execute() {
        Deadline deadline = new Deadline(description, by, priority);
        tasks.addTask(deadline);
        return ui.showTaskAdded(tasks);

    }
}
