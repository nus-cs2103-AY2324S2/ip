package dave.commands;

import dave.Storage;
import dave.TaskList;
import dave.Ui;
import dave.exceptions.UnableToFindTaskException;

public class UnmarkTaskCommand extends Command {
    /** The index of the task to be unmarked as not done. */
    private int taskNumber;

    /**
     * Creates new UnmarkTaskCommand.
     * Takes in the index of the task to be unmarked as not done.
     * 
     * @param taskNumber Index of task to be unmarked.
     */
    public UnmarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * {@inheritDoc}
     * Unmarks the task as not done, if previously marked as done.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.getTask(taskNumber).isNotCompleted();
            storage.rewriteOutput(taskList);
            ui.showTaskUnmarked(taskList.getTask(taskNumber));
        } catch (Exception exc) {
            ui.showHorizontalLine();
            System.out.println(new UnableToFindTaskException(taskList).getMessage());
            ui.showHorizontalLine();
        }
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
