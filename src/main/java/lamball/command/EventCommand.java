package lamball.command;

import java.util.Objects;

import lamball.TaskList;

/**
 * Command that creates an event.
 */
public class EventCommand extends Command {
    private static final int MINIMUM_ARG_COUNT = 3;
    private TaskList taskList;
    private String[] args;
    private boolean isInit;

    /**
     * Constructor for Event command.
     *
     * @param args Arguments to create event task.
     * @param tasks TaskList to add task to.
     * @param isInit Whether this is run during initialization.
     */
    public EventCommand(String[] args, TaskList tasks, boolean isInit) {
        this.taskList = tasks;
        this.args = args;
        this.isInit = isInit;
    }

    @Override
    public boolean run() {
        assert (!Objects.isNull(this.args));
        assert this.args.length >= MINIMUM_ARG_COUNT;

        taskList.event(this.args, isInit);
        return true;
    }
}
