package dave.commands;

import dave.Storage;
import dave.TaskList;
import dave.Ui;
import dave.exceptions.UnableToFindTaskException;

public class MarkTaskCommand extends Command {
    /** The index of the task to be marked as done. */
    private int taskNumber;

    /**
     * Creates new MarkTaskCommand.
     * Takes in the index of the task to be marked as done.
     * 
     * @param taskNumber Index of task to be marked.
     */
    public MarkTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * {@inheritDoc}
     * Marks the task as done.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.getTask(taskNumber).isCompleted();
            storage.rewriteOutput(taskList);
            ui.showTaskMarked(taskList.getTask(taskNumber));
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
