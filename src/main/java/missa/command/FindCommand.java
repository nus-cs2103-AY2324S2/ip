package missa.command;

import missa.TaskList;
import missa.Ui;

/**
 * FindCommand class used to search for tasks.
 */
public class FindCommand extends Command {
    private String keyword;
    private TaskList tasks;

    public FindCommand(String keyword, TaskList tasks) {
        this.keyword = keyword;
        this.tasks = tasks;
    }

    @Override
    public TaskList execute(Ui ui) {
        String matchingTasks = tasks.findTasks(keyword);
        System.out.println(ui.replyFindCommand(matchingTasks));
        return tasks;
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
