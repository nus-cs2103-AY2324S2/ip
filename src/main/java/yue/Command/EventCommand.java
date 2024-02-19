package yue.Command;


import yue.Tasks.EventTask;
import yue.Tasks.TaskList;
import yue.Storage;
import yue.YueException;
import yue.Tasks.Task;



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
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        assert startTime != null && !startTime.isEmpty() : "Start time cannot be null or empty";
        assert endTime != null && !endTime.isEmpty() : "End time cannot be null or empty";

        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    /**
     * Executes the EventCommand, adding an event task to the task list.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage handler.
     * @throws YueException If an error occurs during command execution.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws YueException {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";

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

