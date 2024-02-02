package duke.Command;

import duke.*;
import duke.Tasks.*;
import duke.Tasks.TaskList;


/**
 * Represents a command to add an event task.
 */

public class EventCommand extends Command {
    private String description;
    private String startTime;
    private String endTime;


    /**
     * Constructs an EventCommand object with the given description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param startTime   The start time of the event task.
     * @param endTime     The end time of the event task.
     */
    public EventCommand(String description, String startTime, String endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    /**
     * Executes the EventCommand, adding an event task to the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new EventTask(description, startTime, endTime);
        tasks.addTask(task);
        int count = tasks.size();
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

