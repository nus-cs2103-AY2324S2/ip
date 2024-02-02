package duke.Command;

import duke.*;
import duke.Tasks.Task;
import duke.Tasks.TaskList;
import duke.Tasks.TodoTask;


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
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new TodoTask(description);
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

