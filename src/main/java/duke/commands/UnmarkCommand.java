package duke.commands;

import duke.exceptions.MeanDukeException;
import duke.tasks.TaskList;
import duke.core.Ui;

public class UnmarkCommand extends Command {
    private int index;
    private TaskList taskList;

    public UnmarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    public static String getUsage() {
        return Command.getUsage() + " unmark <taskIndex>";
    }

    @Override
    public void execute() throws MeanDukeException {
        try {
            if (taskList.unmarkDone(index)) {
                //Task successfully changed from done to not done
                Ui.printMessage("Marked task: " + (index + 1) + " as not completed.\nWhy did you mark this in the first place?");
            } else {
                //Task was already not complete
                Ui.printMessage("Task " + (index + 1) + " is already not completed.\nMaybe you should start working on it.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new MeanDukeException("Dude... you don't even have a task " + (index + 1));
        }
    }
}
