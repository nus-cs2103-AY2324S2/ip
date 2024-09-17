package meanduke.commands;

import meanduke.exceptions.MeanDukeException;
import meanduke.tasks.TaskList;

/**
 * This class represents a Command that marks the Task at the given index of the given TaskList as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;
    private final TaskList taskList;
    private final int visualIndex;

    /**
     * Constructs a new UnmarkCommand that marks the Task at the given index of the given TaskList as not done.
     *
     * @param taskList TaskList containing Task to be marked as not done.
     * @param index    Index of Task in taskList to be marked as not done.
     */
    public UnmarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
        this.visualIndex = index + 1;
    }

    /**
     * Returns the string describing the proper format to mark a Task in a TaskList as not done.
     */
    public static String getUsage() {
        return Command.getUsage() + " unmark <taskIndex>";
    }

    @Override
    public String execute() throws MeanDukeException {
        try {
            if (taskList.unmarkDone(this.index)) {
                //Task successfully changed from done to not done
                return "Marked task: " + this.visualIndex
                        + " as not completed.\nWhy did you mark this in the first place?";
            } else {
                //Task was already not complete
                return "Task " + this.visualIndex
                        + " is already not completed.\nMaybe you should start working on it.";
            }
        } catch (IndexOutOfBoundsException e) {
            throw new MeanDukeException("Dude... you don't even have a task " + this.visualIndex);
        }
    }
}
