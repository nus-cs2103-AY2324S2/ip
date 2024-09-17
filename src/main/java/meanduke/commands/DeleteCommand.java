package meanduke.commands;

import meanduke.exceptions.MeanDukeException;
import meanduke.tasks.TaskList;

/**
 * This class represents a Command that adds deletes a Task from a TaskList
 */
public class DeleteCommand extends Command {
    private final int index;
    private final TaskList taskList;
    private final int visualIndex;

    /**
     * Constructs a DeleteCommand that deletes the task at the given index of the given TaskList.
     *
     * @param taskList TaskList from which the Task is to be deleted from.
     * @param index    Index of the Task to be deleted from taskList.
     */
    public DeleteCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
        this.visualIndex = index + 1;
    }

    /**
     * Returns the string describing the proper format for deleting a Task.
     */
    public static String getUsage() {
        return Command.getUsage() + " delete <taskIndex>";
    }

    @Override
    public String execute() throws MeanDukeException {
        try {
            return "deleted task:\n" + this.taskList.delete(this.index);
        } catch (IndexOutOfBoundsException e) {
            throw new MeanDukeException("Dude... you don't even have a task " + this.visualIndex);
        }
    }
}
