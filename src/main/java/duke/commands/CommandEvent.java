package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the command for adding an event task to the task list in the Duke application.
 */
public class CommandEvent extends Command {
    private final Event event;

    /**
     * Constructs a new CommandEvent object with the specified event task.
     *
     * @param event The event task to be added to the task list.
     */
    public CommandEvent(Event event) {
        this.event = event;
    }

    /**
     * Executes the "event" command, which adds the specified event task to the task list,
     * saves the updated task list to storage, and displays a confirmation message to the user.
     *
     * @param tasks   The task list to which the event task will be added.
     * @param ui      The user interface component for displaying messages to the user.
     * @param storage The storage component for saving the updated task list.
     * @throws DukeException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.event);

        storage.saveTasks(tasks);

        ui.showMessage(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
            this.event, tasks.size()));
    }
}
