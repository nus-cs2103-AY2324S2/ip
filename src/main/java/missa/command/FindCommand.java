package missa.command;

import missa.TaskList;
import missa.Ui;

/**
 * FindCommand class used to search for tasks.
 */
public class FindCommand extends Command {
    private String keyword;
    private String matchingTasks;
    private TaskList tasks;

    /**
     * Create a find command
     *
     * @param keyword Keyword used to search for tasks.
     * @param tasks Task list that stores all tasks.
     */
    public FindCommand(String keyword, TaskList tasks) {
        this.keyword = keyword;
        this.tasks = tasks;
    }

    @Override
    public TaskList execute() {
        matchingTasks = tasks.findTasks(keyword);
        return tasks;
    }

    @Override
    public String getReply(Ui ui) {
        return ui.replyFindCommand(matchingTasks);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
