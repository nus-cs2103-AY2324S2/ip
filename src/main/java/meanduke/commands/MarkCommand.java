package meanduke.commands;

import meanduke.exceptions.MeanDukeException;
import meanduke.tasks.TaskList;

/**
 * This class represents a Command that marks the Task at the given index of the given TaskList as done.
 */
public class MarkCommand extends Command {
    private final int index;
    private final TaskList taskList;
    private final int visualIndex;

    /**
     * Constructs a new MarkCommand that marks the Task at the given index of the given TaskList as done.
     *
     * @param taskList TaskList containing Task to be marked as done.
     * @param index    Index of Task in taskList to be marked as done.
     */
    public MarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
        this.visualIndex = index + 1;
    }

    /**
     * Returns the string describing the proper format to mark a Task in a TaskList as done.
     */
    public static String getUsage() {
        return Command.getUsage() + " mark <taskIndex>";
    }

    @Override
    public String execute() throws MeanDukeException {
        try {
            if (this.taskList.markDone(this.index)) {
                //Task successfully changed from not done to done
                return "Marked task: " + this.visualIndex + " as completed.\nCould you have taken any longer?";
            } else {
                //Task was already completed
                return "You have already marked task " + this.visualIndex + " as completed you goldfish...";
            }
        } catch (IndexOutOfBoundsException e) {
            throw new MeanDukeException("Dude... you don't even have a task " + this.visualIndex);
        }
    }
}
