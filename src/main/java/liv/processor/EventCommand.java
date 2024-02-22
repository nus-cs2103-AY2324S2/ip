package liv.processor;

import liv.container.Storage;
import liv.task.Event;
import liv.container.TaskList;
import liv.ui.Ui;

/**
 * Represents a command to add an event to the task list.
 */
public class EventCommand extends Command {
    private Event event;

    /**
     * The constructor of the class.
     * @param event The Event object to be added.
     */
    public EventCommand(Event event) {
        this.event = event;
    }

    /**
     * Add the event to the task list and signals the Ui to respond.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The Ui to gives interaction with users.
     * @param storage The storage where the data is stored.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(event);
        String message = Ui.getEventMessage(event);
        storage.saveTaskToFile();
        return message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasChangedData() {
        return true;
    }
}
