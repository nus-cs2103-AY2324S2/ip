package dave.commands;

import dave.Storage;
import dave.Ui;
import dave.TaskList;
import dave.tasks.Task;

public class FindTaskCommand extends Command {
    /** The keyword to use for finding tasks. */
    private String keyword;

    /**
     * Creates new FindTaskCommand.
     * 
     * @param keyword The keyword to use for searching.
     */
    public FindTaskCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    /**
     * {@inheritDoc}
     * Finds the tasks in the task list that contain the keyword.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showHorizontalLine();
        System.out.println("\nDave has found the following matching tasks:\n");
        int noOfResults = 0;
        for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
            Task currTask = taskList.getTask(i);
            if (currTask.getTaskName().contains(this.keyword)) {
                System.out.println(String.format("%d. %s", noOfResults+1, taskList.getTask(i).toString()));
                noOfResults++;
            }
        }
        ui.showHorizontalLine();
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
