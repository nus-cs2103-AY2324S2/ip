package dave.commands;

import dave.Storage;
import dave.TaskList;
import dave.Ui;

import dave.tasks.Task;
import dave.exceptions.UnableToFindTaskException;

public class DeleteTaskCommand extends Command {
    /** The index of the task to be deleted. */
    private int taskNumber;

    /**
     * Creates new DeleteTaskCommand.
     * Takes in the index of the task to be deleted.
     * 
     * @param taskNumber Index of task in task list.
     */
    public DeleteTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * {@inheritDoc}
     * Deletes the task from the task list and output file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task toDelete = taskList.getTask(this.taskNumber);
            taskList.deleteTask(this.taskNumber);
            storage.rewriteOutput(taskList);
            ui.showTaskDeleted(toDelete, taskList);    
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
