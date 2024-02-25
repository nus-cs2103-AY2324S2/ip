package bob.command;

import java.time.LocalDateTime;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.InvalidEventException;
import bob.exception.SavingException;
import bob.task.Task;

/**
 * Represents an action to add an event. An <code>AddEventCommand</code> object corresponds to
 * a command to add an event with a specified description, start time and end time.
 */
public class AddEventCommand extends AddCommand {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Returns a command to add an event with a specified description, start time and due time.
     *
     * @param description The description for the event to be added.
     * @param from The start time for the event to be added.
     * @param to The end time for the event to be added.
     */
    public AddEventCommand(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command to add an event with a specified description, start time and end time.
     *
     * @param ui The UI to display the result of adding the event.
     * @param storage The storage to save the new event in hard disk.
     * @param taskList The task list to store the new event.
     * @throws SavingException If there was an error saving the new event in hard disk.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws SavingException, InvalidEventException {
        Task task = taskList.addEvent(description, from, to);
        ui.showAdd(task, taskList.getSize());
        storage.saveTask(task);
    }
}
