package lamball.command;

import java.util.Objects;

import lamball.TaskList;


/**
 * Command that creates a deadline.
 */
public class DeadlineCommand extends Command {
    private static final int MINIMUM_ARG_COUNT = 2;
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
        assert (!Objects.isNull(this.args));
        assert this.args.length >= MINIMUM_ARG_COUNT;

        taskList.deadline(args, isInit);
        return true;
    }
}
