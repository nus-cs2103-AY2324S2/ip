package lamball.command;

import lamball.TaskList;

/**
 * Command that creates a deadline.
 */
public class DeadlineCommand extends Command {
    private TaskList taskList;
    private String[] args;
    private boolean isInit;

    /**
     * Constructor for deadline command.
     *
     * @param args Arguments for creation of deadline.
     * @param tasks TaskList to create deadline in.
     * @param isInit Whether this command is executed during initialization.
     */
    public DeadlineCommand(String[] args, TaskList tasks, boolean isInit) {
        this.taskList = tasks;
        this.args = args;
        this.isInit = isInit;
    }

    @Override
    public boolean run() {
        taskList.deadline(args, isInit);
        return true;
    }
}
