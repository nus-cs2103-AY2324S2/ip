package dave.commands;

import java.io.IOException;

import dave.Storage;
import dave.TaskList;
import dave.Ui;

import dave.tasks.Event;

public class AddEventCommand extends Command {
    /** The Event object to be added. */
    private Event toAdd;

    /**
     * Creates new AddEventCommand.
     * Parameters taken in are used to create the Event object.
     * 
     * @param taskName Name or description of the task.
     * @param from Time when event starts.
     * @param to Time when event ends.
     */
    public AddEventCommand(String taskName, String from, String to) {
        this.toAdd = new Event(taskName, from, to);
    }

    /**
     * {@inheritDoc}
     * Adds an Event task to the task list and saves it to output file.
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
