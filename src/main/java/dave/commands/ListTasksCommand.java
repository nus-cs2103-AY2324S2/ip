package dave.commands;

import dave.Storage;
import dave.Ui;
import dave.TaskList;

public class ListTasksCommand extends Command {
    private static final String LIST_TASKS_MSG = "Here are the tasks in your list:\n";

    /**
     * {@inheritDoc}
     * Lists the current tasks in the task list.
     * 
     * @return Show task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String listTasks = LIST_TASKS_MSG;
        for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
            listTasks += String.format("\n%d. %s", i+1, taskList.getTask(i).toString());
        }
        return listTasks;
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
