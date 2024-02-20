package tofu.command;

import tofu.TofuException;
import tofu.task.TaskList;
import tofu.ui.Ui;

public class ListCommand implements Command {

    /**
     * Display all the Task in TaskList.
     *
     * @param tasks the TaskList where the Task are in
     * @param ui the UI that will be used to display the message
     * @return a String of the UI message and a list of tasks that are in the TaskList
     */
    public String execute(TaskList tasks, Ui ui) throws TofuException {
        if (tasks.isEmpty()) {
            throw new TofuException(Ui.emptyListError());
        }
        return ui.listMessage(tasks.toString());
    }

    public boolean isExit() {
        return false;
    }
}
