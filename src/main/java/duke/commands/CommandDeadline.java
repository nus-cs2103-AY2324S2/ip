package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the command for adding a deadline task to the task list in the Duke application.
 */
public class CommandDeadline extends Command {
    private final Deadline deadline;

    /**
     * Constructs a new CommandDeadline object with the specified deadline task.
     *
     * @param deadline The deadline task to be added to the task list.
     */
    public CommandDeadline(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Executes the "deadline" command, which adds the specified deadline task to the task list,
     * saves the updated task list to storage, and displays a confirmation message to the user.
     *
     * @param tasks   The task list to which the deadline task will be added.
     * @param ui      The user interface component for displaying messages to the user.
     * @param storage The storage component for saving the updated task list.
     * @throws DukeException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.deadline);

        storage.saveTasks(tasks);

        ui.showMessage(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
            this.deadline, tasks.size()));
    }
}
