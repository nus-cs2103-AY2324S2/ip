package yue.command;

import yue.Storage;
import yue.YueException;
import yue.tasks.Task;
import yue.tasks.TaskList;
import yue.tasks.TodoTask;


/**
 * Represents a command to add a new todo task.
 */
public class TodoCommand extends Command {
    private String description;


    /**
     * Constructs a TodoCommand object with the given task description.
     *
     * @param description The description of the todo task to be added.
     */
    public TodoCommand(String description) {
        this.description = description;
    }



    /**
     * Executes the TodoCommand, adding a new todo task to the task list.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage handler.
     * @throws YueException If an error occurs during command execution.
     */

    @Override
    public String execute(TaskList tasks, Storage storage) throws YueException {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";

        Task task = new TodoTask(description);
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

