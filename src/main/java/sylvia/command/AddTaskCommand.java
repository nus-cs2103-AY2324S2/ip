package sylvia.command;

import sylvia.SylviaException;
import sylvia.state.ProgramState;
import sylvia.task.Task;
import sylvia.task.TaskList;

/**
 * Represents a command to add a task.
 */
public abstract class AddTaskCommand extends Command implements Undoable {
    private Task task;

    /**
     * Constructs a new add task command.
     *
     * @param body The body of the command.
     */
    public AddTaskCommand(String body) {
        super(body);
    }

    /**
     * Creates a task from the description and sets it as the task of the command.
     * This method is to be called in the execute method of the subclass.
     *
     * @throws SylviaException
     */
    private void setTaskFromDescription() throws SylviaException {
        this.task = getTaskFromDescription(getBody());
    };

    /**
     * Creates a task from the description. This method is to be overridden by the
     * subclasses.
     *
     * @param description
     * @return The task created from the description.
     * @throws SylviaException
     */
    protected abstract Task getTaskFromDescription(String description) throws SylviaException;

    /**
     * Executes the command. This command adds a task to the task list. The program
     * state is set to normal after the command is executed, even if the command
     * fails to execute.
     *
     * @param list  The task list to be modified.
     * @param state The program state to be modified.
     * @return The response to be displayed to the user.
     * @throws SylviaException If the command fails to execute.
     */
    @Override
    public String execute(TaskList list, ProgramState state) throws SylviaException {
        this.setTaskFromDescription();
        list.addTask(task);
        state.setNormal();
        state.addCommandToHistory(this);
        assert state.isRedoable() == false
                : "There should be no redoable commands after a Undoable command is executed.";
        return "Added: " + task + "\nYou now have " + list.size() + " tasks in the list.";
    }

    @Override
    public String undo(TaskList list, ProgramState state) throws SylviaException {
        list.removeTask(this.task);
        state.setNormal();
        return "Task removed: " + task;
    }

    @Override
    public String redo(TaskList list, ProgramState state) throws SylviaException {
        list.addTask(this.task);
        state.setNormal();
        return "Task added: " + task;
    }
}
