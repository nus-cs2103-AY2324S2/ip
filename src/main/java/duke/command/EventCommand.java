package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class that inherits from Command class.
 * Represents a command to add an event task to the TaskList.
 */
public class EventCommand extends Command {

    private Event event;

    /**
     * Constructs an EventCommand with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public EventCommand(String description, String from, String to) {
        this.event = new Event(description, from, to);
    }

    /**
     * Executes the EventCommand by adding the event task to the TaskList,
     * displaying an addition message, and saving the updated TaskList to storage.
     * Saves the changes into the file.
     *
     * @param tasks         The list of tasks.
     * @param archiveTasks  The list of archive tasks.
     * @param ui            The Ui to interact with the user.
     * @param storage       The Storage to save the tasks to a file.
     * @param archived      The storage to save the archived tasks to a file.
     * @throws DukeException If there is an error while executing the command.
     */
    @Override
    public String execute(TaskList tasks, TaskList archiveTasks, Ui ui,
                          Storage storage, Storage archived) throws DukeException {
        tasks.add(event);
        storage.saveTask(tasks);
        return ui.showAddMsg(event, tasks.getTaskSize());
    }

    /**
     * Checks if the EventCommand is an exit command.
     *
     * @return false, as the EventCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isArchive() {
        return false;
    }
}
