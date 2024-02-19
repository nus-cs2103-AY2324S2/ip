package hal.command;

import hal.task.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.listMatchingTasks(keyword);
    }
}
