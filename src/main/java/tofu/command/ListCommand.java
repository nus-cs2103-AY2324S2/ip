package tofu.command;

import tofu.TofuException;
import tofu.task.TaskList;
import tofu.ui.Ui;

public class ListCommand implements Command {

    /**
     * Display all the tasks in TaskList using the UI.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     * @param ui The UI used to generate the message.
     * @return A string representing the UI message and the list of tasks in the TaskList.
     * @throws TofuException If the TaskList is empty.
     */
    public String execute(TaskList tasks, Ui ui) throws TofuException {
        if (tasks.isEmpty()) {
            throw new TofuException(Ui.emptyListError());
        }
        return ui.listMessage(tasks.toString());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
    public boolean isExit() {
        return false;
    }
}
