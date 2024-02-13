package duke.commands;

import duke.exceptions.MeanDukeException;
import duke.tasks.TaskList;

/**
 * This class represents a Command that marks the Task at the given index of the given TaskList as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;
    private final TaskList taskList;

    /**
     * Constructs a new UnmarkCommand that marks the Task at the given index of the given TaskList as not done.
     *
     * @param taskList TaskList containing Task to be marked as not done.
     * @param index    Index of Task in taskList to be marked as not done.
     */
    public UnmarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
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
            if (taskList.unmarkDone(index)) {
                //Task successfully changed from done to not done
                return "Marked task: " + (index + 1)
                        + " as not completed.\nWhy did you mark this in the first place?";
            } else {
                //Task was already not complete
                return "Task " + (index + 1)
                        + " is already not completed.\nMaybe you should start working on it.";
            }
        } catch (IndexOutOfBoundsException e) {
            throw new MeanDukeException("Dude... you don't even have a task " + (index + 1));
        }
    }
}
