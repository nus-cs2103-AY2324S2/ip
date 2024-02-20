package tofu.command;

import tofu.TofuException;
import tofu.task.TaskList;
import tofu.ui.Ui;

public class FindCommand implements Command {
    private String keyword;

    /**
     * Initializes a new FindCommand to search for tasks based on a keyword.
     *
     * @param keyword The keyword used to search the task descriptions.
     */
    public FindCommand(String  keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches for tasks in the TaskList that match the keyword and returns a UI message.
     *
     * @param tasks The TaskList to be searched.
     * @param ui The UI used to generate the message.
     * @return A string representing the UI message and the list of matching tasks.
     * @throws TofuException If no matching tasks are found in the TaskList.
     */
    public String execute(TaskList tasks, Ui ui) throws TofuException {
        TaskList foundList = tasks.find(keyword);
        if (foundList.isEmpty()) {
            throw new TofuException(Ui.noMatchError());
        }
        return ui.findMessage(foundList.toString());
    }

    public boolean isExit() {
        return false;
    }
}
