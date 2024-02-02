package dave.commands;

import dave.Storage;
import dave.TaskList;
import dave.Ui;

import dave.exceptions.UnableToFindTaskException;

public class ToggleTaskDoneCommand extends Command {
    /** The task to mark/unmark as done/not done. */
    private int taskNumber;
    /** The status of the task. True if done, false otherwise. */
    private boolean isDone;

    /**
     * Creates new ToggleTaskDoneCommand.
     * 
     * @param taskNumber Index of task to mark/unmark.
     * @param isDone Status of task to set to.
     */
    public ToggleTaskDoneCommand(int taskNumber, boolean isDone) {
        this.taskNumber = taskNumber;
        this.isDone = isDone;
    }

    /**
     * {@inheritDoc}
     * Marks the task as done if isDone = true, unmarks the task as not done otherwise.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.getTask(taskNumber).setDone(this.isDone);
            storage.rewriteOutput(taskList);
            if (this.isDone) {
                ui.showTaskMarked(taskList.getTask(taskNumber));
            } else {
                ui.showTaskUnmarked(taskList.getTask(taskNumber));
            }
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
