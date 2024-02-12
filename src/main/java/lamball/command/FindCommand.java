package lamball.command;

import lamball.TaskList;

/**
 * Command that finds a task.
 */
public class FindCommand extends Command {
    private TaskList taskList;
    private String args;

    /**
     * Constructor for command.
     *
     * @param args Argument to search for.
     * @param tasks TaskList to search in.
     */
    public FindCommand(String args, TaskList tasks) {
        this.taskList = tasks;
        this.args = args;
    }

    @Override
    public boolean run() {
        taskList.find(args);
        return true;
    }

}
