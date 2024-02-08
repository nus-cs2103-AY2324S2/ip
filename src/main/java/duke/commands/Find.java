package duke.commands;

import duke.TaskList;

public class Find extends Command {
    private TaskList userTasks;
    public Find(String[] cmd, TaskList userTasks) {
        super(cmd);
        this.userTasks = userTasks;
    }

    @Override
    public String execute() {
        super.validateArgs();
        TaskList searchResults = userTasks.searchTasks(cmd[1], userTasks);
        System.out.println(searchResults.stringifyTasks(true));
        return searchResults.stringifyTasks(true);
    }
}
