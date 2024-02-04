package duke.commands;

import duke.exceptions.MeanDukeException;
import duke.tasks.TaskList;
import duke.core.Ui;

public class MarkCommand extends Command {
    private int index;
    private TaskList taskList;

    public MarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    public static String getUsage() {
        return Command.getUsage() + " mark <taskIndex>";
    }

    @Override
    public void execute() throws MeanDukeException {
        try {
            if (this.taskList.markDone(index)) {
                //Task successfully changed from not done to done
                Ui.printMessage("Marked task: " + (index + 1) + " as completed.\nCould you have taken any longer?");
            } else {
                //Task was already completed
                Ui.printMessage("You have already marked task " + (index + 1) + " as completed you goldfish...");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new MeanDukeException("Dude... you don't even have a task " + (index + 1));
        }
    }
}
