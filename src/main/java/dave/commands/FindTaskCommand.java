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
    public FindTaskCommand(String keywordToFind) {
        keyword = keywordToFind.trim();
    }

    /**
     * {@inheritDoc}
     * Finds the tasks in the task list that contain the keyword.
     * 
     * @return Show tasks matching the keyword.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String loadResult = "Dave has found the following matching tasks:";
        int noOfResults = 0;
        for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
            Task currTask = taskList.getTask(i);
            if (currTask.getTaskName().contains(keyword)) {
                loadResult += String.format("\n%d. %s", noOfResults+1, taskList.getTask(i).toString());
                noOfResults++;
            }
        }
        return loadResult;
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
