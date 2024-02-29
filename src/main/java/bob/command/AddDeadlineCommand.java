package bob.command;

import java.time.LocalDateTime;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.SavingException;
import bob.task.Task;

/**
 * Represents an action to add a deadline. An <code>AddDeadlineCommand</code> object corresponds to
 * a command to add a deadline with a specified description and due time.
 */
public class AddDeadlineCommand extends AddCommand {
    private final LocalDateTime by;

    /**
     * Returns a command to add a deadline with a specified description and due time.
     *
     * @param description The description for the deadline to be added.
     * @param by The due time for the deadline to be added.
     */
    public AddDeadlineCommand(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Executes the command to add a deadline with a specified description and due time.
     *
     * @param storage The storage to save the new deadline in hard disk.
     * @param taskList The task list to store the new deadline.
     * @return The response as a result of adding the deadline.
     * @throws SavingException If there was an error saving the new deadline in hard disk.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws SavingException {
        Task task = taskList.addDeadline(description, by);
        storage.saveTask(task);
        return Ui.getAddResponse(task, taskList.getSize());
    }
}
