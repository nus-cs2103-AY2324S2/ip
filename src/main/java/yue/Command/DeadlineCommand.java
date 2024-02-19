package yue.Command;


import yue.Tasks.DeadlineTask;
import yue.Tasks.TaskList;
import yue.Storage;
import yue.YueException;
import yue.Tasks.Task;



/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String deadline;


    /**
     * Constructs a DeadlineCommand object with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline of the task.
     */
    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }


    /**
     * Executes the DeadlineCommand, adding a deadline task to the task list.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage handler.
     * @throws YueException If an error occurs during command execution.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws YueException {

        Task task = new DeadlineTask(description, deadline);
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

