package ben.commands;

import ben.storage.Storage;
import ben.tasks.Deadline;
import ben.tasks.Task;
import ben.tasks.TaskList;
import ben.ui.Ui;

import java.time.LocalDate;

/**
 * Represents a command to add a new deadline task.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDate deadline;

    /**
     * Creates a DeadlineCommand with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline of the task.
     */
    public DeadlineCommand(String description, LocalDate deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Executes the DeadlineCommand by adding a new Deadline task to the task list.
     *
     * @param tasks   The task list to which the new task will be added.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newDeadline = new Deadline(false, description, deadline);
        tasks.addTask(newDeadline);

        return ui.showAddedTaskMessage() +
                ui.show(newDeadline.toString()) +
                ui.showCurrNoOfTasks(tasks);
    }
}