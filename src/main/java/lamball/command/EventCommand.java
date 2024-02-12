package lamball.command;

import lamball.TaskList;

/**
 * Command that creates an event.
 */
public class EventCommand extends Command {
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
        taskList.event(args, isInit);
        return true;
    }
}
