package duke.Command;

import duke.*;
import duke.Tasks.*;
import duke.Tasks.TaskList;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String deadline;


    /**
     * Constructs a DeadlineCommand object with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline of the task.
     */
    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }


    /**
     * Executes the DeadlineCommand, adding a deadline task to the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new DeadlineTask(description, deadline);
        tasks.addTask(task);
        int count = tasks.size();;
        ui.showAddedMessage(task, count);
        storage.save(tasks.getAllTasks());
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return Always returns false, as this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

