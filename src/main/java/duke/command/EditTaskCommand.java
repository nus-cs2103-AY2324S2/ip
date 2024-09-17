package duke.command;

import java.io.IOException;

import duke.JamieException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * Represents a command to edit attributes of a specific task in the task list.
 */
public class EditTaskCommand extends Command {
    private final int index;
    private final String attribute;
    private final String newValue;

    /**
     * Creates an EditTaskCommand to edit the specified attribute of a task.
     *
     * @param index     The index of the task in the task list to be edited.
     * @param attribute The attribute of the task to be edited.
     * @param newValue  The new value for the specified attribute.
     */
    public EditTaskCommand(int index, String attribute, String newValue) {
        this.index = index;
        this.attribute = attribute;
        this.newValue = newValue;
    }

    /**
     * Executes the edit task command, which modifies an attribute of a task in the task list.
     *
     * @param tasks   The TaskList containing the list of tasks.
     * @param ui      The Ui for interactions with the user.
     * @param storage The Storage to save changes to the task list.
     * @return A message indicating the result of the command execution.
     * @throws JamieException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamieException {
        try {
            Task task = tasks.getTask(index);
            switch (attribute.toLowerCase()) {
            case "by": // For Deadline
                if (task instanceof Deadline) {
                    ((Deadline) task).setBy(newValue);
                } else {
                    throw new JamieException("This task does not support the 'by' attribute.");
                }
                break;
            case "from": // For Event
                if (task instanceof Event) {
                    ((Event) task).setFrom(newValue);
                } else {
                    throw new JamieException("This task does not support the 'from' attribute.");
                }
                break;
            case "to": // For Event
                if (task instanceof Event) {
                    ((Event) task).setTo(newValue);
                } else {
                    throw new JamieException("This task does not support the 'to' attribute.");
                }
                break;
            default:
                throw new JamieException("Unknown attribute for editing.");
            }
            storage.save(tasks); // Save the updated task list
            return ui.showEditTaskMessage(task);
        } catch (IndexOutOfBoundsException e) {
            throw new JamieException("The task index provided is out of bounds.");
        } catch (IOException e) {
            throw new JamieException("An IO error occurred while saving the task list.");
        }
    }
}
