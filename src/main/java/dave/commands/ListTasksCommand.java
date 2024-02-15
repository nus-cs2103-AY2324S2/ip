package dave.commands;

import dave.Storage;
import dave.Ui;
import dave.TaskList;

public class ListTasksCommand extends Command {

    /**
     * {@inheritDoc}
     * Lists the current tasks in the task list.
     * 
     * @return Show task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String listTasks = "\nHere are the tasks in your list:\n";
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
