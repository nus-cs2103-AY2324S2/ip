package seedu.chatteroo.commands;

import seedu.chatteroo.tasks.TaskList;
import seedu.chatteroo.ui.Ui;
import seedu.chatteroo.storage.Storage;

/**
 * Finds the tasks that contain the specified keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for the FindCommand class.
     * @param keyword The keyword to be searched.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the specified command by finding the tasks that contain the keyword.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int listCount = tasks.getTaskListSize();
        int count = 0;
        for (int i = 0; i < listCount; i++) {
            if (tasks.getTask(i).getDescription().contains(keyword)) {
                count++;
                if (count == 1) {
                    System.out.println("Here are the matching tasks in your list:");
                }
                System.out.println(count + ". " + tasks.getTask(i).toString());
            }
        }
        if (count == 0) {
            System.out.println("There are no matching tasks in your list.");
        }
        return null;
    }
}
