package commands;

import java.time.LocalDateTime;

import exceptions.CalException;
import storage.StorageManager;
import tasklist.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    protected Task task;
    protected boolean status = false;

    /**
     * Constructs an AddCommand with Todo task
     *
     * @param description     task description
     */
    public AddCommand(String description) {
        this.task = new Todo(description);
    }

    /**
     * Constructs an AddCommand with Deadline task
     *
     * @param description     task description
     * @param by              task due date
     */
    public AddCommand(String description, LocalDateTime by) {
        this.task = new Deadline(description, by);
    }

    /**
     * Constructs an AddCommand with Event task
     *
     * @param description     event description
     * @param startDate       event start date
     * @param startDate       event end date
     */
    public AddCommand(String description, LocalDateTime startDate, LocalDateTime endDate) {
        this.task = new Event(description, startDate, endDate);
    }

    /**
     * Executes the AddCommand, adding the task to the task list and saving the changes.
     *
     * @param tasks          The task list where the task will be added.
     * @param storageManager The storage manager to save the changes.
     * @return String print output.
     * @throws CalException
     */
    public String execute(TaskList tasks, StorageManager storageManager) throws CalException {
        task.setStatus(status);
        tasks.add(task);
        storageManager.save(tasks);
        StringBuilder output = new StringBuilder();
        output.append("Got it. I've added this task:\n");
        output.append(task).append("\n");
        output.append(String.format("Now you have %d tasks in the list.\n", tasks.getSize()));
        return output.toString();
    }

    /**
     * Indicates whether the AddCommand is an exit command.
     *
     * @return False, as AddCommand is not an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
