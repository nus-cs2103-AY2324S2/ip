package bob.command;

import java.time.LocalDateTime;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.InvalidPeriodException;
import bob.exception.SavingException;
import bob.task.Task;

/**
 * Represents an action to add a task. An <code>AddPeriodCommand</code> object corresponds to
 * a command to add a task with a specified description, start time and end time.
 */
public class AddPeriodCommand extends AddCommand {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Returns a command to add a task with a specified description, start time and end time.
     *
     * @param description The description for the task to be added.
     * @param start The start time for the task to be added.
     * @param end The end time for the task to be added.
     */
    public AddPeriodCommand(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the command to add a task with a specified description, start time and end time.
     *
     * @param storage The storage to save the new task in hard disk.
     * @param taskList The task list to store the new task.
     * @return The response as a result of adding the task.
     * @throws SavingException If there was an error saving the new task in hard disk.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws SavingException, InvalidPeriodException {
        Task task = taskList.addPeriod(description, start, end);
        storage.saveTask(task);
        return Ui.getAddResponse(task, taskList.getSize());
    }
}
