package dave.commands;

import java.io.IOException;

import dave.Storage;
import dave.TaskList;
import dave.Ui;

import dave.tasks.Todo;

public class AddTodoCommand extends Command {
    /** The Todo object to be added. */
    private Todo toAdd;

    /**
     * Creates new AddTodoCommand.
     * Parameters taken in are used to create the Todo object.
     * 
     * @param taskName Name or description of the task.
     */
    public AddTodoCommand(String taskName) {
        this.toAdd = new Todo(taskName);
    }

    /**
     * {@inheritDoc}
     * Creates new AddTodoCommand.
     * Adds a Todo task to the task list and saves it to output file.
     * 
     * @return Feedback to user about successful task addition or error.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.toAdd);
        try {
            storage.saveTask(this.toAdd);
        } catch (IOException exc) {
            return "Dave could not write the new task to the output file";
        }
        return ui.showTaskAdded(this.toAdd, taskList);
    }

    /**
     * {@inheritDoc}
     * Not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
