package duke.command;

import duke.DukeException;
import duke.state.ProgramState;
import duke.task.Task;
import duke.task.TaskList;

public abstract class AddTaskCommand extends Command implements Undoable {
    private Task task;

    public AddTaskCommand(String body) {
        super(body);
    }

    /**
     * Creates a task from the description and sets it as the task of the command.
     * This method is to be called in the execute method of the subclass.
     *
     * @throws DukeException
     */
    private void setTaskFromDescription() throws DukeException {
        this.task = getTaskFromDescription(getBody());
    };

    /**
     * Creates a task from the description. This method is to be overridden by the
     * subclasses.
     *
     * @param description
     * @return
     * @throws DukeException
     */
    protected abstract Task getTaskFromDescription(String description) throws DukeException;

    /**
     * Executes the command. This command adds a task to the task list. The program
     * state is set to normal after the command is executed, even if the command
     * fails to execute.
     *
     * @param list  The task list to be modified.
     * @param state The program state to be modified.
     * @return The response to be displayed to the user.
     * @throws DukeException If the command fails to execute.
     */
    @Override
    public String execute(TaskList list, ProgramState state) throws DukeException {
        this.setTaskFromDescription();
        list.addTask(task);
        state.setNormal();
        return "Added: " + task + "\nYou now have " + list.size() + " tasks in the list.";
    }

    @Override
    public String undo(TaskList list, ProgramState state) throws DukeException {
        list.removeTask(this.task);
        state.setNormal();
        return "Task removed: " + task;
    }

    @Override
    public String redo(TaskList list, ProgramState state) throws DukeException {
        list.addTask(this.task);
        state.setNormal();
        return "Task added: " + task;
    }
}
