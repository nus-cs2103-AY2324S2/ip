package duke.Command;


import duke.Tasks.EventTask;
import duke.Tasks.TaskList;
import duke.Storage;
import duke.DukeException;
import duke.Tasks.Task;



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
     * @param storage The storage handler.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = new EventTask(description, startTime, endTime);
        tasks.addTask(task);
        int count = tasks.size();

        String addedMessage = "    Got it. I've added this task:\n" + "      " + task + "\n" +
                "    Now you have " + count + " tasks in the list.\n";

        storage.save(tasks.getAllTasks());

        return addedMessage;
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

