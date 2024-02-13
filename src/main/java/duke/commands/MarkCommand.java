package duke.commands;

import duke.exceptions.MeanDukeException;
import duke.tasks.TaskList;

/**
 * This class represents a Command that marks the Task at the given index of the given TaskList as done.
 */
public class MarkCommand extends Command {
    private final int index;
    private final TaskList taskList;

    /**
     * Constructs a new MarkCommand that marks the Task at the given index of the given TaskList as done.
     *
     * @param taskList TaskList containing Task to be marked as done.
     * @param index    Index of Task in taskList to be marked as done.
     */
    public MarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
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
            if (this.taskList.markDone(index)) {
                //Task successfully changed from not done to done
                return "Marked task: " + (index + 1) + " as completed.\nCould you have taken any longer?";
            } else {
                //Task was already completed
                return "You have already marked task " + (index + 1) + " as completed you goldfish...";
            }
        } catch (IndexOutOfBoundsException e) {
            throw new MeanDukeException("Dude... you don't even have a task " + (index + 1));
        }
    }
}
