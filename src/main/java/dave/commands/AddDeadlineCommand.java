package dave.commands;

import java.io.IOException;

import dave.Storage;
import dave.TaskList;
import dave.Ui;

import dave.tasks.Deadline;

public class AddDeadlineCommand extends Command {
    /** The Deadline object to be added. */
    private Deadline toAdd;

    /**
     * Creates new AddDeadlineCommand.
     * Parameters taken in are used to create the Deadline object.
     * 
     * @param taskName Name or description of the task.
     * @param deadline Deadline to finish the task by.
     */
    public AddDeadlineCommand(String taskName, String deadline) {
        toAdd = new Deadline(taskName, deadline);
    }

    /**
     * {@inheritDoc}
     * Adds a Deadline task to the task list and saves it to output file.
     * 
     * @return Feedback to user about successful task addition or error.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(toAdd);
        try {
            storage.saveTask(toAdd);
        } catch (IOException exc) {
            return "Dave could not write the new task to the output file";
        }
        return ui.showTaskAdded(toAdd, taskList);
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
